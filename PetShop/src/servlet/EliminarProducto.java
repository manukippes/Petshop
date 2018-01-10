 package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Producto;
import logica.ControladorDeProducto;

/**
 * Servlet implementation class EliminarProducto
 */
@WebServlet({ "/EliminarProducto", "/eliminarproducto", "/eliminarProducto" })
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
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
        
    	response.setContentType("text/html;charset=UTF-8");
        
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        ControladorDeProducto ctrlProducto = new ControladorDeProducto();
        Producto prod = new Producto();
        prod.setIdProducto(idProducto);
        
        try{
        	if(ctrlProducto.eliminarProducto(prod)){
                response.getWriter().println("Producto eliminado exitosamente");
            }else{
                response.getWriter().println("ERROR al eliminar el producto");
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        
    }
}
