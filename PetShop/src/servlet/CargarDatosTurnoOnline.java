package servlet;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Mascota;
import entidades.Servicio;
import entidades.Turno;
import logica.ControladorDeMascota;
import logica.ControladorDeServicio;
import logica.ControladorDeTurno;

/**
 * Servlet implementation class CargarDatosTurnoOnline
 */
@WebServlet("/CargarDatosTurnoOnline")
public class CargarDatosTurnoOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatosTurnoOnline() {
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
				//INICIALIZACIONES NECESARIAS PARA EL METODO
				
				ControladorDeMascota ctrlMascota = new ControladorDeMascota();					//CONTROLADOR
				ControladorDeServicio ctrlServicio = new ControladorDeServicio();				//CONTROLADOR
				Turno turnoActual = new Turno();												//TURNO NUEVO A CARGAR
				
				
				//OBTENER LO PARAMETROS EN JSON Y PARSEARLOS
				String json = request.getParameter("jsonData");
				
				JsonObject campos = (JsonObject) new JsonParser().parse(json);

			
				int idServicio = (int) campos.get("servicio").getAsInt();
				String fecha = (String) campos.get("fecha").getAsString();
				String horario = (String) campos.get("horario").getAsString();
				String conRepeticion = (String) campos.get("repeticion").getAsString();
				int idMascota = (int) campos.get("idMascota").getAsInt();
				Boolean conRetiro = (Boolean) campos.get("conRetiro").getAsBoolean();
				String observaciones = (String) campos.get("observaciones").getAsString();
				try {
					
					
					//CREO LA MASCOTA
					
					Mascota mascota = new Mascota();									//MASCOTA
					mascota.setIdMascota(idMascota);
					mascota = ctrlMascota.getMascota(mascota);
					turnoActual.setMascota(mascota);			
					
					Servicio servicio = new Servicio();									//SERVICIO
					servicio.setIdServicio(idServicio);
					servicio = ctrlServicio.getServicio(servicio);
					turnoActual.setServicio(servicio);
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");			//FECHA DEL TURNO
					Date fechaDate = df.parse(fecha);
					java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
					turnoActual.setFecha(sqlDate);
					
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");			//HORA DEL TURNO
					long ms = sdf.parse(horario).getTime();
					Time horarioActual = new Time(ms);
					turnoActual.setHora(horarioActual);									
					
					turnoActual.setRepetir(conRepeticion); 								//REPETICION
								
					turnoActual.setRetiroDom(conRetiro);								//CON RETIRO A DOMICILIO
					
					turnoActual.setEstado("Pendiente"); 								//ESTADO
					
					turnoActual.setObservaciones(observaciones); 									//OBSERVACIONES			
										
					request.getSession().setAttribute("turnoActual", turnoActual);
					request.getSession().removeAttribute("turnoPendiente");
					request.getSession().setAttribute("turnoPendiente", true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
}