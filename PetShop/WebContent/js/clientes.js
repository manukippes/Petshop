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
					$("<small class='form-text text-muted text-danger'>Debe completar el campo: Tel&eacute;fono</small>").insertAfter("#telefono");
				}
		}
		else{
			$("#apellidoGroup").addClass("has-error");
			$("#apellido").focus();
			$("<small class='form-text text-muted text-danger'>Debe completar el campo: Apellido</small>").insertAfter("#apellido");
		}
	}
	else{
		$("#nombreGroup").addClass("has-error");
		$("#nombre").focus();
		$("<small class='form-text text-muted text-danger'>Debe completar el campo: Nombre</small>").insertAfter("#nombre");
	}
} 
	
		
	
$(document).ready(function() {
	$('#btnAgregarCliente').click(function(e){
		e.preventDefault();
		var resultado = validar();
		if(resultado){
			var data = new FormData($('#form_nuevo_cliente')[0]);
			$.ajax({
				url : "ConfirmarAltaCliente",
				type : "post",
				data : data,
				contentType : false,
				processData : false,
				success : function(data){
					alert(data);
				}
			})
		}
	})
})