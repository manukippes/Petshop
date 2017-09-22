package presentacion;

import java.util.ArrayList;
import entidades.Mascota;
import entidades.TipoMascota;
import entidades.Usuario;
import datos.DatosMascota;
import datos.DatosTipoMascota;
import datos.DatosUsuario;

public class Principal {

	public static void main(String[] args) {
			
			DatosUsuario capaDatosUsuario = new DatosUsuario();
			DatosMascota capaDatosMascota = new DatosMascota();
			DatosTipoMascota capaDatosTipoMascota = new DatosTipoMascota();
			
			//USUARIO CREADO MANUALMENTE
			Usuario usuariodePrueba = new Usuario();
			usuariodePrueba.setIdUsuario(2);
			usuariodePrueba.setDni(132);
			usuariodePrueba.setUsuarioLogin("Hugo");
			usuariodePrueba.setPassword("Hugo");
			usuariodePrueba.setNombre("Hugo Miguel");
			usuariodePrueba.setApellido("Perez");
			//TIPO DE MASCOTA CREADA MANUALMENTE
			TipoMascota tipoMascotadePrueba = new TipoMascota();
			tipoMascotadePrueba.setIdTipoMascota(1);
			tipoMascotadePrueba.setPelo("Largo");
			tipoMascotadePrueba.setTamanio("Chico");
			//MASCOTA CREADA MANUALMENTE
			Mascota mascotadePrueba = new Mascota();
			mascotadePrueba.setIdMascota(1);
			mascotadePrueba.setUsuario(usuariodePrueba);
			mascotadePrueba.setTipoMascota(tipoMascotadePrueba);
			mascotadePrueba.setNombre("Pluto");
			
					
			
			
			try{
				//capaDatos.modificarUsuario(usuariodePrueba);
				//capaDatosMascota.modificarMascota(mascotadePrueba);
				
				//ArrayList<TipoMascota> listado = capaDatosTipoMascota.devolverTodos();
				//System.out.println(listado.get(0).getIdTipoMascota()+" "+listado.get(0).getPelo());
				//System.out.println(listado.get(1).getIdTipoMascota()+" "+listado.get(1).getPelo());
				
				
			}
			catch (Exception e){
				System.out.println(e);
			}
			System.out.println("Fin de la ejecucion");
			
		}

	}

