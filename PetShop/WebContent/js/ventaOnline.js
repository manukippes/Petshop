/**
 * 
 */

function calcularSubtotal(){
	
	var subtotal = 0.0;
	var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
	
	$.each(filas,function(i,fila){
		if(i>0){
			//OBTENGO DE CADA ARTICULO EL ID Y LA CANTIDAD A COMPRAR
			var row = $(this).closest('tr');
			var cantidad = row.find('#scrollCantidadProducto').val();
			var precio = row.find("#precioProducto").text();
			subtotal += (parseFloat(precio)*parseFloat(cantidad));
			}
	})
	$("#subtotal").val(subtotal.toFixed(2));
}

function agregarProductoVenta(idProducto,cantidad){
	
	//VERIFICO QUE EL PRODUCTO NO ESTE EN EL CARRITO
	var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
	var bandera = false;
	//RECORRO LA TABLA 	VERIFICANDO QUE NO ESTE AGREGADO EL PRODUCTO
	$.each(filas,function(i,fila){
		if (i>0){
			if (fila.cells[1].innerHTML==idProducto){
				bandera = true;
				alertError("Este producto ya est\u00E1 en tu carrito de compras");
			}
		}			
	})
	
	if(!bandera){
		//AGREGA EL PRODUCTO A LA VENTA SI SE INGRESO UNA CANTIDAD
		if (cantidad.length != 0){ 
			//AGREGO EL PRODUCTO A LA SESION
	
			$.ajax({
				type : "post",
				url : "agregarAlCarrito",
				data : {idProducto : idProducto,
						cantidad : cantidad
						},
				success : function(respuesta){
					$(location).attr('href',"VentaOnline");					//RECARGO LA PAGINA PARA ACTUALIZAR EL CARRITO
				}	
			})
		}else{
			alertError("Para agregar un producto debes ingresar la cantidad");
		}
	}
	
}

function buscarProductosVenta(inputProducto){

	var parametro = {
					inputProducto : inputProducto,		
					};
	$('#tabla > tbody').html("");//ELIMINO LAS FILAS DE LA TABLA QUE EXISTE EN ESTE MOMENTO
	
	$.post("buscaProductosVenta",$.param(parametro),function(responseJson){
		$.each(responseJson,function(index, productos){
			$('<tr>',{
				'html' : "	<td id='imagen'>" +
				"				<div class='img-hover'>" +
				"					<a href='"+productos.imagen+"' class='preview' title='"+productos.nombre+" "+productos.presentacion+"'><img class='img-thumbnail' src='"+productos.imagen+"' width='50px' height='50px'/></a>" +
				"				</div>" +
				"			</td>" +
				"			<td class='hidden' id='idProducto'>"+productos.idProducto+"</td>" +
				"			<td id='nombreProducto'>"+productos.nombre+"</td>" +
				"			<td id='presentacionProducto'>"+productos.presentacion+"</td>" +
				"			<td id='precioProducto'>"+productos.precio+"</td>" +
				"			<td>" +
				"				<input id='cantidad' type='number' class='form-control cantidad' min='0' max="+productos.stock+"></input>" +
				"			</td>" +
				"			<td class='col-sm-3 col-lg-2'>" +
				"				<div class='input-group'>" +
				"					<a class='btn btn-info btnAgregarProductoVenta' href='\'>Agregar</a>" +
				"				</div>" +
				"			</td>"
				}).appendTo("#tabla > tbody");
			
			})
		})
	}
//////////////////////////












////////////////
$(document).ready(function() {
	

	//DETECTO CLICK EN INPUT DE FILTRAR POR NOMBRE
	$('#buscarProductosVenta').click(function(e){
		e.preventDefault();
		if($("#inputProducto").val()!=""){
			buscarProductosVenta($('#inputProducto').val());
			
		}else{
			$('#inputProductoGroup').addClass("has-error");
			$('#buscarVacio').removeClass("hidden");			
			}
	});
	
	//DETECTO CLICK EN BUSCAR PRODUCTO
	$('#inputProducto').click(function(e){
		$('#inputProductoGroup').removeClass("has-error");
		$('#buscarVacio').addClass("hidden");
	})
	
	//-----------------------------DETECTO CAMBIOS EN CANTIDAD AGREGADA EN EL CARRITO----------------------------------//
	$(document).on('click','#scrollCantidadProducto',function(e){					//HACIENDO CLICK
		$(this).parent().removeClass("has-error");
		
	})
	
	$(document).on('change','#scrollCantidadProducto',function(e){					//CAMBIOS EN GENERAL
		
		var fila = $(this).parent().parent().parent();		
		
		var idProducto = fila.find('#idProducto').text();
		var cantidad = fila.find('#scrollCantidadProducto').val();
		
		if(cantidad==0){
			$('#cantidadProductoGroup').addClass("has-error");
			$("<small class='form-text text-muted text-danger' id='errorCantidad'>Valor Inv&aacute;lido</small>").insertAfter("#cantidadProductoGroup");
		}else{
			$('#errorCantidad').remove();
			//AGREGO EL PRODUCTO A LA SESION
			$.ajax({
				type : "post",
				url : "agregarAlCarrito",
				data : {idProducto : idProducto,
						cantidad : cantidad
						},
				success : function(respuesta){
					//alertError(respuesta);				
				}	
			})
			calcularSubtotal();
		}
		
		
	})
	
	//------------------------DETECTO EL CLICK EN AGREGAR DE LA FILA DE UN PRODUCTO------------------------------//
	$(document).on('click','.btnAgregarProductoVenta',function(e){
		e.preventDefault();
		
		var fila =$(this).parent().parent().parent()
		
		var idAgregar = fila.find('#idProducto').text();
		var nombreAgregar = fila.find('#nombreProducto').text();
		var presentacionAgregar = fila.find('#presentacionProducto').text();
		var cantidadAgregar = fila.find('.cantidad').val();
		//var precioAgregar = fila.find('#precioProducto').text();
		//var imagenAgregar = fila.find("#imagen").html();
		var cantidadMaxima = fila.find('.cantidad').attr('max');
		
		if (cantidadAgregar!=""){
			if (parseInt(cantidadAgregar) <= parseInt(cantidadMaxima)){ //VALIDO QUE NO SE EXCEDA DEL STOCK DISPONIBLE
				if(cantidadAgregar>0){		//VALIDO QUE LA CANTIDAD A AGREGAR NO SEA 0
					agregarProductoVenta(idAgregar,cantidadAgregar);
				}else{
					alertError("La cantidad ingresada debe ser mayor a 0 unidades")
				}
			}else{
				alertError("El stock disponible de "+nombreAgregar+", "+presentacionAgregar+" es: "+cantidadMaxima+" unidades. Seleccion\u00e1 una cantidad menor.");
			}
		}else {alertError("Para agregar un producto debes ingresar la cantidad")}
	});
	
	
	//-------------------------------------CAPTURO EL CLICK EN QUITAR PRODUCTO DE LA VENTA-----------------------------------//
	
	$(document).on('click','.btnEliminarProductoVenta',function(e){
		e.preventDefault();
		
		//OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
		var fila = $(this).parent().parent().parent();		
		
		var idProducto = fila.find('#idProducto').text();
		
		//QUITO EL PRODUCTO DE LA SESION

		$.ajax({
			type : "post",
			url : "QuitarDelCarrito",
			data : {idProducto : idProducto
					},
			success : function(respuesta){
				//alertError(respuesta);
				//$(location).attr('href',"VentaOnlinePaso2");
				alert("se elimino");
			}	
		})
		
		//QUITO EL PRODUCTO DE LA TABLA SIN RECARGAR LA PAGINA
		fila.remove();
		
		calcularSubtotal();
		$("#articulosCarrito").text(parseInt($("#articulosCarrito").text())-1);
		
		var cantFilas = ($(".tablaVentaActual tr")).length;
		
		if (cantFilas == 1){
			
			$('#carritoVacio').removeClass("hidden");
			$('#subtotalGroup').addClass("hidden");
			$('#tablaVentaActual').addClass("hidden");
		}
		});
	
	//-----------------------------------CAPTURO EL CLICK EN CONTINUAR (VENTA PASO 1)------------------------------//
	$(document).on('click','#btnContinuar',function(e){
		e.preventDefault();
		
		var validaCantidad = true;
		var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		if (filas.length == 1){
			alertError("No seleccionaste ningun producto");
		}else{
			var arregloProductos = [];
			$.each(filas,function(i,fila){
				if(i>0){
					//OBTENGO DE CADA ARTICULO EL ID Y LA CANTIDAD A COMPRAR
					var row = $(this).closest('tr');
					var idProducto = row.find("#idProducto").text();
					var cantidad = row.find(".cantidadProducto").val();
					var stockDisponible = row.find(".cantidadProducto").attr("max");
					if (cantidad > stockDisponible){
						validaCantidad = false;
						row.find("#cantidadProductoGroup").addClass("has-error");
					}else{
						var elemento = {idProducto,cantidad};
						arregloProductos.push(elemento); //AGREGO EL ELEMENTO Y SU CANTIDAD AL ARREGLO DE ELEMENTOS
					}
					
					}
				})
			var parametro = JSON.stringify(arregloProductos);
			if(validaCantidad){
				$.ajax({
					type : "post",
					url : "CargarProductosVenta",
					data : {jsonData : parametro},
					success : function(respuesta){
						//alertError(respuesta);
						$(location).attr('href',"VentaOnlinePaso2");
					}	
				})
			} else {
				alertError("Se ha ingresado una cantidad superior al stock disponible");
			}
			
		}
	})
		 
	//CAPTURO EL CAMBIO DEL COMBO DE MEDIO DE PAGO
		 
	$("select[name=medioPago]").change(function(){
		
		var idMedioPago = $('#medioPago').val();
		
		switch(idMedioPago){
		
		case "seleccione un medio":
			$('#tarjeta').prop('disabled',true);
			$('#cuotasGroup').hide();
			$('#tarjetaGroup').hide();
			$('#datosTarjeta').hide();
			break;
		case "1":
			//Codigo 1 es efectivo
			$('#tarjeta').prop('disabled',true);
			$('#tarjetaGroup').hide();
			$('#cuotasGroup').hide();
			$('#datosTarjeta').hide();
			
			break;
		case "2":
			//Codigo 2 es Debito
			$('#tarjetaGroup').removeClass("hidden");
			$('#tarjetaGroup').show();
			$('#cuotasGroup').hide();
			$('#datosTarjeta').show();
			$('#datosTarjeta').removeClass("hidden");
			$('#tarjeta').prop('disabled',false);
			
			var parametro = {idMedioPago : idMedioPago};
			$.post("ComboTarjetas",$.param(parametro),function(responseJson){
				$('#tarjeta').empty();
				$('#tarjeta').append($('<option value="tarjeta">Seleccion&aacute; una tarjeta</option>'));
				$.each(responseJson,function(index, tarjeta){
					$('#tarjeta').append($('<option value="'+tarjeta.idTarjeta+'">'+tarjeta.nombre+'</option>'));
				});
			});	
			break;
		case "3":
			//Codigo 3 es Credito
			$('#tarjetaGroup').removeClass("hidden");
			$('#cuotasGroup').removeClass("hidden");
			$('#tarjetaGroup').show();
			$('#cuotasGroup').show();
			$('#tarjeta').prop('disabled',false);
			$('#datosTarjeta').removeClass("hidden");
			$('#datosTarjeta').show();
			
			var parametro = {idMedioPago : idMedioPago};
			$.post("ComboTarjetas",$.param(parametro),function(responseJson){
				$('#tarjeta').empty();
				$('#tarjeta').append($('<option value="tarjeta">Seleccion&aacute; una tarjeta</option>'));
				$.each(responseJson,function(index, tarjeta){
					$('#tarjeta').append($('<option value="'+tarjeta.idTarjeta+'">'+tarjeta.nombre+'</option>'));
				});
			});	
			break;		
		}
	});
	//CAPTURO LA TARJETA DE CREDITO QUE SE SELECCIONO
	$("select[name=tarjeta]").change(function(){
		
		var idMedioPago = $('#medioPago').val();
		var idTarjeta = $('#tarjeta').val();
		if (idMedioPago == 3){
			if(idTarjeta!="tarjeta"){
				$('#cuotas').prop('disabled',false);
				var parametro = {idTarjeta : idTarjeta};
				$.post("ComboCuotas",$.param(parametro),function(responseJson){
					$('#cuotas').empty();
					$('#cuotas').append($('<option value="cuotas">Nro. de cuotas</option>'));
					$.each(responseJson,function(index, cuotas){
						var interes = parseFloat(cuotas.recargo) *100;
						$('#cuotas').append($('<option value="'+cuotas.idCuota+'">'+cuotas.cantCuotas+' - Interes: '+interes+'%</option>'));
					});
				});	
			}else{
				$('#cuotas').prop('disabled',true);
			}
			
		}
	})	
	
	
	//COMBO CATEGORIA DE PANEL MODAL DE LISTADO DE PRODUCTOS
	$(this).on("change", "#categoria", function(e){
	    e.preventDefault();
	    var idCategoria = $('#categoria').val();
		var parametro = {idCategoria : idCategoria };
		if(idCategoria!="categoria"){
			
			$.post("ComboSubcategoria",$.param(parametro),function(responseJson){
				$('#subcategoria').empty();
				$('#subcategoria').append($('<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>'));
				$('#subcategoria').prop("disabled",false);
				$.each(responseJson,function(index, subcat){
					$('#subcategoria').append($('<option value="'+subcat.idSubcategoria+'">'+subcat.nombre+'</option>'));
				});
			});	
		}else{
			$('#subcategoria').attr('disabled',true);
			$('#subcategoria').empty();
			$('#subcategoria').append($('<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>'));
		}    	
	}); 
	
	$(this).on("change", "#subcategoria", function(e){
	    e.preventDefault();
	    var idSubcategoria = $('#subcategoria').val();
	    if ($('#soloStock').is(":checked")){
	    	var soloStock = true;
	    }else{
	    	var soloStock = false;
	    }
		var parametro = {idSubcategoria : idSubcategoria,
						soloStock : soloStock
						};
		$.post("ProductosSubcategoria",$.param(parametro),function(responseJson){
			$('#tablaAgregarProducto > tbody').html("");
			$.each(responseJson,function(index, productos){
				$('<tr>',{
					'html' : "	<td id='imagen'>" +
					"				<div class='img-hover'>" +
					"					<a href='"+productos.imagen+"' class='preview' title='"+productos.nombre+" "+productos.presentacion+"'><img class='img-thumbnail' src='"+productos.imagen+"' width='50px' height='50px'/></a>" +
					"				</div>" +
					"			</td>" +
					"			<td class='hidden' id='idProducto'>"+productos.idProducto+"</td>" +
					"			<td id='nombreProducto'>"+productos.nombre+"</td>" +
					"			<td id='presentacionProducto'>"+productos.presentacion+"</td>" +
					"			<td id='precioProducto'>"+productos.precio+"</td>" +
					"			<td>" +
					"				<input id='cantidad' type='number' class='form-control cantidad' min='0' max="+productos.stock+"></input>" +
					"			</td>" +
					"			<td class='col-sm-3 col-lg-2'>" +
					"				<div class='input-group'>" +
					"					<a class='btn btn-info btnAgregarProductoVenta' href='\'>Agregar</a>" +
					"				</div>" +
					"			</td>"
					}).appendTo("#tablaAgregarProducto > tbody");
				
			})
		})
	})
	$(this).on("change", "#soloStock", function(e){
	    e.preventDefault();
	    
	    var idSubcategoria = $('#subcategoria').val();
	    if(idSubcategoria!="subcategoria"){
	    	 if ($('#soloStock').is(":checked")){
	 	    	var soloStock = true;
	 	    }else{
	 	    	var soloStock = false;
	 	    }
	 		var parametro = {idSubcategoria : idSubcategoria,
	 						soloStock : soloStock
	 						};
	 		$.post("ProductosSubcategoria",$.param(parametro),function(responseJson){
	 			$('#tablaAgregarProducto > tbody').html("");
	 			$.each(responseJson,function(index, productos){
	 				$('<tr>',{
	 					'html' : "<td id='idProducto'>"+productos.idProducto+"</td>" +
	 					"			<td id='nombreProducto'>"+productos.nombre+"</td>" +
	 					"			<td id='presentacionProducto'>"+productos.presentacion+"</td>" +
	 					"			<td id='precioProducto'>"+productos.precio+"</td>" +
	 					"			<td>" +
	 					"				<input id='cantidad' type='number' class='form-control' min='0' max="+productos.stock+"></input>" +
	 					"			</td>" +
	 					"			<td class='col-sm-3 col-lg-2'>" +
	 					"				<div class='input-group'>" +
	 					"					<a class='btn btn-info btnAgregarProductoVenta' href='\'><span class='glyphicon glyphicon-plus'></span> Agregar</a>" +
	 					"				</div>" +
	 					"			</td>"
	 					}).appendTo('#tablaAgregarProducto > tbody');
	 			})
	 		})
	    }
	})

	
	$(document).on("click", "#confirmarVenta", function(e){
	    e.preventDefault();

	    var medioPagoValido = false;
		var tarjeta ="0";
		var cuotas ="0";
		var medioPago ="0";
		
		//VALIDACION DE MEDIO DE PAGO
		if(!($('#medioPago').val()=="seleccione un medio")){		
			medioPago = $('#medioPago').val();	//GUARDO EL MEDIO DE PAGO
			
			switch (medioPago){
			
			case "1":
				medioPagoValido = true;
				break;
			case "2":
			case "3":
				if($('#tarjeta').val()=="tarjeta"){
					alertError("Debes seleccionar una tarjeta");
				}else{
					tarjeta = $('#tarjeta').val();
					if (tarjeta==3){
						if ($('#cuotas').val() == "cuotas"){
							alertError("Debes seleccionar un plan de cuotas")
						}else{
							cuotas = $('#cuotas').val();
							medioPagoValido=true;
						}
					}else{
						medioPagoValido=true;
					}
					
				}
				break;
			}
		}else{alertError("Debes seleccionar un medio de pago para continuar");}
		if (cuotas == null){
			cuotas="0";
		}
		if(medioPagoValido){
			//VALIDACION DE USUARIO
			var idUsuario ="0";
			if (!($('#idUsuario').val()=="")){
				idUsuario = $('#idUsuario').val();
			};
			
			//RECUPERO OBSERVACIONES
			
			var observaciones = $('#observaciones').val();
			var parametro = {
							medioPago : medioPago,
							tarjeta : tarjeta,
							cuotas : cuotas,
							idUsuario : idUsuario,
							observaciones : observaciones
							}
			var parametros = JSON.stringify(parametro);
			//alertError(parametros);
			$.ajax({
				type : "post",
				url : "ProcesarVenta",
				data : {jsonData : parametros},
				success : function(respuesta){
					if (respuesta == 1){
						var opcion = alertDetiene("Venta cargada Exitosamente");
						if(opcion){
							$(location).attr('href','VentaOnline');
						}else{
							alertError("No se pudo mostrar el popUp");
						}
					}else{
						alertError("Error al cargar la venta");
					}
				}
			}); 
		}
	})
	
})
