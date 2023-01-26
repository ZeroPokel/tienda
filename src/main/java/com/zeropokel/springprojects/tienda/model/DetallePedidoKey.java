package com.zeropokel.springprojects.tienda.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetallePedidoKey implements Serializable{
    
    int pedido_codigo;
    int producto_codigo;

    public DetallePedidoKey() {
    }

    public DetallePedidoKey(int pedido_codigo, int producto_codigo) {
        this.pedido_codigo = pedido_codigo;
        this.producto_codigo = producto_codigo;
    }

    public int getPedido_codigo() {
        return pedido_codigo;
    }

    public void setPedido_codigo(int pedido_codigo) {
        this.pedido_codigo = pedido_codigo;
    }

    public int getProducto_codigo() {
        return producto_codigo;
    }

    public void setProducto_codigo(int producto_codigo) {
        this.producto_codigo = producto_codigo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pedido_codigo;
        result = prime * result + producto_codigo;
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
        DetallePedidoKey other = (DetallePedidoKey) obj;
        if (pedido_codigo != other.pedido_codigo)
            return false;
        if (producto_codigo != other.producto_codigo)
            return false;
        return true;
    }



    
}
