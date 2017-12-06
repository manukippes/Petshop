package logica;

import entidades.Usuario;
import datos.DatosUsuario;

public class ControladorDeUsuario {
	private DatosUsuario baseUsuario = new DatosUsuario();
	public Usuario recuperarUsuario(Usuario user) throws Exception
	{
		Usuario usuario = new Usuario();
		try {
			
			usuario = baseUsuario.recuperarUsuario(user);
	
			
		} catch (Exception e) {
			
			throw e;
		}
		return usuario;
	}

}
