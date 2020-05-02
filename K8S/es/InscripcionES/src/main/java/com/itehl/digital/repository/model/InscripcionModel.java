package com.itehl.digital.repository.model;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import org.springframework.data.annotation.Id;

public class InscripcionModel {

    @Id
    private String idInscripcion;
    private String idCurso;
    private String idCliente;
    private String curso;
    private String nombre;
    private String estado;

    public InscripcionModel(String idInscripcion, String idCurso, String idCliente, String curso, String nombre, String estado) {
        this.idInscripcion = idInscripcion;
        this.idCurso = idCurso;
        this.idCliente = idCliente;
        this.curso = curso;
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(String idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "InscripcionModel{" +
                "idInscripcion='" + idInscripcion + '\'' +
                ", idCurso='" + idCurso + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", curso='" + curso + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

