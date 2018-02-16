package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Mascota;
import entidades.Usuario;
import logica.ControladorDeMascota;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ConfirmarModificacionUsuario
 */
@WebServlet("/ConfirmarModificacionUsuario")
public class ConfirmarModificacionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarModificacionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		}


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
			Usuario usu = new Usuario();
			ControladorDeMascota ctrlMascota = new ControladorDeMascota();
			ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		
			String json = request.getParameter("jsonData");
			JsonObject cliente = (JsonObject) new JsonParser().parse(json);
			
			String usuario = (String) cliente.get("usuario").getAsString();
			String password = (String) cliente.get("password").getAsString();
			
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
					usu = ctrlUsuario.modificarUsuario(usu);

					JsonArray mascotas = (JsonArray) cliente.get("arregloMascotas").getAsJsonArray();
					if(!(mascotas.isJsonNull()))
					{
						
						try {
							 for (JsonElement obj : mascotas) 
							 {
								JsonObject gsonObj = obj.getAsJsonObject();
								String nombreMasco = gsonObj.get("nombreMascota").getAsString();
								String tamanioMasco = gsonObj.get("tamanioMascota").getAsString();
								String pelajeMasco = gsonObj.get("pelajeMascota").getAsString();
								String fechaMasco = gsonObj.get("fechaNacimientoMascota").getAsString();
								String observacionesMasco = gsonObj.get("observacionesMascota").getAsString();
									
							 	Mascota masco = new Mascota();
								masco.setNombre(nombreMasco);
								
								SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");			
								Date fechaDate = fecha.parse(fechaMasco);
								java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
								masco.setFechaNacimiento(sqlDate);
								
								masco.setObservaciones(observacionesMasco);
								
								masco.setTipoMascota(ctrlTipoMascota.getTipoMascotaSegunTamanioPelaje(tamanioMasco, pelajeMasco));
								
								masco.setUsuario(usu);
								
								if(ctrlMascota.existeMascota(masco)){
									ctrlMascota.agregarMascota(masco);
								}else{
									ctrlMascota.modificarMascota(masco);
								}
								
							 }
							 usu.setMascotas(ctrlMascota.getMascotas(usu));
							 request.getSession().removeAttribute("user");
							 request.getSession().setAttribute("user", usu);
							 response.getWriter().println(1);
						} 
						catch (Exception e) {
							response.getWriter().println(0);
							e.printStackTrace();
						}
				}
			}
			catch (Exception e1) {
			response.getWriter().println(0);
			e1.printStackTrace();
			}

		}
	}