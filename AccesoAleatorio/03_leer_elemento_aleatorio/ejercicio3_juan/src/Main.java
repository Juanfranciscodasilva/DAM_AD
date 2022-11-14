import Clases.Empleado;

import javax.swing.*;
import java.io.*;

public class Main {

    public static Empleado[] empleados = {
            new Empleado("aaaaaaaaaa",1,900),
            new Empleado("bbbbbbbbbb",2,1780),
            new Empleado("cccccccccc",3,1350),
            new Empleado("dddddddddd",2,2500),
            new Empleado("eeeeeeeeee",3,1900),
    };
    public static RandomAccessFile bd = null;

    public static int idPredeterminado = 1;

    public static void main(String[] args) throws Exception {
        try{
            bd = inicializarBaseDeDatos();
            int idPedido = pedirIdUsuario();
            leerEmpleadoPorId(idPedido);
            bd.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    public static RandomAccessFile inicializarBaseDeDatos() throws Exception{
        try{
            bd = new RandomAccessFile("empleados.dat", "rw");
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
            return bd;
        }catch (FileNotFoundException ex){
            System.out.println("La base de datos no existe");
            throw ex;
        }catch (Exception ex){
            throw ex;
        }
    }

    public static int pedirIdUsuario(){
        try{
            return Integer.parseInt(JOptionPane.showInputDialog("Escribe el ID que quieres buscar"));
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Como no has puesto un número (parece que es dificil), se va a buscar el ID "+idPredeterminado+" predeterminado");
            return idPredeterminado;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado, se va a buscar el ID "+idPredeterminado+" predeterminado");
            return idPredeterminado;
        }
    }

    public static void leerEmpleadoPorId(int idBuscar) throws Exception{
        try{
            int posicion = 0;
            bd.seek(0);
            while(bd.getFilePointer() < bd.length()){
                bd.seek(posicion);
                int id = bd.readInt();
                if(id == idBuscar){
                    StringBuffer buffer = new StringBuffer();
                    for(int x = 0;x<10;x++){
                        buffer.append(bd.readChar());
                    }
                    String apellido = buffer.toString();
                    int departamento = bd.readInt();
                    double salario = bd.readDouble();
                    Empleado empleGenerado = new Empleado(id,apellido,departamento,salario);
                    System.out.println(empleGenerado.toString());
                    return;
                }
                posicion += 36;
            }
            System.out.println("No se ha encontrado el empleado con el ID: "+idBuscar);
        }catch (EOFException ex){
            System.out.println("Ya se han leido todos los empleados");
        }catch (Exception ex){
            throw ex;
        }
    }
}