package logica;

import java.util.ArrayList;

import datos.DatosProducto;
import entidades.Categoria;
import entidades.Producto;
import utilidades.ExcepcionEspecial;

public class ControladorDeProducto {
	
	//METODOS IMPLEMENTADOS:
		//	Devolver todas las categorias
		//	Devolver todos los productos de una categoria

	public ArrayList<Categoria> getCategorias()throws ExcepcionEspecial, Exception{
		
		DatosProducto baseProducto = new DatosProducto();
		ArrayList <Categoria> categorias = baseProducto.getCategorias();
		return categorias;
		
	}
	
	public ArrayList<Producto> getProductos(Categoria categoria)throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		ArrayList <Producto> productos = baseProducto.getProductos(categoria);		
		return productos;
	}
	
	public ArrayList<Producto> getProductos()throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		ArrayList <Producto> productos = baseProducto.getProductos();		
		return productos;
	}
}
