package utilidades;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.lang.Exception;
import java.lang.Throwable;

public class ExcepcionEspecial extends Exception implements Serializable  {
	private Throwable originalExepcion;
	private String mensaje;
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public ExcepcionEspecial(Throwable e, String msg, Level errorLevel) {
		super(msg);
		this.originalExepcion = e;
		this.setMensaje(msg);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,msg);
    }	
	
	public ExcepcionEspecial(String msg, Level errorLevel) {
		super(msg);
		this.setMensaje(msg);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,msg);
    }	
}
