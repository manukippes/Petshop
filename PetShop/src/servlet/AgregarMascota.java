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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Mascota;
import entidades.Usuario;
import logica.ControladorDeMascota;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class AgregarMascota
 */
@WebServlet("/AgregarMascota")
public class AgregarMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarMascota() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		
		String json = request.getParameter("jsonData");
		JsonObject mascotaJSON = (JsonObject) new JsonParser().parse(json);
		
		String accion = (String) mascotaJSON.get("accion").getAsString();
		String idMascota = (String) mascotaJSON.get("idMascota").getAsString();
		
		String nombre = (String) mascotaJSON.get("nombre").getAsString();
		String tamanio = (String) mascotaJSON.get("tamanio").getAsString();
				
		String pelaje = (String) mascotaJSON.get("pelaje").getAsString();
		String fechaNacimiento = (String) mascotaJSON.get("fechaNacimiento").getAsString();
		String observacion = (String) mascotaJSON.get("observacion").getAsString();
		
		ArrayList<Mascota> mascotasTemp = (ArrayList<Mascota>) request.getSession().getAttribute("mascotasTemp"); //arreglo temporal de mascotas
		
		Mascota mascotaActual = new Mascota();
						
		mascotaActual.setNombre(nombre);																		//NOMBRE
		mascotaActual.setObservaciones(observacion);															//OBSERVACIONES
		try {
			mascotaActual.setTipoMascota(ctrlTipoMascota.getTipoMascotaSegunTamanioPelaje(tamanio, pelaje));	//TIPOMASCOTA
			
			SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");			
			Date fechaDate = fecha.parse(fechaNacimiento);
			java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
			mascotaActual.setFechaNacimiento(sqlDate);															//FECHA NACIMIENTO
			mascotaActual.setEstado(1); 																		//ESTADO
			
		//hay dos acciones que llegan a este servlet: alta y modificacion
		
		switch (accion){
			case "alta":
				//la mascota no tiene idUsuario ni idMascota
				mascotasTemp.add(mascotaActual);
				request.getSession().removeAttribute("mascotasTemp");
				request.getSession().setAttribute("mascotasTemp", mascotasTemp);
				response.getWriter().println(1);
				
				break;
			case "modificacion":
				//La primera vez me fijo si hay mascotas en el usuario y las agrego al mascotasTemp
				if (mascotasTemp.size()==0){
					Usuario user = (Usuario) request.getSession().getAttribute("user");
					if (user.getTipoUsuario().equals("Administrador")){
						user = (Usuario) request.getSession().getAttribute("cliente");
					}
					
					ArrayList<Mascota> mascotas = user.getMascotas();
					for(Mascota masco : mascotas){
						masco.setUsuario(new Usuario());
					};
					mascotasTemp = mascotas;
				}
				//Luego verifico si la mascota a agregar esta, si es asi la modifico y sino la agrego
				
				
				if(idMascota.equals("0")){
					//es una mascota nueva, la busco por sus datos
					int i=0;
					int encontrada=-1;
					String nombreAModificar = (String) mascotaJSON.get("nombreAModificar").getAsString();
					String pelajeAModificar = (String) mascotaJSON.get("pelajeAModificar").getAsString();
					String tamanioAModificar = (String) mascotaJSON.get("tamanioAModificar").getAsString();
					
					for (Mascota mascota : mascotasTemp){
						
						if(mascota.getNombre().equals(nombreAModificar)&&mascota.getTipoMascota().getPelo().equals(pelajeAModificar)&&mascota.getTipoMascota().getTamanio().equals(tamanioAModificar)){
							encontrada = i;
						}
						i++;
					}
					if(encontrada!=-1){
						
						mascotasTemp.remove(encontrada);
						mascotasTemp.add(mascotaActual);
						request.getSession().removeAttribute("mascotasTemp");
						request.getSession().setAttribute("mascotasTemp", mascotasTemp);
						response.getWriter().println(2);
						
					}else{
						//mascota nueva no encontrada en el arreglo temporal de mascotas
						mascotasTemp.add(mascotaActual);
						request.getSession().removeAttribute("mascotasTemp");
						request.getSession().setAttribute("mascotasTemp", mascotasTemp);
						response.getWriter().println(1);
						
					}
				} else {
					//tiene id por lo que es una mascota que ya estaba en la bd
					mascotaActual.setIdMascota(Integer.parseInt(idMascota));
					int i=0;
					int encontrada=-1;
					for (Mascota mascota : mascotasTemp){
						if(mascota.getIdMascota()==mascotaActual.getIdMascota()){
							encontrada = i;
						}
						i++;
					}
					if(encontrada!=-1){
						mascotasTemp.remove(encontrada);
						mascotasTemp.add(mascotaActual);
						request.getSession().removeAttribute("mascotasTemp");
						request.getSession().setAttribute("mascotasTemp", mascotasTemp);
						response.getWriter().println(2);
					}else{
						System.out.println("mascota existente no encontrada");
					}
				}
				break;
		}
		
	
			
		} catch (Exception e) {
			response.getWriter().println(0);
			e.printStackTrace();
			System.out.println("Hubo algun error");//hubo algun error
		}
		
	}

}
