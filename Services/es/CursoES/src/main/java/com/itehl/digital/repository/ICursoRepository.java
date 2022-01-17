package com.itehl.digital.repository;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.repository.model.CursoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICursoRepository extends MongoRepository<CursoModel,String> {
}
