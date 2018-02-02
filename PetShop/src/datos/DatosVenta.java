package datos;

import java.io.Serializable;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import entidades.Categoria;
import entidades.Cuotas;
import entidades.LineaVenta;
import entidades.MedioPago;
import entidades.Producto;
import entidades.Subcategoria;
import entidades.Tarjeta;
import entidades.TipoMascotaServicio;
import entidades.Usuario;
import entidades.Venta;
import logica.ControladorDeProducto;
import logica.ControladorDeTipoMascota;
import logica.ControladorDeUsuario;
import logica.ControladorDeVenta;
import utilidades.ExcepcionEspecial;

public class DatosVenta implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR VENTA
	//						MODIFICAR VENTA
	//						GET TARJETA
	//						GET MEDIO DE PAGO (COMPLETAR DATOS)
	//						GET MEDIOS DE PAGO
	// 						GET CUOTAS (COMPLETAR DATOS)
	//						GET FECHA
	//						AGREGAR LINEA DE VENTA
	// 						OBTENER VENTAS FILTRADAS CON PARAMETROS
	//						GET VENTA (COMPLETAR DATOS)
	//						GET LINEAS DE UNA VENTA
	
	public int agregarVenta (Venta venta) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int idVenta = 0;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO venta(idUsuario,idMedioPago,total,estado,fecha,datosOpcionales,envioDom,domicilio) VALUES (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			if(venta.getUsuario().getIdUsuario()==0){
				pstm.setNull(1, java.sql.Types.INTEGER);
			}else{
				pstm.setInt(1, venta.getUsuario().getIdUsuario());
			}
			pstm.setInt(2, venta.getMedioPago().getIdMedioPago());
			pstm.setDouble(3, venta.getTotal());
			pstm.setString(4, venta.getEstado());
			pstm.setDate(5, venta.getFecha());
			pstm.setString(6, venta.getDatosOpcionales());
			pstm.setBoolean(7, venta.getEnvioDom());
			pstm.setString(8, venta.getDomicilio());
			pstm.executeUpdate();
						
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				venta.setIdVenta(rs.getInt(1));
				idVenta = rs.getInt(1);
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
		return idVenta;
	}
	public Boolean modificarVenta(Venta venta) throws Exception
	{
		PreparedStatement pstm = null;
		Boolean bandera=false;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE venta SET idUsuario=?,idMedioPago=?,total=?,estado=?,fecha=?,datosOpcionales=?,envioDom=?,domicilio=?,idTarjeta=?,idCuotas=? WHERE idVenta=?");	
			
			if(venta.getUsuario().getIdUsuario()==0){
				pstm.setNull(1, java.sql.Types.INTEGER);
			}else{
				pstm.setInt(1, venta.getUsuario().getIdUsuario());
			}
			pstm.setInt(2, venta.getMedioPago().getIdMedioPago());
			pstm.setDouble(3, venta.getTotal());
			pstm.setString(4, venta.getEstado());
			pstm.setDate(5, venta.getFecha());
			pstm.setString(6, venta.getDatosOpcionales());
			pstm.setBoolean(7, venta.getEnvioDom());
			pstm.setString(8, venta.getDomicilio());
			
			if( venta.getTarjeta().getIdTarjeta()==0){
				pstm.setNull(9, java.sql.Types.INTEGER);
			}else{
				pstm.setInt(9, venta.getTarjeta().getIdTarjeta());
			}
						
			if(venta.getCuotas().getIdCuota()==0){
				pstm.setNull(10, java.sql.Types.INTEGER);
			}else{
				pstm.setInt(10, venta.getCuotas().getIdCuota());
			}
			pstm.setInt(11, venta.getIdVenta());
			pstm.executeUpdate();
			bandera=true;
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
	public Tarjeta getTarjeta(Tarjeta tarjeta)throws Exception{
			
			PreparedStatement pstm = null;
			ResultSet rs = null;
			Tarjeta tarjetaActual = new Tarjeta();
			ControladorDeVenta ctrlVenta = new ControladorDeVenta();
			
			try {
				pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
						"SELECT * FROM Tarjeta where idTarjeta =?");
				pstm.setInt(1, tarjeta.getIdTarjeta());
				rs=pstm.executeQuery();
				
				if(rs!=null)
				{
					while(rs.next())
					{
						tarjetaActual.setIdTarjeta(rs.getInt("idTarjeta"));				//SETEO ID TARJETA
						tarjetaActual.setNombre(rs.getString("nombre"));				//SETEO NOMBRE DE LA TARJETA
						MedioPago medioPago = new MedioPago();
						medioPago.setIdMedioPago(rs.getInt("idMedioPago"));
						medioPago = ctrlVenta.getMedioPago(medioPago);
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
			return tarjetaActual;
		
	}
	
	public MedioPago getMedioPago(MedioPago medioPago)throws Exception{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MedioPago medioPagoActual = new MedioPago();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM medio_pago where idMedioPago =?");
			pstm.setInt(1, medioPago.getIdMedioPago());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					medioPagoActual.setIdMedioPago(rs.getInt("idMedioPago"));
					medioPagoActual.setTipo(rs.getString("tipo"));
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
		return medioPagoActual;
	
}
	
	public ArrayList<MedioPago> getMediosPago() throws ExcepcionEspecial, Exception{
		
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<MedioPago> mediosPago = new ArrayList<MedioPago>();
		
		try {
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("SELECT * FROM medio_pago");
					
			if(rs!=null)
			{
				while(rs.next())
				{
					MedioPago medioPagoActual=new MedioPago();
					medioPagoActual.setIdMedioPago(rs.getInt("idMedioPago"));
					medioPagoActual.setTipo(rs.getString("tipo"));
					
					mediosPago.add(medioPagoActual);
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
		
		return mediosPago;
	}

public ArrayList<Tarjeta> getTarjetas(MedioPago medioPago) throws Exception{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Tarjeta> tarjetas = new ArrayList<>();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM tarjeta where idMedioPago =?");
			pstm.setInt(1, medioPago.getIdMedioPago());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Tarjeta tarjetaActual = new Tarjeta();
					tarjetaActual.setIdTarjeta(rs.getInt("idTarjeta"));
					tarjetaActual.setNombre(rs.getString("nombre"));
					tarjetaActual.setMedioPago(medioPago);
					
					tarjetas.add(tarjetaActual);
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
		return tarjetas;
	
	}
public ArrayList<Cuotas> getCuotas(Tarjeta tarjeta) throws Exception{
	
	PreparedStatement pstm = null;
	ResultSet rs = null;
	ArrayList<Cuotas> cuotasTarjeta = new ArrayList<>();
	
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
				"SELECT * FROM cuotas where idTarjeta =?");
		pstm.setInt(1, tarjeta.getIdTarjeta());
		rs=pstm.executeQuery();
		
		if(rs!=null)
		{
			while(rs.next())
			{
				Cuotas cuotaActual = new Cuotas();
				cuotaActual.setIdCuota(rs.getInt("idCuota"));
				cuotaActual.setCantCuotas(rs.getInt("cantCuotas"));
				cuotaActual.setRecargo(rs.getDouble("recargo"));
				cuotaActual.setTarjeta(tarjeta);
				
				cuotasTarjeta.add(cuotaActual);
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
	return cuotasTarjeta;

	}

public Cuotas getCuotas(Cuotas cuotas)throws Exception{
	
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Cuotas cuotasActual = new Cuotas();
	Tarjeta tarjetaActual = new Tarjeta();
	ControladorDeVenta ctrlVenta = new ControladorDeVenta();
	
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
				"SELECT * FROM cuotas where idCuota =?");
		pstm.setInt(1, cuotas.getIdCuota());
		rs=pstm.executeQuery();
		
		if(rs!=null)
		{
			while(rs.next())
			{
				cuotasActual.setIdCuota(rs.getInt("idCuota"));
				cuotasActual.setCantCuotas(rs.getInt("cantCuotas"));
				cuotasActual.setRecargo(rs.getDouble("recargo"));
				
				tarjetaActual.setIdTarjeta(rs.getInt("idTarjeta"));
				tarjetaActual = ctrlVenta.getTarjeta(tarjetaActual);
				
				cuotasActual.setTarjeta(tarjetaActual);
				
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
	return cuotasActual;

}
public String getFechaActual() throws Exception
{
	Statement stm=null;
	ResultSet rs=null;
	String fechaActual="";
	
	try {
		stm = FactoryConnection.getinstancia().getConn().createStatement();
		rs = stm.executeQuery("SELECT CURRENT_TIMESTAMP");
				
		if(rs!=null){
			while(rs.next()){
				fechaActual=(rs.getString("CURRENT_TIMESTAMP"));
			}
		}
		
		
	} catch (SQLException e) {
		throw e;
	}
	
	try {
		if(rs!=null)rs.close();
		if(stm!=null)stm.close();
		FactoryConnection.getinstancia().releaseConn();
	} catch (SQLException e) {
		throw e;
	}
	
	return fechaActual;
	}

public Boolean agregarLineaVenta (LineaVenta lv) throws Exception
	{
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Boolean bandera = false;
	
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
				"INSERT INTO linea_venta(idVenta,idProducto,idTMascServ,tipoLineaVenta,precioUnitario,cantidad) VALUES (?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);
		
		pstm.setInt(1, lv.getVenta().getIdVenta());
		pstm.setInt(2, lv.getProducto().getIdProducto());
		int idTmascServ = lv.getTipoMascotaServicio().getIdTMascServ();
		if(idTmascServ==0){
			pstm.setNull(3, java.sql.Types.INTEGER);
		}else{
			pstm.setInt(3, lv.getTipoMascotaServicio().getIdTMascServ());
		}
		pstm.setString(4, lv.getTipoLineaVta());
		pstm.setDouble(5, lv.getPrecioUnitario());
		pstm.setInt(6, lv.getCantidad());

		pstm.executeUpdate();
		
		
		rs=pstm.getGeneratedKeys();
		if(rs!=null && rs.next()){
		lv.setIdLineaVenta(rs.getInt(1));
		bandera=true;
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
	return bandera;
	}
public ArrayList<Venta> getVentas(Hashtable<String, String> parametros) throws Exception{
	PreparedStatement pstm = null;
	ResultSet rs = null;
	ArrayList<Venta> ventas= new ArrayList<Venta>();
	ControladorDeVenta ctrlVenta = new ControladorDeVenta();
	int i = 0;
	ArrayList<String> campos = new ArrayList<>();
	
	try {
		//FECHA DESDE
		String sql="SELECT * FROM venta ve INNER JOIN linea_venta lv ON ve.idVenta = lv.idVenta INNER JOIN producto pr ON lv.idProducto = pr.idProducto INNER JOIN usuario us ON ve.idUsuario = us.idUsuario";
		if(!parametros.get("fechaDesde").equals("")){		//Si viene fecha desde la agrego como primer filtro
			sql += " Where fecha >= ?";
			campos.add("fechaDesde");
			i++;
			}
		//FECHA HASTA
		if(!parametros.get("fechaHasta").equals("")){ //si viene nombre lo agrego como primer filtro
			if (i==0){
				sql += " Where fecha <= ?";
				campos.add("fechaHasta");
				i++;
			}else{					//si ya hay un filtro anterior agrego la condicion nombre
				sql += " and fecha <= ?";
				campos.add("fechaHasta");
				i++;
				}
			}
		//PRODUCTO
		if(!parametros.get("producto").equals("")){ 
			if (i==0){
				sql += " Where pr.nombre like ?";
				campos.add("producto");
				i++;
			}else{				
				sql += " and pr.nombre like ?";
				campos.add("producto");
				i++;
				}
			}
		//TOTAL DESDE
		if(!parametros.get("importeDesde").equals("")){ 
			if (i==0){
				sql += " Where total >= ?";
				campos.add("importeDesde");
				i++;
			}else{					
				sql += " and total >= ?";
				campos.add("importeDesde");
				i++;
				}
			}
		//Medio de Pago
		if(!parametros.get("medioPago").equals("")){ 
			if (i==0){
				sql += " Where idMedioPago = ?";
				campos.add("medioPago");
				i++;
			}else{					
				sql += " and idMedioPago = ?";
				campos.add("medioPago");
				i++;
				}
			}
		//CLIENTE
		if(!parametros.get("cliente").equals("")){ 
			if (i==0){
				sql += " Where us.idUsuario like ? or us.nombre like ? or us.apellido like ?";
				campos.add("cliente");
				i++;
			}else{					
				sql += " and (us.idUsuario like ? or us.nombre like ? or us.apellido like ?)";
				campos.add("cliente");
				i++;
				}
			}
		//Con Retiro
		if(!(parametros.get("conRetiro").equals(""))){ 
			if (i==0){
				sql += " Where envioDom = ?";
				campos.add("conRetiro");
				i++;
			}else{					
				sql += " and envioDom = ?";
				campos.add("conRetiro");
				i++;
				}
			}

		//CREO UN PREPARESTATEMENT
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
				sql);
		
		//SETEO LOS VALORES DE ACUERDO A CUALES SE CARGARON EN EL ARRAYLIST CAMPOS
		for (int j=0;j<i;j++){
			switch (campos.get(j)){
			case "fechaDesde":
			case "fechaHasta":
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaDate = sdf.parse(parametros.get(campos.get(j)));
				java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
				pstm.setDate(j+1, sqlDate);
				break;
			case "importeDesde":
				pstm.setDouble(j+1, Double.parseDouble(parametros.get(campos.get(j))));
				break;
			case "medioPago":
				pstm.setInt(j+1, Integer.parseInt(parametros.get(campos.get(j))));
				break;
			case "cliente":
				String likes = "%"+parametros.get(campos.get(j))+"%";
				pstm.setString(j+1, likes);
				pstm.setString(j+2, likes);
				pstm.setString(j+3, likes);
				j++;
				j++;
				break;
			case "conRetiro":
				if (parametros.get(campos.get(j)).equals("Si")){
					pstm.setBoolean(j+1, true);
				}else{
					pstm.setBoolean(j+1, false);
				}
				break;
			default:
				String like = "%"+parametros.get(campos.get(j))+"%";
				pstm.setString(j+1, like);
				break;		
			}
		}
		//EJECUTO LA CONSULTA
		System.out.println(pstm);
		rs=pstm.executeQuery();
		
		if(rs!=null)
		{
			while(rs.next())
			{
				Venta ventaActual = new Venta();
				ventaActual.setIdVenta(rs.getInt("idVenta"));
				
				ventaActual = ctrlVenta.getVenta(ventaActual);
	
				ventas.add(ventaActual);		
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
			return ventas;
	}
	
	public Venta getVenta(Venta venta) throws Exception{
			
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Venta ventaActual = new Venta();
		ControladorDeVenta ctrlVenta = new ControladorDeVenta();
		ControladorDeUsuario ctrlUsuario = new ControladorDeUsuario();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM venta where idVenta =?");
			pstm.setInt(1, venta.getIdVenta());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					ventaActual.setIdVenta(venta.getIdVenta());
					ventaActual.setTotal(rs.getDouble("total"));
					ventaActual.setEstado(rs.getString("estado"));
					ventaActual.setFecha(rs.getDate("fecha"));
					ventaActual.setDatosOpcionales(rs.getString("datosOpcionales"));
					ventaActual.setEnvioDom(rs.getBoolean("envioDom"));
					ventaActual.setDomicilio(rs.getString("domicilio"));
					
					Usuario usuarioActual = new Usuario();
					usuarioActual.setIdUsuario(rs.getInt("idUsuario"));
					usuarioActual = ctrlUsuario.getUsuario(usuarioActual);
					ventaActual.setUsuario(usuarioActual);
					
					MedioPago medioPagoActual = new MedioPago();
					medioPagoActual.setIdMedioPago(rs.getInt("idMedioPago"));
					medioPagoActual = ctrlVenta.getMedioPago(medioPagoActual);
					ventaActual.setMedioPago(medioPagoActual);
					
					Tarjeta tarjetaActual = new Tarjeta();
					tarjetaActual.setIdTarjeta(rs.getInt("idTarjeta"));
					tarjetaActual = ctrlVenta.getTarjeta(tarjetaActual);
					ventaActual.setTarjeta(tarjetaActual);
					
					Cuotas cuotasActual = new Cuotas();
					cuotasActual.setIdCuota(rs.getInt("idCoutas"));
					cuotasActual = ctrlVenta.getCuotas(cuotasActual);
					ventaActual.setCuotas(cuotasActual);
					
					ArrayList<LineaVenta> lineasVenta = new ArrayList<LineaVenta>();
					lineasVenta = ctrlVenta.getLineas(ventaActual);
					
					ventaActual.setLineas(lineasVenta);
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
		return ventaActual;
		
	}
	
	public ArrayList<LineaVenta> getLineasVenta(Venta venta) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<LineaVenta> lineasVenta= new ArrayList<LineaVenta>();
		ControladorDeProducto ctrlProducto = new ControladorDeProducto();
		ControladorDeTipoMascota ctrlTipoMascota = new ControladorDeTipoMascota();
		
		
		try {
			//CREO UN PREPARESTATEMENT
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"select * from linea_venta where idVenta = ?");
			
					pstm.setInt(1,venta.getIdVenta());
					
	
			//EJECUTO LA CONSULTA
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					LineaVenta lineaVentaActual= new LineaVenta();
					lineaVentaActual.setIdLineaVenta(rs.getInt("idLineaVenta"));
					lineaVentaActual.setTipoLineaVta(rs.getString("tipoLineaVenta"));
					lineaVentaActual.setPrecioUnitario(rs.getDouble("precioUnitario"));
					lineaVentaActual.setCantidad(rs.getInt("cantidad"));
					lineaVentaActual.setVenta(venta);
					
					Producto productoActual = new Producto();
					productoActual.setIdProducto(rs.getInt("idProducto"));
					productoActual = ctrlProducto.getProducto(productoActual);
					lineaVentaActual.setProducto(productoActual);
					
					TipoMascotaServicio tMascServ = new TipoMascotaServicio();
					tMascServ.setIdTMascServ(rs.getInt("idTMascServ"));
					tMascServ = ctrlTipoMascota.getTipoMascotaServicio(tMascServ);
					lineaVentaActual.setTipoMascotaServicio(tMascServ);
					
					lineasVenta.add(lineaVentaActual);		
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
		return lineasVenta;
	}
	
}
