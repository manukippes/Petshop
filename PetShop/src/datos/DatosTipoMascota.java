package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Categoria;
import entidades.Mascota;
import entidades.Producto;
import entidades.Servicio;
import entidades.Subcategoria;
import entidades.TipoMascota;
import entidades.TipoMascotaServicio;
import logica.ControladorDeMascota;
import logica.ControladorDeServicio;
import logica.ControladorDeTipoMascota;


public class DatosTipoMascota implements Serializable{
//			METODOS IMPLEMENTADOS:
//								DEVOLVER TODOS LOS TIPOS DE MASCOTA
	//							GET TIPO MASCOTA (COMPLETAR LOS DATOS)
	//							GET TIPO MASCOTA SERVICIO (COMPLETAR LOS DATOS)

	public ArrayList<TipoMascota> devolverTodos() throws Exception
	{
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<TipoMascota> tiposdemascota= new ArrayList<TipoMascota>();
		
		try 
		{
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("select * from tipo_mascota");
			if(rs!=null){
				while(rs.next()){
					TipoMascota tipomascota = new TipoMascota();
					tipomascota.setIdTipoMascota(rs.getInt("idTipoMascota"));
					tipomascota.setPelo(rs.getString("pelo"));
					tipomascota.setTamanio(rs.getString("tamanio"));
					tiposdemascota.add(tipomascota);
				}
			}
		} 
		catch (SQLException e) 
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
		
		return tiposdemascota;
		
	}
	public TipoMascota getTipoMascota(TipoMascota tipoMascota)throws Exception{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM tipo_mascota where idTipoMascota=?");
			pstm.setInt(1, tipoMascota.getIdTipoMascota());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					tipoMascota.setPelo(rs.getString("pelo"));			//SETEO PELO
					tipoMascota.setTamanio(rs.getString("tamanio"));	//SETEO TAMAÑO
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
		return tipoMascota;
	}
	public TipoMascotaServicio getTipoMascotaServicio(TipoMascotaServicio tMascServ) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ControladorDeServicio ctrlServicio = new ControladorDeServicio();
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM tipo_mascota_servicio where idTMascServ=?");
			pstm.setInt(1, tMascServ.getIdTMascServ());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					tMascServ.setTiempo(rs.getTime("tiempo"));
					tMascServ.setPrecio(rs.getDouble("precio"));
					
					Servicio servicioActual = new Servicio();
					servicioActual.setIdServicio(rs.getInt("idServicio"));
					servicioActual = ctrlServicio.getServicio(servicioActual);
					tMascServ.setServicio(servicioActual);
					
					TipoMascota tipoMascotaActual = new TipoMascota();
					tipoMascotaActual.setIdTipoMascota(rs.getInt("idTipoMascota"));
					tipoMascotaActual = ctrlTipoMascota.getTipoMascota(tipoMascotaActual);
					tMascServ.setTipoMascota(tipoMascotaActual);
					
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
		return tMascServ;
	}
}
