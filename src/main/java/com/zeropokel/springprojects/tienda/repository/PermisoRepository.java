package com.zeropokel.springprojects.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeropokel.springprojects.tienda.model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer>{
    
}
