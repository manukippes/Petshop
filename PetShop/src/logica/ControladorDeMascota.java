package logica;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosMascota;
import entidades.Mascota;
import entidades.Usuario;

public class ControladorDeMascota implements Serializable{

	private DatosMascota baseMascota = new DatosMascota();
	
	//				METODOS IMPLEMENTADOS
	
	//			GET MASCOTAS (CLIENTE)
	//			GET MASCOTA (RECUPERA DATOS)
	//			AGREGAR MASCOTA
	//			ELIMINAR MASCOTA
	//			VERIFICAR SI EXISTE LA MASCOTA
	//			MODIFICAR MASCOTA
	
	
	public ArrayList<Mascota> getMascotas(Usuario cliente) throws Exception{
		return baseMascota.getMascotas(cliente);
	}
	
	public Mascota getMascota(Mascota mascota) throws Exception{
		return baseMascota.getMascota(mascota);
	}
	
	public boolean agregarMascota(Mascota mascota) throws Exception{
		boolean respuesta = baseMascota.agregarMascota(mascota);
		return respuesta;
	}
	
	public boolean eliminarMascota(Mascota mascota) throws Exception{
		boolean respuesta = baseMascota.eliminarMascota(mascota);
		return respuesta;
	}
	public Boolean existeMascota(Mascota mascota) throws Exception{
		return baseMascota.existeMascota(mascota);
	}
	public void modificarMascota(Mascota mascota)throws Exception{
		baseMascota.modificarMascota(mascota);
	}
}
