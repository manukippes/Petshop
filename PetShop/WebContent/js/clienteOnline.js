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

/// LIMPIAR CAMPOS UNA VEZ QUE SE AGREGO EL CLIENTE Y SU MASCOTA ///
function limpiarCampos(){
	$("#usuario").val("");
	$("#contrasenia").val("");
	$("#contraseniaRepetir").val("");
	$("#nombre").val("");
	$("#apellido").val("");
	$("#dni").val("");
	$("#direccion").val("");
	$("#telefono").val("");
	$("#email").val("");
	$('#tablaMascota').addClass("hidden");
	$('#tablaMascota > tbody').html("");//ELIMINO LAS FILAS DE LA TABLA QUE EXISTE EN ESTE MOMENTO	
}
function limpiarCamposModal(){
		$(".nombreMascota").val("");
		$('#btnPatitaGrande').removeClass("icon-button-active");
		$('#btnPatitaMediana').removeClass("icon-button-active");
		$('#btnPatitaChica').removeClass("icon-button-active");
		$('#btnTijeraChica').removeClass("icon-button-active");
		$('#btnTijeraGrande').removeClass("icon-button-active");
		$('#completartamanio').remove();
		$('#completarpelaje').remove();
		$('#completarfechanacimiento').remove();
		$('#completarnombreMascota').remove();
		$("#fechaNacimientoMascota").val("");
		$("#observacionesMascota").val("");	
}

/// VALIDO QUE LOS DATOS OBLIGATORIOS DEL CLIENTE ESTEN COMPLETOS ///
function validar(){
	var nombre, apellido, telefono,usuario,contrasenia,contraseniaRepetir,telefonoValido,dniValido,emailValido;
	
	var pass = $("#contrasenia").val();
	var pass2 = $("#contraseniaRepetir").val();
	
	usuario = false;
	if ($("#usuario").val() != "") {
		usuario = true;
	}
	contrasenia = false;
	if (pass != "") {
		contrasenia = true;	
	}
	contraseniaRepetir = false;
	if (pass2 != "") {
		contraseniaRepetir = true;
	}
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
		emailValido = validarEmail();
	}
	
	
	
	
	if(usuario){
		if(contrasenia){
			if(contraseniaRepetir){
				if(pass==pass2){
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
						resaltarCampo("nombre","Nombre");
					}
				} else {
					$("#completarcontrasenia").remove();
					$("#completarcontraseniaRepetir").remove();
					$("#contraseniaGroup").addClass("has-error");
					$("#contraseniaRepetirGroup").addClass("has-error");
					
					$("<small class='form-text text-muted text-danger' id='completarcontrasenia'>Las contrase&ntilde;as ingresadas no coinciden</small>").insertAfter("#contrasenia");
					}
			}else {
				resaltarCampo("contraseniaRepetir","Repetir Contrase&ntilde;a")
				}
		}else {
			resaltarCampo("contrasenia","Contrase&ntilde;a");
			}
	} else {
		resaltarCampo("usuario","Usuario");
		}
	$("#usuario").change(function(){

		$('#usuarioGroup').removeClass("has-error");
		$("#completarusuario").remove();
		
	})
	$("#contrasenia").change(function(){

		$('#contraseniaGroup').removeClass("has-error");
		$("#completarcontrasenia").remove();
		
	})
	$("#contraseniaRepetir").change(function(){

		$('#contraseniaRepetirGroup').removeClass("has-error");
		$("#completarcontraseniaRepetir").remove();
		
	})
	$("#nombre").change(function(){

		$('#nombreGroup').removeClass("has-error");
		$("#completarnombre").remove();
		
	})

	$("#apellido").change(function(){

		$('#apellidoGroup').removeClass("has-error");
		$("#completarapellido").remove();
		
	})
	$("#dni").change(function(){

		$('#dniGroup').removeClass("has-error");
		$("#completardni").remove();
		
	})
	
	$("#telefono").change(function(){

		$('#telefonoGroup').removeClass("has-error");
		$("#completartelefono").remove();
		
	})
	
} 

$(document).ready(function() {
	
			$("#email").change(function(){
				$('#completaremail').remove();
				validarEmail();
			})			
			
			$(this).on("click", ".btnModificarMascota", function(e){
			    e.preventDefault();
			    //OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
				var fila =$(this).parent().parent().parent()
				
		
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
				
				$('.nombreMascota').val(nombreMascota);
				$('#fechaNacimientoMascota').val(fechaNacimiento);
				$('#observacionesMascota').val(observacion);
				$('.nombreBoton').text("Modificar");
				$('#btnAgregarMascota').click();
				
				
				
				
			});	
			//PARA QUE QUEDE SELECCIONADO UN TAMANIO Y UN PELAJE////
			$(this).on("click","#btnPatitaGrande",function(e){
				e.preventDefault();
				$('#patitaGroup').removeClass("con-error");
				$('#completartamanio').remove();
				$('#btnPatitaGrande').addClass("icon-button-active");
				$('#btnPatitaMediana').removeClass("icon-button-active");
				$('#btnPatitaChica').removeClass("icon-button-active");
				
			})
			
			$(this).on("click","#btnPatitaMediana",function(e){
				e.preventDefault();
				$('#patitaGroup').removeClass("con-error");
				$('#completartamanio').remove();
				$('#btnPatitaGrande').removeClass("icon-button-active");
				$('#btnPatitaMediana').addClass("icon-button-active");
				$('#btnPatitaChica').removeClass("icon-button-active");
				
			})
			
			$(this).on("click","#btnPatitaChica",function(e){
				e.preventDefault();
				$('#patitaGroup').removeClass("con-error");
				$('#completartamanio').remove();
				$('#btnPatitaGrande').removeClass("icon-button-active");
				$('#btnPatitaMediana').removeClass("icon-button-active");
				$('#btnPatitaChica').addClass("icon-button-active");
				
			})
			
			$(this).on("click","#btnTijeraGrande",function(e){
				e.preventDefault();
				$('#pelajeGroup').removeClass("con-error");
				$('#completarpelaje').remove();
				$('#btnTijeraGrande').addClass("icon-button-active");
				$('#btnTijeraChica').removeClass("icon-button-active");	
			})
			
			$(this).on("click","#btnTijeraChica",function(e){
				e.preventDefault();
				$('#pelajeGroup').removeClass("con-error");
				$('#completarpelaje').remove();
				$('#btnTijeraGrande').removeClass("icon-button-active");
				$('#btnTijeraChica').addClass("icon-button-active");
			
			})
			
			$(this).on("click", ".btnQuitarMascota", function(e){
			    e.preventDefault();
			    //OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
				var fila =$(this).parent().parent().parent()
				var cantFilas = $("#tableMas tr").length; //OBTENGO LA CANTIDAD DE FILAS DE LA TABLA 
				
				if (cantFilas != 1){
					$('#tablaMascota').addClass("hidden");
				}
		
				fila.remove();
				
			});	
	
	$(this).on("click", "#nombreMascotaGroup", function(e){
	    $('#nombreMascotaGroup').removeClass("has-error"); 
	    $('#completarnombreMascota').remove();
	})
	$(this).on("click", "#fechaNacimientoMascotaGroup", function(e){
	    $('#fechaNacimientoMascotaGroup').removeClass("has-error"); 
	    $('#completarfechanacimiento').remove();
	})
	
	
	/// AGREGAR MASCOTAS ///
	$('#btnAgregarMascotaModal').on("click",function(e){
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
	    			 
	    			 }else {$("<small class='form-text text-muted text-danger' id='completarfechanacimiento'>Debes ingresar la fecha de nacimiento</small>").insertAfter("#fechaNacimientoMascota");
	    			 	$('#fechaNacimientoMascotaGroup').addClass("has-error");}
	    			 
	    		 }else{$("<small class='form-text text-muted text-danger' id='completarpelaje'>Debes seleccionar un largo de pelaje</small>").insertAfter("#pelajeGroup");
	    		 $('#pelajeGroup').addClass("con-error");}
	    	
	    	}else{$("<small class='form-text text-muted text-danger' id='completartamanio'>Debes seleccionar un tama&ntilde;o</small>").insertAfter("#patitaGroup");
	    	$('#patitaGroup').addClass("con-error");}
			
		}else {resaltarCampo("nombreMascota","Nombre de la Mascota")}
			
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
			
	        
	        $('<tr>',{
				'html' : "<td id='nombreMascota'>"+nombre+"</td>" +
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
	        
	        
			$('#agregarMascota').modal('toggle');
	        $('#tablaMascota').removeClass("hidden");
	        limpiarCamposModal();
			}	        
		})
		
	/// ALTA DE CLIENTE ///
	$('#btnAltaClienteOnline').click(function(e){
		e.preventDefault();
		$('#completaremail').remove();
		var resultado = validar();
		var arregloMascotas = [];
		
		var filas = $("#tableMas tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		var cantidad = filas.length; 
		if (filas.length != 1){
			$.each(filas,function(i,fila) {
				//OBTENGO DE CADA MASCOTA NOMBRE TAMANIO PELAJE FECHA DE NACIMIENTO
				if(i>0){
					var nombreMascota = fila.cells[0].innerHTML;
					var tamanioMascota = fila.cells[1].innerHTML;
					var pelajeMascota = fila.cells[2].innerHTML;
					var fechaNacimientoMascota = fila.cells[3].innerHTML;
					var observacionesMascota = fila.cells[5].innerHTML;
					var elementoMascota = {nombreMascota,tamanioMascota,pelajeMascota,fechaNacimientoMascota,observacionesMascota};
					arregloMascotas.push(elementoMascota); //AGREGO EL ELEMENTO Y SU CANTIDAD AL ARREGLO DE ELEMENTOS
				}
				
			})		
		}
		
		if(resultado)
		{
			var usuario = $("#usuario").val();
			var password = $("#contrasenia").val();
			var nombre = $("#nombre").val();
			var apellido = $("#apellido").val();
			var dni = $("#dni").val();
			var direccion = $("#direccion").val();
			var telefono = $("#telefono").val();
			var email = $("#email").val();
			var habilitado = 1;
			
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
						if (data == 1)
						{
							
							$("#btnHidden").click();
						
							limpiarCampos();
							setTimeout("$(location).attr('href','PrimerIngreso');",3500);
							
						} 
						else
						{
							alert("No se pudo agregar el cliente.");
						}
	                    
					}
			})
		}
	})
	
	/// ALTA DE CLIENTE ///
	$('#btnModificarUsuario').click(function(e){
		e.preventDefault();
		$('#completaremail').remove();
		var resultado = validar();
		var arregloMascotas = [];
		
		var filas = $("#tableMas tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		var cantidad = filas.length; 
		if (filas.length != 1){
			$.each(filas,function(i,fila) {
				//OBTENGO DE CADA MASCOTA NOMBRE TAMANIO PELAJE FECHA DE NACIMIENTO
				if(i>0){
					var nombreMascota = fila.cells[0].innerHTML;
					var tamanioMascota = fila.cells[1].innerHTML;
					var pelajeMascota = fila.cells[2].innerHTML;
					var fechaNacimientoMascota = fila.cells[3].innerHTML;
					var observacionesMascota = fila.cells[5].innerHTML;
					var elementoMascota = {nombreMascota,tamanioMascota,pelajeMascota,fechaNacimientoMascota,observacionesMascota};
					arregloMascotas.push(elementoMascota); //AGREGO EL ELEMENTO Y SU CANTIDAD AL ARREGLO DE ELEMENTOS
				}
				
			})		
		}
		
		if(resultado)
		{
			var usuario = $("#usuario").val();
			var password = $("#contrasenia").val();
			var nombre = $("#nombre").val();
			var apellido = $("#apellido").val();
			var dni = $("#dni").val();
			var direccion = $("#direccion").val();
			var telefono = $("#telefono").val();
			var email = $("#email").val();
			var habilitado = 1;
			
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
					url : "ConfirmarModificacionUsuario",
					type : "post",
					data : {jsonData : parametros},
					success : function(data){
						if (data == 1)
						{
							
							$("#btnHidden").click();
						
							limpiarCampos();
							setTimeout("$(location).attr('href','VentaOnline');",3500);
							
						} 
						else
						{
							alert("No se pudo modificar el cliente.");
						}
	                    
					}
			})
		}
	})
	
	
	
	
	
	

});
	