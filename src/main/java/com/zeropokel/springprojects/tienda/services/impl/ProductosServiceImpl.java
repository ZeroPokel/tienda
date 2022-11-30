package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zeropokel.springprojects.tienda.dao.ProductosDAO;
import com.zeropokel.springprojects.tienda.model.Producto;
import com.zeropokel.springprojects.tienda.services.ProductosService;

@Service
public class ProductosServiceImpl implements ProductosService{

    @Autowired
    ProductosDAO productosDAO;

    @Override
    public Page<Producto> findAll(Pageable page) {
        return productosDAO.findAll(page);
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

        if(producto.getImg() != null && producto.getImg().length > 0){
            productosDAO.updateImg(producto);
        }
    }

    @Override
    public void delete(int codigo){
        productosDAO.delete(codigo);
    }
    
}
