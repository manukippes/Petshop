<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Servicio"%>
<%@page import="logica.ControladorDeServicio"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<meta charset="UTF-8">
	

	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/turnos.js"></script>
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
			<h4><strong>NUEVO TURNO</strong> - Paso 1 de 2</h4>
			<hr>
			
			<form action="#" method="post" class="form-horizontal"  id="formTurnos">
				<div class="container-fluid">
					<div class="form-group">
						
						<div class="row col-lg-6 col-md-12">	
							<div class="container">
								<h4><strong>TAMA&Ntilde;O DEL PERRO</strong></h4>
							</div>
							<div class="container-fluid ">
								<div class="col-sm-4">
									<label class="sr-only">Perro grande</label>
									<button class="icon-button " id="btnPatitaGrande">						
										<span class="fa fa-paw" style="font-size:90px"></span>
										<p><strong class="text-center"> GRANDE </strong></p>
									</button>								
								</div>
								<div class="col-sm-4 active">
									<label class="sr-only">Perro mediano</label>
									<button class="icon-button" id="btnPatitaMediana">						
										<span class="fa fa-paw" style="font-size:70px;margin-top:20px"></span>
									</button>			
									<p><strong class="text-center"> MEDIANO </strong></p>					
									
								</div>
								<div class="col-sm-4">
									<label class="sr-only">Perro chico</label>
									<button class="icon-button" id="btnPatitaChica">						
										<span class="fa fa-paw" style="font-size:50px;margin-top:40px"></span>
										<p><strong class="text-center"> CHICO </strong></p>		
									</button>	
														
								</div>
							</div>
						</div>
						
						<div class="row col-lg-6 col-md-12">	
							<div class="container">
								<h4><strong>PELAJE</strong></h4>
							</div>
							<div class="container-fluid">
								<div class="col-sm-4">
									<label class="sr-only">Tamano del perro</label>
									<button class="icon-button" id="btnTijeraGrande">						
										<span class="fa fa-scissors" style="font-size:90px"></span>
										<p><strong class="text-center"> LARGO </strong></p>
									</button>								
								</div>
								<div class="col-sm-4">
									<label class="sr-only">Tamano del perro</label>
									<button class="icon-button"id="btnTijeraChica">						
										<span class="fa fa-scissors" style="font-size:70px;margin-top:20px"></span>
										<p><strong class="text-center"> CORTO </strong></p>
									</button>								
								</div>
							</div>
						</div>
					
					</div>
					
					<div class="form-group">
					
						<div class="row col-md-12">	
							<div class="container">
								<h4><strong>TIPO DE SERVICIO</strong></h4>
							</div>
							<div class="container-fluid">
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
									<input type="checkbox" id="conRetiro" >Incluye retiro a domicilio</input>
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
				    				<input type="date" class="form-control"></input>
								</div>
								<div class="col-xs-12 col-md-4">
									<h4><strong>HORARIOS DISPONIBLES</strong></h4>
									<label class="sr-only">Selecciona un horario</label>
									<select class="form-control" name="horarioSeleccionado" id="horario" aria-describedby="horarioHelp" required>
								    	<option value="horario">Seleccion&aacute; un horario</option>
								    </select>
								</div>								
							</div>
						</div>
					</div>
					
					<hr>
					
					<div class="form-group">			
						
						<div class="row col-md-12">	
							
							<div class="container-fluid">
							
								<div class="selectContainer col-xs-12 col-lg-8" id="fechaGroup">
				    				<h4><strong>SELECCIONE UN CLIENTE</strong></h4>
				    				<input type="text" class="form-control" placeholder="Ingrese nombre o apellido"></input>
								</div>
								<div class="col-xs-12 col-lg-4">
									<h4><strong>O AGREGUE UN CLIENTE NUEVO</strong></h4>
									<label class="sr-only">Agregar un cliente</label>
									<button class="btn btn-primary">GESTION DE CLIENTES</button>
								</div>								
							</div>
						</div>
					</div>
					<div class="form-group">			
						
						<div class="row col-md-12">	
							
							<div class="container-fluid">
							
								<div class="selectContainer col-xs-12 col-sm-8" id="fechaGroup">
				    				<h4><strong>SELECCIONE MASCOTA</strong></h4>
				    				<select class="form-control" name="mascotaSeleccionada" id="mascota" aria-describedby="mascotaHelp" required>
								    	<option value="mascota">Seleccion&aacute; una mascota</option>
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

						<button class="btn btn-primary btn-lg btn-group-justified" name="btnContinuar" id="btnContinuar"> Continuar </button>
					
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