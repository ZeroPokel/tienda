package com.zeropokel.springprojects.tienda.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Departamento {

    @Id
    @GeneratedValue
    private int codigo;
    
    private String nombre;

    //bi-directional many-to-many association to Empleado
	@ManyToMany
	@JoinTable(
		name="departamento_empleado"
		, joinColumns={
			@JoinColumn(name="departamento_codigo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="empleado_codigo")
			}
		)

    private List<Empleado> empleados;

    public Departamento() {
    }

    public Departamento(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return int return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        Departamento other = (Departamento) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

}
