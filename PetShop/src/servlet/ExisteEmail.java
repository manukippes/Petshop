package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import logica.ControladorDeUsuario;

/**
 * Servlet implementation class ExisteEmail
 */
@WebServlet("/ExisteEmail")
public class ExisteEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExisteEmail() {
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
		// TODO Auto-generated method stub
		String json = request.getParameter("jsonData");
		JsonObject cliente = (JsonObject) new JsonParser().parse(json);
		String email = (String) cliente.get("email").getAsString();
		
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		Boolean resultado = false;
		try {
			resultado = ctrlUsuario.validarEmail(email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultado){
			response.getWriter().println(1);
		}else {
			response.getWriter().println(0);
		}

	}

}
