package com.zeropokel.springprojects.tienda.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoKey id = new DetallePedidoKey();

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @MapsId("producto_codigo")
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @MapsId("pedido_codigo")
    private Pedido pedido;

    private int cantidad;
    private float subtotal;


    public DetallePedido() {
    } 

    public DetallePedidoKey getId() {
        return id;
    }

    public void setId(DetallePedidoKey id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

}
