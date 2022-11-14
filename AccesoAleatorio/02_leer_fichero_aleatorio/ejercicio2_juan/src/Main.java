import Clases.Empleado;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Main {

    public static Empleado[] empleados = {
            new Empleado("aaaaaaaaaa",1,900),
            new Empleado("bbbbbbbbbb",2,1780),
            new Empleado("cccccccccc",3,1350),
            new Empleado("dddddddddd",2,2500),
            new Empleado("eeeeeeeeee",3,1900),
    };
    public static RandomAccessFile bd = null;

    public static void main(String[] args) throws Exception {
        try{
            bd = new RandomAccessFile("empleados.dat", "rw");
            insertarDatos();
            leerDatos();
            bd.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public static void insertarDatos() throws Exception{
        try{
            bd.setLength(0);
            for(int x=0;x<empleados.length;x++){
                Empleado emple = empleados[x];
                emple.setId(x+1);
                bd.writeInt(emple.getId());
                StringBuffer buffer = new StringBuffer(emple.getApellido());
                buffer.setLength(10);
                bd.writeChars(buffer.toString());
                bd.writeInt(emple.getDepartamento());
                bd.writeDouble(emple.getSalario());
            }
        }catch (FileNotFoundException ex){
            System.out.println("La base de datos no existe");
            throw ex;
        }catch (Exception ex){
            throw ex;
        }
    }

    public static void leerDatos()throws Exception{
        try{
            int posicion = 0;
            bd.seek(0);
            while(bd.getFilePointer() < bd.length()){
                bd.seek(posicion);
                int id = bd.readInt();
                StringBuffer buffer = new StringBuffer();
                for(int x = 0;x<10;x++){
                    buffer.append(bd.readChar());
                }
                String apellido = buffer.toString();
                int departamento = bd.readInt();
                double salario = bd.readDouble();
                Empleado empleGenerado = new Empleado(id,apellido,departamento,salario);
                System.out.println(empleGenerado.toString());
                posicion += 36;
            }
        }catch (EOFException ex){
            System.out.println("Ya se han leido todos los empleados");
        }catch (Exception ex){
            throw ex;
        }
    }
}