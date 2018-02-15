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
	var nombre, apellido, telefono,usuario,contrasenia,contraseniaRepetir,telefonoValido,dniValido;
	
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
	
	if(usuario){
		if(contrasenia){
			if(contraseniaRepetir){
				if(pass==pass2){
					if (nombre) {
						if (apellido) {
							if(dniValido){
								if (telefono) {
									if(telefonoValido){ return true; }
									
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
								} 
								else
								{
									$("<small class='form-text text-muted text-success' id='completaremail'>El mail ingresado es correcto</small>").insertAfter("#email");
								}  
							}					
				})
			    } else {
			    	$("<small class='form-text text-muted text-danger' id='completaremail'>El mail ingresado no tiene un formato v&aacute;lido</small>").insertAfter("#email");
			    }
			})			
			
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
				"					<a class='btn btn-danger btnQuitarMascota' href='\'>Quitar</a>" +
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
			limpiarCampos();
			
			$.ajax({
					url : "ConfirmarAltaCliente",
					type : "post",
					data : {jsonData : parametros},
					success : function(data){
						if (data == 1)
						{
							if(confirm("Se agreg&oacute; el cliente correctamente")!=""){
								$(location).attr('href','index.html');
							}
						} 
						else
						{
							prompt("No se pudo agregar el cliente.");
						}
	                    
					}
			})
		}
	})
	
	//CAPTURO EL CLICK DEL BOTON BUSCAR EN EL PANEL MODAL
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
	
	/// CLICK EN EL BOTON DE MODIFICAR CLIENTES ///
	$(this).on("click", "#btnVerCliente", function(e){
		e.preventDefault();
	    if($('#cliente').val() == "cliente"){
			alert("Para modificar el cliente primero debes seleccionarlo");
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
						alert("No es posible modificar el cliente.");
					}
                    
				}
	        })	  
		}
	})
	
	
	/// CLICK EN EL BOTON DE BORRAR CLIENTES ///
	$(this).on("click", "#btnBorrarCliente", function(e){
		e.preventDefault();
	    if($('#cliente').val() == "cliente"){
			alert("Para eliminar el cliente primero debes seleccionarlo");
		}else{
			var idCliente = $('#cliente').val();
	        
	        parametro = {idCliente : idCliente};
	        var parametros = JSON.stringify(parametro);
	        
	        $.ajax({
				url : "EliminarCliente",
				type : "post",
				data : {jsonData : parametros},
				success : function(data){
					if (data ==1)
					{
						confirm("Se elimin&oacute; el cliente.");
					} 
					else
					{
						alert("No se pudo eliminar el cliente.");
					}
                    
				}
	        })	   
		}
		
	})
	
	//VALIDAR EMAIL
	$(this).on("click", "#btnValidar", function(e){
	    e.preventDefault();
		$('#emailValido').remove();
		
		var email = $('#email').val();
		if(email!=""){
			presionoValidar = true;
			var parametro = {email: email}			
			var parametros = JSON.stringify(parametro);		
			 $.ajax({
					url : "ValidarMail",
					type : "post",
					data : {jsonData : parametros},
					success : function(data){
						if (data == 1)
						{
							valido = true;
							presionoValidar = true;
							$("<small class='form-text text-muted text-success' id='emailValido'> Direcci&oacute;n de email v&aacute;lida</small>").insertAfter("#btnValidar");
						} 
						else
						{
							presionoValidar = false;
							valido = false;
							$("<small class='form-text text-muted text-danger' id='emailValido'> La direcci&oacute;n de email ya se encuentra registrada</small>").insertAfter("#btnValidar");
						}
	                    
					}
		        })
		}else{
			presionoValidar = false;
			alert("Primero debe ingresar un email para validarlo.")
			}
	}); 

});
	