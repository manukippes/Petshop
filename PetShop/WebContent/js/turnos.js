/**
 * 
 */


$(document).ready(function() {
		
	$('#btnPatitaGrande').click(function(e){
		e.preventDefault();
		
		$('#btnPatitaGrande').addClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		
	})

	$('#btnPatitaMediana').click(function(e){
		e.preventDefault();
		
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').addClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		
	})
	
	$('#btnPatitaChica').click(function(e){
		e.preventDefault();
		
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').addClass("icon-button-active");
		
	})

	$('#btnTijeraGrande').click(function(e){
		e.preventDefault();
		
		$('#btnTijeraGrande').addClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active");

		
	})
	
	$('#btnTijeraChica').click(function(e){
		e.preventDefault();
		
		$('#btnTijeraGrande').removeClass("icon-button-active");
		$('#btnTijeraChica').addClass("icon-button-active");

	})
	
	$("#tableUsuario tr td").on('DOMSubtreeModified',function(){

		var idUsuario = $('#idUsuario').val();
		var parametro = {idUsuario : idUsuario };
		if(idUsuario!=""){
			//Cargar el combo de mascotas
			
			$.post("ComboMascota",$.param(parametro),function(responseJson){
				$('#mascota').empty();
				$('#mascota').append($('<option value="mascota">Seleccion&aacute; una mascota</option>'));
				var bandera = false;
				
				$.each(responseJson,function(index, mascota){
					$('#mascota').append($('<option value="'+mascota.idMascota+'">'+mascota.nombre+'</option>'));
					bandera = true;					
				});
				
				if(!bandera){
					$('#mascota').empty();
					$('#mascota').append($('<option value="mascota">El cliente no tiene mascotas registradas</option>'));
					$('#mascota').prop("disabled",true);
				}
				else{
					$('#mascota').prop("disabled",false);
				}
			})
		}
		else
		{
			//Descargar el combo de mascotas 
			$('#mascota').empty();
			$('#mascota').append($('<option value="mascota">Seleccion&aacute; una mascota</option>'));
			$('#mascota').prop("disabled",true);
		}		
	})	
	
	$('#btnContinuar').click(function(e){
		e.preventDefault();
		
		var tamanio = false;
		if ($('#btnPatitaGrande').hasClass("icon-button-active")||$('#btnPatitaMediana').hasClass("icon-button-active")||$('#btnPatitaChica').hasClass("icon-button-active")){
			tamanio = true;
		}
		var pelaje = false;
		if($('#btnTijeraChica').hasClass("icon-button-active")||$('#btnTijeraGrande').hasClass("icon-button-active")){
			pelaje=true;
		}
		var servicio=false;
		if($('#servicio').val()!="servicio"){
			servicio=true;
		}
		
		if (tamanio){
			if(pelaje){
				if(servicio){
					
					confirm("Todo ok");
					
				}else{alert("Debes seleccionar un tipo de servicio")}
			}else{alert("Debes seleccionar un largo del pelaje")}
		}else{alert("Debes seleccionar un tama&ntilde;o de mascota")}
	})
	
})