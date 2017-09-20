package entidades;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Turno implements Serializable{
	private int idTurno;
	private Date fecha;
	private Time hora;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
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
