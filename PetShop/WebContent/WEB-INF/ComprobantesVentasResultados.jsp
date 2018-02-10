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
		
		<div class="table-responsive">
			<table id="dataTable" class="display" cellspacing="0" width="100%">
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
		<div id="botonera" class="container">
		</div>
		
		<br>
		<br>
    </div>
    
   	<script type="text/javascript" src="/Petshop/js/jquery-latest.js"></script>
	<script type="text/javascript" src="/Petshop/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/comprobantesVentas.js"></script>
	
	<script type="text/javascript" src="/Petshop/DataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/buttons.bootstrap.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/buttons.flash.min.js"></script>
		
	<script type="text/javascript" src="/Petshop/DataTables/JSZip/jszip.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/PDFmake/pdfmake.min.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/PDFmake/vfs_fonts.js"></script>
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/buttons.html5.min.js"></script>	
	<script type="text/javascript" src="/Petshop/DataTables/Buttons/js/buttons.print.min.js"></script>
	
	

</body>