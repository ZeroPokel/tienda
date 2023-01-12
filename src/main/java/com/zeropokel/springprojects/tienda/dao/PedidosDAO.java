package com.zeropokel.springprojects.tienda.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.model.Pedido;

public interface PedidosDAO {
    
    public Page<Pedido> findAll(Pageable page);
    public Pedido findByID(int codigo);
    public void insert(Pedido pedido);
    public void update(Pedido pedido);
    public void delete(int codigo);
}
