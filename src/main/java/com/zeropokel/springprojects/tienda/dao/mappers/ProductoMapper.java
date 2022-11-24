package com.zeropokel.springprojects.tienda.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.zeropokel.springprojects.tienda.model.Producto;


public class ProductoMapper implements RowMapper<Producto>{


    @Override
    @Nullable
    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

        Producto producto = new Producto();
        producto.setCodigo(rs.getInt("codigo"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getFloat("precio"));

        return producto;
    }
}
