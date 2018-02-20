package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuitarDelCarrito
 */
@WebServlet("/QuitarDelCarrito")
public class QuitarDelCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitarDelCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	

		ArrayList<ArrayList<String>> productosVenta = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("productosVenta");
		
		int i=0;
		int encontrado=-1;
		String idProducto = (String) request.getParameter("idProducto");
	
		for(ArrayList<String> prodcante : productosVenta){	 
			if(prodcante.get(0).equals(idProducto)){
				encontrado = i;
			}
			i++;
		}
		if(encontrado!=-1){
			productosVenta.remove(encontrado);
		}

		request.getSession().removeAttribute("productosVenta");
		request.getSession().setAttribute("productosVenta", productosVenta);
	}

}
