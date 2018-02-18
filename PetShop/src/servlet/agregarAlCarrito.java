package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class agregarAlCarrito
 */
@WebServlet("/agregarAlCarrito")
public class agregarAlCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregarAlCarrito() {
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
		
		int i=0;
		Boolean productoExistente = false;
		
		ArrayList<ArrayList<String>> productosVenta = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("productosVenta");

		String idProducto = (String) request.getParameter("idProducto");
		String cantidad = (String) request.getParameter("cantidad");
		ArrayList<String> prodCant = new ArrayList<>();				//CREO UN ELEMENTO {IDPRODUCTO,CANTIDAD}
		prodCant.add(idProducto);
		prodCant.add(cantidad);

		
		for(ArrayList<String> prodcante : productosVenta){	 		//RECORRO TODOS LOS PRODUCTOS PARA VER SI YA EXISTE
			if(prodcante.get(0).equals(idProducto)){
				productosVenta.set(i, prodCant);
				productoExistente = true;							//SE ENCONTRO EL PRODUCTO
			}
			i++;
		}
		if(productoExistente==false){								//NO SE ENCONTRO EL PRODUCTO
			productosVenta.add(prodCant);							//AGREGO EL ELEMENTO A LA VENTA
		}
		
		request.getSession().removeAttribute("productosVenta");
		request.getSession().setAttribute("productosVenta", productosVenta);
	}

}
