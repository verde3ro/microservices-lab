package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.controller.dto.CorreoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/us/email/v1")
public class CorreoUSAmbassadorController {

    private static final Logger LOG = LoggerFactory.getLogger(CorreoUSAmbassadorController.class);
    RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean enviarMensaje(@RequestBody CorreoDTO correoDTO) {

        LOG.info("Start Ambassador enviarMensaje");

        Map<String, String> params = new HashMap<>();
        params.put("to",correoDTO.getTo());
        params.put("message",correoDTO.getMensaje());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<Map<String, String>>(params,headers);

        restTemplate.exchange("https://azure-function-email-1593648448112.azurewebsites.net/api/emailus/?to={to}&message={message}", HttpMethod.POST, request, String.class, params );

        LOG.info("End Ambassador enviarMensaje");

        return true;

    }


}
