package com.zeropokel.springprojects.tienda.dao.impl;

import com.zeropokel.springprojects.tienda.dao.ClientesDAO;
import com.zeropokel.springprojects.tienda.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ClientesDAOImpl extends JdbcDaoSupport implements ClientesDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Cliente> findAll() {

        String query = "select * from Clientes";

        final List<Cliente> clientes = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Cliente.class));

        return clientes;
    }

    @Override
    public Cliente findByID(int codigo){
        String query = "select * from Clientes where codigo = ?";

        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        Cliente cliente = (Cliente) getJdbcTemplate().queryForObject(query, params, types, new BeanPropertyRowMapper(Cliente.class));

        return cliente;
    }

    @Override
    public void insert(Cliente cliente) {

        String query = "insert into Clientes (nombre," +
                                            " apellidos," +
                                            " telefono," +
                                            " direccion," +
                                            " vip)" +
                                            " values (?, ?, ?, ?, ?)";
        
        Object[] params = {
            cliente.getNombre(),
            cliente.getApellidos(),
            cliente.getTelefono(),
            cliente.getDireccion(),
            cliente.getVip()
        };

        final int[] types = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.BOOLEAN
        };

        int update = getJdbcTemplate().update(query, params, types);
    }

    @Override
    public void update(Cliente cliente) {

        String query = "update Clientes set nombre = ?," +
                                            " apellidos = ?," +
                                            " telefono = ?," +
                                            " direccion = ?," +
                                            " vip = ?" +
                                            " where codigo = ?";
        
        Object[] params = {
            cliente.getNombre(),
            cliente.getApellidos(),
            cliente.getTelefono(),
            cliente.getDireccion(),
            cliente.getVip(),
            cliente.getCodigo()
        };

        final int[] types = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.BOOLEAN,
            Types.VARCHAR
        };

        int update = getJdbcTemplate().update(query, params, types);
    }

    @Override
    public void delete(int codigo){
        
        String query = "delete from Clientes where codigo = ?";

        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        int update = getJdbcTemplate().update(query, params, types);

    }
}
