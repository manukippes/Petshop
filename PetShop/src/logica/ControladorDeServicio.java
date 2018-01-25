package logica;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosServicio;
import entidades.Servicio;

public class ControladorDeServicio implements Serializable{
	private DatosServicio baseServicio = new DatosServicio();
	//		METODOS IMPLEMENTADOS
	
	//		GET TIPOSDESERVICIO
	
	public ArrayList<Servicio> getServicios() throws Exception{
		return baseServicio.getServicios();
	}
}
