package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Producto;
import entidades.Subcategoria;
import utilidades.ExcepcionEspecial;
import entidades.Categoria;

public class DatosProducto {
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR PRODUCTO
	//						MODIFICAR PRODUCTO
	//						GET CATEGORIAS
	//						OBTENER PRODUCTOS DE UNA CATEGORIA
	//						OBTENER TODOS LOS PRODUCTOS
	
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
	
	public ArrayList<Categoria> getCategorias() throws ExcepcionEspecial, Exception{
		
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			
			try {
				stm = FactoryConnection.getinstancia().getConn().createStatement();
				rs = stm.executeQuery("SELECT * FROM CATEGORIA");
						
				if(rs!=null)
				{
					while(rs.next())
					{
						Categoria categoriaActual=new Categoria();
						categoriaActual.setIdCategoria(rs.getInt("idCategoria"));
						categoriaActual.setNombre(rs.getString("nombre"));
						categorias.add(categoriaActual);
					}
				}
			}
				
			catch (SQLException e) {
				throw e;
			} 
			
			try {
				if(rs!=null)rs.close();
				if(stm!=null)stm.close();
				FactoryConnection.getinstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
			
			return categorias;
		}
	
	public ArrayList<Producto> getProductos(Categoria categoria) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Producto> productos= new ArrayList<Producto>();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM PRODUCTO p INNER JOIN subcategoria sc ON p.idSubCategoria = sc.idSubCategoria INNER JOIN categoria c ON sc.idCategoria = c.idCategoria where c.idCategoria=?");
			pstm.setInt(1, categoria.getIdCategoria());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Producto productoActual= new Producto();
					productoActual.setIdProducto(rs.getInt("idProducto"));			//SETEO ID PRODUCTO DEL PRODUCTO
					productoActual.setNombre(rs.getString("nombre"));				//SETEO NOMBRE DEL PRODUCTO
					productoActual.setStock(rs.getInt("stock"));					//SETEO STOCK ACTUAL DEL PRODUCTO
					productoActual.setStockMinimo(rs.getInt("stockMinimo"));		//SETEO STOCK MINIMO DEL PRODUCTO
					productoActual.setPresentacion(rs.getString("presentacion"));	//SETEO PRESENTACION DEL PRODUCTO
					productoActual.setPrecio(rs.getDouble("precio"));				//SETEO PRECIO DEL PRODUCTO
					
					//CREO LA CATEGORIA
					Categoria cate = new Categoria();
					cate.setIdCategoria(rs.getInt("c.idCategoria"));
					cate.setNombre(rs.getString("c.nombre"));
					
					//CREO LA SUBCATEGORIA
					Subcategoria subcat = new Subcategoria();	
					subcat.setIdSubCategoria(rs.getInt("idSubCategoria"));
					subcat.setCategoria(cate);  //SETEO LA CATEGORIA			
					
					productoActual.setSubcategoria(subcat);  					//SETEO LA SUBCATEGORIA DEL PRODUCTO
					
					productos.add(productoActual);								//AGREGO EL PRODUCTO AL ARRAYLIST
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
		return productos;
	}
	
	public ArrayList<Producto> getProductos() throws Exception{
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Producto> productos= new ArrayList<Producto>();
		
		try {
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs=stm.executeQuery("SELECT * FROM PRODUCTO p INNER JOIN subcategoria sc ON p.idSubCategoria = sc.idSubCategoria INNER JOIN categoria c ON sc.idCategoria = c.idCategoria");
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Producto productoActual= new Producto();
					productoActual.setIdProducto(rs.getInt("idProducto"));			//SETEO ID PRODUCTO DEL PRODUCTO
					productoActual.setNombre(rs.getString("nombre"));				//SETEO NOMBRE DEL PRODUCTO
					productoActual.setStock(rs.getInt("stock"));					//SETEO STOCK ACTUAL DEL PRODUCTO
					productoActual.setStockMinimo(rs.getInt("stockMinimo"));		//SETEO STOCK MINIMO DEL PRODUCTO
					productoActual.setPresentacion(rs.getString("presentacion"));	//SETEO PRESENTACION DEL PRODUCTO
					productoActual.setPrecio(rs.getDouble("precio"));				//SETEO PRECIO DEL PRODUCTO
					
					//CREO LA CATEGORIA
					Categoria cate = new Categoria();
					cate.setIdCategoria(rs.getInt("c.idCategoria"));
					cate.setNombre(rs.getString("c.nombre"));
					
					//CREO LA SUBCATEGORIA
					Subcategoria subcat = new Subcategoria();	
					subcat.setIdSubCategoria(rs.getInt("idSubCategoria"));
					subcat.setCategoria(cate);  //SETEO LA CATEGORIA			
					
					productoActual.setSubcategoria(subcat);  					//SETEO LA SUBCATEGORIA DEL PRODUCTO
					
					productos.add(productoActual);								//AGREGO EL PRODUCTO AL ARRAYLIST
				}
				
			}
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(rs!=null)rs.close();
			if(stm!=null)stm.close();
			FactoryConnection.getinstancia().releaseConn();
		} 
		catch (Exception e) {
			throw e;
		}
		return productos;
	}
	
}
	

