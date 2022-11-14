import Clases.Empleado;

import javax.swing.*;
import java.io.EOFException;
import java.io.RandomAccessFile;

public class Main {

    public static RandomAccessFile bd = null;

    public static void main(String[] args) throws Exception {
        try{
            bd = new RandomAccessFile("empleados.dat", "rw");
            do{
                Empleado emple = pedirEmpleadoAlUsuario();
                insertarEmpleadoEnFichero(emple);
                leerDatos();
            }while(JOptionPane.showConfirmDialog(null,"Deseas insertar otro empleado?") == 0);
            bd.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            bd.close();
            throw ex;
        }
    }

    public static void insertarEmpleadoEnFichero(Empleado emple) throws Exception{
        try{
            bd.seek(bd.length());
            bd.writeInt(obtenerUltimoId());
            StringBuffer buffer = new StringBuffer(emple.getApellido());
            buffer.setLength(10);
            bd.writeChars(buffer.toString());
            bd.writeInt(emple.getDepartamento());
            bd.writeDouble(emple.getSalario());
        }catch (Exception ex){
            throw ex;
        }
    }

    public static int obtenerUltimoId() throws Exception{
        try{
            int posicion = 0;
            int ultimoId = 0;
            bd.seek(0);
            System.out.println(bd.length());
            while(bd.getFilePointer() < bd.length()){
                System.out.println(bd.getFilePointer());
                ultimoId = bd.readInt();
                posicion += 36;
                bd.seek(posicion);
            }
            return ultimoId+1;
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

    public static Empleado pedirEmpleadoAlUsuario() throws Exception {
        try{
            String apellido = pedirApellido();
            int departamento = pedirDepartamento();
            double salario = pedirSalario();
            Empleado emple = new Empleado(apellido,departamento,salario);
            return emple;
        }catch (Exception ex){
            throw ex;
        }
    }

    public static String pedirApellido() throws Exception{
        try{
            String apellido = JOptionPane.showInputDialog("Escribe el apellido");
            return apellido == null ? "":apellido;
        }catch(Exception ex){
            throw ex;
        }
    }

    public static int pedirDepartamento() throws Exception{
        do{
            try{
                String dptoString = JOptionPane.showInputDialog("Escribe un departamento (numero entero)");
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