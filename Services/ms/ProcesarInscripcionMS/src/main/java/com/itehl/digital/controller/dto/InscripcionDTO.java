package com.itehl.digital.controller.dto;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
public class InscripcionDTO {

    private String idInscripcion;
    private String idCurso;
    private String idCliente;
    private String curso;
    private String nombre;
    private String estado;

    public InscripcionDTO() {
    }

    public InscripcionDTO(String idInscripcion, String idCurso, String idCliente, String curso, String nombre, String estado) {
        this.idInscripcion = idInscripcion;
        this.idCurso = idCurso;
        this.idCliente = idCliente;
        this.curso = curso;
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(String idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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
        return "InscripcionDTO{" +
                "idInscripcion='" + idInscripcion + '\'' +
                ", idCurso='" + idCurso + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", curso='" + curso + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}