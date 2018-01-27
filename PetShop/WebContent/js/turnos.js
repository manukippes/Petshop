/**
 * 
 */


$(document).ready(function() {
		
	$('#btnPatitaGrande').click(function(e){
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
	
	$("#servicio").change(function(){

		$('#servicioGroup').removeClass("has-error");
	})
	
	$("#horario").change(function(){

		$('#horarioGroup').removeClass("has-error");
	})
	$("#mascota").change(function(){

		$('#mascotaGroup').removeClass("has-error");
	})
	//CAPTURO el dia que se ingreso
	$("#fechaSeleccionada").change(function(){
		
		$('#fechaGroup').removeClass("has-error");
		var fechaSeleccionada = $('#fechaSeleccionada').val();
		if (fechaSeleccionada!=""){
			$('#horario').prop('disabled',false);
			var parametro = {fechaSeleccionada : fechaSeleccionada};
			$.post("ComboHorarios",$.param(parametro),function(responseJson){
				$('#horario').empty();
				$('#horario').append($('<option value="horario">Seleccion&aacute; un horario</option>'));
				$.each(responseJson,function(index,horarios){
					//recuperos los horarios
					$('#horario').append($('<option value="'+horarios+'">'+horarios+'</option>'));
				});
			});	
		}else{
			$('#horario').prop('disabled',true);
		}
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
		var fecha =false;
		if($('#fechaSeleccionada').val()!=""){
			fecha=true;
		}
		var horario =false;
		if($('#horario').val()!="horario"){
			horario=true;
		}
		var cliente=false;
		if($('#idUsuario').val()!=""){
			cliente=true;
		}
		var mascota = false;
		if($('#mascota').val()!="mascota"){
			mascota = true;
		}
		
		var resultado = false;
		if (tamanio){
			if(pelaje){
				if(servicio){
					if(fecha){
						if(horario){
							if(cliente){
								if(mascota){
									confirm("Todo ok");	
							
								}else{alert("Debes seleccionar una mascota del cliente");
								$('#mascotaGroup').addClass("has-error");}
						
							}else{alert("Debes seleccionar un cliente registrado");}
					
						}else{alert("Debes seleccionar un horario disponible");
								$('#horarioGroup').addClass("has-error");}
						
					}else{alert("Debes seleccionar una fecha para el turno");
							$('#fechaGroup').addClass("has-error");}
					
				}else{alert("Debes seleccionar un tipo de servicio");
						$('#servicioGroup').addClass("has-error");}
				
			}else{alert("Debes seleccionar un largo del pelaje")
					$('#pelajeGroup').addClass("con-error");}
			
		}else{alert("Debes seleccionar un tama&ntilde;o de mascota");
				$("#patitaGroup").addClass("con-error")}
	})
	
})