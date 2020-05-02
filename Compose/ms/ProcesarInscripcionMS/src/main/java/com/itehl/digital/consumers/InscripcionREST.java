package com.itehl.digital.consumers;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class InscripcionREST {

    RestTemplate restTemplate = new RestTemplate();

    public void aprobar(String idInscripcion){


        Map<String, String> body = new HashMap<>();
        body.put("idInscripcion",idInscripcion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<Map<String, String>>(body,headers);

        ResponseEntity<Boolean> restExchange = restTemplate.exchange(
                "http://itehl-es-inscripcion-api:1003/es/inscripcion/v1/aprobar", HttpMethod.PUT, request, Boolean.class);


    }


    public void rechazar(String idInscripcion){

        Map<String, String> body = new HashMap<>();
        body.put("idInscripcion",idInscripcion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<Map<String, String>>(body,headers);

        ResponseEntity<Boolean> restExchange = restTemplate.exchange(
                "http://itehl-es-inscripcion-api:1003/es/inscripcion/v1/rechazar", HttpMethod.PUT, request, Boolean.class);

    }
}
