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
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ObtenerCliente
 */
@WebServlet({ "/ObtenerCliente", "/obtenerCliente", "/Obtenercliente", "/obtenercliente" })
public class ObtenerCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerCliente() {
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
		String idUsuario = request.getParameter("idCliente");
		Usuario usuarioActual = new Usuario();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		usuarioActual.setIdUsuario(Integer.parseInt(idUsuario));
		ArrayList<Usuario> usuarios = new ArrayList<>();						//PASO EL UNICO USUARIO COMO UN ARRAYLIST POR REUTILIZAR CODIGO
					
		try {
			usuarioActual = ctrlUsuario.getUsuario(usuarioActual);    			//Consigo los datos del cliente

			usuarios.add(usuarioActual);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Usuario> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Usuario usuario : usuarios){
			usuario.setMascotas(new ArrayList<Mascota>());
			lista.add(usuario);
		}
		
		String json = new Gson().toJson(lista);
		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
