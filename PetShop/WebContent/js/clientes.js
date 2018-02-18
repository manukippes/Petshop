/**
 * 
 */
function resaltarCampo(campo,nombre){
	$("#"+campo+"Group").addClass("has-error");
	$("#"+campo).focus();
	if(!($("#completar"+campo).length)){
		$("<small class='form-text text-muted text-danger' id='completar"+campo+"'>Debe completar el campo "+nombre+"</small>").insertAfter("#"+campo);
	}
}
function validarEmail(){
	
	$('#completaremail').remove();
	$("#emailGroup").removeClass("has-error");
	
	var validaMail= false;
	
	var email = $('#email').val();
	
	emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    
    if (emailRegex.test(email)) {
    	validaMail=true;
    } else {
    	validaMail = false;
    }
    
    if(validaMail){ 
    	var parametro = { email : email }
		var parametros = JSON.stringify(parametro);
		
		$.ajax({
				url : "ValidarMail",
				type : "post",
				data :  {jsonData : parametros},
				success : function(data){
					if (data == 1)
					{
						$("#emailGroup").addClass("has-error");
						$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado ya se encuentra registrado</small>").insertAfter("#email");
						return false;
					} 
					else
					{
						$("<small class='form-text text-muted text-success' id='completaremail'>El mail ingresado es correcto</small>").insertAfter("#email");
						return true;
					}           
				}
		})
    } else {
    	$("#emailGroup").addClass("has-error");
    	$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado no tiene un formato v&aacute;lido</small>").insertAfter("#email");
    	return false;
    }
}

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
/// LIMPIAR CAMPOS UNA VEZ QUE SE AGREGO EL CLIENTE Y SU MASCOTA ///
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
function limpiarCamposModal(){
		$(".nombreMascota").val("");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active")
		$('#btnTijeraGrande').removeClass("icon-button-active")
		$("#fechaNacimientoMascota").val("");
		$("#observacionesMascota").val("");
		$("#idMascotaHidden").val("");			
}

/// VALIDO QUE LOS DATOS OBLIGATORIOS DEL CLIENTE ESTEN COMPLETOS ///
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
							return false
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
			resaltarCampo("apellido","Apellido")
		}
	} else {
		$("#nombreGroup").addClass("has-error");
		$("#nombre").focus();
		if(!($("#completarnombre").length)){
			$("<small class='form-text text-muted text-danger' id='completarnombre'>Debe completar el campo Nombre</small>").insertAfter("#nombre");
		}
	}
}

$(document).ready(function() {
	
	var agregar=true;
	var valido = false;
	
	$("#email").change(function(){
		$('#completaremail').remove();
		validarEmailExistente();
	})
	
		//----------------------------------------------BOTON MODIFICAR MASCOTA DE LA FILA DE LA TABLA----------------------------------------------//
	
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
				
				$("#idMascotaHidden").val(idMascota);
				$('.nombreMascota').val(nombreMascota);
				$('#fechaNacimientoMascota').val(fechaNacimiento);
				$('#observacionesMascota').val(observacion);
				$('#agregarMascotaModificar').modal('toggle');

				
			});	
	

	//----------------------------------------------BOTON CRUZ DE LA TABLA PARA ELIMINAR LA FILA----------------------------------------------//
			$(this).on("click", ".btnQuitarMascota", function(e){
			    e.preventDefault();
			   
			    //OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
				var fila =$(this).parent().parent().parent()
				var cantFilas = $("#tableMas tr").length; //OBTENGO LA CANTIDAD DE FILAS DE LA TABLA 
				
				if (cantFilas <= 1){
					$('#tablaMascota').addClass("hidden");
				}
						
				var idMascota = fila.find('#idMascota').text();
				
				if(idMascota!=""){
					fila.addClass("hidden");
					fila.find('#nombreMascota').text("QuitarMascota");
				}else{

					fila.remove();
				}			
			});	
	
	$(this).on("click", "#nombreMascotaGroup", function(e){
	    $('#nombreMascotaGroup').removeClass("has-error"); 
	})
	$(this).on("click", "#fechaNacimientoMascotaGroup", function(e){
	    $('#fechaNacimientoMascotaGroup').removeClass("has-error"); 
	})
	
	
		///---------------------------------------------- BOTON CONFIRMAR DEL PANEL MODAL DE MASCOTAS ----------------------------------------------///	
	$('.btnAgregarMascotaModal').on("click",function(e){
	    e.preventDefault();
	    
	    
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
	    			 
	    			 }else {alertError("Para agregar primero debes ingresar la fecha de nacimiento");
	    			 	$('#fechaNacimientoMascotaGroup').addClass("has-error");}
	    			 
	    		 }else{alertError("Para agregar primero debes seleccionar un pelaje de la mascota");
	    		 $('#pelajeGroup').addClass("con-error");}
	    	
	    	}else{alertError("Para agregar primero debes seleccionar un tamaño de la mascota");
	    	$('#patitaGroup').addClass("con-error");}
			
		}else {alertError("Para agregar primero debes ingresar un nombre");
		$('#nombreMascotaGroup').addClass("has-error");}
			
		if (respuesta){			
	        
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
			
			if(agregar){
	        $('<tr>',{
				'html' : "<td class='hidden' id='idMascota'></td>" +
				"			<td id='nombreMascota'>"+nombre+"</td>" +
				"			<td id='tamanio'>"+tamanio+"</td>" +
				"			<td id='pelaje'>"+pelaje+"</td>" +
				"			<td id='fechaNacimiento'>"+fechaNacimiento+"</td>" +
				"			<td class='col-sm-3 col-lg-2'>" +
				"				<div class='input-group'>" +
				"					<a class='btn btn-info btnModificarMascota' title='Editar mascota' href='\'><span class='fa fa-pencil'></span> </a>" +
				"					<a class='btn btn-danger btnQuitarMascota' title='Quitar mascota' href='\'><span class='fa fa-times'></span> </a>" +
				"				</div>" +
				"			</td>"+
				"			<td id='observacion' class='hidden'>"+observacion+"</td>"		
				}).appendTo(".tableMas > tbody");  
	        
	        
	        $('#tablaMascota').removeClass("hidden");
	        limpiarCamposModal();
	        $('#agregarMascotaModificar').modal('toggle');
	       
			}else{
				
				$('#agregarMascotaModificar').modal('toggle');
				
				var filas = $("#tableMas tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
				var cantidad = filas.length; 
				if (filas.length != 1){
					
					var idMascotaInput = $('#idMascotaHidden').val();
					$.each(filas,function(i,fila) {
						//OBTENGO DE CADA MASCOTA NOMBRE TAMANIO PELAJE FECHA DE NACIMIENTO
						var idMascota = fila.cells[0].innerHTML;
						
						if(idMascota==idMascotaInput){
							
							fila.cells[1].innerHTML=nombre;
							fila.cells[2].innerHTML=tamanio;
							fila.cells[3].innerHTML=pelaje;
							fila.cells[4].innerHTML=fechaNacimiento;
							fila.cells[6].innerHTML=observacion;
							
						}
						
					})		
				}
				
				limpiarCamposModal();
				
				
			}
			agregar = true;
			}
		})
		
	///---------------------------------------------- ALTA DE CLIENTE ----------------------------------------------///
	$('#btnAgregarCliente').click(function(e){
		e.preventDefault();
		$('#completaremail').remove();
		var resultado = validar(false);
	
		var arregloMascotas = [];
		
		var filas = $("#tableMas tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		var cantidad = filas.length; 
		if (filas.length != 1){
			$.each(filas,function(i,fila) {
				//OBTENGO DE CADA MASCOTA NOMBRE TAMANIO PELAJE FECHA DE NACIMIENTO
				if(i>0){
					var idMascota = fila.cells[0].innerHTML;
					var nombreMascota = fila.cells[1].innerHTML;
					var tamanioMascota = fila.cells[2].innerHTML;
					var pelajeMascota = fila.cells[3].innerHTML;
					var fechaNacimientoMascota = fila.cells[4].innerHTML;
					var observacionesMascota = fila.cells[6].innerHTML;
					var elementoMascota = {idMascota,nombreMascota,tamanioMascota,pelajeMascota,fechaNacimientoMascota,observacionesMascota};
					arregloMascotas.push(elementoMascota); //AGREGO EL ELEMENTO Y SU CANTIDAD AL ARREGLO DE ELEMENTOS
				}
				
			})		
		}
		
						
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
						habilitado : habilitado,
						arregloMascotas : arregloMascotas	
					}
					
				
					var parametros = JSON.stringify(parametro);
					
					
					$.ajax({
							url : "ConfirmarAltaCliente",
							type : "post",
							data : {jsonData : parametros},
							success : function(data){
								if (data != 0){
									
									if (data == 1){
										$("#btnHidden").click();
										limpiarCampos();
										setTimeout("$(location).attr('href','Ventas');",3500);
									}
									if (data == 2){
										alertError("ERROR AL GRABAR LAS MASCOTAS");
									}
									if (data == 3){
										$("#emailGroup").addClass("has-error");
										$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado ya se encuentra registrado</small>").insertAfter("#email");
									}
								} else {
									prompt("ERROR");
								}	
							}
					})
				}

	})
	///---------------------------------------------- MODIFICACION DE CLIENTE---------------------------------------------- ///
	$('#btnModificarUsuario').click(function(e){
		e.preventDefault();
		$('#completaremail').remove();
		
		var resultado = validar(true);
		var arregloMascotas = [];
		
		var filas = $("#tableMas tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		var cantidad = filas.length; 
		if (filas.length != 1){
			$.each(filas,function(i,fila) {
				//OBTENGO DE CADA MASCOTA NOMBRE TAMANIO PELAJE FECHA DE NACIMIENTO
				if(i>0){
					var idMascota = fila.cells[0].innerHTML;
					var nombreMascota = fila.cells[1].innerHTML;
					var tamanioMascota = fila.cells[2].innerHTML;
					var pelajeMascota = fila.cells[3].innerHTML;
					var fechaNacimientoMascota = fila.cells[4].innerHTML;
					var observacionesMascota = fila.cells[6].innerHTML;
					var elementoMascota = {idMascota,nombreMascota,tamanioMascota,pelajeMascota,fechaNacimientoMascota,observacionesMascota};
					arregloMascotas.push(elementoMascota); //AGREGO EL ELEMENTO Y SU CANTIDAD AL ARREGLO DE ELEMENTOS
				}
				
			})		
		}
		
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
				habilitado : habilitado,
				arregloMascotas : arregloMascotas	
			}
			
		
			var parametros = JSON.stringify(parametro);
			
			
			$.ajax({
					url : "ConfirmarModificacionUsuario",
					type : "post",
					data : {jsonData : parametros},
					success : function(data){
						if (data == 1)
						{
							$("#btnHidden").click();
						
							//limpiarCampos();
							setTimeout("$(location).attr('href','Ventas');",3500);
							
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
		
		var inputCliente = $('#inputCliente').val();
		if(inputCliente!=""){
			var parametro = {inputCliente : inputCliente};
			$('#cliente').prop('disabled',false);
			
			$.post("ObtenerTodosClientes",$.param(parametro),function(responseJson){
				$('#cliente').empty();
				$('#cliente').append($('<option value="cliente">Seleccion&aacute; un cliente</option>'));
				$.each(responseJson,function(index, usuarios){
					$('#cliente').append($('<option value="'+usuarios.idUsuario+'">'+usuarios.apellido+', '+usuarios.nombre+'</option>'));
				});
			});
		}else{
			$('#cliente').empty();
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
				 text : "¿Seguro quer\u00e9s eliminar el cliente?",
				 icon : "info" , 
				 button: {
					 cancel: 
					 	  {
						    text: "Cancelar",
						    value: null,
						    visible: false,
						    className: "",
						    closeModal: true,
						  },
						  confirm: {
						    text: "Aceptar",
						    value: true,
						    visible: true,
						    className: "",
						    closeModal: true
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
										alertOk("Se elimin\u00F3 el cliente.");
									} 
									else
									{
										alertError("No se pudo eliminar el cliente.");
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
	
	$("#email").change(function(){
				$('#completaremail').remove();
				$("#emailGroup").removeClass("has-error");
				
				var validaMail= false;
				
				var email = $('#email').val();
				
				emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
			    
			    if (emailRegex.test(email)) {
			    	validaMail=true;
			    } else {
			    	validaMail = false;
			    }
			    
			    if(validaMail){ 
			    	$("<small class='form-text text-muted text-success' id='completaremail'>El mail ingresado es v&aacute;lido</small>").insertAfter("#email");
			    	
			    } else {
			    	$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado no tiene un formato v&aacute;lido</small>").insertAfter("#email");
			    }
			})
			
			
//-------------PARA QUE QUEDE SELECCIONADO UN TAMANIO Y UN PELAJE--------------------------------////
	$(this).on("click","#btnPatitaGrande",function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#btnPatitaGrande').addClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
	})
	
	$(this).on("click","#btnPatitaMediana",function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').addClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
	})
	
	$(this).on("click","#btnPatitaChica",function(e){
		e.preventDefault();
		$('#patitaGroup').removeClass("con-error");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').addClass("icon-button-active");
	})
	
	$(this).on("click","#btnTijeraGrande",function(e){
		e.preventDefault();
		$('#pelajeGroup').removeClass("con-error");
		$('#btnTijeraGrande').addClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active");	
	})
	
	$(this).on("click","#btnTijeraChica",function(e){
		e.preventDefault();
		$('#pelajeGroup').removeClass("con-error");
		$('#btnTijeraGrande').removeClass("icon-button-active");
		$('#btnTijeraChica').addClass("icon-button-active");
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
//-------------------------------------------------------------------------------------------------------------////
});
	