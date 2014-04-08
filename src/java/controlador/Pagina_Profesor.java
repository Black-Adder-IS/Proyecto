/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ProfesorBD;

/**
 *
 * @author sainoba
 */
@WebServlet(name = "Pagina_Profesor", urlPatterns = {"/profesor"})
public class Pagina_Profesor extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("id");
            ProfesorBD profesorBD = new ProfesorBD();
            
out.println("<!doctype html>");
out.println("<html class='no-js' lang='en'>");
out.println("<head>");
out.println("  <meta charset='utf-8' />");
out.println("  <meta name='viewport' content='width=device-width, initial-scale=1.0' />");
out.println("  <title>Escuela de Inglés | Bienvenido</title>");
out.println("  <link rel='stylesheet' href='css/foundation.css' />");
out.println("  <link rel='stylesheet' href='css/index.css' />");
out.println("  <link rel='stylesheet' href='css/maestro.css' />");
out.println("  <script src='js/vendor/modernizr.js'></script>");
out.println("  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->");
out.println("  <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js'></script>");
out.println("  <link href='http://fonts.googleapis.com/css?family=Maven+Pro' rel='stylesheet' type='text/css'>");
out.println("  <script src='//cdn.sublimevideo.net/js/x9bc50rn.js' type='text/javascript'></script>");
out.println("</head>");
out.println("<body>");
out.println("  <div id='iniciarModal' class='reveal-modal' data-reveal>");
out.println("    <h2>Inicio de sesión</h2>");
out.println("    <form>");
out.println("      <div class='row'>");
out.println("        <div class='large-4 columns'>");
out.println("          <label>Correo");
out.println("            <input type='text' placeholder='large-4.columns' />");
out.println("          </label>");
out.println("        </div>");
out.println("        <div class='large-4 columns'>");
out.println("          <label>Contraseña");
out.println("            <input type='text' placeholder='large-4.columns' />");
out.println("          </label>");
out.println("        </div>");
out.println("        <div class='large-4 columns'>");
out.println("          <label>Iniciar sesión como:</label>");
out.println("          <input type='radio' name='pokemon' value='Red' id='pokemonRed'><label for='pokemonRed'>Alumno</label>");
out.println("          <input type='radio' name='pokemon' value='Blue' id='pokemonBlue'><label for='pokemonBlue'>Maestro</label>");
out.println("        </div>");
out.println("      </div>");
out.println("      <div class='text-center'>");
out.println("        <a href='#' class='button [tiny small large]'>Entrar</a>");
out.println("      </div>");
out.println("    </form>");
out.println("    <a class='close-reveal-modal'>&#215;</a>");
out.println("  </div>");
out.println("  <div id='myModal' class='reveal-modal' data-reveal>");
out.println("    <h2>Elige el tipo de registro</h2>");
out.println("    <p class='lead'>Puedes elegir entre maestro y alumno</p>");
out.println("    <div class='panel'>");
out.println("      <h5>Aprende</h5>");
out.println("      <p>Selecciona el nivel de curso que quieras tomar con el maestro que te agrade.</p>");
out.println("      <a href='#' class='small button'  data-reveal-id='registroAlumnoModal' data-reveal>Aprender</a>");
out.println("    </div>");
out.println("    <div class='panel'>");
out.println("      <h5>Enseña</h5>");
out.println("      <p>Ofrece cursos eligiendo los horarios que quieras y a alumnos de tu agrado.</p>");
out.println("      <a href='#' class='small button' data-reveal-id='registroMaestroModal' data-reveal>Enseñar</a>");
out.println("    </div>");
out.println("    <a class='close-reveal-modal'>&#215;</a>");
out.println("  </div>");
out.println("");
out.println("  <div id='registroAlumnoModal' class='reveal-modal' data-reveal>");
out.println("    <h2>Registro de Alumno</h2>");
out.println("    <form>");
out.println("      <div class='row'>");
out.println("        <div class='large-12 columns'>");
out.println("          <label>Nombre");
out.println("            <input type='text' placeholder='large-12.columns' />");
out.println("          </label>");
out.println("        </div>");
out.println("      </div>");
out.println("      <div class='row'>");
out.println("        <div class='large-4 columns'>");
out.println("          <label>Correo");
out.println("            <input type='text' placeholder='large-4.columns' />");
out.println("          </label>");
out.println("        </div>");
out.println("        <div class='large-4 columns'>");
out.println("          <label>Constraseña");
out.println("            <input type='text' placeholder='min. 6 caracteres' />");
out.println("          </label>");
out.println("        </div>");
out.println("        <div class='large-4 columns'>");
out.println("          <label>Nivel de Inglés");
out.println("            <select>");
out.println("              <option value='husker'>Principiante</option>");
out.println("              <option value='starbuck'>Intermedio</option>");
out.println("              <option value='hotdog'>Avanzado</option>");
out.println("            </select>");
out.println("          </label>");
out.println("        </div>");
out.println("      </div>");
out.println("      <div class='row'>");
out.println("        <div class='large-12 columns text-center'>");
out.println("          <a href='#' class='button'>Registrar</a>");
out.println("        </div>");
out.println("      </div>");
out.println("    </form>");
out.println("");
out.println("    <a class='close-reveal-modal'>&#215;</a>");
out.println("  </div>");
out.println("  <div id='registroMaestroModal' class='reveal-modal' data-reveal>");
out.println("    <h2>Registro de Maestro</h2>");
out.println("    <form>");
out.println("      <div class='row'>");
out.println("        <div class='large-12 columns'>");
out.println("          <label>Nombre");
out.println("            <input type='text' placeholder='large-12.columns' />");
out.println("          </label>");
out.println("        </div>");
out.println("      </div>");
out.println("      <div class='row'>");
out.println("        <div class='large-6 columns'>");
out.println("          <label>Correo");
out.println("            <input type='text' placeholder='large-4.columns' />");
out.println("          </label>");
out.println("        </div>");
out.println("        <div class='large-6 columns'>");
out.println("          <label>Constraseña");
out.println("            <input type='text' placeholder='min. 6 caracteres' />");
out.println("          </label>");
out.println("        </div>");
out.println("      </div>");
out.println("      <div class='row'>");
out.println("        <div class='large-12 columns text-center'>");
out.println("          <div class='panel'>");
out.println("            <p>Despúes del registro debes subir un video de 1 minuto presentandote y una constancia antes de poder ofrecer cursos.</p>");
out.println("          </div>");
out.println("        </div>");
out.println("      </div>");
out.println("      <div class='row'>");
out.println("        <div class='large-12 columns text-center'>");
out.println("          <a href='#' class='button'>Registrar</a>");
out.println("        </div>");
out.println("      </div>");
out.println("    </form>");
out.println("    <a class='close-reveal-modal'>&#215;</a>");
out.println("  </div>");
out.println("");
out.println("  <div class='row'>");
out.println("    <div class='small-8 large-10 columns'>");
out.println("      <img src='images/logo.png'>");
out.println("    </div>");
out.println("    <div class='small-4 large-2 columns navbar fixed sticky'>");
out.println("      <a href='#' data-dropdown='drop' class='[tiny small medium large] [secondary alert success] [radius round] button dropdown'>Menú</a><br>");
out.println("      <ul id='drop' data-dropdown-content class='f-dropdown'>");
out.println("        <li class='active'><a href='../../index.html'>Inicio</a></li>");
out.println("        <li><a href='#' data-reveal-id='myModal' data-reveal>Registrar</a></li>");
out.println("        <li><a href='../horarios.html'>Ver Horarios</a></li>");
out.println("        <li><a href='../maestros.html'>Ver Maestros</a></li>");
out.println("        <li></br></li>");
out.println("        <li><a href='#' data-reveal-id='iniciarModal' data-reveal>Iniciar Sesión</a></li>");
out.println("        <li><a href='../alumnoConf.html'>Ver Cuenta(Alumno)</a></li>");
out.println("        <li><a href='../maestroConf.html'>Ver Cuenta(Maestro)</a></li>");
out.println("        <li><a href='#'>Salir</a></li>");
out.println("      </ul>");
out.println("    </div>");
out.println("  </div>");
out.println("  <div class='row'>");
out.println("    <div class='small-12 large-12 columns text-center'>");
out.println("      <h1>Pancho el Golpeado López</h1>");
out.println("      <div>");
out.println("        <span class='secondary label'>Cursos finalizados: 0</span>");
out.println("        <span class='secondary label'>Cursos en progreso: 2</span>");
out.println("        <span class='secondary label'>Cursos disponilbes: 4</span>");
out.println("      </div>");
out.println("<video id='a240e92d' class='sublime' poster='https://cdn.sublimevideo.net/vpa/ms_800.jpg' width='640' height='360' title='Midnight Sun' data-uid='a240e92d' data-autoresize='fit' preload='none'>");
out.println("  <source src='https://cdn.sublimevideo.net/vpa/ms_360p.mp4' />");
out.println("  <source src='https://cdn.sublimevideo.net/vpa/ms_720p.mp4' data-quality='hd' />");
out.println("  <source src='https://cdn.sublimevideo.net/vpa/ms_360p.webm' />");
out.println("  <source src='https://cdn.sublimevideo.net/vpa/ms_720p.webm' data-quality='hd' />");
out.println("</video>");
out.println("    </div>");
out.println("  </div>");
out.println("  <div class='row'>");
out.println("    <div class='small-12 large-12 columns text-center'>");
out.println("      <span class='primary label'>Cursos disponibles</span>");
out.println("      <table max-width='600'>");
out.println("        <thead>");
out.println("          <tr>");
out.println("            <th class='text-center' width='40%'>Tipo</th>");
out.println("            <th class='text-center' width='40%'>Horarios</th>");
out.println("            <th class='text-center' width='20%'>Solicitar</th>");
out.println("          </tr>");
out.println("        </thead>");
out.println("        <tbody>");
out.println("          <tr>");
out.println("            <td>Principiante</td>");
out.println("            <td>10:30 - 11:40</td>");
out.println("            <td><a href='#' class='button success radius tiny'>Solicitar</a></td>");
out.println("          </tr>");
out.println("          <tr>");
out.println("            <td>Intermedio</td>");
out.println("            <td>13:00 - 14:15</td>");
out.println("            <td><a href='#' class='button success radius tiny'>Solicitar</a></td>");
out.println("          </tr>");
out.println("          <tr>");
out.println("            <td>Avanzado</td>");
out.println("            <td>17:00 - 20:00</td>");
out.println("            <td><a href='#' class='button success radius tiny'>Solicitar</a></td>");
out.println("          </tr>");
out.println("          <tr>");
out.println("            <td>Conversación</td>");
out.println("            <td>15:00 - 15:15</td>");
out.println("            <td><a href='#' class='button success radius tiny'>Solicitar</a></td>");
out.println("          </tr>");
out.println("        </tbody>");
out.println("      </table>");
out.println("    </div>");
out.println("  </div>");
out.println("  <div class='row'>");
out.println("    <div class='small-12 large-12 columns text-center'>");
out.println("      <div class='panel radius'>");
out.println("        Si te interesa puedes descargas la constancia de acreditación del maestro: <a href='#'>constacia.pdf</a>");
out.println("      </div>");
out.println("    </div>");
out.println("  </div>");
out.println("");
out.println("  <script src='../../js/vendor/jquery.js'></script>");
out.println("  <script src='../../js/foundation.min.js'></script>");
out.println("  <script>");
out.println("    $(document).foundation();");
out.println("  </script>");
out.println("</body>");
out.println("</html>");

            
            //out.println("<!DOCTYPE html>");
            //out.println("<html>");
            //out.println("<head>");
            //out.println("<title>Servlet Pagina_Profesor</title>");            
            //out.println("</head>");
            //out.println("<body>");
            //out.println("<h1>Servlet Pagina_Profesor at " + request.getContextPath() + "</h1>");
            //out.println("</body>");
            //out.println("</html>");
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
