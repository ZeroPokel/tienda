package com.zeropokel.springprojects.tienda.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.zeropokel.springprojects.tienda.dao.PedidosDAO;
import com.zeropokel.springprojects.tienda.model.Cliente;
import com.zeropokel.springprojects.tienda.model.Pedido;

@Repository
public class PedidosDAOImpl extends JdbcDaoSupport implements PedidosDAO{

    @Autowired
    DataSource dataSource;
    
    @PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

    @Override
    public Page<Pedido> findAll(Pageable page) {
        
        String queryCount = "select count(1) from Pedidos";
        Integer total = getJdbcTemplate().queryForObject(queryCount,Integer.class);


        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");

        String query = "SELECT p.*, c.nombre FROM Pedidos p, Clientes c where p.codigo_cliente = c.codigo ORDER BY " + order.getProperty() + " "
        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();

        final List<Pedido> pedidos = getJdbcTemplate().query(query, new RowMapper<Pedido>() {

            @Override
            @Nullable
            public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pedido pedido = new Pedido();
                pedido.setCodigo(rs.getInt("codigo"));
                pedido.setCliente(new Cliente(rs.getInt("codigo_cliente")));
                pedido.getCliente().setNombre(rs.getString("nombre"));
                pedido.setFecha(new java.util.Date(rs.getDate("fecha").getTime()));
                pedido.setTotal(rs.getFloat("total"));
                return pedido;
            }
        });

        return new PageImpl<Pedido>(pedidos, page, total);
    }

    @Override
    public Pedido findByID(int codigo) {
        String query = "select p.* from Pedidos p where p.codigo = ?";

        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        Pedido producto = (Pedido) getJdbcTemplate().queryForObject(query, params, types, new RowMapper<Pedido>() {

            @Override
            @Nullable
            public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pedido pedido = new Pedido();
                pedido.setCodigo(rs.getInt("codigo"));
                pedido.setCliente(new Cliente(rs.getInt("codigo_cliente")));
                //pedido.getCliente().setNombre(rs.getString("nombre"));
                pedido.setFecha(new java.util.Date(rs.getDate("fecha").getTime()));
                pedido.setTotal(rs.getFloat("total"));
                return pedido;
            }
            
        });
        return producto;
    }

    @Override
    public void insert(Pedido pedido) {
        String query = "insert into Pedidos (codigo_cliente," + 
                                            " total," + 
                                            " fecha)" + 
                                            " values (?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, pedido.getCliente().getCodigo());
                ps.setFloat(2, pedido.getTotal());
                java.util.Date fecha = new Date();
                ps.setDate(3, new java.sql.Date(fecha.getTime()));
                return ps;
            }
        }, keyHolder);

        pedido.setCodigo(keyHolder.getKey().intValue());
        
    }

    @Override
    public void update(Pedido pedido) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int codigo) {
        

        String query = "delete from Pedidos where codigo = ?";

        Object[] params = {
            codigo
        };

        final int[] types = {
            Types.INTEGER
        };
        getJdbcTemplate().update(query, params, types);   
    }
    
}
