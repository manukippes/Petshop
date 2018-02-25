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
		<jsp:include page="cssGeneral.jsp" />
		<title>Insertar nombre del comercio</title>
	</head>
	<body onload="iniciar('ventas');">
		<jsp:include page="Navbar.jsp" />
		
		<div class="container panel panel-default colorPanel">
	 		<div class="panel-body">
				<br>
				<br>
				<h4><strong>VENTA ONLINE </strong></h4>
				<hr>
				<form action="#" method="post" class="form-horizontal"  id="formVentas">
			
				<h4>Buscar productos</h4>
				
				<div class="form-group">	
					<label class="sr-only">Ingresa nombre o descripcion del producto</label>
					<div class="col-xs-12 col-sm-5 col-md-6" id="inputProductoGroup">
						<input class="form-control" name="inputProducto" id="inputProducto" type="text" placeholder="Ingres&aacute; descripci&oacute;n parcial o nombre del producto">
						<small class="text-danger hidden" id="buscarVacio">Para buscar debes completar este campo</small>
					</div>
					<div class="col-xs-12 col-sm-3">
						<button class="btn btn-primary form-control" id="buscarProductosVenta"> <span class="glyphicon glyphicon-search" ></span> Buscar </button>
					</div>
					<div class="col-xs-12 col-sm-4 col-md-3">
						<a href="#listadoProductos" class="btn btn-default form-control" data-toggle="modal"> Buscar por Categor&iacute;a <span class="glyphicon glyphicon-th-list"></span> </a>
					</div>		
				</div>
				<hr>
			
				<div class="table-responsive">
					<table id="tabla" class="table table-striped">
						<thead>
							<tr class="active hidden" id="cabeceraTablaBusqueda">
								<th>
									<div class="imagen">
										<label class="sr-only">Imagen</label>
										
									</div>
								</th>
								<th class="hidden">
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
										
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
				 		</tbody>
					</table>
				</div>
				
				
				<!-- PANEL MODAL DE LISTADO DE PRODUCTOS -->
					<div class="modal fade" id="listadoProductos">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<strong>BUSCAR PRODUCTOS POR CATEGORIA</strong>
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
									</div>
									<br>

									<div class="table-responsive">
										<table id="tablaAgregarProducto" class="table table-striped table-hover tablaAgregarProducto">
											<thead>
												<tr class="active">
													<th>
														<div class="imagen">
															<label class="sr-only">Imagen</label>
															
														</div>
													</th>
													<th class="hidden">
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
				
				
				
					
				
			</form>
			</div>
		</div>
		
	<jsp:include page="jsGeneral.jsp" />
	<script type="text/javascript" src="js/ventaOnline.js"></script>
		
	</body>

</html>