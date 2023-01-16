package com.zeropokel.springprojects.tienda.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.dao.DetallePedidoDAO;
import com.zeropokel.springprojects.tienda.services.DetallePedidosService;

@Service
public class DetallePedidosServiceImpl implements DetallePedidosService{

    @Autowired
    DetallePedidoDAO detallePedidoDAO;

    @Override
    public void delete(int codigo) {
        detallePedidoDAO.delete(codigo);
        
    }
    
}
