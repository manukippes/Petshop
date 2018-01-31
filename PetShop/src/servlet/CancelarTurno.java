package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Producto;
import entidades.Turno;
import logica.ControladorDeProducto;
import logica.ControladorDeTurno;

/**
 * Servlet implementation class CancelarTurno
 */
@WebServlet({ "/CancelarTurno", "/cancelarTurno", "/cancelarturno", "/Cancelarturno" })
public class CancelarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarTurno() {
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
response.setContentType("text/html;charset=UTF-8");
        
        int idTurno = Integer.parseInt(request.getParameter("idTurno"));
        ControladorDeTurno ctrlTurno = new ControladorDeTurno();
        Turno turno = new Turno();
        turno.setIdTurno(idTurno);
        
        try{
        	if(ctrlTurno.cancelarTurno(turno)){
                response.getWriter().println("Turno cancelado");
            }else{
                response.getWriter().println("ERROR al cancelar el turno");
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
	}

}
