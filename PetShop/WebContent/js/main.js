/**
 * 
 */
											//-----------------------------------
											//FUNCIONES GENERALES A TODOS LOS JSP
											//-----------------------------------

function iniciar(parametro){
    		switch(parametro){
    		case 'administracion':
    			administracionTab.className += " active";
    			break;
    		case 'ventas':
    			ventasTab.className += " active";
    			break;
    		case 'clientes':
    			clientesTab.className += " active";
    			break;
    		case 'consultas':
    			consultasTab.className += " active";
    			break;
    		case 'turnos':
    			turnosTab.className += " active";
    			break;
    		default:
    			break;
    		}
    	
    	}
											//-----------------------------------------------
											//FUNCIONES DE MODULO ADMINISTRACION DE PRODUCTOS
											//-----------------------------------------------

function validarCampos(nombre, presentacion, precio,categoria,subcategoria){
	
	// REMUEVE LAS CLASES HAS-ERROR DE TODOS LOS INPUT
	document.getElementById('categoriaGroup').classList.remove("has-error");
	document.getElementById('subcategoriaGroup').classList.remove("has-error");
	document.getElementById('nombreGroup').classList.remove("has-error");
	document.getElementById('presentacionGroup').classList.remove("has-error");
	document.getElementById('precioGroup').classList.remove("has-error");
	document.getElementById('imagenGroup').classList.remove("has-error");
	
	if(categoria!="categoria"){
		if(subcategoria!="subcategoria"){	
			if (nombre.length > 3 && nombre.length <=30){
				if(presentacion.length >3 && presentacion.length <= 30){
					if($.isNumeric(precio)){	
						
						//NO SE VALIDA STOCK
						
						//Validar archivos
						if (document.getElementById('imagenes').type == "hidden"){
							alert("No se reemplaza la imagen");
							return true;
						}else
							{
							var archivos = document.getElementById('imagenes').files;
							
							if (archivos.length>0){
								if(archivos.length <2){
									for(var i=0;i<archivos.length;i++){
										var name = archivos[i].name;
										var ext = name.substring(name.lastIndexOf('.')).toLowerCase();	
										if (ext!='.jpg'){
											alert("El archivo "+name+" no es v\u00e1lido, solo se aceptan imagenes en .JPG");
											document.getElementById('imagenGroup').classList.add("has-error");											
											return false;
										}
									}
								
								return true;
								}else{
									alert("El m\u00e1aximo n\u00famero de archivos permitidos es uno")
									document.getElementById('imagenGroup').classList.add("has-error");
									}
							}else{
								alert("No elegiste ninguna imagen");
								document.getElementById('imagenGroup').classList.add("has-error");
								}
						}
					}else{
						alert("Ups! Ingresaste un precio no v\u00e1lido, pod\u00e9s usar n\u00fameros enteros o decimales");
						document.getElementById('precioGroup').classList.add("has-error");
						}
				}else{
					alert("Ups! Ingresaste una presentaci\u00f3n no v\u00e1lida, debe tener entre 4 y 30 caracteres");
					document.getElementById('presentacionGroup').classList.add("has-error");
					}
			}else{
				alert("Ups! Ingresaste un nombre no v\u00e1lido, debe tener entre 4 y 30 caracteres");
				document.getElementById('nombreGroup').classList.add("has-error"); //Obtengo el elemento del DOM con id="nombreGroup" yle agrego la clase bootstrap has-error
				}
		}else{
			alert("Ups! No seleccionaste una subcategor\u00eda");
			document.getElementById('subcategoriaGroup').classList.add("has-error");
			}
	}else{
		alert("Ups! No seleccionaste una categor\u00eda");
		document.getElementById('categoriaGroup').classList.add("has-error");
		}	
	
	return false;
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

function filtrarTabla(dispositivo){
	
	switch (dispositivo){
	
	case "smartphone":
		{
		var nombre = $('#filtrarNombrexs').val();
		var idProducto = $('#filtrarIdProductoxs').val();
		var presentacion = $('#filtrarPresentacionxs').val();
		var precioDesde = $('#filtrarPrecioDesdexs').val();
		var precioHasta = $('#filtrarPrecioHastaxs').val();
		var stockDesde = $('#filtrarStockDesdexs').val();
		var stockHasta = $('#filtrarStockHastaxs').val();
		break;
		}
	case "pc":
		{
		var nombre = $('#filtrarNombre').val();
		var idProducto = $('#filtrarIdProducto').val();
		var presentacion = $('#filtrarPresentacion').val();
		var precioDesde = $('#filtrarPrecioDesde').val();
		var precioHasta = $('#filtrarPrecioHasta').val();
		var stockDesde = $('#filtrarStockDesde').val();
		var stockHasta = $('#filtrarStockHasta').val();
		break;
		}
	}

	var parametro = {
					idProducto : idProducto,		
					nombre : nombre,
					presentacion : presentacion,
					precioDesde : precioDesde,
					precioHasta : precioHasta,
					stockDesde : stockDesde,
					stockHasta : stockHasta,
					};
	$('#tabla > tbody').html("");//ELIMINO LAS FILAS DE LA TABLA QUE EXISTE EN ESTE MOMENTO
	
	$.post("FiltraProductos",$.param(parametro),function(responseJson){
		$.each(responseJson,function(index, productos){
			$('<tr>',{
				'html' : "<td id='idProducto'>"+productos.idProducto+"</td>" +
				"			<td id='nombreProducto'>"+productos.nombre+"</td>" +
				"			<td id='presentacionProducto'>"+productos.presentacion+"</td>" +
				"			<td>"+productos.precio+"</td>" +
				"			<td>"+productos.stock+"</td>" +
				"			<td class='col-sm-3 col-lg-2'>" +
				"				<div class='input-group'>" +
				"					<a class='btn btn-danger btnEliminarProducto' href='\'>Eliminar</a>" +
				"					<a class='btn btn-primary' id='btnModificarProducto'  href='ModificarProducto?id="+productos.idProducto+"'>Modificar</a>" +
				"				</div>" +
				"			</td>"
				}).appendTo("table > tbody");
			
			
			})
		})
	}

//FUNCIONES DE MODULO VENTAS

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

function agregarProductoVenta(idProducto,nombre,presentacion,precio,cantidad){
	
		var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		var bandera = false;
		//RECORRO LA TABLA 	VERIFICANDO QUE NO ESTE AGREGADO EL PRODUCTO
		$.each(filas,function(i,fila){
			if (i>0){
				if (fila.cells[0].innerHTML==idProducto){
					bandera=true;
					alert("Este elemento ya esta en la venta");
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
				
				subtotal += ((cantidad*1)*(precio*1));
				
				$("#subtotal").val(subtotal);

			}else{
				alert("Para agregar un producto debes ingresar la cantidad");
			}
		}
	}
												//---------------
												//JQUERY GENERAL
												//---------------

$(document).ready(function() {

												//-----------------------------------------
												//JQUERY MODULO ADMINISTRACION DE PRODUCTOS
												//----------------------------------------- 
	
	$('#btnGuardarModificacionProducto').click(function(e){
		e.preventDefault();
		var categoria = $('#categoria').val();
		var subcategoria = $('#subcategoria').val();
		var nombre = $('#nombre').val();
		var presentacion = $('#presentacion').val();
		var precio = $('#precio').val();
		
		if(validarCampos(nombre,presentacion,precio,categoria,subcategoria)){
			var data = new FormData($('#form_modificar_producto')[0]);
			$.ajax({
				url : "ConfirmarAltaProducto",
				type : "post",
				data : data,
				contentType : false,
				processData : false,
				success : function(data){
					alert(data);
				}
			});
		}
	});
	
	$('#btnAgregarProducto').click(function(e){
		e.preventDefault();
		var categoria = $('#categoria').val();
		var subcategoria = $('#subcategoria').val();
		var nombre = $('#nombre').val();
		var presentacion = $('#presentacion').val();
		var precio = $('#precio').val();
		
		if(validarCampos(nombre,presentacion,precio,categoria,subcategoria)){
			var data = new FormData($('#form_nuevo_producto')[0]);
			$.ajax({
				url : "ConfirmarAltaProducto",
				type : "post",
				data : data,
				contentType : false,
				processData : false,
				success : function(data){
					alert(data);
				}
			});
		}
	});	
	
	$('#btnImagenes').click(function(e){
		e.preventDefault();
		document.getElementById('btnImagenes').type = "hidden";
		document.getElementById('imagenes').type = "file";
		document.getElementById('archivoHelp').classList.remove("hidden");
	});	
	
	$("select[name=categoria]").change(function(){
		var idCategoria = $('#categoria').val();
		var parametro = {idCategoria : idCategoria };
		if(idCategoria!="categoria"){
			$.post("ComboSubcategoria",$.param(parametro),function(responseJson){
				$('#subcategoria').empty();
				$('#subcategoria').append($('<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>'));
				$.each(responseJson,function(index, subcat){
					$('#subcategoria').append($('<option value="'+subcat.idSubcategoria+'">'+subcat.nombre+'</option>'));
				});
			});	
		}
	});
	
	//FUNCION POSIBLEMENTE INNECESARIA
	
	$('tr #btnEliminarProducto').click(function(e){
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
			});
		}
	});
	
	// FIN FUNCION POSIBLEMENTE INNECESARIA
	
	$(document).on('click','.btnEliminarProducto',function(e){
		e.preventDefault();  //detiene la accion del boton (VIDEO 10)
		
		var nombreProducto = $(this).parent().parent().parent().find('#nombreProducto').text()+" "+$(this).parent().parent().parent().find('#presentacionProducto').text();
		var opcion = confirm("Seguro quer\u00e9s eliminar el producto "+nombreProducto+" ?");
		if (opcion){
			var fila =$(this).parent().parent().parent()
			var idProducto = fila.find('#idProducto').text();//captura el idproducto dentro de la estructura de la pagina
			var data={idProducto : idProducto};
			$.post("EliminarProducto",data,function(res,est,jqXHR){ //Llama al servlet, le pasa data y ejecuta una funcion con un resultado, un estado y un ....
				//alert(res);    //Muestra la respuesta de ejecutar EliminarProducto
				fila.remove();
			});
		}
	});
	
										//----------------------
										//JQUERY MODULO CLIENTES
										//----------------------
										
	$('#btnAgregarCliente').click(function(e){
		e.preventDefault();
		var nombre = $('#nombre').val();
		var apellido = $('#apellido').val();
		var dni = $('#dni').val();
		var direccion = $('#direccion').val();
		var telefono = $('#telefono').val();
		var email = $('#email').val();
		
		//falta el validar campos
		if(true){
			var data = new FormData($('#form_nuevo_cliente')[0]);
			$.ajax({
				url : "ConfirmarAltaCliente",
				type : "post",
				data : data,
				contentType : false,
				processData : false,
				success : function(data){
					alert(data);
				}
			});
		}
	});	
	
	
	
									//VALIDACION EN DISPOSITIVOS DE ESCRITORIO
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarNombre').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarIdProducto').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarPresentacion').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarPrecioDesde').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarPrecioHasta').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarStockDesde').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarStockHasta').change(function(e){
		filtrarTabla("pc");
	});
	
									//VALIDACION EN DISPOSITIVOS MOVILES
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarNombrexs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarIdProductoxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarPresentacionxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarPrecioDesdexs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarPrecioHastaxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarStockDesdexs').change(function(e){
		filtrarTabla("smartphone");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarStockHastaxs').change(function(e){
		filtrarTabla("smartphone");
	});
	
													//-----------------------
													//	JQUERY MODULO VENTAS
													//-----------------------
	
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#buscarProductosVenta').click(function(e){
		e.preventDefault();
		buscarProductosVenta($('#inputProducto').val());
	});
	
	//DETECTO EL CLICK EN BUSCAR PRODUCTOS
	$(document).on('click','.btnAgregarProductoVenta',function(e){
		e.preventDefault();
		
		var fila =$(this).parent().parent().parent()
		
		var idAgregar = fila.find('#idProducto').text();
		var nombreAgregar = fila.find('#nombreProducto').text();
		var presentacionAgregar = fila.find('#presentacionProducto').text();
		var cantidadAgregar = fila.find('#cantidad').val();
		var precioAgregar = fila.find('#precioProducto').text();

		// Mostrar todos los campos
		//alert("id: "+idAgregar+" \n nombre: "+nombreAgregar+" \n presentacion: "+presentacionAgregar+" \n cantidad: "+cantidadAgregar+" \n precio: "+precioAgregar)
		
		agregarProductoVenta(idAgregar,nombreAgregar,presentacionAgregar,precioAgregar,cantidadAgregar);
		
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
		subtotal -= ((precio*1)*(cantidad*1));
		
		$("#subtotal").val(subtotal);
		
		fila.remove();
		});
	
	//CAPTURO EL CLICK EN CONTINUAR (VENTA PASO 1)
	$(document).on('click','#btnContinuar',function(e){
		e.preventDefault();
		
	
		var filas = $(".tablaVentaActual tr"); //OBTENGO UN ARREGLO DE LAS FILAS DE LA TABLA
		if (filas.length == 1){
			alert("No seleccionaste ningun producto");
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
			
				alert(parametro);
			$.ajax({
				type : "post",
				url : "CargarProductosVenta",
				data : {jsonData : parametro},
				success : function(respuesta){
					//alert(respuesta);
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
			break;
		case "1":
			$('#tarjeta').prop('disabled',true);
			break;
		case "2":
			$('#tarjeta').prop('disabled',false);
			break;
		case "3":
			$('#tarjeta').prop('disabled',false);
			break;
				
		}
			/*var parametro = {idCategoria : idCategoria };
			if(idCategoria!="categoria"){
				$.post("ComboSubcategoria",$.param(parametro),function(responseJson){
					$('#subcategoria').empty();
					$('#subcategoria').append($('<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>'));
					$.each(responseJson,function(index, subcat){
						$('#subcategoria').append($('<option value="'+subcat.idSubcategoria+'">'+subcat.nombre+'</option>'));
					});
				});	
			}
		
		*/
	});
});
