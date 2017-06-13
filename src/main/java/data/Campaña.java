package data;

import java.util.List;

import org.joda.time.DateTime;

public class Campaña {
	
	private String nombre;
	private String descripcion;
	private DateTime inicio;
	
	private Usuario owner;
	private List<Tag> tags;
	
	private MensajeCampaña mensaje;
	private EstadoCampaña estado;
	private DuracionCampaña duracion;
	
	public Campaña(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DateTime getInicio() {
		return inicio;
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public MensajeCampaña getMensaje() {
		return mensaje;
	}

	public void setMensaje(MensajeCampaña mensaje) {
		this.mensaje = mensaje;
	}

	public EstadoCampaña getEstado() {
		return estado;
	}

	public void setEstado(EstadoCampaña estado) {
		this.estado = estado;
	}

	public DuracionCampaña getDuracion() {
		return duracion;
	}

	public void setDuracion(DuracionCampaña duracion) {
		this.duracion = duracion;
	}
	
	
	
}
