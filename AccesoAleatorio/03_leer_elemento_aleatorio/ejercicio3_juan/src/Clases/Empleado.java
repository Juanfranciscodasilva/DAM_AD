package Clases;

import java.io.Serializable;

public class Empleado implements Serializable {

    private int id;
    private String apellido;
    private int departamento;
    private double salario;

    public Empleado(){

    }

    public Empleado(int id, String apellido, int departamento, double salario) {
        this.id = id;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    public Empleado(String apellido, int departamento, double salario) {
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", departamento=" + departamento +
                ", salario=" + salario +
                '}';
    }
}
