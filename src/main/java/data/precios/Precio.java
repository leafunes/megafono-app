package data.precios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;

public class Precio {
	
	private Date fechaFin;
	private Date fechaCreacion;
	
	private BigDecimal monto;
	
	private boolean current;

	protected Precio() {
		monto = new BigDecimal("0");
		current = true;
		fechaCreacion = new Date(); //now
	}

	public DateTime getFechaFin() {
		return new DateTime(fechaFin);
	}

	public void setFechaFin(DateTime fechaFin) {
		this.fechaFin = new Date(fechaFin.getMillis());//fechaFin.getMillis();
	}

	public DateTime getFechaCreacion() {
		return new DateTime(fechaCreacion);
	}

	public void setFechaCreacion(DateTime fechaCreacion) {
		this.fechaCreacion = new Date(fechaCreacion.getMillis());//fechaCreacion.getMillis();
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
