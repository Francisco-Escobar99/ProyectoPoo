/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseconexion;

import java.util.Scanner;


public class Gerente {
     private String NombreGerente;
     String usuario="";
     int contraseña=0;
    
    public Gerente()
    {
    }
    
    public String getGrte() {
    return NombreGerente;
  }
    
    public void setGrte(String nombre) {
    this.NombreGerente = nombre;
    } 
    
    
    public void AccesoGerente(){
        Scanner teclado =new Scanner(System.in);
        while((usuario!="gre001") && (contraseña!=1234)){
            System.out.println("autorizacion del gerente");
            System.out.println("Introduzca su Usuario");
            usuario=teclado.nextLine();
            System.out.println("Introduzca su contraseña");
            contraseña=teclado.nextInt();
        }
        System.out.println("Usuario y password correcto");
    }
    }
    
