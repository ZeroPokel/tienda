package com.zeropokel.springprojects.tienda.model;

public class DetallePedido {
    
    private int codigo;
    private Producto producto;
    private int cantidad;

    public DetallePedido() {
    }

    public DetallePedido(int codigo) {
        this.codigo = codigo;
    }

    public DetallePedido(int codigo, Producto producto, int cantidad) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
