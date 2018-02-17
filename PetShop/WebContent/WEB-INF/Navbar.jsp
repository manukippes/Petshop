<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<div class="container">
	<br>
	<header>
		<% Usuario usuarioNavbar = (Usuario) session.getAttribute("user");
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
						<li id="ventasTab" class=""><a href="Ventas"><span class="fa fa-shopping-bag"></span> Ventas</a></li>
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
					<%ArrayList<ArrayList<String>> listado = (ArrayList<ArrayList<String>>) session.getAttribute("productosVenta");
					int cantidad = listado.size();%>
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