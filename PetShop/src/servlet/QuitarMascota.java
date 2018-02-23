package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Mascota;
import entidades.Usuario;
import logica.ControladorDeTipoMascota;

/**
 * Servlet implementation class QuitarMascota
 */
@WebServlet("/QuitarMascota")
public class QuitarMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitarMascota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = request.getParameter("jsonData");
		JsonObject mascotaJSON = (JsonObject) new JsonParser().parse(json);
		
		String idMascota = (String) mascotaJSON.get("idMascota").getAsString();
		
		String nombre = (String) mascotaJSON.get("nombre").getAsString();
		String tamanio = (String) mascotaJSON.get("tamanio").getAsString();
				
		String pelaje = (String) mascotaJSON.get("pelaje").getAsString();
		String fechaNacimiento = (String) mascotaJSON.get("fechaNacimiento").getAsString();
		String observacion = (String) mascotaJSON.get("observacion").getAsString();
	
		
		ArrayList<Mascota> mascotasTemp = (ArrayList<Mascota>) request.getSession().getAttribute("mascotasTemp"); //arreglo temporal de mascotas
		
		if(idMascota.equals("0")){
			//elimino una mascota nueva no agregada a la bd
			int i=0;
			int encontrada = -1;
			for (Mascota mascota : mascotasTemp){
				if(mascota.getNombre().equals(nombre)&&mascota.getTipoMascota().getPelo().equals(pelaje)&&mascota.getTipoMascota().getTamanio().equals(tamanio)){
					encontrada = i;
					
				}
				i++;
			}
			if(encontrada!=-1){
				mascotasTemp.remove(encontrada);
				response.getWriter().println(1);
			}else{
				response.getWriter().println(0);
			}
			
		}else{
			//elimino una mascota que ya existe en la bd (tiene id de mascota)
			int i=0;
			int encontrada = -1;
			for (Mascota mascota : mascotasTemp){
				
				if(mascota.getIdMascota()==Integer.parseInt(idMascota)){
					encontrada = i;
				}
				i++;
			}
			if(encontrada!=-1){
				mascotasTemp.remove(encontrada);
				response.getWriter().println(1);
			}else{
				response.getWriter().println(0);
			}
		}
		
		request.getSession().removeAttribute("mascotasTemp");
		request.getSession().setAttribute("mascotasTemp", mascotasTemp);
		
		
	}

}
