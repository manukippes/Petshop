package logica;

import entidades.Usuario;

import java.io.Serializable;
import java.util.ArrayList;

import datos.DatosUsuario;


public class ControladorDeUsuario implements Serializable{
	private DatosUsuario baseUsuario = new DatosUsuario();
	
	//METODOS IMPLEMENTADOS
	
	//			GETUSUARIO
	//			AGREGARUSUARIO
	//			MODIFICARUSUARIO
	//			ELIMINARUSUARIO
	//			GETUSUARIOSLIKE (RECIBE UN STRING)
	//			COMPLETAR LOS DATOS DEL USUARIO
	
	public Usuario obtenerUsuario(Usuario user) throws Exception
	{
		Usuario usuario = new Usuario();
		try {
			
			usuario = baseUsuario.obtenerUsuario(user);
	
			
		} catch (Exception e) {
			
			throw e;
		}
		return usuario;
	}
	
	public int agregarUsuario(Usuario user) throws Exception{
		int idUsuario = baseUsuario.agregarUsuario(user);
		return idUsuario;
	}
	
	public void modificarUsuario(Usuario user) throws Exception{
		baseUsuario.modificarUsuario(user);
	}
	
	public void eliminarUsuario(Usuario user) throws Exception{
		baseUsuario.eliminarUsuario(user);
	}
	
	public ArrayList<Usuario> getUsuariosLike(String inputUsuario) throws Exception{
		return baseUsuario.getUsuariosLike(inputUsuario);
	}
	public Usuario getUsuario(Usuario user) throws Exception
	{
		return baseUsuario.getUsuario(user);
	}
}
