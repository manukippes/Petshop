package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;

import entidades.Producto;
import entidades.Usuario;
import jdk.nashorn.internal.parser.JSONParser;
import logica.ControladorDeUsuario;

/**
 * Servlet implementation class VentasPaso2
 */
@WebServlet({ "/CargarProductosVenta", "/cargarProductosVenta", "/cargarproductosventa" })
public class CargarProductosVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarProductosVenta() {
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
		
		ArrayList<ArrayList<String>> productosVenta = new ArrayList<ArrayList<String>>();
		
		//OBTENGO EL ARREGLO DE LOS PRODUCTOS EN EL JSON
		JsonArray productos = (JsonArray) new JsonParser().parse(json);
		
		//LOS AGREGO A UN ARREGLO DE PRODUCTOS DE LA VENTA
		for (int i=0;i<productos.size();i++){
			String idProducto = ((JsonObject) productos.get(i)).get("idProducto").getAsString();
			String cantidad = ((JsonObject) productos.get(i)).get("cantidad").getAsString();
			ArrayList<String> prodCant = new ArrayList<>();//CREO UN ELEMENTO {IDPRODUCTO,CANTIDAD}
			prodCant.add(idProducto);
			prodCant.add(cantidad);
			productosVenta.add(prodCant);//AGREGO EL ELEMENTO A LA VENTA
		}
		request.getSession().setAttribute("productosVenta", productosVenta);
		
	}
}
