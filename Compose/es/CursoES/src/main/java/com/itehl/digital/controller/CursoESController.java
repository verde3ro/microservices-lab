package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.controller.dto.CursoDTO;
import com.itehl.digital.controller.service.CursoSvc;
import com.itehl.digital.controller.service.ICursoSvc;
import com.itehl.digital.repository.ICursoRepository;
import com.itehl.digital.repository.model.CursoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/es/curso/v1")
@Component
public class CursoESController {

    private static final Logger LOG = LoggerFactory.getLogger(CursoESController.class);
    ICursoSvc cursoSvc;

    public CursoESController(ICursoRepository cursoRepository) {

        cursoSvc = new CursoSvc(cursoRepository);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoModel crearCurso(@RequestBody CursoDTO cursoDTO) {

        LOG.info("Start crearCurso");

        CursoModel cursoModel = cursoSvc.crearCurso(convertToModel(cursoDTO));

        LOG.info("End crearCurso");

        return cursoModel;

    }

    @GetMapping("/{idCurso}")
    public CursoModel consultarCursoPorId(@PathVariable String idCurso) {

        LOG.info("Start consultarCursoPorId");

        CursoModel cursoModel = cursoSvc.consultarPorId(idCurso);

        LOG.info("End consultarCursoPorId");

        return cursoModel;

    }


    protected CursoModel convertToModel(CursoDTO cursoDTO) {

        return new CursoModel(cursoDTO.getIdCurso(),cursoDTO.getNombre(),cursoDTO.getDescripcion(),cursoDTO.getModalidad());

    }
}
