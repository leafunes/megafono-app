package dtos;

import java.math.BigDecimal;

import data.precios.Precio;

public class PrecioDTO {
	
	//hace falta que sean private?
	private BigDecimal monto;
	private String fechaCreacion;
	private String fechaFin;
	
	public PrecioDTO(Precio p) {
		
		this.monto = p.getMonto();
		this.fechaCreacion = p.getFechaCreacion().toString("yyyy/MM/dd HH:mm:ss");
		
		if(p.isCurrent()){
			this.fechaFin = "N/A";
		}
		else{
			this.fechaFin = p.getFechaFin().toString("yyyy/MM/dd HH:mm:ss");
		}
		
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
