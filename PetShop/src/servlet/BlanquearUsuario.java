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
		
		//CONTROLADORES
		
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		
		String email = request.getParameter("email");

		//GENERO UN USUARIO Y PASSWORD SEGUROS ALEATORIOS
		RandomString gen = new RandomString(8, ThreadLocalRandom.current());
		
		String usuario = gen.nextString().toUpperCase();
		String pass = gen.nextString().toUpperCase();
				
		//RECUPERO EL USUARIO CON EL MAIL INGRESADO
		try {
			if(ctrlUsuario.blanquearUsuario(email, usuario, pass)){

				String contenidoMail = (""
						+ "				<table cellspacing='0' cellpadding='0' style='width:100%; border:none;'>"
						+ "					<tr style='	padding:15px;"
						+ "								background-color:#adbcf1;"
						+ "								color:#ffffff;"
						+ "								border:none;"
						+ "								height:50px;'>"
						+ "						<th style='width:33%'></th>"
						+ "						<th  style='width:34%'><h3><strong>Sistema de Gestion de Pet Shops:</strong></h3></th>"
						+ "						<th style='width:33%'></th>"
						+ "					</tr>"
						+ "					<tr style='background-color: #F5F5F5;height:15px;'>"
						+ "						<td></td>"
						+ "						<td></td>"
						+ "						<td></td>"					
						+ "					</tr>"
						+ "					<tr style='background-color: #F5F5F5;'>"
						+ "						<td style='padding-left:15px;'>"
						+ "							<h4>Se ha solicitado un blanqueo de usuario y contraseña</h4>"
						+ "						</td>"
						+ "						<td> </td>"
						+ "						<td> </td>"
						+ "					</tr>"
						+ "					<tr style='background-color: #F5F5F5;'>"
						+ "						<td style='padding-left:15px;'>"						
						+ "							<p>El nuevo usuario es: <strong>"+usuario+"</strong></p>"
						+ "						</td>"
						+ "						<td> </td>"		
						+ "						<td> </td>"
						+ "					</tr>"
						+ "					<tr style='background-color: #F5F5F5;'>"
						+ "						<td style='padding-left:15px;'>"						
						+ "							<p>El nuevo password es: <strong>"+pass+"</strong></p>"
						+ "						</td>"
						+ "						<td> </td>"	
						+ "						<td> </td>"
						+ "					</tr>"
						+ "				<table>"
						+ "");
				
				if (Emailer.getInstance().send(email,"Sistema de Gestion de Pet Shops - Blanqueo de Usuario",contenidoMail)) {
					response.getWriter().println(true);
				} else {
					response.getWriter().println(false);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
