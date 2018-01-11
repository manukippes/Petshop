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
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		
		
		Subcategoria subcate = new Subcategoria();
		subcate.setIdSubCategoria(Integer.parseInt(campos.get(1)));
		
		try{

			subcate = ctrlProducto.getSubcategoria(subcate);
		
			Producto productoNuevo = new Producto();
			productoNuevo.setNombre(campos.get(2));
			productoNuevo.setPresentacion(campos.get(3));
			productoNuevo.setPrecio(Double.parseDouble(campos.get(4)));
			productoNuevo.setStock(Integer.parseInt(campos.get(5)));
			productoNuevo.setStockMinimo(Integer.parseInt(campos.get(6)));
			productoNuevo.setSubcategoria(subcate);
			productoNuevo.setImagen(imgs.get(0));
			
			if (campos.size()==8){		//SI HAY 7 CAMPOS ES PORQUE HAY UN ID
				productoNuevo.setIdProducto(Integer.parseInt(campos.get(7)));
				if(ctrlProducto.modificarProducto(productoNuevo)){
					response.getWriter().println("Producto modificado exitosamente");
				}else{
					response.getWriter().println("Error al modificar el producto");
				};
			}else{
				if(ctrlProducto.agregarProducto(productoNuevo)){
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
