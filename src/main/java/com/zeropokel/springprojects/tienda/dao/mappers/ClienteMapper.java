package com.zeropokel.springprojects.tienda.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.zeropokel.springprojects.tienda.model.Cliente;

public class ClienteMapper implements RowMapper<Cliente>{

    @Override
    @Nullable
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setCodigo(rs.getInt("codigo"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidos(rs.getString("apellidos"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setVip(rs.getBoolean("vip"));

        return cliente;
    }
    
}
