package logica;

import java.io.Serializable;

import datos.DatosTipoMascota;
import entidades.TipoMascota;

public class ControladorDeTipoMascota implements Serializable{
	DatosTipoMascota baseTipoMascota = new DatosTipoMascota();
	
	
	public TipoMascota getTipoMascota(TipoMascota tipoMascota) throws Exception{
		return baseTipoMascota.getTipoMascota(tipoMascota);
	}
}
