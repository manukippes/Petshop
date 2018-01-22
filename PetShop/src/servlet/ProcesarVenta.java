package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entidades.Cuotas;
import entidades.LineaVenta;
import entidades.MedioPago;
import entidades.Producto;
import entidades.Tarjeta;
import entidades.TipoMascotaServicio;
import entidades.Usuario;
import entidades.Venta;
import logica.ControladorDeProducto;
import logica.ControladorDeUsuario;
import logica.ControladorDeVenta;

/**
 * Servlet implementation class ProcesarVenta
 */
@WebServlet({ "/ProcesarVenta", "/procesarVenta", "/Procesarventa", "/procesarventa" })
public class ProcesarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarVenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = request.getParameter("jsonData");
		
		JsonObject campos = (JsonObject) new JsonParser().parse(json);
		
		int idUsuario = (int) campos.get("idUsuario").getAsInt();
		int idMedioPago = (int) campos.get("medioPago").getAsInt();
		int idTarjeta = (int) campos.get("tarjeta").getAsInt();
		int idCuotas = (int) campos.get("cuotas").getAsInt();
		String observaciones = (String) campos.get("observaciones").getAsString();
		
		ControladorDeVenta ctrlVenta = new ControladorDeVenta();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		Venta ventaActual = new Venta();
		ArrayList<LineaVenta> lineas = new ArrayList<>();
		
		try {
			//CREO EL USUARIO DE LA VENTA (CLIENTE)
			
			if (idUsuario!=0){
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(idUsuario);
				usuario = ctrlUsuario.getUsuario(usuario);
				ventaActual.setUsuario(usuario);
			}
						
			//CREO EL MEDIO DE PAGO
			
			if (idMedioPago!=0){
				MedioPago medioPago = new MedioPago();
				medioPago.setIdMedioPago(idMedioPago);
				medioPago = ctrlVenta.getMedioPago(medioPago);
				ventaActual.setMedioPago(medioPago);
				}
			
			//CREO LA TARJETA
			
			if (idTarjeta!=0){
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setIdTarjeta(idTarjeta);
				tarjeta = ctrlVenta.getTarjeta(tarjeta);
				ventaActual.setTarjeta(tarjeta);
				}
			
			//CREO LAS CUOTAS
			if (idCuotas!=0){
				Cuotas cuotas = new Cuotas();
				cuotas.setIdCuota(idCuotas);
				cuotas = ctrlVenta.getCuotas(cuotas);
				ventaActual.setCuotas(cuotas);
				}
			ventaActual.setDatosOpcionales(observaciones);
			ventaActual.setDomicilio("");
			ventaActual.setEnvioDom(false);
			ventaActual.setEstado("Finalizada");
			
			//CREO LAS LINEAS DE VENTA Y SE LAS AGREGO AL ARREGO DE LINEAS DE VENTA DE LA CLASE
			
			ArrayList<ArrayList<String>> productosVenta =(ArrayList<ArrayList<String>>) request.getSession().getAttribute("productosVenta");
			for(ArrayList<String> elemento : productosVenta){
				int idProducto = Integer.parseInt(elemento.get(0));
				int cantidad = Integer.parseInt(elemento.get(1));
				LineaVenta linea = new LineaVenta();
				linea.setTipoLineaVta("Mostrador");
				
				Producto prodActual = new Producto();
				prodActual.setIdProducto(idProducto);
				prodActual = ctrlProducto.getProducto(prodActual);
				
				linea.setProducto(prodActual);
				linea.setPrecioUnitario(prodActual.getPrecio());
				linea.setCantidad(cantidad);
				lineas.add(linea);
			}
			
			ventaActual.setLineas(lineas);
			
			System.out.println(ctrlVenta.agregarVenta(ventaActual));
			

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
