package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeropokel.springprojects.tienda.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{
    
}
