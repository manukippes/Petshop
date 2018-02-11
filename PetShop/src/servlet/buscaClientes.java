package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Producto;
import entidades.Subcategoria;
import entidades.Usuario;
import logica.ControladorDeProducto;
import logica.ControladorDeUsuario;


/**
 * Servlet implementation class buscaProductosVenta
 */
@WebServlet({ "/buscaClientes", "/BuscarClientes", "/buscaclientes" })
public class buscaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscaClientes() {
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

		String buscarCliente = request.getParameter("inputCliente");
		
        ArrayList<Usuario> clientes = new ArrayList<>();
		ControladorDeUsuario ctrlClientes = new ControladorDeUsuario();
		
		try {
			clientes = ctrlClientes.getUsuariosLike(buscarCliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Usuario> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Usuario usu : clientes){
			lista.add(usu);
		}
				
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(json);
		return;
	}

}
