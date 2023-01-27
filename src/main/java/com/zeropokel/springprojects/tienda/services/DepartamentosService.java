package com.zeropokel.springprojects.tienda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.model.Departamento;

public interface DepartamentosService {
    public Page<Departamento> findAll(Pageable page);

    public Departamento findByID(int codigo);

    public void insert(Departamento departamento);

    public void update(Departamento departamento);

    public void delete(int codigo);
}
