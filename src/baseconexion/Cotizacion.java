
package baseconexion;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public class Cotizacion extends BaseConexion {
    
   String Nombre;
    int precio,cantidad, idServicio,Precio,total;
    int idCliente;
    String nombredelCliente;
    String ApellidoPaterno;
   String ApellidoMaterno;
   String direccion,fecha;
    private Object consulta;
   
   public  void AgregarCotizacion (){
        Scanner teclado =new Scanner(System.in);
        System.out.println("Fecha");
        fecha=teclado.nextLine();
        System.out.println("ingrese el nombre del cliente");
        nombredelCliente=teclado.nextLine();
        System.out.println("ingrese el Apellido paterno");
        ApellidoPaterno=teclado.nextLine();
        System.out.println("ingrese el Apellido materno");
        ApellidoMaterno=teclado.nextLine();
        System.out.println("ingrese su direccion");
        direccion=teclado.nextLine();
       
        
       try{ 
        System.out.println("eliga el servicio (ingrese id)");
        idServicio=teclado.nextInt();
        System.out.println("cantidad de prendas:");
        cantidad=teclado.nextInt();
        Statement Consulta = con.createStatement();
        try{
            
         String SQL=("SELECT Descripcion, precio FROM servicios WHERE idServicio="+idServicio);
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(SQL);
         
         while(rs.next())
         {
             Nombre=(rs.getString("Descripcion"));
             Precio= (rs.getInt("Precio"));
             
         }
        }
       
        catch (Exception e){
            e.printStackTrace();
        }       
    }
       catch(Exception e)
       {
           e.getStackTrace();
       }
       //de 1 a 7 prendas froman el 1kg
       if(cantidad>=1 && cantidad<=7){
           total=Precio;
       }else{
           // de 8 a 14 =2kg
          if(cantidad>=8 && cantidad<=14){
              total=Precio*2;
          } else{
            if(cantidad>=14 && cantidad<=20){
                total=Precio*3;
            }   
          }
       }
        try{
            
        Statement consulta=con.createStatement();
        consulta.execute("insert into cotizacion (Nombre, ApellidoPaterno, ApellidoMaterno, Direccion, Descripcion, Cantidad, Precio, Fecha) VALUES('"+nombredelCliente+"','"+ApellidoPaterno+"','"+ApellidoMaterno+"','"+direccion+"','"+Nombre+"','"+cantidad+"','"+total+"','"+fecha+"')");
        //con.close();
         System.out.println("datos insertados");
        }catch(Exception e) 
            {
            System.out.println("Error: "+e.getMessage());
        }
        
        System.out.println("\t\t\t   Tienda de servicios *Francisco*");
       System.out.println("Ubicado en 11 sur poniente #136 entre calle Central y 1ra poniente");
       System.out.println("\t\t\tTuxtla Gutierrez, Chiapas");
       System.out.println("\t\tTel.:9612341221      fecha:"+fecha);
       System.out.println("____________________________________________________________________");
       System.out.println("id cleinte\t\tnombre\t\tApellido Paterno\t\tApellido Materno\t\tdireccion\t\tdescripcion\t\tcantidad\tTotal");
       
       try{
            
         String SQL=("SELECT * from cotizacion WHERE Fecha="+fecha);
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(SQL);
         
         while(rs.next())
         {
            System.out.println(rs.getInt("idCliente")+"\t"+rs.getString("Nombre")+"\t\t"+rs.getString("ApellidoPaterno")+"\t\t"+rs.getString("ApellidoMaterno")+"\t\t\t"+rs.getString("Direccion")+"\t\t"+rs.getString("Descripcion")+"\t\t"+rs.getInt("Cantidad")+"\t"+rs.getInt("Precio"));
             
         }
        }
       
        catch (Exception e){
            e.printStackTrace();
        } 
       
       
    }
   
   public  void EliminarCotizacion(){
       try{
        Scanner teclado =new Scanner(System.in);
        
        System.out.println("ingrese el id del cliente");
        idCliente=teclado.nextInt();
        Statement consulta=con.createStatement();
        consulta.executeUpdate("DELETE FROM cotizacion WHERE idCliente="+idCliente);
        con.close();
         System.out.println("cliente eliminado");
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
    }
   
   public void MostrarCotizacion(){
       try{
        

            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cotizacion");
             System.out.println("_______________________________________________________________________");
            System.out.println("id\tNombre(cliente)\tApellido Paterno\tApellido Materno\tDireccion\tdescripcion\tcantidad\tprecio");
        while(rs.next())
        {
            System.out.println(rs.getInt("idCliente")+"\t"+rs.getString("Nombre")+"\t\t"+rs.getString("ApellidoPaterno")+"\t\t"+rs.getString("ApellidoMaterno")+"\t\t\t"+rs.getString("Direccion")+"\t\t"+rs.getString("Descripcion")+"\t\t"+rs.getInt("Cantidad")+"\t"+rs.getInt("Precio"));
        }
            System.out.println("_______________________________________________________________________");
        con.close();
         stmt.close();
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
   }  
   
   public void ActualizarCotizacion(){
        int result=0;
        try{
        Scanner teclado =new Scanner(System.in);
        
        System.out.println("ingrese la nueva direccion");
        direccion=teclado.nextLine();
        System.out.println("ingrese el id del cliente");
        idCliente=teclado.nextInt();
        
        java.sql.Statement stmt = con.createStatement();
        result=stmt.executeUpdate("UPDATE cotizacion SET direccion ='"+direccion+"' WHERE idCliente=" +idCliente);
        con.close();
         System.out.println("registro actualizado");
        
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
    }
   public void Menu(){
       int seleccion;
       
       Cotizacion mostrado=new Cotizacion();
        Scanner opc= new Scanner(System.in);
         Usuarios ver=new Usuarios();
         ver.InicioSesion(null);
         do{
        System.out.println("Que accion de cotizacion desea realizar");
       System.out.println("1. Registrar cotizacion  2. Eliminar cotizacion  3. Actualizar cotizacion 4. Mostrar cotizacion");
       seleccion=opc.nextInt();
       switch(seleccion){
           case 1: 
                Servicios sro= new Servicios();
                sro.Mostrarservicio();
                 mostrado.AgregarCotizacion();
               break;
           case 2: 
                 mostrado.EliminarCotizacion();
               break; 
           case 3: 
                 mostrado.ActualizarCotizacion();
               break;
           case 4: 
                 mostrado.MostrarCotizacion();
                break;
       }
       System.out.println("desea realizar otra actividad precione (1) salir (2)");
       seleccion=opc.nextInt();
        }while(seleccion==1);
   }
}
