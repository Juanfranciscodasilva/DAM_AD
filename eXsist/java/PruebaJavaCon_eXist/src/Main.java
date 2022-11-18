import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;
import org.exist.xmldb.EXistResource;

public class Main   {

    private static String URI = "xmldb:exist://172.20.222.105:8080/exist/xmlrpc/db/empleados";

    final static String driver = "org.exist.xmldb.DatabaseImpl";

    public static void main(String[] args) throws Exception {

        System.out.println("INICIADA PRUEBA DE EXIST XML DB");

        prueba();
    }

    public static void prueba() throws Exception{
        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/PruebaFicheros/ColeccionPruebas";
        String usu = "admin";
        String usuPwd = "12345Abcde";

        try{


            // get the collection
            Collection col = DatabaseManager.getCollection(URI, usu, usuPwd);
            XMLResource  res = (XMLResource)col.getResource("empleados.xml");

            if(res == null) {
                System.out.println("document not found!");
            } else {
                System.out.println(res.getContent());
            }
        }catch (Exception ex){
            System.out.println(ex.getStackTrace());
            System.out.println(ex.getMessage());
            throw ex;
        }finally {
            //dont forget to clean up!

            /*if(res != null) {
                try { ((EXistResource)res).freeResources(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }*/

            /*if(col != null) {
                try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }*/
        }
    }
}