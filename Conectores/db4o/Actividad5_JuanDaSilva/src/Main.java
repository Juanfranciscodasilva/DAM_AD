import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {
    final static String BDPer = "EMPLEDEP.yap";

    public static void main(String[] args) {
        ObjectContainer db= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDPer);

        //insertarDatos(db);

        ObjectSet<Departamento> result = db.queryByExample(new Departamento());

        while (result.hasNext()) {
            Departamento dpto = result.next();
            System.out.println("------------DEPARTAMENTO---------------");
            System.out.println(dpto.toString());
            System.out.println("------------EMPLEADOS DEL DEPARTAMENTO---------------");
            for(Empleado emple : dpto.getEmpleados())
            System.out.println(emple.toString());

            System.out.println("----------------------------------------------------\n");
        }

        result = db.queryByExample(new Departamento());

        while (result.hasNext()) {
            Departamento dpto = result.next();
            System.out.println("------------DEPARTAMENTO---------------");
            System.out.println(dpto.toString());
            System.out.println("------------EMPLEADOS DEL DEPARTAMENTO---------------");
            for(Empleado emple : dpto.getEmpleados())
                System.out.println(emple.toString());

            System.out.println("----------------------------------------------------\n");
        }

        db.close();

    }

    public static void insertarDatos(ObjectContainer db){

        Empleado e1 = new Empleado(7369,"SÁNCHEZ","EMPLEADO",20);
        Empleado e2 = new Empleado(7499,"ARROYO","VENDEDOR",30);
        Empleado e3 = new Empleado(7521,"SALA","VENDEDOR",30);
        Empleado e4 = new Empleado(7566,"JIMÉNEZ","DIRECTOR", 20);
        Empleado e5 = new Empleado(7782,"CEREZO","DIRECTOR",10);
        Empleado e6 = new Empleado(7839,"REY","PRESIDENTE",10);

        if(db.queryByExample(e1).size() == 0){
            db.store(e1);
        }
        if(db.queryByExample(e2).size() == 0){
            db.store(e2);
        }
        if(db.queryByExample(e3).size() == 0){
            db.store(e3);
        }
        if(db.queryByExample(e4).size() == 0){
            db.store(e4);
        }
        if(db.queryByExample(e5).size() == 0){
            db.store(e5);
        }
        if(db.queryByExample(e6).size() == 0){
            db.store(e6);
        }

        Departamento d1 = new Departamento(10,"CONTABILIDAD","SEVILLA");
        d1.getEmpleados().add(e5);
        d1.getEmpleados().add(e6);
        Departamento d2 = new Departamento(20,"INVESTIGACIÓN","MADRID");
        d2.getEmpleados().add(e1);
        d2.getEmpleados().add(e4);
        Departamento d3 = new Departamento(30,"VENTAS","BARCELONA");
        d3.getEmpleados().add(e2);
        d3.getEmpleados().add(e3);

        if(db.queryByExample(d1).size() == 0){
            db.store(d1);
        }
        if(db.queryByExample(d2).size() == 0){
            db.store(d2);
        }
        if(db.queryByExample(d3).size() == 0){
            db.store(d3);
        }

    }
}