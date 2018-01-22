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
										

$(document).ready(function() {

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
									
});

