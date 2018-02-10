package servlet;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Usuario;
import logica.ControladorDeUsuario;
import utilidades.Emailer;
import utilidades.RandomString;

/**
 * Servlet implementation class BlanquearUsuario
 */
@WebServlet({ "/BlanquearUsuario", "/blanquearUsuario", "/Blanquearusuario", "/blanquearusuario" })
public class BlanquearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlanquearUsuario() {
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
		
		
		//CONTROLADORES
		
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		
		
		
		String email = request.getParameter("email");
		
		/*ControladorTipoDeElemento ctrlTipo = new ControladorTipoDeElemento();
		try {
			ctrlTipo.crearTipoElemento(tipoEle);
			Logger logger = LogManager.getLogger(getClass());								//Agrego la transaccion al log de TRACE
			logger.log(Level.INFO,"Alta Tipo Elemento Exitosa. Nombre: "+tipoEle.getNombre());
			
		} 
		*/
		
		
		//GENERO UN USUARIO Y PASSWORD SEGUROS ALEATORIOS
		RandomString gen = new RandomString(8, ThreadLocalRandom.current());
		
		String usuario = gen.nextString().toUpperCase();
		String pass = gen.nextString().toUpperCase();
				
		//RECUPERO EL USUARIO CON EL MAIL INGRESADO
		try {
			if(ctrlUsuario.blanquearUsuario(email, usuario, pass)){

				String contenidoMail = ("Sistema de Gestion de Pet Shops:\n "
						+ 				"Se ha solicitado un blanqueo de usuario y contraseña\n"
						+ 				"El nuevo usuario es: "+usuario +"\n"
						+ 				"El nuevo password es: "+pass);
				Emailer.getInstance().send("tpfinaljava2017@gmail.com","Sistema de Gestion de Pet Shops - Blanqueo de Usuario",contenidoMail);
				response.getWriter().println(true);
				
			}else{
				
				response.getWriter().println(false);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
