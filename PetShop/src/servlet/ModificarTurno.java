package servlet;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Mascota;
import entidades.Producto;
import entidades.Turno;
import logica.ControladorDeMascota;
import logica.ControladorDeProducto;
import logica.ControladorDeTurno;

/**
 * Servlet implementation class ModificarTurno
 */
@WebServlet({ "/ModificarTurno", "/modificarTurno", "/Modificarturno", "/modificarturno" })
public class ModificarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarTurno() {
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
		
		String idTurno = request.getParameter("id");
    	
    	ControladorDeTurno ctrlTurno = new ControladorDeTurno();
    	ControladorDeMascota ctrlMascota = new ControladorDeMascota();
    	ArrayList<Mascota> mascotasUsuario = new ArrayList<Mascota>();
    	ArrayList<Time> horarios = new ArrayList<Time>();
    	Turno turnoActual = new Turno();
    	
    	
    	turnoActual.setIdTurno(Integer.parseInt(idTurno));
    	
    	try {
			turnoActual = ctrlTurno.getTurno(turnoActual);	
			mascotasUsuario = ctrlMascota.getMascotas(turnoActual.getMascota().getUsuario());
			horarios=ctrlTurno.getHorariosDisponibles(turnoActual.getFecha().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("horarios", horarios);
    	request.setAttribute("mascotasUsuario", mascotasUsuario);
    	request.getSession().setAttribute("turno", turnoActual);    	
    	request.getRequestDispatcher("WEB-INF/ModificarTurno.jsp").forward(request, response);
    	
    }

}
