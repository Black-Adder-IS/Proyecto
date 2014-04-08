/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$( document ).ready(function() {
    $( "#iniciar_sesion_btn" ).click(function( event ) {
        var id=$('#id_text').val();
        var contrasena=$('#contrasena_text').val();
        if ($("#profesor_radio").prop("checked")) {
            console.log('Entrando a post profesor, correo: ' + id + " contraseña: " + contrasena);
            $.post('Profesor',{tipo:0,id:id,contrasena:contrasena},function(responseText) { 
                console.log('Respuesta de post' + responseText);
                if (responseText.trim() === "true"){
                    $('#mensaje').text("Bienvenido profesor " + id + " a la página ");
                    localStorage.setItem('tipo','profesor');
                    localStorage.setItem('id',id);
                } else if(responseText.trim() === "false"){
                    $('#mensaje').text("Su datos no son correctos ");
                } else {
                    $('#mensaje').text("Hubo un error el servidor");
                }
            });
        } else {
            console.log('Entrando a post estudiante, correo: ' + id + " contraseña: " + contrasena);
            $.post('Estudiante',{tipo:0,id:id,contrasena:contrasena},function(responseText) { 
                console.log('Respuesta de post' + responseText);
                if (responseText.trim() == "true"){
                    $('#mensaje').text("Bienvenido estudiante " + id + " a la página ");
                    localStorage.setItem('tipo','estudiante');
                    localStorage.setItem('id','id');
                } else if(responseText.trim() == "false"){
                    $('#mensaje').text("Su datos no son correctos ");
                } else {
                    $('#mensaje').text("Hubo un error el servidor");
                }
            });
        }
    });
    $( "#cerrar_sesion_btn" ).click(function( event ) {
        $('#mensaje').text("Sesión Cerrada ");
        localStorage.removeItem("tipo");
        localStorage.removeItem("id");
    });
});