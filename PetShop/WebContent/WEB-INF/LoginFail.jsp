<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/estilos.css" type="text/css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <script src="style/ie-emulation-modes-warning.js"></script>
    <title>Log In</title>
  </head>

  <body>
    <div class = "container col-lg-6">
		<div class="wrapper">
			<br>
			<form action="start" method="post" name="Login_Form" class="form-signin">       
			    <h4 class="form-signin-heading">Sistema de Gesti&oacuten de Pet Shops</h4>
				  <hr class="colorgraph">
				  <h6 class="">Autenticaci&oacuten</h6>
				  <div class="input-group mb-2 mr-sm-2 mb-sm-2 has-danger">
				  	<div class="input-group-addon">
				  		<i class="fa fa-user" aria-hidden="true"></i>
				  	</div>
				  	
				  	<input type="text" class="form-control has-danger" name="Username" placeholder="Ingres&aacute tu usuario" value="<%=(String) session.getAttribute("userError")%>" required="" />
				  </div>
				  
				  <div class="input-group mb-2 mr-sm-2 mb-sm-2 has-danger">
				  	<div class="input-group-addon">
				  		<i class="fa fa-lock" aria-hidden="true"></i>
				  	</div>
				  	<input type="password" class="form-control has-error " name="Password" placeholder="Ingres&aacute tu contraseña" value="<%=(String) session.getAttribute("passError")%>" required=""/>    		  
				  </div>
				  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Ingresar</button>  			
			</form>	
			
			<br/>
			<div class="alert alert-warning confirmacion"><%=(String)session.getAttribute("mensaje")%>
		    	<button class="close" data-dismiss="alert"><span>&times;</span></button>
		    </div>    	   		    	  	
		</div>
	</div>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>