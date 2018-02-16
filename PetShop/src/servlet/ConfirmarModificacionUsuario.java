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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
			
			String idUsuario = (String) cliente.get("idUsuario").getAsString();
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
			
			try {
				usu.setIdUsuario(Integer.parseInt(idUsuario));
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
				if(!(mascotas.isJsonNull())){
					
					 for (JsonElement obj : mascotas){
						 
						JsonObject gsonObj = obj.getAsJsonObject();
						String idMasco = gsonObj.get("idMascota").getAsString();
						String nombreMasco = gsonObj.get("nombreMascota").getAsString();
						String tamanioMasco = gsonObj.get("tamanioMascota").getAsString();
						String pelajeMasco = gsonObj.get("pelajeMascota").getAsString();
						String fechaMasco = gsonObj.get("fechaNacimientoMascota").getAsString();
						String observacionesMasco = gsonObj.get("observacionesMascota").getAsString();
							
					 	Mascota masco = new Mascota();
					 	
					 	int idMascotaActual=0;
						if(!(idMasco.equals(""))){
							idMascotaActual = Integer.parseInt(idMasco);
						}
					 	masco.setIdMascota(idMascotaActual);
						masco.setNombre(nombreMasco);
						
						SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");			
						Date fechaDate = fecha.parse(fechaMasco);
						java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
						masco.setFechaNacimiento(sqlDate);
						
						masco.setObservaciones(observacionesMasco);
						
						masco.setTipoMascota(ctrlTipoMascota.getTipoMascotaSegunTamanioPelaje(tamanioMasco, pelajeMasco));
						
						masco.setUsuario(usu);
						
						masco.setEstado(1);
						
						if(masco.getIdMascota()==0){
							ctrlMascota.agregarMascota(masco);
							//System.out.println("SE AGREGA"+masco.getNombre());
						}else{
							
							if(masco.getNombre().equals("QuitarMascota")){
								ctrlMascota.eliminarMascota(masco);
								//System.out.println("SE ELIMINA"+masco.getNombre());
							}else{
								ctrlMascota.modificarMascota(masco);
								//System.out.println("SE MODIFICA"+masco.getNombre());
							}
						}
					}
					 
					
				}
				
				usu.setMascotas(ctrlMascota.getMascotas(usu));
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", usu);
				response.getWriter().println(1);		 
				
				
				}
			catch (MySQLIntegrityConstraintViolationException e1){
				e1.printStackTrace();
				response.getWriter().println(2); //el correo ingresado ya existe
			}
			catch (Exception e) {
				response.getWriter().println(0);
				e.printStackTrace();
			}			
			

		}
	}