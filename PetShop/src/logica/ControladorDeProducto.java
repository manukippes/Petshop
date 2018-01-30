package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import datos.DatosProducto;
import entidades.Categoria;
import entidades.Producto;
import entidades.Subcategoria;
import utilidades.ExcepcionEspecial;

public class ControladorDeProducto implements Serializable{
	private DatosProducto baseProducto = new DatosProducto();
	//METODOS IMPLEMENTADOS:
		//	Devolver todas las categorias
		//	Devolver todos los productos de una categoria
		//	Devolver una categoria (No se implementa controlador de categoria)
		//	Devolver una subcategoria (No se implementa controlador de subcategoria)
		//	Agregar un Producto
		//	Eliminar un producto
		// 	Devolver un producto
		// 	Devolver todas las subcategorias de una categoria
		//	Modificar un producto
		//  Obtener productos con un parametro string
		//	Obtener productos de una subcategoria con o sin stock

	public ArrayList<Categoria> getCategorias()throws ExcepcionEspecial, Exception{
		
		ArrayList <Categoria> categorias = baseProducto.getCategorias();
		return categorias;
		
	}
	
	public ArrayList<Producto> getProductos(Categoria categoria)throws Exception{

		ArrayList <Producto> productos = baseProducto.getProductos(categoria);		
		return productos;
	}
	
	public ArrayList<Producto> getProductos()throws Exception{

		ArrayList <Producto> productos = baseProducto.getProductos();		
		return productos;
	}
	
	public Categoria getCategoria (Categoria cate) throws Exception{

		return baseProducto.getCategoria(cate);
	}
	
	public Subcategoria getSubcategoria (Subcategoria subcate) throws Exception{

		return baseProducto.getSubcategoria(subcate);
	}
	public boolean agregarProducto(Producto prod) throws Exception{
		Boolean bandera;

		bandera = baseProducto.agregarProducto(prod);
		System.out.println(bandera);
		return bandera;
		
	}
	public boolean eliminarProducto(Producto prod) throws Exception{

		return baseProducto.eliminarProducto(prod);
	}
	public Producto getProducto(Producto produ)throws Exception{

		return baseProducto.getProducto(produ);
	}
	public ArrayList<Subcategoria> getSubcategorias(Categoria categoria) throws Exception{

		return baseProducto.getSubcategorias(categoria);
	}
	public Boolean modificarProducto(Producto producto)throws Exception{

		return baseProducto.modificarProducto(producto);
	}
	
	public ArrayList<Producto> getProductos(Hashtable<String, String> parametros)throws Exception{

		return baseProducto.getProductos(parametros);
	}
	
	public ArrayList<Producto> getProductos(String inputProducto)throws Exception{

		return baseProducto.getProductos(inputProducto);
	}
	
	public ArrayList<Producto> getProductos(Subcategoria subcat, Boolean soloStock) throws Exception{
		return baseProducto.getProductos(subcat,soloStock);
	}
}



