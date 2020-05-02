package com.itehl.digital.repository.model;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import org.springframework.data.annotation.Id;

public class CupoModel {

    @Id
    private String idCurso;
    private int cuposPermitidos;
    private int cuposAcomulados;


    public CupoModel(String idCurso, int cuposPermitidos, int cuposAcomulados) {
        this.idCurso = idCurso;
        this.cuposPermitidos = cuposPermitidos;
        this.cuposAcomulados = cuposAcomulados;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public int getCuposPermitidos() {
        return cuposPermitidos;
    }

    public void setCuposPermitidos(int cuposPermitidos) {
        this.cuposPermitidos = cuposPermitidos;
    }

    public int getCuposAcomulados() {
        return cuposAcomulados;
    }

    public void setCuposAcomulados(int cuposAcomulados) {
        this.cuposAcomulados = cuposAcomulados;
    }

    @Override
    public String toString() {
        return "VerificarCupoDTO{" +
                "idCurso='" + idCurso + '\'' +
                ", cuposPermitidos=" + cuposPermitidos +
                ", cuposAcomulados=" + cuposAcomulados +
                '}';
    }
}
