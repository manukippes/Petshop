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
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
	
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/listadoProductos.js"></script>

	
	<title>SGPS - Mantenimiento de Productos</title>
</head>
<body onload="iniciar('administracion');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>	
		<h4><strong>M&Oacute;DULO DE MANTENIMIENTO DE PRODUCTOS</strong></h4>
		<hr>
		
		<h5>Para dar de alta un nuevo producto:</h5>
		<div class="col-12 btn-group-justified">
			
			<a href="AgregarProducto" type="button" class="btn btn-primary">ALTA NUEVO PRODUCTO</a>
		</div>
		<hr>
		<h5>Para consultar o modificar un producto:</h5>
		<%ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		ArrayList<Producto> productos = ctrlProducto.getProductos(); %>
		
		<div class="table-responsive">
			<table id="tabla" class="table table-striped">
				<thead>
				  <tr class="active">
				    <th>C&oacute;digo</th>
				    <th>Nombre</th>
				    <th>Presentaci&oacute;n</th>
				    <th class="col-sm-1">Precio</th>
				    <th class="col-sm-1">Stock</th>
				    <th class="col-sm-3 col-lg-2">Acciones</th>
				  </tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="">
								<label class="sr-only">Filtrar por codigo</label>
								<input type="text" id="filtrarCodigo" name="filtrarCodigo" title="Filtrar por codigo" class="form-control" aria-describedby="codigoHelp" placeholder="C&oacute;digo"></input>
							</div>
						</td>
						
						<td><input type="text" id="filtrarNombre" name="filtrarNombre" title="Filtrar por nombre" class="form-control" placeholder="Nombre"></input></td>
						<td><input type="text" id="filtrarPresentacion" name="filtrarPresentacion" title="Filtrar por presentaci&oacute;n" class="form-control" placeholder="Presentaci&oacute;n"></input></td>
						<td>
							<div class="">
								<div class="input group input-group-sm">
									<input type="text" id="filtrarPrecioDesde" name="filtrarPrecioDesde" class="form-control" title="Filtrar precios desde" placeholder="Desde"></input>
									<input type="text" id="filtrarPrecioHasta" name="filtrarPrecioHasta" class="form-control" title="Filtrar precios hasta"placeholder="Hasta"></input>
								</div>
							</div>
						</td>
						<td>
							<div class="">
								<div class="input group input-group-sm">
									<input type="text" id="filtrarStockDesde" name="filtrarStockDesde" title="Filtrar stock desde" class="form-control col-lg-2" placeholder="Desde"></input>
									<input type="text" id="filtrarStockHasta" name="filtrarStockHasta" title="Filtrar stock hasta" class="form-control" placeholder="Hasta"></input>
								</div>
							</div>
						</td>
						<td></td>
					</tr>
		 		<%=ctrlProducto.getHtml() %>	
				
		 		</tbody>
			</table>
			
			
	</div>
	
		
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>