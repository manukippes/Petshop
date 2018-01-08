<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Categoria"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="java.util.ArrayList"%>
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
	<title>SGPS - Alta nuevo producto</title>
</head>
<body onload="iniciar();">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>	
		<h4><strong>ALTA DE PRODUCTO NUEVO</strong></h4>
		<hr>
		<div class="container-fluid">
			<form class="form" action="ConfirmarAltaProducto" method="post">
	            	
	            	<!-- Combobox de categoria -->
	            	<div class="form-group row">
		    			<div class=" selectContainer col-lg-6 col-md-12">
		    				<label class="sr-only">Categoria</label>
						    <small id="categoriaHelp" class="form-text text-muted">Seleccion&aacute; una categor&iacute;a.</small>
						    <select class="form-control" name="categoria" aria-describedby="categoriaHelp" required>
								     <% ControladorDeProducto ctrlProducto = new ControladorDeProducto();
						      		 ArrayList<Categoria> categorias = ctrlProducto.getCategorias();
						      	 	 for(Categoria cate : categorias){ %>
						      	<option value="<%=(cate.getIdCategoria())%>"><%=cate.getNombre()%></option>						    
						      <%} %>
						    </select>
						</div>
					
					
					<!-- Combobox de subcategoria -->
		    			<div class=" selectContainer col-lg-6 col-md-12">
		    				<label class="sr-only">Subcategoria</label>
						    <small id="subcategoriaHelp" class="form-text text-muted">Seleccion&aacute; una subcategor&iacute;a.</small>
						    <select class="form-control" name="subcategoria" aria-describedby="subcategoriaHelp" required>
								     <!--<% 
						      	 	 for(Categoria cate : categorias){ %>  -->
						      	<option value="<%=(cate.getIdCategoria())%>"><%=cate.getNombre()%></option>						    
						      <%} %>
						    </select>
						</div>
					</div>
					
					<hr>
					
	            	<div class="form-group row">
	            	
	            	<!-- Input de nombre -->
	            		<label class="sr-only">Nombre</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="nombreHelp" class="form-text text-muted">Ingres&aacute; el nombre del producto</small>
					    	<input type="text" class="form-control" name="nombre" aria-describedby="nombreHelp" placeholder="Ingres&aacute; el nombre de producto a dar de alta" required>
						</div>
						
					<!-- Input de presentacion -->
						<label class="sr-only">Presentacion</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="presentacionHelp" class="form-text text-muted">Ingres&aacute; la presentaci&oacute;n del producto</small>
					    	<input type="text" class="form-control" name="presentacion" aria-describedby="presentacionHelp" placeholder="Ingres&aacute; la presentaci&oacute;n del producto a dar de alta" required>
						</div>
					</div>
					
					<hr>
					
					<div class="form-group row">
	            	
	            	<!-- Input de precio-->
	            		<label class="sr-only">Precio</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="precioHelp" class="form-text text-muted">Ingres&aacute; el precio del producto</small>
					    	<input type="text" class="form-control" name="precio" aria-describedby="precioHelp" placeholder="Ingres&aacute; el precio actual del producto a dar de alta" required>
						</div>
						
					<!-- Input de stock inicial -->
						<label class="sr-only">Stock inicial</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="stockHelp" class="form-text text-muted">Ingres&aacute; el stock inicial del producto</small>
					    	<input type="number" min="0"class="form-control" name="stock" aria-describedby="stockHelp" placeholder="Ingres&aacute; la presentaci&oacute;n del producto a dar de alta" required>
						</div>
					</div>
					<hr>			
					<div class="form-group row">		
						<div class="col-lg-12">
							<input type="submit" name="agregarProducto" value="Agregar" class="col-lg-2 col-xs-12 btn btn-primary btn-lg pull-right">
							
						</div>
					</div>
	            </form> 
	        </div>
     	</div>
	
		
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>