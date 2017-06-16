package data;

import org.joda.time.Duration;

public class DuracionCampania {
	
	TipoDuracion tipo;
	Duration duracion;
	
	public DuracionCampania(TipoDuracion tipo) {
		this.tipo = tipo;
	}

	public TipoDuracion getTipo() {
		return tipo;
	}

	public void setTipo(TipoDuracion tipo) {
		this.tipo = tipo;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}
	
	

}
