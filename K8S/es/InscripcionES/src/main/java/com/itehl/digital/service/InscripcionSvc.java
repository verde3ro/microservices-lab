package com.itehl.digital.service;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.repository.IInscripcionRepository;
import com.itehl.digital.repository.model.InscripcionModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscripcionSvc implements IInscripcionSvc {

    private IInscripcionRepository repository;

    public InscripcionSvc(IInscripcionRepository repository) {
        this.repository = repository;
    }

    @Override
    public InscripcionModel crearInscripcion(InscripcionModel inscripcionModel) {

        return repository.save(inscripcionModel);
    }

    @Override
    public InscripcionModel actualizarInscripcion(InscripcionModel inscripcionModel) {

       InscripcionModel modelToUpdate =  repository.findById(inscripcionModel.getIdInscripcion()).get();

        modelToUpdate.setEstado(inscripcionModel.getEstado());
        modelToUpdate.setCurso(inscripcionModel.getCurso());
        modelToUpdate.setIdCliente(inscripcionModel.getIdCliente());
        modelToUpdate.setIdCurso(inscripcionModel.getIdCurso());
        modelToUpdate.setIdInscripcion(inscripcionModel.getIdInscripcion());
        modelToUpdate.setNombre(inscripcionModel.getNombre());

        return modelToUpdate;
    }

    @Override
    public void aprobarInscripcion(String idInscripcion) {

      Optional<InscripcionModel> inscripcionModel =  repository.findById(idInscripcion);

      if (inscripcionModel.isPresent()){
          inscripcionModel.get().setEstado("Aprobada");
          repository.save(inscripcionModel.get());
      }

    }

    @Override
    public void rechazarInscripcion(String idInscripcion) {

        Optional<InscripcionModel> inscripcionModel =  repository.findById(idInscripcion);

        if (inscripcionModel.isPresent()){
            inscripcionModel.get().setEstado("Rechazada");
            repository.save(inscripcionModel.get());
        }

    }

    @Override
    public InscripcionModel consultarInscripcionPorId(String idInscripcion) {
        return repository.findById(idInscripcion).get();
    }
}
