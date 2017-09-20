package entidades;

public class MedioPago {
	private int idMedioPago;
	private String tipo;
	private Tarjeta tarjeta;
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
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
}
