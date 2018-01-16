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
		//	Crear tabla html de productos
		//  Obtener productos con un parametro string

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
	
	public Categoria getCategoria (Categoria cate) throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.getCategoria(cate);
	}
	
	public Subcategoria getSubcategoria (Subcategoria subcate) throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.getSubcategoria(subcate);
	}
	public boolean agregarProducto(Producto prod) throws Exception{
		Boolean bandera;
		DatosProducto baseProducto = new DatosProducto();
		bandera = baseProducto.agregarProducto(prod);
		System.out.println(bandera);
		return bandera;
		
	}
	public boolean eliminarProducto(Producto prod) throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.eliminarProducto(prod);
	}
	public Producto getProducto(Producto produ)throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.getProducto(produ);
	}
	public ArrayList<Subcategoria> getSubcategorias(Categoria categoria) throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.getSubcategorias(categoria);
	}
	public Boolean modificarProducto(Producto producto)throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.modificarProducto(producto);
	}
	public String getHtml(){
		ArrayList<Producto> productos = new ArrayList<>();
		String codigoHtml = "";
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		try {
			productos = ctrlProducto.getProductos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Producto produ : productos){
		codigoHtml += "<tr>"
						+ "<td id='idProducto'>"+produ.getIdProducto()+"</td>"
						+ "<td id='nombreProducto'>"+produ.getNombre()+"</td>"
						+ "<td id='presentacionProducto'>"+produ.getPresentacion()+"</td>"
						+ "<td>"+produ.getPrecio()+"</td>"
						+ "<td>"+produ.getStock()+"</td>"
						+ "<td>"
							+ "<div class='input-group'>"
								+ "<a class='btn btn-danger' id='btnEliminarProducto' href='\'>Eliminar</a>"
								+ "<a class='btn btn-primary' id='btnModificarProducto'  href='ModificarProducto?id="+produ.getIdProducto()+"'>Modificar</a>"
							+ "</div>"
						+ "</td>"
					+ "</tr> ";
		}
																		
		return codigoHtml;
	}	
	public ArrayList<Producto> getProductos(Hashtable<String, String> parametros)throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.getProductos(parametros);
	}
	
	public ArrayList<Producto> getProductos(String inputProducto)throws Exception{
		DatosProducto baseProducto = new DatosProducto();
		return baseProducto.getProductos(inputProducto);
	}
}



