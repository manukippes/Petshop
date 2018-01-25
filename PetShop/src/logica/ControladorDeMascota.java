package logica;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosMascota;
import entidades.Mascota;
import entidades.Usuario;

public class ControladorDeMascota implements Serializable{

	private DatosMascota baseMascota = new DatosMascota();
	
	
	public ArrayList<Mascota> getMascotas(Usuario cliente) throws Exception{
		return baseMascota.getMascotas(cliente);
	}
}
