package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.dao.ClientesDAO;
import com.zeropokel.springprojects.tienda.dao.DetallePedidoDAO;
import com.zeropokel.springprojects.tienda.dao.PedidosDAO;
import com.zeropokel.springprojects.tienda.model.Cliente;
import com.zeropokel.springprojects.tienda.model.DetallePedido;
import com.zeropokel.springprojects.tienda.model.Pedido;
import com.zeropokel.springprojects.tienda.services.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{

    @Autowired
    PedidosDAO pedidosDAO;

    @Autowired
    DetallePedidoDAO detallePedidoDAO;

    @Autowired
    ClientesDAO clientesDAO;

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return pedidosDAO.findAll(pageable);
    }

    @Override
    public Pedido findByID(int codigo) {
        Pedido pedido = pedidosDAO.findByID(codigo);

        Cliente cliente = clientesDAO.findByID(pedido.getCliente().getCodigo());

        pedido.setCliente(cliente);

        List<DetallePedido> detalle = detallePedidoDAO.findDetalle(pedido);
        pedido.setDetallepedidos(detalle);
        
        return pedido;
    }

    @Override
    public void insert(Pedido pedido) {
        pedidosDAO.insert(pedido);

        List<DetallePedido> detallePedidos = pedido.getDetallepedidos();
        for (DetallePedido detallePedido : detallePedidos) {
            detallePedidoDAO.insert(pedido, detallePedido);
        }
        
    }

    @Override
    public void delete(int codigo) {
        pedidosDAO.delete(codigo);       
    }

    
}
