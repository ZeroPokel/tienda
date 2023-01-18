package com.zeropokel.springprojects.tienda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.model.Proveedor;

public interface ProveedoresService {
    
    public Page<Proveedor> findAll(Pageable page);
    public Proveedor findByID(int codigo);
    public void insert(Proveedor proveedor);
    public void update(Proveedor proveedor);
    public void delete(int codigo);
}
