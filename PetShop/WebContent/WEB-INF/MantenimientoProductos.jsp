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
	<jsp:include page="cssGeneral.jsp" />
	
	<title>SGPS - Mantenimiento de Productos</title>
</head>
<body onload="iniciar('administracion');filtrarTabla();">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container panel panel-default colorPanel">
	 <div class="panel-body">">
		<br>
		<br>	
		<h4><strong>M&Oacute;DULO DE MANTENIMIENTO DE PRODUCTOS</strong></h4>
		<hr>
		
		<h5>Para dar de alta un nuevo producto:</h5>
		<div class="col-12 btn-group-justified">
			
			<a href="AgregarProducto" type="button" class="btn btn-primary">ALTA NUEVO PRODUCTO</a>
		</div>
		<hr>
		<h5>Para modificar o eliminar un producto existente:</h5>
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
				<div id="collapse1" class="panel-collapse collapse">
					<div class="panel-body">
						<div class="input-group input-group-sm">
							<input type="number" min="1" id="filtrarIdProductoxs" name="filtrarIdProductoxs" title="Filtrar por Id" class="form-control" aria-describedby="idHelp" placeholder="Filtrar por Id de Producto"></input>
							<input type="text" id="filtrarNombrexs" name="filtrarNombrexs" title="Filtrar por nombre" class="form-control" placeholder="Filtrar por Nombre"></input>
							<input type="text" id="filtrarPresentacionxs" name="filtrarPresentacionxs" title="Filtrar por presentacion" class="form-control" placeholder="Filtrar por Presentaci&oacute;n"></input>
							<input type="text" id="filtrarPrecioDesdexs" name="filtrarPrecioDesdexs" class="form-control" title="Filtrar precios desde" placeholder="Filtrar Precios Desde"></input>
							<input type="text" id="filtrarPrecioHastaxs" name="filtrarPrecioHastaxs" class="form-control" title="Filtrar precios hasta"placeholder="Filtrar Precios Hasta"></input>
							<input type="number" id="filtrarStockDesdexs" name="filtrarStockDesdexs" title="Filtrar stock desde" class="form-control" placeholder="Filtrar Stock Desde"></input>
							<input type="number" id="filtrarStockHastaxs" name="filtrarStockHastaxs" title="Filtrar stock hasta" class="form-control" placeholder="Filtrar Stock Hasta"></input>
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
							<div class="">
								<label class="sr-only">Filtrar por Id</label>
								<input type="number" min="1" id="filtrarIdProducto" name="filtrarIdProducto" title="Filtrar por Id" class="form-control hidden-xs" aria-describedby="idHelp" placeholder="Filtrar..."></input>
								<hr class="hidden-xs">
								<span>Id Producto</span>
							</div>
						</th>
						<th id="nombreProducto">
							<input type="text" id="filtrarNombre" name="filtrarNombre" title="Filtrar por nombre" class="form-control hidden-xs" placeholder="Filtrar..."></input>
							<hr class="hidden-xs">
							<span>Nombre</span>
						</th>
						<th id="presentacionProducto">
							<input type="text" id="filtrarPresentacion" name="filtrarPresentacion" title="Filtrar por presentacion" class="form-control hidden-xs" placeholder="Filtrar..."></input>
							<hr class="hidden-xs">
							<div>Presentaci&oacute;n</div>
						<th>
							<div class="">
								<div class="input group input-group-sm">
									<input type="text" id="filtrarPrecioDesde" name="filtrarPrecioDesde" class="form-control hidden-xs" title="Filtrar precios desde" placeholder="Desde"></input>
									<input type="text" id="filtrarPrecioHasta" name="filtrarPrecioHasta" class="form-control hidden-xs" title="Filtrar precios hasta"placeholder="Hasta"></input>
									<hr class="hidden-xs">
									<div>Precio</div>
								</div>
							</div>
						</th>
						<th>
							<div class="">
								<div class="input group input-group-sm">
									<input type="number" id="filtrarStockDesde" name="filtrarStockDesde" title="Filtrar stock desde" class="form-control hidden-xs" placeholder="Desde"></input>
									<input type="number" id="filtrarStockHasta" name="filtrarStockHasta" title="Filtrar stock hasta" class="form-control hidden-xs" placeholder="Hasta"></input>
									<hr class="hidden-xs">
									<div>Stock&nbsp;</div>
								</div>
							</div>							
						</th>
						<th class="col-sm-3 col-lg-2"> 
							<div class="input group input-group-sm">
									<input type="button" id="btnLimpiarfiltros" name="limpiarFiltros" class="btn btn-primary btnLimpiarfiltros hidden-xs" value="Limpiar Filtros"></input>
									<hr class="hidden-xs">
									<div>Acciones&nbsp;</div>
								</div>
						</th>
					</tr>
				</thead>
				<tbody>
		 		</tbody>
			</table>
		</div>
			
			<jsp:include page="jsGeneral.jsp" />
			<script type="text/javascript" src="js/mantProductos.js"></script>
			
		</div>
	  </div>
	</body>
</html>