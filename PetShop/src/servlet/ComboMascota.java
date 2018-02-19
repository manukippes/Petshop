package servlet;

import java.io.IOException;
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
import logica.ControladorDeMascota;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ComboMascota
 */
@WebServlet({ "/ComboMascota", "/comboMascota", "/combomascota", "/Combomascota" })
public class ComboMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComboMascota() {
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
		
		String idUsuario = request.getParameter("idUsuario");
		ArrayList<Mascota> mascotas = new ArrayList<>();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();
		
		Usuario cliente = new Usuario();
		cliente.setIdUsuario(Integer.parseInt(idUsuario));
		
		
		try {
			cliente = ctrlUsuario.getUsuario(cliente);
			mascotas = ctrlMascota.getMascotas(cliente);    			//CONSIGO LAS MASCOTAS DEL CLIENTE SELECCIONADO
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Mascota> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Mascota mascota : mascotas){
			mascota.setUsuario(new Usuario());		//LE BORRO EL USUARIO PORQUE TIENE DENTRO UN LISTADO DE MASCOTAS
			lista.add(mascota);
		}
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
