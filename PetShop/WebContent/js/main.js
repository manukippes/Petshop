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

 
function alertError(mensaje)
{
    var dgcTiempo=500;
    var ventanaCS="<div class='modal fade' id='alert'>" +
    		"			<div class='modal-dialog'>" +
    		"				<div class='modal-content'>" +
    		"					<div class='modal-header'>" +
    		"						<div class='pull-right'>" +
    		"							<button type='button' id='btnCerrarAlertX' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>" +
    		"						</div>" +
    		"						<div class='container'>" +
    		"							<h3 class='text-danger'><span class='glyphicon glyphicon-warning-sign'></span>  Disculpe, algo no est&aacute; bien</h3>" +
    		"						</div>" +
    		"					</div>" +
    		"					<div class='modal-body'>" +						
    		"						<br><div class='container-fluid'>" +
    		"								<p class='text-info'> "+mensaje+"</p><br>" +
    		"							</div>" +
    		//"					</div>" +
    		//"					<div class='modal-footer'>" +
    		//"						<button class='btn btn-primary' id='btnCerrarAlert'>Aceptar</button>" +
    		"					</div>" +
    		"				</div>" +
    		"			</div>" +
    		"		</div>";
    	$('body').append(ventanaCS);
    	$('#alert').modal('toggle');
    	$('#btnCerrarAlertX').click(function(e) {
    		$('#alert').modal('toggle');
    		setTimeout("$('#alert').remove()",dgcTiempo);
    	})

}

function alertExito(mensaje)
{

	var dgcTiempo=500;
    var ventanaCS="<div class='modal fade' id='alert'>" +
    		"			<div class='modal-dialog'>" +
    		"				<div class='modal-content'>" +
    		"					<div class='modal-header'>" +
    		"						<div class='pull-right'>" +
    		"							<button type='button' id='btnCerrarAlertX' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>" +
    		"						</div>" +
    		"						<div class='container'>" +
    		"							<h3 class='text-success'><span class='glyphicon glyphicon-ok'></span>  Felicitaciones! </h3>" +
    		"						</div>" +
    		"					</div>" +
    		"					<div class='modal-body'>" +						
    		"						<br><div class='container-fluid'>" +
    		"								<p class='text-info'> "+mensaje+"</p><br>" +
    		"							</div>" +
    		//"					</div>" +
    		//"					<div class='modal-footer'>" +
    		//"						<button class='btn btn-primary' id='btnCerrarAlert'>Aceptar</button>" +
    		"					</div>" +
    		"				</div>" +
    		"			</div>" +
    		"		</div>";
    	$('body').append(ventanaCS);
    	$('#alert').modal('toggle');
    	$('#btnCerrarAlertX').click(function(e) {
    		$('#alert').modal('toggle');
    		setTimeout("$('#alert').remove()",dgcTiempo);
    	})

}
function alertConfirmar(mensaje)
{

	var dgcTiempo=500;
    var ventanaCS="<div class='modal fade' id='alert'>" +
    		"			<div class='modal-dialog'>" +
    		"				<div class='modal-content'>" +
    		"					<div class='modal-header'>" +
    		"						<div class='pull-right'>" +
    		"							<button type='button' id='btnCerrarAlertX' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>" +
    		"						</div>" +
    		"						<div class='container'>" +
    		"							<h3 class='text-success'><span class='glyphicon glyphicon-ok'></span> Atencion </h3>" +
    		"						</div>" +
    		"					</div>" +
    		"					<div class='modal-body'>" +						
    		"						<br><div class='container-fluid'>" +
    		"								<p class='text-info'> "+mensaje+"</p><br>" +
    		"							</div>" +
    		//"					</div>" +
    		//"					<div class='modal-footer'>" +
    		//"						<button class='btn btn-primary' id='btnCerrarAlert'>Aceptar</button>" +
    		"					</div>" +
    		"				</div>" +
    		"			</div>" +
    		"		</div>";
    	$('body').append(ventanaCS);
    	$('#alert').modal('toggle');
    	$('#btnCerrarAlertX').click(function(e) {
    		$('#alert').modal('toggle');
    		setTimeout("$('#alert').remove()",dgcTiempo);
    	})

}
window.prompt = function (message) {
	
	alertExito(message);
	
};

window.alert = function (message) {

	alertError(message);		//Llama al alert de error

};

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
			alert("Para agregar primero debes seleccionar un cliente");
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
									
})

