package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.model.Departamento;
import com.zeropokel.springprojects.tienda.model.Empleado;
import com.zeropokel.springprojects.tienda.repository.DepartamentoRepository;
import com.zeropokel.springprojects.tienda.repository.EmpleadoRepository;
import com.zeropokel.springprojects.tienda.services.DepartamentosService;

@Service
public class DepartamentosServiceImpl implements DepartamentosService {

    @Autowired
    DepartamentoRepository repository;

    @Autowired
    EmpleadoRepository repositoryEmpleados;

    @Override
    public Page<Departamento> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Departamento findByID(int codigo) {
        Optional<Departamento> findById = repository.findById(codigo);
        if(findById != null){
            return findById.get();
        }
        return null;
    }

    @Override
    public void insert(Departamento departamento) {
        repository.save(departamento);
        
    }

    @Override
    public void update(Departamento departamento) {

        if (departamento.getEmpleados() == null){
            Departamento depBD = findByID(departamento.getCodigo());
            departamento.setEmpleados(depBD.getEmpleados());
        }

        List<Empleado> empleadosDep = departamento.getEmpleados();


        departamento.setEmpleados(empleadosDep);

        repository.save(departamento);
    }

    @Override
    public void delete(int codigo) {
        repository.deleteById(codigo);
    }
    
}
