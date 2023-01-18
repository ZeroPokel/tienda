package com.zeropokel.springprojects.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vendedor {
    
    @Id
    @GeneratedValue
    private int codigo;
    private String nombre;
    private String apellidos;
    private String puesto;

    public Vendedor(int codigo, String nombre, String apellidos, String puesto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
    }

    public Vendedor(int codigo) {
        this.codigo = codigo;
    }

    public Vendedor() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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
        Vendedor other = (Vendedor) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    


}
