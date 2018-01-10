package datos;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Servicio;
import entidades.TipoMascota;

public class DatosServicio implements Serializable{
//			METODOS IMPLEMENTADOS:
//					DEVOLVER TODOS LOS SERVICIOS

	public ArrayList<Servicio> devolverTodos() throws Exception
	{
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<Servicio> servicios= new ArrayList<Servicio>();
		
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
		
		return servicios;
		
	}
}
