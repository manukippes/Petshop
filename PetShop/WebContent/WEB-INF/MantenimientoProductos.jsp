<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">

	<script type="text/javascript">

    	function iniciar(){
    		administracionTab.className += " active";
    		}
    	
    	function listadoElementos(){
    		
    		
    	}
    	
	</script>
	<title>SGPS - Mantenimiento de Productos</title>
</head>
<body onload="iniciar();">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>	
		<h4><strong>M&Oacute;DULO DE MANTENIMIENTO DE PRODUCTOS</strong></h4>
		<hr>
		
		<h5>Para dar de alta un nuevo producto:</h5>
		<div class="col-12 btn-group-justified">
			
			<a href="AgregarProducto" type="button" class="btn btn-primary">ALTA NUEVO PRODUCTO</a>
		</div>
		<hr>
		<h5>Para consultar o modificar un producto:</h5>
	</div>
	
		
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>