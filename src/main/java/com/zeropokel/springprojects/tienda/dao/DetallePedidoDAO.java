package com.zeropokel.springprojects.tienda.dao;

import java.util.List;

import com.zeropokel.springprojects.tienda.model.DetallePedido;
import com.zeropokel.springprojects.tienda.model.Pedido;

public interface DetallePedidoDAO {
    
    public void insert(Pedido pedido, DetallePedido detallePedido);

    public List<DetallePedido> findDetalle(Pedido pedido);
}
