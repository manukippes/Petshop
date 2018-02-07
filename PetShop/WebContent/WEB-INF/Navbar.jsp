<%@page import="entidades.Usuario"%>
<div class="container">
	<br>
	<header>
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
						<li id="ventasTab" class=""><a href="Ventas">Ventas</a></li>
						<li id="turnosTab" class=""><a href="Turnos">Turnos</a></li>
						<li id="comprobantesVentasTab" class=""><a href="ComprobantesVentas">Comprobantes de Ventas</a></li>
						<li class="dropdown" id="administracionTab">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
								<span>Administraci&oacute;n </span><span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="MantenimientoProductos" class="" aria-hidden="true"> Mantenimiento de Productos </a></li>
								<li><a href="Clientes"> Mantenimiento de Clientes </a></li>
								<li><a href="MantenimientoTurnos"> Mantenimiento de Turnos </a></li>
							</ul>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
								<span class="glyphicon glyphicon-user"></span> <%= ((Usuario) session.getAttribute("user")).getNombre() %> <span class="caret"></span>
							</a>
						
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"> Mis Compras </a></li>
								<li><a href="#"> Editar Perfil </a></li>
								<li class="divider"></li>
								<li>
									<a href="#confirmacion" class="" data-toggle="modal"> Salir </a>
								</li>
							</ul>
						</li>
					</ul>
				</div>	
			</div>
		</nav>
	</header>
</div>

<div class="modal fade" id="confirmacion">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<h4>¿Seguro desea salir?</h4>
			</div>
			<div class="modal-footer">
				<a href="index.html" class="btn btn-default" aria-hidden="true"> Salir </a>
				<button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true"> Cancelar </button>
				
			</div>
		</div>
	</div>
</div>
