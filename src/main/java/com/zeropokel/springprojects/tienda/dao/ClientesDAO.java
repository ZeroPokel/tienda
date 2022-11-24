package com.zeropokel.springprojects.tienda.dao;
import com.zeropokel.springprojects.tienda.model.Cliente;
import java.util.List;

public interface ClientesDAO {
    
    public List<Cliente> findAll();
    public Cliente findByID(int codigo);
    public void insert(Cliente cliente);
    public void update(Cliente cliente);
    public void delete(int codigo);
}
