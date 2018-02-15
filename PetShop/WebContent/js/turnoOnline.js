/**
 * 
 *//**
 * 
 */
function validarDatosTurno(){
	
	var resultado = false;
	
	
	var validaServicio=false;
	if($('#servicio').val()!="servicio"){
		validaServicio=true;
	}
	
	var validaFecha =false;
	if($('#fechaSeleccionada').val()!=""){
		validaFecha=true;
	}
	var validaHorario =false;
	if($('#horario').val()!="horario"){
		validaHorario=true;
	}
	
	var validaRepetir = false;
	if($('#repetir').is(':checked')){	
		var opcionElegida=$('input[name=opcion]:checked').val();
		if (opcionElegida == "Semanal" || opcionElegida == "Quincenal" || opcionElegida == "Mensual" ){
			validaRepetir=true;
		};
	}else{
		validaRepetir=true;
	}
		
	var validaMascota = false;
	if($('#mascota').val()!="mascota"){
		validaMascota = true;
	}
	
	
	if(validaMascota){
		if(validaServicio){
			if(validaFecha){
				if(validaHorario){
					if(validaRepetir){
						
								resultado = true;
											
						
				
					}else{alert("Debes seleccionar una frecuencia de repeticion");
							$('#repetirRadioGroup').addClass("con-error");}
			
				}else{alert("Debes seleccionar un horario disponible");
						$('#horarioGroup').addClass("has-error");}
				
			}else{alert("Debes seleccionar una fecha para el turno");
					$('#fechaGroup').addClass("has-error");}
			
		}else{alert("Debes seleccionar un tipo de servicio");
				$('#servicioGroup').addClass("has-error");}
	}else{alert("Debes seleccionar una de tus mascotas");
	$('#mascotaGroup').addClass("has-error");}
		
return resultado;
}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





$(document).ready(function() {
		
		
	$("#servicio").change(function(){

		$('#servicioGroup').removeClass("has-error");
	})
	
	$("#horario").change(function(){

		$('#horarioGroup').removeClass("has-error");
	})
	
	$("#repetir").click(function(){
		if($('#repetir').is(':checked')){
			$('#repetirRadioGroup').removeClass("hidden");
		}else{$('#repetirRadioGroup').addClass("hidden");}
	})
	
	$(".rbutton").click(function(){
		$('#repetirRadioGroup').removeClass("con-error");		
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
	
	
	$('#btnContinuarAlta').click(function(e){
		e.preventDefault();
		var servicio="";
		var fecha="";
		var horario ="";
		var idUsuario=0;
		var idMascota=0;
		var conRetiro=false;
		var repeticion="No";
		var resultado = false;
		
		var validaServicio=false;
		if($('#servicio').val()!="servicio"){
			validaServicio=true;
			servicio=$('#servicio').val();
		}
		
		conRetiro = $('#conRetiro').is(':checked');
		
		var validaFecha =false;
		if($('#fechaSeleccionada').val()!=""){
			validaFecha=true;
			fecha = $('#fechaSeleccionada').val();
		}
		var validaHorario =false;
		if($('#horario').val()!="horario"){
			validaHorario=true;
			horario = $('#horario').val();
		}
		
		var validaRepetir = false;
		if($('#repetir').is(':checked')){	
			var opcionElegida=$('input[name=opcion]:checked').val();
			if (opcionElegida == "Semanal" || opcionElegida == "Quincenal" || opcionElegida == "Mensual" ){
				validaRepetir=true;
				repeticion = $('input[name=opcion]:checked').val();
			};
		}else{
			validaRepetir=true;
		}
		
		var validaMascota = false;
		if($('#mascota').val()!="mascota"){
			validaMascota = true;
			idMascota = $('#mascota').val();
		}
		var observaciones = $('#observaciones').val();
		
		var resultado = validarDatosTurno();
		if(resultado){
					//////////////////////PROCESAR TURNO///////////////////////////////
				
			var parametro = {
					servicio : servicio,
					fecha : fecha,
					horario : horario,
					repeticion : repeticion,
					idUsuario : idUsuario,
					idMascota : idMascota,
					conRetiro : conRetiro,
					observaciones : observaciones
					}
			var parametros = JSON.stringify(parametro);
			//alert(parametros);
			$.ajax({
				type : "post",
				url : "CargarDatosTurnoOnline",
				data : {jsonData : parametros},
				success : function(respuesta){
					//alert(respuesta);
					$(location).attr('href',"TurnoOnlinePaso2");
					
				}
			}); 
		
		}
	})
	
	$('#btnConfirmarTurno').click(function(e){
		e.preventDefault();

		//alert(parametros);
		$.ajax({
			type : "post",
			url : "ProcesarTurnoOnline",
			success : function(respuesta){
				//alert(respuesta);		//NO DETIENE LA EJECUCION POR LO QUE NO SE MUESTRA
				if (respuesta){
					if(prompt("Turno creado Exitosamente")==""){
						$(location).attr('href','Turnos');
					}
				}else{
					alert("Error al cargar el turno");
				}
			}
		}); 
	})
	
	
})