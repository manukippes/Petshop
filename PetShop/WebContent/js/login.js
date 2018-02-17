/**
 * 
 */


$(document).ready(function() {
	
	$('#btnIngresar').click(function(e){
		e.preventDefault();
		var username = $('#username').val();
		var password = $('#password').val();
		
		var parametro = {username : username,
						password : password
						};	
		
		$.post("Start",$.param(parametro),function(respuesta){
			
			$('#mensaje').remove();
			if (respuesta==1){
				$(location).attr('href','Ventas');

			}else{
				if (respuesta==2){
					$(location).attr('href','VentaOnline');
				}else{
					if(respuesta==3){
						$("<div class='alert alert-danger' id='mensaje'>Usuario o contrase&ntilde;a incorrectos <button class='close' data-dismiss='alert'><span>&times;</span></button></div>").insertAfter("#mensajeError");
						
					}else{
						if(respuesta==4){
							$("<div class='alert alert-danger' id='mensaje'>Usuario deshabilitado, por favor contact&aacute; a un administrador <button class='close' data-dismiss='alert'><span>&times;</span></button></div>").insertAfter("#mensajeError");
						}
					}
				}
			}
								
		});
		
	})
	
	$('#btnEnviarEmail').click(function(e){
		e.preventDefault();
		
		$('#confirmacionEnvio').addClass("hidden");
		var validaMail = false;
		var email = $('#inputEmail').val();
		
		emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	    
	    if (emailRegex.test(email)) {
	    	validaMail=true;
	    } else {
	    	validaMail = false;
	    }
			
		
		if(validaMail){
			//el mail es correcto
			$('#barraProgreso').removeClass("hidden");
			
			
			var parametro = {email : email};	
			
			$.post("BlanquearUsuario",$.param(parametro),function(respuesta){
				$('#barraProgreso').addClass("hidden");
				if (respuesta){
					
					$('#confirmacionEnvio').removeClass("alert-danger");
					$('#confirmacionEnvio').addClass("alert-success");
					$('#confirmacionEnvio').removeClass("hidden");
					$('#confirmacionEnvio').empty();
					$('#confirmacionEnvio').append("<span class='fa fa-check'></span> Env&iacute;o confirmado, por favor verific&aacute; tu casilla");
					
				}else{
					$('#confirmacionEnvio').removeClass("alert-success");
					$('#confirmacionEnvio').addClass("alert-danger");
					$('#confirmacionEnvio').removeClass("hidden");
					$('#confirmacionEnvio').empty();
					$('#confirmacionEnvio').append("<span class='fa fa-exclamation' style='font-size:20px'></span> No se pudo enviar el correo, la direcci&oacute;n ingresada no corresponde a un usuario");
					
				}
			});
						
		} else {
			$('#emailValido').removeClass("hidden");
			$('#inputEmailGroup').addClass("has-error");
		}
	})
	
	$('#inputEmail').click(function(e){
		$('#emailValido').addClass("hidden");
		$('#inputEmailGroup').removeClass("has-error");
	})
	
})