/*
 * Controlador del profesor
 */

package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ProfesorBD;

/**
 *
 * @author Marco Aurelio Nila Fonseca
 * @version 1.0
 */
@WebServlet(name = "Profesor", urlPatterns = {"/Profesor"})
public class Profesor extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
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
