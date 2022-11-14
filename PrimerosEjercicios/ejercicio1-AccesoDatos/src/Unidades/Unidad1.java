package Unidades;

import java.io.*;

public class Unidad1 {

    public static void ejercicio1() throws IOException {
        File f = new File("C:/Users/8fdam02/Desktop/f.txt");
        //OutputStream out = new FileOutputStream(f);
        String contenido = "asi es mario";
        FileWriter writer = new FileWriter(f);
        writer.write(contenido);
        writer.flush();
        writer.close();

        //out.write(contenido.getBytes());
        //out.flush();
        //out.close();
        //InputStream input = new FileInputStream(f);
        //String contenidoRecogido = new String(input.readAllBytes());
        FileReader fr = new FileReader(f);
        String a = "";
        int valor=fr.read();
        while(valor!=-1){
            a += (char)valor;
            valor=fr.read();
        }
        System.out.println("Name: "+ f.getName());
        System.out.println("Path: "+ f.getPath());
        System.out.println("Absolute path: "+ f.getAbsolutePath());
        System.out.println("Size: "+ f.getTotalSpace());
        System.out.println("READ?: "+ f.canRead());
        System.out.println("write?: "+ f.canWrite());
        System.out.println("is it a directory?: "+ f.isDirectory());
        System.out.println("is it a file?: "+ f.isFile());
        System.out.println("content?: " + a );
    }

    public static void ejercicio2() throws IOException{
        File d = new File("/dir");
        d.mkdir();
        File f1 = new File("/dir/f1.txt");
        f1.createNewFile();
        File f2 = new File("/dir/f2.txt");
        f2.createNewFile();


        System.out.println(f1.getName()+ " Existe:" + f1.exists()+" File: "+ f1.isFile());
        System.out.println(f2.getName()+" Existe:" + f2.exists()+" File: "+ f1.isFile());
        System.out.println(d.getName()+" Existe:" + d.exists()+" Directorio: "+ d.isDirectory());

        f1.delete();

        System.out.println(f1.getName()+ " Existe:" + f1.exists());

        do{
            File[] archivos = d.listFiles();

            for (File archivo : archivos){
                archivo.delete();
            }
        }while(d.listFiles().length > 0);

        d.delete();

        System.out.println(d.getName()+" Existe:" + d.exists());
    }

    public static void ejercicio3() throws IOException {
        File archivo = new File("C:\\Users\\8fdam02\\Desktop\\prueba.txt");
        InputStream input = new FileInputStream(archivo);
        String contenido = new String(input.readAllBytes());
        System.out.println("*************CONTENIDO*************\n");
        System.out.println(contenido);
        System.out.println("\n***********************************");
    }

    public static void ejercicio4() throws IOException {
        //Hecho en una linea
        //new FileWriter("C:/Users/8fdam02/Desktop/prueba-copia.txt").write("Texto.".toCharArray());

        FileWriter writer = new FileWriter("C:/Users/8fdam02/Desktop/prueba-copia.txt");
        String st = "Texto.";

        writer.write(st.toCharArray());
        writer.flush();
        writer.close();
    }

    public static void ejercicio5() throws IOException {

        FileWriter writer = new FileWriter("C:/Users/8fdam02/Desktop/prueba-copia2.txt");
        //Hecho en una linea
        //Arrays.stream(arrayStrings).forEach(cadena -> {try {writer.write(cadena);} catch (IOException e) {throw new RuntimeException(e);}});

        String[] arrayStrings = new String[] {"esto es el primero"," esto el segundo"," y el tercero"};

        for(String cadena : arrayStrings){
            writer.write(cadena);
        }
        writer.flush();
        writer.close();
    }

    public static void ejercicio5_1() throws IOException {

        FileWriter writer = new FileWriter("C:/Users/8fdam02/Desktop/prueba-copia2.txt");
        //Hecho en una linea
        //Arrays.stream(arrayStrings).forEach(cadena -> {try {writer.write(cadena+"\n");} catch (IOException e) {throw new RuntimeException(e);}});

        String[] arrayStrings = new String[] {"esto es el primero"," esto el segundo"," y el tercero"};

        for(String cadena : arrayStrings){
            writer.write(cadena+"\n");
        }
        writer.flush();
        writer.close();
    }

    public static void ejercicio6() throws IOException {

        File fichero = new File("FichData.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        DataOutputStream dataOS = new DataOutputStream(fileout);
        String nombres[] = {"Ana","Luis, Miguel","Alicia","Pedro","Manuel","Andrés",
                "Julio","Antonio","María Jesús"};
        int edades[] = {14,15,13,15,16,12,16,14,13};
        for (int i=0;i<edades.length; i++){
            dataOS.writeUTF(nombres[i]); //inserta nombre
            dataOS.writeInt(edades[i]); //inserta edad
        }
        dataOS.close(); //cerrar stream

        FileInputStream input = new FileInputStream(fichero);
        DataInputStream dataInput = new DataInputStream(input);
        while(dataInput.available() != 0){
            System.out.print(dataInput.readUTF()+" ");
            System.out.print(dataInput.readInt()+"\n");
        }
        dataInput.close();
    }

    public static void ejercicio7() throws IOException, ClassNotFoundException {

        File fichero = new File("EscribirFichObject.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        ObjectOutputStream objOS = new ObjectOutputStream(fileout);
        Persona per1 = new Persona("Juan", 21);
        Persona per2 = new Persona("Adrian", 24);
        objOS.writeObject(per1);
        objOS.writeObject(per2);
        objOS.close();

        FileInputStream input = new FileInputStream(fichero);
        ObjectInputStream objIS = new ObjectInputStream(input);
        while(input.available() != 0){
            System.out.println((objIS.readObject()).toString());
        }

        objIS.close();
    }
}
