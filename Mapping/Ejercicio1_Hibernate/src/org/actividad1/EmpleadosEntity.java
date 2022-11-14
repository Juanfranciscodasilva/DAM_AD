package org.actividad1;

import javax.persistence.*;

@Entity
@Table(name = "empleados", schema = "actividad1", catalog = "")
public class EmpleadosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dni", nullable = false, length = 10)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;
    @Basic
    @Column(name = "sueldo", nullable = true)
    private Integer sueldo;
    @Basic
    @Column(name = "cod_departamento", nullable = true, length = 10)
    private String codDepartamento;
    @Basic
    @Column(name = "Departamentos_cod_departamento", nullable = false, length = 10)
    private String departamentosCodDepartamento;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSueldo() {
        return sueldo;
    }

    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getDepartamentosCodDepartamento() {
        return departamentosCodDepartamento;
    }

    public void setDepartamentosCodDepartamento(String departamentosCodDepartamento) {
        this.departamentosCodDepartamento = departamentosCodDepartamento;
    }
}
