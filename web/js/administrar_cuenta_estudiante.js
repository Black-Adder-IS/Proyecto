 var correoVar;
 if(typeof(Storage)!=="undefined") {
    correoVar = localStorage.getItem("id");
} else {}

$(document).ready(function() {
    $.post('Estudiante?operacion=obtener_Cursos_Actuales', {
            correo_Estudiante : correoVar
        }, function (respuesta) {
            $('#cursos_Actuales').html(respuesta);
    });
    
    $.post('Estudiante?operacion=obtener_Cursos_Finalizados', {
            correo_Estudiante : correoVar
        }, function (respuesta) {
            $('#cursos_Finalizados').html(respuesta);
    });
    
    $('#subir').click(function(event) {
        var nombreVar = $('#nombre_Estudiante').val();
        var correoVar1 = $('#correo_Estudiante').val();
        var contraseniaVar = $('#contrasenia_Estudiante').val();
                
        $.post('Estudiante?operacion=editar_Estudiante', {
            correo_Estudiante : correoVar,
            nuevo_nombre_Estudiante : nombreVar,
            nuevo_correo_Estudiante : correoVar1,
            nuevo_contrasenia_Estudiante : contraseniaVar
        }, function (respuesta) {
            if (parseInt(respuesta) === 2) {                
                if (correoVar1.trim() == "") {
                    
                } else {
                    localStorage.setItem("id", correoVar1);
                    location.href = "estudianteConf.html";
                }
                $('#mensaje').text("Datos modificados");
            } else if (confirmacion === 3) {
                $('#mensaje').text("No se pueden modificar los datos, intenta con otro correo");
            } else {
                $('#mensaje').text("Hubo un problema al editar la información. Intentalo de nuevo.");
            }
        });
    });
                    
    $('#borrar').click(function(event) {
        $.post('Estudiante?operacion=eliminar_Estudiante', {
            correo_Estudiante : correoVar
            }, function(respuesta) {
            var confirmacion = parseInt(respuesta);

            if (confirmacion === 4) {
                localStorage.removeItem("id");
                localStorage.removeItem("tipo");
                $('#mensaje').text("Lo sentimos, esperamos que regreses.");
                location.href="index.html";
            } else if(confirmacion === 5) {
                $('#mensaje').text("No se ha podido borrar la cuenta");
            } else {
                $('#mensaje').text("Hubo un problema al borrar la cuenta. Intentalo después.");
            }
        });
    });
});