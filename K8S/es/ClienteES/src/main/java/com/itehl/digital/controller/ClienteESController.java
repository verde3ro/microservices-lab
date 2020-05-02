package com.itehl.digital.controller;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */

import com.itehl.digital.controller.dto.ClienteDTO;
import com.itehl.digital.repository.IClienteRepository;
import com.itehl.digital.repository.model.ClienteModel;
import com.itehl.digital.service.ClienteSvc;
import com.itehl.digital.service.IClienteSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/es/cliente/v1")
@Component
public class ClienteESController {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteESController.class);
    IClienteSvc clienteSvc;

    public ClienteESController( IClienteRepository clienteRepository) {

        clienteSvc = new ClienteSvc(clienteRepository);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel CrearCliente(@RequestBody ClienteDTO clienteDTO) {

        LOG.info("Start CrearCliente");

        ClienteModel clienteModel = clienteSvc.crearClente(convertToModel(clienteDTO));

        LOG.info("End CrearCliente");

        return clienteModel;

    }

    @GetMapping("/{idCliente}")
    public ClienteModel consultarClientePorId(@PathVariable String idCliente) {

        LOG.info("Start consultarClientePorId");

        ClienteModel clienteModel = clienteSvc.consultarPorId(idCliente);

        LOG.info("End consultarClientePorId");

        return clienteModel;

    }

    @GetMapping
    public ArrayList<ClienteModel> consultarClientes() {

        LOG.info("Start consultarClientes");

        ArrayList<ClienteModel> clienteModel =  clienteSvc.consultarClientes();

        LOG.info("End consultarClientes");

        return clienteModel;

    }

    protected ClienteModel convertToModel(ClienteDTO clienteDTO) {

        return new ClienteModel(clienteDTO.getIdCliente(),
                clienteDTO.getNombres(),
                clienteDTO.getApellidos(),
                clienteDTO.getProfesion(),
                clienteDTO.getEmail());

    }
}
