package com.zeropokel.springprojects.tienda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.model.Vendedor;

public interface VendedoresService {
    public Page<Vendedor> findAll(Pageable page);
    public Vendedor findByID(int codigo);
    public void insert(Vendedor vendedor);
    public void update(Vendedor vendedor);
    public void delete(int codigo);
}
