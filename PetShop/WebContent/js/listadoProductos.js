/**
 * 
 */

$(function(){
	$('tr #btnEliminarProducto').click(function(e){
		e.preventDefault();  //eliminar la accion del boton VIDEO 10
		
		var nombreProducto = $(this).parent().parent().find('#nombreProducto').text()+" "+$(this).parent().parent().find('#presentacionProducto').text();
		var opcion = confirm("Seguro quer\u00e9s eliminar el producto "+nombreProducto+" ?");
		if (opcion){
			var fila =$(this).parent().parent();
			var idProducto = fila.find('#idProducto').text();//captura el idproducto dentro de la estructura de la pagina
			
			var data={idProducto : idProducto};
			$.post("EliminarProducto",data,function(res,est,jqXHR){ //Llama al servlet, le pasa data y ejecuta una funcion con un resultado, un estado y un ....
				//alert(res);    //Muestra la respuesta de ejecutar EliminarProducto
				fila.remove();
			});
		}
	});
	/*$('tr #btnModificarProducto').click(function(e){
		e.preventDefault();  //eliminar la accion del boton VIDEO 10
		alert("Apretaste modificar");
		var fila =$(this).parent().parent();
		var idProducto = fila.find('#idProducto').text();//captura el idproducto dentro de la estructura de la pagina
		
		var data={idProducto : idProducto};
		$.post("ModificarProducto",data){ //Llama al servlet, le pasa data y ejecuta una funcion con un resultado, un estado y un ....
			//alert(res);    //Muestra la respuesta de ejecutar EliminarProducto
			//fila.remove();
		});
		
	});*/
});



