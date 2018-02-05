package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Venta;
import logica.ControladorDeVenta;

/**
 * Servlet implementation class ComprobantesVentasResultados
 */
@WebServlet("/ComprobantesVentasResultados")
public class ComprobantesVentasResultados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobantesVentasResultados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ControladorDeVenta ctrlVenta = new ControladorDeVenta();
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		
		//Creo un diccionario con los parametros
				Hashtable<String, String> parametros = new Hashtable<String, String>();
				 
        //Completo los datos del diccionario
		parametros.put("fechaDesde", "");
		parametros.put("fechaHasta", "");
        parametros.put("producto", "");
        parametros.put("importeDesde", "");
        parametros.put("medioPago", "");
        parametros.put("cliente", "");
        parametros.put("conRetiro", "");
        
        if(!request.getParameter("fechaDesde").equals(null)){
        	parametros.put("fechaDesde", request.getParameter("fechaDesde"));
        }
        if(!request.getParameter("fechaHasta").equals(null)){
        	parametros.put("fechaHasta", request.getParameter("fechaHasta"));
        }
        if(!request.getParameter("producto").equals(null)){
        	parametros.put("producto", request.getParameter("producto"));
        }
        if(!request.getParameter("importeDesde").equals(null)){
        	parametros.put("importeDesde", request.getParameter("importeDesde"));
        }
        if(!request.getParameter("medioPago").equals("medioPago")){
        	parametros.put("medioPago", request.getParameter("medioPago"));
        }
        if(!request.getParameter("cliente").equals(null)){
        	parametros.put("cliente", request.getParameter("cliente"));
        }
        
        if(!request.getParameter("conRetiro").equals("conRetiro")){
        	parametros.put("conRetiro", request.getParameter("conRetiro"));
        }
        
        try {
			ventas = ctrlVenta.getVentas(parametros);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		request.getSession().setAttribute("ventas", ventas);
		request.getRequestDispatcher("WEB-INF/ComprobantesVentasResultados.jsp").forward(request, response);
	
	}

}
