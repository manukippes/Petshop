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
	//			BLANQUEAR USUARIO POR MAIL
	
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
	
	public Usuario agregarUsuario(Usuario user) throws Exception{
		Usuario usuario = baseUsuario.agregarUsuario(user);
		return usuario;
	}
	
	public void modificarUsuario(Usuario user) throws Exception{
		baseUsuario.modificarUsuario(user);
	}
	
	public boolean eliminarUsuario(Usuario user) throws Exception{
		boolean respuesta = baseUsuario.eliminarUsuario(user);
		return respuesta;
	}
	
	public ArrayList<Usuario> getUsuariosLike(String inputUsuario) throws Exception{
		return baseUsuario.getUsuariosLike(inputUsuario);
	}
	public Usuario getUsuario(Usuario user) throws Exception
	{
		return baseUsuario.getUsuario(user);
	}
	public boolean blanquearUsuario(String email,String nuevoUsuario, String nuevoPass) throws Exception{
		return baseUsuario.blanquearUsuario(email,nuevoUsuario,nuevoPass);
	}
	
}
