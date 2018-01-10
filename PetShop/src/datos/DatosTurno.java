package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Turno;

public class DatosTurno implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR TURNO
	//						MODIFICAR TURNO
	
	public void agregarTurno (Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO turno(idTurno,idMascota,idServicio,fecha,hora,repetir,retiroDom,estado) VALUES (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, turno.getIdTurno());
			pstm.setInt(2, turno.getMascota().getIdMascota());
			pstm.setInt(3, turno.getServicio().getIdServicio());
			pstm.setString(4, turno.getFecha());
			pstm.setString(5, turno.getHora());
			pstm.setString(6, turno.getRepetir());
			pstm.setBoolean(7, false);//turno.getRetiroDom());
			pstm.setString(8, turno.getEstado());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				turno.setIdTurno(rs.getInt(1));
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
		
	}
	public void modificarTurno(Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE turno SET idMascota=?,idServicio=?,fecha=?,hora=?,repetir=?,retiroDom=?,estado=? WHERE idTurno=?");
			pstm.setInt(1, turno.getMascota().getIdMascota());
			pstm.setInt(2, turno.getServicio().getIdServicio());
			pstm.setString(3, turno.getFecha());
			pstm.setString(4, turno.getHora());
			pstm.setString(5, turno.getRepetir());
			pstm.setBoolean(6, false);//turno.getRetiroDom());
			pstm.setString(7, turno.getEstado());
			pstm.setInt(8, turno.getIdTurno());
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
}
