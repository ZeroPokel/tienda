package com.zeropokel.springprojects.tienda.dao;
import com.zeropokel.springprojects.tienda.model.Producto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductosDAO {

    public Page<Producto> findAll(Pageable page);
    public Producto findByID(int codigo);
    public void insert(Producto producto);
    public void update(Producto producto);
    public void delete(int codigo);
    public void updateImg(Producto producto);
}
