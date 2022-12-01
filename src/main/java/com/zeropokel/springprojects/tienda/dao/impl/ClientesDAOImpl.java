package com.zeropokel.springprojects.tienda.dao.impl;

import com.zeropokel.springprojects.tienda.dao.ClientesDAO;
import com.zeropokel.springprojects.tienda.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.ResultSet;
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
    public Page<Cliente> findAll(Pageable page) {

        String queryCount = "select count(1) from Clientes";
        Integer total = getJdbcTemplate().queryForObject(queryCount,Integer.class);


        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");

        String query = "SELECT * FROM Clientes ORDER BY " + order.getProperty() + " "
        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();

        final List<Cliente> clientes = getJdbcTemplate().query(query, new RowMapper<Cliente>() {

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
            
        });

        

        return new PageImpl<Cliente>(clientes, page, total);
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
        
        /*
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
        */

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellidos());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getDireccion());
                ps.setBoolean(5, cliente.getVip());

                return ps;
            }
        }, keyHolder);

        cliente.setCodigo(keyHolder.getKey().intValue());

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
