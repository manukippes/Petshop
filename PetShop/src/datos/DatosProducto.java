package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import entidades.Producto;
import entidades.Subcategoria;
import logica.ControladorDeProducto;
import utilidades.ExcepcionEspecial;
import entidades.Categoria;

public class DatosProducto implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR PRODUCTO
	//						MODIFICAR PRODUCTO
	//						GET CATEGORIAS
	//						OBTENER PRODUCTOS DE UNA CATEGORIA
	//						OBTENER TODOS LOS PRODUCTOS
	//						COMPLETAR LOS DATOS DE UNA CATEGORIA 
	//						COMPLETAR LOS DATOS DE UNA SUBCATEGORIA 
	//						COMPLETAR LOS DATOS DE UN PRODUCTO
	//						OBTENER LAS SUBCATEGORIAS DE UNA CATEGORIA
	// 						OBTENER PRODUCTOS FILTRADOS SEGUN CRITERIOS
	
	public Boolean agregarProducto (Producto producto) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Boolean bandera = false;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO producto(idSubCategoria,nombre,stock,stockMinimo,presentacion,precio,imagen) VALUES (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, producto.getSubcategoria().getIdSubCategoria());
			pstm.setString(2, producto.getNombre());
			pstm.setInt(3, producto.getStock());
			pstm.setInt(4, producto.getStockMinimo());
			pstm.setString(5, producto.getPresentacion());
			pstm.setDouble(6, producto.getPrecio());
			pstm.setString(7, producto.getImagen());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			
			if(rs!=null && rs.next()){
				bandera = true;
				producto.setIdProducto(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bandera;
	}
	public Boolean modificarProducto(Producto producto) throws Exception
	{
		PreparedStatement pstm = null;
		Boolean bandera = false;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE producto SET idSubCategoria=?,nombre=?,stock=?,stockMinimo=?,presentacion=?,precio=?,imagen=? WHERE idProducto=?");
			pstm.setInt(1, producto.getSubcategoria().getIdSubCategoria());
			pstm.setString(2, producto.getNombre());
			pstm.setInt(3, producto.getStock());
			pstm.setInt(4, producto.getStockMinimo());
			pstm.setString(5, producto.getPresentacion());
			pstm.setDouble(6, producto.getPrecio());
			pstm.setString(7, producto.getImagen());
			pstm.setInt(8, producto.getIdProducto());
			
			if(pstm.executeUpdate()==1){
				bandera = true;
			}
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
		return bandera;
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
	public Categoria getCategoria(Categoria cate) throws Exception{   //ESTE METODO PODRIA ESTAR EN UNA BASE DE CATEGORIA
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Categoria categoriaActual= new Categoria();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM CATEGORIA WHERE idCategoria=?");
			pstm.setInt(1, cate.getIdCategoria());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					categoriaActual.setIdCategoria(rs.getInt("idCategoria"));		//SETEO ID CATEGORIA
					categoriaActual.setNombre(rs.getString("nombre"));				//SETEO NOMBRE DE LA CATEGORIA
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
		return categoriaActual;
	
	}
	public Subcategoria getSubcategoria(Subcategoria subcate) throws Exception{   //ESTE METODO PODRIA ESTAR EN UNA BASE DE CATEGORIA
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Subcategoria subcategoriaActual= new Subcategoria();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM SUBCATEGORIA WHERE idSubCategoria=?");
			pstm.setInt(1, subcate.getIdSubCategoria());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					subcategoriaActual.setIdSubCategoria(rs.getInt("idSubCategoria"));		//SETEO ID SUBCATEGORIA
					subcategoriaActual.setNombre(rs.getString("nombre"));				//SETEO NOMBRE DE LA SUBCATEGORIA
					
					DatosProducto baseProducto = new DatosProducto();
					Categoria cate = new Categoria();
					cate.setIdCategoria(rs.getInt("idCategoria"));
					cate = baseProducto.getCategoria(cate);
					
					subcategoriaActual.setCategoria(cate);								//SETEO LA CATEGORIA A LA QUE PERTENECE
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
		return subcategoriaActual;
	
	}
	public Boolean eliminarProducto(Producto producto) throws Exception
	{
		PreparedStatement pstm = null;
		Boolean bandera = false;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"delete from producto where idproducto=?");
			pstm.setInt(1, producto.getIdProducto());
			if (pstm.executeUpdate() ==1){
				bandera=true;
			}
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
		return bandera;
	}
	public Producto getProducto(Producto produ) throws Exception{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Producto productoActual= new Producto();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM PRODUCTO where idProducto =?");
			pstm.setInt(1, produ.getIdProducto());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					productoActual.setIdProducto(rs.getInt("idProducto"));			//SETEO ID PRODUCTO DEL PRODUCTO
					productoActual.setNombre(rs.getString("nombre"));				//SETEO NOMBRE DEL PRODUCTO
					productoActual.setStock(rs.getInt("stock"));					//SETEO STOCK ACTUAL DEL PRODUCTO
					productoActual.setStockMinimo(rs.getInt("stockMinimo"));		//SETEO STOCK MINIMO DEL PRODUCTO
					productoActual.setPresentacion(rs.getString("presentacion"));	//SETEO PRESENTACION DEL PRODUCTO
					productoActual.setPrecio(rs.getDouble("precio"));				//SETEO PRECIO DEL PRODUCTO
					productoActual.setImagen(rs.getString("imagen")); 				//SETEO LA RUTA DE LA IMAGEN DEL PRODUCTO
					
					//CREO LA SUBCATEGORIA
					Subcategoria subcat = new Subcategoria();	
					subcat.setIdSubCategoria(rs.getInt("idSubCategoria"));
					DatosProducto baseProducto = new DatosProducto();
					subcat = baseProducto.getSubcategoria(subcat);
					
					productoActual.setSubcategoria(subcat);  						//SETEO LA SUBCATEGORIA DEL PRODUCTO
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
		return productoActual;
	}
	public ArrayList<Subcategoria> getSubcategorias(Categoria categoria) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Subcategoria> subcategorias = new ArrayList<>();
		
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		Categoria categoriaActual = new Categoria();
		categoriaActual = ctrlProducto.getCategoria(categoria);					//COMPLETO LOS DATOS DE LA CATEGORIA
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM SUBCATEGORIA where idCategoria=?");
			pstm.setInt(1, categoria.getIdCategoria());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Subcategoria subcat = new Subcategoria();
					subcat.setIdSubCategoria(rs.getInt("idSubCategoria"));			//SETEO ID DE SUBCATEGORIA
					subcat.setNombre(rs.getString("nombre"));						//SETEO NOMBRE DE LA SUBCATEGORIA
									
					subcat.setCategoria(categoriaActual);									//SETEO LA CATEGORIA A LA QUE PERTENECE					
					subcategorias.add(subcat);
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
		return subcategorias;
	}

	 public ArrayList<Producto> getProductos(Hashtable<String, String> parametros) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Producto> productos= new ArrayList<Producto>();
		int i = 0;
		ArrayList<String> campos = new ArrayList();
		
		try {
			//IDPRODUCTO
			String sql="select * from producto";
			if(!parametros.get("idProducto").equals("")){		//Si viene id Producto lo agrego como primer filtro
				sql += " Where idProducto = ?";
				campos.add("idProducto");
				i++;
				}
			//NOMBRE
			if(!parametros.get("nombre").equals("")){ //si viene nombre lo agrego como primer filtro
				if (i==0){
					sql += " Where nombre like ?";
					campos.add("nombre");
					i++;
				}else{					//si ya hay un filtro anterior agrego la condicion nombre
					sql += " and nombre like ?";
					campos.add("nombre");
					i++;
					}
				}
			//PRESENTACION
			if(!parametros.get("presentacion").equals("")){ 
				if (i==0){
					sql += " Where presentacion like ?";
					campos.add("presentacion");
					i++;
				}else{				
					sql += " and presentacion like ?";
					campos.add("presentacion");
					i++;
					}
				}
			//PRECIO DESDE
			if(!parametros.get("precioDesde").equals("")){ 
				if (i==0){
					sql += " Where precio > ?";
					campos.add("precioDesde");
					i++;
				}else{					
					sql += " and precio > ?";
					campos.add("precioDesde");
					i++;
					}
				}
			//PRECIO HASTA
			if(!parametros.get("precioHasta").equals("")){ 
				if (i==0){
					sql += " Where precio < ?";
					campos.add("precioHasta");
					i++;
				}else{					
					sql += " and precio < ?";
					campos.add("precioHasta");
					i++;
					}
				}
			//STOCK DESDE
			if(!parametros.get("stockDesde").equals("")){ 
				if (i==0){
					sql += " Where stock > ?";
					campos.add("stockDesde");
					i++;
				}else{					
					sql += " and stock > ?";
					campos.add("stockDesde");
					i++;
					}
				}
			//STOCK HASTA
			if(!parametros.get("stockHasta").equals("")){ 
				if (i==0){
					sql += " Where stock < ?";
					campos.add("stockHasta");
					i++;
				}else{					
					sql += " and stock < ?";
					campos.add("stockHasta");
					i++;
					}
				}

			//CREO UN PREPARESTATEMENT
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					sql);
			
			//SETEO LOS VALORES DE ACUERDO A CUALES SE CARGARON EN EL ARRAYLIST CAMPOS
			for (int j=0;j<i;j++){
				switch (campos.get(j)){
				case "idProducto":
					pstm.setInt(j+1, Integer.parseInt(parametros.get(campos.get(j))));
					break;
				case "precioDesde":
				case "precioHasta":
					pstm.setDouble(j+1, Double.parseDouble(parametros.get(campos.get(j))));
					break;
				case "stockDesde":
				case "stockHasta":
					pstm.setInt(j+1, Integer.parseInt(parametros.get(campos.get(j))));
					break;
				default:
					String like = "%"+parametros.get(campos.get(j))+"%";
					pstm.setString(j+1, like);
					break;		
				}
			}
			//EJECUTO LA CONSULTA
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
}
	

