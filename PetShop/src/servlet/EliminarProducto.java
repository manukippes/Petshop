 package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Producto;
import logica.ControladorDeProducto;

/**
 * Servlet implementation class EliminarProducto
 */
@WebServlet({ "/EliminarProducto", "/eliminarproducto", "/eliminarProducto" })
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	response.setContentType("text/html;charset=UTF-8");
    	
    	String json = request.getParameter("jsonData");
		JsonObject producto = (JsonObject) new JsonParser().parse(json);
        
        int idProducto = (int) producto.get("idProducto").getAsInt();
        ControladorDeProducto ctrlProducto = new ControladorDeProducto();
        Producto prod = new Producto();
        prod.setIdProducto(idProducto);
        int resp = 0;
        
        try{
        	if(ctrlProducto.eliminarProducto(prod)){
        		resp=1;
                response.getWriter().println(resp);
            }else{
                response.getWriter().println(resp);
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        
    }
}
