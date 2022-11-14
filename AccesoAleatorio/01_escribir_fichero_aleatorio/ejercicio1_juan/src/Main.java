import Clases.Empleado;

import java.io.*;
import java.nio.ByteBuffer;

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
}