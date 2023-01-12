package com.zeropokel.springprojects.tienda.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int codigo;
    private Cliente cliente;
    private List<DetallePedido> detallepedidos;
    private Date fecha;
    private float total;

    public Pedido() {
        this.cliente = new Cliente();
        this.detallepedidos = new ArrayList<DetallePedido>();
    }

    public Pedido(int codigo) {
        this.codigo = codigo;
    }

    public Pedido(int codigo, Cliente cliente, List<DetallePedido> detallepedidos, Date fecha, float total) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.detallepedidos = detallepedidos;
        this.fecha = fecha;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetallepedidos() {
        return detallepedidos;
    }

    public void setDetallepedidos(List<DetallePedido> detPedidos) {
        this.detallepedidos = (List<DetallePedido>) detPedidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public double getTotalCesta() {

        total = 0;
        for (DetallePedido detalle : this.detallepedidos) {
            total += detalle.getCantidad()*detalle.getProducto().getPrecio();
        }

        return total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

}
