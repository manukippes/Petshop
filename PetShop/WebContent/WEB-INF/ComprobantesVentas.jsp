<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.MedioPago"%>
<%@page import="logica.ControladorDeVenta"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	<jsp:include page="cssGeneral.jsp" />	
	
	<title>SGPS - Comprobantes de Ventas</title>
</head>
<body onload="iniciar('comprobantesVentas');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container panel panel-default colorPanel">
	 <div class="panel-body">
		<br>
		<br>	
		<h4><strong>COMPROBANTES DE VENTAS</strong></h4>
		<hr>
		<div class="container-fluid">
			<form class="form" action="ComprobantesVentasResultados" method="post" id="form_comprobantes_venta">
			
			
				<div class="form-group row">
            		<label class="sr-only">Fecha Desde</label>
				    <div class="col-lg-6 col-md-12" id="fechaDesdeGroup">
				    	<small id="fechaDesdeHelp" class="form-text text-muted">Fecha m&iacute;nima</small>
				    	<input type="date" class="form-control" name="fechaDesde" id="fechaDesde" aria-describedby="fechaDesdeHelp">
					</div>
					

					<label class="sr-only">Fecha Hasta</label>
				    <div class="col-lg-6 col-md-12" id="fechaHastaGroup">
				    	<small id="fechaHastaHelp" class="form-text text-muted">Fecha m&aacute;xima</small>
				    	<input type="date" class="form-control" name="fechaHasta" id="fechaHasta" aria-describedby="fechaHastaHelp">
					</div>
				</div>
				
				<div class="form-group row">
            		<label class="sr-only">Producto</label>
				    <div class="container-fluid col-12" id="productoGroup">
				    	<small id="productoHelp" class="form-text text-muted">Producto</small>
				    	<input type="text" class="form-control" name="producto" id="producto" aria-describedby="productoHelp" placeholder="Ingres&aacute; descripci&oacute;n o id de un producto">
					</div>
				</div>
				            	
            	<div class="form-group row">
            		
            		<label class="sr-only">Importe minimo</label>
				    <div class="col-lg-6 col-md-12" id="importeDesdeGroup">
				    	<small id="importeDesdeHelp" class="form-text text-muted">Importe m&iacute;nimo de la venta</small>
				    	<input type="text" class="form-control" name="importeDesde" id="importeDesde" aria-describedby="importeDesdeHelp" placeholder="Ingres&aacute; el importe total m&iacute;nimo de la venta">
					</div>	
				

	    			<div class=" selectContainer col-lg-6 col-md-12" id="medioPagoGroup">
	    				<label class="sr-only">Medio de pago</label>
					    <small id="medioPagoHelp" class="form-text text-muted">Medio de Pago</small>
					    <select class="form-control" name="medioPago" id="medioPago" aria-describedby="medioPagoHelp" required>
							<option value="medioPago">Todos los medios de pago</option>
							<% ControladorDeVenta ctrlVenta  = new ControladorDeVenta();
							ArrayList<MedioPago> mediosPago = ctrlVenta.getMediosPago();
							for(MedioPago medioPago : mediosPago){
								
							%>
							<option value="<%=medioPago.getIdMedioPago()%>"><%=medioPago.getTipo()%></option>
							<%	
							}
							%>
					    </select>
					</div>
				</div>
					
				<div class="form-group row">
	            	
            		<label class="sr-only">Cliente</label>
				    <div class="container-fluid col-12" id="clienteGroup">
				    	<small id="clienteHelp" class="form-text text-muted">Cliente</small>
				    	<input type="text" class="form-control" name="cliente" id="cliente" aria-describedby="clienteHelp" placeholder="Ingres&aacute; nombre, apellido o id de un cliente">
					</div>
				</div>
				
				<div class="form-group row">
					<div class=" selectContainer col-lg-6 col-md-12" id="medioPagoGroup">
	    				<label class="sr-only">Envios</label>
					    <small id="conRetiroHelp" class="form-text text-muted">Incluy&oacute; Env&iacute;o</small>
					    <select class="form-control" name="conRetiro" id="conRetiro" aria-describedby="conRetiroHelp" required>
							<option value="conRetiro">Indistinto</option>
							<option value="Si">Si</option>
							<option value="No">No</option>
					    </select>
					</div>	
				</div>	
					
				<hr>	
					
				<div class="form-group row">		
					<div class="col-lg-12">
						<input type="submit" id="btnObtenerVentas" value="Obtener Ventas" class="col-lg-2 col-xs-12 btn btn-primary btn-lg pull-right">
					</div>
				</div>
            </form> 
        </div>
      </div>
   	</div>

	<jsp:include page="jsGeneral.jsp" />
	<script type="text/javascript" src="js/mantProductos.js"></script>
			
</body>

</html>