package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entidades.Producto;
import entidades.Subcategoria;
import logica.ControladorDeProducto;

/**
 * Servlet implementation class ConfirmarAltaProducto
 */
@WebServlet({ "/ConfirmarAltaProducto", "/Confirmaraltaproducto", "/confirmaraltaproducto" })
public class ConfirmarAltaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarAltaProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        //response.setContentType("text/html;charset=UTF-8");
        
        FileItemFactory file_factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(file_factory);
		
		ArrayList<String> campos = new ArrayList<>();
		ArrayList<String> imgs = new ArrayList<>();
		
		Producto productoActual = new Producto();								//CREO UNA INSTANCIA DE PRODUCTO
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();		//CREO UNA INSTANCIA DE CONTROLADOR DE PRODUCTO
		Subcategoria subcate = new Subcategoria();								//CREO UNA INSTANCIA DE SUBCATEGORIA (DE PRODUCTO)
		
		//OBTENGO TODOS LOS PARAMETROS DEL FORM ENVIADO
		
		try{ 			
			List items = sfu.parseRequest(request);
			
			for (int i=0; i<items.size();i++){
				FileItem item = (FileItem) items.get(i);
				if (!item.isFormField()){
					File archivo = new File("C:\\Users\\hugos\\git\\Petshop\\PetShop\\WebContent\\imgs\\productos\\"+item.getName());
					item.write(archivo);
					imgs.add("imgs/productos/"+item.getName());
				}else{
					campos.add(item.getString());
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}				
		try{
			subcate.setIdSubCategoria(Integer.parseInt(campos.get(2)));			//SETEO LA ID DE LA SUBCATEGORIA
			subcate = ctrlProducto.getSubcategoria(subcate);					//COMPLETO LOS DATOS DE LA SUBCATEGORIA
			
			productoActual.setNombre(campos.get(3));
			productoActual.setPresentacion(campos.get(4));
			productoActual.setPrecio(Double.parseDouble(campos.get(5)));
			productoActual.setStock(Integer.parseInt(campos.get(6)));
			productoActual.setStockMinimo(Integer.parseInt(campos.get(7)));
			productoActual.setSubcategoria(subcate);							//SETEO LA SUBCATEGORIA A LA QUE PERTENECE EL PRODUCTO
			
			if(imgs.size() == 1){
				productoActual.setImagen(imgs.get(0));							//SI VIENE IMAGEN, LA AGREGO AL PRODUCTO
			}else{					
				Producto temp = new Producto();									//SINO RECUPERO LA QUE ESTABA EN LA BD
				temp.setIdProducto(Integer.parseInt(campos.get(9)));
				temp= ctrlProducto.getProducto(temp);
				productoActual.setImagen(temp.getImagen()); 						//SETEO LA IMAGEN QUE EL PRODUCTO TENIA ALMACENADA
			}
			
			if (campos.get(0).equals("modificacion")){									//DETERMINO SI ES ALTA O MODIFICACION
				productoActual.setIdProducto(Integer.parseInt(campos.get(9)));	//LE SETEO LA ID QUE VIENE COMO PARAMETRO AL PRODUCTO
				if(ctrlProducto.modificarProducto(productoActual)){
					response.getWriter().println("Producto modificado exitosamente");
				}else{
					response.getWriter().println("Error al modificar el producto");
				};
				
			}else{
				if(ctrlProducto.agregarProducto(productoActual)){
					response.getWriter().println("Producto creado exitosamente");
				}else{
					response.getWriter().println("Error en la creacion del producto");
				};			
			};
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return;
	}
	
}
