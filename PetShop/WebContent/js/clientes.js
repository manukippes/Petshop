/**
 * 
 */


/// LIMPIAR CAMPOS UNA VEZ QUE SE AGREGO EL CLIENTE Y SU MASCOTA ///
function limpiarCampos(){
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
		$('#btnTijeraChica').removeClass("icon-button-active")
		$('#btnTijeraGrande').removeClass("icon-button-active")
		$("#fechaNacimientoMascota").val("");
		$("#observacionesMascota").val("");	
}

/// VALIDO QUE LOS DATOS OBLIGATORIOS DEL CLIENTE ESTEN COMPLETOS ///
function validar(){
	var nombre, apellido, telefono;
	
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
	
	if(isNaN($('#dni').val())){
		alert("Debe ingresar s&oacute;lo n&uacute;meros en el campo Telefono.");		
	}else if(isNaN($('#telefono').val())){
		alert("Debe ingresar s&oacute;lo n&uacute;meros en el campo Dni.");
	}
		
	if (nombre) {
		if (apellido) {
				if (telefono) {
					return true;
				}
				else{
					$("#telefonoGroup").addClass("has-error");
					$("#telefono").focus();
					if(!($("#completarTelefono").length)){
						$("<small class='form-text text-muted text-danger' id='completarTelefono'>Debe completar el campo: Tel&eacute;fono</small>").insertAfter("#telefono");
					}
				}
		}
		else{
			$("#apellidoGroup").addClass("has-error");
			$("#apellido").focus();
			if(!($("#completarApellido").length)){
				$("<small class='form-text text-muted text-danger' id='completarApellido'>Debe completar el campo: Apellido</small>").insertAfter("#apellido");
		    }
		}
	}
	else{
		$("#nombreGroup").addClass("has-error");
		$("#nombre").focus();
		if(!($("#completarNombre").length)){
			$("<small class='form-text text-muted text-danger' id='completarNombre'>Debe completar el campo: Nombre</small>").insertAfter("#nombre");
		}
	}
	
	$("#nombre").change(function(){

		$('#nombreGroup').removeClass("has-error");
		$("#completarNombre").remove();
		
	})

	$("#apellido").change(function(){

		$('#apellidoGroup').removeClass("has-error");
		$("#completarApellido").remove();
		
	})
	
	$("#telefono").change(function(){

		$('#telefonoGroup').removeClass("has-error");
		$("#completarTelefono").remove();
		
	})
	
} 

$(document).ready(function() {
	//if (($("tablaMascota").hasClass('hidden'))) {

			//PARA QUE QUEDE SELECCIONADO UN TAMANIO Y UN PELAJE////
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
	//}
			
			$(this).on("click", ".btnQuitarMascota", function(e){
	    e.preventDefault();
	    //OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
		var fila =$(this).parent().parent().parent()

		fila.remove();
		
		});
			
	
	
	
	$(this).on("click", "#nombreMascotaGroup", function(e){
	    $('#nombreMascotaGroup').removeClass("has-error"); 
	})
	$(this).on("click", "#fechaNacimientoMascotaGroup", function(e){
	    $('#fechaNacimientoMascotaGroup').removeClass("has-error"); 
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
	    			 
	    			 }else {alert("Para agregar primero debes ingresar la fecha de nacimiento");
	    			 	$('#fechaNacimientoMascotaGroup').addClass("has-error");}
	    			 
	    		 }else{alert("Para agregar primero debes seleccionar un pelaje de la mascota");
	    		 $('#pelajeGroup').addClass("con-error");}
	    	
	    	}else{alert("Para agregar primero debes seleccionar un tamaño de la mascota");
	    	$('#patitaGroup').addClass("con-error");}
			
		}else {alert("Para agregar primero debes ingresar un nombre");
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
			}	        
	       limpiarCamposModal();
		})
		
	/// ALTA DE CLIENTE ///
	$('#btnAgregarCliente').click(function(e){
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
		
		if(resultado){
			
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
				nombre : nombre,
				apellido : apellido,
				dni : dni,
				direccion : direccion,
				telefono : telefono,
				email : email,
				habilitado : habilitado,
				arregloMascotas : arregloMascotas	
			}
			
		}
		var parametros = JSON.stringify(parametro);
		limpiarCampos();
		
		$.ajax({
				url : "ConfirmarAltaCliente",
				type : "post",
				data : {jsonData : parametros},
				success : function(data){
					if (data == "true")
					{
						confirm("Se agreg&oacute; el cliente correctamente.");
					} 
					else
					{
						alert("No se pudo agregar el cliente.");
					}
                    
				}
		})
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
					if (data)
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
					if (data)
					{
						confirm("Se elimino el cliente.");
					} 
					else
					{
						alert("No se pudo eliminar el cliente.");
					}
                    
				}
	        })	   
		}
		
	})

});
	