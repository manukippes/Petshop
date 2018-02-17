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
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		boolean bandera = false;
		ArrayList<Mascota> mascotasUsuario = new ArrayList<Mascota>();
		
	
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
				usu = ctrlUsuario.agregarUsuario(usu);
				
				bandera = true;

				JsonArray mascotas = (JsonArray) cliente.get("arregloMascotas").getAsJsonArray();
				if(!(mascotas.isJsonNull()))
				{
					try {
						 for (JsonElement obj : mascotas) 
						 {						 
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
						 System.out.println(usuario);
						 if(!usuario.equals("")){
							 
							 usu.setMascotas(mascotasUsuario);
							request.getSession().setAttribute("user", usu);
						 }
					} 
					catch (Exception e) {
						response.getWriter().println(0);
						System.out.println("ERROR");
						e.printStackTrace();
					}
			}
			if(bandera){
				 response.getWriter().println(1);	
				
			 }else{
				 response.getWriter().println(2);
			 }
		}
		catch (MySQLIntegrityConstraintViolationException mailExistente) {
			response.getWriter().println(3);
			mailExistente.printStackTrace();
			
		}

		catch (Exception e1) {
		response.getWriter().println(0);
		e1.printStackTrace();
		}
		
		
		
	}
}
