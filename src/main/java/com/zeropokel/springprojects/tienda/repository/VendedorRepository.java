package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeropokel.springprojects.tienda.model.Vendedor;

public interface VendedorRepository  extends JpaRepository<Vendedor, Integer>{
    
}