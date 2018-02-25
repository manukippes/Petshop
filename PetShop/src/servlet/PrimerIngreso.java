package servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		ArrayList<ArrayList<String>> productosVenta = new ArrayList<ArrayList<String>>();
		request.getSession().setAttribute("productosVenta", productosVenta);
		request.getRequestDispatcher("WEB-INF/VentaOnline.jsp").forward(request, response);	
		

	}

}
