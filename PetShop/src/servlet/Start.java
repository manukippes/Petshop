package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidades.Usuario;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class start
 */
@WebServlet({ "/start", "/Start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String user=request.getParameter("username"); //recupero user
		String pass=request.getParameter("password");	//recupero pass (todos los parametros vienen como string)
		
		try {

			System.out.println("Eu usuario ingresado es"+user);
			System.out.println("Eu pass ingresado es"+pass);
			Usuario usuario=new Usuario();
			usuario.setUsuarioLogin(user);
			usuario.setPassword(pass);
			
			ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
			usuario = ctrlUsuario.obtenerUsuario(usuario);
			
			if(usuario.getEstado()==1){
				request.getSession().setAttribute("user", usuario); //crea o recupera una sesion si ya esta creada	
				request.getSession().setAttribute("turnoPendiente", false);
				switch (usuario.getTipoUsuario()){
				
					case "Administrador":
						response.getWriter().println(1);
						break;
						
					case "Online":
						response.getWriter().println(2);
						break;
				}								
			}
			else {
				if(usuario.getNombre().equals("null")){
					response.getWriter().println(3);
				}else{
					response.getWriter().println(4);
				}
			}			
		} catch (Exception e) {
			response.getWriter().println(3);
		}
		return;
	}	
}
