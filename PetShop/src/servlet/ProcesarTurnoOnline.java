package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Turno;
import logica.ControladorDeTurno;

/**
 * Servlet implementation class ProcesarTurnoOnline
 */
@WebServlet("/ProcesarTurnoOnline")
public class ProcesarTurnoOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarTurnoOnline() {
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
		
		//ALTA DE TURNO
		Turno turnoActual = (Turno) request.getSession().getAttribute("turnoActual");
		
					
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
		
	}


}
