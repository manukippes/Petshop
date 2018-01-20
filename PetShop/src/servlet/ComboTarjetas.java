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

import entidades.Categoria;
import entidades.MedioPago;
import entidades.Subcategoria;
import entidades.Tarjeta;
import logica.ControladorDeProducto;
import logica.ControladorDeVenta;

/**
 * Servlet implementation class ComboTarjetas
 */
@WebServlet({ "/ComboTarjetas", "/comboTarjetas", "/combotarjetas", "/Combotarjetas" })
public class ComboTarjetas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComboTarjetas() {
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
		String idMedioPago = request.getParameter("idMedioPago");
		
		ArrayList<Tarjeta> tarjetas = new ArrayList<>();
		ControladorDeVenta ctrlVenta = new ControladorDeVenta();
		
		MedioPago medioPago = new MedioPago();   						//Creo una instancia de medio de pago
		medioPago.setIdMedioPago(Integer.parseInt(idMedioPago));
			
		try {
			tarjetas = ctrlVenta.getTarjetas(medioPago);    			//Consigo las tarjetas del tipo de medio de pago (debito/credito)
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Tarjeta> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Tarjeta tarjeta : tarjetas){
			lista.add(tarjeta);
		}
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
