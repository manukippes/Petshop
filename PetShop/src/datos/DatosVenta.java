package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Venta;

public class DatosVenta {
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR VENTA
	//						MODIFICAR VENTA
	
	public void agregarVenta (Venta venta) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO venta(idVenta,idUsuario,idMedioPago,total,estado,fecha,datosOpcionales,envioDom,domicilio) VALUES (?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, venta.getIdVenta());
			pstm.setInt(2, venta.getUsuario().getIdUsuario());
			pstm.setInt(3, venta.getMedioPago().getIdMedioPago());
			pstm.setDouble(4, venta.getTotal());
			pstm.setString(5, venta.getEstado());
			pstm.setDate(6, venta.getFecha());
			pstm.setString(7, venta.getDatosOpcionales());
			pstm.setBoolean(8, venta.getEnvioDom());
			pstm.setString(9, venta.getDomicilio());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				venta.setIdVenta(rs.getInt(1));
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
	public void modificarVenta(Venta venta) throws Exception
	{
		PreparedStatement pstm = null;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE usuario SET idUsuario=?,idMedioPago=?,total=?,estado=?,fecha=?,datosOpcionales=?,envioDom=?,domicilio=? WHERE idVenta=?");	
			pstm.setInt(1, venta.getUsuario().getIdUsuario());
			pstm.setInt(2, venta.getMedioPago().getIdMedioPago());
			pstm.setDouble(3, venta.getTotal());
			pstm.setString(4, venta.getEstado());
			pstm.setDate(5, venta.getFecha());
			pstm.setString(6, venta.getDatosOpcionales());
			pstm.setBoolean(7, venta.getEnvioDom());
			pstm.setString(8, venta.getDomicilio());
			pstm.setInt(9, venta.getIdVenta());
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
