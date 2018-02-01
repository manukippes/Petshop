package logica;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Hashtable;

import datos.DatosTurno;
import entidades.Producto;
import entidades.Turno;

public class ControladorDeTurno implements Serializable{
	private DatosTurno baseTurno = new DatosTurno();
	
	// METODOS IMPLEMENTADOS
	
	//			GET HORARIOS DISPONIBLES PARA UNA FECHA
	//			AGREGAR TURNO
	//			FILTRAR PRODUCTOS
	//			GET TURNO (COMPLETAR DATOS)
	//			CANCELAR TURNO
	//			MODIFICAR TURNO
	
	
	public ArrayList<Time> getHorariosDisponibles(String fechaSeleccionada) throws Exception{
		return baseTurno.getHorariosDisponibles(fechaSeleccionada);
	}
	
	public Boolean agregarTurno(Turno turno) throws Exception{
		return baseTurno.agregarTurno(turno);
	}
	
	public ArrayList<Turno> getTurnos(Hashtable<String, String> parametros)throws Exception{

		return baseTurno.getTurnos(parametros);
	}
	public Turno getTurno(Turno turno) throws Exception{
		return baseTurno.getTurno(turno);
	}
	
	public Boolean cancelarTurno(Turno turno) throws Exception{
		return baseTurno.cancelarTurno(turno);
	}
	
	public Boolean modificarTurno(Turno turno) throws Exception{
		return baseTurno.modificarTurno(turno);
	}
}
