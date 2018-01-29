package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Categoria;
import entidades.Producto;
import entidades.Subcategoria;
import entidades.Turno;

public class DatosTurno implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR TURNO
	//						MODIFICAR TURNO
	//						GET HORARIOS DISPONIBLES
	
	public Boolean agregarTurno (Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Boolean bandera=false;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO turno(idMascota,idServicio,fecha,hora,repetir,retiroDom,estado,observaciones) VALUES (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, turno.getMascota().getIdMascota());
			pstm.setInt(2, turno.getServicio().getIdServicio());
			pstm.setDate(3, turno.getFecha());
			pstm.setTime(4, turno.getHora());
			pstm.setString(5, turno.getRepetir());
			pstm.setBoolean(6, turno.getRetiroDom());
			pstm.setString(7, turno.getEstado());
			pstm.setString(8, turno.getObservaciones());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				turno.setIdTurno(rs.getInt(1));
				bandera=true;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		return bandera;
		
	}
	public void modificarTurno(Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE turno SET idMascota=?,idServicio=?,fecha=?,hora=?,repetir=?,retiroDom=?,estado=?, observaciones=? WHERE idTurno=?");
			pstm.setInt(1, turno.getMascota().getIdMascota());
			pstm.setInt(2, turno.getServicio().getIdServicio());
			pstm.setDate(3, turno.getFecha());
			pstm.setTime(4, turno.getHora());
			pstm.setString(5, turno.getRepetir());
			pstm.setBoolean(6, false);//turno.getRetiroDom());
			pstm.setString(7, turno.getEstado());
			pstm.setString(8, turno.getObservaciones());
			pstm.setInt(9, turno.getIdTurno());
			pstm.executeUpdate();
		} 
		catch (Exception e) 
		{
			throw e;
		}
		
		finally
		{
			try {
				if(pstm!=null)pstm.close();
				FactoryConnection.getinstancia().releaseConn();
			} catch (Exception e) {
				throw e;
			}	
		}
		
	}
	public ArrayList<Time> getHorariosDisponibles(String fechaSeleccionada)throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Time> horariosDisponibles = new ArrayList<Time>();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareCall(
					"call getHorariosDisponibles(?)");
			pstm.setString(1, fechaSeleccionada);
		
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Time horarioActual = rs.getTime("horario");
					horariosDisponibles.add(horarioActual);
				}
				
			}
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} 
		catch (Exception e) {
			throw e;
		}
		return horariosDisponibles;
	}
}
