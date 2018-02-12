<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Categoria"%>
<%@page import="entidades.Subcategoria"%>
<%@page import="entidades.Producto"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.css" type="text/css">
	
	<title>SGPS - Modificar producto</title>
</head>
<body onload="iniciar('administracion');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>	
		<h4><strong>MODIFICAR PRODUCTO</strong></h4>
		<hr>
		<div class="container-fluid">
			<form class="form" action="ModificarProducto" method="post" enctype="multipart/form-data" id="form_modificar_producto">
	            	
	            	<div class="form-group row">
	            	<% Producto produ = ((Producto) session.getAttribute("producto")); %>
	            	<!-- Campo de modificacion / alta -->
	            	<input type="hidden"  name="modificacion" id="modificacion" value="modificacion">
	            	
	            	<!-- Label de id -->
	            		<label class="sr-only">Nombre</label>
					    <div class="col-lg-6 col-md-12">
					    	<label class="form-text text-muted"><strong>ID PRODUCTO: <%= produ.getIdProducto()  %></strong></label>
						</div>
					</div>
	            	
	            	<!-- Combobox de categoria -->
	            	<div class="form-group row">
		    			<div class=" selectContainer col-lg-6 col-md-12" id="categoriaGroup">
		    				<label class="sr-only">Categoria</label>
						    <small id="categoriaHelp" class="form-text text-muted">Seleccion&aacute; una categor&iacute;a.</small>
						    <select class="form-control" name="categoria" id="categoria" aria-describedby="categoriaHelp" required>
						    	<option value="categoria">Seleccion&aacute; una categor&iacute;a</option>
								     <% ControladorDeProducto ctrlProducto = new ControladorDeProducto();
						      		 ArrayList<Categoria> categorias = ctrlProducto.getCategorias();
						      		 String seleccionado = "";
						      	 	 for(Categoria cate : categorias){ 
						      	 	 	if (cate.getIdCategoria()== produ.getSubcategoria().getCategoria().getIdCategoria()){
						      	 	 	seleccionado="selected";	
						      	 	 	}else{
						      	 	 		seleccionado="";
						      	 	 	}
						      	 	 	%>
						      	<option value="<%=(cate.getIdCategoria())%>"<%=seleccionado %>><%=cate.getNombre()%></option>						    
						      <%} %>
						    </select>
						</div>
					
					
					<!-- Combobox de subcategoria -->
		    			<div class=" selectContainer col-lg-6 col-md-12" id="subcategoriaGroup">
		    				<label class="sr-only">Subcategoria</label>
						    <small id="subcategoriaHelp" class="form-text text-muted">Seleccion&aacute; una subcategor&iacute;a.</small>
						    <select class="form-control" name="subcategoria" id="subcategoria" aria-describedby="subcategoriaHelp" required>
								     <option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>
								     <% 
						      		 ArrayList<Subcategoria> subcat = ctrlProducto.getSubcategorias(produ.getSubcategoria().getCategoria());
						      		 seleccionado = "";
						      	 	 for(Subcategoria subcate : subcat){ 
						      	 	 	if (subcate.getIdSubCategoria()== produ.getSubcategoria().getIdSubCategoria()){
						      	 	 	seleccionado="selected";	
						      	 	 	}else{
						      	 	 		seleccionado="";
						      	 	 	}
						      	 	 	%>
						      	<option value="<%=(subcate.getIdSubCategoria())%>"<%=seleccionado %>><%=subcate.getNombre()%></option>						    
						      <%} %>
						    </select>
						</div>
					</div>
					
					<hr>
					
	            	<div class="form-group row">
	            	
	            	<!-- Input de nombre -->
	            		<label class="sr-only">Nombre</label>
					    <div class="col-lg-6 col-md-12" id="nombreGroup">
					    	<small id="nombreHelp" class="form-text text-muted">Ingres&aacute; el nombre del producto</small>
					    	<input type="text" class="form-control" name="nombre" id="nombre" aria-describedby="nombreHelp" placeholder="Ingres&aacute; el nombre de producto a dar de alta" value="<%= produ.getNombre() %>" required>
						</div>
						
					<!-- Input de presentacion -->
						<label class="sr-only">Presentacion</label>
					    <div class="col-lg-6 col-md-12" id="presentacionGroup">
					    	<small id="presentacionHelp" class="form-text text-muted">Ingres&aacute; la presentaci&oacute;n del producto</small>
					    	<input type="text" class="form-control" name="presentacion" id="presentacion" aria-describedby="presentacionHelp" placeholder="Ingres&aacute; la presentaci&oacute;n del producto a dar de alta" value="<%= produ.getPresentacion() %>" required>
						</div>
					</div>
					
					<hr>
					
					<div class="form-group row">
	            	
	            	<!-- Input de precio-->
	            		<label class="sr-only">Precio</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="precioHelp" class="form-text text-muted">Ingres&aacute; el precio del producto</small>
					    	<div class="input-group" id="precioGroup">
					    		<span class="input-group-addon">$</span>
					    		<input type="text" class="form-control" name="precio" id="precio" aria-describedby="precioHelp" placeholder="Ingres&aacute; el precio actual del producto a dar de alta" value="<%= produ.getPrecio()%>"required>
					    	</div>
					    	
						</div>
						
					<!-- Input de stock inicial -->
						<label class="sr-only">Stock inicial</label>
					    <div class="col-lg-6 col-md-12" id="stockGroup">
					    	<small id="stockHelp" class="form-text text-muted">Ingres&aacute; el stock inicial del producto</small>
					    	<input type="number" min="0"class="form-control" name="stock" id="stock" aria-describedby="stockHelp" placeholder="Ingres&aacute; el stock inicial del producto a dar de alta" value="<%= produ.getStock()%>"required>
						</div>
					
					
					<!-- Input de stock minimo -->
						<label class="sr-only">Stock minimo</label>
					    <div class="col-lg-6 col-md-12" id="stockMinimoGroup">
					    	<small id="stockMinimoHelp" class="form-text text-muted">Ingres&aacute; el stock m&iacute;nimo deseado del producto</small>
					    	<input type="number" min="0"class="form-control" name="stockminimo" id="stockminimo" aria-describedby="stockMinimoHelp" placeholder="Ingres&aacute; el stock m&iacute;nimo deseado del producto a dar de alta" value="<%= produ.getStockMinimo()%>" required>
						</div>
					</div>
					
					<hr>	
					
					<div class="form-group row">
	            	
	            	<!-- Input de archivo de imagen-->
	            		<label class="sr-only">Imagen de producto</label>
					    <div class="col-md-12 form-group" id="imagenGroup">
					    	<small id="archivoHelp" class="form-text text-muted hidden">Modificar imagen del producto (solo se admite formato JPG)</small>
					    	<div class="input-group">
					    		<input type="button" class="btn btn-warning btn-lg" id="btnImagenes" name="btnImagenes" value="Modificar Imagen del Producto">
						    	<input type="hidden" class="form-control" name="file[]" id="imagenes" multiple>
						    	
					    	</div>
					    </div>
					</div>
					<hr>		
					<!-- Agrego el campo id oculto -->
					
					<input type="hidden" name="idProducto" id="idProducto" value="<%= produ.getIdProducto()%>">
					<div class="form-group row">		
						<div class="col-lg-12">
							<input type="submit" id="btnGuardarModificacionProducto" value="Modificar" class="col-lg-2 col-xs-12 btn btn-primary btn-lg pull-right">
						</div>
					</div>
	            </form> 
	        </div>
     	</div>
	
		<script type="text/javascript" src="/Petshop/js/jquery-latest.js"></script>
		<script type="text/javascript" src="/Petshop/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/mantProductos.js"></script>

</body>

</html>