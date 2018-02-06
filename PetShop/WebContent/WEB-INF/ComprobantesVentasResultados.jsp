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
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.css" type="text/css">
	<link rel="stylesheet" href="/Petshop/DataTables/css/jquery.dataTables.min.css" type="text/css">
	<link rel="stylesheet" href="/Petshop/DataTables/Buttons/css/buttons.dataTables.min.css" type="text/css">
	
	<title>SGPS - Comprobantes de Ventas</title>
</head>
<body onload="iniciar('comprobantesVentas');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>
		
		<h4><strong>COMPROBANTES DE VENTAS REGISTRADOS</strong></h4>
		<hr>
		<div class="container-fluid visible-print">
				<table id="tablaImprimir" class="table table-striped ">
					<thead>
						<tr class="active">
						    <th>ID</th>
						    <th>CLIENTE</th>
						    <th>DIRECCI&Oacute;N</th>
						    <th>FECHA</th>
						    <th>ESTADO</th>
						    <th>MEDIO DE PAGO</th>
						    <th>TOTAL</th>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					
				</table>
			</div>
		<div class="table-responsive">
			<table id="example" class="display" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOMBRE</th>
		                <th>APELLIDO</th>
		                <th>DIRECCION</th>
		                <th>FECHA</th>
		                <th>ESTADO</th>
		                <th>MEDIO DE PAGO</th>
		                <th>TOTAL</th>
		                <th>VER</th>
		            </tr>
		        </thead>
		    </table>			
	
		</div>	
		<hr>
		<div class="form-group">
			<div class="col-sm-4 col-xs-12 ">
				<button class="btn btn-primary form-control hidden-print" onclick="window.print();"> <span class="fa fa-print"></span> IMPRIMIR LISTADO </button>
			</div>
			<br class="visible-xs">
			<br class="visible-xs">
			<div class="col-sm-4 col-xs-12 ">
				<button class="btn btn-primary form-control hidden-print"> <span class="fa fa-file text-warning"></span> EXPORTAR LISTADO </button>
			</div>
			<br class="visible-xs">
			<br class="visible-xs">
			<div class="col-sm-4 col-xs-12 ">
				<button class="btn btn-primary form-control hidden-print"> <span class="fa fa-download text-info"></span> GUARDAR COMO PDF </button>
			</div>	
		</div>	
		<br>
		<br>
    </div>
    
   
	<script type="text/javascript" src="/Petshop/js/jquery-latest.js"></script>
	<script type="text/javascript" src="/Petshop/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/comprobantesVentas.js"></script>
	
	<script type="text/javascript" src="/Petshop/DataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/dataTables.buttons.min.js"></script>
		
	<script type="text/javascript" src="/Petshop/DataTables/JSZip/jszip.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/PDFmake/pdfmake.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/PDFmake/vfs_fonts.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/buttons.html5.min.js"></script>
	
	

</body>