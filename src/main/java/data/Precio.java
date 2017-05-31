package data;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.DateTime;

public class Precio {
	
	private DateTime fechaFin;
	private DateTime fechaCreacion;
	
	private int leftComma;
	private int rigthComma;
	
	private boolean current;
	
	private Object objetoValuable;

	public Precio() {
		
	}

	public DateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(DateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public DateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(DateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getLeftComma() {
		return leftComma;
	}

	public void setLeftComma(int leftComma) {
		this.leftComma = leftComma;
	}

	public int getRigthComma() {
		return rigthComma;
	}

	public void setRigthComma(int rigthComma) {
		this.rigthComma = rigthComma;
	}
	
	public Object getObjetoValuable() {
		return objetoValuable;
	}

	public void setObjetoValuable(Object objetoValuable) {
		this.objetoValuable = objetoValuable;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
	

}
