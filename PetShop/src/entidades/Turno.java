package entidades;

import java.io.Serializable;


public class Turno implements Serializable{
	private int idTurno;
	private String fecha;
	private String hora;
	private String repetir;
	private Boolean retiroDom;
	private String estado;
	private Mascota mascota;
	private Servicio servicio;
	
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getRepetir() {
		return repetir;
	}
	public void setRepetir(String repetir) {
		this.repetir = repetir;
	}
	public Boolean getRetiroDom() {
		return retiroDom;
	}
	public void setRetiroDom(Boolean retiroDom) {
		this.retiroDom = retiroDom;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}
