package com.itehl.digital.consumers;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.controller.dto.ClienteDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteREST {

    RestTemplate restTemplate = new RestTemplate();

    public ClienteDTO consultarClientePorId(String idCliente){

        ResponseEntity<ClienteDTO> restExchange =
                restTemplate.exchange(
                        "http://es-cliente-svc:1005/es/cliente/v1/{idCliente}",
                        HttpMethod.GET,
                        null, ClienteDTO.class, idCliente);

        return restExchange.getBody();
    }



}


