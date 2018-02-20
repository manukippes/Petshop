<%@page import="java.math.BigDecimal"%>
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
		<jsp:include page="cssGeneral.jsp" />
		
		<title>Sistema de Gestion de Pet Shops</title>

	</head>
	<body onload="iniciar('ventas');">
		<jsp:include page="Navbar.jsp" />		 
		 
		<div class="container panel panel-default colorPanel">
		 	<div class="panel-body">
				<br>
				<br>
				<h4><strong>VENTA ONLINE</strong> - Realizar Pago</h4>
				<hr>
				
				<form action="" method="post" class="form-horizontal"  id="formVentasPaso2">
				
					<div class="input-group">
						<h4>Est&aacute;s por comprar:</h4>
					</div>
					<div class="table-responsive">
						<table id="tablaVentaActualPaso2" class="table table-striped table-hover tablaVentaActualPaso2">
							<thead>
								<tr class="active">
									<th class="hidden">
										<div class="idProducto">
											<label class="sr-only">Id Producto</label>
											<span>Id Producto</span>
										</div>
									</th>
									<th></th>
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
											<span>Subtotal </span>
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<% ArrayList<ArrayList<String>> productos = (ArrayList<ArrayList<String>>)session.getAttribute("productosVenta");
								ControladorDeProducto ctrlProducto = new ControladorDeProducto();
								Producto prod = new Producto();
								
								//SE TRABAJA CON BIGDECIMAL POR PROBLEMAS DE REDONDEO
								BigDecimal cantidad = BigDecimal.valueOf(0,2);
								BigDecimal total = BigDecimal.valueOf(0,2);
								BigDecimal precio = BigDecimal.valueOf(0,2);
								BigDecimal subtotal = BigDecimal.valueOf(0,2);

								for(ArrayList<String> producto : productos){
									prod.setIdProducto(Integer.parseInt(producto.get(0)));
									prod = ctrlProducto.getProducto(prod);
									precio = BigDecimal.valueOf(prod.getPrecio());
									cantidad = BigDecimal.valueOf(Double.parseDouble(producto.get(1)));
									total = total.add(cantidad.multiply(precio));
									total = total.setScale(2, java.math.RoundingMode.CEILING);
									subtotal = (precio.multiply(cantidad)).setScale(2, java.math.RoundingMode.CEILING);
								%>
								<tr class="">
									<td class="hidden"><%=prod.getIdProducto() %></td>
									<td id="imagen">
										<div class="">
											<img class="img-thumbnail" src="<%=prod.getImagen() %>" width='50px' height='50px'/>
										</div>
									</td>
									<td class="align-middle"><%=prod.getNombre() %></td>
									<td><%=prod.getPresentacion() %></td>
									<td><span class='fa fa-dollar'></span> <%=prod.getPrecio() %></td>
									<td><%=producto.get(1) %></td>
									<td><span class='fa fa-dollar'></span> <%=subtotal%></td>
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
					    	<small id="totalHelp" class="form-text text-muted">Total a Pagar</small>
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
					<div id="datosTarjeta" class="form-group row hidden">
						    
						<!-- Input de numero de tarjeta -->
	            		<label class="sr-only">Numero de Tarjeta</label>
					    <div class="col-xs-12 col-sm-6 col-lg-4" id="numeroTarjetaGroup">
					    	<small id="numeroTarjetaHelp" class="form-text text-muted">N&uacute;mero de tarjeta</small>
					    	<input type="text" class="form-control" name="numeroTarjeta" id="numeroTarjeta" aria-describedby="numeroTarjetaHelp" placeholder="Ingres&aacute; los 16 d&iacute;gitos de la tarjeta" required>
						</div>
						<div class="col-xs-12 col-sm-6 col-lg-4" id="nombreApellidoGroup">
					    	<small id="nombreApellidoHelp" class="form-text text-muted">Nombre y apellido del titular</small>
					    	<input type="text" class="form-control" name="nombreApellido" id="nombreApellido" aria-describedby="nombreApellidoHelp" placeholder="Ingres&aacute; el nombre y apellido del titular" required>
						</div>
						<div class="col-xs-6 col-sm-4 col-lg-2" id="vencimientoMesGroup">
					    	<small id="vencimientoMesHelp" class="form-text text-muted">Vencimiento mes</small>
					    	<select class="form-control" name="vencimientoMes" id="vencimientoMes" aria-describedby="vencimientoMesHelp">
						    	<%for(int i=1;i<=12;i++){%>
						    		<option value="<%=i %>"> <%=i %> </option>
						    	<%} %>
						    </select>
						</div>
						<div class="col-xs-6 col-sm-4 col-lg-2" id="vencimientoAnioGroup">
					    	<small id="vencimientoAnioHelp" class="form-text text-muted">Vencimiento a&ntilde;o</small>
					    	<select class="form-control" name="vencimientoAnio" id="vencimientoAnio" aria-describedby="vencimientoAnioHelp">
						    	<%for(int i=2018;i<=2030;i++){%>
						    		<option value="<%=i %>"> <%=i %> </option>
						    	<%} %>
						    </select>
						</div>
						<div class="col-xs-6 col-sm-4" id="codigoSeguridadGroup">
					    	<small id="codigoSeguridadHelp" class="form-text text-muted">Codigo de Seguridad</small>
					    	<input type="text" class="form-control" name="codigoSeguridad" id="codigoSeguridad" aria-describedby="codigoSeguridadHelp" placeholder="Ingres&aacute; el c&oacute;digo de seguridad" required>
						</div>
					</div>
					
					<hr class="divisor">
					<div class="input-group">
						<h4>Datos de contacto:</h4>
					</div>
					
					<div class="table" id="tablaClienteSeleccionado">
						<table class="table table-striped" >
							<tr id="idUsuario">
							<%Usuario usuario = (Usuario) session.getAttribute("user"); %>
								<td id="nombreApellidoCliente"><%=usuario.getNombre() %>, <%=usuario.getApellido() %></td>
								<td id="telefonoCliente"><%=usuario.getTelefono() %></td>
								<td id="direccionCliente"><%=usuario.getDireccion() %></td>
							</tr>
						</table>
					</div>
					
					<hr class="divisor">
					<div class="form-group row">
						<div id="enviosRadioGroup" class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
							<div class="radio col-xs-6 col-sm-12" >
								<label for ="retiroLocal">
									<input type="radio" name="opcion" value="local" class="rbutton" checked> Retiro en local
								</label>
							</div>
							<div class="radio col-xs-6 col-sm-12" >
								<label for ="envioDomicilio">
									<input type="radio" name="opcion" value="domicilio" class="rbutton"> Env&iacute;o a domicilio
								</label>
							</div>
						</div>
						<div class="visible-xs">
							<br>
							<br>
							<br>
						</div>
						
						<div id="domicilioEnvioGroup" class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
							<small id="domicilioEnvioHelp" class="form-text text-muted"><strong>Domicilio env&iacute;o</strong></small>
							<input type="text" class="form-control" name="domicilioEnvio" id="domicilioEnvio" aria-describedby="domicilioEnvioHelp" placeholder="Ingres&aacute; la direcci&oacute;n de destino de la compra" disabled>
						</div>	
					</div>
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
						<div class="col-sm-3 col-xs-12 pull-right hidden">
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
										<div class="col-xs-12 col-sm-8" id="inputClienteGroup">
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
										    <select class="form-control" name="clienteSeleccionado" id="cliente" aria-describedby="clienteHelp" disabled>
										    	<option value="cliente">Seleccion&aacute; un cliente</option>
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
	
		<jsp:include page="jsGeneral.jsp" />
		<script type="text/javascript" src="js/ventaOnline.js"></script>
		<script type="text/javascript" src="js/ventaOnlinePaso2.js"></script>
		
	</body>

</html>