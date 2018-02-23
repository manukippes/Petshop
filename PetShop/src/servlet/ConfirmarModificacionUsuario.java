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
			Usuario sessionUser = (Usuario) request.getSession().getAttribute("user");
			Boolean usuarioOnline = false;
			if(sessionUser.getTipoUsuario().equals("Online")){
				usuarioOnline = true;
			}
		
			String json = request.getParameter("jsonData");
			JsonObject cliente = (JsonObject) new JsonParser().parse(json);
			
			String idUsuario = (String) cliente.get("idUsuario").getAsString();
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
			
			try {
				usu.setIdUsuario(Integer.parseInt(idUsuario));
				if(!(usuario==null)){							//Si viene vacio estoy en el modulo de administracion
					usu.setUsuarioLogin(usuario);
					usu.setPassword(password);
				}else{
					Usuario userTemp = ctrlUsuario.getUsuario(usu);		//En este caso recupero los datos que ya tenia el usuario
					usu.setUsuarioLogin(userTemp.getUsuarioLogin());
					usu.setPassword(userTemp.getPassword());
				}
				usu.setNombre(nombre);
				usu.setApellido(apellido);
				usu.setDni(dni);
				usu.setDireccion(direccion);
				usu.setTelefono(telefono);
				usu.setEmail(email);
				usu.setEstado(habilitado);
				usu.setTipoUsuario("Online");							//SOLO SE MODIFICAN USUARIOS ONLINE!!
				usu.setMascotas(new ArrayList<Mascota>());
				usu = ctrlUsuario.modificarUsuario(usu);

				//comprobar el listado de mascotas temporal contra las mascotas del usuario
				
				ArrayList<Mascota> mascotasTemp = (ArrayList<Mascota>) request.getSession().getAttribute("mascotasTemp");
				ArrayList<Mascota> borrar = new ArrayList<Mascota>();			//ARREGLO DE MASCOTAS A BORRAR
				ArrayList<Mascota> modificar = new ArrayList<Mascota>();		//ARREGLO DE MASCOTAS A MODIFICAR
				ArrayList<Mascota> agregar = new ArrayList<Mascota>();			//ARREGLO DE MASCOTAS A AGREGAR
				
				for (Mascota mascota : mascotasTemp){
					if (mascota.getIdMascota()==0){		//Las que no tienen id hay que agregarlas
						mascota.setUsuario(usu);
						agregar.add(mascota);
					} else {
						mascota.setUsuario(usu);		//Las que tienen id hay que eliminarlas
						modificar.add(mascota);
					}
				}
				ArrayList<Mascota> mascotasUsu = ctrlMascota.getMascotas(usu);	//Obtengo todas las mascotas actuales del usuario
				
				for (Mascota mascotaE : mascotasUsu){			//recorro las mascotas del usuario
					Boolean encontrado=false;
					for(Mascota masco : mascotasTemp){			//recorro las mascotas de mascotasTemp
						if (masco.getIdMascota()==mascotaE.getIdMascota()){			
							encontrado=true;							//si coincide pongo la bandera en true
						}
					}
					if (!encontrado){							//si no coincidio, entonces no estaba en las mascotasTemp
						borrar.add(mascotaE);					//por lo que debe ser eliminada
					}
				}
				for(Mascota agregarMascota : agregar){						//PERSISTO LOS CAMBIOS EN LA BD
					ctrlMascota.agregarMascota(agregarMascota);
				}
				for(Mascota modificarMascota : modificar){
					ctrlMascota.modificarMascota(modificarMascota);
				}
				for(Mascota borrarMascota : borrar){
					ctrlMascota.eliminarMascota(borrarMascota);
				}
				
				usu.setMascotas(ctrlMascota.getMascotas(usu));
				if(usuarioOnline){
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", usu);
				}
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