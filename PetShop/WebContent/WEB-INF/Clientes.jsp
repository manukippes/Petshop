<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
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
		            <!-- Input de cliente -->
	            		<label class="sr-only">Cliente</label>
					    <div class="col-lg-6 col-md-12" id="nombreGroup">
					    	<input type="text" class="form-control" name="nombre" id="nombre" aria-describedby="nombreHelp" placeholder="Ingres&aacute; nombre, apellido o id del cliente" required>
						</div>
						
					<!-- Botton Ver-->
						<label class="sr-only">Ver</label>
					    <div class="col-lg-6 col-md-12" id="presentacionGroup">
					    	<input type="button" class="btn btn-primary btn-block" value="VER">
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
		
		<script src="js/jquery-latest.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		
		<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
			
</body>
</html>