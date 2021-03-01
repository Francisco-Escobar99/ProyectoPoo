/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseconexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ventas_Servicios extends BaseConexion{
    int idServicio, acumulador,guardarventa, pagar;
    int cantidad, seleccion;
    int TotalPagar=0;
    int total,dia,mes,anio,Precio;
    String Nombre;
     
    public void RealizarVenta(Statement Consulta) throws SQLException{
        Scanner teclado =new Scanner(System.in);
        
       try{
            
         String SQL=("SELECT * from ventas");
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(SQL);
         
         while(rs.next())
         {
            acumulador=acumulador+1;
             
         }
        }
       
        catch (Exception e){
            e.printStackTrace();
        }       
        
        guardarventa=guardarventa+acumulador;
        
        System.out.println("ingrese el dia:");
        dia=teclado.nextInt();
        System.out.println("Mes:");
        mes=teclado.nextInt();
        System.out.println("Año:");
        anio=teclado.nextInt();
        
       do{
           guardarventa=guardarventa+1;
        System.out.println("servicios disponibles de la Tienda");
        Ventas_Servicios ver=new Ventas_Servicios();
        Servicios servicio =new Servicios();
        servicio.Mostrarservicio();
        ver.contadorVentaReporte();
        
       try{ 
        System.out.println("Que servicio desea realizar (ingrese id)");
        idServicio=teclado.nextInt();
        System.out.println("cantidad:");
        cantidad=teclado.nextInt();
         Consulta = con.createStatement();
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
        consulta.execute("insert into ventas (idServicio, Descripcion, Cantidad, Total, Dia, Mes, Año) VALUES('"+idServicio+"','"+Nombre+"','"+cantidad+"','"+total+"','"+dia+"','"+mes+"','"+anio+"')");
        
        }catch(Exception e) 
            {
            System.out.println("Error: "+e.getMessage());
        }
       TotalPagar=TotalPagar+total;
      System.out.println("desea realizar otro servicio 1(si) 2(no) ");
      seleccion=teclado.nextInt();
       }while(seleccion==1);
       System.out.println("su total es de: $"+TotalPagar);
       System.out.println("con cuanto va a pagar");
       pagar=teclado.nextInt();
       pagar=pagar-TotalPagar;
    }   

   public void mostrarTicket(){
        int numero=(int)(Math.random()*10000+1);
        System.out.println("*******************************************************************");
       System.out.println("\t\t\t\t ||Recibo de pago||");
       System.out.println("\t\t\t   Tienda de servicios *Francisco*");
       System.out.println("Ubicado en 11 sur poniente #136 entre calle Central y 1ra poniente");
       System.out.println("\t\t\tTuxtla Gutierrez, Chiapas");
       System.out.println("\t\tNo.Ticket: "+numero);
       System.out.println("\t\tTel.:9612341221      fecha:"+dia+"/"+mes+"/"+anio);
      
       System.out.println("____________________________________________________________________");
       System.out.println("id\t\tdescripcion\t\t   cantidad\t\tprecio");
       
       try{
            
         String SQL=("SELECT * from ventas WHERE Dia="+dia);
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(SQL);
         
         while(rs.next())
         {
           System.out.println(rs.getInt("idServicio")+"\t"+rs.getString("Descripcion")+"\t\t"+rs.getString("Cantidad")+"\t\t"+rs.getString("Total"));
             
         }
        }
       
        catch (Exception e){
            e.printStackTrace();
        } 
          System.out.println("____________________________________________________________________");
          System.out.println("\t\t\t\t\tTotal: $"+TotalPagar);
          System.out.println("\t\t\t\t\tCambio: $"+pagar);
       
}
  
   public  void EliminarVenta(){
      
      Ventas_Servicios ver=new Ventas_Servicios();
       ver.VentaMinReporte();
       
       try{
        Scanner teclado =new Scanner(System.in);
        
        System.out.println("ingrese el dia de la venta");
        dia=teclado.nextInt();
        Statement consulta=con.createStatement();
        consulta.executeUpdate("DELETE FROM ventas WHERE Dia="+dia);
        con.close();
         System.out.println("venta eliminada");
        }catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
            }
        }
           
   public void MostrarVenta(){
       try{
       
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ventas");
            System.out.println("_______________________________________________________________________");
            System.out.println("id\tdescripcion\t\tcantidad\tprecio\t\t\tdia|mes|año");
        while(rs.next())
        {
            System.out.println(rs.getInt("idServicio")+"\t"+rs.getString("Descripcion")+"\t\t"+rs.getString("Cantidad")+"\t\t"+rs.getString("Total")+"\t"+rs.getInt("Dia")+"/"+rs.getInt("Mes")+"/"+rs.getInt("Año"));
        }
            System.out.println("_______________________________________________________________________");
        //con.close();
         //stmt.close();
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
   }
   
    public void ActualizarVenta(){
        int result=0;
        Scanner teclado =new Scanner(System.in);
        Ventas_Servicios ver=new Ventas_Servicios();
        ver.VentaMaxReporte();
        ver.MostrarVenta();
        try{
       
        System.out.println("ingrese el dia");
        dia=teclado.nextInt();
       System.out.println("ingrese el id del servicio del servicio");
       idServicio=teclado.nextInt();
       
        java.sql.Statement stmt = con.createStatement();
        result=stmt.executeUpdate("UPDATE ventas SET Dia ='"+dia+"' WHERE idServicio=" +idServicio);
        con.close();
         System.out.println("dia actualizado");
        
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
    }
   public void Menu6() throws SQLException{
       int seleccion;
       Gerente mostrarG= new Gerente(); 
        Ventas_Servicios mostrado=new Ventas_Servicios();
        
        //Usuarios ver=new Usuarios();
        Scanner opc= new Scanner(System.in);
      //  ver.InicioSesion(null);
        do{
            int num=1029;
        System.out.println("Que accion desea realizar");
        System.out.println("1. Realizar venta  2. Eliminar venta  3. Actualizar fecha de venta 4. Mostrar ventas    5.Corte del dia   6.Corte del mes");
        seleccion=opc.nextInt();
        switch(seleccion){
           case 1: 
               mostrado.RealizarVenta(null);
             
                mostrado.mostrarTicket();
               
               break;
           case 2: 
                // mostrado.VentaMinReporte();
                 mostrado.MostrarVenta();
                
                 mostrado.EliminarVenta();
                 
               break; 
           case 3:         
                 mostrado.ActualizarVenta();
               break;
           case 4: 
                 mostrado.MostrarVenta();
                 mostrado.VentaSumaReporte();
                break;
           case 5:
               
               mostrado.Corte(dia);
               break; 
           case 6:
               mostrarG.AccesoGerente();
               mostrado.Corte(mes,dia);
               break;
       }
        System.out.println("desea realizar otra actividad (1) Salir(2)");
        seleccion=opc.nextInt();
       
        }while(seleccion==1);
       
   }
   
   public void Corte(int dia){
       Scanner teclado =new Scanner(System.in);
       try{
        
            System.out.println("Ingrese el dia");
            dia=teclado.nextInt();
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ventas WHERE Dia="+dia);
            System.out.println("_______________________________________________________________________");
            System.out.println("id\tdescripcion\t\tcantidad\ttotal");
        while(rs.next())
        {
            System.out.println(rs.getInt("idservicio")+"\t"+rs.getString("Descripcion")+"\t\t"+rs.getInt("Cantidad")+"\t\t"+rs.getInt("Total"));
        }
            System.out.println("_______________________________________________________________________");
        con.close();
         stmt.close();
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
}
 
   public void contadorVentaReporte(){
       int nRegistros;
       try{
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT count(*)total FROM servicios");
            if(rs.next())
        {
            nRegistros=(rs.getInt("Total"));
            System.out.println("la cantidad de servicios son de:"+nRegistros);
        }
            else{
                nRegistros=0;
            }
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
         
   }
   
   public void VentaMaxReporte(){
       String nRegistros;
       try{
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT max(Total)total FROM ventas");
            if(rs.next())
        {
            nRegistros=(rs.getString("Total"));
            System.out.println("el precio mas alto es de: $"+nRegistros);
        }
            
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
         
   }
   public void VentaMinReporte(){
       String nRegistros;
       try{
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT min(Total)total FROM ventas");
            if(rs.next())
        {
            nRegistros=(rs.getString("Total"));
            System.out.println("el precio mas bajo es: $"+nRegistros);
        }
            
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
         
   }
   
   public void VentaSumaReporte(){
       String nRegistros;
       try{
         java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT sum(Total)total FROM ventas");
            if(rs.next())
        {
            nRegistros=(rs.getString("Total"));
            System.out.println("la cantidad  total de ventas registradas es de: $"+nRegistros);
        }
            
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
   }

   
    public void Corte(int mes, int dia){
       Scanner teclado =new Scanner(System.in);
       try{
        
            System.out.println("Ingrese el mes");
            mes=teclado.nextInt();
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ventas WHERE Mes="+mes);
            System.out.println("_______________________________________________________________________");
            System.out.println("id\t\tdescripcion\t\t\tcantidad\ttotal\tdia|mes|año");
        while(rs.next())
        {
            System.out.println(rs.getInt("idservicio")+"\t"+rs.getString("Descripcion")+"\t\t"+rs.getInt("Cantidad")+"\t\t"+rs.getInt("Total")+"\t"+rs.getInt("Dia")+"/"+rs.getInt("Mes")+"/"+rs.getInt("Año"));
        }
            System.out.println("_______________________________________________________________________");
        con.close();
         stmt.close();
        }catch(Exception e)
            {
            System.out.println("Error: "+e.getMessage());
        }
   }
     
}