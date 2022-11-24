package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.dao.ProductosDAO;
import com.zeropokel.springprojects.tienda.model.Producto;
import com.zeropokel.springprojects.tienda.services.ProductosService;

@Service
public class ProductosServiceImpl implements ProductosService{

    @Autowired
    ProductosDAO productosDAO;

    @Override
    public List<Producto> findAll() {
        return productosDAO.findAll();
    }

    @Override
    public Producto findByID(int codigo){
        return productosDAO.findByID(codigo);
    }

    @Override
    public void insert(Producto producto){
        productosDAO.insert(producto);
    }

    @Override
    public void update(Producto producto){
        productosDAO.update(producto);
    }

    @Override
    public void delete(int codigo){
        productosDAO.delete(codigo);
    }
    
}
