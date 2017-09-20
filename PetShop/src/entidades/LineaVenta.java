package entidades;

import java.io.Serializable;

public class LineaVenta implements Serializable{
	private int idLineaVenta;
	private String tipoLineaVta;
	private Double precioUnitario;
	private int cantidad;
	private Venta venta;
	private Producto producto;
	private TipoMascotaServicio tipoMascotaServicio;
	
	public int getIdLineaVenta() {
		return idLineaVenta;
	}
	public void setIdLineaVenta(int idLineaVenta) {
		this.idLineaVenta = idLineaVenta;
	}
	public String getTipoLineaVta() {
		return tipoLineaVta;
	}
	public void setTipoLineaVta(String tipoLineaVta) {
		this.tipoLineaVta = tipoLineaVta;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public TipoMascotaServicio getTipoMascotaServicio() {
		return tipoMascotaServicio;
	}
	public void setTipoMascotaServicio(TipoMascotaServicio tipoMascotaServicio) {
		this.tipoMascotaServicio = tipoMascotaServicio;
	}
	
}
