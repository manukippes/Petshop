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
import entidades.TipoMascota;
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
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
				
		String json = request.getParameter("jsonData");
		JsonArray cliente = (JsonArray) new JsonParser().parse(json);
				
		String nombre = ((JsonObject) cliente.get(0)).get("nombre").getAsString();
		String apellido = ((JsonObject) cliente.get(1)).get("apellido").getAsString();
		int dni = ((JsonObject) cliente.get(2)).get("dni").getAsInt();
		String direccion = ((JsonObject) cliente.get(3)).get("direccion").getAsString();
		int telefono = ((JsonObject) cliente.get(4)).get("telefono").getAsInt();
		String email = ((JsonObject) cliente.get(5)).get("email").getAsString();
		
		usu.setNombre(nombre);
		usu.setApellido(apellido);
		usu.setDni(dni);
		usu.setDireccion(direccion);
		usu.setTelefono(telefono);
		usu.setEmail(email);
		
		int idUsuario;
		try {
			idUsuario = ctrlUsuario.agregarUsuario(usu);
			JsonArray mascotas = ((JsonObject) cliente.get(6)).get("arregloMascotas").getAsJsonArray();
			
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
				TipoMascota tipoMas = new TipoMascota();
				masco.setTipoMascota(ctrlTipoMascota.getTipoMascotaSegunTamanioPelaje(tamanioMasco, pelajeMasco));
				masco.setUsuario(usu);
				ctrlMascota.agregarMascota(masco);
			}
			response.getWriter().println(true);	
		} catch (Exception e) {
			response.getWriter().println(false);
			e.printStackTrace();
		}
		
		//LOS AGREGO A UN ARREGLO DE PRODUCTOS DE LA VENTA
		/*for (int i=0;i<productos.size();i++){
			String idProducto = ((JsonObject) productos.get(i)).get("idProducto").getAsString();
			String cantidad = ((JsonObject) productos.get(i)).get("cantidad").getAsString();
			ArrayList<String> prodCant = new ArrayList<>();//CREO UN ELEMENTO {IDPRODUCTO,CANTIDAD}
			prodCant.add(idProducto);
			prodCant.add(cantidad);
			productosVenta.add(prodCant);//AGREGO EL ELEMENTO A LA VENTA
		}
		request.getSession().setAttribute("productosVenta", productosVenta);*/
	}

}
