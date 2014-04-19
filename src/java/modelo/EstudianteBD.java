/*
 * Clase para atender todas las consultas que tengan que ver con la tabla de estudiantes
 */

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marco Aurelio Nila Fonseca
 * @version 1.0
 */
public class EstudianteBD extends ConexionBD{
    
    public EstudianteBD(){
        super();
    }
    
    /**
     * Método para iniciar sesión
     * @param id para buscar en la base de datos
     * @param contrasena para buscar en la base de datos
     * @return indicador si el usuario existe en la base de datos
     * @throws java.sql.SQLException
     */
    public boolean iniciar_sesion(String id, String contrasena) throws SQLException{
        boolean encontrado;
        String consulta = "select * from Estudiante where estudiante_correo = '" + id + "' AND estudiante_contrasena = '" + contrasena +"';";
        System.out.println(consulta);
        Connection conexion;
        try {
            conexion = getConexion();
        } catch (ClassNotFoundException ex) {
            throw new SQLException();
        }
        ResultSet resultado =  consulta(conexion, consulta);
        if (resultado == null) {
            return false;
        }
        encontrado = resultado.next();
        cierraConexion(conexion);
        return encontrado;
    }
}