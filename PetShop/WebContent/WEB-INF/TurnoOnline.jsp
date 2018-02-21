<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Servicio"%>
<%@page import="entidades.Mascota"%>
<%@page import="entidades.Turno"%>
<%@page import="logica.ControladorDeServicio"%>
<%@page import="logica.ControladorDeMascota"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	<jsp:include page="cssGeneral.jsp" />
			
	<title>SGPS - Turnos</title>
	
</head>
<body onload="iniciar('turnos');">
	<jsp:include page="Navbar.jsp" />
	
	<div class="container panel panel-default colorPanel">
	 	<div class="panel-body">
			<br>
			<br>
			<h4><strong>TURNO ONLINE</strong> - Paso 1 de 2</h4>
			<hr>
			<form action="#" method="post" class="form-horizontal"  id="formTurnos">
				<div class="container-fluid">
				
				<div class="form-group">			
						
					<div class="row col-md-12">	
						<div class="selectContainer col-xs-12 col-sm-6" id="mascotaGroup">
		    				<h4><strong class="text-muted">SELECCION&Aacute; UNA DE TUS MASCOTAS</strong></h4>
		    				<select class="form-control" name="mascotaSeleccionada" id="mascota" aria-describedby="mascotaHelp">
						    	<option value="mascota">Seleccion&aacute; una mascota</option>
						    	<%ControladorDeMascota ctrlMascota = new ControladorDeMascota();
						    	Usuario usuario = (Usuario) session.getAttribute("user");
						    	ArrayList<Mascota> mascotas = ctrlMascota.getMascotas(usuario); 
						    	for (Mascota mascota : mascotas){
						    	%>
						    	<option value="<%=mascota.getIdMascota()%>"><%=mascota.getNombre()%></option>
						    	<%} %>
						    </select>
						</div>						
					</div>
				</div>
				<hr>
					
				<div class="form-group">
					<div class="row col-md-12">	
						<div class="container-fluid">
							<h4><strong class="text-muted">ELEG&Iacute; QUE TIPO DE SERVICIO QUER&Eacute;S</strong></h4>
						</div>
						<div class="selectContainer col-xs-12 col-sm-8" id="servicioGroup">
		    				<label class="sr-only">Selecciona un tipo de servicio</label>
						    <select class="form-control" name="servicioSeleccionado" id="servicio" aria-describedby="servicioHelp" required>
						    	<option value="servicio">Seleccion&aacute; un tipo de servicio</option>
						    	<% ControladorDeServicio ctrlServicio = new ControladorDeServicio();
						    	ArrayList<Servicio> servicios = ctrlServicio.getServicios();
						    	for(Servicio servicio : servicios){
						    	%>
						    	<option value="<%=servicio.getIdServicio()%>"><%=servicio.getTipo()%></option>
						   		
						   		<% 		
						     	}
						    	%>
						    </select>
						</div>
						<div class="col-xs-12 col-sm-4 checkbox">
							<input type="checkbox" id="conRetiro" >Incluye retiro a domicilio
						</div>								
					</div>
				</div>
				
				<hr>
				
				<div class="form-group">			
					<div class="row col-md-12">	
						<div class="selectContainer col-xs-12 col-md-8" id="fechaGroup">
		    				<h4><strong class="text-muted">SELECCION&Aacute; LA FECHA</strong></h4>
		    				<input type="date" class="form-control" id="fechaSeleccionada"></input>
						</div>
						<div class="col-xs-12 col-md-4" id="horarioGroup">
							<h4><strong class="text-muted">ELEG&Iacute; EL HORARIO</strong></h4>
							<label class="sr-only">Selecciona un horario</label>
							<select class="form-control" name="horarioSeleccionado" id="horario" aria-describedby="horarioHelp" required>
						    	<option value="horario">Seleccion&aacute; un horario</option>
						    </select>
						    <div class="container-fluid checkbox col-md-offset-1 ">
								<input type="checkbox" id="repetir" >Repetir
								<div class="container-fluid hidden" id="repetirRadioGroup">
									<div class="radio" >
										<label for ="semanal">
											<input type="radio" name="opcion" value="Semanal" class="rbutton"> Semanal
										</label>
									</div>
									<div class="radio" >
										<label for ="quincenal">
											<input type="radio" name="opcion" value="Quincenal" class="rbutton"> Quincenal
										</label>
									</div>
									<div class="radio" >
										<label for ="mensual">
											<input type="radio" name="opcion" value="Mensual" class="rbutton"> Mensual
										</label>
									</div>
								</div>	
							</div>	
						</div>								
					</div>
				</div>
					
				<hr>
				
				<div class="form-group">				
					<div class="row col-md-12">	
						<div class="container ">
							<h4><strong class="text-muted">CORROBOR&Aacute; TU INFORMACI&Oacute;N PERSONAL</strong></h4>
						</div>
						<div class="container-fluid">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
										
											<th>Nombre</th>
											<th>Apellido</th>
											<th>Direcci&oacute;n</th>
											<th>Tel&eacute;fono</th>
											<th>Email</th>
										</tr>
									</thead>
									<tbody>
									<%String email = "";
									if(!(usuario.getEmail()==null)){
										email=usuario.getEmail();
									}
									%>
										<tr>
											<td><%=usuario.getNombre() %></td>
											<td><%=usuario.getApellido() %></td>
											<td><%=usuario.getDireccion() %></td>
											<td><%=usuario.getTelefono() %></td>
											<td><%=email%></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="form-group">
					<div class="container-fluid">
						<div class="row col-md-12">	
							<div class="input-group">
								<h4><strong class="text-muted">ESCRIB&Iacute; LOS DATOS ADICIONALES QUE TE PAREZCA</strong></h4>
							</div>
							<div class="form-group">
								<div class="col-xs-12">
									<textarea class="form-control" rows="5" id="observaciones" placeholder="Escrib&iacute; ac&aacute; los datos adicionales de ser necesarios"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-xs-12 pull-right">

					<button class="btn btn-primary btn-lg btn-group-justified" name="btnContinuarAlta" id="btnContinuarAlta"> Continuar </button>
				
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
	
	<jsp:include page="jsGeneral.jsp" /> 
	<script type="text/javascript" src="js/turnoOnline.js"></script>
	<script type="text/javascript" src="js/ventaOnline.js"></script>
	 
</body>
</html>