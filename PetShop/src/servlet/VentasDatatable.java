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

import entidades.LineaVenta;
import entidades.Producto;
import entidades.Venta;

/**
 * Servlet implementation class VentasDatatable
 */
@WebServlet({ "/VentasDatatable", "/ventasDatatable", "/Ventasdatatable" })
public class VentasDatatable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentasDatatable() {
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
		
		
		ArrayList<Venta> ventas = (ArrayList<Venta>)request.getSession().getAttribute("ventas");
		ArrayList<LineaVenta> lv = new ArrayList<>();
		
		
		List<Venta> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Venta venta : ventas){
			venta.setLineas(lv);
			lista.add(venta);
		}
		String json = new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(json);
		response.getWriter().write(json);
	}

}
