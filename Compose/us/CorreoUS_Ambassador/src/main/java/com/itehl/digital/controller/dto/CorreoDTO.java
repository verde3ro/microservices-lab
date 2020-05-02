package com.itehl.digital.controller.dto;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
public class CorreoDTO {

    private String to;
    private String mensaje;

    public CorreoDTO(String to, String mensaje) {
        this.to = to;
        this.mensaje = mensaje;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
