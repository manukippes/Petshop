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
						<a href="#" class="navbar-brand">Sistema de Gestion de Pet Shops</a>
					</div>
					
						
					<div class="collapse navbar-collapse" id="navbar-1">
						<ul class="nav navbar-nav">
							<li id="ventasTab" class=""><a href="Ventas">Ventas</a></li>
							<li id="clientesTab" class=""><a href="">Clientes</a></li>
							<li id="turnosTab" class=""><a href="">Turnos</a></li>
							<li id="consultasTab" class=""><a href="">Consultas</a></li>
							<li id="administracionTab" class=""><a href="">Administracion</a></li>
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