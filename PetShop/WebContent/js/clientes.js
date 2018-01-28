/**
 * 
 */

function validar(){
	var nombre, apellido, telefono;
	
	nombre = false;
	if ($("#nombre").val() != "") {
		nombre = true;
	}
	
	apellido = false;
	if ($("#apellido").val() != "") {
		apellido = true;
	}
	
	telefono = false;
	if ($("#telefono").val() != "") {
		telefono = true;
	}
		
	if (nombre) {
		if (apellido) {
				if (telefono) {
					return true;
				}
				else{
					$("#telefonoGroup").addClass("has-error");
					$("#telefono").focus();
					if(!($("#completarTelefono").length)){
						$("<small class='form-text text-muted text-danger' id='completarTelefono'>Debe completar el campo: Tel&eacute;fono</small>").insertAfter("#telefono");
					}
				}
		}
		else{
			$("#apellidoGroup").addClass("has-error");
			$("#apellido").focus();
			if(!($("#completarApellido").length)){
				$("<small class='form-text text-muted text-danger' id='completarApellido'>Debe completar el campo: Apellido</small>").insertAfter("#apellido");
		    }
		}
	}
	else{
		$("#nombreGroup").addClass("has-error");
		$("#nombre").focus();
		if(!($("#completarNombre").length)){
			$("<small class='form-text text-muted text-danger' id='completarNombre'>Debe completar el campo: Nombre</small>").insertAfter("#nombre");
		}
	}
	
	$("#nombre").change(function(){

		$('#nombreGroup').removeClass("has-error");
		$("#completarNombre").remove();
		
	})

	$("#apellido").change(function(){

		$('#apellidoGroup').removeClass("has-error");
		$("#completarApellido").remove();
		
	})
	
	$("#telefono").change(function(){

		$('#telefonoGroup').removeClass("has-error");
		$("#completarTelefono").remove();
		
	})
	
} 
	
		
	
$(document).ready(function() {
	$('#btnAgregarCliente').click(function(e){
		e.preventDefault();
		var resultado = validar();
		if(resultado){
			var parametro = {
					nombre : $("#nombre").val(),
					apellido : $("#apellido").val(),
					dni : $("#dni").val(),
					direccion : $("#direccion").val(),
					telefono : $("#telefono").val(),
					email : $("#email").val(),
			}
			var parametros = JSON.stringify(parametro);
			
			$.ajax({
				url : "ConfirmarAltaCliente",
				type : "post",
				data : {jsonData : parametros},
				success : function(data){
					alert(data);
				}
			})
		}
	})
})