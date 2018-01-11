/**
 * 
 */
$(document).ready(function(){

	
	$("select[name=categoria]").change(function(){
		var idCategoria = $('#categoria').val();
		var parametro = {idCategoria : idCategoria };
		$.post("ComboSubcategoria",$.param(parametro),function(responseJson){
			$('#subcategoria').empty();
			$('#subcategoria').append($('<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>'));
			$.each(responseJson,function(index, subcat){
				$('#subcategoria').append($('<option value="'+subcat.idSubcategoria+'">'+subcat.nombre+'</option>'));
			});
		});	
	});
});



