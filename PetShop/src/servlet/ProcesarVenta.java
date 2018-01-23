package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		
		//INICIALIZACIONES NECESARIAS PARA EL METODO
		ControladorDeVenta ctrlVenta = new ControladorDeVenta();			//CONTROLADOR
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();		//CONTROLADOR
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();	//CONTROLADOR
		Venta ventaActual = new Venta();									//VENTA NUEVA A CARGAR
		ArrayList<LineaVenta> lineas = new ArrayList<>();					//ARREGLO DE LINEAS DE VENTA
		
		
		//OBTENER LO PARAMETROS EN JSON Y PARSEARLOS
		String json = request.getParameter("jsonData");
		
		JsonObject campos = (JsonObject) new JsonParser().parse(json);
		
		int idUsuario = (int) campos.get("idUsuario").getAsInt();
		int idMedioPago = (int) campos.get("medioPago").getAsInt();
		int idTarjeta = (int) campos.get("tarjeta").getAsInt();
		int idCuotas = (int) campos.get("cuotas").getAsInt();
		String observaciones = (String) campos.get("observaciones").getAsString();
		
		//CREAR LOS PARAMETROS NECESARIOS PARA AGREGAR UNA VENTA A LA BD
		boolean bandera;		//VALIDACION DE OPERACION EXITOSA
		Double total = 0.0;		//TOTAL DE LA VENTA NECESARIO PARA CREARLA
		
		
		try {
			//CREO EL USUARIO DE LA VENTA (CLIENTE)			
			Usuario usuario = new Usuario();									//USUARIO
			usuario.setIdUsuario(idUsuario);
			if (idUsuario!=0){
				usuario = ctrlUsuario.getUsuario(usuario);
				ventaActual.setUsuario(usuario);
			}else{
				ventaActual.setUsuario(usuario);
			}
						
			//CREO EL MEDIO DE PAGO
			
			MedioPago medioPago = new MedioPago();
			medioPago.setIdMedioPago(idMedioPago);
			if (idMedioPago!=0){												//MEDIO DE PAGO
				medioPago = ctrlVenta.getMedioPago(medioPago);
				ventaActual.setMedioPago(medioPago);
				}else{
				ventaActual.setMedioPago(medioPago);
				}
			
			//CREO LA TARJETA
			Tarjeta tarjeta = new Tarjeta();
			tarjeta.setIdTarjeta(idTarjeta);
			if (idTarjeta!=0){													//TARJETA
				tarjeta = ctrlVenta.getTarjeta(tarjeta);
				ventaActual.setTarjeta(tarjeta);
			}else{
				ventaActual.setTarjeta(tarjeta);
			}
			
			//CREO LAS CUOTAS
			Cuotas cuotas = new Cuotas();
			cuotas.setIdCuota(idCuotas);
			if (idCuotas!=0){													//CUOTAS
				cuotas = ctrlVenta.getCuotas(cuotas);
				ventaActual.setCuotas(cuotas);
			}else{
				ventaActual.setCuotas(cuotas);
			}
			
			ventaActual.setDatosOpcionales(observaciones);						//OBSERVACIONES			
			ventaActual.setDomicilio("");										//DOMICILIO
			ventaActual.setEnvioDom(false);										//ENVIODOMICILIO
			ventaActual.setEstado("Finalizada");								//ESTADO
			
			//OBTENGO LA FECHA ACTUAL
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date fechaDate = df.parse(ctrlVenta.getFechaActual());
			java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
		
			ventaActual.setFecha(sqlDate);										//FECHA DE LA VENTA
			
			ventaActual.setTotal(total);										//TOTAL EN 0 PARA CREAR LA VENTA
			
			//CREACION DE LA VENTA EN LA BD
			int idVenta = ctrlVenta.agregarVenta(ventaActual); 					//CREO LA VENTA PARA OBTENER EL ID 
			if (idVenta!=0){													//NECESARIO PARA CREAR LAS LINEAS DE VENTA
				ventaActual.setIdVenta(idVenta);
				
				bandera = true;
			}else{
				bandera = false;
			}
			
			//CREO LAS LINEAS DE VENTA Y SE LAS AGREGO AL ARREGO DE LINEAS DE VENTA DE LA CLASE
			
			
			//TOMO EL ARREGLO DE PRODUCTOS Y CANTIDADES QUE HAY EN LA SESION
			ArrayList<ArrayList<String>> productosVenta = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("productosVenta");
			
			//RECORRO EL ARREGLO GENERANDO LAS LINEAS DE VENTA
			for(ArrayList<String> elemento : productosVenta){
				int idProducto = Integer.parseInt(elemento.get(0));				//IDPRODUCTO
				int cantidad = Integer.parseInt(elemento.get(1));				//CANTIDAD
				LineaVenta linea = new LineaVenta();
				linea.setTipoLineaVta("Mostrador");
				
				Producto prodActual = new Producto();							//CREO UN NUEVO PRODUCTO
				prodActual.setIdProducto(idProducto);							//SETEO EL ID DEL PRODUCTO
				prodActual = ctrlProducto.getProducto(prodActual);				//OBTENGO EL PRODUCTO DE LA BD
				
				total += (prodActual.getPrecio()*cantidad);						//AUMENTO EL TOTAL DE LA VENTA
				
				linea.setVenta(ventaActual);									//SETEO VENTA EN LV
				linea.setProducto(prodActual);									//SETEO PRODUCTO EN LV
				linea.setPrecioUnitario(prodActual.getPrecio());				//SETEO PRECIOUNITARIO (PARA QUE?)
				linea.setCantidad(cantidad);									//SETEO CANTIDAD DEL PRODUCTO
				TipoMascotaServicio tmascserv = new TipoMascotaServicio();		//CREO TIPOMASCOTASERVICIO
				tmascserv.setIdTMascServ(0);									//LE ASIGNO EL ID EN 0
				linea.setTipoMascotaServicio(tmascserv);						//SETEO EL TIPOMASCOTASERVICIO
				lineas.add(linea);												//AGREGO LA LINEA AL ARREGLO
				
				bandera = ctrlVenta.agregarLineaVenta(linea);					//AGREGO LA LINEA A LA BD
			}		
			ventaActual.setLineas(lineas);							//SETEO EL ARRAYLIST DE LINEAS EN LA VENTA
			ventaActual.setTotal(total);							//SETEO EL TOTAL A LA VENTA
			
			bandera = ctrlVenta.modificarVenta(ventaActual);		//UNA VEZ CARGADAS TODAS LAS FILAS ACTUALIZO EL TOTAL DE LA VENTA
			
			if(bandera){
				response.getWriter().println("Venta Cargada Exitosamente");	
			}else{
				response.getWriter().println("Error al cargar la Venta");	
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
