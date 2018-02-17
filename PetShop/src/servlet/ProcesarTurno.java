package servlet;

import java.io.IOException;
import java.sql.Time;
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
import entidades.MedioPago;
import entidades.Producto;
import entidades.Servicio;
import entidades.Tarjeta;
import entidades.TipoMascotaServicio;
import entidades.Turno;
import entidades.Usuario;
import entidades.Venta;
import logica.ControladorDeMascota;
import logica.ControladorDeProducto;
import logica.ControladorDeServicio;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeTurno;
import logica.ControladorDeUsuario;
import logica.ControladorDeVenta;

/**
 * Servlet implementation class ProcesarTurno
 */
@WebServlet({ "/ProcesarTurno", "/procesarTurno", "/Procesarturno", "/procesarturno" })
public class ProcesarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarTurno() {
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
			
		// CONTROLADORES
		
		ControladorDeTurno ctrlTurno = new ControladorDeTurno();
		
		//PARAMETROS JSON
		String json = request.getParameter("jsonData");
		
		JsonObject campos = (JsonObject) new JsonParser().parse(json);

		String observaciones = (String) campos.get("observaciones").getAsString();
		String proceso = (String) campos.get("proceso").getAsString();
		int resp = 0;
		
		//ALTA DE TURNO
		Turno turnoActual = (Turno) request.getSession().getAttribute("turnoActual");
		
		if(proceso.equals("alta")){
			
			turnoActual.setObservaciones(observaciones);								//OBSERVACIONES
			
			try {
				if(ctrlTurno.agregarTurno(turnoActual)){
					request.getSession().removeAttribute("turnoActual");
					request.getSession().removeAttribute("turnoPendiente");
					request.getSession().setAttribute("turnoPendiente", false);
					response.getWriter().println(true);
				}else{
					response.getWriter().println(false);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}else{
			try {
				if(ctrlTurno.modificarTurno(turnoActual)){
					request.getSession().removeAttribute("turnoActual");
					request.getSession().removeAttribute("turnoPendiente");
					request.getSession().setAttribute("turnoPendiente", false);
					resp = 1;
					response.getWriter().println(resp);
				}else{
					response.getWriter().println(resp);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
