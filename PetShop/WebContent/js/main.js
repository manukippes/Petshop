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
    		case 'comprobantesVentas':
    			comprobantesVentasTab.className += " active";
    			break;
    		case 'turnos':
    			turnosTab.className += " active";
    			break;
    		default:
    			break;
    		}
}

 
 function alertError(mensaje){
	 swal ( {
		 title : "Error!",
		 text : mensaje,
		 icon : "error" ,
		 button: {
			    text: "Aceptar",
			    value: true,
			    visible: true,
			    className: "",
			    closeModal: true
			  }
		} ) ;
 }
 
 function alertOk(mensaje){
	 swal ( {
		 title : "Bien hecho!",
		 text : mensaje,
		 icon : "success" , 
		 button: {
			    text: "Aceptar",
			    value: true,
			    visible: true,
			    className: "",
			    closeModal: true
			  }
		} ) ;
 }
 
 function alertConfirm(mensaje){
	 swal ( {
		 title : "Atenci\u00F3n!",
		 text : mensaje,
		 icon : "info" , 
		 button: {
			 cancel: 
			 	  {
				    text: "Cancelar",
				    value: null,
				    visible: false,
				    className: "",
				    closeModal: true,
				  },
				  confirm: {
				    text: "Aceptar",
				    value: true,
				    visible: true,
				    className: "",
				    closeModal: true
				  }
			  }
		} )
		.then((respuesta) => {
			  if (respuesta) {
			    return true;
			  } else {
			    return false;
			  }
			});
 }

function destacarCampo(nombre){
	$('#'+nombre).addClass("has-error");
	clearTimeout();
	setTimeout(function(){$('#'+nombre).removeClass("has-error");},3500);
};


$(document).ready(function() {
										//
										//JQUERY MODAL AGREGAR CLIENTE
										//
	
	
	//CAPTURO EL CLICK DEL BOTON BUSCAR EN EL PANEL MODAL
	$(this).on("click", "#btnBuscarCliente", function(e){
	    e.preventDefault();
		
		var inputCliente = $('#inputCliente').val();
		if(inputCliente!=""){
			var parametro = {inputCliente : inputCliente};
			$('#cliente').prop('disabled',false);
			$.post("ComboClientes",$.param(parametro),function(responseJson){
				$('#cliente').empty();
				$('#cliente').append($('<option value="cliente">Seleccion&aacute; un cliente</option>'));
				$.each(responseJson,function(index, usuarios){
					$('#cliente').append($('<option value="'+usuarios.idUsuario+'">'+usuarios.apellido+', '+usuarios.nombre+'</option>'));
				});
			});
		}else{
			$('#cliente').empty();
			$('#cliente').append($('<option value="cliente">Seleccion&aacute; un cliente</option>'));
			$('#cliente').prop('disabled',true);
			$('#inputClienteGroup').addClass("has-error");
			clearTimeout();
			setTimeout(function(){$('#inputClienteGroup').removeClass("has-error");},2000);
			}
	}); 
	
	
	$(this).on("click", "#btnAgregarClienteSeleccionado", function(e){
	    e.preventDefault();
	    if($('#cliente').val() == "cliente"){
			alertError("Para agregar primero debes seleccionar un cliente");
		}else{
			$('#buscarCliente').modal('toggle');
	        $('#btnQuitarCliente').removeClass("hidden");
	        $('#btnAgregarUnCliente').addClass("hidden");
	        $('#btnQuitarCliente').show();
	        $('#btnAgregarUnCliente').hide();
	        $('#btnQuitarCliente').add();
	        $('#tablaClienteSeleccionado').removeClass("hidden");
	        
	        var idCliente = $('#cliente').val();
	        
	        parametro = {idCliente : idCliente};
	        $.post("ObtenerCliente",$.param(parametro),function(responseJson){
				$.each(responseJson,function(index, cliente){
					$('#idUsuario').val(cliente.idUsuario); 			
			        $('#nombreApellidoCliente').text(cliente.nombre+", "+cliente.apellido);
			        $('#telefonoCliente').text(cliente.telefono);
			        $('#direccionCliente').text(cliente.direccion);
				});
			});	   
		}
	}); 
	
	$(this).on("click", "#btnQuitarCliente", function(e){
	    e.preventDefault();
	    
	    $('#btnQuitarCliente').addClass("hidden");
	    $('#btnQuitarCliente').hide();
	    $('#btnAgregarUnCliente').removeClass("hidden");
	    $('#btnAgregarUnCliente').show();
	    $('#tablaClienteSeleccionado').addClass("hidden");
	    $('#idUsuario').val("");								//borro el id del usuario
	    $('#nombreApellidoCliente').text("");
	    $('#telefonoCliente').text("");
	    $('#direccionCliente').text("");
		
	}); 
	
	$('#fila1').click(function(e){
		$('#fila2').hide();
		
	});
})

