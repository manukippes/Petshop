<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.LineaVenta"%>
<%@page import="entidades.Producto"%>
<%@page import="logica.ControladorDeVenta"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.css" type="text/css">
	<script type="text/javascript" src="js/main.js"></script>
	
	<title>SGPS - Comprobantes de Ventas</title>
</head>
<body onload="iniciar('comprobantesVentas');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>
		
		<h4><strong>COMPROBANTES DE VENTAS REGISTRADOS</strong></h4>
		<hr>
		<div class="table-responsive">
			
			<div class="panel-group table-responsive" id="accordion" role="tablist">

			<%
			ControladorDeVenta ctrVenta = new ControladorDeVenta();
			ArrayList<Venta> ventas = (ArrayList<Venta>) session.getAttribute("ventas");
			int i=0;
			for (Venta venta : ventas){
				i++;
			%>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="heading<%=i%>">
					<div class="panel-title">
						<a href="#collapse<%=i%>" data-toggle="collapse" data-parent="#accordion">
							<table id="tabla" class="table">
								<%if (i==1){ %>
								<thead>
									<tr class="active">
									    <th>ID</th>
									    <th>CLIENTE</th>
									    <th>DIRECCI&Oacute;N</th>
									    <th>FECHA</th>
									    <th>ESTADO</th>
									    <th>MEDIO DE PAGO</th>
									    <th>TOTAL</th>
									    <th>VER</th>
									</tr>
								</thead>
								<%} %>
								<tbody>
									<tr>
									    <td><%=venta.getIdVenta()%></td>
									    <td><%=venta.getUsuario().getApellido()%>, <%=venta.getUsuario().getNombre()%></td>
									    <td><%=venta.getUsuario().getDireccion()%></td>
									    <td><%=venta.getFecha()%></td>
									    <td><%=venta.getEstado()%></td>
									    <td><%=venta.getMedioPago().getTipo()%></td>
									    <td><%=venta.getTotal()%></td>
									    <td> + </td>
									</tr>
								</tbody>
							</table>
						</a>
					</div>
				</div>
				<div id="collapse<%=i%>" class="panel-collapse collapse">
					<div class="table-responsive">
						<table id="tablaLv" class="table table-striped">
							<thead>
								<tr class="active">
								    <td>ID</td>
								    <td>NOMBRE</td>
								    <td>PRESENTACION</td>
								    <td>CANTIDAD</td>
								    <td>PRECIO UNITARIO</td>
								    <td>SUBTOTAL</td>							
								</tr>
							</thead>
							<tbody>
							<%for (LineaVenta lv : venta.getLineas()){
								%>
								
								<tr>
								    <td><%=lv.getProducto().getIdProducto()%></td>
								    <td><%=lv.getProducto().getNombre()%></td>
								    <td><%=lv.getProducto().getPresentacion()%></td>
								    <td><%=lv.getCantidad()%></td>
								    <td><%=lv.getPrecioUnitario()%></td>
								    <%Double subtotal = lv.getPrecioUnitario() * lv.getCantidad(); %>
								    <td><%=subtotal%></td>							
								</tr>
							
							
							<%
							}
							%>
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
			<%
			}
			%>
				
			</div>
			
			
	
		</div>	
		<hr>
		<div class="form-group">
			<div class="col-sm-4 col-xs-12 ">
				<button class="btn btn-primary form-control"> <span class="fa fa-print"></span> IMPRIMIR LISTADO </button>
			</div>
			<br class="visible-xs">
			<br class="visible-xs">
			<div class="col-sm-4 col-xs-12 ">
				<button class="btn btn-primary form-control"> <span class="fa fa-file text-warning"></span> EXPORTAR LISTADO </button>
			</div>
			<br class="visible-xs">
			<br class="visible-xs">
			<div class="col-sm-4 col-xs-12 ">
				<button class="btn btn-primary form-control"> <span class="fa fa-download text-info"></span> GUARDAR COMO PDF </button>
			</div>	
		</div>	
		<br>
		<br>
    </div>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>