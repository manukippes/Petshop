package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.Level;

import entidades.Mascota;
import entidades.Usuario;
import logica.ControladorDeMascota;
import utilidades.ExcepcionEspecial;
import utilidades.RandomString;

public class DatosUsuario implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR USUARIO
	//						MODIFICAR USUARIO
	//						VALIDAR USUARIO POR NOMBRE Y PASS OBTENER USUARIO HABILITADO
	//						ELIMINAR USUARIO
	//						GETUSUARIOSLIKE(RECIBE UN STRING)
	// 						COMPLETAR DATOS DEL USUARIO
	//						BLANQUEAR USUARIO
	//						VERIFICAR SI EXISTE MAIL
	//						VALIDAR USUARIO POR NOMBRE Y PASS OBTENER USUARIO
	

	public Usuario agregarUsuario (Usuario user) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean bandera = false;
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO usuario(idUsuario,usuarioLogin,password,nombre,apellido,estado,tipoUsuario,dni,direccion,telefono,email,legajo,tipoEmpleado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, user.getIdUsuario());
			pstm.setString(2, user.getUsuarioLogin());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getNombre());
			pstm.setString(5, user.getApellido());
			pstm.setInt(6, user.getEstado());
			pstm.setString(7, user.getTipoUsuario());
			pstm.setInt(8, user.getDni());
			pstm.setString(9, user.getDireccion());
			pstm.setString(10, user.getTelefono());
			if (user.getEmail().equals("")){
				RandomString gen = new RandomString(12, ThreadLocalRandom.current());
				String emailRandom = gen.nextString();
				pstm.setString(11, emailRandom);
			}else{
				pstm.setString(11, user.getEmail());
			}
			
			pstm.setInt(12, user.getLegajo());
			pstm.setString(13, user.getTipoEmpleado());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				user.setIdUsuario(rs.getInt(1));
				user.setMascotas(ctrlMascota.getMascotas(user));
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
		return user;
	}
	public Usuario modificarUsuario(Usuario user) throws Exception
	{
		PreparedStatement pstm = null;
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();
				
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE usuario SET usuarioLogin=?,password=?,nombre=?,apellido=?,estado=?,tipoUsuario=?,dni=?,direccion=?,telefono=?,email=?,legajo=?,tipoEmpleado=? WHERE idUsuario=?");
			pstm.setString(1, user.getUsuarioLogin());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getNombre());
			pstm.setString(4, user.getApellido());
			pstm.setInt(5, user.getEstado());
			pstm.setString(6, user.getTipoUsuario());
			pstm.setInt(7, user.getDni());
			pstm.setString(8, user.getDireccion());
			pstm.setString(9, user.getTelefono());
			pstm.setString(10, user.getEmail());
			pstm.setInt(11, user.getLegajo());
			pstm.setString(12, user.getTipoEmpleado());
			pstm.setInt(13, user.getIdUsuario());
			pstm.executeUpdate();
			user.setMascotas(ctrlMascota.getMascotas(user));
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
		return user;
		
	}
	
	public Usuario obtenerUsuarioHabilitado(Usuario user) throws Exception, ExcepcionEspecial{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario usuario=null;
		int estado = 1;
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM usuario WHERE usuarioLogin=? AND password=? AND estado = ?");
			pstm.setString(1, user.getUsuarioLogin());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, estado);
			rs=pstm.executeQuery();
			if(rs!=null)
			{	rs.next();
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getInt("idUsuario"));
					usuario.setUsuarioLogin(rs.getString("usuarioLogin"));
					usuario.setPassword(rs.getString("password"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellido(rs.getString("apellido"));
					usuario.setEstado(rs.getInt("estado"));
					usuario.setTipoUsuario(rs.getString("tipoUsuario"));
					usuario.setDni(rs.getInt("dni"));
					usuario.setDireccion(rs.getString("direccion"));
					usuario.setTelefono(rs.getString("telefono"));
					usuario.setEmail(rs.getString("email"));
					usuario.setLegajo(rs.getInt("legajo"));
					usuario.setTipoEmpleado(rs.getString("tipoEmpleado"));
					usuario.setMascotas(ctrlMascota.getMascotas(usuario));
			}
			else{
				throw new Exception();
				
			}
		} catch (SQLException exc) {
			
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
		return usuario;
	}
	
	public boolean eliminarUsuario(Usuario user) throws Exception{
		 PreparedStatement ps = null;
		 int estado= 0;
		 try {
			ps = FactoryConnection.getinstancia().getConn().prepareStatement("UPDATE usuario SET estado=? WHERE idUsuario=?");
			ps.setInt(1, estado);
			ps.setInt(2, user.getIdUsuario());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ExcepcionEspecial(e, "No es posible eliminar el usuario", Level.ERROR);
			
		}
		 catch (Exception e) {
			 throw e;
		}
		 
		try {
			if(ps!=null)ps.close();
			FactoryConnection.getinstancia().releaseConn();	
		} catch (Exception e) {
			throw e;
		}
		
		return true;
		
	}
	
	public ArrayList<Usuario> getUsuariosLike(String inputUsuario) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		inputUsuario = ("%"+inputUsuario+"%");
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario ";
		int estado = 1;
		
		try {
			if (!inputUsuario.equals("%%")){
				sql+=" where (nombre like ? or apellido like ? or idUsuario like ? ) and estado = ?;";
				}
			
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(sql);
			
			if (!inputUsuario.equals("%%")){
				pstm.setString(1, inputUsuario);
				pstm.setString(2, inputUsuario);
				pstm.setString(3, inputUsuario);
				pstm.setInt(4, estado);
			}
			
			rs=pstm.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					Usuario usuarioActual = new Usuario();
					usuarioActual.setIdUsuario(rs.getInt("idUsuario"));
					usuarioActual.setUsuarioLogin(rs.getString("usuarioLogin"));
					usuarioActual.setPassword(rs.getString("password"));
					usuarioActual.setNombre(rs.getString("nombre"));
					usuarioActual.setApellido(rs.getString("apellido"));
					usuarioActual.setEstado(rs.getInt("estado"));
					usuarioActual.setTipoUsuario(rs.getString("tipoUsuario"));
					usuarioActual.setDni(rs.getInt("dni"));
					usuarioActual.setDireccion(rs.getString("direccion"));
					usuarioActual.setTelefono(rs.getString("telefono"));
					usuarioActual.setEmail(rs.getString("email"));
					usuarioActual.setLegajo(rs.getInt("legajo"));
					usuarioActual.setTipoEmpleado(rs.getString("tipoEmpleado"));
					usuarios.add(usuarioActual);
				}
			}
		} catch (SQLException exc) {
			
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		return usuarios;
	}
	
	public Usuario getUsuario(Usuario user) throws Exception, ExcepcionEspecial{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		int estado = 1;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM usuario WHERE idUsuario = ?");
			pstm.setInt(1, user.getIdUsuario());
			rs=pstm.executeQuery();
			if(rs!=null)
			{	rs.next();
					
					usuario.setIdUsuario(rs.getInt("idUsuario"));
					usuario.setUsuarioLogin(rs.getString("usuarioLogin"));
					usuario.setPassword(rs.getString("password"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellido(rs.getString("apellido"));
					usuario.setEstado(rs.getInt("estado"));
					usuario.setTipoUsuario(rs.getString("tipoUsuario"));
					usuario.setDni(rs.getInt("dni"));
					usuario.setDireccion(rs.getString("direccion"));
					usuario.setTelefono(rs.getString("telefono"));
					usuario.setEmail(rs.getString("email"));
					usuario.setLegajo(rs.getInt("legajo"));
					usuario.setTipoEmpleado(rs.getString("tipoEmpleado"));
			}
			else{
				throw new Exception();
				
			}
		} catch (SQLException exc) {
			
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
		return usuario;
	}

	public boolean blanquearUsuario(String email, String nuevoUsuario, String nuevoPass) throws Exception, ExcepcionEspecial{
		
		PreparedStatement pstm = null;
		Boolean bandera = false;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE usuario SET usuarioLogin=?,password=? WHERE email=?");
			pstm.setString(1, nuevoUsuario);
			pstm.setString(2, nuevoPass);
			pstm.setString(3, email);
			if(pstm.executeUpdate()==1){
				bandera=true;
			};
			
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
	
	public ArrayList<Usuario> getTodosUsuariosLike(String inputUsuario) throws Exception, ExcepcionEspecial{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		inputUsuario = ("%"+inputUsuario+"%");
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario ";
		
		try {
			if (!inputUsuario.equals("%%")){
				sql+=" where nombre like ? or apellido like ? or idUsuario like ?";
				}
			
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(sql);
			
			if (!inputUsuario.equals("%%")){
				pstm.setString(1, inputUsuario);
				pstm.setString(2, inputUsuario);
				pstm.setString(3, inputUsuario);
			}
			
			rs=pstm.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					Usuario usuarioActual = new Usuario();
					usuarioActual.setIdUsuario(rs.getInt("idUsuario"));
					usuarioActual.setUsuarioLogin(rs.getString("usuarioLogin"));
					usuarioActual.setPassword(rs.getString("password"));
					usuarioActual.setNombre(rs.getString("nombre"));
					usuarioActual.setApellido(rs.getString("apellido"));
					usuarioActual.setEstado(rs.getInt("estado"));
					usuarioActual.setTipoUsuario(rs.getString("tipoUsuario"));
					usuarioActual.setDni(rs.getInt("dni"));
					usuarioActual.setDireccion(rs.getString("direccion"));
					usuarioActual.setTelefono(rs.getString("telefono"));
					usuarioActual.setEmail(rs.getString("email"));
					usuarioActual.setLegajo(rs.getInt("legajo"));
					usuarioActual.setTipoEmpleado(rs.getString("tipoEmpleado"));
					usuarios.add(usuarioActual);
				}
			}
		} catch (SQLException exc) {
			
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		return usuarios;
	}
	public Boolean validarEmail(String email) throws Exception, ExcepcionEspecial{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Boolean bandera = false;
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM usuario WHERE email = ?");
			pstm.setString(1, email);
			rs = pstm.executeQuery();
			rs=pstm.executeQuery();
			if(rs.next())
			{
				bandera = true;
			}
			
		} catch (SQLException e) {
			throw new ExcepcionEspecial(e,"No es posible agregar la persona, la validación en el email fallo.", Level.ERROR);
		} catch (ExcepcionEspecial e) {
			throw e;
		}
		return bandera;
		
	}
public Usuario obtenerUsuario(Usuario user) throws Exception, ExcepcionEspecial{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario usuario=null;
		ControladorDeMascota ctrlMascota = new ControladorDeMascota();
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM usuario WHERE usuarioLogin=? AND password=?");
			pstm.setString(1, user.getUsuarioLogin());
			pstm.setString(2, user.getPassword());
			rs=pstm.executeQuery();
			if(rs!=null)
			{	rs.next();
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getInt("idUsuario"));
					usuario.setUsuarioLogin(rs.getString("usuarioLogin"));
					usuario.setPassword(rs.getString("password"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellido(rs.getString("apellido"));
					usuario.setEstado(rs.getInt("estado"));
					usuario.setTipoUsuario(rs.getString("tipoUsuario"));
					usuario.setDni(rs.getInt("dni"));
					usuario.setDireccion(rs.getString("direccion"));
					usuario.setTelefono(rs.getString("telefono"));
					usuario.setEmail(rs.getString("email"));
					usuario.setLegajo(rs.getInt("legajo"));
					usuario.setTipoEmpleado(rs.getString("tipoEmpleado"));
					usuario.setMascotas(ctrlMascota.getMascotas(usuario));
			}
			else{
				throw new Exception();
				
			}
		} catch (SQLException exc) {
			
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
		return usuario;
	}
}
