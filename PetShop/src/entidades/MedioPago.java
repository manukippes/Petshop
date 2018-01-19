package entidades;

import java.io.Serializable;

public class MedioPago implements Serializable{
	private int idMedioPago;
	private String tipo;
	
	public int getIdMedioPago() {
		return idMedioPago;
	}
	public void setIdMedioPago(int idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
