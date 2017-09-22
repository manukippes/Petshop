package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entidades.TipoMascota;

import utilidades.ExcepcionesEscritorio;

public class DatosTipoMascota {
//			METODOS IMPLEMENTADOS:
//								DEVOLVER TODOS LOS TIPOS DE MASCOTA

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
		
		catch (ExcepcionesEscritorio excep) 
		{
			throw excep;
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
}
