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

/**
 *
 * @author sainoba
 */
public class CursoBD extends ConexionBD{

    public CursoBD() {
        super();
    }
    
    public int cuenta_cursos(String filtro) throws SQLException{
        int cantidad = 0;
        String consulta ="SELECT count(curso_id) AS cantidad " + 
            "FROM `Escuela`.`Profesor` AS foo JOIN " +
            "`Escuela`.`Curso` AS bar ON foo.profesor_correo = bar.profesor_correo " +
            "WHERE curso_estado = \"Espera\" ";
    
        if (!filtro.trim().equals("")) {
            consulta += "AND curso_tipo = \"" + filtro.trim()+"\" ";
        }
        consulta += ";";
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
    
    public ArrayList<String> obten_cursos(String filtro, int pagina, int cantidad) throws SQLException{
        ArrayList<String> profesores = new ArrayList<String>();
        String consulta ="SELECT profesor_id, profesor_nombre, curso_id, curso_tipo, DATE_FORMAT(curso_inicio, '%H:%i') AS curso_inicio, DATE_FORMAT(curso_final, '%H:%i') AS curso_final " + 
            "FROM `Escuela`.`Profesor` AS foo JOIN " +
            "`Escuela`.`Curso` AS bar ON foo.profesor_correo = bar.profesor_correo " +
            "WHERE curso_estado = \"Espera\" ";
    
        if (!filtro.trim().equals("")) {
            consulta += "AND curso_tipo = \"" + filtro.trim() + "\" ";
        }
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
//profesor_id, profesor_nombre, curso_id, curso_tipo, curso_inicio, curso_final          
            String cadena = "<tr>";
            cadena += "<td><a href=\"../profesor?id=" + resultado.getInt("profesor_id") +"\">" + resultado.getString("profesor_nombre") +"</a></td>";
            cadena += "<td>"+resultado.getString("curso_tipo")+"</td>";
            cadena += "<td>"+resultado.getString("curso_inicio")+" - "+ resultado.getString("curso_final") +"</td>";
            cadena += "<td>"+"<a href=\"#\" class=\"button success radius tiny\">Solicitar</a>"+"</td>";
            cadena +=  "</tr>";
            profesores.add(new String(cadena));
        }
        cierraConexion(conexion);
        return profesores;
    }
}
