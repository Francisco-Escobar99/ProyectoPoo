
package baseconexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author franc
 */
public class Servicios extends BaseConexion {
    
    String nombreServicio;
    int precio;
    int idservicio;
   public  void InsertarServicio (){
       
        Scanner teclado =new Scanner(System.in);
        System.out.println("ingrese la descripcion del servicio");
        nombreServicio=teclado.nextLine();
        System.out.println("ingrese el precio del servicio");
        precio=teclado.nextInt();
        try{
          
        Statement consulta=con.createStatement();
        consulta.execute("insert into servicios (descripcion, precio) values('"+nombreServicio+"',"+precio+")");
        con.close();
         System.out.println("insertado");
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
   
   public  void EliminarServicio(){
       try{
        Scanner teclado =new Scanner(System.in);
        
        System.out.println("ingrese el id del servicio del servicio");
        idservicio=teclado.nextInt();
        Statement consulta=con.createStatement();
        consulta.executeUpdate("DELETE FROM servicios WHERE idServicio="+idservicio);
        con.close();
         System.out.println("servicio eliminado");
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
    }
   
   public void Mostrarservicio(){
       try{
        

            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM servicios");
            System.out.println("_______________________________________________________________________");
            System.out.println("id\t\tdescripcion\t\t\tPrecio/kilo");
        while(rs.next())
        {
            System.out.println(rs.getInt("idservicio")+"\t"+rs.getString("Descripcion")+"\t"+rs.getInt("Precio"));
        }
        System.out.println("_______________________________________________________________________");
        con.close();
         stmt.close();
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
        
   }  
   
   public void Actualizarservicio(){
        int result=0;
        try{
        Scanner teclado =new Scanner(System.in);
        Servicios mostrarM=new Servicios();
        mostrarM.Mostrarservicio();
        
        
        System.out.println("ingrese el precio del servicio");
        precio=teclado.nextInt();
        System.out.println("ingrese el id del servicio del servicio");
        idservicio=teclado.nextInt();
        
        java.sql.Statement stmt = con.createStatement();
        result=stmt.executeUpdate("UPDATE servicios SET Precio ='"+precio+"' WHERE idServicio=" +idservicio);
        con.close();
         System.out.println("servicio actualizado");
        
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
   
}
