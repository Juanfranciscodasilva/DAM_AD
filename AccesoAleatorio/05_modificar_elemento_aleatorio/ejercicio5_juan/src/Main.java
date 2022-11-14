import Clases.Empleado;

import javax.swing.*;
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
            bd = inicializarBaseDeDatos();
            do{
                int idPedido = pedirIdEmpleado();
                double salario = pedirSalario();
                actualizarEmpleado(idPedido,salario);
                leerDatos();
            }while(JOptionPane.showConfirmDialog(null,"Deseas actualizar otro empleado?") == 0);
            bd.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public static void actualizarEmpleado(int idEmpleado, double salario) throws Exception{
        try{
            int posicion = 0;
            bd.seek(0);
            while(bd.getFilePointer() < bd.length()){
                bd.seek(posicion);
                int id = bd.readInt();
                if(id == idEmpleado){
                    StringBuffer buffer = new StringBuffer();
                    for(int x = 0;x<10;x++){
                        buffer.append(bd.readChar());
                    }
                    String apellido = buffer.toString();
                    int departamento = bd.readInt();
                    double salarioEncontrado = bd.readDouble();
                    Empleado empleGenerado = new Empleado(id,apellido,departamento,salarioEncontrado);
                    System.out.println("Se ha encontrado el empleado con ID: "+idEmpleado);
                    System.out.println(empleGenerado.toString());
                    double salarioNuevo = salarioEncontrado+salario;
                    System.out.println("El salario anterior era: "+salarioEncontrado+", el nuevo salario será: "+salarioNuevo);
                    bd.seek(posicion+28);
                    bd.writeDouble(salarioNuevo);
                    return;
                }
                posicion += 36;
            }
            System.out.println("No se ha encontrado el empleado con el ID: "+idEmpleado);
        }catch (Exception ex){
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

    public static void leerDatos()throws Exception{
        try{
            System.out.println("---------------- DATOS DEL FICHERO ----------------\n");
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
            System.out.println("\n-----------------------------------------------------");
        }catch (EOFException ex){
            System.out.println("Ya se han leido todos los empleados");
        }catch (Exception ex){
            throw ex;
        }
    }

    public static int pedirIdEmpleado() throws Exception{
        do{
            try{
                String dptoString = JOptionPane.showInputDialog("Escribe un ID de empleado");
                return Integer.parseInt(dptoString);
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Escribe un numero entero, por favor");
            }catch(Exception ex){
                throw ex;
            }
        }while (true);
    }

    public static double pedirSalario() throws Exception{
        do{
            try{
                String salarioString = JOptionPane.showInputDialog("Escribe un salario");
                return Double.parseDouble(salarioString);
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Escribe un salario válido, por favor");
            }catch(Exception ex){
                throw ex;
            }
        }while (true);
    }
}