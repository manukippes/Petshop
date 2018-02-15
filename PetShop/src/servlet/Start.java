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
		 
				String user=request.getParameter("Username"); //recupero user
				String pass=request.getParameter("Password");	//recupero pass (todos los parametros vienen como string)
				
				try {

					
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
							request.getRequestDispatcher("WEB-INF/Principal.jsp").forward(request, response);
							break;
							
						case "Online":
							request.getRequestDispatcher("WEB-INF/VentaOnline.jsp").forward(request, response);
							break;
						}
													
					}
					else {
						if(usuario.getNombre().equals("null")){
							request.getSession().setAttribute("mensaje", "Error, Usuario o contraseña incorrectos");
							request.getSession().setAttribute("userError", user);
							request.getSession().setAttribute("passError", pass);
							request.getRequestDispatcher("WEB-INF/LoginFail.jsp").forward(request, response);
						}else{
							request.getSession().setAttribute("userError", user);
							request.getSession().setAttribute("passError", pass);
							request.getSession().setAttribute("mensaje", "El usuario ingresado se encuentra deshabilitado, por favor póngase en contacto con un administrador");
							request.getRequestDispatcher("WEB-INF/LoginFail.jsp").forward(request, response);
						}
					}
								
				} catch (Exception e) {
					request.getSession().setAttribute("error", "Error");
					request.getSession().setAttribute("userError", user);
					request.getSession().setAttribute("passError", pass);
					request.getSession().setAttribute("mensaje", "Usuario o contraseña incorrectos, intentá nuevamente");
					request.getRequestDispatcher("WEB-INF/LoginFail.jsp").forward(request, response);
				}
				return;
			}	
}
