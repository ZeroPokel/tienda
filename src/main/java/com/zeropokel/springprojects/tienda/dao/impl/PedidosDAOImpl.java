package com.zeropokel.springprojects.tienda.dao.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.dao.PedidosDAO;
import com.zeropokel.springprojects.tienda.model.Pedido;

public class PedidosDAOImpl implements PedidosDAO{

    @Override
    public Page<Pedido> findAll(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido findByID(int codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Pedido pedido) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Pedido pedido) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int codigo) {
        // TODO Auto-generated method stub
        
    }
    
}
