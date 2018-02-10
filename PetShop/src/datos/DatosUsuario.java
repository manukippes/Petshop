package datos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entidades.Usuario;
import utilidades.ExcepcionEspecial;

public class DatosUsuario implements Serializable{
	
	//METODOS IMPLEMENTADOS:
	//						AGREGAR USUARIO
	//						MODIFICAR USUARIO
	//						VALIDAR USUARIO POR NOMBRE Y PASS OBTENER USUARIO
	//						ELIMINAR USUARIO
	//						GETUSUARIOSLIKE(RECIBE UN STRING)
	// 						COMPLETAR DATOS DEL USUARIO
	

	public int agregarUsuario (Usuario user) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean bandera = false;
		
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
			pstm.setInt(10, user.getTelefono());
			pstm.setString(11, user.getEmail());
			pstm.setInt(12, user.getLegajo());
			pstm.setString(13, user.getTipoEmpleado());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				user.setIdUsuario(rs.getInt(1));
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
		return rs.getInt(1);
	}
	public void modificarUsuario(Usuario user) throws Exception
	{
		PreparedStatement pstm = null;
				
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
			pstm.setInt(9, user.getTelefono());
			pstm.setString(10, user.getEmail());
			pstm.setInt(11, user.getLegajo());
			pstm.setString(12, user.getTipoEmpleado());
			pstm.setInt(13, user.getIdUsuario());
			pstm.executeUpdate();
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
		
	}
	public Usuario obtenerUsuario(Usuario user) throws Exception, ExcepcionEspecial{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario usuario=null;
		
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
					usuario.setTelefono(rs.getInt("telefono"));
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
	
	public void eliminarUsuario(Usuario user) throws Exception{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			ps = FactoryConnection.getinstancia().getConn().prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
			ps.setInt(1, user.getIdUsuario());
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new ExcepcionEspecial(e, "No es posible eliminar el usuario", Level.ERROR);
			
		}
		 catch (Exception e) {
			 throw e;
		}
		 
		try {
			if(ps!=null)ps.close();
			if(rs!=null)ps.close();
			FactoryConnection.getinstancia().releaseConn();	
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public ArrayList<Usuario> getUsuariosLike(String inputUsuario) throws Exception{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		inputUsuario = ("%"+inputUsuario+"%");
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario ";		
		
		try {
			if (!inputUsuario.equals("%%")){
				sql+=" where nombre like ? or apellido like ?;";
				}
			
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(sql);
			
			if (!inputUsuario.equals("%%")){
				pstm.setString(1, inputUsuario);
				pstm.setString(2, inputUsuario);
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
					usuarioActual.setTelefono(rs.getInt("telefono"));
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
					usuario.setTelefono(rs.getInt("telefono"));
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
}
