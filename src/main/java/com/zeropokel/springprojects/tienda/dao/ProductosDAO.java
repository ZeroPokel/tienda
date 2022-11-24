package com.zeropokel.springprojects.tienda.dao;
import com.zeropokel.springprojects.tienda.model.Producto;
import java.util.List;

public interface ProductosDAO {

    public List<Producto> findAll();
    public Producto findByID(int codigo);
    public void insert(Producto producto);
    public void update(Producto producto);
    public void delete(int codigo);
}
