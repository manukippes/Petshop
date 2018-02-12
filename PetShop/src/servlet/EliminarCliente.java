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
 * Servlet implementation class EliminarProducto
 */
@WebServlet({ "/EliminarCliente", "/eliminarcliente", "/eliminarCliente" })
public class EliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		String json = request.getParameter("jsonData");
		JsonObject cliente = (JsonObject) new JsonParser().parse(json);
		int idCliente = (int) cliente.get("idCliente").getAsInt();
		
        ControladorDeUsuario ctrlCliente = new ControladorDeUsuario();
        Usuario cli = new Usuario();
        cli.setIdUsuario(idCliente);
        
        try{
        	if(ctrlCliente.eliminarUsuario(cli)){
                response.getWriter().println("Cliente eliminado exitosamente");
            }else{
                response.getWriter().println("ERROR al eliminar el cliente");
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        
    }
}
