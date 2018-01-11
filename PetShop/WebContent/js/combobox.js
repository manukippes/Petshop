/**
 * 
 */
$(document).ready(function(){

	
	$("select[name=categoria]").change(function(){

		var idCategoria = $('#categoria').val();
		$.ajax({
			url : "Subcategoria",
			type : "post",
			data : {idCategoria : idCategoria},
			dataType : 'json',
			success : function(data){
				$('#subcategoria').empty();
				$('#subcategoria').append($('<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>'));
				
			}
	
		});	
	
	});
});



