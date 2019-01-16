/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Alberto Goujon
 */
public class GestorConexion 
{
    Connection conn1;
    public GestorConexion() 
    {
        conn1 = null;
        try 
        {
            String url1 = "jdbc:mysql://localhost:3306/discografica?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "";
            conn1 = (Connection)DriverManager.getConnection(url1, user, password);
            if (conn1 != null) 
            {
                System.out.println("Conectado a discográfica…");
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("ERROR: dirección o usuario/clave no válida");
            ex.printStackTrace();
        }
    }
    
    public void cerrar_conexion() 
    {
        try 
        {
            System.out.println("Cerrada conexion con la BBDD");
            conn1.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("ERROR:al cerrar la conexión");
            ex.printStackTrace();
        }
    }
    
    public void addYearPublicacion()
    {
        try 
        {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("ALTER TABLE album ADD anno_publicacion YEAR");
            System.out.println("Columna añadida");
            sta.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("ERROR");
        }
    }
    
    public void borrarTabla()
    {
        try 
        {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("DROP TABLE album");
            System.out.println("Borrada");
            sta.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("ERROR");
        }
    }
    
    public void insertar()
    {
        try 
        {
            // Crea un statement
            Statement sta = conn1.createStatement();
            // Ejecuta la inserción
            sta.executeUpdate("INSERT INTO album VALUES (2,'Greatest Hits','Queen','1981')");
            // Cierra el statement
            sta.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
    }
}
