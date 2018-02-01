<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Turno"%>
<%@page import="entidades.TipoMascotaServicio"%>
<%@page import="entidades.Servicio"%>
<%@page import="logica.ControladorDeServicio"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	

	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/turnos.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.css" type="text/css">
	<link rel="stylesheet" href="css/estilos.css" type="text/css">
			
	<title>SGPS - Turnos</title>
</head>
<body onload="iniciar('turnos');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container panel panel-default colorPanel">
	 	<div class="panel-body">
			<br>
			<br>
			<h4><strong>MODIFICAR TURNO</strong> - Paso 2 de 2</h4>
			<hr>
			<form action="#" method="post" class="form-horizontal"  id="formTurnosPaso2">
				<div class="container-fluid">
					<div class="form-group">
					<h4><strong>VERIFIQUE LOS DATOS ANTES DE CONTINUAR</strong></h4>
						<div class="row ">	
							<div class="container-fluid col-lg-6 col-md-12">
								<h4><strong>DATOS DEL CLIENTE</strong></h4>
								<% Usuario userActual = new Usuario();
								Turno turnoActual = (Turno) session.getAttribute("turnoActual");
								userActual = turnoActual.getMascota().getUsuario();%>
								<div class="container">
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> NOMBRE: </strong> <%=turnoActual.getMascota().getUsuario().getNombre() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> APELLIDO: </strong> <%=turnoActual.getMascota().getUsuario().getApellido() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> DIRECCION: </strong> <%=turnoActual.getMascota().getUsuario().getDireccion() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> TELEFONO: </strong> <%=turnoActual.getMascota().getUsuario().getTelefono() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> NOMBRE MASCOTA: </strong> <%=turnoActual.getMascota().getNombre() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> TAMA&Ntilde;O: </strong> <%=turnoActual.getMascota().getTipoMascota().getTamanio() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> PELAJE: </strong> <%=turnoActual.getMascota().getTipoMascota().getPelo() %></h4>
								</div>
							</div>
							
							<div class="container-fluid col-lg-6 col-md-12">
								<h4><strong>DATOS DEL TURNO</strong></h4>
								<div class="container">
									<% ControladorDeServicio ctrlServicio = new ControladorDeServicio();
									TipoMascotaServicio tMascServ = ctrlServicio.getTipoMascotaServicio(turnoActual.getMascota(),turnoActual.getServicio());
									%>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> DIA: </strong> <%=turnoActual.getFecha() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> HORA: </strong> <%=turnoActual.getHora()%></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> DURACION: </strong><%=tMascServ.getTiempo() %></h4>
									<h4><span class="glyphicon glyphicon-chevron-right"> </span><strong> TIPO DE SERVICIO: </strong> <%=turnoActual.getServicio().getTipo() %></h4>
								</div>
							</div>
						</div>
						
						<div class="row container-fluid">
							<hr>
							<div class="col-12">
								<h4><strong>DATOS ADICIONALES</strong></h4>
								<div class="container-fluid form-group">
									<textarea class="form-control" rows="5" id="observaciones" placeholder="Escrib&iacute; ac&aacute; los datos adicionales de ser necesarios"><%=turnoActual.getObservaciones() %></textarea>
								</div>
							</div>
						</div>

						<hr>
					
						<div class="row">
							<div class="container-fluid form-group ">
								<div class="col-sm-3 col-xs-12 pull-right">
									<button class="btn btn-primary form-control" id="btnConfirmarModificacionTurno"> Guardar cambios </button>
								</div>	
								<div class="col-sm-3 col-xs-12 pull-right">
									<button class="btn btn-default form-control" id="volverPaso1"> Volver </button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
	<!-- <script src="js/bootstrap.min.js"></script> -->
	 
</body>
</html>