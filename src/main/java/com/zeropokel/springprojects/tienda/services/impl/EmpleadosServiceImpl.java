package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.model.Empleado;
import com.zeropokel.springprojects.tienda.repository.EmpleadoRepository;
import com.zeropokel.springprojects.tienda.services.EmpleadosService;

@Service
public class EmpleadosServiceImpl implements EmpleadosService{

    @Autowired
    EmpleadoRepository repository;

    @Override
    public Page<Empleado> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Empleado findByID(int codigo) {
        Optional<Empleado> findById = repository.findById(codigo);
        if(findById != null){
            return findById.get();
        }
        return null;
    }

    @Override
    public void insert(Empleado empleado) {
        repository.save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
        repository.save(empleado);
    }

    @Override
    public void delete(int codigo) {
        repository.deleteById(codigo);
    }

    @Override
    public List<Empleado> findAll() {
        return repository.findAll();
    }

    
    
}
