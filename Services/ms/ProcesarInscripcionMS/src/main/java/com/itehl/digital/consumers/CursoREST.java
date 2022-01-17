package com.itehl.digital.consumers;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.controller.dto.CursoDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CursoREST {


    RestTemplate restTemplate = new RestTemplate();

    public CursoDTO consultarCursoPorId(String idCurso){

        ResponseEntity<CursoDTO> restExchange =
                restTemplate.exchange(
                        "http://es-curso-svc:1004/es/curso/v1/{idCurso}",
                        HttpMethod.GET,
                        null, CursoDTO.class, idCurso);

        return restExchange.getBody();
    }
}
