package servlet;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import logica.ControladorDeTurno;

/**
 * Servlet implementation class ComboHorarios
 */
@WebServlet({ "/ComboHorarios", "/combohorarios", "/Combohorarios", "/comboHorarios" })
public class ComboHorarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComboHorarios() {
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
		String fechaSeleccionada = request.getParameter("fechaSeleccionada");
		
		ArrayList<Time> horariosDisponibles = new ArrayList<>();
		ControladorDeTurno ctrlTurno = new ControladorDeTurno();
		
		try {
			horariosDisponibles = ctrlTurno.getHorariosDisponibles(fechaSeleccionada);    			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Time> lista = new ArrayList<>();  //convierto el arraylist en list
		for(Time horario : horariosDisponibles){
			lista.add(horario);
		}
		String json = new Gson().toJson(lista);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


		//return;

	}

}
