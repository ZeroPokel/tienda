package com.zeropokel.springprojects.tienda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.model.Pedido;

public interface PedidosService {
    
    public Page<Pedido> findAll(Pageable page);
    public Pedido findByID(int codigo);
    public void save(Pedido pedido);
    // public void update(Pedido pedido);
    public void delete(int codigo);
}
