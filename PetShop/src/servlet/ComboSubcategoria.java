package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Categoria;
import entidades.Subcategoria;
import logica.ControladorDeProducto;

import com.google.gson.Gson;

/**
 * Servlet implementation class subcategoria
 */
@WebServlet({ "/Combosubcategoria", "/ComboSubcategoria" })
public class ComboSubcategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComboSubcategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String idCategoria = request.getParameter("idCategoria");
		
		ArrayList<Subcategoria> subcategorias = new ArrayList<>();
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		
		Categoria cate = new Categoria();   						 	//Creo una instancia de Categoria
		cate.setIdCategoria(Integer.parseInt(idCategoria));				//Seteo el Id de la categoria
			
		try {
			subcategorias = ctrlProducto.getSubcategorias(cate);    	//Consigo las subcategorias de la categoria
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Subcategoria> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Subcategoria subcat : subcategorias){
			lista.add(subcat);
		}
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;
	}

}
