package logica;

import java.io.Serializable;

import datos.DatosTipoMascota;
import entidades.TipoMascota;
import entidades.TipoMascotaServicio;

public class ControladorDeTipoMascota implements Serializable{
	DatosTipoMascota baseTipoMascota = new DatosTipoMascota();
	
	
	public TipoMascota getTipoMascota(TipoMascota tipoMascota) throws Exception{
		return baseTipoMascota.getTipoMascota(tipoMascota);
	}
	public TipoMascotaServicio getTipoMascotaServicio(TipoMascotaServicio tMascServ) throws Exception{
		return baseTipoMascota.getTipoMascotaServicio(tMascServ);
	}
}
