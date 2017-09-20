package entidades;

public class Cuotas {
	private int idCuota;
	private int cantCuotas;
	private Double recargo;
	private Tarjeta tarjeta;
	
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
	public int getCantCuotas() {
		return cantCuotas;
	}
	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}
	public Double getRecargo() {
		return recargo;
	}
	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
}
