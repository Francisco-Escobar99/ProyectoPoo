/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseconexion;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public class Usuarios  extends BaseConexion{
    
    
   String nombre;
   String ApellidoPaterno;
   String ApellidoMaterno;
   int idUsuario;
   String telefono;
   int contraseña, pass;
    Scanner teclado =new Scanner(System.in);
   public  void AgregarUsuario (){
        
        System.out.println("ingrese el nombre del empleado");
        nombre=teclado.nextLine();
        System.out.println("ingrese el Apellido paterno");
        ApellidoPaterno=teclado.nextLine();
        System.out.println("ingrese el Apellido materno");
        ApellidoMaterno=teclado.nextLine();
        System.out.println("ingrese el su numero telefonico");
        telefono=teclado.nextLine();
        System.out.println("Asignele una contraseña");
        contraseña=teclado.nextInt();
        try{
            
        Statement consulta=con.createStatement();
        consulta.execute("insert into usuarios (Nombre, ApellidoPaterno, ApellidoMaterno, Telefono, Contraseña) VALUES('"+nombre+"','"+ApellidoPaterno+"','"+ApellidoMaterno+"','"+telefono+"','"+contraseña+"')");
        con.close();
         System.out.println("datsos insertados");
        }catch(Exception e) 
            {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
   
   public  void EliminarUsuario(){
       try{
        System.out.println("ingrese el id del usuario");
        idUsuario=teclado.nextInt();
        Statement consulta=con.createStatement();
        consulta.executeUpdate("DELETE FROM usuarios WHERE idUsuario="+idUsuario);
        con.close();
         System.out.println("servicio eliminado");
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
    }
   
   public void MostrarUsuario(){
       try{
        

            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            System.out.println("_______________________________________________________________________");
            System.out.println("id\tNombre(Empleado)\tApellido Paterno\tApellido Materno\tTelefono\tcontraseña");
        while(rs.next())
        {
            System.out.println(rs.getInt("idUsuario")+"\t"+rs.getString("Nombre")+"\t\t"+rs.getString("ApellidoPaterno")+"\t\t"+rs.getString("ApellidoMaterno")+"\t\t\t"+rs.getString("Telefono")+"\t"+rs.getInt("Contraseña"));
        }
            System.out.println("_______________________________________________________________________");
        con.close();
         stmt.close();
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
   }  
   
   public void ActualizarUsuario(){
        int result=0;
        try{
       
        System.out.println("ingrese el telefono del usuario");
        telefono=teclado.nextLine();
        System.out.println("ingrese el id del usuario");
        idUsuario=teclado.nextInt();
        
        java.sql.Statement stmt = con.createStatement();
        result=stmt.executeUpdate("UPDATE usuarios SET Telefono ='"+telefono+"' WHERE idUsuario=" +idUsuario);
        con.close();
         System.out.println("servicio actualizado");
        
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
    }
   
   public void Menu(){
       int seleccion;
       do{
       Usuarios mostrado=new Usuarios();
        Scanner opc= new Scanner(System.in);
        
        System.out.println("Que accion de usuarios desea realizar");
       System.out.println("1. Registrar usuario  2. Eliminar usuario  3. Actualizar usuario 4. Mostrar usuarios");
       seleccion=opc.nextInt();
       switch(seleccion){
           case 1: 
               
                 mostrado.AgregarUsuario();
               break;
           case 2: 
                 mostrado.EliminarUsuario();
               break; 
           case 3: 
                 mostrado.ActualizarUsuario();
               break;
           case 4: 
                 mostrado.MostrarUsuario();
                break;
           
       }
        System.out.println("desea realizar otra actividad (1) Salir(2)");
        seleccion=opc.nextInt();
        }while(seleccion==1);
   }
   public void InicioSesion(Statement Consulta){//antes de realizar alguna venta, eliminar, actualizar
                                                //se le pide su usuario(id) del empleado y contraseña. 
       do{
       try{
       System.out.println("Ingrese su Usuario (id)");
       idUsuario=teclado.nextInt();
       System.out.println("ingrese su contraseña");
       pass=teclado.nextInt();
       Consulta = con.createStatement();
       try{
            
         String SQL=("SELECT Contraseña FROM Usuarios WHERE idUsuario="+idUsuario);
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(SQL);
         
         while(rs.next())
         {
             contraseña=(rs.getInt("Contraseña"));   
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
       }while(pass!=contraseña);
   }
   
}

