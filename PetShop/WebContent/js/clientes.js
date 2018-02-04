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
	
	if(isNaN($('#dni').val())){
		alert("Debe ingresar s&oacute;lo n&uacute;meros en el campo Telefono.");		
	}else if(isNaN($('#telefono').val())){
		alert("Debe ingresar s&oacute;lo n&uacute;meros en el campo Dni.");
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
	if (($("tablaMascota").hasClass('hidden'))) {
		
			//PARA QUE QUEDE SELECCIONADO UN TAMANIO Y UN PELAJE////
			('#btnPatitaGrande').click(function(e){
				e.preventDefault();
				$('#patitaGroup').removeClass("con-error");
				$('#btnPatitaGrande').addClass("icon-button-active");
				$('#btnPatitaMediana').removeClass("icon-button-active");
				$('#btnPatitaChica').removeClass("icon-button-active");
				
			})
			
			$('#btnPatitaMediana').click(function(e){
				e.preventDefault();
				$('#patitaGroup').removeClass("con-error");
				$('#btnPatitaGrande').removeClass("icon-button-active");
				$('#btnPatitaMediana').addClass("icon-button-active");
				$('#btnPatitaChica').removeClass("icon-button-active");
				
			})
			
			$('#btnPatitaChica').click(function(e){
				e.preventDefault();
				$('#patitaGroup').removeClass("con-error");
				$('#btnPatitaGrande').removeClass("icon-button-active");
				$('#btnPatitaMediana').removeClass("icon-button-active");
				$('#btnPatitaChica').addClass("icon-button-active");
				
			})
			
			$('#btnTijeraGrande').click(function(e){
				e.preventDefault();
				$('#pelajeGroup').removeClass("con-error");
				$('#btnTijeraGrande').addClass("icon-button-active");
				$('#btnTijeraChica').removeClass("icon-button-active");
			
				
			})
			
			$('#btnTijeraChica').click(function(e){
				e.preventDefault();
				$('#pelajeGroup').removeClass("con-error");
				$('#btnTijeraGrande').removeClass("icon-button-active");
				$('#btnTijeraChica').addClass("icon-button-active");
			
			})
	}
	
	/// ALTA DE CLIENTE ///
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
					dataType: 'json',
					success : function(data){
						if (data)
						{
							confirm("Se agreg&oacute; el cliente correctamente.");
						} 
						else
						{
							alert("No se pudo agregar el cliente.");
						}
	                    
					}
			})
		}
	})
	
	
	/// AGREGAR MASCOTAS ///
	$(this).on("click", "#btnAgregarMascotaModal", function(e){
	    e.preventDefault();
	    if(false){
			alert("Para agregar primero debes ingresar un nombre.");
		}else if(false){
			alert("Para agregar primero debes seleccionar un tamaño de la mascota");
		}else if(false){
			alert("Para agregar primero debes seleccionar un pelaje de la mascota");
		}else{
			$('#agregarMascota').modal('toggle');
	        $('#tablaMascota').removeClass("hidden");
	        
	        var idCliente = $('#cliente').val();
	        
	        parametro = {idCliente : idCliente};
	        $.post("ObtenerCliente",$.param(parametro),function(responseJson){
				$.each(responseJson,function(index, cliente){
					$('#idUsuario').val(cliente.idUsuario); 			
			        $('#nombreApellidoCliente').text(cliente.nombre+", "+cliente.apellido);
			        $('#telefonoCliente').text(cliente.telefono);
			        $('#direccionCliente').text(cliente.direccion);
				});
			});	   
		}
	});
	
})