package com.itehl.digital.controller.dto;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
public class CorreoDTO {

    private String to;
    private String asunto;
    private String nombre;
    private String curso;
    private String plantilla;
    private String mensaje;

    public CorreoDTO(String to, String asunto, String nombre, String curso, String plantilla, String mensaje) {
        this.to = to;
        this.asunto = asunto;
        this.nombre = nombre;
        this.curso = curso;
        this.plantilla = plantilla;
        this.mensaje = mensaje;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "CorreoDTO{" +
                "to='" + to + '\'' +
                ", asunto='" + asunto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", curso='" + curso + '\'' +
                ", plantilla='" + plantilla + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
