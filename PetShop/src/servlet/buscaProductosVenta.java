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
import logica.ControladorDeProducto;


/**
 * Servlet implementation class buscaProductosVenta
 */
@WebServlet({ "/buscaProductosVenta", "/BuscarProductosVenta", "/buscaproductosventa" })
public class buscaProductosVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscaProductosVenta() {
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

		String inputProducto = request.getParameter("inputProducto");
		
        ArrayList<Producto> productos = new ArrayList<>();
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		try {
			productos = ctrlProducto.getProductos(inputProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Producto> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Producto producto : productos){
			lista.add(producto);
		}
				
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(json);
		response.getWriter().write(json);
		return;
	}

}
