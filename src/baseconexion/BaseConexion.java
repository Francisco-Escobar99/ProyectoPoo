
package baseconexion;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.Scanner;

public class BaseConexion {
    
 Connection con;
    
    
    public BaseConexion(){
     
     try{
         Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bd191234","root","escobar1999");
         System.out.println("Conexion establecida");
         
     }catch(ClassNotFoundException | SQLException e){
         System.err.println("Error"+e);
         
     }
 }
    
    
    public void Menu(){
       int seleccion;
      
       Servicios mostrado=new Servicios();
        Scanner opc= new Scanner(System.in);
        Gerente persona=new Gerente();
        System.out.println("Gerente:");
        persona.setGrte("Gerardo Ruiz Escobar");
        System.out.println(persona.getGrte());
        persona.AccesoGerente();
        do{
        System.out.println("Que accion desea realizar");
       System.out.println("1. Registrar servicio  2. Eliminar servicio  3. Actualizar servicio  4.Mostrar Servicio");
       seleccion=opc.nextInt();
       switch(seleccion){
           case 1: 
                 mostrado.InsertarServicio();
               break;
           case 2: 
                 mostrado.EliminarServicio();
               break; 
           case 3: 
                 mostrado.Actualizarservicio();
                
               break;
           case 4: 
                 mostrado.Mostrarservicio();
                break;
       }
       
       System.out.println("desea realizar otra actividad (1) Salir(2)");
        seleccion=opc.nextInt();
        }while(seleccion==1);
}
    
    }
    

