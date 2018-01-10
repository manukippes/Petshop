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
 * Servlet implementation class ModificarProducto
 */
@WebServlet({ "/ModificarProducto", "/modificarProducto", "/modificarproducto" })
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProducto() {
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
    	response.getWriter().println("HOLA");
    	//request.getRequestDispatcher("WEB-INF/Principal.jsp").forward(request, response);
    	/*
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
     */   
    }

}
