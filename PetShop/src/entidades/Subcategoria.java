package entidades;

public class Subcategoria {
	private int idSubCategoria;
	private Categoria categoria;
	private String nombre;
	
	public int getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
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
