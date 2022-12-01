package com.zeropokel.springprojects.tienda.services;
import com.zeropokel.springprojects.tienda.model.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientesService {
    
    public Page<Cliente> findAll(Pageable page);
    public Cliente findByID(int codigo);
    public void insert(Cliente cliente);
    public void update(Cliente cliente);
    public void delete(int codigo);
}
