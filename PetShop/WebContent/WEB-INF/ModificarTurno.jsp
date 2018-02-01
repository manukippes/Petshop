<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Mascota"%>
<%@page import="entidades.Servicio"%>
<%@page import="entidades.Turno"%>
<%@page import="logica.ControladorDeServicio"%>
<%@page import="java.util.ArrayList"%>
<%@page import= "java.sql.Date"%>
<%@page import= "java.sql.Time"%>
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
			<h4><strong>MODIFICAR TURNO</strong> - Paso 1 de 2</h4>
			<hr>
			<form action="#" method="post" class="form-horizontal"  id="formTurnos">
				<div class="container-fluid">
					<div class="form-group">
						
						<div class="row col-lg-6 col-md-12">	
							<div class="container-fluid">
								<h4><strong>TAMA&Ntilde;O DEL PERRO</strong></h4>
							</div>
							<% Turno turnoActual = (Turno) session.getAttribute("turnoActual");
								String tamanio = turnoActual.getMascota().getTipoMascota().getTamanio();
								String pelaje = turnoActual.getMascota().getTipoMascota().getPelo();
								String grande="", mediano="", chico="", corto="", largo="";
								switch (tamanio){
								case "Grande":
									grande="icon-button-active";
									break;
								case "Mediano":
									mediano="icon-button-active";
									break;
								case "Chico":
									chico="icon-button-active";
									break;
								}
								switch (pelaje){
								case "Corto":
									corto="icon-button-active";
									break;
								case "Largo":
									largo="icon-button-active";
									break;
								}
								%>
							<div class="container-fluid" id="patitaGroup">
								<div class="col-sm-4 centrado">
									<label class="sr-only">Perro grande</label>
									<button class="icon-button <%=grande%>" id="btnPatitaGrande">						
										<span class="fa fa-paw" style="font-size:90px"></span>
										<p><strong class="text-center "> GRANDE </strong></p>
									</button>								
								</div>
								<div class="col-sm-4 centrado">
									<label class="sr-only">Perro mediano</label>
									<button class="icon-button <%= mediano%>" id="btnPatitaMediana">						
										<span class="fa fa-paw" style="font-size:70px;margin-top:20px"></span>
										<p><strong class="text-center"> MEDIANO </strong></p>	
									</button>			
													
									
								</div>
								<div class="col-sm-4 centrado">
									<label class="sr-only">Perro chico</label>
									<button class="icon-button <%=chico%>" id="btnPatitaChica">						
										<span class="fa fa-paw" style="font-size:50px;margin-top:40px"></span>
										<p><strong class="text-center"> CHICO </strong></p>		
									</button>					
								</div>							
							</div>
						</div>
						
						<div class="row col-lg-6 col-md-12">	
							<div class="container-fluid">
								<h4><strong>PELAJE</strong></h4>
							</div>
							<div class="container-fluid" id="pelajeGroup">
								<div class="col-sm-4 centrado">
									<label class="sr-only">Tamano del perro</label>
									<button class="icon-button <%=largo%>" id="btnTijeraGrande">						
										<span class="fa fa-scissors" style="font-size:90px"></span>
										<p><strong class="text-center"> LARGO </strong></p>
									</button>								
								</div>
								<div class="col-sm-4 centrado">
									<label class="sr-only">Tamano del perro</label>
									<button class="icon-button <%=corto%>" id="btnTijeraChica">						
										<span class="fa fa-scissors" style="font-size:70px;margin-top:20px"></span>
										<p><strong class="text-center"> CORTO </strong></p>
									</button>								
								</div>
							</div>
						</div>
					
					</div>
					
					<div class="form-group">
					
						<div class="row col-md-12">	
							
							<div class="container-fluid">
								<div class="container-fluid">
									<h4><strong>TIPO DE SERVICIO</strong></h4>
								</div>
								<div class="selectContainer col-xs-12 col-sm-8" id="servicioGroup">
				    				<label class="sr-only">Selecciona un tipo de servicio</label>
								    <select class="form-control" name="servicioSeleccionado" id="servicio" aria-describedby="servicioHelp" required>
								    	<option value="servicio">Seleccion&aacute; un tipo de servicio</option>
								    	<% ControladorDeServicio ctrlServicio = new ControladorDeServicio();
								    	ArrayList<Servicio> servicios = ctrlServicio.getServicios();
								    	for(Servicio servicio : servicios){
								    		String selected="";
									    	if (servicio.getIdServicio() == turnoActual.getServicio().getIdServicio()){
									    		selected=" selected";
									    	}
								    	%>
								    	<option value="<%=servicio.getIdServicio()%>"<%=selected%> ><%=servicio.getTipo()%></option>
								   		<% 		
								     	}
								    	%>
								    </select>
								</div>
								<div class="col-xs-12 col-sm-4 checkbox">
								<%
								String conRetiro="";
								if (turnoActual.getRetiroDom()){
									conRetiro=" checked";
								}
								%>
								
									<input type="checkbox" id="conRetiro" <%=conRetiro%> >Incluye retiro a domicilio
								</div>								
							</div>
						</div>
					</div>
					
					<hr>
					
					<div class="form-group">			
						
						<div class="row col-md-12">	
							
							<div class="container-fluid">
							
								<div class="selectContainer col-xs-12 col-md-8" id="fechaGroup">
				    				<h4><strong>SELECCIONE LA FECHA</strong></h4>
				    				<%Date fecha = turnoActual.getFecha();%>
				    				
				    				<input type="date" class="form-control" id="fechaSeleccionada" value="<%=fecha %>"></input>
								</div>
								<div class="col-xs-12 col-md-4" id="horarioGroup">
									<h4><strong>HORARIOS DISPONIBLES</strong></h4>
									<label class="sr-only">Selecciona un horario</label>
									<select class="form-control" name="horarioSeleccionado" id="horario" aria-describedby="horarioHelp" required>
								    	<option value="<%=turnoActual.getHora()%>" selected><%=turnoActual.getHora()%></option>
								    	<%ArrayList<Time> horarios = (ArrayList<Time>) request.getAttribute("horarios"); 
								    	for (Time horario : horarios){
								    	%>
								    	<option value="<%=horario %>"><%=horario %></option>
								    	<%
								    	}
								    	%>
								    </select>
								    <div class="container-fluid checkbox col-md-offset-1 ">
								    <%String repetir = "", radioGroup="",semanal="",quincenal="",mensual="";
								    if (!turnoActual.getRepetir().equals("No")){
								    	repetir = " checked";
								    	switch (turnoActual.getRepetir()){
								    	case "Semanal":
								    		semanal = " checked";
								    		break;
								    	case "Quincenal":
								    		quincenal =" checked";
								    		break;
								    	case "Mensual":
								    		mensual = " cheked";
								    		break;
								    	}
								    	
								    }else {radioGroup = " hidden";}
								    	%>
								    
										<input type="checkbox" <%=repetir %> id="repetir" >Repetir
										<div class="container-fluid <%=radioGroup %>" id="repetirRadioGroup">
											<div class="radio" >
												<label for ="semanal">
													<input type="radio" name="opcion" value="Semanal" class="rbutton"<%=semanal %>> Semanal
												</label>
											</div>
											<div class="radio" >
												<label for ="quincenal">
													<input type="radio" name="opcion" value="Quincenal" class="rbutton"<%=quincenal %>> Quincenal
												</label>
											</div>
											<div class="radio" >
												<label for ="mensual">
													<input type="radio" name="opcion" value="Mensual" class="rbutton"<%=mensual %>> Mensual
												</label>
											</div>
										</div>
											
									</div>	
								</div>								
							</div>
						</div>
					</div>
					
					<hr>
					<div class="form-group">				
						<div class="row col-md-12">	
							<div class="container-fluid" id="clienteGroup">
								<div class="container input-group ">
									<h4><strong>SELECCIONAR UN CLIENTE</strong></h4>
								</div>
								
								<div class="table-responsive col-sm-8" id="tablaClienteSeleccionado">
									
									<table class="table table-striped active" id="tableUsuario">
										<tr id="idUsuario" data-value="<%=turnoActual.getMascota().getUsuario().getIdUsuario()%>">
											<td id="nombreApellidoCliente"><%=(turnoActual.getMascota().getUsuario().getNombre()+", "+turnoActual.getMascota().getUsuario().getApellido())%></td>
											<td id="telefonoCliente"><%=turnoActual.getMascota().getUsuario().getTelefono()%></td>
											<td id="direccionCliente"><%=turnoActual.getMascota().getUsuario().getDireccion()%></td>
										</tr>
									</table>
								</div>

								<div class="col-sm-4 col-xs-12 pull-right">
									<a href="#buscarCliente" class="btn btn-primary form-control hidden" id="btnAgregarUnCliente" data-toggle="modal"> <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span> Agregar Cliente </a>
								</div>
								<div class="col-sm-4 col-xs-12 pull-right">
									<a href="#quitarCliente" class="btn btn-danger form-control" id="btnQuitarCliente"> <span class="glyphicon glyphicon-remove "> </span><span class="glyphicon glyphicon-user"></span> Quitar Cliente </a>
								</div>
								<br>
							</div>
						</div>
					</div>
					<hr>
					<div class="form-group">			
						
						<div class="row col-md-12">	
							
							<div class="container-fluid">
							
								<div class="selectContainer col-xs-12 col-sm-8" id="mascotaGroup">
				    				<h4><strong>SELECCIONE MASCOTA</strong></h4>
				    				<select class="form-control" name="mascotaSeleccionada" id="mascota" aria-describedby="mascotaHelp">
								    	<option value="mascota">Seleccion&aacute; una mascota</option>
								    	<% ArrayList<Mascota> mascotasUsuario = (ArrayList<Mascota>) request.getAttribute("mascotasUsuario");
								    	
										for (Mascota mascota : mascotasUsuario){
											String selected="";
								    		if (mascota.getIdMascota()==turnoActual.getMascota().getIdMascota()){
								    			selected=" selected";
								    		}
								    	%>
								    	<option value="<%=mascota.getIdMascota() %>" <%=selected %>><%=mascota.getNombre() %></option>
								    	<%} %>
								    </select>
								</div>
								<div class="col-xs-12 col-sm-4">
								<br class="hidden-xs">
								<br>
									<span class="small">Solo se muestran mascotas que coincidan con el tamaño y pelaje seleccionado</span>
								</div>								
							</div>
						</div>
					</div>
					
					<hr>
					<div class="col-sm-3 col-xs-12 pull-right">

						<button class="btn btn-primary btn-lg btn-group-justified" name="btnContinuarModificacion" id="btnContinuarModificacion"> Continuar </button>
					
					</div>
					
				
								
				<!-- PANEL MODAL DE BUSQUEDA DE CLIENTE -->
					<div class="modal fade" id="buscarCliente">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<strong>BUSQUEDA DE CLIENTES</strong>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									<div class="container-fluid">
									<br>
										<div class="col-xs-12 col-sm-8" id="inputClienteGroup">
											<input class="form-control" placeholder="Ingres&aacute; nombre o apellido" id="inputCliente"></input>
										</div>
										<div class="col-xs-12 col-sm-4">
											<button class="btn btn-primary" id="btnBuscarCliente"><span class="glyphicon glyphicon-search"></span> Buscar</button>
										</div>								
									</div>
									
									<br>
									<div class="container-fluid">
										<div class="selectContainer col-xs-12 col-sm-8" id="clienteGroup">
						    				<label class="sr-only">Seleccione un cliente</label>
										    <select class="form-control" name="clienteSeleccionado" id="cliente" aria-describedby="clienteHelp" disabled>
										    	<option value="cliente">Seleccion&aacute; un cliente</option>
										    </select>
										</div>
										<div class="col-xs-12 col-sm-4">
											<button class="btn btn-primary" id="btnAgregarClienteSeleccionado"><span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span> Agregar Cliente </button>
										</div>							
									</div>
									<br>
									<br>
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