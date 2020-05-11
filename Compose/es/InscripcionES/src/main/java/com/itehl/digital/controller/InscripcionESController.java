package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.controller.dto.InscripcionDTO;
import com.itehl.digital.repository.IInscripcionRepository;
import com.itehl.digital.repository.model.InscripcionModel;
import com.itehl.digital.service.IInscripcionSvc;
import com.itehl.digital.service.InscripcionSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/es/inscripcion/v1")
@Component
public class InscripcionESController {

    private static final Logger LOG = LoggerFactory.getLogger(InscripcionESController.class);
    @Autowired
    IInscripcionSvc inscripcionSvc;
    private final KafkaTemplate<String, InscripcionDTO> kafkaTemplate;

    public InscripcionESController(IInscripcionRepository inscripcionRepository,
                                   KafkaTemplate<String, InscripcionDTO> kafkaTemplate) {

        inscripcionSvc = new InscripcionSvc(inscripcionRepository);
        this.kafkaTemplate = kafkaTemplate;
    }



    @Autowired
    public InscripcionESController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionModel crearInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {

        LOG.info("Start crearInscripcion");

        //Mensaje del  evento "inscriptionCreated"
        Message<InscripcionDTO> message1 = MessageBuilder
                .withPayload(inscripcionDTO)
                .setHeader(KafkaHeaders.TOPIC, "inscriptionCreated")
                .build();

        //Mensaje del  evento "inscriptionReplicated"
        Message<InscripcionDTO> message2 = MessageBuilder
                .withPayload(inscripcionDTO)
                .setHeader(KafkaHeaders.TOPIC, "inscriptionReplicated")
                .build();

        InscripcionModel inscripcionModel = inscripcionSvc.crearInscripcion(convertToModel(inscripcionDTO));

        LOG.info("Publish event inscriptionCreated");
        kafkaTemplate.send(message1);

        LOG.info("Publish event inscriptionReplicated");
        kafkaTemplate.send(message2);

        LOG.info("End crearInscripcion");

        return inscripcionModel;

    }

    @PutMapping("/actualizar")
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionModel actualizarInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {

        LOG.info("Start actualizarInscripcion");

        Message<InscripcionDTO> message = MessageBuilder
                .withPayload(inscripcionDTO)
                .setHeader(KafkaHeaders.TOPIC, "inscriptionUpdated")
                .build();

        InscripcionModel inscripcionModel = inscripcionSvc.crearInscripcion(convertToModel(inscripcionDTO));

        LOG.info("Publish event inscriptionUpdated");
        kafkaTemplate.send(message);

        LOG.info("End actualizarInscripcion");

        return inscripcionModel;

    }

    @PutMapping("/aprobar")
    @ResponseStatus(HttpStatus.CREATED)
    public void aprobarInscripcion(@RequestBody Map<String, String> body) {

        LOG.info("Start aprobarInscripcion");

        // Se obtiene del body el valor del id de la inscripción
        String idInscripcion =  body.get("idInscripcion");

        // Se aprueba la inscripción
        inscripcionSvc.aprobarInscripcion(idInscripcion);

        //Se genera el evento de actualización para la réplica de datos
        actualizarInscripcion(convertToDTO(inscripcionSvc.consultarInscripcionPorId(idInscripcion)));

        LOG.info("End aprobarInscripcion");
    }

    @PutMapping("/rechazar")
    @ResponseStatus(HttpStatus.CREATED)
    public void rechazarInscripcion(@RequestBody Map<String, String> body) {

        LOG.info("Start rechazarInscripcion");
        // Se obtiene del body el valor del id de la inscripción
        String idInscripcion =  body.get("idInscripcion");

        // Se rechaza la inscripción
        inscripcionSvc.rechazarInscripcion(idInscripcion);

        //Se genera el evento de actualización para la réplica de datos
        actualizarInscripcion(convertToDTO(inscripcionSvc.consultarInscripcionPorId(idInscripcion)));

        LOG.info("End rechazarInscripcion");

    }

    protected InscripcionModel convertToModel(InscripcionDTO inscripcionDTO) {

        return new InscripcionModel(inscripcionDTO.getIdInscripcion(),
                                    inscripcionDTO.getIdCurso(),
                                    inscripcionDTO.getIdCliente(),
                                    inscripcionDTO.getCurso(),
                                    inscripcionDTO.getNombre(),
                                    inscripcionDTO.getEstado());

    }

    protected InscripcionDTO convertToDTO(InscripcionModel inscripcionModel) {

        return new InscripcionDTO(inscripcionModel.getIdInscripcion(),
                inscripcionModel.getIdCurso(),
                inscripcionModel.getIdCliente(),
                inscripcionModel.getCurso(),
                inscripcionModel.getNombre(),
                inscripcionModel.getEstado());

    }
}
