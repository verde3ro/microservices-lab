package com.itehl.digital.consumers;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import com.itehl.digital.controller.dto.CorreoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpServerErrorException;

public class EnviarCorreoPlantillaREST {

   private static final Logger LOG = LoggerFactory.getLogger(EnviarCorreoPlantillaREST.class);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Boolean> restExchange;

    public boolean enviarCorre(CorreoDTO correoDTO){

       String mensaje = "Hola " + correoDTO.getNombre() + "\n \n" +
                "Bienvenido al curso " + correoDTO.getCurso() + "." +  "\n \n" +
                "Itehl Digital.";

        correoDTO.setMensaje(mensaje);
    try {

        restExchange = restTemplate.postForEntity("http://itehl-us-correo-api:1008/us/email/v1", correoDTO, Boolean.class);

    }catch (HttpServerErrorException.InternalServerError error){
        //Se hace el llamado por localhost cuando se despliega el servicio como parte del mismo POD
        restExchange = restTemplate.postForEntity("http://localhost:1008/us/email/v1", correoDTO, Boolean.class);
    }

       return restExchange.getBody().booleanValue();

    }
}
