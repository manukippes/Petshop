package logica;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosServicio;
import entidades.Mascota;
import entidades.Servicio;
import entidades.TipoMascotaServicio;

public class ControladorDeServicio implements Serializable{
	private DatosServicio baseServicio = new DatosServicio();
	//		METODOS IMPLEMENTADOS
	
	//		GET TIPOSDESERVICIO
	//		GET TIPO MASCOTA SERVICIO
	
	public ArrayList<Servicio> getServicios() throws Exception{
		return baseServicio.getServicios();
	}
	public Servicio getServicio(Servicio servicio) throws Exception{
		return baseServicio.getServicio(servicio);
	}
	
	public TipoMascotaServicio getTipoMascotaServicio(Mascota mascota, Servicio servicio) throws Exception{
		return baseServicio.getTipoMascotaServicio(mascota,servicio);
	}
}

