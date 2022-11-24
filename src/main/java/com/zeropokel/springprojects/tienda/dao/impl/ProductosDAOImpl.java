package com.zeropokel.springprojects.tienda.dao.impl;

import com.zeropokel.springprojects.tienda.dao.ProductosDAO;
import com.zeropokel.springprojects.tienda.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ProductosDAOImpl extends JdbcDaoSupport implements ProductosDAO {
    
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Producto> findAll() {

        String query = "select * from Productos";

        final List<Producto> productos = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Producto.class));

        return productos;
    }

    @Override
    public Producto findByID(int codigo){
        String query = "select * from Productos where codigo = ?";

        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        Producto producto = (Producto) getJdbcTemplate().queryForObject(query, params, types, new BeanPropertyRowMapper(Producto.class));

        return producto;
    }

    @Override
    public void insert(Producto producto) {

        String query = "insert into Productos (nombre," +
                                            " descripcion," +
                                            " precio)" +
                                            " values (?, ?, ?)";
        
        Object[] params = {
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio()
        };

        final int[] types = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.FLOAT
        };

        int update = getJdbcTemplate().update(query, params, types);
    }

    @Override
    public void update(Producto producto) {

        String query = "update Productos set nombre = ?," +
                                            " descripcion = ?," +
                                            " precio = ?" +
                                            " where codigo = ?";
        
        Object[] params = {
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCodigo()
        };
                                    
        final int[] types = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.FLOAT,
            Types.VARCHAR
        };

        int update = getJdbcTemplate().update(query, params, types);
    }

    @Override
    public void delete(int codigo){
        
        String query = "delete from Productos where codigo = ?";

        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        int update = getJdbcTemplate().update(query, params, types);

    }

}
