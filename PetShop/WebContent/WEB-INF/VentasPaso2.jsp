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
									<td>
										<div class="idProducto">
											<label class="sr-only">Id Producto</label>
											<span><%=prod.getIdProducto() %></span>
										</div>
									</td>
									<td id="nombreProducto">
										<label class="sr-only">Nombre de Producto</label>
										<span><%=prod.getNombre() %></span>
									</td>
									<td id="presentacionProducto">
										<label class="sr-only">Presentacion</label>
										<span><%=prod.getPresentacion() %></span>
									<td>
										<label class="sr-only">Precio</label>
										<span><%=prod.getPrecio() %></span>	
									</td>
									<td>
										<label class="sr-only">Cantidad</label>
										<span><%=producto.get(1) %> </span>				
									</td>
									<td> 
										<div class="'col-sm-3 col-lg-2 input-group">
											<span><%=prod.getPrecio()*cantidad %></span>
										</div>
									</td>
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
					<div class="form-group ">
						<div class="col-sm-3 col-xs-12 pull-right">
							<button class="btn btn-primary form-control" id="confirmarVenta"> Finalizar </button>
						</div>	
						<div class="col-sm-3 col-xs-12 pull-right">
							<button class="btn btn-default form-control" id="volverPaso1"> Volver </button>
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