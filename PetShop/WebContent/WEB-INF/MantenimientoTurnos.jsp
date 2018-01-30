<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Producto"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/mantTurnos.js"></script>
	
	<title>SGPS - Mantenimiento de Turnos</title>
</head>
<body onload="iniciar('administracion');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container panel panel-default colorPanel">
	 <div class="panel-body">">
		<br>
		<br>	
		<h4><strong>M&Oacute;DULO DE MANTENIMIENTO DE TURNOS</strong></h4>
		<hr>
		
		<h5>Listado de turnos registrados</h5>
		<%ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		ArrayList<Producto> productos = ctrlProducto.getProductos(); %>
		
		
		
		<div class="panel-group visible-xs" id="acordeon" role="tablist">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="heading1">
					<h4 class="panel-title">
						<div class="container">
							<button href="#collapse1" data-toggle="collapse" data-parent="#accordion" class="col-xs-12 btn btn-default">
								<span>Filtrar Listado</span>
							</button>
						</div>	
					</h4>
				</div>
				<div>
				
				</div>
				<div id="collapse1" class="panel-collapse collapse">
					<div class="panel-body">
						<div class="input-group input-group-sm">
							<input type="date" id="filtrarFechaDesdexs" name="filtrarFechaDesdexs" title="Filtrar Fecha Desde" class="form-control" placeholder="Filtrar Fecha Desde"></input>
							<input type="date" id="filtrarFechaHastaxs" name="filtrarFechaHastaxs" title="Filtrar Fecha Hasta" class="form-control" placeholder="Filtrar Fecha Hasta"></input>
						    <select class="form-control" name="filtrarEstadoxs" id="filtrarEstadoxs" aria-describedby="estadoHelp" required>
						    	<option value="filtrar">Filtrar por Estado</option>
						    	<option value="Pendiente">Pendiente</option>
						    	<option value="Realizado">Realizado</option>
						    	<option value="Cancelado">Cancelado</option>
						    </select>
						    <input type="text" id="filtrarIdClientexs" name="filtrarIdClientexs" title="Filtrar por id de cLiente" class="form-control" placeholder="Filtrar por Id"></input>
							<input type="text" id="filtrarApellidoxs" name="filtrarApellidoxs" class="form-control" title="Filtrar por apellido" placeholder="Filtrar Apellido"></input>
							<input type="text" id="filtrarMascotaxs" name="filtrarMascotaxs" class="form-control" title="Filtrar por nombre de mascota" placeholder="Filtrar Mascota"></input>
							<input type="text" id="filtrarDireccionxs" name="filtrarDireccionxs" title="Filtrar por direccion" class="form-control" placeholder="Filtrar Direccion"></input>
							<select class="form-control" name="filtrarConTransportexs" id="filtrarConTransportexs" aria-describedby="conTransporteHelp" required>
						    	<option value="filtrar">Con Retiro</option>
						    	<option value="true">Si</option>
						    	<option value="false">No</option>
						    </select>
						    <select class="form-control" name="filtrarServicioxs" id="filtrarServicioxs" aria-describedby="tipoServicioHelp" required>
						    	<option value="filtrar">Filtrar por Servicio</option>
						    	<option value="1">Banio</option>
						    	<option value="2">Corte</option>
						    	<option value="3">Banio con Corte</option>
						    	<option value="3">Antipulgas</option>
						    </select>
						</div>
					</div>
				</div>
			</div>
		</div>
			
			<div class="table-responsive">
				<table id="tabla" class="table table-striped table-hover">
					<thead>
						<tr class="active">
							<th>
								<div class="input group input-group-sm">
									<div class="input group input-group-sm">
										<input type="date" id="filtrarFechaDesde" name="filtrarFechaDesde" title="Filtrar Fecha Desde" class="form-control hidden-xs" placeholder="Filtrar Fecha Desde"></input>
										<input type="date" id="filtrarFechaHasta" name="filtrarFechaHasta" title="Filtrar Fecha Hasta" class="form-control hidden-xs" placeholder="Filtrar Fecha Hasta"></input>
										<hr class="hidden-xs">
										<div>Fecha&nbsp;</div>
									</div>
								</div>		
							</th>
							<th id="estadoGroup" class="input group input-group-sm">
								<select class="form-control hidden-xs" name="filtrarEstado" title="Filtrar por Estado de turno" id="filtrarEstado" aria-describedby="estadoHelp" required>
							    	<option value="filtrar">Filtrar por Estado</option>
							    	<option value="Pendiente">Pendiente</option>
							    	<option value="Realizado">Realizado</option>
							    	<option value="Cancelado">Cancelado</option>
							    </select>
								<hr class="hidden-xs">
								<span>Estado</span>
							</th>
							<th id="apellidoGroup" class="input group input-group-sm">
								<input type="text" id="filtrarApellido" name="filtrarApellido" class="form-control hidden-xs" title="Filtrar por apellido" placeholder="Filtrar Apellido"></input>
								<hr class="hidden-xs">
								<div>Apellido</div>
							</th>
							<th>
								<div>
									<div class="input group input-group-sm">
										<input type="text" id="filtrarMascota" name="filtrarMascota" class="form-control hidden-xs" title="Filtrar por nombre de mascota" placeholder="Filtrar Mascota"></input>
										<hr class="hidden-xs">
										<div>Mascota</div>
									</div>
								</div>
							</th>
							<th>
								<div class="">
									<div class="input group input-group-sm">
										<input type="text" id="filtrarDireccion" name="filtrarDireccion" title="Filtrar por direccion" class="form-control hidden-xs" placeholder="Filtrar Direccion"></input>
										<hr class="hidden-xs">
										<div>Direcci&oacute;n</div>
									</div>
								</div>							
							</th>
							<th>
								<div class="">
									<div class="input group input-group-sm">
										<select class="form-control hidden-xs" name="filtrarConTransporte" title="Filtrar con/sin retiro" id="filtrarConTransporte" aria-describedby="conTransporteHelp" required>
									    	<option value="filtrar">Filtrar con retiro</option>
									    	<option value="true">Si</option>
									    	<option value="false">No</option>
									    </select>
										<hr class="hidden-xs">
										<div>Transporte</div>
									</div>
								</div>							
							</th>
							<th>
								<div class="input group input-group-sm">
									<select class="form-control hidden-xs" name="filtrarServicio" title="Filtrar Tipo de Servicio" id="filtrarServicio" aria-describedby="tipoServicioHelp" required>
								    	<option value="filtrar">Filtrar por Servicio</option>
								    	<option value="1">Banio</option>
								    	<option value="2">Corte</option>
								    	<option value="3">Banio con Corte</option>
								    	<option value="4">Antipulgas</option>
								    </select>
									<hr class="hidden-xs">
									<div class="inline">Servicio</div>
								</div>					
							</th>
							<th class="col-sm-3 col-lg-2"> 
								<div>
									<button class="btn btn-primary hidden-xs"><h4><span class="glyphicon glyphicon-print"> </span> <strong>Imprimir</strong></h4></button>
								</div>
								<hr class="hidden-xs">
								<div class="input-group">
									<span>Acciones </span>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
		 			</tbody>
				</table>
			
				</div>
				<script src="http://code.jquery.com/jquery-latest.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
				<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
				<!-- <script src="js/bootstrap.min.js"></script> -->
				<div class="">
					<button class="btn btn-primary visible-xs"><span class="glyphicon glyphicon-print"> </span> <strong>Imprimir listado actual</strong></button>
				</div>
			</div>
	  	</div>
	</body>
</html>