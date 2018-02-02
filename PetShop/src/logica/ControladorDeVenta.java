package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import datos.DatosVenta;
import entidades.Cuotas;
import entidades.LineaVenta;
import entidades.MedioPago;
import entidades.Producto;
import entidades.Tarjeta;
import entidades.Venta;

public class ControladorDeVenta implements Serializable{

	private DatosVenta baseVenta = new DatosVenta();
	//		METODOS IMPLEMENTADOS
	
	//					GET TARJETA
	// 					GET MEDIO DE PAGO (COMPLETAR LOS DATOS)
	//					GET MEDIOS DE PAGO
	//					GET TARJETAS (SEGUN MEDIO DE PAGO)
	//					GET CUOTAS DE LA TARJETA
	//					GET CUOTAS (COMPLETAR CLASE)7
	//					AGREGAR VENTA
	//					AGREGAR LINEA VENTA
	//					OBTENER VENTAS FILTRADAS POR PARAMETROS
	//					GET VENTA (COMPLETAR DATOS)
	//					GET LINEAS DE UNA VENTA
	
	public Tarjeta getTarjeta(Tarjeta tarjeta)throws Exception{
		
		return baseVenta.getTarjeta(tarjeta);
	}
	
	public MedioPago getMedioPago(MedioPago medioPago)throws Exception{
		
		return baseVenta.getMedioPago(medioPago);
	}
	
	
	public ArrayList<MedioPago> getMediosPago() throws Exception{
		
		return baseVenta.getMediosPago();
	}
	
	public ArrayList<Tarjeta> getTarjetas(MedioPago medioPago) throws Exception{
		
		return baseVenta.getTarjetas(medioPago);
	}
		
	public ArrayList<Cuotas> getCuotas(Tarjeta tarjeta) throws Exception{
		
		return baseVenta.getCuotas(tarjeta);
	}
		
	public Cuotas getCuotas(Cuotas cuotas) throws Exception{
		return baseVenta.getCuotas(cuotas);
	}
	
	public int agregarVenta(Venta venta) throws Exception{
		return baseVenta.agregarVenta(venta);
	}
	
	public String getFechaActual() throws Exception{
		return baseVenta.getFechaActual();
	}
	public Boolean agregarLineaVenta(LineaVenta lv) throws Exception {
		return baseVenta.agregarLineaVenta(lv);
	}
	public Boolean modificarVenta(Venta venta) throws Exception{
		return baseVenta.modificarVenta(venta);
	}
	public ArrayList<Venta> getVentas(Hashtable<String, String> parametros)throws Exception{

		return baseVenta.getVentas(parametros);
	}
	public Venta getVenta(Venta venta) throws Exception{
		return baseVenta.getVenta(venta);
	}
	public ArrayList<LineaVenta> getLineas(Venta venta) throws Exception{
		return baseVenta.getLineasVenta(venta);
	}
}
