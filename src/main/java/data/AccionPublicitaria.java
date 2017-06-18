package data;

import java.time.Instant;

import org.joda.time.Period;

public class AccionPublicitaria {
	
	private TipoAccionPublicitaria tipo;
	//FIXME: ver si no hay que cambiar a milis por el tema de neodatis vs jodaTime
	private Period periodicidad;
	private Instant ultimaEjecucion;
	
	private String destinatario;
	private String remitente;
	private String remitentePsw;
	
	private Campania camp;
	private Tag tag;
	
	public AccionPublicitaria(String destinatario, String remitente, String remitentePsw) {
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.remitentePsw = remitentePsw;
	}

	public Period getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(Period periodicidad) {
		this.periodicidad = periodicidad;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public TipoAccionPublicitaria getTipo() {
		return tipo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getRemitente() {
		return remitente;
	}

	public String getRemitentePsw() {
		return remitentePsw;
	}

	public Instant getUltimaEjecucion() {
		return ultimaEjecucion;
	}

	public void setUltimaEjecucion(Instant ultimaEjecucion) {
		this.ultimaEjecucion = ultimaEjecucion;
	}

	public Campania getCamp() {
		return camp;
	}

	public void setCamp(Campania camp) {
		this.camp = camp;
	}
	
	

}
