<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Mascota"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="css/estilos.css" type="text/css">
		<link rel="stylesheet" href="font-awesome/css/font-awesome.css" type="text/css">
		<link rel="shortcut icon" href="#">

		<title>SGPS - Modificar cliente</title>
	</head>
	<body onload="iniciar('administracion');">
		<jsp:include page="Navbar.jsp" />
		<div class="container panel panel-default colorPanel">
			<div class="panel-body">
				<br>
				<br>	
				<h4><strong>MODIFICAR CLIENTE</strong></h4>
				<hr>
				
				<div class="container-fluid">
					<form class="form" action="ModificarCliente" method="post" enctype="multipart/form-data" id="form_modificar	_cliente">
		            	
		     
		            	<!-- Campo de modificacion / alta -->
		            	<input type="hidden"  name="alta" id="alta" value="alta">
						
		            	<div class="form-group row">
		            	<% 
		            	Usuario cli = ((Usuario) session.getAttribute("cliente")); 
		            	ArrayList<Mascota> listadoMascota = cli.getMascotas();
		            	%>
		            	<!-- Input de idUsuario -->
			            		
							    <input type="text" class="hidden" name="idUsuario" id="idUsuario" aria-describedby="usuarioHelp" value="<%= cli.getIdUsuario() %>">
		            	
		            	  	<!-- Input de nombre -->
			            		<label class="sr-only">Nombre</label>
							    <div class="col-lg-6 col-md-12" id="nombreGroup">
							    	<small id="nombreHelp" class="form-text text-muted"><strong>Nombre*</strong></small>
							    	<input type="text" class="form-control" name="nombre" id="nombre" aria-describedby="nombreHelp" placeholder="Ingres&aacute; el nombre del cliente a dar de alta" value="<%= cli.getNombre() %>">
								</div>
								
							<!-- Input de apellido -->
								<label class="sr-only">Apellido</label>
							    <div class="col-lg-6 col-md-12" id="apellidoGroup">
							    	<small id="apellidoHelp" class="form-text text-muted"><strong>Apellido*</strong></small>
							    	<input type="text" class="form-control" name="apellido" id="apellido" aria-describedby="apellidoHelp" placeholder="Ingres&aacute; el apellido del cliente a dar de alta" value="<%= cli.getApellido() %>">
								</div>
						</div>
						<hr>
						
						<div class="form-group row">
			            	<!-- Input de dni-->
			            		<label class="sr-only">DNI</label>
							    <div class="col-lg-6 col-md-12" id="dniGroup">
							    	<small id="precioHelp" class="form-text text-muted"><strong>Dni</strong></small>
							    	<input type="text" class="form-control" name="dni" id="dni" aria-describedby="dniHelp" placeholder="Ingres&aacute; el dni del cliente a dar de alta" value="<%= cli.getDni() %>">
								</div>
								
							<!-- Input de direccion -->
								<label class="sr-only">Direcci&oacute;n</label>
							    <div class="col-lg-6 col-md-12" id="direccionGroup">
							    	<small id="direccionHelp" class="form-text text-muted"><strong>Direcci&oacute;n</strong></small>
							    	<input type="text" min="0"class="form-control" name="direccion" id="direccion" aria-describedby="direccionHelp" placeholder="Ingres&aacute; la direcci&oacute;n del cliente a dar de alta" value="<%= cli.getDireccion() %>">
								</div>
						</div>
						<hr>
						
						<div class="form-group row">
							<!-- Input de telefono -->
								<label class="sr-only">Tel&eacute;fono</label>
							    <div class="col-lg-6 col-md-12" id="telefonoGroup">
							    	<small id="direccionHelp" class="form-text text-muted"><strong>Tel&eacute;fono*</strong></small>
							    	<input type="text" class="form-control" name="telefono" id="telefono" aria-describedby="telefonoHelp" placeholder="Ingres&aacute; el tel&eacute;fono, sin guiones, del cliente a dar de alta" value="<%= cli.getTelefono() %>">
								</div>
						         	
			            	<!-- Input de email-->
			            		<label class="sr-only">Email</label>
							    <div class="col-lg-6 col-md-12" id="emailGroup">
							    	<small id="emailHelp" class="form-text text-muted"><strong>Email</strong></small>
							    	<input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Ingres&aacute; el email del cliente a dar de alta" value="<%= cli.getEmail() %>">
								</div>
						</div>
						<hr>
						
						<div class="form-group row ">
							<label class="sr-only">Mascota</label>
							<div class="col-lg-3 col-md-12">
								<a href="#agregarMascotaModificar" class="btn btn-primary form-control" id="btnAgregarMascota" data-toggle="modal"> <span class="glyphicon glyphicon-plus"></span> Agregar Mascota</a>
							</div>
								<div class="col-lg-9 col-md-12" id="MascotaGroup">	
								<%String hidden = " hidden";if(!(listadoMascota.isEmpty()) || listadoMascota.size() != 0){hidden="";}%>
								<div class="table-responsive <%=hidden%>" id="tablaMascota">
   								   <table class="table table-striped table-hover active tableMas" id="tableMas">
										<thead>
											<tr>
											    <th>Nombre</th>
											    <th>Tama�o</th>
											    <th>Pelaje</th>
											    <th>Fecha de Nacimiento</th>
											    <th>Quitar</th>
											    <th class='hidden'></th>
											</tr>
										</thead>
  										<tbody>	
  											<%if(!(listadoMascota.isEmpty()) || listadoMascota.size() != 0)
  											{
  												for(Mascota masco : listadoMascota)
  												{					
  											%>
  												<tr>
  													<td class="hidden" id='idMascota'><%=masco.getIdMascota() %></td>
	  												<td id='nombreMascota'><%=masco.getNombre()%></td> 
													<td id='tamanio'><%=masco.getTipoMascota().getTamanio()%></td>
													<td id='pelaje'><%=masco.getTipoMascota().getPelo()%></td>
													<td id='fechaNacimiento'><%=masco.getFechaNacimiento().toString()%></td>
													<td class='col-sm-3 col-lg-2'>
														<div class='input-group'>
															<a class='btn btn-info btnModificarMascota' title='Editar mascota' href='\'><span class='fa fa-pencil'></span> </a>
														
															<a class='btn btn-danger btnQuitarMascota' title='Quitar mascota' href='\'><span class='fa fa-times'></span> </a>
														</div>
													</td>
													<td id='observacion' class='hidden'><%=masco.getObservaciones()%></td>
												</tr>
											<%	}
  											}%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<hr>
									
						<div class="form-group row">	
							<div class="col-lg-12">
								<input type="checkbox" id="habilitado" <% int estado = cli.getEstado();if(estado==1){%>checked<%}%>><small id="habilitadoHelp" class="form-text text-muted"><strong> Habilitado</strong></small>
							</div>
							<div class="col-lg-12">
								<input type="submit" id="btnModificarUsuario" value="Modificar" class="col-lg-2 col-xs-12 btn btn-primary btn-lg pull-right">
							</div>
						</div>
						
						<!-- PANEL MODAL AGREGAR MASCOTA -->
						<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="agregarMascotaModificar">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<strong>AGREGAR MASCOTA</strong>
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group row container-fluid">
										
											<!-- Input de idMascota -->
							   					 <input type="text" class="hidden" name="idMascotaHidden" id="idMascotaHidden" aria-describedby="idMascotaHelp">
										
											<!-- Input de Nombre Mascota -->
												<label class="sr-only">Nombre</label>
											    <div class="col-lg-12 col-md-12" id="nombreMascotaGroup">
											    	<small id="nombreMascotaHelp" class="form-text text-muted"><strong>Nombre *</strong></small>
											    	<input type="text" class="form-control nombreMascota" name="nombreMascota" id="nombreMascota" placeholder ="Ingres&aacute; el nombre de la mascota.">
												</div>
										</div>
										<hr class="hrModal">
										<div class="form-group row">
											<div class="col-lg-6 col-md-12">	
												<div class="container-fluid">
													<h4><strong>TAMA&Ntilde;O DEL PERRO *</strong></h4>
												</div>
												<div class="container-fluid" id="patitaGroup">
													<div class="col-sm-4 centrado">
														<label class="sr-only">Perro grande</label>
														<button class="icon-button" id="btnPatitaGrande">						
															<span class="fa fa-paw" style="font-size:90px"></span>
															<p><strong class="text-center"> GRANDE </strong></p>
														</button>								
													</div>
													<div class="col-sm-4 centrado">
														<label class="sr-only">Perro mediano</label>
														<button class="icon-button" id="btnPatitaMediana">						
															<span class="fa fa-paw" style="font-size:70px;margin-top:20px"></span>
															<p><strong class="text-center"> MEDIANO </strong></p>	
														</button>			
													</div>
													<div class="col-sm-4 centrado">
														<label class="sr-only">Perro chico</label>
														<button class="icon-button" id="btnPatitaChica">						
															<span class="fa fa-paw" style="font-size:50px;margin-top:40px"></span>
															<p><strong class="text-center"> CHICO </strong></p>		
														</button>					
													</div>
												</div>
											</div>
											
											<div class="col-lg-6 col-md-12">	
												<div class="container-fluid">
													<h4><strong>PELAJE *</strong></h4>
												</div>
												<div class="container-fluid" id="pelajeGroup">
													<div class="col-sm-4 centrado">
														<label class="sr-only">Tamano del pelaje</label>
														<button class="icon-button" id="btnTijeraGrande">						
															<span class="fa fa-scissors" style="font-size:90px"></span>
															<p><strong class="text-center"> LARGO </strong></p>
														</button>								
													</div>
													<div class="col-sm-4 centrado">
														<label class="sr-only">Tamano del pelaje</label>
														<button class="icon-button"id="btnTijeraChica">						
															<span class="fa fa-scissors" style="font-size:70px;margin-top:20px"></span>
															<p><strong class="text-center"> CORTO </strong></p>
														</button>								
													</div>
												</div>
											</div>
										</div>
										<hr class="hrModal">
										<div class="form-group row container-fluid">
											<!-- Input de Fecha de Nacimiento Mascota -->
												<label class="sr-only">Fecha de Nacimiento</label>
											    <div class="col-lg-4 col-md-12" id="fechaNacimientoMascotaGroup">
											    	<small id="fechaNacimientoMascotaHelp" class="form-text text-muted"><strong>Fecha de Nacimiento</strong></small>
											    	<input type="date" class="form-control" name="fechaNacimientoMascota" id="fechaNacimientoMascota" aria-describedby="fechaNacimientoMascotaHelp">
												</div>
												
												<!-- Input de Observaciones Mascota -->
												<label class="sr-only">Observaciones</label>
											    <div class="col-lg-8 col-md-12" id="observacionesMascotaGroup">
											    	<small id="observacionesMascotaHelp" class="form-text text-muted"><strong>Observaciones</strong></small>
											    	<textarea class="form-control" rows="5" id="observacionesMascota" placeholder="Completa con datos adicionales de la mascota de ser necesarios"></textarea>
												</div>
										</div>
										<hr class="hrModal">
										<div class="form-group row container-fluid">
											<div class="col-lg-12 col-md-12">
												<button class="col-lg-3 col-md-12 btn btn-primary pull-right" id="btnAgregarMascotaModal"><h4><span class="fa fa-paw"></span> Confirmar</h4></button>
											</div>
										</div>
									</div>								
								</div>
							</div>
						</div>
						<!-- PANEL MODAL CONFIRMACION -->
						
						<a href="#confirmacionmodal" class="hidden" id='btnHidden' data-toggle='modal'>botonazo</a>
						<div class="modal fade" id="confirmacionmodal">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<strong class="text-success"><span class="fa fa-check"></span> MODIFICACI&Oacute;N EXITOSA</strong>
										
									</div>
									<div class="modal-body">
										<div class="container-fluid">
											<div class="form-horizontal form-group">
												<br>
												<div class="row-inline">
													<div class="col-xs-12 col-sm-2 centrado">
														<p><span class="fa fa-user-circle text-info" style="font-size:70px"></span></p>
													</div>
													<div class="col-xs-12 col-sm-10 align-middle">
														<br>
														<span class="text-success align-middle">Por favor aguard&aacute; un instante mientras actualizamos los datos del cliente</span>
													
													</div>
													<br>
													<br>
													<br>
													<div id="barraProgreso" class="col-xs-12 col-sm-10">
														<div class="progress ">
															<div class="progress-bar progress-bar-success progress-bar-striped active " role="progressbar" style="width:100%;min-width:100%";>
																<span class="">Guardando datos...</span>
															</div>
														</div>
													</div>									
															
												</div>
												<div class="row">
													<br>
													<br>
												</div>
																		
												<br>
											</div>
										</div>								
									</div>
								</div>
							</div>
						</div>
		            </form> 
		        </div>
		   	</div>
		</div>
			<script type="text/javascript" src="js/jquery-latest.js"></script>
			<script type="text/javascript" src="js/bootstrap.min.js"></script>
			<script type="text/javascript" src="js/clientes.js"></script>
			<script type="text/javascript" src="js/main.js"></script>
	</body>
</html>