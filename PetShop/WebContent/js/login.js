/**
 * 
 */


$(document).ready(function() {
	
	$('#btnEnviarEmail').click(function(e){
		e.preventDefault();
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
			
			
			
			var parametro = {email : email};	
			
			$.post("BlanquearUsuario",$.param(parametro),function(respuesta){
				
				if (respuesta){
					confirm("Email enviado Exitosamente");
				}else{
					alert("Error al enviar el Email");
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