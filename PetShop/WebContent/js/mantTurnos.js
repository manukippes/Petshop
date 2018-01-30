/**
 * 
 */

function filtrarTabla(dispositivo){
	
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
			
			if (turnos.retiroDom){
				transporte= "Si";
			}else{
				transporte ="No";
			}
			
			$('<tr>',{
				'html' : "  <td id='fecha'>"+turnos.fecha+"</td>" +
				"			<td id='estado'>"+turnos.estado+"</td>" +
				"			<td id='idCliente'>"+turnos.mascota.usuario.apellido+"</td>" +
				"			<td id='idMascota'>"+turnos.mascota.nombre+"</td>" +
				"			<td>"+turnos.mascota.usuario.direccion+"</td>" +
				"			<td>"+transporte+"</td>" +
				"			<td>"+turnos.servicio.tipo+"</td>" +
				"			<td class='col-sm-3 col-lg-2'>" +
				"				<div class='input-group'>" +
				"					<a class='btn btn-danger' id='btnCancelarTurno' href='\'>Cancelar</a>" +
				"					<a class='btn btn-primary' id='btnModificarTurno'  href='ModificarTurno?id="+turnos.idTurno+"'>Modificar</a>" +
				"				</div>" +
				"			</td>"
				}).appendTo("table > tbody");
			})
		})
	}
function eliminarFila(e){
	e.preventDefault();  //detiene la accion del boton (VIDEO 10)
	
	var nombreProducto = $(this).parent().parent().parent().find('#nombreProducto').text()+" "+$(this).parent().parent().parent().find('#presentacionProducto').text();
	var opcion = confirm("Seguro quer\u00e9s eliminar el producto "+nombreProducto+" ?");
	if (opcion){
		var fila =$(this).parent().parent().parent()
		var idProducto = fila.find('#idProducto').text();//captura el idproducto dentro de la estructura de la pagina
		alert(idProducto);
		
		var data={idProducto : idProducto};
		$.post("EliminarProducto",data,function(res,est,jqXHR){ //Llama al servlet, le pasa data y ejecuta una funcion con un resultado, un estado y un ....
			//alert(res);    //Muestra la respuesta de ejecutar EliminarProducto
			fila.remove();
			})
		}
	
	}
$(document).ready(function() {
//VALIDACION EN DISPOSITIVOS DE ESCRITORIO

	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarFechaDesde').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarFechaHasta').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarEstado').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarApellido').change(function(e){
		
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarMascota').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarDireccion').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarConTransporte').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarServicio').change(function(e){
		filtrarTabla("pc");
	});
	
									//VALIDACION EN DISPOSITIVOS MOVILES
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarFechaDesdexs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarFechaHastaxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarEstadoxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarApellidoxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarMascotaxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarDireccionxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarConTransportexs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarServicioxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
})