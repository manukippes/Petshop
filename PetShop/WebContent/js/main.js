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
    		"							<h3 class='text-danger'><span class='glyphicon glyphicon-warning-sign'></span>  Ups, algo no est&aacute; bien</h3>" +
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
window.confirm = function (message) {
	
	alertExito(message);
};

window.alert = function (message) {

	alertError(message);		//Llama al alert de error

};


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

