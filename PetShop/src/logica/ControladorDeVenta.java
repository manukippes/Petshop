package logica;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosVenta;
import entidades.Cuotas;
import entidades.MedioPago;
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
	
	public Boolean agregarVenta(Venta venta) throws Exception{
		return baseVenta.agregarVenta(venta);
	}
}
