<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>	
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Producto"%>
<%@page import="entidades.MedioPago"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="logica.ControladorDeVenta"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta charset="UTF-8">
		
		<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="css/estilos.css" type="text/css">
		<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/ventas.js"></script>
		
		<title>Sistema de Gestion de Pet Shops</title>
	</head>
	<body>
		<jsp:include page="Navbar.jsp" />		 
		 
		<div class="container panel panel-default colorPanel">
		 	<div class="panel-body">
				<br>
				<br>
				<h4><strong>NUEVA VENTA</strong> - Paso 2 de 2</h4>
				<hr>
				
				<form action="" method="post" class="form-horizontal"  id="formVentasPaso2">
				
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
											<span>Subtotal </span>
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<% ArrayList<ArrayList<String>> productos = (ArrayList<ArrayList<String>>)session.getAttribute("productosVenta");
								ControladorDeProducto ctrlProducto = new ControladorDeProducto();
								Producto prod = new Producto();
								int cantidad = 0;
								Double total = 0.0;
								for(ArrayList<String> producto : productos){
									prod.setIdProducto(Integer.parseInt(producto.get(0)));
									prod = ctrlProducto.getProducto(prod);
									cantidad = Integer.parseInt(producto.get(1));
									total += prod.getPrecio()*cantidad;
								%>
								<tr class="">
									<td><%=prod.getIdProducto() %></td>
									<td><%=prod.getNombre() %></td>
									<td><%=prod.getPresentacion() %></td>
									<td><%=prod.getPrecio() %></td>
									<td><%=producto.get(1) %></td>
									<td><%=prod.getPrecio()*cantidad %></td>
								</tr>
								<%
								}
								%>
					 		</tbody>
						</table>
					</div>
					<div class="form-group row">
						<label class="sr-only">Total</label>
					    <div class="col-sm-6 col-lg-4 pull-right">
					    	<small id="totalHelp" class="form-text text-muted">Total a Cobrar</small>
					    	<div class="input-group">
					    		<span class="input-group-addon"><small>$</small></span>
								<input class="form-control" type="text" name="total" value="<%=total %>" id="total" placeholder="Total" disabled>
					    	</div>
						</div>
					</div>
					<hr class="divisor">
					<div class="input-group">
						<h4>Seleccionar Medio de Pago</h4>
					</div>
					<div class="form-group row">
	            		<!-- Combobox de Medio de pago -->
		    			<div class=" selectContainer col-md-4 col-sm-12"id="medioPagoGroup">
		    				<label class="sr-only">Medio de pago</label>
						    <small id="medioPagoHelp" class="form-text text-muted">Seleccion&aacute; un medio de pago.</small>
						    <select class="form-control" name="medioPago" id="medioPago" aria-describedby="medioPagoHelp" required>
						    	<option value="seleccione un medio">Seleccion&aacute; un medio de pago</option>
								<% ControladorDeVenta ctrlVenta = new ControladorDeVenta();
									ArrayList<MedioPago> mediosPago = ctrlVenta.getMediosPago();
									for(MedioPago medioPago : mediosPago){
										%>
										<option value="<%=medioPago.getIdMedioPago()%>"><%=medioPago.getTipo()%></option>
									<%
									}
								%>
						    </select>
						</div>
						<!-- Combobox de Tarjeta -->
		    			<div class=" selectContainer col-md-4 col-sm-12 hidden" id="tarjetaGroup">
		    				<label class="sr-only">Tarjeta</label>
						    <small id="tarjetaPagoHelp" class="form-text text-muted">Seleccion&aacute; una tarjeta.</small>
						    <select class="form-control" name="tarjeta" id="tarjeta" aria-describedby="tarjetaHelp" required disabled>
						    
						    </select>
						</div>
						<!-- Combobox de -cuotas -->
		    			<div class=" selectContainer col-md-2 col-sm-12 hidden" id="cuotasGroup">
		    				<label class="sr-only">Cuotas</label>
						    <small id="cuotasHelp" class="form-text text-muted">Cantidad de Cuotas</small>
						    <select class="form-control" name="cuotas" id="cuotas" aria-describedby="cuotasHelp" required disabled>
						    	
						    </select>
						</div>
					</div>
					<hr class="divisor">
					<div class="input-group">
						<h4>Seleccionar un Cliente (Opcional)</h4>
					</div>
					<div class="table-responsive col-sm-9 hidden" id="tablaClienteSeleccionado">
						<table class="table table-bordered active" >
							<tr id="idUsuario">
								<td id="nombreApellidoCliente"></td>
								<td id="telefonoCliente"></td>
								<td id="direccionCliente"></td>
							</tr>
						</table>
					</div>
					<div class="form-group">
						<div class="col-sm-3 col-xs-12 pull-right">
							<a href="#buscarCliente" class="btn btn-primary form-control" id="btnAgregarClienteVenta" data-toggle="modal"> <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span> Agregar Cliente </a>
						</div>
						<div class="col-sm-3 col-xs-12 pull-right">
							<a href="#quitarCliente" class="btn btn-danger form-control hidden" id="btnQuitarClienteVenta" > <span class="glyphicon glyphicon-remove "> </span><span class="glyphicon glyphicon-user"></span> Quitar Cliente </a>
						</div>
					</div>
					<hr class="divisor">
					<div class="input-group">
						<h4>Observaciones</h4>
					</div>
					<div class="form-group">
						<div class="col-sm-9 col-xs-12">
							<textarea class="form-control" rows="5" id="observaciones" placeholder="Escrib&iacute; ac&aacute; los datos adicionales de ser necesarios"></textarea>
						</div>
					</div>
					<hr class="divisor">
					<div class="form-group ">
						<div class="col-sm-3 col-xs-12 pull-right">
							<button class="btn btn-primary form-control" id="confirmarVenta"> Finalizar </button>
						</div>	
						<div class="col-sm-3 col-xs-12 pull-right">
							<button class="btn btn-default form-control" id="volverPaso1"> Volver </button>
						</div>
					</div>
					
				<!-- PANEL MODAL DE BUSQUEDA DE CLIENTE -->
					<div class="modal fade" id="buscarCliente">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<strong>BUSQUEDA DE CLIENTES</strong>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									<div class="container-fluid">
									<br>
										<div class="col-xs-12 col-sm-8">
											<input class="form-control" placeholder="Ingres&aacute; nombre o apellido" id="inputCliente"></input>
										</div>
										<div class="col-xs-12 col-sm-4">
											<button class="btn btn-primary" id="btnBuscarCliente"><span class="glyphicon glyphicon-search"></span> Buscar</button>
										</div>								
									</div>
									
									<br>
									<div class="container-fluid">
										<div class="selectContainer col-xs-12 col-sm-8" id="clienteGroup">
						    				<label class="sr-only">Seleccione un cliente</label>
										    <select class="form-control" name="clienteSeleccionado" id="cliente" aria-describedby="clienteHelp" required>
										    	<option value="seleccione un cliente">Seleccion&aacute; un cliente</option>
										    </select>
										</div>
										<div class="col-xs-12 col-sm-4">
											<button class="btn btn-primary" id="btnAgregarClienteSeleccionado"><span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span> Agregar Cliente </button>
										</div>								
									</div>
									<br>
									<br>
								</div>								
							</div>
						</div>
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