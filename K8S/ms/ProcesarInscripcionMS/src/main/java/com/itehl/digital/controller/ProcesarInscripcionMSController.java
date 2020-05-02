package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import com.itehl.digital.consumers.*;
import com.itehl.digital.controller.dto.ClienteDTO;
import com.itehl.digital.controller.dto.CorreoDTO;
import com.itehl.digital.controller.dto.CursoDTO;
import com.itehl.digital.controller.dto.InscripcionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/ms/procesarinscripcion/v1")
@Component
public class ProcesarInscripcionMSController {

    private static final Logger LOG = LoggerFactory.getLogger(ProcesarInscripcionMSController.class);
    private RestTemplate restTemplate;

    ClienteREST clienteREST = new ClienteREST();
    CursoREST cursoREST = new CursoREST();
    InscripcionREST inscripcionREST = new InscripcionREST();
    ReservarCupoREST reservarCupoREST = new ReservarCupoREST();
    EnviarCorreoPlantillaREST enviarCorreoPlantillaREST = new EnviarCorreoPlantillaREST();

    public ProcesarInscripcionMSController(){

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String procesar(){

        return "Publica un mensaje en el tópico de inscriptionCreated";

    }

    @KafkaListener(topics = "inscriptionCreated")
    public void procesarMensaje(@Payload InscripcionDTO inscripcionDTO,
                                @Headers MessageHeaders headers){

        LOG.info("Start Subscribe Event inscriptionCreated");

        //Consume el servicio
        boolean esAprobado = reservarCupoREST.reservarCupo(inscripcionDTO.getIdCurso());

        if(esAprobado){

           LOG.info("Inscripción Aprobada");
           inscripcionREST.aprobar(inscripcionDTO.getIdInscripcion());
           enviarCorreo(inscripcionDTO.getIdCurso(),inscripcionDTO.getIdCliente());

       }else {

           LOG.info("Inscripción Rechazada");
           inscripcionREST.rechazar(inscripcionDTO.getIdInscripcion());
       }

        LOG.info("End Subscribe Event inscriptionCreated");

    }

    private void enviarCorreo(String idCurso, String idCliente ){

        LOG.info("Start enviarCorreo procesar inscripción");

        ClienteDTO clienteDTO = clienteREST.consultarClientePorId(idCliente);
        CursoDTO cursoDTO = cursoREST.consultarCursoPorId(idCurso);
        CorreoDTO correoDTO = new CorreoDTO(clienteDTO.getEmail(),"",clienteDTO.getNombres(),cursoDTO.getNombre(),"EMAIL_INSCRIPTION","");

        enviarCorreoPlantillaREST.enviarCorre(correoDTO);

        LOG.info("End enviarCorreo procesar inscripción");
    }

}
