package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Mascota;
import entidades.Usuario;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ObtenerMascotasTemp
 */
@WebServlet("/ObtenerMascotasTemp")
public class ObtenerMascotasTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerMascotasTemp() {
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
		
		
		ArrayList<Mascota> mascotasTemp = (ArrayList<Mascota>) request.getSession().getAttribute("mascotasTemp");
		List<Mascota> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Mascota mascota : mascotasTemp){
			mascota.setUsuario(new Usuario());
			lista.add(mascota);
		}
		
		String json = new Gson().toJson(lista);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
}