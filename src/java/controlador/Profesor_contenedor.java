/*
 * Estructura que guarda los datos de un profesor
 */

package controlador;

import java.util.ArrayList;

/**
 *
 * @author Marco Aurelio Nila Fonseca
 * @version 1.0
 */
public class Profesor_contenedor {
    public String nombre, correo, video_url, certificado_url;
    public int id, cursos_terminado, cursos_cursando, cursos_espera;
    public ArrayList<String> cursos;
}
