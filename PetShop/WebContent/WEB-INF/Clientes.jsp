<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	<jsp:include page="cssGeneral.jsp" />
	<title>SGPS - Clientes</title>
</head>
<body onload="iniciar('administracion');">
	<jsp:include page="Navbar.jsp" />
	<div class="container panel panel-default colorPanel">
		<div class="panel-body">
			<br>
			<br>	
			<h4><strong>MANTENIMIENTO DE CLIENTES</strong></h4>
			<hr>
			<h5>Para consultar o modificar un cliente:</h5>
			<div class="col-12 btn-group-justified">
				<form class="form" action="ModificarConsultarCliente" method="post" enctype="multipart/form-data" id="form_clientes">
					<div class="form-group row">
					<!-- Input cliente -->	
						 <div class="col-lg-6 col-md-12" >
						    <div class="input-group" id="nombreGroup">
						      <input type="text" class="form-control" name="inputCliente" id="inputCliente" placeholder="Ingres&aacute; nombre, apellido o id del cliente">
						      <span class="input-group-btn">
						        <button class="btn btn-primary" type="button" id="btnBusquedaCliente"><span class="glyphicon glyphicon-search"></span></button>
						      </span>
						    </div>
						   
						 </div>
					
					<!-- Seleccionar cliente -->	
						<div class="selectContainer col-lg-6 col-md-12" id="clienteGroup">
							<div class="input-group">
			    				<label class="sr-only">Seleccione un cliente</label>
							    <select class="form-control" name="clienteSeleccionado" id="cliente" aria-describedby="clienteHelp" disabled>
							    	<option value="cliente">Seleccion&aacute; un cliente</option>
							    </select>
							    <span class="input-group-btn">
						        	<button class="btn btn-primary" type="button" id="btnVerCliente"><span class="glyphicon glyphicon-pencil"></span></button>
						        	<button class="btn btn-danger" type="button" id="btnBorrarCliente"><span class="glyphicon glyphicon-remove"></span></button>
						      	</span>
						    </div>
						</div>
					</div>
				</form>
				<hr>
				<h5>Para dar de alta un nuevo cliente:</h5>
				<div class="col-12 btn-group-justified">
					<a href="AgregarCliente" type="button" class="btn btn-primary">ALTA NUEVO CLIENTE</a>
				</div>
			</div>
		</div>
	</div>
		
		<jsp:include page="jsGeneral.jsp" />
		<script type="text/javascript" src="js/clientes.js"></script>
</body>
</html>