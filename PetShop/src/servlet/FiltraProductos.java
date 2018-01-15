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
 * Servlet implementation class FiltraProductos
 */
@WebServlet({ "/FiltraProductos", "/filtraproductos", "/Filtraproductos", "/filtraProductos" })
public class FiltraProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltraProductos() {
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

		//Creo un diccionario con los parametros
		Hashtable<String, String> parametros = new Hashtable<String, String>();
		 
        //Completo los datos del diccionario
		parametros.put("idProducto", "");
		parametros.put("nombre", "");
        parametros.put("presentacion", "");
        parametros.put("precioDesde", "");
        parametros.put("precioHasta", "");
        parametros.put("stockDesde", "");
        parametros.put("stockHasta", "");
         
        /*
        if(!request.getParameter("idProducto").equals("null")){
        	parametros.put("idProducto", request.getParameter("idProducto"));
        }*/
        if(!request.getParameter("nombre").equals(null)){
        	parametros.put("nombre", request.getParameter("nombre"));
        }
        if(!request.getParameter("presentacion").equals(null)){
        	parametros.put("presentacion", request.getParameter("presentacion"));
        }
        if(!request.getParameter("precioDesde").equals(null)){
        	parametros.put("precioDesde", request.getParameter("precioDesde"));
        }
        if(!request.getParameter("precioHasta").equals(null)){
        	parametros.put("precioHasta", request.getParameter("precioHasta"));
        }
        if(!request.getParameter("stockDesde").equals(null)){
        	parametros.put("stockDesde", request.getParameter("stockDesde"));
        }
        if(!request.getParameter("stockHasta").equals(null)){
        	parametros.put("stockHasta", request.getParameter("stockHasta"));
        }
        System.out.println("el id del producto es:"+parametros.get("idProducto"));
        System.out.println(parametros.get("nombre"));
        System.out.println(parametros.get("presentacion"));
        System.out.println(parametros.get("precioDesde"));
        System.out.println(parametros.get("precioHasta"));
        System.out.println(parametros.get("stockDesde"));
        
        
        ArrayList<Producto> productos = new ArrayList<>();
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		try {
			productos = ctrlProducto.getProductos();
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
		response.getWriter().write(json);
		return;
	}

}
