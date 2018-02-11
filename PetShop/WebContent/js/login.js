/**
 * 
 */


$(document).ready(function() {
	
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
					
					
					//confirm("Email enviado Exitosamente");
				}else{
					$('#confirmacionEnvio').removeClass("alert-success");
					$('#confirmacionEnvio').addClass("alert-danger");
					$('#confirmacionEnvio').removeClass("hidden");
					$('#confirmacionEnvio').empty();
					$('#confirmacionEnvio').append("<span class='fa fa-exclamation' style='font-size:20px'></span> No se pudo enviar el correo, la direcci&oacute;n ingresada no corresponde a un usuario");
					//alert("Error al enviar el Email");
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