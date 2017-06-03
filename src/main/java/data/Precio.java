package data;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.DateTime;

public class Precio {
	
	private DateTime fechaFin;
	private DateTime fechaCreacion;
	
	private BigDecimal monto;
	
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	public void setMonto(String sMonto){
		this.monto = new BigDecimal(sMonto);
	}
	
	

}
