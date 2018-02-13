package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Producto;
import entidades.Usuario;
import logica.ControladorDeProducto;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet({ "/ModificarCliente", "/modificarCliente", "/modificarcliente" })
public class ModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    	//request.getRequestDispatcher("WEB-INF/Principal.jsp").forward(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String json = request.getParameter("jsonData");
		JsonObject cliente = (JsonObject) new JsonParser().parse(json);
		int idCliente = (int) cliente.get("idCliente").getAsInt();
    	
    	ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
    	Usuario clienteActual = new Usuario();
    	
    	clienteActual.setIdUsuario(idCliente);
    	
    	try {
			clienteActual = ctrlUsuario.getUsuario(clienteActual);	
			response.getWriter().println(true);
		} catch (Exception e) {
			response.getWriter().println(false);	
			e.printStackTrace();
		}
    	request.getSession().setAttribute("cliente", clienteActual);
    }

}
