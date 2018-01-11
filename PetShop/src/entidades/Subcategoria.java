package entidades;

import java.io.Serializable;

public class Subcategoria implements Serializable{
	private int idSubcategoria;
	private Categoria categoria;
	private String nombre;
	
	public int getIdSubCategoria() {
		return idSubcategoria;
	}
	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubcategoria = idSubCategoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
