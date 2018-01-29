package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Mascota;
import entidades.Servicio;
import entidades.TipoMascota;
import entidades.TipoMascotaServicio;
import entidades.Usuario;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;

public class DatosServicio implements Serializable{
//			METODOS IMPLEMENTADOS:
//					GET SERVICIOS
//					GET SERVICIO (COMPLETAR CLASE)
//					GET TIPO MASCOTA SERVICIO

	public ArrayList<Servicio> getServicios() throws Exception
	{
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		
		try 
		{
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("select * from servicio");
			if(rs!=null){
				while(rs.next()){
					Servicio servicio = new Servicio();
					servicio.setIdServicio(rs.getInt("idServicio"));
					servicio.setTipo(rs.getString("tipo"));
					servicios.add(servicio);
				}
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		
		try {
			if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return servicios;
		
	}
	public Servicio getServicio(Servicio servicio) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Servicio servicioActual = new Servicio();
		
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM Servicio where idServicio =?");
			pstm.setInt(1, servicio.getIdServicio());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				if(rs.next())
				{
					servicioActual.setIdServicio(rs.getInt("idServicio"));
					servicioActual.setTipo(rs.getString("tipo"));					
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
		return servicioActual;
	}
	public TipoMascotaServicio getTipoMascotaServicio(Mascota mascota, Servicio servicio) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TipoMascotaServicio tipoMascotaServicioActual = new TipoMascotaServicio();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM tipo_mascota_servicio where idServicio =? and idTipoMascota =?");
			pstm.setInt(1, servicio.getIdServicio());
			pstm.setInt(2, mascota.getTipoMascota().getIdTipoMascota());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					tipoMascotaServicioActual.setIdTMascServ(rs.getInt("idTMascServ"));
					tipoMascotaServicioActual.setTiempo(rs.getTime("tiempo"));
					tipoMascotaServicioActual.setServicio(servicio);
					tipoMascotaServicioActual.setTipoMascota(mascota.getTipoMascota());
					tipoMascotaServicioActual.setPrecio(rs.getDouble("precio"));
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
		return tipoMascotaServicioActual;
	}
}
