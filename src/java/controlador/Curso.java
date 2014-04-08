/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import modelo.CursoBD;

/**
 *
 * @author sainoba
 */
@WebServlet(name = "Curso", urlPatterns = {"/Curso"})
public class Curso extends HttpServlet {

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
        CursoBD curso;
        String filtro;
        switch(tipo){
            //Cuenta número de cursos para poder paginar
            case 1:
                filtro = request.getParameter("filtro");
                int cuantos;
                filtro = filtro == null ? "" : filtro.trim();
                curso = new CursoBD();
                try {
                    cuantos = curso.cuenta_cursos(filtro);
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
                ArrayList<String> cursos;
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
                curso = new CursoBD();
                try {
                    cursos = curso.obten_cursos(filtro, pagina, cantidad);
                } catch (SQLException ex) {
                    System.out.println(ex);
                    try (PrintWriter out = response.getWriter()) {
                        out.println("error");
                    }
                    return;
                }
                try (PrintWriter out = response.getWriter()) {
                    String json = new Gson().toJson(cursos);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
        }
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
