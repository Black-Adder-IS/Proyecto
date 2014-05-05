/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$( document ).ready(function() {
    var genera_menu = function (profundidad) {
        //folder /
        var f_index = "";
        //folder /html
        var f_html = "";
        var menu = $('#menu-ul');//.html('<li class="arrow unavailable"><a href="#">&laquo;</a></li>');
        menu.html('');
        f_index = profundidad === 0 ? "":"../";
        f_html = profundidad === 0 ? "html/":"";
        
        menu.append('<li><a class="button alert tiny menu_button" href="' + f_index + 'index.html">Inicio</a></li>');
        menu.append('<li><a class="button alert tiny menu_button" href="'+f_html+'horarios.html">Ver Horarios</a></li>');
        menu.append('<li><a class="button alert tiny menu_button" href="'+f_html+'profesores.html">Ver Profesores</a></li>');
        if(!localStorage.tipo || localStorage.tipo === ""){
            menu.append('<li><a class="button alert tiny menu_button" href="#" data-reveal-id="iniciarModal" data-reveal>Iniciar Sesión</a></li>');
            //menu.append('<li><a href="#" data-reveal-id="myModal" data-reveal>Registrar</a></li>');
        }else{
          if(localStorage.tipo === "profesor"){
            menu.append('<li><a class="button alert tiny menu_button" href="'+f_index+'profesorConf.html">Ver Cuenta(Profesor)</a></li>');
          }
          if(localStorage.tipo === "estudiante"){
            menu.append('<li><a class="button alert tiny menu_button" href="'+f_index+'estudianteConf.html">Ver Cuenta(Estudiante)</a></li>');
          }
          menu.append('<li><a class="button alert tiny menu_button" id="cerrar_sesion_btn" href="#">Salir</a></li>');
        }
        
        
    };
    genera_menu(1);
 });