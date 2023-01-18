package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeropokel.springprojects.tienda.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
