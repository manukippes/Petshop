package entidades;

public class TipoMascota {
	private int idTipoMascota;
	private String pelo;
	private String tamanio;
	
	public int getIdTipoMascota() {
		return idTipoMascota;
	}
	public void setIdTipoMascota(int idTipoMascota) {
		this.idTipoMascota = idTipoMascota;
	}
	public String getPelo() {
		return pelo;
	}
	public void setPelo(String pelo) {
		this.pelo = pelo;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
}
