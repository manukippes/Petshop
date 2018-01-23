package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Categoria;
import entidades.Cuotas;
import entidades.LineaVenta;
import entidades.MedioPago;
import entidades.Producto;
import entidades.Subcategoria;
import entidades.Tarjeta;
import entidades.Venta;
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
		
		System.out.println(pstm);
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
}
