/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sainoba
 */
public class ProfesorBD extends ConexionBD{
    
    public ProfesorBD(){
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
        String consulta = "select * from Profesor where profesor_correo = '" + id + "' AND profesor_contrasena = '" + contrasena +"';";
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
    
    public int cuenta_profesores(String filtro) throws SQLException{
        int cantidad = 0;
        String consulta ="SELECT count(profesor_id) AS cantidad FROM " +
        "(SELECT profesor_id, profesor_nombre, sum(cantidad_terminado) AS cantidad_terminado, sum(cantidad_espera) AS cantidad_espera, sum(cantidad_cursando) AS cantidad_cursando " +
        "FROM Profesores ";
    
        if (!filtro.trim().equals("")) {
            consulta += "WHERE curso_tipo = \"" + filtro.trim() + "\" ";
        }
        consulta += "GROUP BY profesor_id) AS foo; ";
        System.out.println(consulta);
        Connection conexion;
        try {
            conexion = getConexion();
        } catch (ClassNotFoundException ex) {
            throw new SQLException();
        }
        ResultSet resultado =  consulta(conexion, consulta);
        if (resultado == null) {
            return -1;
        }
        if (resultado.next()) {
            cantidad = resultado.getInt("cantidad");
        }
        cierraConexion(conexion);
        return cantidad;
    }
    
    public ArrayList<String> obten_profesores(String filtro, int pagina, int cantidad) throws SQLException{
        ArrayList<String> profesores = new ArrayList<String>();
        String consulta ="SELECT * FROM " +
        "(SELECT profesor_id, profesor_nombre, sum(cantidad_terminado) AS cantidad_terminado, sum(cantidad_espera) AS cantidad_espera, sum(cantidad_cursando) AS cantidad_cursando " +
        "FROM Profesores ";
    
        if (!filtro.trim().equals("")) {
            consulta += "WHERE curso_tipo = \"" + filtro.trim() + "\" ";
        }
        consulta += "GROUP BY profesor_id) AS foo ";
        consulta += "LIMIT " + pagina * cantidad + ", " + (pagina + 1) * cantidad +";";
        System.out.println(consulta);
        Connection conexion;
        try {
            conexion = getConexion();
        } catch (ClassNotFoundException ex) {
            throw new SQLException();
        }
        ResultSet resultado =  consulta(conexion, consulta);
        if (resultado == null) {
            return null;
        }
        while (resultado.next()) {            
            String cadena = "<tr>";
            cadena += "<td><a href=\"../profesor?id=" + resultado.getInt("profesor_id") +"\">" + resultado.getString("profesor_nombre") +"</a></td>";
            cadena += "<td>"+resultado.getInt("cantidad_terminado")+"</td>";
            cadena += "<td>"+resultado.getInt("cantidad_cursando")+"</td>";
            cadena += "<td>"+resultado.getInt("cantidad_espera")+"</td>";
            cadena +=  "</tr>";
            profesores.add(new String(cadena));
        }
        cierraConexion(conexion);
        return profesores;
    }
}

