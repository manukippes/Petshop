package servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Usuario;
import logica.ControladorDeUsuario;


@WebServlet({ "/PrimerIngreso", "/primerIngreso", "/primeringreso" })
public class PrimerIngreso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PrimerIngreso() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario=new Usuario();
		usuario = (Usuario) request.getSession().getAttribute("userOnline");
		request.getSession().setAttribute("user", usuario);
		ArrayList<ArrayList<String>> productosVenta = new ArrayList<ArrayList<String>>();
		request.getSession().setAttribute("productosVenta", productosVenta);
		request.getRequestDispatcher("WEB-INF/VentaOnline.jsp").forward(request, response);	
	}

}
