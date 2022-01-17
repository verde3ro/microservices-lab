package com.itehl.digital.service;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.repository.IClienteRepository;
import com.itehl.digital.repository.model.ClienteModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClienteSvc implements IClienteSvc {

    private IClienteRepository repository;

    public ClienteSvc(IClienteRepository repository) {

        this.repository = repository;
    }

    @Override
    public ClienteModel crearClente(ClienteModel clienteModel) {

       return repository.save(clienteModel);

    }

    @Override
    public ClienteModel consultarPorId(String idCliente) {

        Optional<ClienteModel> clienteModel = repository.findById(idCliente);

        return clienteModel.get();
    }

    @Override
    public ArrayList<ClienteModel> consultarClientes() {

        ArrayList<ClienteModel> clienteModel = (ArrayList<ClienteModel>) repository.findAll();

        return clienteModel;
    }
}
