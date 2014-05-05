/*
 * Controlador del profesor
 */

package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ProfesorBD;
import modelo.CursoBD;

/**
 *
 * @author Marco Aurelio Nila Fonseca
 * @version 1.0
 */
@WebServlet(name = "Profesor", urlPatterns = {"/Profesor"})
public class Profesor extends HttpServlet {
    
    private final String CONFIRMACION_REGISTRO = "0";
    private final String RECHAZO_REGISTRO = "1";
    private final String CONFIRMACION_EDICION = "2";
    private final String RECHAZO_EDICION = "3";
    private final String CONFIRMACION_BORRADO = "4";
    private final String RECHAZO_BORRADO = "5";
    private final String CONFIRMACION = "6";
    private final String RECHAZO = "7";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        

        if (operacion == null) {
            int tipo;
            try {
                tipo = Integer.parseInt(request.getParameter("tipo"));
            } catch (NumberFormatException e) {
                return;
            }
            ProfesorBD profesor;
            String filtro;
            switch(tipo){
                //Iniciar sesión
                case 0 :
                    String id = request.getParameter("id");
                    String contrasena = request.getParameter("contrasena");
                    if (id == null || contrasena == null || id.trim().equals("") || contrasena.trim().equals("")) {
                        System.out.println("saliendo");
                        return;
                    }
                    id = id.trim();
                    contrasena = contrasena.trim();
                    profesor = new ProfesorBD();
                    boolean encontrado;
                    try {
                        encontrado = profesor.iniciar_sesion(id, contrasena);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        try (PrintWriter out = response.getWriter()) {
                            out.println("error");
                        }
                        return;
                    }
                    try (PrintWriter out = response.getWriter()) {
                        out.println(encontrado ? "true" : "false");
                    }
                    return;
                //Cuenta número de profesores para poder paginar
                case 1:
                    filtro = request.getParameter("filtro");
                    int cuantos;
                    filtro = filtro == null ? "" : filtro.trim();
                    profesor = new ProfesorBD();
                    try {
                        cuantos = profesor.cuenta_profesores(filtro);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        try (PrintWriter out = response.getWriter()) {
                            out.println("error");
                        }
                        return;
                    }
                    try (PrintWriter out = response.getWriter()) {
                        out.println(cuantos);
                    }
                    return;
                //Profesores para mostrar
                case 2:
                    ArrayList<String> profesores;
                    filtro = request.getParameter("filtro");
                    String cantidad_str = request.getParameter("cantidad");
                    String pagina_str = request.getParameter("pagina");
                    int cantidad, pagina;
                    try {
                        cantidad = Integer.parseInt(cantidad_str);
                        pagina = Integer.parseInt(pagina_str);
                    } catch (Exception e) {
                        return;
                    }
                    filtro = filtro == null ? "" : filtro.trim();
                    System.out.println("Parámetro: " + filtro);
                    profesor = new ProfesorBD();
                    try {
                        profesores = profesor.obten_profesores(filtro, pagina, cantidad);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        try (PrintWriter out = response.getWriter()) {
                            out.println("error");
                        }
                        return;
                    }
                    try (PrintWriter out = response.getWriter()) {
                        String json = new Gson().toJson(profesores);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(json);
                    }
            }
            return;
        } else {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (operacion.equals("registrar_Profesor")) {
            String user = request.getParameter("nombre_Profesor");
            String correo = request.getParameter("correo_Profesor");
            String contrasenia = request.getParameter("contrasenia_Profesor");
            String url_constancia = "data/constancias/" + correo + ".pdf";
            String url_video = "data/videos/" + correo + ".mp4";
            
            if(new ProfesorBD().crear_profesor(user, correo, contrasenia, url_constancia, url_video)) {
                out.println(CONFIRMACION_REGISTRO);
            } else {
                out.print(RECHAZO_REGISTRO);
            }
            
        } else if (operacion.equals("editar_Profesor")) {
            String correoA = request.getParameter("correo_Profesor");
            String nombre = request.getParameter("nuevo_nombre_Profesor");
            String correo = request.getParameter("nuevo_correo_Profesor");
            String contrasenia = request.getParameter("nuevo_contrasenia_Profesor");
            String url_constancia = "data/constancias/" + correo + ".pdf";
            String url_video = "data/videos/" + correo + ".mp4";
            
            if (new ProfesorBD().editar_profesor(correoA, nombre, correo, contrasenia, url_video, url_constancia) == 1) {
                out.println(CONFIRMACION_EDICION);
            } else {
                out.println(RECHAZO_EDICION);
            }
            
        } else if (operacion.equals("eliminar_Profesor")) {
            String correo = request.getParameter("correo_Profesor");
            if (new ProfesorBD().eliminar_profesor(correo) == 1) {
                out.println(CONFIRMACION_BORRADO);
            } else {
                out.println(RECHAZO_BORRADO);
            }

        } else if (operacion.equals("obtener_Notificaciones_Pendientes")) {
            String correo = request.getParameter("correo_Profesor");
            ResultSet rs = new CursoBD().consulta("SELECT ALL `curso_id`, `estudiante_correo`, `curso_tipo`, `curso_inicio`, `curso_final`" +
                                                  "FROM `Escuela`.`Curso` WHERE `curso_estado`='Confirmando' AND `profesor_correo`='" + correo +"';");
            try {
                while (rs.next()) {
                    Object [] fila = new Object[5];

                    for (int i = 1; i <= 5; i++)
                        fila[i-1] = rs.getObject(i);
                    
                    ResultSet r = new CursoBD().consulta("SELECT `estudiante_nombre` FROM `Escuela`.`Estudiante` WHERE `estudiante_correo`='" + 
                                                         fila[1].toString() + "';");
                    r.next();
                    Object nombre_Estudiante = r.getObject(1);
            
                    out.println("<tr>");
                    out.println("<td>" + nombre_Estudiante + "</td>");
                    out.println("<td>" + fila[2] + "</td>");
                    out.println("<td>" + fila[3].toString().substring(0, 5) + " - " + fila[4].toString().substring(0, 5) + "</td>");
                    out.println("<td><a href=\"#\" data-dropdown=\"drop_1\" class=\"button dropdown tiny\">Acción</a><br>");
                    out.println("<ul id=\"drop_1\" data-dropdown-content class=\"f-dropdown\">");
                    out.println("<li><a href=\"#\" onclick=\"asignar_curso(" + fila[0] + ", true )\">Aceptar</a></li>");
                    out.println("<li><a href=\"#\" onclick=\"asignar_curso(" + fila[0] + ", false )\">Rechazar</a></li>");
                    out.println("</ul></td>");
                    out.println("</tr>");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

        } else if (operacion.equals("obtener_Cursos_Espera")) {
            String correo = request.getParameter("correo_Profesor");
            ResultSet rs = new CursoBD().consulta("SELECT ALL `curso_id`, `curso_tipo`, `curso_inicio`, `curso_final`" +
                                                  "FROM `Escuela`.`Curso` WHERE `curso_estado`='Espera' AND `profesor_correo`='" + correo + "';");
            try {
                while (rs.next()) {
                    Object [] fila = new Object[4];

                    for (int i = 1; i <= 4; i++)
                        fila[i-1] = rs.getObject(i);
            
                    out.println("<tr>");
                    out.println("<td>" + fila[1] + "</td>");
                    out.println("<td>" + fila[2].toString().substring(0, 5) + " - " + fila[3].toString().substring(0, 5) + "</td>");
                    out.println("<td><a href=\"#\" class=\"button tiny alert\" onclick=\"eliminar_curso(" + fila[0] + ")\">Eliminar</a></td>");
                    out.println("</tr>");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
 
        } else if (operacion.equals("obtener_Cursos_Actuales")) {
            String correo = request.getParameter("correo_Profesor");
            ResultSet rs = new CursoBD().consulta("SELECT ALL `curso_id`, `estudiante_correo`, `curso_tipo`, `curso_inicio`, `curso_final`" +
                                                  "FROM `Escuela`.`Curso` WHERE `curso_estado`='Cursando' AND `profesor_correo`='" + correo + "';");
            try {
                while (rs.next()) {
                    Object [] fila = new Object[5];

                    for (int i = 1; i <= 5; i++)
                        fila[i-1] = rs.getObject(i);

                    ResultSet r = new CursoBD().consulta("SELECT `estudiante_nombre` FROM `Escuela`.`Estudiante` WHERE `estudiante_correo`='" + 
                                                         fila[1].toString() + "';");
                    r.next();
                    Object nombre_Estudiante = r.getObject(1);
                    
                    out.println("<tr>");
                    out.println("<td>" + nombre_Estudiante + "</td>");
                    out.println("<td>" + fila[2] + "</td>");
                    out.println("<td>" + fila[3].toString().substring(0, 5) + " - " + fila[4].toString().substring(0, 5) + "</td>");
                    out.println("<td>");
                    out.println("<a href=\"#\" class=\"button tiny\" onclick=\"calificar_curso(" + fila[0] + ")\">Calificar</a>");
                    out.println("</td></tr>");

                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if (operacion.equals("obtener_Cursos_Finalizados")) {
            String correo = request.getParameter("correo_Profesor");
            ResultSet rs = new CursoBD().consulta("SELECT ALL `estudiante_correo`, `curso_tipo`, `curso_calificacion`" +
                                                  "FROM `Escuela`.`Curso` WHERE `curso_estado`='Terminado' AND `profesor_correo`='" + correo + "';");
            try {
                while (rs.next()) {
                    Object [] fila = new Object[3];

                    for (int i = 1; i <= 3; i++)
                        fila[i-1] = rs.getObject(i);

                    ResultSet r = new CursoBD().consulta("SELECT `estudiante_nombre` FROM `Escuela`.`Estudiante` WHERE `estudiante_correo`='" + 
                                                         fila[0].toString() + "';");
                    r.next();
                    Object nombre_Estudiante = r.getObject(1);
            
                    out.println("<tr>");
                    out.println("<td>" + nombre_Estudiante + "</td>");
                    out.println("<td>" + fila[1] + "</td>");
                    out.println("<td>" + fila[2] + "</td>");
                    out.println("</tr>");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

        }}

        //try (PrintWriter out = response.getWriter()) {
        //    /* TODO output your page here. You may use following sample code. */
        //    out.println("<!DOCTYPE html>");
        //    out.println("<html>");
        //    out.println("<head>");
        //    out.println("<title>Servlet Profesor</title>");            
        //    out.println("</head>");
        //    out.println("<body>");
        //    out.println("<h1>Servlet Profesor at " + request.getContextPath() + "</h1>");
        //    out.println("</body>");
        //    out.println("</html>");
        //}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
