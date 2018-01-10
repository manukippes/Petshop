package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Mascota;

public class DatosMascota implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR MASCOTA
	//						MODIFICAR MASCOTA
	
	public void agregarMascota (Mascota mascota) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO mascota(idMascota,idUsuario,idTipoMascota,nombre,fechaNacimiento,observaciones) VALUES (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, mascota.getIdMascota());
			pstm.setInt(2, mascota.getUsuario().getIdUsuario());
			pstm.setInt(3, mascota.getTipoMascota().getIdTipoMascota());
			pstm.setString(4, mascota.getNombre());
			pstm.setDate(5, mascota.getFechaNacimiento());
			pstm.setString(6, mascota.getObservaciones());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				System.out.println("Algo fallo");
				mascota.setIdMascota(rs.getInt(1));
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
	public void modificarMascota(Mascota mascota) throws Exception
	{
		PreparedStatement pstm = null;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE mascota SET idUsuario=?,idTipoMascota=?,nombre=?,fechaNacimiento=?,observaciones=? WHERE idMascota=?");
			pstm.setInt(1, mascota.getUsuario().getIdUsuario());
			pstm.setInt(2, mascota.getTipoMascota().getIdTipoMascota());
			pstm.setString(3, mascota.getNombre());
			pstm.setDate(4, mascota.getFechaNacimiento());
			pstm.setString(5, mascota.getObservaciones());
			pstm.setInt(6, mascota.getIdMascota());
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
