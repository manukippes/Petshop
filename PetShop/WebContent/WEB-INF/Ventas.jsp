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
    		ventasTab.className += " active";
    		}
    	
    	function listadoElementos(){
    		
    		
    	}
    	
	</script>
	<title>SGPS - Ventas</title>
</head>
<body onload="iniciar();">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>
		
		<form action="" method="post" class="form-horizontal">
			<h4><strong>Nueva venta</strong> - Paso 1 de 2</h4>
			<br>
			<h5>Buscar productos</h5>
			
			<div class="form-group col-md-offset-2">

				
				
				<label class="sr-only">Ingresa descripcion o codigo</label>
				
				<div class="col-md-8 col-xs-12">
					<input class="form-control" nombre="buscar" id="buscar" type="text" placeholder="Ingres&aacute; descripci&oacute;n parcial o c&oacute;digo de producto">
				</div>
				<div class="col-md-4 ">
					<button class="btn btn-primary"> <span class="glyphicon glyphicon-search" ></span> Buscar </button>
					<button class="btn btn-default" onclick = "this.form.action = 'listadoProductos';  this.form.submit();" > Listado <span class="glyphicon glyphicon-th-list"></span> </button>
				</div>
			</div>
		</form>
	</div>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>