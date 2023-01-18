package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeropokel.springprojects.tienda.model.Proveedor;

public interface ProveedorRepository  extends JpaRepository<Proveedor, Integer>{
    
}
