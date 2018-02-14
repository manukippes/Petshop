package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entidades.Categoria;
import entidades.Mascota;
import entidades.Producto;
import entidades.Subcategoria;
import entidades.TipoMascota;
import entidades.Usuario;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;
import utilidades.ExcepcionEspecial;

public class DatosMascota implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR MASCOTA
	//						MODIFICAR MASCOTA
	//						GET MASCOTAS DE UN CLIENTE
	//						GET MASCOTA (COMPLETAR CLASE)
	//						ELIMINAR MASCOTA 
	
	public boolean agregarMascota (Mascota mascota) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO mascota(idMascota,idUsuario,idTipoMascota,nombre,fechaNacimiento,observaciones,estado) VALUES (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, mascota.getIdMascota());
			pstm.setInt(2, mascota.getUsuario().getIdUsuario());
			pstm.setInt(3, mascota.getTipoMascota().getIdTipoMascota());
			pstm.setString(4, mascota.getNombre());
			pstm.setDate(5, mascota.getFechaNacimiento());
			pstm.setString(6, mascota.getObservaciones());
			pstm.setInt(7, 1);
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
		
		return true;
	}
	public void modificarMascota(Mascota mascota) throws Exception
	{
		PreparedStatement pstm = null;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE mascota SET idUsuario=?,idTipoMascota=?,nombre=?,fechaNacimiento=?,observaciones=?,estado=? WHERE idMascota=?");
			pstm.setInt(1, mascota.getUsuario().getIdUsuario());
			pstm.setInt(2, mascota.getTipoMascota().getIdTipoMascota());
			pstm.setString(3, mascota.getNombre());
			pstm.setDate(4, mascota.getFechaNacimiento());
			pstm.setString(5, mascota.getObservaciones());
			pstm.setInt(6, mascota.getEstado());
			pstm.setInt(7, mascota.getIdMascota());
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
	public ArrayList<Mascota> getMascotas(Usuario cliente) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Mascota> mascotas= new ArrayList<Mascota>();
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM MASCOTA where idUsuario =?");
			pstm.setInt(1, cliente.getIdUsuario());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Mascota mascotaActual= new Mascota();
					mascotaActual.setIdMascota(rs.getInt("idMascota"));					//SETEO ID MASCOTA
					mascotaActual.setNombre(rs.getString("nombre"));					//SETEO NOMBRE DE LA MASCOTA
					mascotaActual.setFechaNacimiento(rs.getDate("fechaNacimiento"));	//SETEO FECHA DE NACIMIENTO
					mascotaActual.setObservaciones(rs.getString("observaciones"));		//SETEO OBSERVACIONES
					mascotaActual.setUsuario(cliente);									//SETEO DUEÑO DE LA MASCOTA
					
					TipoMascota tipoMascota = new TipoMascota();
					tipoMascota.setIdTipoMascota(rs.getInt("idTipoMascota"));
					tipoMascota = ctrlTipoMascota.getTipoMascota(tipoMascota);
					
					mascotaActual.setTipoMascota(tipoMascota);							//SETEO EL TIPO DE MASCOTA
					mascotaActual.setEstado(rs.getInt("estado"));						//SETEO ESTADO DE LA MASCOTA
					mascotas.add(mascotaActual);										//AGREGO LA MASCOTA AL ARRAYLIST
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
		return mascotas;
	}
	
	public Mascota getMascota(Mascota mascota) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Mascota mascotaActual = new Mascota();
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		TipoMascota tipoMascota = new TipoMascota();
		Usuario duenio = new Usuario();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM MASCOTA where idMascota =?");
			pstm.setInt(1, mascota.getIdMascota());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					mascotaActual.setIdMascota(rs.getInt("idMascota"));					//SETEO ID MASCOTA
					duenio.setIdUsuario(rs.getInt("idUsuario"));
					tipoMascota.setIdTipoMascota(rs.getInt("idTipoMascota"));
					mascotaActual.setNombre(rs.getString("nombre"));					//SETEO NOMBRE DE LA MASCOTA
					mascotaActual.setFechaNacimiento(rs.getDate("fechaNacimiento"));	//SETEO FECHA DE NACIMIENTO
					mascotaActual.setObservaciones(rs.getString("observaciones"));		//SETEO OBSERVACIONES			
					mascotaActual.setEstado(rs.getInt("estado"));						//SETEO ESTADO
					
					duenio = ctrlUsuario.getUsuario(duenio);
					mascotaActual.setUsuario(duenio);									//SETEO DUEÑO DE LA MASCOTA
					
					tipoMascota = ctrlTipoMascota.getTipoMascota(tipoMascota);
					mascotaActual.setTipoMascota(tipoMascota);							//SETEO EL TIPO DE MASCOTA
					
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
		return mascotaActual;
	}
	
	public boolean eliminarMascota(Mascota masco) throws Exception, ExcepcionEspecial{
		PreparedStatement ps = null;
		boolean respuesta = false;
		
		try {
			ps = FactoryConnection.getinstancia().getConn().prepareStatement("UPDATE mascota SET estado=? WHERE idMascota = ?");
			ps.setInt(1, masco.getEstado());
			ps.setInt(2, masco.getIdMascota());
			ps.executeQuery();
			respuesta = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExcepcionEspecial e1) {
			throw new ExcepcionEspecial(e1,"No es posible eliminar la persona de la base de datos", Level.ERROR);
		}
		

		try {
			if(ps!=null)ps.close();
			FactoryConnection.getinstancia().releaseConn();
		} 
		catch (Exception e) {
			throw e;
		}
		
		return respuesta;
		
	}
}
