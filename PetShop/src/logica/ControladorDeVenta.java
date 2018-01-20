package logica;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosVenta;
import entidades.MedioPago;
import entidades.Tarjeta;

public class ControladorDeVenta implements Serializable{

	//		METODOS IMPLEMENTADOS
	
	//					GET TARJETA
	// 					GET MEDIO DE PAGO (COMPLETAR LOS DATOS)
	//					GET MEDIOS DE PAGO
	//					GET TARJETAS (SEGUN MEDIO DE PAGO)
	
	public Tarjeta getTarjeta(Tarjeta tarjeta)throws Exception{
		DatosVenta baseVenta = new DatosVenta();
		return baseVenta.getTarjeta(tarjeta);
	}
	
	public MedioPago getMedioPago(MedioPago medioPago)throws Exception{
		DatosVenta baseVenta = new DatosVenta();
		return baseVenta.getMedioPago(medioPago);
	}
	
	
	public ArrayList<MedioPago> getMediosPago() throws Exception{
		DatosVenta baseVenta = new DatosVenta();
		return baseVenta.getMediosPago();
	}
	
	public ArrayList<Tarjeta> getTarjetas(MedioPago medioPago) throws Exception{
		DatosVenta baseVenta = new DatosVenta();
		return baseVenta.getTarjetas(medioPago);
	}
	
}
