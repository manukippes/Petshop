<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Categoria"%>
<%@page import="entidades.Producto"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/combobox.js"></script>
		
	
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
	            	
	            	<!-- Input de id -->
	            		<label class="sr-only">Nombre</label>
					    <div class="col-lg-6 col-md-12">
					    	<label id="idProductoHelp" class="form-text text-muted"><strong>ID PRODUCTO: <%= ((Producto) session.getAttribute("producto")).getIdProducto() %></strong></label>
						</div>
					</div>
	            	
	            	<!-- Combobox de categoria -->
	            	<div class="form-group row">
		    			<div class=" selectContainer col-lg-6 col-md-12">
		    				<label class="sr-only">Categoria</label>
						    <small id="categoriaHelp" class="form-text text-muted">Seleccion&aacute; una categor&iacute;a.</small>
						    <select class="form-control" name="categoria" id="categoria" aria-describedby="categoriaHelp" required>
						    	<option value="categoria">Seleccion&aacute; una categor&iacute;a</option>
								     <% ControladorDeProducto ctrlProducto = new ControladorDeProducto();
						      		 ArrayList<Categoria> categorias = ctrlProducto.getCategorias();
						      		 String seleccionado = "";
						      	 	 for(Categoria cate : categorias){ 
						      	 	 	if (cate.getIdCategoria()==((Producto) session.getAttribute("producto")).getSubcategoria().getCategoria().getIdCategoria()){
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
		    			<div class=" selectContainer col-lg-6 col-md-12">
		    				<label class="sr-only">Subcategoria</label>
						    <small id="subcategoriaHelp" class="form-text text-muted">Seleccion&aacute; una subcategor&iacute;a.</small>
						    <select class="form-control" name="subcategoria" id="subcategoria" aria-describedby="subcategoriaHelp" required>
								     <option value="subcategoria">Seleccion&aacute; una subcategor&iacute;a</option>
						    </select>
						</div>
					</div>
					
					<hr>
					
	            	<div class="form-group row">
	            	
	            	<!-- Input de nombre -->
	            		<label class="sr-only">Nombre</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="nombreHelp" class="form-text text-muted">Ingres&aacute; el nombre del producto</small>
					    	<input type="text" class="form-control" name="nombre" id="nombre" aria-describedby="nombreHelp" placeholder="Ingres&aacute; el nombre de producto a dar de alta" value="<%= ((Producto) session.getAttribute("producto")).getNombre() %>" required>
						</div>
						
					<!-- Input de presentacion -->
						<label class="sr-only">Presentacion</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="presentacionHelp" class="form-text text-muted">Ingres&aacute; la presentaci&oacute;n del producto</small>
					    	<input type="text" class="form-control" name="presentacion" id="presentacion" aria-describedby="presentacionHelp" placeholder="Ingres&aacute; la presentaci&oacute;n del producto a dar de alta" value="<%= ((Producto) session.getAttribute("producto")).getPresentacion() %>" required>
						</div>
					</div>
					
					<hr>
					
					<div class="form-group row">
	            	
	            	<!-- Input de precio-->
	            		<label class="sr-only">Precio</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="precioHelp" class="form-text text-muted">Ingres&aacute; el precio del producto</small>
					    	<input type="text" class="form-control" name="precio" id="precio" aria-describedby="precioHelp" placeholder="Ingres&aacute; el precio actual del producto a dar de alta" value="<%= ((Producto) session.getAttribute("producto")).getPrecio()%>"required>
						</div>
						
					<!-- Input de stock inicial -->
						<label class="sr-only">Stock inicial</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="stockHelp" class="form-text text-muted">Ingres&aacute; el stock inicial del producto</small>
					    	<input type="number" min="0"class="form-control" name="stock" id="stock" aria-describedby="stockHelp" placeholder="Ingres&aacute; el stock inicial del producto a dar de alta" value="<%= ((Producto) session.getAttribute("producto")).getStock()%>"required>
						</div>
					
					
					<!-- Input de stock inicial -->
						<label class="sr-only">Stock minimo</label>
					    <div class="col-lg-6 col-md-12">
					    	<small id="stockMinimoHelp" class="form-text text-muted">Ingres&aacute; el stock m&iacute;nimo deseado del producto</small>
					    	<input type="number" min="0"class="form-control" name="stockminimo" id="stockminimo" aria-describedby="stockMinimoHelp" placeholder="Ingres&aacute; el stock m&iacute;nimo deseado del producto a dar de alta" value="<%= ((Producto) session.getAttribute("producto")).getStockMinimo()%>" required>
						</div>
					</div>
					
					<hr>	
					
					<div class="form-group row">
	            	
	            	<!-- Input de archivo de imagen-->
	            		<label class="sr-only">Imagen de producto</label>
					    <div class="col-lg-6 col-md-12 form-group">
					    	<small id="archivoHelp" class="form-text text-muted">Agreg&aacute; una imagen del producto en formato JPEG</small>
					    	<div class="input-group">
						    	<input type="file" class="form-control" name="file[]" id="imagenes" multiple>
					    	</div>
					    </div>
					</div>
					<hr>		
					<!-- Agrego el campo id oculto -->
					
					<input type="hidden" name="idProducto" id="idProducto" value="<%= ((Producto) session.getAttribute("producto")).getIdProducto()%>">
					<div class="form-group row">		
						<div class="col-lg-12">
							<input type="submit" id="btnGuardarModificacionProducto" value="Modificar" class="col-lg-2 col-xs-12 btn btn-primary btn-lg pull-right">
						</div>
					</div>
	            </form> 
	        </div>
     	</div>
	
		
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>

</html>