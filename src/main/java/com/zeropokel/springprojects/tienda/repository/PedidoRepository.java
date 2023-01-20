package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeropokel.springprojects.tienda.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    
}
