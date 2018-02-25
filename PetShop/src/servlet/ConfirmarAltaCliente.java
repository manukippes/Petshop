package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entidades.Mascota;
import entidades.Usuario;
import logica.ControladorDeMascota;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;

@WebServlet({"/ConfirmarAltaCliente", "/Confirmaraltacliente", "/confirmaraltacliente"})
public class ConfirmarAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConfirmarAltaCliente() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		Usuario usu = new Usuario();
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();		
	
		String json = request.getParameter("jsonData");
		JsonObject cliente = (JsonObject) new JsonParser().parse(json);
		
		String usuario = (String) cliente.get("usuario").getAsString();
		if(usuario.equals("")){usuario=null;};
		String password = (String) cliente.get("password").getAsString();
		if(password.equals("")){password=null;};
		String nombre = (String) cliente.get("nombre").getAsString();
		String apellido = (String) cliente.get("apellido").getAsString();
		String dniParametro = (String) cliente.get("dni").getAsString();
		int dni=0;
		if(!(dniParametro.equals(""))){
			dni = Integer.parseInt(dniParametro);
		}
		String direccion = (String) cliente.get("direccion").getAsString();
		String telefono = (String) cliente.get("telefono").getAsString();
		String email = (String) cliente.get("email").getAsString();
		if(email.equals("")){email=null;};
		int habilitado = (int) cliente.get("habilitado").getAsInt();
		
		try 
		{
			usu.setUsuarioLogin(usuario);
			usu.setPassword(password);
			usu.setNombre(nombre);
			usu.setApellido(apellido);
			usu.setDni(dni);
			usu.setDireccion(direccion);
			usu.setTelefono(telefono);
			usu.setEmail(email);
			usu.setEstado(habilitado);
			usu.setTipoUsuario("Online");
			usu.setMascotas(new ArrayList<Mascota>());
			usu = ctrlUsuario.agregarUsuario(usu);
			
			ArrayList<Mascota> mascotasTemp = (ArrayList<Mascota>) request.getSession().getAttribute("mascotasTemp");
			
			for(Mascota mascota : mascotasTemp){
				mascota.setUsuario(usu);
				mascota.setEstado(1);
				ctrlMascota.agregarMascota(mascota);
			}
			request.getSession().setAttribute("userOnline", usu);
			response.getWriter().println(1);
		}
		catch (MySQLIntegrityConstraintViolationException mailExistente) {
			String respuesta = mailExistente.getMessage();		//DETERMINO EN QUE CAMPO FALLO LA UNIQUE ID
			
			if (respuesta.toLowerCase().contains(("usuarioLogin_UNIQUE").toLowerCase())){
				response.getWriter().println(4);
			}else{
				if(respuesta.toLowerCase().contains(("email_UNIQUE").toLowerCase())){
					response.getWriter().println(3);
				}
			};
		}

		catch (Exception e1) {
		response.getWriter().println(0);
		e1.printStackTrace();
		}
		
	}
}
