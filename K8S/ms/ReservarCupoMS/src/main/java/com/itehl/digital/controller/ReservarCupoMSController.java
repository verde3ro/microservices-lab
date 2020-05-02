package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import com.itehl.digital.repository.IReservarCupoRepository;
import com.itehl.digital.repository.model.CupoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ms/reservarcupo/v1")
@Component
public class ReservarCupoMSController {

    private static final Logger LOG = LoggerFactory.getLogger(ReservarCupoMSController.class);

    @Autowired
    IReservarCupoRepository reservarCupoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean reservarCupo(@RequestBody Map<String, String> body) {

        LOG.info("Start reservarCupo");

        // Se obtiene del body el valor del id del curso
        String idCurso =  body.get("idCurso");

        //Consultar la información de cupos por el id del curso
        Optional<CupoModel> reservarCupoModelTemp = reservarCupoRepository.findById(idCurso);

        //Valida que el curso exista
        if (reservarCupoModelTemp.isPresent()) {
            //Valida que existan cupos disponibles
            if (reservarCupoModelTemp.get().getCuposAcomulados() < reservarCupoModelTemp.get().getCuposPermitidos()) {
                //Reserva el cupo
                reservarCupoModelTemp.get().setCuposAcomulados(reservarCupoModelTemp.get().getCuposAcomulados() + 1);
                //Actualiza el collecction
                reservarCupoRepository.save(reservarCupoModelTemp.get());
            } else {

                return false;
            }

        } else {

            //Crea un nuevo curso para controlar sus reservas
            CupoModel reservarCupoModel = new CupoModel(idCurso, 5, 1);

            reservarCupoRepository.save(reservarCupoModel);

            return true;
        }

        LOG.info("End reservarCupo");

        return true;
    }
}