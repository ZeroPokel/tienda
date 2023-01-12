package com.zeropokel.springprojects.tienda.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.model.Pedido;
import com.zeropokel.springprojects.tienda.services.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{

    

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
    public void delete(int codigo) {
        // TODO Auto-generated method stub
        
    }

    
}
