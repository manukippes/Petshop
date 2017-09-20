package entidades;

import java.io.Serializable;
import java.sql.Time;

public class TipoMascotaServicio implements Serializable{
	private int idTMascServ;
	private Time tiempo;
	private Double precio;
	private Servicio servicio;
	private TipoMascota tipoMascota;
	
	public int getIdTMascServ() {
		return idTMascServ;
	}
	public void setIdTMascServ(int idTMascServ) {
		this.idTMascServ = idTMascServ;
	}
	public Time getTiempo() {
		return tiempo;
	}
	public void setTiempo(Time tiempo) {
		this.tiempo = tiempo;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public TipoMascota getTipoMascota() {
		return tipoMascota;
	}
	public void setTipoMascota(TipoMascota tipoMascota) {
		this.tipoMascota = tipoMascota;
	}
}
