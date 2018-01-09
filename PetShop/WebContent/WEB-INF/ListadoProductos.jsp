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
	<script type="text/javascript" src="js/main.js"></script>
	<title>Buscar producto</title>
</head>
<body onload="iniciar('ventas');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container">
		<br>
		<br>
		
		<h4><strong>LISTADO GENERAL DE PRODUCTOS</strong></h4>
		
		<div class="form-group row">
		    <div class="col-xs-12 selectContainer">
			    <small id="categoriaHelp" class="form-text text-muted">Seleccione una categor&iacute;a.</small>
			    <select class="form-control" name="categoria" aria-describedby="categoriaHelp" required>
					     <% ControladorDeProducto ctrlProducto = new ControladorDeProducto();
			      		 ArrayList<Categoria> categorias = ctrlProducto.getCategorias();
			      	 	 for(Categoria cate : categorias){ %>
			      	<option value="<%=(cate.getIdCategoria())%>"><%=cate.getNombre()%></option>						    
			      <%} %>
			    </select>
			    <input type="checkbox" class="form-check-input" value="1" name="mostrarDisponibles" aria-describedby="disponiblesHelp">
		    	<small id="disponiblesHelp" class="form-text text-muted">Mostrar solo disponibles.</small>
			</div>
		</div>
	
	
		<div class="">
			<table id="tabla" class="table table-striped">
				<thead>
				  <tr class="active">
				    <th>C&oacute;digo</th>
				    <th>Nombre</th>
				    <th>Presentaci&oacute;n</th>
				    <th>Precio</th>
				    <th>Stock</th>
				  </tr>
				</thead>
				<tbody>
		 		<% 	int i=0;
		 		
		 		
				ArrayList<Producto> productos = ctrlProducto.getProductos();
				for(Producto produ : productos){
				i++;
				%>
					<tr class="selected" id=<%=i%> onclick="seleccionar(this.id);">
						<td><%=produ.getIdProducto()%></td>
						<td><%=produ.getNombre()%></td>
						<td><%=produ.getPresentacion()%></td>
						<td><%=produ.getPrecio()%></td>
						<td><%=produ.getStock()%></td>
					</tr>
				<%
				} 
				%>
		 		</tbody>
			</table>
			<button class="btn btn-primary btn-lg col-offset-10" type="button"> Agregar a la venta</button>
    	</div>
    </div> 
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>