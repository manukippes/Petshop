package logica;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

import datos.DatosTurno;

public class ControladorDeTurno implements Serializable{
	private DatosTurno baseTurno = new DatosTurno();
	
	public ArrayList<Time> getHorariosDisponibles(String fechaSeleccionada) throws Exception{
		return baseTurno.getHorariosDisponibles(fechaSeleccionada);
	}
}
