/**
 * 
 */
//--------------------------------RESALTAR CAMPOS--------------------------------------------//
function resaltarCampo(campo,nombre){
	$("#"+campo+"Group").addClass("has-error");
	$("#"+campo).focus();
	if(!($("#completar"+campo).length)){
		$("<small class='form-text text-muted text-danger' id='completar"+campo+"'>Debe completar el campo "+nombre+"</small>").insertAfter("#"+campo);
	}
}
//-----------------------------------DAR FORMATO A LA FECHA DEL JSON---------------------------------------//
function formatearFecha(fecha){
	var fechaF = new Date(fecha);
	var dia = fechaF.getDate();
	if (dia <=9){dia = "0"+dia};
	var mes = fechaF.getMonth()+1;
	if (mes <=9){mes = "0"+mes};
	var anio = fechaF.getFullYear();
	fechaParseada = anio+"-"+mes+"-"+dia
	return fechaParseada;
}

//-----------------------------------VALIDAR SINTACTICAMENTE EMAIL---------------------------------------//
function validarEmailExistente(){
	
	$('#completaremail').remove();
	$("#emailGroup").removeClass("has-error");
	
	var validaMail= false;
	
	var email = $('#email').val();
	
	emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	
	if(emailRegex.test(email)){
		return true;
	}else{
		$("#emailGroup").addClass("has-error");
		$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado no tiene un formato v&aacute;lido</small>").insertAfter("#email");
	return false;
	}
}

//	-------------------------------------	VALIDAR DATOS DEL PANEL MODAL DE MASCOTA 	--------------------------------------//
function validarModal(){
	var validaNombre = false;
    if ($('.nombreMascota').val()!=""){
    	validaNombre = true;
    }
    var validaTamanio = false;
	if ($('#btnPatitaGrande').hasClass("icon-button-active")||$('#btnPatitaMediana').hasClass("icon-button-active")||$('#btnPatitaChica').hasClass("icon-button-active")){
		validaTamanio = true;
	}
	
	var validaPelaje = false;
	if($('#btnTijeraChica').hasClass("icon-button-active")||$('#btnTijeraGrande').hasClass("icon-button-active")){
		validaPelaje=true;
	}
	
	var validaFecha = false;
    if ($('#fechaNacimientoMascota').val()!=""){
    	validaFecha = true;
    }
	
	var respuesta = false;
	
	if(validaNombre){
    	
    	if(validaTamanio){
    		
    		 if(validaPelaje){
    			 
    			 if(validaFecha){
    				 
    				 respuesta = true;	
    			 
    			 }else {$("<small class='form-text text-muted text-danger' id='completarfechanacimiento'>Debes ingresar la fecha de nacimiento</small>").insertAfter("#fechaNacimientoMascota");
    			 	$('#fechaNacimientoMascotaGroup').addClass("has-error");}
    			 
    		 }else{$("<small class='form-text text-muted text-danger' id='completarpelaje'>Debes seleccionar un largo de pelaje</small>").insertAfter("#pelajeGroup");
    		 $('#pelajeGroup').addClass("con-error");}
    	
    	}else{$("<small class='form-text text-muted text-danger' id='completartamanio'>Debes seleccionar un tama&ntilde;o</small>").insertAfter("#patitaGroup");
    	$('#patitaGroup').addClass("con-error");}
		
	}else {$("<small class='form-text text-muted text-danger' id='completarnombreMascota'>Debes ingresar un nombre para la mascota</small>").insertAfter(".nombreMascota");
	$('#nombreMascotaGroup').addClass("has-error");}
	return respuesta;
}

/// 	--------------------------------LIMPIAR CAMPOS UNA VEZ QUE SE AGREGO EL CLIENTE Y SU MASCOTA 	--------------------------------///
function limpiarCampos(){
	
	$("#nombre").val("");
	$("#apellido").val("");
	$("#dni").val("");
	$("#direccion").val("");
	$("#telefono").val("");
	$("#email").val("");
	$('#tablaMascota').addClass("hidden");
	$('#tableMas > tbody').html("");//ELIMINO LAS FILAS DE LA TABLA QUE EXISTE EN ESTE MOMENTO
	$('#completaremail').remove();
	valido = false;	
}



//	------------------------------------LIMPIAR CAMPOS DEL PANEL MODAL	--------------------------------------------------//
function limpiarCamposModal(){
		$(".nombreMascota").val("");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active");
		$('#btnTijeraGrande').removeClass("icon-button-active");
		$('#completarpelaje').remove();
		$('#completartamanio').remove();
		$('#completarfechanacimiento').remove();
		$("#fechaNacimientoMascota").val("");
		$("#observacionesMascota").val("");
		$("#idMascotaHidden").val("");			
}


///	------------------------ VALIDO QUE LOS DATOS OBLIGATORIOS DEL CLIENTE ESTEN COMPLETOS 	---------------------------------///
function validar(usuarioRegistrado){

	var nombre, apellido, telefono,telefonoValido,dniValido,emailValido;
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
	
	telefonoValido = true;
	if(isNaN($('#telefono').val())){
		telefonoValido=false;
	}
	dniValido = true;
	if(isNaN($('#dni').val())){
		dniValido=false;
	}
	emailValido = true;
	$('#completaremail').remove();
	
	if($('#email').val()!=""){
		emailValido=validarEmailExistente();
	}
	
	if (nombre) {
		if (apellido) {
			if(dniValido){
				if (telefono) {
					if(telefonoValido){ 
						if(emailValido){	
							return true; 	
						}
						else {
							
							return false;
							
						}
						
					}
					else {
						
						$("#completartelefono").remove();
						$("#telefonoGroup").addClass("has-error");
						$("<small class='form-text text-muted text-danger' id='completartelefono'>Debes ingresar s&oacute;lo n&uacute;meros en el campo tel&eacute;fono</small>").insertAfter("#telefono");	
					
					}
				}
				else{
					
					resaltarCampo("telefono","Telefono");	
				
				}
			}
			else{
				
				$("#completardni").remove();
				$("#dniGroup").addClass("has-error");
				$("<small class='form-text text-muted text-danger' id='completardni'>Debes ingresar s&oacute;lo n&uacute;meros en el campo DNI</small>").insertAfter("#dni");
				
			}
		} else {
			
			resaltarCampo("apellido","Apellido");
			
		}
	} else {
		
		$("#nombreGroup").addClass("has-error");
		$("#nombre").focus();
		if(!($("#completarnombre").length)){
			
			$("<small class='form-text text-muted text-danger' id='completarnombre'>Debe completar el campo Nombre</small>").insertAfter("#nombre");
		
		}
		
	}
	
}






















//---------------------------------RECARGAR LA TABLA DE MASCOTAS--------------------------------//
function recargarTablaMascotas(accion){
	if(accion=="alta"){
		linea = "";
	}else{
		linea = "<a class='btn btn-info btnModificarMascota' title='Editar mascota' href='\'><span class='fa fa-pencil'></span> </a>"
	}
	 $('.tableMas tbody').empty();
	 $.post("ObtenerMascotasTemp",function(responseJson){
		 var i=0;
			$.each(responseJson,function(index, mascotas){
				
				 $('<tr>',{
						'html' : "<td class='hidden' id='idMascota'>"+mascotas.idMascota+"</td>" +
						"			<td id='nombreMascota'>"+mascotas.nombre+"</td>" +
						"			<td id='tamanio'>"+mascotas.tipoMascota.tamanio+"</td>" +
						"			<td id='pelaje'>"+mascotas.tipoMascota.pelo+"</td>" +
						"			<td id='fechaNacimiento'>"+formatearFecha(mascotas.fechaNacimiento)+"</td>" +
						"			<td class='col-sm-3 col-lg-2'>" +
						"				<div class='input-group'>" +
						"					<a class='btn btn-danger btnQuitarMascota' title='Quitar mascota' href='\'><span class='fa fa-times'></span> </a>" +
						linea	+
						"				</div>" +
						"			</td>"+
						"			<td id='observacion' class='hidden'>"+mascotas.observaciones+"</td>"		
				}).appendTo(".tableMas > tbody"); 
				 $('.tableMas').removeClass("hidden");
				i++;
			})
			if(i>0){
				$('#tablaMascota').removeClass("hidden");
			}
      })
}





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



$(document).ready(function() {
	
	var agregar=true;
	var valido = false;
	
	var nombreAModificar="";
	var tamanioAModificar="";
	var pelajeAModificar="";
	
	//---------AL CERRAR EL PANEL MODAL SE LIMPIA SU CONTENIDO	---------------//
	$(document).on("hide.bs.modal","#agregarMascotaModificar",function(){
		limpiarCamposModal();
		agregar = true;
	})
	
	//----------VALIDA SINTACTICAMENTE EL MAIL CUANDO CAMBIA -----------------//
	$("#email").change(function(){
		$('#completaremail').remove();
		$("#emailGroup").removeClass("has-error");
		if($("#email").val()!=""){
			validarEmailExistente();
		}
	})	
	
//---------------------------------------BOTON MODIFICAR MASCOTA DE LA FILA DE LA TABLA----------------------------------------//
	
	$(this).on("click", ".btnModificarMascota", function(e){
	    e.preventDefault();
	    //OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
		var fila =$(this).parent().parent().parent()
		
		agregar = false;	
		
		var idMascota = fila.find("#idMascota").text();
		
		var nombreMascota = fila.find('#nombreMascota').text();
		var tamanio = fila.find('#tamanio').text();
		var pelaje = fila.find('#pelaje').text();
		var fechaNacimiento = fila.find('#fechaNacimiento').text();
		var observacion = fila.find('#observacion').text();
		switch (tamanio){
			case "Grande":
				$('#btnPatitaGrande').addClass("icon-button-active");
				break;
			case "Mediano":
				$('#btnPatitaMediana').addClass("icon-button-active");
				break;
			case "Chico":
				$('#btnPatitaChica').addClass("icon-button-active");
				break;
		};
		switch (pelaje){
			case "Largo":
				$('#btnTijeraGrande').addClass("icon-button-active");
				break;
			case "Corto":
				$('#btnTijeraChica').addClass("icon-button-active");
				break;
		}
		//CARGO EL PANEL MODAL CON LOS DATOS OBTENIDOS DE LA TABLA
		$("#idMascotaHidden").val(idMascota);
		$('.nombreMascota').val(nombreMascota);
		$('#fechaNacimientoMascota').val(fechaNacimiento);
		$('#observacionesMascota').val(observacion);
		$('#agregarMascotaModificar').modal('toggle');
		nombreAModificar=nombreMascota;
		tamanioAModificar=tamanio;
		pelajeAModificar=pelaje;
					
	});	
	

	//----------------------------------------------BOTON CRUZ DE LA TABLA PARA ELIMINAR LA FILA-----------------------------------//
	$(this).on("click", ".btnQuitarMascota", function(e){
	    e.preventDefault();
	   
	    //OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
		var fila =$(this).parent().parent().parent()
		var cantFilas = $("#tableMas tr").length; //OBTENGO LA CANTIDAD DE FILAS DE LA TABLA 
		
		if (cantFilas <= 1){
			$('#tablaMascota').addClass("hidden");
		}
		var idMascota = fila.find("#idMascota").text();
		var nombre = fila.find("#nombreMascota").text();
		var tamanio = fila.find("#tamanio").text();
		var pelaje = fila.find("#pelaje").text();
		var fechaNacimiento = fila.find("#fechaNacimiento").text();
		var observacion = fila.find("#observacion").text();
		
		var parametro = {
				
				idMascota : idMascota,
				nombre : nombre,
				tamanio : tamanio,
				pelaje : pelaje,
				fechaNacimiento : fechaNacimiento,
				observacion : observacion	
			}
		
		var parametros = JSON.stringify(parametro);	
		$.ajax({
			url : "QuitarMascota",
			type : "post",
			data : {jsonData : parametros},
			success : function(data){
				if (data == 1){
					//alertOk("Mascota eliminada correctamente, p");	
			        recargarTablaMascotas();
				}
				if (data == 0){
					alertError("No se pudo eliminar la mascota")
				}
				
			}
		})
		
					
	});	
	////////////////////////////////////////////////////////////////////////////////////
	//QUITAR LAS ALERTAS A LOS CAMPOS AL ESCRIBIR EN ELLOS
	$(this).on("click", "#nombreMascotaGroup", function(e){
	    $('#nombreMascotaGroup').removeClass("has-error"); 
	    $('#completarnombreMascota').remove();
	})
	
	$(this).on("click", "#fechaNacimientoMascotaGroup", function(e){
	    $('#fechaNacimientoMascotaGroup').removeClass("has-error");
	    $('#completarfechanacimiento').remove();
	    
	})
	///////////////////////////////////////////////////////////////////////////////////
	
	
	// ---------LLAMAR AL PANEL MODAL DESDE EL BOTON AGREGAR MASCOTA	--------//
	$('#btnAgregarMascota').click(function(e){
		e.preventDefault();
		agregar=true;
		$('#agregarMascotaModificar').modal('toggle');
	})
	
	
	
///--------------------------------------- BOTON CONFIRMAR DEL PANEL MODAL DE MASCOTAS ---------------------------------------///
	//SOLO EN CASO DE ALTAS DE NUEVOS CLIENTES
	
	$('.btnAgregarMascotaModalAlta').on("click",function(e){
	    e.preventDefault();
	    
	    $('#completarpelaje').remove();
		$('#completartamanio').remove();
		$('#completarfechanacimiento').remove();
		$('#completarnombreMascota').remove();

		if (validarModal()){		
			
			var idMascota = $("#idMascotaHidden").val();
	        var nombre = $('.nombreMascota').val();
	        var tamanio = "";
	        var pelaje = "";
	        var fechaNacimiento = $('#fechaNacimientoMascota').val();
	        var observacion = $('#observacionesMascota').val();
	        
	        if($('#btnPatitaGrande').hasClass("icon-button-active")){
				tamanio="Grande";
			}else{
				if($('#btnPatitaMediana').hasClass("icon-button-active")){
					tamanio="Mediano";
				}else{
					tamanio="Chico";
				}
			}
	        
			if($('#btnTijeraGrande').hasClass("icon-button-active")){
				pelaje ="Largo";
			}else{
				pelaje ="Corto";
			}
			
			var accion = "alta";
			
			var parametro = {
					accion : accion,
					idMascota : idMascota,
					nombre : nombre,
					tamanio : tamanio,
					pelaje : pelaje,
					fechaNacimiento : fechaNacimiento,
					observacion : observacion	
				}
				
			var parametros = JSON.stringify(parametro);
			
			if(idMascota!=""){
				//MODIFICAR UNA MASCOTA
		
			} else {
				$.ajax({
					url : "AgregarMascota",
					type : "post",
					data : {jsonData : parametros},
					success : function(data){
						if (data == 1){
							//alertOk("Mascota agregada correctamente");
							limpiarCamposModal();
					        $('#agregarMascotaModificar').modal('toggle');
			
					        recargarTablaMascotas(accion);
			
						}
						if (data == 2){
							//alertOk("Mascota modificada correctamente");
							limpiarCamposModal();
					        $('#agregarMascotaModificar').modal('toggle');
					        recargarTablaMascotas(accion);
						}
						if(data==0){
							alertError("Error al procesar la mascota");
						}
					}
				})
			}
					
		} else {
			alertError("Por favor revisa los datos ingresados")
		}
	})
		
	//SOLO EN CASO DE MODIFICACION DE CLIENTES---------------------------------------------------------------//
	
	$('.btnAgregarMascotaModal').on("click",function(e){
	    e.preventDefault(); 
	    $('#completarpelaje').remove();
		$('#completartamanio').remove();
		$('#completarfechanacimiento').remove();
		$('#completarnombreMascota').remove();
			
		if (validarModal()){		
			
			var idMascota = $("#idMascotaHidden").val();
	        var nombre = $('.nombreMascota').val();
	        var tamanio = "";
	        var pelaje = "";
	        var fechaNacimiento = $('#fechaNacimientoMascota').val();
	        var observacion = $('#observacionesMascota').val();
	        
	        if($('#btnPatitaGrande').hasClass("icon-button-active")){
				tamanio="Grande";
			}else{
				if($('#btnPatitaMediana').hasClass("icon-button-active")){
					tamanio="Mediano";
				}else{
					tamanio="Chico";
				}
			}
	        if (idMascota==""){
	        	idMascota="0";
	        }
	        
			if($('#btnTijeraGrande').hasClass("icon-button-active")){
				pelaje ="Largo";
			}else{
				pelaje ="Corto";
			}
			
			var accion = "modificacion";
			
			
			var parametro = {
					accion : accion,
					idMascota : idMascota,
					nombre : nombre,
					tamanio : tamanio,
					pelaje : pelaje,
					fechaNacimiento : fechaNacimiento,
					observacion : observacion,
					nombreAModificar : nombreAModificar,
					tamanioAModificar : tamanioAModificar,
					pelajeAModificar : pelajeAModificar
				}
				
			var parametros = JSON.stringify(parametro);
			
			$.ajax({
				url : "AgregarMascota",
				type : "post",
				data : {jsonData : parametros},
				success : function(data){
					if (data == 1){
						//alertOk("Mascota agregada correctamente");
						limpiarCamposModal();
				        $('#agregarMascotaModificar').modal('toggle');
				       
				        recargarTablaMascotas(accion);
		
					}
					if (data == 2){
						//alertOk("Mascota modificada correctamente");
						limpiarCamposModal();
				        $('#agregarMascotaModificar').modal('toggle');
				        recargarTablaMascotas(accion);
					}
					if(data==0){
						alertError("Error al procesar la mascota");
					}
				}
			})
			
					
		} else {
			alertError("Por favor revisa los datos ingresados")
		}
	})
	
	
	
	///---------------------------------------------- ALTA DE CLIENTE ----------------------------------------------///
	$('#btnAgregarCliente').click(function(e){
		e.preventDefault();
		$('#completaremail').remove();
		var resultado = validar(false);	
						
		if(resultado)
		{
			var usuario = "";
			var password = "";
			var nombre = $("#nombre").val();
			var apellido = $("#apellido").val();
			var dni = $("#dni").val();
			var direccion = $("#direccion").val();
			var telefono = $("#telefono").val();
			var email = $("#email").val();
			var habilitado = 0;
			if($("#habilitado").is(':checked')){
				habilitado = 1;
			};
			
			
			var parametro = {
						usuario : usuario,
						password : password,
						nombre : nombre,
						apellido : apellido,
						dni : dni,
						direccion : direccion,
						telefono : telefono,
						email : email,
						habilitado : habilitado	
					}
					
				
			var parametros = JSON.stringify(parametro);
			
			
			$.ajax({	
				url : "ConfirmarAltaCliente",
				type : "post",
				data : {jsonData : parametros},
				success : function(data){
					if (data != 0){
						
						if (data == 2){
							alertError("ERROR AL GRABAR LAS MASCOTAS");
						}
						if (data == 3){
							$("#emailGroup").addClass("has-error");
							$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado ya se encuentra registrado</small>").insertAfter("#email");
							alertError("Algunos de los campos del formulario tienen errores");
						}
						if (data == 1){
							swal ( {
								 title : "Bien hecho!",
								 text : "Cliente agregado exitosamente",
								 icon : "success" , 
								 buttons: {
									 confirm: {
										    text: "Aceptar",
										    value: true,
										    visible: true,
										    className: "",
										    closeModal: true
										  },									  
									  }
								} )
							.then((respuesta) => {
								if(respuesta){
									$(location).attr('href','Ventas');
								}else{
									$(location).attr('href','Ventas');	
								}
							})
					
						}
					} else {
						alertError("ERROR");
					}	
				}
				})
				}
		else{
			alertError("Algunos de los campos del formulario tienen errores");
		}

	})
	
	
	
	
	
	
	///---------------------------------------------- MODIFICACION DE CLIENTE---------------------------------------------- ///
	$('#btnModificarUsuario').click(function(e){
		e.preventDefault();
		$('#completaremail').remove();
		
		var resultado = validar(true);
		
		if(resultado)
		{
			var idUsuario = $("#idUsuario").val(); 
			var usuario = "";
			var password = "";
			var nombre = $("#nombre").val();
			var apellido = $("#apellido").val();
			var dni = $("#dni").val();
			var direccion = $("#direccion").val();
			var telefono = $("#telefono").val();
			var email = $("#email").val();
			var habilitado = 0;
			if($("#habilitado").is(':checked')){
				habilitado = 1;
			};
			
			var parametro = {
				idUsuario : idUsuario,	
				usuario : usuario,
				password : password,
				nombre : nombre,
				apellido : apellido,
				dni : dni,
				direccion : direccion,
				telefono : telefono,
				email : email,
				habilitado : habilitado
				
			}
			
		
			var parametros = JSON.stringify(parametro);
			
			
			$.ajax({
					url : "ConfirmarModificacionUsuario",
					type : "post",
					data : {jsonData : parametros},
					success : function(data){
						if (data == 1)
						{
							swal ( {
								 title : "Bien hecho!",
								 text : "Cliente modificado exitosamente",
								 icon : "success" , 
								 buttons: {
									 confirm: {
										    text: "Aceptar",
										    value: true,
										    visible: true,
										    className: "",
										    closeModal: true
										  },									  
									  }
								} )
							.then((respuesta) => {
								if(respuesta){
									$(location).attr('href','Ventas');
								}else{
									$(location).attr('href','Ventas');	
								}
							})
							
						} 
						if (data == 2)
						{
							$("#emailGroup").addClass("has-error");
							$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado ya se encuentra registrado</small>").insertAfter("#email");
						
						}
						if (data==0){
							alertError("ERROR");
						}
	                    
					}
			})
		}
	})
	
	
	
	
	
	
	
	
	//-----------------------------------CAPTURO EL CLICK DEL BOTON BUSCAR EN EL PANEL MODAL---------------------------------//
	$(this).on("click", "#btnBusquedaCliente", function(e){
	    e.preventDefault();
	    $("#sinResultados").remove();
	    
	    var hayResultados=0;
	    
		var inputCliente = $('#inputCliente').val();
		if(inputCliente!=""){
			var parametro = {inputCliente : inputCliente};
			$('#cliente').prop('disabled',false);
			
			$.post("ObtenerTodosClientes",$.param(parametro),function(responseJson){
				
				$('#cliente').empty();
				$('#cliente').append($('<option value="cliente">Seleccion&aacute; un cliente</option>'));
				$.each(responseJson,function(index, usuarios){
					$('#cliente').append($('<option value="'+usuarios.idUsuario+'">'+usuarios.apellido+', '+usuarios.nombre+'</option>'));
					hayResultados++;
				});
				
				if (hayResultados==0){
					$('#cliente').prop('disabled',true);
					$("<small class='form-text text-danger' id='sinResultados'>No se encontraron clientes que coincidan con '"+inputCliente+"'</small>").insertAfter("#nombreGroup");
				}
				
				
			});
		}else{
			$('#cliente').empty();
			$("<small class='form-text text-danger' id='sinResultados'>No ingresaste nombre o apellido</small>").insertAfter("#nombreGroup");
			$('#cliente').append($('<option value="cliente">Seleccion&aacute; un cliente</option>'));
			$('#cliente').prop('disabled',true);
			$('#inputClienteGroup').addClass("has-error");
			clearTimeout();
			setTimeout(function(){$('#inputClienteGroup').removeClass("has-error");},2000);
			}
	}); 
	
	
	
	
	
	
	
	
	
	
	/// ----------------------------------------------CAPTURO CLICK EN EL BOTON DE MODIFICAR CLIENTES ----------------------------------------------///
	$(this).on("click", "#btnVerCliente", function(e){
		e.preventDefault();
	    if($('#cliente').val() == "cliente"){
			alertError("Para modificar el cliente primero debes seleccionarlo");
		}else{	
			var idCliente = $('#cliente').val();
	        
	        parametro = {idCliente : idCliente};
	              
	        var parametros = JSON.stringify(parametro);
	        
	        $.ajax({
				url : "ModificarCliente",
				type : "post",
				data : {jsonData : parametros},
				success : function(data){
					if (data == 1)
					{
						$(location).attr('href','ModificarClienteConDatos');
					} 
					else
					{
						alertError("No es posible modificar el cliente.");
					}
                    
				}
	        })	  
		}
	})
	
	
	
	
	
	
	
	
	
	///---------------------------------------CAPTURO CLICK EN EL BOTON DE BORRAR CLIENTES ----------------------------------------///
	$(this).on("click", "#btnBorrarCliente", function(e){
		e.preventDefault();
	    if($('#cliente').val() == "cliente"){
			alertError("Para eliminar el cliente primero debes seleccionarlo");
		}else{
			var idCliente = $('#cliente').val();
	        
			swal ( {
				 title : "Atenci\u00F3n!",
				 text : "Seguro quer\u00e9s inhabilitar el cliente?",
				 icon : "info" , 
				 buttons: {
					 confirm: {
						    text: "Aceptar",
						    value: true,
						    visible: true,
						    className: "",
						    closeModal: true
						  },
					 cancel: 
					 	  { text: "Cancelar",
						    value: false,
						    visible: true,
						    className: "",
						    closeModal: true,
						  }
						  
					  }
				} )
				.then((respuesta) => {
					  if (respuesta) {
						  parametro = {idCliente : idCliente};
					        var parametros = JSON.stringify(parametro);
					        
					        $.ajax({
								url : "EliminarCliente",
								type : "post",
								data : {jsonData : parametros},
								success : function(data){
									if (data ==1)
									{
										alertOk("Se inhabilit\u00F3 el cliente.");
									} 
									else
									{
										alertError("No se pudo inhabilitar el cliente. Ya se encuentra utilizado.");
									}
				                    
								}
					        })   
					  }else{
						  return false;
					  }
					});
		}
		
	})
	
	//----------------------------------------------VALIDAR EMAIL---------------------------------------------//
	
	
//-------------PARA QUE QUEDE SELECCIONADO UN TAMANIO Y UN PELAJE--------------------------------////
	$(this).on("click","#btnPatitaGrande",function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#btnPatitaGrande').addClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		$('#completartamanio').remove();
		
	})
	
	$(this).on("click","#btnPatitaMediana",function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').addClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		$('#completartamanio').remove();
		
	})
	
	$(this).on("click","#btnPatitaChica",function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').addClass("icon-button-active");
		$('#completartamanio').remove();
	})
	
	$(this).on("click","#btnTijeraGrande",function(e){
		e.preventDefault();
		$('#pelajeGroup').removeClass("con-error");
		$('#btnTijeraGrande').addClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active");
		$('#completarpelaje').remove();
	})
	
	$(this).on("click","#btnTijeraChica",function(e){
		e.preventDefault();
		$('#pelajeGroup').removeClass("con-error");
		$('#btnTijeraGrande').removeClass("icon-button-active");
		$('#btnTijeraChica').addClass("icon-button-active");
		$('#completarpelaje').remove();
	})
//-------------------------------------QUITAR ALERTAS DE ERROR--------------------------------------////
	//	QUITAR ALERTAS DE ERROR AL CAMPO
	$("#nombre").change(function(){
		$('#nombreGroup').removeClass("has-error");
		$("#completarnombre").remove();
	})
	//	QUITAR ALERTAS DE ERROR AL CAMPO
	$("#apellido").change(function(){
		$('#apellidoGroup').removeClass("has-error");
		$("#completarapellido").remove();	
	})
	//	QUITAR ALERTAS DE ERROR AL CAMPO
	$("#dni").change(function(){
		$('#dniGroup').removeClass("has-error");
		$("#completardni").remove();	
	})
	//	QUITAR ALERTAS DE ERROR AL CAMPO	
	$("#telefono").change(function(){
		$('#telefonoGroup').removeClass("has-error");
		$("#completartelefono").remove();	
	})
	//	QUITAR ALERTAS DE ERROR AL CAMPO	
	$("#nombreMascota").change(function(){
		 $('#nombreMascotaGroup').removeClass("has-error"); 
		 $('#completarnombreMascota').remove();
	})
	
	
//-------------------------------------------------------------------------------------------------------------////
});
	