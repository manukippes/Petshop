/**
 * 
 */

function agregarProductoVenta(idProducto,nombre,presentacion,precio,cantidad){
	
		var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		var bandera = false;
		//RECORRO LA TABLA 	VERIFICANDO QUE NO ESTE AGREGADO EL PRODUCTO
		$.each(filas,function(i,fila){
			if (i>0){
				if (fila.cells[0].innerHTML==idProducto){
					bandera=true;
					alertError("Este producto ya est&aacute; en la venta, para modificarlo debes quitarlo primero");
				}
			}			
		})
		
		if (!bandera){
			//AGREGA EL PRODUCTO A LA TABLA DE VENTA SI SE INGRESO UNA CANTIDAD
			if (cantidad.length != 0){
				$('<tr>',{
					'html' : "<td id='idProducto'>"+idProducto+"</td>" +
					"			<td id='nombreProducto'>"+nombre+"</td>" +
					"			<td id='presentacionProducto'>"+presentacion+"</td>" +
					"			<td id='precioProducto'>"+precio+"</td>" +
					"			<td id='cantidadProducto'>"+cantidad+"</td>" +
					"			<td class='col-sm-3 col-lg-2'>" +
					"				<div class='input-group'>" +
					"					<a class='btn btn-danger btnEliminarProductoVenta' href='\'>Quitar</a>" +
					"				</div>" +
					"			</td>"
					}).appendTo(".tablaVentaActual > tbody");
				
				
				var subtotal = parseFloat($("#subtotal").val());
				
				subtotal += (parseFloat(cantidad)*parseFloat(precio));
				
				$("#subtotal").val(subtotal.toFixed(2));

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
				'html' : "<td id='idProducto'>"+productos.idProducto+"</td>" +
				"			<td id='nombreProducto'>"+productos.nombre+"</td>" +
				"			<td id='presentacionProducto'>"+productos.presentacion+"</td>" +
				"			<td id='precioProducto'>"+productos.precio+"</td>" +
				"			<td>" +
				"				<input id='cantidad' type='number' class='form-control' min='0' max="+productos.stock+"></input>" +
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


////////////////
$(document).ready(function() {

	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#buscarProductosVenta').click(function(e){
		e.preventDefault();
		if($("#inputProducto").val()!=""){
			buscarProductosVenta($('#inputProducto').val());
			
		}else{
			$('#inputProductoGroup').addClass("has-error");
			$('#buscarVacio').removeClass("hidden");			
			}
	});
	
	$('#inputProducto').click(function(e){
		$('#inputProductoGroup').removeClass("has-error");
		$('#buscarVacio').addClass("hidden");
	})
	
	//DETECTO EL CLICK EN BUSCAR PRODUCTOS
	$(document).on('click','.btnAgregarProductoVenta',function(e){
		e.preventDefault();
		
		var fila =$(this).parent().parent().parent()
		
		
		var idAgregar = fila.find('#idProducto').text();
		var nombreAgregar = fila.find('#nombreProducto').text();
		var presentacionAgregar = fila.find('#presentacionProducto').text();
		var cantidadAgregar = fila.find('#cantidad').val();
		var precioAgregar = fila.find('#precioProducto').text();
		
		var cantidadMaxima = fila.find('#cantidad').attr('max');
		
		if (cantidadAgregar!=""){
			if (parseInt(cantidadAgregar) <= parseInt(cantidadMaxima)){ //VALIDO QUE NO SE EXCEDA DEL STOCK DISPONIBLE
				if(cantidadAgregar>0){		//VALIDO QUE LA CANTIDAD A AGREGAR NO SEA 0
					agregarProductoVenta(idAgregar,nombreAgregar,presentacionAgregar,precioAgregar,cantidadAgregar);
				}else{
					alertError("La cantidad ingresada debe ser mayor a 0 unidades")
				}
			}else{
				alertError("El stock disponible de "+nombreAgregar+", "+presentacionAgregar+" es: "+cantidadMaxima+" unidades. Seleccion\u00e1 una cantidad menor.");
			}
		}else {alertError("Para agregar un producto debes ingresar la cantidad")}
	});
	
	//CAPTURO EL CLICK EN QUITAR PRODUCTO DE LA VENTA
	$(document).on('click','.btnEliminarProductoVenta',function(e){
		e.preventDefault();
		
		//OBTENGO LA FILA DE LA CUAL ESTA EL BOTON QUITAR
		var fila =$(this).parent().parent().parent()
		//OBTENGO EL SUBTOTAL ACTUAL
		var subtotal = parseFloat($("#subtotal").val());
		
		//OBTENGO PRECIO Y CANTIDAD DEL PRODUCTO A BORRAR
		var precio = fila.find("#precioProducto").text();
		var cantidad = fila.find("#cantidadProducto").text();
		
		//RESTO AL SUBTOTAL EL IMPORTE CALCULADO
		
		subtotal -= (parseFloat(precio)*parseFloat(cantidad));
		
		$("#subtotal").val(subtotal.toFixed(2));
		fila.remove();
		
		});
	
	//CAPTURO EL CLICK EN CONTINUAR (VENTA PASO 1)
	$(document).on('click','#btnContinuar',function(e){
		e.preventDefault();
		
	
		var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		if (filas.length == 1){
			alertError("No seleccionaste ningun producto");
		}else{
			var arregloProductos = [];
			$.each(filas,function(i,fila){
				if(i>0){
					//OBTENGO DE CADA ARTICULO EL ID Y LA CANTIDAD A COMPRAR
					var idProducto = fila.cells[0].innerHTML;
					var cantidad = fila.cells[4].innerHTML;
					var elemento = {idProducto,cantidad};
					arregloProductos.push(elemento); //AGREGO EL ELEMENTO Y SU CANTIDAD AL ARREGLO DE ELEMENTOS
					}
				})
			var parametro = JSON.stringify(arregloProductos);
		
			$.ajax({
				type : "post",
				url : "CargarProductosVenta",
				data : {jsonData : parametro},
				success : function(respuesta){
					//alertError(respuesta);
					$(location).attr('href',"VentasPaso2");
					
				}
					
				})
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
			break;
		case "1":
			//Codigo 1 es efectivo
			$('#tarjeta').prop('disabled',true);
			$('#tarjetaGroup').hide();
			$('#cuotasGroup').hide();
			break;
		case "2":
			//Codigo 2 es Debito
			$('#tarjetaGroup').removeClass("hidden");
			$('#tarjetaGroup').show();
			$('#cuotasGroup').hide();
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
					$('#cuotas').append($('<option value="cuotas">Cantidad de cuotas</option>'));
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
							alertError("Debes seleccionar un plan de cuotas");
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
						  swal ( {
							  title : "Bien hecho!", 
							  text : "Venta cargada Exitosamente", 
							  icon : "success" , 
							  buttons: {
								    cancel: false,
								    confirm: true,
								  },
							} )
							
						 .then((willDelete) => {
							  if (willDelete) {
								  $(location).attr('href','Ventas');
							  } else {
								  alertError("No se pudo mostrar el popUp");
							  }
							});
					}else{
						alertError("Error al cargar la venta");
					}
				}
			}); 
		}
	})
})
