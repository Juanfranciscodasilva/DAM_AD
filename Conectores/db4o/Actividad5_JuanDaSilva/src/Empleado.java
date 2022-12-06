public class Empleado {

    private long id;
    private String nombre;
    private String cargo;
    private long departamento;

    public Empleado() {
    }
    public Empleado(long id, String nombre, String cargo, long departamento) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(long edad) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", departamento=" + departamento +
                '}';
    }
}
