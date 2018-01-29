package logica;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

import datos.DatosTurno;
import entidades.Turno;

public class ControladorDeTurno implements Serializable{
	private DatosTurno baseTurno = new DatosTurno();
	
	// METODOS IMPLEMENTADOS
	
	//			GET HORARIOS DISPONIBLES PARA UNA FECHA
	//			AGREGAR TURNO
	
	
	public ArrayList<Time> getHorariosDisponibles(String fechaSeleccionada) throws Exception{
		return baseTurno.getHorariosDisponibles(fechaSeleccionada);
	}
	
	public Boolean agregarTurno(Turno turno) throws Exception{
		return baseTurno.agregarTurno(turno);
	}
}
