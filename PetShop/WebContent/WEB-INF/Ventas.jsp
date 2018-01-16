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
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	
	<title>SGPS - Ventas</title>
</head>
<body onload="iniciar('ventas');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>
		<h4><strong>Nueva venta</strong> - Paso 1 de 2</h4>
		<hr>
		
		<form action="" method="post" class="form-horizontal">
			
			<br>
			<h5>Buscar productos</h5>
			
			<div class="form-group col-md-offset-2">	
				<label class="sr-only">Ingresa descripcion o codigo</label>
				
				<div class="col-md-8 col-xs-12">
					<input class="form-control" name="inputProducto" id="inputProducto" type="text" placeholder="Ingres&aacute; descripci&oacute;n parcial o c&oacute;digo de producto">
				</div>
				<div class="col-md-4 ">
					<button class="btn btn-primary" id="buscarProductosVenta"> <span class="glyphicon glyphicon-search" ></span> Buscar </button>
					<button class="btn btn-default" onclick = "this.form.action = 'listadoProductos';  this.form.submit();" > Listado <span class="glyphicon glyphicon-th-list"></span> </button>
				</div>
			</div>
			<hr>
		</form>
		<div class="table-responsive">
			<table id="tabla" class="table table-striped table-hover">
				<thead>
					<tr class="active">
						<th>
							<div class="idProducto">
								<label class="sr-only">Id Producto</label>
								<span>Id Producto</span>
							</div>
						</th>
						<th id="nombreProducto">
							<label class="sr-only">Nombre de Producto</label>
							<span>Nombre</span>
						</th>
						<th id="presentacionProducto">
							<label class="sr-only">Presentacion</label>
							<span>Presentaci&oacute;n</span>
						<th>
							<label class="sr-only">Precio</label>
							<span>Precio</</span>	
						</th>
						<th>
							<label class="sr-only">Cantidad</label>
							<span>Cantidad</span>				
						</th>
						<th> 
							<div class="input-group">
								<span>Agregar </span>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
		 		</tbody>
			</table>
			<hr>
			
	</div>
	
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
		<!-- <script src="js/bootstrap.min.js"></script> -->

</body>
</html>