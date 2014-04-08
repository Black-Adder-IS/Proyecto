/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author sainoba
 */
public class ConexionBD {
    private String driver;
    private String url;
    private String db;
    private String userName;
    private String password;
    
    
    public ConexionBD() {
        driver ="com.mysql.jdbc.Driver";
        url = "jdbc:mysql://127.0.0.1:3306/";
        db = "Escuela";
        userName = "root";
        password = "";
    }

    public Connection getConexion() throws ClassNotFoundException {
        Connection cn;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url+db, userName, password);
            System.out.println("Todo chido");
            return cn;
        } catch (SQLException ex) {
            System.out.println("error: " + ex);
            return null;
        }
    }

    public void cierraConexion( Connection cn ) {
        try {
            if( cn != null && ! cn.isClosed() ) {
                cn.close();
            }
        } catch( SQLException e ) {
            e.printStackTrace();
        }
    }
    
    public ResultSet consulta (Connection conexion, String consulta) {
        ResultSet resultado = null;
        try {
            // Instrucción SQL para obtener los datos
            // del usuario indicado :
            //String query = "select * from Estudiante";
            String query = consulta;
            Statement st = conexion.createStatement();
            resultado = st.executeQuery(query);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        finally {
            return resultado;
        }
    }
    
}
