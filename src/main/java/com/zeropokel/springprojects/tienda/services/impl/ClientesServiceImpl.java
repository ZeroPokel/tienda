package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.dao.ClientesDAO;
import com.zeropokel.springprojects.tienda.model.Cliente;
import com.zeropokel.springprojects.tienda.services.ClientesService;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    ClientesDAO clientesDAO;

    @Override
    public List<Cliente> findAll() {
        return clientesDAO.findAll();
    }

    @Override
    public Cliente findByID(int codigo){
        return clientesDAO.findByID(codigo);
    }

    @Override
    public void insert(Cliente cliente){
        clientesDAO.insert(cliente);
    }

    @Override
    public void update(Cliente cliente){
        clientesDAO.update(cliente);
    }

    @Override
    public void delete(int codigo){
        clientesDAO.delete(codigo);
    }
}
