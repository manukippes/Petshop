package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Usuario;
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
				
		String json = request.getParameter("jsonData");
		JsonObject campos = (JsonObject) new JsonParser().parse(json);
		
		String nombre = (String) campos.get("nombre").getAsString();
		String apellido = (String) campos.get("apellido").getAsString();
		int dni = (int) campos.get("dni").getAsInt();
		String direccion = (String) campos.get("direccion").getAsString();
		int telefono = (int) campos.get("telefono").getAsInt();
		String email = (String) campos.get("email").getAsString();
		
		usu.setNombre(nombre);
		usu.setApellido(apellido);
		usu.setDni(dni);
		usu.setDireccion(direccion);
		usu.setTelefono(telefono);
		usu.setEmail(email);
		
		try {
			if(ctrlUsuario.agregarUsuario(usu)){
				response.getWriter().println(true);	
			}else{
				response.getWriter().println(false);	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
	}

}
