package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.dao.ClientesDAO;
import com.zeropokel.springprojects.tienda.dao.DetallePedidoDAO;
import com.zeropokel.springprojects.tienda.dao.PedidosDAO;
import com.zeropokel.springprojects.tienda.model.Cliente;
import com.zeropokel.springprojects.tienda.model.DetallePedido;
import com.zeropokel.springprojects.tienda.model.Pedido;
import com.zeropokel.springprojects.tienda.repository.ClienteRepository;
import com.zeropokel.springprojects.tienda.repository.PedidoRepository;
import com.zeropokel.springprojects.tienda.services.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{

    @Autowired
    PedidoRepository repository;
    
    @Autowired
    DetallePedidoDAO detallePedidoDAO;

    @Autowired
    ClienteRepository repositoryCliente;

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Pedido findByID(int codigo) {
        Optional<Pedido> findById = repository.findById(codigo);
        if(findById != null){
            return findById.get();
        }
        return null;
    }

    @Override
    public void insert(Pedido pedido) {
        repository.save(pedido);   
        
    }

    @Override
    public void delete(int codigo) {
        repository.deleteById(codigo);       
    }

}
