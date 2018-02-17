package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Mascota;
import entidades.Turno;
import entidades.Usuario;
import logica.ControladorDeTurno;

/**
 * Servlet implementation class FiltraTurnos
 */
@WebServlet({ "/FiltraTurnos", "/filtraTurnos", "/Filtraturnos", "/filtraturnos" })
public class FiltraTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltraTurnos() {
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
		//Creo un diccionario con los parametros
				Hashtable<String, String> parametros = new Hashtable<String, String>();
				 
		        //Completo los datos del diccionario
				parametros.put("fechaDesde", "");
				parametros.put("fechaHasta", "");
		        parametros.put("estado", "");
		        parametros.put("apellido", "");
		        parametros.put("mascota", "");
		        parametros.put("direccion", "");
		        parametros.put("conTransporte", "");
		        parametros.put("idServicio", "");
		                 
		        
		        if(!request.getParameter("fechaDesde").equals("")){
		        	parametros.put("fechaDesde", request.getParameter("fechaDesde"));
		        }
		        if(!request.getParameter("fechaHasta").equals("")){
		        	parametros.put("fechaHasta", request.getParameter("fechaHasta"));
		        }
		        if(!request.getParameter("estado").equals("filtrar")){
		        	parametros.put("estado", request.getParameter("estado"));
		        }
		        if(!request.getParameter("apellido").equals("")){
		        	parametros.put("apellido", request.getParameter("apellido"));
		        }
		        if(!request.getParameter("mascota").equals("")){
		        	parametros.put("mascota", request.getParameter("mascota"));
		        }
		        if(!request.getParameter("direccion").equals("")){
		        	parametros.put("direccion", request.getParameter("direccion"));
		        }
		        if(!request.getParameter("conTransporte").equals("filtrar")){
		        	parametros.put("conTransporte", request.getParameter("conTransporte"));
		        }
		        if(!request.getParameter("idServicio").equals("filtrar")){
		        	parametros.put("idServicio", request.getParameter("idServicio"));
		        }
		    
		        ArrayList<Turno> turnos = new ArrayList<>();
				ControladorDeTurno ctrlTurno = new ControladorDeTurno();
				try {
					turnos = ctrlTurno.getTurnos(parametros);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				List<Turno> lista = new ArrayList<>();  //convierto el arraylist en list
				for(Turno turno : turnos){
					
					Mascota mascota = turno.getMascota();
					Usuario usuario = mascota.getUsuario();
					usuario.setMascotas(new ArrayList<Mascota>());
					mascota.setUsuario(usuario);
					turno.setMascota(mascota);
					lista.add(turno);
				}
				String json = new Gson().toJson(lista);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				return;
			}
		

}
