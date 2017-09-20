package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Venta implements Serializable{
	private int idVenta;
	private Double total;
	private String estado;
	private Date fecha;
	private String datosOpcionales;
	private Boolean envioDom;
	private String domicilio;
	private Usuario usuario;
	private MedioPago medioPago;
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDatosOpcionales() {
		return datosOpcionales;
	}
	public void setDatosOpcionales(String datosOpcionales) {
		this.datosOpcionales = datosOpcionales;
	}
	public Boolean getEnvioDom() {
		return envioDom;
	}
	public void setEnvioDom(Boolean envioDom) {
		this.envioDom = envioDom;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
}
