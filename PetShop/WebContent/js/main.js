/**
 * 
 */
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



$(function() {

	function validarCampos(nombre, presentacion, precio,categoria,subcategoria){
		if(categoria!="categoria"){
			if(subcategoria!="subcategoria"){	
				if (nombre.length > 3 && nombre.length <=30){
					if(presentacion.length >3 && presentacion.length <= 30){
						if($.isNumeric(precio)){
							
						}else{alert("Ups! Ingresaste un precio no v\u00e1lido, pod\u00e9s usar n\u00fameros enteros o decimales")}
					}else{alert("Ups! Ingresaste una presentaci\u00f3n no v\u00e1lida, debe tener entre 4 y 30 caracteres")}
				}else{alert("Ups! Ingresaste un nombre no v\u00e1lido, debe tener entre 4 y 30 caracteres")}
			}else{alert("Ups! No seleccionaste una subcategor\u00eda")}
		}else{alert("Ups! No seleccionaste una categor\u00eda")}	
	}
	
	$('#btnAgregarProducto').click(function(e){
		e.preventDefault();
		var categoria = $('#categoria').val();
		var subcategoria = $('#subcategoria').val();
		var nombre = $('#nombre').val();
		var presentacion = $('#presentacion').val();
		var precio = $('#precio').val();
		
		
		validarCampos(nombre,presentacion,precio,categoria,subcategoria);
		
	});
	
});


	
	
	
