/*
 * Clase general para acceso a la base de datos
 */

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Marco Aurelio Nila Fonseca
 * @version 1.0
 */
public class ConexionBD {
    private String driver;
    private String url;
    private String db;
    private String userName;
    private String password;
    
    
    /**
     * Constructor de la clase
     */
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

    /**
     * Cierra la conexión a la base de datos
     * @param cn es la conexión que se va a cerrar
     */
    public void cierraConexion( Connection cn ) {
        try {
            if( cn != null && ! cn.isClosed() ) {
                cn.close();
            }
        } catch( SQLException e ) {
            e.printStackTrace();
        }
    }
    
    /**
     * Hace las consultas a la base de datos
     * @param conexion es la conexion a la base de datos
     * @param consulta es la consulta que se le va a hacer a la base de datos
     * @return  el resultado de la consulta
     */
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
    
    /**
     * Acutaliza la base de datos
     * @param conexion es la conexión a la base de datos
     * @param consulta es la actualización que se le hará a la base de datos
     * @return la cantidad de renglones de la base de datos que se actualizaron
     */
    public int actualiza (Connection conexion, String consulta) {
        int resultado = 0;
        try {
            // Instrucción SQL para obtener los datos
            // del usuario indicado :
            //String query = "select * from Estudiante";
            String query = consulta;
            Statement st = conexion.createStatement();
            resultado = st.executeUpdate(query);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        finally {
            return resultado;
        }
    }
}
