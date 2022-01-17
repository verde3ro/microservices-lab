package com.itehl.digital.controller.service;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.repository.model.CursoModel;

public interface ICursoSvc {

    public CursoModel crearCurso(CursoModel cursoModel);

    public CursoModel consultarPorId(String idCurso);
}
