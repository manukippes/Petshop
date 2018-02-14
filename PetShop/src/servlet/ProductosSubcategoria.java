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

import entidades.Producto;
import entidades.Subcategoria;
import entidades.Usuario;
import logica.ControladorDeProducto;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ProductosSubcategoria
 */
@WebServlet({ "/ProductosSubcategoria", "/productosSubcategoria", "/Productossubcategoria" })
public class ProductosSubcategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosSubcategoria() {
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
		String idSubcategoria = request.getParameter("idSubcategoria");
		Boolean soloStock = false;
		if(request.getParameter("soloStock").equals("true")){
			soloStock=true;
		};
		
		
		ArrayList<Producto> productos = new ArrayList<>();
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		
		Subcategoria subcat = new Subcategoria();
		subcat.setIdSubCategoria(Integer.parseInt(idSubcategoria));
				
		try {
			subcat = ctrlProducto.getSubcategoria(subcat);
			productos = ctrlProducto.getProductos(subcat,soloStock);    		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Producto> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Producto produ : productos){
			lista.add(produ);
		}
		String json = new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
