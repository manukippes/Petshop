/**
 * 
 */
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
							//confirm("No se reemplaza la imagen");
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
											alertError("El archivo "+name+" no es v\u00e1lido, solo se aceptan imagenes en .JPG");
											document.getElementById('imagenGroup').classList.add("has-error");											
											return false;
										}
									}
								
								return true;
								}else{
									alertError("El m\u00e1aximo n\u00famero de archivos permitidos es uno")
									document.getElementById('imagenGroup').classList.add("has-error");
									}
							}else{
								alertError("No elegiste ninguna imagen");
								document.getElementById('imagenGroup').classList.add("has-error");
								}
						}
					}else{
						alertError("Ups! Ingresaste un precio no v\u00e1lido, pod\u00e9s usar n\u00fameros enteros o decimales");
						document.getElementById('precioGroup').classList.add("has-error");
						}
				}else{
					alertError("Ups! Ingresaste una presentaci\u00f3n no v\u00e1lida, debe tener entre 4 y 30 caracteres");
					document.getElementById('presentacionGroup').classList.add("has-error");
					}
			}else{
				alertError("Ups! Ingresaste un nombre no v\u00e1lido, debe tener entre 4 y 30 caracteres");
				document.getElementById('nombreGroup').classList.add("has-error"); //Obtengo el elemento del DOM con id="nombreGroup" yle agrego la clase bootstrap has-error
				}
		}else{
			alertError("Ups! No seleccionaste una subcategor\u00eda");
			document.getElementById('subcategoriaGroup').classList.add("has-error");
			}
	}else{
		alertError("Ups! No seleccionaste una categor\u00eda");
		document.getElementById('categoriaGroup').classList.add("has-error");
		}	
	
	return false;
	}

function filtrarTabla(){
	
	var dispositivo="";
	if (screen.width < 768){
		dispositivo="smartphone"
	}else{dispositivo = "pc"}
	;
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
		
		var cantFilas = $("table tbody tr").length; 
		alert (cantFilas);
	}

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
					if(data==1){
						swal ( {
							 title : "Bien hecho!",
							 text : "Producto modificado exitosamente",
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
								$(location).attr('href',"MantenimientoProductos");	
							}else{
								$(location).attr('href',"MantenimientoProductos");	
							}
						})

					}else{
						alertError("Error en la modificaci\u00F3n del producto");
					}
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
					if(data==1){
						alertOk("Producto creado exitosamente");
					}else{
						alertError("Error en la creacion del producto");
					}
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
	
	
$(document).on('click','.btnEliminarProducto',function(e){
		e.preventDefault();  //detiene la accion del boton (VIDEO 10)
		var nombreProducto = $(this).parent().parent().parent().find('#nombreProducto').text()+" "+$(this).parent().parent().parent().find('#presentacionProducto').text();
		swal ( {
			 title : "Atenci\u00F3n!",
			 text : "Seguro quer\u00e9s eliminar el producto "+nombreProducto+" ?",
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
					  var fila =$(this).parent().parent().parent()
						var idProducto = fila.find('#idProducto').text();//captura el idproducto dentro de la estructura de la pagina
						var parametro={idProducto : idProducto};
				        var parametros = JSON.stringify(parametro);
					 	$.ajax({
							url : "EliminarProducto",
							type : "post",
							data : {jsonData : parametros},
							success : function(data){
								if (data == 1)
								{
									fila.remove();
									alertOk("Producto eliminado exitosamente");
								} 
								else
								{
									alertError("Al eliminar el producto, ya se encuentra utilizado.");
								}
			                    
							}
				        })	   
				  }else{
					  return false;
				  }
				});
});

	
	//VALIDACION EN DISPOSITIVOS DE ESCRITORIO
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarNombre').change(function(e){
		filtrarTabla("pc");
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarIdProducto').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarPresentacion').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarPrecioDesde').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarPrecioHasta').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarStockDesde').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarStockHasta').change(function(e){
		filtrarTabla();
	});
	
									//VALIDACION EN DISPOSITIVOS MOVILES
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR NOMBRE
	$('#filtrarNombrexs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR ID
	$('#filtrarIdProductoxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE FILTRAR POR PRESENTACION
	$('#filtrarPresentacionxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MINIMO
	$('#filtrarPrecioDesdexs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE PRECIO MAXIMO
	$('#filtrarPrecioHastaxs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MINIMO
	$('#filtrarStockDesdexs').change(function(e){
		filtrarTabla();
	});
	
	//DETECTO LOS CAMBIOS EN INPUT DE STOCK MAXIMO
	$('#filtrarStockHastaxs').change(function(e){
		filtrarTabla();
	});
	
	$('.btnLimpiarfiltros').click(function(){
		
		$('#filtrarNombre').val("");
		$('#filtrarIdProducto').val("");
		$('#filtrarPresentacion').val("");
		$('#filtrarPrecioDesde').val("");
		$('#filtrarPrecioHasta').val("");
		$('#filtrarStockDesde').val("");
		$('#filtrarStockHasta').val("");
		filtrarTabla();

	})
})