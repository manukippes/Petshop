package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
				
		String json = request.getParameter("jsonData");
		System.out.println(json);
		JsonObject cliente = (JsonObject) new JsonParser().parse(json);
				
		String nombre = (String) cliente.get("nombre").getAsString();
		String apellido = (String) cliente.get("apellido").getAsString();
		int dni = (int) cliente.get("dni").getAsInt();
		String direccion = (String) cliente.get("direccion").getAsString();
		int telefono = (int) cliente.get("telefono").getAsInt();
		String email = (String) cliente.get("email").getAsString();
		
		usu.setNombre(nombre);
		usu.setApellido(apellido);
		usu.setDni(dni);
		usu.setDireccion(direccion);
		usu.setTelefono(telefono);
		usu.setEmail(email);
		Usuario nuevoUsuario = new Usuario();
		try {
			
			nuevoUsuario = ctrlUsuario.agregarUsuario(usu);
		} catch (Exception e1) {
			response.getWriter().println(false);
			e1.printStackTrace();
		}
		
		JsonArray mascotas = (JsonArray) cliente.get("arregloMascotas").getAsJsonArray();
			try {
				for (int i = 0; i < mascotas.size(); i++) {
						String nombreMasco = ((JsonObject) mascotas.get(i)).get("nombreMascota").getAsString();
						String tamanioMasco = ((JsonObject) mascotas.get(i)).get("tamanioMascota").getAsString();
						String pelajeMasco = ((JsonObject) mascotas.get(i)).get("pelajeMascota").getAsString();
						String fechaMasco = ((JsonObject) mascotas.get(i)).get("fechaNacimientoMascota").getAsString();
						String observacionesMasco = ((JsonObject) mascotas.get(i)).get("observacionesMascota").getAsString();
						Mascota masco = new Mascota();
						masco.setNombre(nombreMasco);
						Date fechaConvertida = (Date) fecha.parse(fechaMasco);
						masco.setFechaNacimiento(fechaConvertida);
						masco.setObservaciones(observacionesMasco);
						masco.setTipoMascota(ctrlTipoMascota.getTipoMascotaSegunTamanioPelaje(tamanioMasco, pelajeMasco));
						masco.setUsuario(nuevoUsuario);
						ctrlMascota.agregarMascota(masco);
					}
					response.getWriter().println(true);	
			} catch (Exception e) {
				response.getWriter().println(false);
				e.printStackTrace();
			}
	}
}
