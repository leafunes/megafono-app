package data;

import org.joda.time.Duration;

public class DuracionCampaña {
	
	TipoDuracion tipo;
	Duration duracion;
	
	public DuracionCampaña(TipoDuracion tipo) {
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
