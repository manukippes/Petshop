<%@page import="entidades.Usuario"%>
<%@page import="entidades.Producto"%>
<%@page import="logica.ControladorDeProducto"%>
<%@page import="java.util.ArrayList"%>
<div class="container">
	<br>
	<header>
		<% Usuario usuarioNavbar = (Usuario) session.getAttribute("user");
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		ArrayList<ArrayList<String>> listado = (ArrayList<ArrayList<String>>) session.getAttribute("productosVenta");
		int cantidad = listado.size();
		String noHayProductos = ""; 
		String ventaPendiente = "Hay una venta pendiente de finalizar";
		if(cantidad==0){noHayProductos =" hidden"; ventaPendiente="Ventas";}
			switch (usuarioNavbar.getTipoUsuario()){
				case "Administrador":
		%>	
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-1">
						<span class="sr-only">Menu</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>						
					</button>
					<a href="#" class="hidden-xs hidden-sm navbar-brand"><b>S</b>istema de <b>G</b>esti&oacute;n de <b>P</b>et <b>S</b>hops</a>
					<span class="hidden-md hidden-lg navbar-brand negrita">SGPS</span>
				</div>
				
				
				<div class="collapse navbar-collapse" id="navbar-1">
					<ul class="nav navbar-nav">
						<li id="ventasTab" class=""><a href="Ventas" title="<%=ventaPendiente%>"><span class="fa fa-shopping-bag"></span> Ventas <span class="fa fa-bookmark text-danger<%=noHayProductos %>" style="vertical-align:text-top;font-size:10px"></span></a></li>
						<li id="turnosTab" class=""><a href="Turnos"><span class="fa fa-calendar"></span> Turnos</a></li>
						<li id="comprobantesVentasTab" class=""><a href="ComprobantesVentas"><span class="fa fa-th-list"></span> Comprobantes de Ventas</a></li>
						<li class="dropdown" id="administracionTab">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
								<span class="fa fa-briefcase"></span> Administraci&oacute;n <span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="MantenimientoProductos" class="" aria-hidden="true"><span class="fa fa-cube"></span> Mantenimiento de Productos </a></li>
								<li><a href="Clientes"> <span class="fa fa-user"></span> Mantenimiento de Clientes </a></li>
								<li><a href="MantenimientoTurnos"> <span class="fa fa-calendar"></span> Mantenimiento de Turnos </a></li>
							</ul>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
								<span class="fa fa-user-circle"></span> <%= usuarioNavbar.getNombre() %> <span class="caret"></span>
							</a>
						
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><span class="fa fa-pencil"></span> Editar Perfil </a></li>
								<li class="divider"></li>
								<li>
									<a href="#confirmacion" class="" data-toggle="modal"><span class="fa fa-sign-out"></span> Salir </a>
								</li>
							</ul>
						</li>
					</ul>
				</div>	
			</div>
		</nav>
		<%
		break;
		case "Online":%>
			<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-1">
						<span class="sr-only">Menu</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="#" class="hidden-xs hidden-sm navbar-brand"><b>S</b>istema de <b>G</b>esti&oacute;n de <b>P</b>et <b>S</b>hops</a>
					<span class="hidden-md hidden-lg navbar-brand negrita">SGPS</span>
				</div>
				
				
				<div class="collapse navbar-collapse" id="navbar-1">
					<ul class="nav navbar-nav">
						<li id="ventasTab" class=""><a href="VentaOnline"><span class="fa fa-shopping-bag"></span> Ventas</a></li>
						<li id="turnosTab" class=""><a href="TurnoOnline"><span class="fa fa-calendar"></span> Turnos</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
					
						<li><a href="#carrito" data-toggle="modal"> <span class="fa fa-shopping-cart"></span> Mi Carrito <span class="badge" id="articulosCarrito"><%=cantidad %></span></a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
								<span class="fa fa-user-circle"></span> <%= usuarioNavbar.getNombre() %> <span class="caret"></span>
							</a>
						
							<ul class="dropdown-menu" role="menu">
								<li><a href="ModificarUsuarioOnline"><span class="fa fa-pencil"></span> Editar Perfil </a></li>
								<li class="divider"></li>
								<li>
									<a href="#confirmacion" data-toggle="modal"><span class="fa fa-sign-out"></span> Salir </a>
								</li>
							</ul>
						</li>
					</ul>
				</div>	
			</div>
		</nav>
		<!-- Carrito de compras -->
					<div class="modal fade" id="carrito">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4><strong class="text-muted"><span class="fa fa-shopping-cart" style="font-size:35px;"></span> &nbsp;&nbsp;MI CARRITO DE COMPRAS</strong>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></h4>
								</div>
								<div class="modal-body">
									<div class="table-responsive">
									<%String hayProductos = " hidden"; 
									if(cantidad==0){hayProductos ="";}%>
										<h5 class="text-muted text-center <%=hayProductos %>" id="carritoVacio">No hay productos en el carrito</h5>
										<table id="tablaVentaActual" class="table table-striped table-hover tablaVentaActual">
											<thead>
												<tr class="active hidden">
													<th>
														<div class="imagen">
															<label class="sr-only">Imagen de producto</label>
														</div>
													</th>
													<th class="hidden">
														<div class="idProducto">
															<label class="sr-only">Id Producto</label>
															<span>Id Producto</span>
														</div>
													</th>
													<th id="nombreProducto">
														<label class="sr-only">Nombre de Producto</label>
														<span>Nombre</span>
													</th>
													<th id="presentacionProducto">
														<label class="sr-only">Presentacion</label>
														<span>Presentaci&oacute;n</span>
													<th>
														<label class="sr-only">Precio</label>
														<span>Precio</span>	
													</th>
													<th>
														<label class="sr-only">Cantidad</label>
														<span>Cantidad</span>				
													</th>
													<th> 
														<div class="col-sm-3 col-lg-2 input-group">
														</div>
													</th>
												</tr>
											</thead>
											<tbody>
												
													<%Double subtotal =0.0;
													for(ArrayList<String> prodcant : listado){
														Producto producto = new Producto();
														producto.setIdProducto(Integer.parseInt(prodcant.get(0)));
														producto = ctrlProducto.getProducto(producto);
														int cantidadProducto = Integer.parseInt(prodcant.get(1));
														subtotal += (cantidadProducto*producto.getPrecio());	
													%>
												<tr>
													<td id="imagen">
														<div class="img-hover">
															<a href="<%=producto.getImagen() %>" class="preview" title="<%=producto.getNombre() %>,<%=producto.getPresentacion() %>"><img class="img-thumbnail" src="<%=producto.getImagen()%>" width="50px" height="50px"/></a>
														</div>
													</td> 
													<td id="idProducto" class="hidden"><%=producto.getIdProducto() %></td>
													<td id="nombreProducto"><%=producto.getNombre() %></td>
													<td id="presentacionProducto"><%=producto.getPresentacion()%></td>
													<td id="precioProducto"><span class="fa fa-dollar"></span> <%=producto.getPrecio() %></td>
													<td>
														<div class="scrollCantidadProductoGroup">
															<input id="scrollCantidadProducto" type="number" class="form-control scrollCantidadProducto" min="0" max="<%=producto.getStock() %>" value="<%=cantidadProducto %>"></input>
														</div>
													</td>
													<td class="col-sm-3 col-lg-2">
														<div class="input-group">
															<a class="btn btn-danger btnEliminarProductoVenta" href="\"><span class="fa fa-times"></span></a>
														</div>
													</td>
												</tr>
												<%} %>
									 		</tbody>
										</table>
									</div>
									
									
										
									<div class="form-group row <%=noHayProductos %>" id="subtotalGroup">
										<label class="sr-only">Subtotal</label>
									    <div class="col-sm-6 col-lg-4 pull-right">
									    	<small id="subtotalHelp" class="form-text text-muted">Subtotal</small>
									    	<div class="input-group">
									    		<span class="input-group-addon"><small>$</small></span>
												<input class="form-control" type="text" name="subtotal" class="subtotal" value="<%=subtotal %>" id="subtotal" placeholder="Subtotal" disabled>
									    	</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="pull-right">
										<button class="btn btn-primary btn-group-justified btnContinuar" type="submit" id="btnContinuar"><span class="fa fa-cart-arrow-down"></span> Finalizar Compra </button>
									</div>
								</div>								
							</div>
						</div>
					</div>
		<%
			break;
		} 
		%>
	</header>
</div>

<div class="modal fade" id="confirmacion">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><strong>Sistema de Gesti&oacute;n de Pet Shops</strong></h4>
			</div>
			<div class="modal-body container-fluid">
				<div class="col-xs-3">
					<span class="fa fa-sign-out" style="font-size:50px"></span>
				</div>
				<div class="col-xs-9">
					<h4> ¿Seguro deseas salir?</h4>
				</div>
				
			</div>
			<div class="modal-footer">
				<a href="index.html" class="btn btn-default" aria-hidden="true"> Salir </a>
				<button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true"> Cancelar </button>
				
			</div>
		</div>
	</div>
</div>