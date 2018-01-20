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

import entidades.Cuotas;
import entidades.MedioPago;
import entidades.Tarjeta;
import logica.ControladorDeVenta;

/**
 * Servlet implementation class ComboCuotas
 */
@WebServlet({ "/ComboCuotas", "/comboCuotas", "/Combocuotas", "/combocuotas" })
public class ComboCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComboCuotas() {
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
		String idTarjeta = request.getParameter("idTarjeta");
		
		ArrayList<Cuotas> cuotasTarjeta = new ArrayList<>();
		ControladorDeVenta ctrlVenta = new ControladorDeVenta();
		
		Tarjeta tarjeta = new Tarjeta();   						//Creo una instancia de tarjeta
		tarjeta.setIdTarjeta(Integer.parseInt(idTarjeta));
			
						
		try {
			tarjeta = ctrlVenta.getTarjeta(tarjeta);					//COMPLETO LOS DATOS DE LA TARJETA
			cuotasTarjeta = ctrlVenta.getCuotas(tarjeta);    			//Consigo las cuotas disponibles para esta tarjeta
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Cuotas> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Cuotas cuota : cuotasTarjeta){
			lista.add(cuota);
		}
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
