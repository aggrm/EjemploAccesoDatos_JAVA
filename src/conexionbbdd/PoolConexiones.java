/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbbdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Alberto Goujon
 */
public class PoolConexiones 
{
    Connection con;
    public PoolConexiones () 
    {
        con = null;
        BasicDataSource bdSource = new BasicDataSource();
        bdSource.setUrl ("jdbc:mysql://localhost:3306/discografica?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        bdSource.setUsername("root");
        bdSource.setPassword("");
        try 
        {
            //DataSource nos reserva una conexión y nos la devuelve
            con = bdSource.getConnection();
            if (con != null) 
            {
                System.out.println("Conexión creada satisfactoriamente");
            } 
            else 
            {
                System.out.println("No se puede crear una nueva conexión");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.toString());
        }
    }
    
    public void cerrar_conexion() 
    {
        try 
        {
            System.out.println("Cerrada conexion con la BBDD");
            con.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("ERROR:al cerrar la conexión");
            ex.printStackTrace();
        }
    }
    
    public void insert_con_commit()
    {
        try 
        {
            con.setAutoCommit(false);
            
            Statement sta = con.createStatement();
            
            sta.executeLargeUpdate("INSERT INTO album " + "VALUES (5, 'Let it be', 'The Beatles')");
            sta.executeUpdate("INSERT INTO album " + "VALUES (6, 'Abbey Road', 'The Beatles')");
        }
        catch (Exception e) 
        {
            
        }
    }
}
