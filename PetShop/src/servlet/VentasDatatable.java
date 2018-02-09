package servlet;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entidades.LineaVenta;
import entidades.Venta;

/**
 * Servlet implementation class VentasDatatable
 */
@WebServlet({ "/VentasDatatable", "/ventasDatatable", "/Ventasdatatable" })
public class VentasDatatable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public class VentaJson implements Serializable{
		private Venta ventaList;
		private List<LineaVenta> lineasList;
		
		public void setVenta(Venta venta) {
			ArrayList<LineaVenta> lineaVentaNull = new ArrayList<LineaVenta>();
			venta.setLineas(null);
			this.ventaList = venta;
		}
		public void setLineas(List<LineaVenta> lv){

			this.lineasList = lv;
		}
		
	};
       
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
				
		
		List<VentaJson> lista = new ArrayList<>();
		for(Venta venta : ventas){	
			
			List<LineaVenta> liven = new ArrayList<>();
			for (LineaVenta lventa : venta.getLineas()){
				liven.add(lventa);
			}
			
			VentaJson vjson = new VentaJson();
			
			
			
			vjson.setVenta(venta);
			vjson.setLineas(liven);
			
			lista.add(vjson);
			
			
		}
		
		//Type ventaType = new VentaJson().getClass();
		
		String json = new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(json);
		response.getWriter().write(json);
	}

}
