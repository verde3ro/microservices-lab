package com.itehl.digital.controller.service;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.repository.ICursoRepository;
import com.itehl.digital.repository.model.CursoModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoSvc implements ICursoSvc {

    ICursoRepository cursoRepository;

    public CursoSvc(ICursoRepository cursoRepository) {

        this.cursoRepository = cursoRepository;

    }

    @Override
    public CursoModel crearCurso(CursoModel cursoModel) {

        return cursoRepository.save(cursoModel);
    }

    @Override
    public CursoModel consultarPorId(String idCurso) {

        Optional<CursoModel> clienteModel =  cursoRepository.findById(idCurso);

        return clienteModel.get();

    }
}
