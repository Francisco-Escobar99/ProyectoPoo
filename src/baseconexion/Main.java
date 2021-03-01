/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseconexion;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) throws SQLException {
        
        int seleccion;
        Scanner opc= new Scanner(System.in);
        Gerente persona=new Gerente();
        //polimorfismo
        BaseConexion most= new BaseConexion();
        BaseConexion most1= new Cotizacion();
        BaseConexion most2= new Usuarios();
       do{
       System.out.println("Tienda de servicios Francisco");
       System.out.println("Que accion desea realizar");
       System.out.println("1. Ventas  2.Usuarios  3.Servicios 4.Cotizacion");
       seleccion=opc.nextInt();
       switch(seleccion){
           case 1: 
                Usuarios ver=new Usuarios();
                ver.InicioSesion(null);
               
               Ventas_Servicios mostrar6=new Ventas_Servicios();
               mostrar6.Menu6();
               
               
               break;
           case 2: 
                 System.out.println("Gerente:");
                 persona.setGrte("Gerardo Ruiz Escobar");
                 System.out.println(persona.getGrte());
                 persona.AccesoGerente();
                 
                 most2.Menu();
               break; 
           case 3: 
               most.Menu();
               break;
           case 4:
               most1.Menu();
               break;
               
       }
       }while(seleccion!=6);
}
}