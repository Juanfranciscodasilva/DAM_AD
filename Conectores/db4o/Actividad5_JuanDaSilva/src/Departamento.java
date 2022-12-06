import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private long id;
    private String descripcion;
    private String ciudad;
    private List<Empleado> empleados;

    public Departamento() {
        this.empleados = new ArrayList<>();
    }

    public Departamento(long id, String descripcion, String ciudad) {
        this.id = id;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.empleados = new ArrayList<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", empleados=" + empleados.size() +
                '}';
    }
}
