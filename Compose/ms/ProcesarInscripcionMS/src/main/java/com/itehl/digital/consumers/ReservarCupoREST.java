package com.itehl.digital.consumers;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReservarCupoREST {

    RestTemplate restTemplate = new RestTemplate();

    public ReservarCupoREST() {

    }

    public boolean reservarCupo(String idCurso){


        Map<String, String> body = new HashMap<>();
        body.put("idCurso",idCurso);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<Map<String, String>>(body,headers);

        ResponseEntity<Boolean> restExchange = restTemplate.exchange(
                "http://ms-reservarcupo-svc:1002/ms/reservarcupo/v1", HttpMethod.POST, request, Boolean.class);


        return restExchange.getBody().booleanValue();
    }

}
