package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import com.itehl.digital.controller.dto.InscripcionDTO;
import com.itehl.digital.repository.IConsultarInscripcionesRepository;
import com.itehl.digital.repository.model.InscripcionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/ms/consultarinscripciones/v1")
@Component
public class ConsultarInscripcionesController {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultarInscripcionesController.class);
    final
    IConsultarInscripcionesRepository consultarInscripcionesRepository;

    public ConsultarInscripcionesController(IConsultarInscripcionesRepository consultarInscripcionesRepository) {
        this.consultarInscripcionesRepository = consultarInscripcionesRepository;
    }

    @KafkaListener(topics = "inscriptionReplicated")
    public void procesarMensajeCreacion(@Payload InscripcionDTO inscripcionDTO,
                                @Headers MessageHeaders headers){

        LOG.info("Start procesarMensajeCreacion");

        consultarInscripcionesRepository.save(convertToModel(inscripcionDTO));

        LOG.info("End procesarMensajeCreacion");
    }

    @KafkaListener(topics = "inscriptionUpdated")
    public void procesarMensajeActualizacion(@Payload InscripcionDTO inscripcionDTO,
                                @Headers MessageHeaders headers){

        LOG.info("Start procesarMensajeActualizacion");

        InscripcionModel inscripcionModelToUpdate = consultarInscripcionesRepository.findById(inscripcionDTO.getIdInscripcion()).get();

        if(inscripcionModelToUpdate != null)
        {

            inscripcionModelToUpdate.setNombre(inscripcionDTO.getNombre());
            inscripcionModelToUpdate.setIdInscripcion(inscripcionDTO.getIdInscripcion());
            inscripcionModelToUpdate.setIdCliente(inscripcionDTO.getIdCliente());
            inscripcionModelToUpdate.setIdCurso(inscripcionDTO.getIdCurso());
            inscripcionModelToUpdate.setCurso(inscripcionDTO.getCurso());
            inscripcionModelToUpdate.setEstado(inscripcionDTO.getEstado());

            consultarInscripcionesRepository.save(inscripcionModelToUpdate);
        }
        else{
            consultarInscripcionesRepository.save(convertToModel(inscripcionDTO));
        }

        LOG.info("End procesarMensajeActualizacion");

    }

    @GetMapping
    public ArrayList<InscripcionModel> consultarInscripciones() {

        LOG.info("Start consultarInscripciones");

        ArrayList<InscripcionModel> inscripcionModel = (ArrayList<InscripcionModel>) consultarInscripcionesRepository.findAll();

        LOG.info("End consultarInscripciones");

        return inscripcionModel;

    }

    protected InscripcionModel convertToModel(InscripcionDTO inscripcionDTO) {

        return new InscripcionModel(inscripcionDTO.getIdInscripcion(),
                inscripcionDTO.getIdCurso(),
                inscripcionDTO.getIdCliente(),
                inscripcionDTO.getCurso(),
                inscripcionDTO.getNombre(),
                inscripcionDTO.getEstado());

    }

}
