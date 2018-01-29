<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="css/estilos.css" type="text/css">
		<link rel="stylesheet" href="font-awesome/css/font-awesome.css" type="text/css">
		<link rel="shortcut icon" href="#">

		<title>SGPS - Alta nuevo cliente</title>
	</head>
	<body onload="iniciar('clientes');">
		<jsp:include page="Navbar.jsp" />
		<div class="container panel panel-default colorPanel">
			<div class="panel-body">
				<br>
				<br>	
				<h4><strong>NUEVO CLIENTE - PASO 1 DE 2</strong></h4>
				<hr>
				
				<div class="container-fluid">
					<form class="form" action="AgregarCliente" method="post" enctype="multipart/form-data" id="form_nuevo_cliente">
		            	
		     
		            	<!-- Campo de modificacion / alta -->
		            	<input type="hidden"  name="alta" id="alta" value="alta">
						
		            	<div class="form-group row">
		            	  	<!-- Input de nombre -->
			            		<label class="sr-only">Nombre</label>
							    <div class="col-lg-6 col-md-12" id="nombreGroup">
							    	<small id="nombreHelp" class="form-text text-muted"><strong>Nombre*</strong></small>
							    	<input type="text" class="form-control" name="nombre" id="nombre" aria-describedby="nombreHelp" placeholder="Ingres&aacute; el nombre del cliente a dar de alta">
								</div>
								
							<!-- Input de apellido -->
								<label class="sr-only">Apellido</label>
							    <div class="col-lg-6 col-md-12" id="apellidoGroup">
							    	<small id="apellidoHelp" class="form-text text-muted"><strong>Apellido*</strong></small>
							    	<input type="text" class="form-control" name="apellido" id="apellido" aria-describedby="apellidoHelp" placeholder="Ingres&aacute; el apellido del cliente a dar de alta">
								</div>
						</div>
						<hr>
						
						<div class="form-group row">
			            	<!-- Input de dni-->
			            		<label class="sr-only">DNI</label>
							    <div class="col-lg-6 col-md-12" id="dniGroup">
							    	<small id="precioHelp" class="form-text text-muted"><strong>Dni</strong></small>
							    	<input type="text" pattern="[0-9]" class="form-control" name="dni" id="dni" aria-describedby="dniHelp" placeholder="Ingres&aacute; el dni del cliente a dar de alta">
								</div>
								
							<!-- Input de direccion -->
								<label class="sr-only">Direcci&oacute;n</label>
							    <div class="col-lg-6 col-md-12" id="direccionGroup">
							    	<small id="direccionHelp" class="form-text text-muted"><strong>Direcci&oacute;n</strong></small>
							    	<input type="text" min="0"class="form-control" name="direccion" id="direccion" aria-describedby="direccionHelp" placeholder="Ingres&aacute; la direcci&oacute;n del cliente a dar de alta">
								</div>
						</div>
						<hr>
						
						<div class="form-group row">
							<!-- Input de telefono -->
								<label class="sr-only">Tel&eacute;fono</label>
							    <div class="col-lg-6 col-md-12" id="telefonoGroup">
							    	<small id="direccionHelp" class="form-text text-muted"><strong>Tel&eacute;fono*</strong></small>
							    	<input type="text" class="form-control" name="telefono" id="telefono" aria-describedby="telefonoHelp" placeholder="Ingres&aacute; el tel&eacute;fono, sin guiones, del cliente a dar de alta">
								</div>
						         	
			            	<!-- Input de email-->
			            		<label class="sr-only">Email</label>
							    <div class="col-lg-6 col-md-12" id="emailGroup">
							    	<small id="emailHelp" class="form-text text-muted"><strong>Email</strong></small>
							    	<input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Ingres&aacute; el email del cliente a dar de alta">
								</div>
						</div>
						<hr>	
						<div class="form-group row">		
							<div class="col-lg-12">
								<input type="submit" id="btnAgregarCliente" value="Agregar" class="col-lg-2 col-xs-12 btn btn-primary btn-lg pull-right">
							</div>
						</div>	
		            </form> 
		        </div>
		   	</div>
		</div>
			<script src="http://code.jquery.com/jquery-latest.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="js/main.js"></script>
			<script type="text/javascript" src="js/clientes.js"></script>
	</body>
</html>