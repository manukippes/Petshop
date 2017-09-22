package utilidades;

import java.io.Serializable;
import java.lang.Exception;
import java.lang.Throwable;

public class ExcepcionEspecial extends Exception implements Serializable  {
	
	public ExcepcionEspecial(String msg) {
        super(msg);
    	}	
	}
