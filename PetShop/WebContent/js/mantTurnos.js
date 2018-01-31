/**
 * 
 */

function filtrarTabla(){
	
	var dispositivo="";
	if (screen.width < 768){
		dispositivo="smartphone"
	}else{dispositivo = "pc"}
	;
	switch (dispositivo){
	
	case "smartphone":
		{
		var fechaDesde = $('#filtrarFechaDesdexs').val();
		var fechaHasta = $('#filtrarFechaHastaxs').val();
		var estado = $('#filtrarEstadoxs').val();
		var apellido = $('#filtrarApellidoxs').val();
		var mascota = $('#filtrarMascotaxs').val();
		var direccion = $('#filtrarDireccionxs').val();
		var conTransporte = $('#filtrarConTransportexs').val();
		var idTipoServicio = $('#filtrarTipoServicioxs').val();
		break;
		}
	case "pc":
		{
		var fechaDesde = $('#filtrarFechaDesde').val();
		var fechaHasta = $('#filtrarFechaHasta').val();
		var estado = $('#filtrarEstado').val();
		var apellido = $('#filtrarApellido').val();
		var mascota = $('#filtrarMascota').val();
		var direccion = $('#filtrarDireccion').val();
		var conTransporte = $('#filtrarConTransporte').val();
		var idServicio = $('#filtrarServicio').val();
		break;
		}
	}

	var parametro = {
					fechaDesde : fechaDesde,
					fechaHasta : fechaHasta,
					estado : estado,
					apellido : apellido,
					mascota : mascota,
					direccion : direccion,
					conTransporte : conTransporte,
					idServicio : idServicio
					};
	
	$('#tabla > tbody').html("");//ELIMINO LAS FILAS DE LA TABLA QUE EXISTE EN ESTE MOMENTO
	
	$.post("FiltraTurnos",$.param(parametro),function(responseJson){
		$.each(responseJson,function(index, turnos){
			var transporte = "";
			var botones ="";
			if (turnos.retiroDom){
				transporte= "Si";
			}else{
				transporte ="No";
			}
			if(turnos.estado!="Pendiente"){
				botones="hidden";
			}
			$('<tr>',{
				'html' : " 	<td id='idTurno' class='hidden'>"+turnos.idTurno+"</td>" +
				"			<td id='fecha'>"+turnos.fecha+"</td>" +
				"			<td id='estado'>"+turnos.estado+"</td>" +
				"			<td id='cliente'>"+turnos.mascota.usuario.apellido+"</td>" +
				"			<td id='mascota'>"+turnos.mascota.nombre+"</td>" +
				"			<td>"+turnos.mascota.usuario.direccion+"</td>" +
				"			<td>"+transporte+"</td>" +
				"			<td>"+turnos.servicio.tipo+"</td>" +
				"			<td class='col-sm-3 col-lg-2'>" +
				"				<div class='input-group'>" +
				"					<a class='btn btn-danger btnCancelarTurno "+botones+"' href='\'>Cancelar</a>" +
				"					<a class='btn btn-primary "+botones+"' id='btnModificarTurno'  href='ModificarTurno?id="+turnos.idTurno+"'>Modificar</a>" +
				"				</div>" +
				"			</td>"
				}).appendTo("table > tbody");
			})
		})
	}
function eliminarFila(e){
	e.preventDefault();  //detiene la accion del boton (VIDEO 10)
	
	var fecha = $(this).parent().parent().parent().find('#fecha').text();
	var fecha = $(this).parent().parent().parent().find('#mascota').text();
	var opcion = confirm("Seguro quer\u00e9s eliminar el turno de fecha "+fecha+" para la mascota "+mascota+"?");
	/*if (opcion){
		var fila =$(this).parent().parent().parent()
		var idProducto = fila.find('#idProducto').text();//captura el idproducto dentro de la estructura de la pagina
		alert(idProducto);
		
		var data={idProducto : idProducto};
		$.post("EliminarProducto",data,function(res,est,jqXHR){ //Llama al servlet, le pasa data y ejecuta una funcion con un resultado, un estado y un ....
			//alert(res);    //Muestra la respuesta de ejecutar EliminarProducto
			fila.remove();
			})
		}*/
	
	}
$(document).ready(function() {
//VALIDACION EN DISPOSITIVOS DE ESCRITORIO

	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarFechaDesde').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarFechaHasta').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarEstado').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarApellido').change(function(e){
		
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarMascota').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarDireccion').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarConTransporte').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarServicio').change(function(e){
		filtrarTabla();
	});
	
									//VALIDACION EN DISPOSITIVOS MOVILES
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarFechaDesdexs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarFechaHastaxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarEstadoxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarApellidoxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarMascotaxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarDireccionxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarConTransportexs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarServicioxs').change(function(e){
		filtrarTabla();
	});
	
	
	$(document).on('click','.btnCancelarTurno',function(e){
		e.preventDefault();  //detiene la accion del boton (VIDEO 10)
		
		var fecha = $(this).parent().parent().parent().find('#fecha').text();
		var mascota = $(this).parent().parent().parent().find('#mascota').text();
		var opcion = confirm("Seguro quer\u00e9s eliminar el turno de fecha "+fecha+" para la mascota "+mascota+"?");
		if (opcion){
			var fila =$(this).parent().parent().parent()
			var idTurno = fila.find('#idTurno').text(); 		//captura el idturno dentro de la estructura de la pagina
			var parametro = {idTurno : idTurno};	
			
			$.post("CancelarTurno",$.param(parametro),function(responseJson){
				if($('#'))
				filtrarTabla("pc");
			});
		}
	});
	
})