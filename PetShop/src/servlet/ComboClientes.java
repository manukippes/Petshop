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

import entidades.MedioPago;
import entidades.Tarjeta;
import entidades.Usuario;
import logica.ControladorDeUsuario;
import logica.ControladorDeVenta;

/**
 * Servlet implementation class FiltraClientes
 */
@WebServlet({ "/ComboClientes", "/comboClientes", "/Comboclientes", "/comboclientes" })
public class ComboClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComboClientes() {
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
		String inputCliente = request.getParameter("inputCliente");
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
					
		try {
			usuarios = ctrlUsuario.getUsuariosLike(inputCliente);    			//Consigo los clientes que se asemejan al input
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Usuario> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Usuario usuario : usuarios){
			lista.add(usuario);
		}
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
