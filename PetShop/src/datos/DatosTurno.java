package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import entidades.Categoria;
import entidades.Mascota;
import entidades.Producto;
import entidades.Servicio;
import entidades.Subcategoria;
import entidades.Turno;
import logica.ControladorDeMascota;
import logica.ControladorDeServicio;
import logica.ControladorDeTurno;

public class DatosTurno implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR TURNO
	//						MODIFICAR TURNO
	//						GET TURNO (COMPLETAR DATOS)
	//						GET HORARIOS DISPONIBLES
	//						GET TURNOS FILTRADOS SEGUN CRITERIOS
	//						CANCELAR TURNO
	
	public Boolean agregarTurno (Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Boolean bandera=false;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO turno(idMascota,idServicio,fecha,hora,repetir,retiroDom,estado,observaciones) VALUES (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, turno.getMascota().getIdMascota());
			pstm.setInt(2, turno.getServicio().getIdServicio());
			pstm.setDate(3, turno.getFecha());
			pstm.setTime(4, turno.getHora());
			pstm.setString(5, turno.getRepetir());
			pstm.setBoolean(6, turno.getRetiroDom());
			pstm.setString(7, turno.getEstado());
			pstm.setString(8, turno.getObservaciones());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				turno.setIdTurno(rs.getInt(1));
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
	public Boolean modificarTurno(Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
		Boolean bandera = false;
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE turno SET idMascota=?,idServicio=?,fecha=?,hora=?,repetir=?,retiroDom=?,estado=?, observaciones=? WHERE idTurno=?");
			pstm.setInt(1, turno.getMascota().getIdMascota());
			pstm.setInt(2, turno.getServicio().getIdServicio());
			pstm.setDate(3, turno.getFecha());
			pstm.setTime(4, turno.getHora());
			pstm.setString(5, turno.getRepetir());
			pstm.setBoolean(6, turno.getRetiroDom());
			pstm.setString(7, turno.getEstado());
			pstm.setString(8, turno.getObservaciones());
			pstm.setInt(9, turno.getIdTurno());
			pstm.executeUpdate();
			bandera = true;
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
		}return bandera;
		
	}
	
	public Turno getTurno(Turno turno) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Turno turnoActual= new Turno();
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();
		ControladorDeServicio ctrlServicio = new ControladorDeServicio();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM turno where idTurno = ?");
			pstm.setInt(1, turno.getIdTurno());
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					turnoActual.setIdTurno(rs.getInt("idTurno"));
					
					turnoActual.setFecha(rs.getDate("fecha"));
					
					turnoActual.setHora(rs.getTime("hora"));
					
					turnoActual.setEstado(rs.getString("estado"));
					
					turnoActual.setRepetir(rs.getString("repetir"));
					
					turnoActual.setRetiroDom(rs.getBoolean("retiroDom"));
					
					Mascota mascota = new Mascota();									//MASCOTA
					mascota.setIdMascota(rs.getInt("idMascota"));
					mascota = ctrlMascota.getMascota(mascota);
					turnoActual.setMascota(mascota);
					
					Servicio servicio = new Servicio();									//SERVICIO
					servicio.setIdServicio(rs.getInt("idServicio"));
					servicio = ctrlServicio.getServicio(servicio);
					turnoActual.setServicio(servicio);
					
					turnoActual.setObservaciones(rs.getString("observaciones"));		
											
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
		return turnoActual;
	
	}
	public ArrayList<Time> getHorariosDisponibles(String fechaSeleccionada)throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Time> horariosDisponibles = new ArrayList<Time>();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareCall(
					"call getHorariosDisponibles(?)");
			pstm.setString(1, fechaSeleccionada);
		
			rs=pstm.executeQuery();
			
			if(rs!=null)
			{
				while(rs.next())
				{
					Time horarioActual = rs.getTime("horario");
					horariosDisponibles.add(horarioActual);
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
		return horariosDisponibles;
	}
	
	public ArrayList<Turno> getTurnos(Hashtable<String, String> parametros) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		int i = 0;
		ArrayList<String> campos = new ArrayList();
		ControladorDeTurno ctrlTurno = new ControladorDeTurno();
		
		try {
			//FECHA DESDE
			String sql="SELECT idTurno, fecha, tu.estado estado, us.apellido, ma.nombre mascota, us.direccion, retiroDom, se.tipo FROM turno tu INNER JOIN mascota ma ON tu.idMascota = ma.idMascota INNER JOIN usuario us ON us.idUsuario = ma.idUsuario INNER JOIN tipo_mascota tm ON ma.idTipoMascota = tm.idTipoMascota INNER JOIN servicio se ON se.idServicio = tu.idServicio "; 
			if(!parametros.get("fechaDesde").equals("")){		//Si viene fechaDesde lo agrego como primer filtro
				sql += " Where fecha >= ?";
				campos.add("fechaDesde");
				i++;
				}
			//FECHA HASTA
			if(!parametros.get("fechaHasta").equals("")){ //si viene fechaHasta lo agrego como primer filtro
				if (i==0){
					sql += " Where fecha <= ?";								//TIPO DATE
					campos.add("fechaHasta");
					i++;
				}else{					//si ya hay un filtro anterior agrego la condicion fechaHasta
					sql += " and fecha <= ?";
					campos.add("fechaHasta");
					i++;
					}
				}
			//ESTADO
			if(!parametros.get("estado").equals("")){ 						//TIPO STRING
				if (i==0){
					sql += " Where tu.estado like ?";
					campos.add("estado");
					i++;
				}else{				
					sql += " and tu.estado like ?";
					campos.add("estado");
					i++;
					}
				}
			//APELLIDO
			if(!parametros.get("apellido").equals("")){ 					//TIPO STRING
				if (i==0){
					sql += " Where apellido like ?";
					campos.add("apellido");
					i++;
				}else{					
					sql += " and apellido like ?";
					campos.add("apellido");
					i++;
					}
				}
			//MASCOTA
			if(!parametros.get("mascota").equals("")){ 						//TIPO STRING
				if (i==0){
					sql += " Where ma.nombre like ?";
					campos.add("mascota");
					i++;
				}else{					
					sql += " and ma.nombre like ?";
					campos.add("mascota");
					i++;
					}
				}
			//DIRECCION
			if(!parametros.get("direccion").equals("")){ 					//TIPO STRING
				if (i==0){
					sql += " Where direccion like ?";
					campos.add("direccion");
					i++;
				}else{					
					sql += " and direccion like ?";
					campos.add("direccion");
					i++;
					}
				}
			//CON TRANSPORTE
			if(!parametros.get("conTransporte").equals("")){ 				//TIPO BOOLEAN
				if (i==0){
					sql += " Where tu.retiroDom = ?";
					campos.add("conTransporte");
					i++;
				}else{					
					sql += " and tu.retiroDom = ?";
					campos.add("conTransporte");
					i++;
					}
				}
			//TipoServicio
			if(!parametros.get("idServicio").equals("")){ 				//TIPO INT
				if (i==0){
					sql += " Where se.idServicio = ?";
					campos.add("idServicio");

					i++;
				}else{					
					sql += " and se.idServicio = ?";
					campos.add("idServicio");
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
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");			//FECHA DEL TURNO
					Date fechaDate = df.parse(parametros.get(campos.get(j)));
					java.sql.Date sqlDate = new java.sql.Date(fechaDate.getTime());
					pstm.setDate(j+1, sqlDate);
					break;
					
				case "conTransporte":													//CON RETIRO A DOMICILIO
					if(parametros.get(campos.get(j)).equals("true")){
						pstm.setBoolean(j+1,true);
					}else{
						pstm.setBoolean(j+1,false);
					}
					break;
					
				case "idServicio":														//TIPO DE SERVICIO
					pstm.setInt(j+1, Integer.parseInt(parametros.get(campos.get(j))));
					break;
					
				default:																//CAMPOS RESTANTES
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
					Turno turnoActual= new Turno();
					turnoActual.setIdTurno(rs.getInt("idTurno"));
					turnoActual = ctrlTurno.getTurno(turnoActual);
					
					turnos.add(turnoActual);
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
		return turnos;
	}
	
	public Boolean cancelarTurno(Turno turno) throws Exception
	{
		PreparedStatement pstm = null;
		Boolean bandera=false;
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE turno SET estado=? WHERE idTurno=?");
			pstm.setString(1, "Cancelado");
			pstm.setInt(2, turno.getIdTurno());
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
}
