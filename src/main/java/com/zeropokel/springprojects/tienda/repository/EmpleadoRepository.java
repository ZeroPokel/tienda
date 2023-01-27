package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeropokel.springprojects.tienda.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    
}
