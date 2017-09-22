package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Producto;

public class DatosProducto {
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR PRODUCTO
	//						MODIFICAR PRODUCTO
	
	public void agregarProducto (Producto producto) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO producto(idProducto,idSubCategoria,nombre,stockMinimo,presentacion,precio) VALUES (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, producto.getIdProducto());
			pstm.setInt(2, producto.getSubcategoria().getIdSubCategoria());
			pstm.setString(3, producto.getNombre());
			pstm.setInt(4, producto.getStockMinimo());
			pstm.setString(5, producto.getPresentacion());
			pstm.setDouble(6, producto.getPrecio());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				producto.setIdProducto(rs.getInt(1));
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
	public void modificarProducto(Producto producto) throws Exception
	{
		PreparedStatement pstm = null;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE producto SET idSubCategoria=?,nombre=?,stockMinimo=?,presentacion=?,precio=? WHERE idProducto=?");
			pstm.setInt(1, producto.getSubcategoria().getIdSubCategoria());
			pstm.setString(2, producto.getNombre());
			pstm.setInt(3, producto.getStockMinimo());
			pstm.setString(4, producto.getPresentacion());
			pstm.setDouble(5, producto.getPrecio());
			pstm.setInt(6, producto.getIdProducto());
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
