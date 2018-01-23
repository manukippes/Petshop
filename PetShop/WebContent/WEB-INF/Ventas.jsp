<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Producto"%>
<%@page import="entidades.Categoria"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="logica.ControladorDeVenta"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	

	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/ventas.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
			
	<title>SGPS - Ventas</title>
</head>
<body onload="iniciar('ventas');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container panel panel-default colorPanel">
	 	<div class="panel-body">
			<br>
			<br>
			<h4><strong>NUEVA VENTA</strong> - Paso 1 de 2</h4>
			<hr>
			
			<form action="#" method="post" class="form-horizontal"  id="formVentas">
			
				<h4>Buscar productos</h4>
				
				<div class="form-group">	
					<label class="sr-only">Ingresa descripcion o codigo</label>
					<div class="col-sm-6 col-xs-12" id="inputProductoGroup">
						<input class="form-control" name="inputProducto" id="inputProducto" type="text" placeholder="Ingres&aacute; descripci&oacute;n parcial o c&oacute;digo de producto">
					</div>
					<div class="col-sm-3 col-xs-12 ">
						<button class="btn btn-primary form-control" id="buscarProductosVenta"> <span class="glyphicon glyphicon-search" ></span> Buscar </button>
					</div>
					<div class="col-sm-3 col-xs-12 ">
						<a href="#listadoProductos" class="btn btn-default form-control" data-toggle="modal"> Listado <span class="glyphicon glyphicon-th-list"></span> </a>
					
					</div>		
				</div>
				<hr>
			
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
								</th>
								<th>
									<label class="sr-only">Precio</label>
									<span>Precio</span>	
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
				</div>
				<hr class="divisor">
				<div class="input-group">
					<h4>Productos de la venta actual</h4>
				</div>
				<div class="table-responsive">
					<table id="tablaVentaActual" class="table table-striped table-hover tablaVentaActual">
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
									<span>Precio</span>	
								</th>
								<th>
									<label class="sr-only">Cantidad</label>
									<span>Cantidad</span>				
								</th>
								<th> 
									<div class="'col-sm-3 col-lg-2 input-group">
										<span>Eliminar </span>
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
						
				 		</tbody>
					</table>
				</div>
				<div class="form-group row">
					<label class="sr-only">Subtotal</label>
				    <div class="col-sm-6 col-lg-4 pull-right">
				    	<small id="subtotalHelp" class="form-text text-muted">Subtotal</small>
				    	<div class="input-group">
				    		<span class="input-group-addon"><small>$</small></span>
							<input class="form-control" type="text" name="subtotal" value="0" id="subtotal" placeholder="Subtotal" disabled>
				    	</div>
					</div>
				</div>
				
				<!-- PANEL MODAL DE LISTADO DE PRODUCTOS -->
					<div class="modal fade" id="listadoProductos">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<strong>LISTADO GENERAL DE PRODUCTOS</strong>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									
									<div class="container-fluid">
										<div class="selectContainer col-xs-12 col-sm-8" id="categoriaGroup">
						    				<label class="sr-only">Selecciona una categoria</label>
										    <select class="form-control" name="categoriaSeleccionada" id="categoria" aria-describedby="categoriaHelp" required>
										    	<option value="categoria">Seleccion&aacute; una categor&iacute;a</option>
										    	<% ControladorDeProducto ctrlProducto = new ControladorDeProducto();
										    		ArrayList<Categoria> categorias = ctrlProducto.getCategorias();
										    		for(Categoria cate : categorias){
										    			%>
										    			
										    			<option value="<%= cate.getIdCategoria()%>"><%=cate.getNombre()%></option>			    			
											   			
											   			<%
											    		}
												    	%>
										    </select>
										</div>
										<br>
										<br>
										<div class="selectContainer col-xs-12 col-sm-8" id="subcategoriaGroup">
						    				<label class="sr-only">Selecciona una subcategoria</label>
										    <select class="form-control" name="subcategoriaSeleccionada" id="subcategoria" aria-describedby="subcategoriaHelp" disabled>
										    	<option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>
										    </select>
										</div>
										<div class="col-xs-12 checkbox">
											<input type="checkbox" id="soloStock" >Mostrar solo productos con stock</input>
										</div>								
									</div>
									<br>

									<div class="table-responsive">
										<table id="tablaAgregarProducto" class="table table-striped table-hover tablaAgregarProducto">
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
													</th>
													<th>
														<label class="sr-only">Precio</label>
														<span>Precio</span>	
													</th>
													<th>
														<label class="sr-only">Cantidad</label>
														<span>Cantidad</span>				
													</th>
													<th> 
														<div class="'col-sm-3 col-lg-2 input-group">
															<span>Agregar </span>
														</div>
													</th>
												</tr>
											</thead>
											<tbody>
												
									 		</tbody>
										</table>
									</div>									
									<br>
								</div>								
							</div>
						</div>
					</div>
				
				<div class="form-group col-sm-3 col-xs-12 pull-right">
					<button class="btn btn-primary btn-lg btn-group-justified" type="submit" id="btnContinuar"> Continuar </button>
				</div>
			</form>
		</div>
	</div>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
	<!-- <script src="js/bootstrap.min.js"></script> -->
	 
</body>
</html>