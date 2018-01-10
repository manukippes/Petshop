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
    	
    	String idProducto = request.getParameter("id");
    	
    	ControladorDeProducto ctrlProducto = new ControladorDeProducto();
    	Producto productoActual = new Producto();
    	
    	productoActual.setIdProducto(Integer.parseInt(idProducto));
    	
    	try {
			productoActual = ctrlProducto.getProducto(productoActual);			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.getSession().setAttribute("producto", productoActual);    	
    	request.getRequestDispatcher("WEB-INF/ModificarProducto.jsp").forward(request, response);
    	
    }

}
