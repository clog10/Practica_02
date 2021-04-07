/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.client.am.Connection;

/**
 *
 * @author Carlos Loaeza
 */
public class Conexion {

    private static Connection coneccion;
    private static int numConexiones = 0; // después debes de descubrir para qué es útil 
    // el tercer atributo créalo pues es lo que caracteriza al patrón Singleton
    private static Conexion conexion;

    public Conexion(String url, String usuario, String password) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try {
                coneccion = (Connection) DriverManager.getConnection(url, usuario, password);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Conexion getConexion(String url, String usuario, String password){
        numConexiones++;
        if(conexion == null){
            conexion = new Conexion(url, usuario, password);
        }
        return conexion;
    }
    
    public static Connection getConneccion(){
        return coneccion;
    }
    
    public boolean cerrarConexion(){
        try{
            if(coneccion!=null)
                if(numConexiones==1){
                    coneccion.close();
                    return true;
                }else
                    numConexiones--;
            return false;
        }catch(SQLException e){
            System.err.println("Error al tratar de cerrar la conexión "+e);
        }
        return false;
    }
}
