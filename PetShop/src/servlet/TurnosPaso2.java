package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Cuotas;
import entidades.LineaVenta;
import entidades.Mascota;
import entidades.Producto;
import entidades.Servicio;
import entidades.Tarjeta;
import entidades.TipoMascotaServicio;
import entidades.Turno;
import logica.ControladorDeMascota;
import logica.ControladorDeServicio;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class TurnosPaso2
 */
@WebServlet({ "/TurnosPaso2", "/turnospaso2", "/Turnospaso2", "/turnosPaso2" })
public class TurnosPaso2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurnosPaso2() {
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
		
		request.getRequestDispatcher("WEB-INF/TurnosPaso2.jsp").forward(request, response);
		
	}

}
