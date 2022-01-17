package com.itehl.digital.service;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import com.itehl.digital.repository.model.InscripcionModel;

public interface IInscripcionSvc {

    public InscripcionModel crearInscripcion(InscripcionModel inscripcionModel);

    public InscripcionModel actualizarInscripcion(InscripcionModel inscripcionModel);

    public void  aprobarInscripcion(String idInscripcion);

    public void  rechazarInscripcion(String idInscripcion);

    public InscripcionModel  consultarInscripcionPorId(String idInscripcion);
}
