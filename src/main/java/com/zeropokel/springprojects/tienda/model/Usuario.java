package com.zeropokel.springprojects.tienda.model;

public class Usuario {

    private int codigo;
    private String usuario;
    private String password;
    
    public Usuario() {
    }

    public Usuario(int codigo) {
        this.codigo = codigo;
    }

    public Usuario(int codigo, String usuario, String password) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.password = password;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        Usuario other = (Usuario) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }
    
    
}
