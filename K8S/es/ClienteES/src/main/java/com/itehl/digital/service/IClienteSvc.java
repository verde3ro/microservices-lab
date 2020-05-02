package com.itehl.digital.service;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.itehl.digital.repository.model.ClienteModel;

import java.util.ArrayList;

public interface IClienteSvc {

    public ClienteModel crearClente(ClienteModel clienteModel);

    public ClienteModel consultarPorId(String id);

    public ArrayList<ClienteModel> consultarClientes();
}
