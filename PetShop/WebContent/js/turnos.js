/**
 * 
 */
function validarDatosTurno(){
	
	var resultado = false;
	
	var validaTamanio = false;
	if ($('#btnPatitaGrande').hasClass("icon-button-active")||$('#btnPatitaMediana').hasClass("icon-button-active")||$('#btnPatitaChica').hasClass("icon-button-active")){
		validaTamanio = true;
	}
	
	var validaPelaje = false;
	if($('#btnTijeraChica').hasClass("icon-button-active")||$('#btnTijeraGrande').hasClass("icon-button-active")){
		validaPelaje=true;
	}
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
	
	var validaCliente=false;
	if($('#idUsuario').val()!=""){
		validaCliente=true;

	}else {
		if($('#idUsuario').data("value")!=""){
			validaCliente=true;
		}
	} //	nunca da false

	
	var validaMascota = false;
	if($('#mascota').val()!="mascota"){
		validaMascota = true;
	}
	
	if (validaTamanio){
		if(validaPelaje){
			if(validaServicio){
				if(validaFecha){
					if(validaHorario){
						if(validaRepetir){
							if(validaCliente){
								if(validaMascota){
									resultado = true;
												
								}else{alertError("Por favor revis\u00e1 los datos ingresados");
									$("<small class='form-text text-danger' id='completarmascota'>Debes seleccionar una de mascota del cliente</small>").insertAfter("#mascota");
									$('#mascotaGroup').addClass("has-error");}
						
							}else{alertError("Debes seleccionar un cliente registrado");
							$("<small class='form-text text-danger' id='completarcliente'>Debes seleccionar un cliente</small>").insertAfter("#btnAgregarUnCliente");}
					
						}else{alertError("Debes seleccionar una frecuencia de repetici\u00f3n");
						$("<small class='form-text text-danger' id='completarfrecuencia'>Debes seleccionar una frecuencia de repeticion</small>").insertAfter("#repetirRadioGroup");
								$('#repetirRadioGroup').addClass("con-error");}
				
					}else{alertError("Por favor revis\u00e1 los datos ingresados");
							$("<small class='form-text text-danger' id='completarhorario'>Debes seleccionar un horario disponible</small>").insertAfter("#horario");
							$('#horarioGroup').addClass("has-error");}
					
				}else{alertError("Por favor revis\u00e1 los datos ingresados");
						$("<small class='form-text text-danger' id='completarfecha'>Debes seleccionar una fecha para el turno</small>").insertAfter("#fechaSeleccionada");
						$('#fechaGroup').addClass("has-error");}
				
			}else{alertError("Por favor revis\u00e1 los datos ingresados");
					$("<small class='form-text text-danger' id='completarservicio'>Debes seleccionar un tipo de servicio</small>").insertAfter("#servicio");
					$('#servicioGroup').addClass("has-error");}
			
		}else{alertError("Por favor revis\u00e1 los datos ingresados");
			$("<small class='form-text text-danger' id='completarpelaje'>Debes seleccionar un largo del pelaje</small>").insertAfter("#pelajeGroup");
			$('#pelajeGroup').addClass("con-error");}
		
	}else{alertError("Por favor revis\u00e1 los datos ingresados");
		$("<small class='form-text text-danger' id='completartamanio'>Debes seleccionar un tama\u00f1o de mascota</small>").insertAfter("#patitaGroup");
		$("#patitaGroup").addClass("con-error")}
return resultado;
}

function cargarUsuario(usuario){
	$('#idUsuario').val(cliente.idUsuario); 			
    $('#nombreApellidoCliente').text(cliente.nombre+", "+cliente.apellido);
    $('#telefonoCliente').text(cliente.telefono);
    $('#direccionCliente').text(cliente.direccion);
}

$(document).ready(function() {
		
	$('#btnAgregarUnCliente').click(function(e){
		$('#completarcliente').remove();
	})
	
	$('#btnPatitaGrande').click(function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#completartamanio').remove();
		$('#btnPatitaGrande').addClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
	})

	$('#btnPatitaMediana').click(function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#completartamanio').remove();
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').addClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
	})
	
	$('#btnPatitaChica').click(function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#completartamanio').remove();
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').addClass("icon-button-active");
	})

	$('#btnTijeraGrande').click(function(e){
		e.preventDefault();
		$('#pelajeGroup').removeClass("con-error");
		$('#completarpelaje').remove();
		$('#btnTijeraGrande').addClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active");		
	})
	
	$('#btnTijeraChica').click(function(e){
		e.preventDefault();
		$('#pelajeGroup').removeClass("con-error");
		$('#completarpelaje').remove();
		$('#btnTijeraGrande').removeClass("icon-button-active");
		$('#btnTijeraChica').addClass("icon-button-active");

	})
	
	$("#servicio").change(function(){
		$('#completarservicio').remove();
		$('#servicioGroup').removeClass("has-error");
	})
	
	$("#horario").change(function(){
		$('#completarhorario').remove();
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
		$('#completarmascota').remove();
		$('#mascotaGroup').removeClass("has-error");
	})
	//CAPTURO el dia que se ingreso
	$("#fechaSeleccionada").change(function(){
		$('#completarfecha').remove();
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
					$('#completarmascota').remove();
					$('#mascotaGroup').removeClass("has-error");
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
	
	$('#btnContinuarAlta').click(function(e){
		e.preventDefault();
		var tamanio="";
		var pelaje="";
		var servicio="";
		var fecha="";
		var horario ="";
		var idUsuario=0;
		var idMascota=0;
		var conRetiro=false;
		var repeticion="No";
		var resultado = false;
		
		var validaTamanio = false;
		if ($('#btnPatitaGrande').hasClass("icon-button-active")||$('#btnPatitaMediana').hasClass("icon-button-active")||$('#btnPatitaChica').hasClass("icon-button-active")){
			validaTamanio = true;
			if($('#btnPatitaGrande').hasClass("icon-button-active")){
				tamanio="Grande";
			}else{
				if($('#btnPatitaMediana').hasClass("icon-button-active")){
					tamanio="Mediano";
				}else{
					tamanio="Chico";
				}
			}
		}
		
		var validaPelaje = false;
		if($('#btnTijeraChica').hasClass("icon-button-active")||$('#btnTijeraGrande').hasClass("icon-button-active")){
			validaPelaje=true;
			if($('#btnTijeraGrande').hasClass("icon-button-active")){
				pelaje ="Largo";
			}else{
				pelaje ="Corto";
			}
		}
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
		
		var validaCliente=false;
		if($('#idUsuario').val()!=""){
			validaCliente=true;
			idUsuario = $('#idUsuario').val();
		}else {
			if($('#idUsuario').data("value")!=""){
				validaCliente=true;
				idUsuario = $('#idUsuario').data("value");
			}
		}
		
		var validaMascota = false;
		if($('#mascota').val()!="mascota"){
			validaMascota = true;
			idMascota = $('#mascota').val();
		}
		
	var resultado = validarDatosTurno();
	if(resultado){
				//////////////////////PROCESAR TURNO///////////////////////////////
		var proceso = "alta";
	
		var parametro = {
				proceso : proceso,
				tamanio : tamanio,
				pelaje : pelaje,
				servicio : servicio,
				fecha : fecha,
				horario : horario,
				repeticion : repeticion,
				idUsuario : idUsuario,
				idMascota : idMascota,
				conRetiro : conRetiro
				}
		var parametros = JSON.stringify(parametro);
		//alertError(parametros);
		$.ajax({
			type : "post",
			url : "CargarDatosTurno",
			data : {jsonData : parametros},
			success : function(respuesta){
				//alertError(respuesta);
				$(location).attr('href',"TurnosPaso2");
				
			}
		}); 
		
	}
	})
	
	$('#btnConfirmarTurno').click(function(e){
		e.preventDefault();
		
		var proceso="alta";
		var observaciones = $('#observaciones').val();
		var parametro = {
						proceso: proceso,
						observaciones : observaciones
						}
		var parametros = JSON.stringify(parametro);
		//alertError(parametros);
		$.ajax({
			type : "post",
			url : "ProcesarTurno",
			data : {jsonData : parametros},
			success : function(respuesta){
				//alertError(respuesta);		//NO DETIENE LA EJECUCION POR LO QUE NO SE MUESTRA
				if (respuesta == 1){
					swal ( {
						  title : "Bien hecho!", 
						  text : "Turno creado exitosamente", 
						  icon : "success" , 
						  buttons: {
							    cancel: false,
							    confirm: true,
							  },
						} )
						
					 .then((willDelete) => {
						  if (willDelete) {
							  $(location).attr('href','Turnos');
						  } else {
							  alertError("No se pudo mostrar el popUp");
						  }
						});
				}else{
					alertError("Error al cargar el turno");
				}
			}
		}); 
	})
	
	$('#btnContinuarModificacion').click(function(e){
		e.preventDefault();
				
		var resultado = validarDatosTurno();
		if(resultado){
					//////////////////////PROCESAR TURNO///////////////////////////////
			var tamanio,pelaje,servicio,fecha,horario="";
			var conRetiro,resultado=false;
			var repeticion="No";
			var idUsuario,idMascota=0;
			
			//var pelaje="";
			//var servicio="";
			//var fecha="";
			//var horario ="";
			//var idMascota=0;
			//var resultado = false;
			
			var validaTamanio = false;
			if ($('#btnPatitaGrande').hasClass("icon-button-active")||$('#btnPatitaMediana').hasClass("icon-button-active")||$('#btnPatitaChica').hasClass("icon-button-active")){
				validaTamanio = true;
				if($('#btnPatitaGrande').hasClass("icon-button-active")){
					tamanio="Grande";
				}else{
					if($('#btnPatitaMediana').hasClass("icon-button-active")){
						tamanio="Mediano";
					}else{
						tamanio="Chico";
					}
				}
			}
			
			var validaPelaje = false;
			if($('#btnTijeraChica').hasClass("icon-button-active")||$('#btnTijeraGrande').hasClass("icon-button-active")){
				validaPelaje=true;
				if($('#btnTijeraGrande').hasClass("icon-button-active")){
					pelaje ="Largo";
				}else{
					pelaje ="Corto";
				}
			}
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
			
			var validaCliente=false;
			if($('#idUsuario').val()!=""){
				validaCliente=true;
				idUsuario = $('#idUsuario').val();
			}else {
				if($('#idUsuario').data("value")!=""){
					validaCliente=true;
					idUsuario = $('#idUsuario').data("value");
				}
			}
			
			var validaMascota = false;
			if($('#mascota').val()!="mascota"){
				validaMascota = true;
				idMascota = $('#mascota').val();
			}
			
		var proceso = "modificacion";
			var parametro = {
					proceso : proceso,
					tamanio : tamanio,
					pelaje : pelaje,
					servicio : servicio,
					fecha : fecha,
					horario : horario,
					repeticion : repeticion,
					idUsuario : idUsuario,
					idMascota : idMascota,
					conRetiro : conRetiro
					}
			var parametros = JSON.stringify(parametro);
			//alertError(parametros);
			$.ajax({
				type : "post",
				url : "CargarDatosTurno",
				data : {jsonData : parametros},
				success : function(respuesta){
					//alertError(respuesta);
					$(location).attr('href',"ModificarTurnoPaso2");
					
				}
			}); 
			
		}
	})
	
	$('#btnConfirmarModificacionTurno').click(function(e){
		e.preventDefault();
		
		var proceso = "modificacion";
		var observaciones = $('#observaciones').val();
		var parametro = {
						proceso : proceso,
						observaciones : observaciones
						}
		var parametros = JSON.stringify(parametro);
		//alertError(parametros);
		$.ajax({
			type : "post",
			url : "ProcesarTurno",
			data : {jsonData : parametros},
			success : function(respuesta){
				//alertError(respuesta);		//NO DETIENE LA EJECUCION POR LO QUE NO SE MUESTRA
				if (respuesta ==1){
					swal ( {
						  title : "Bien hecho!", 
						  text : "Turno modificado exitosamente", 
						  icon : "success" , 
						  buttons: {
							    cancel: false,
							    confirm: true,
							  },
						} )
						
					 .then((willDelete) => {
						  if (willDelete) {
							  $(location).attr('href','Turnos');
						  } else {
							  alertError("No se pudo mostrar el popUp");
						  }
						});
				}else{
					alertError("Error al modificar el turno");
				}
			}
		}); 
	})
	
	$('#volverPaso1').click(function(e){
		e.preventDefault();
		$(location).attr('href','Turnos');
	})
})