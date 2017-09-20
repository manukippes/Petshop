package entidades;

public class Senia {
	private int idSenia;
	private Double monto;
	private LineaVenta lineaVenta;
	private Venta venta;
	
	public int getIdSenia() {
		return idSenia;
	}
	public void setIdSenia(int idSenia) {
		this.idSenia = idSenia;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public LineaVenta getLineaVenta() {
		return lineaVenta;
	}
	public void setLineaVenta(LineaVenta lineaVenta) {
		this.lineaVenta = lineaVenta;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
}
