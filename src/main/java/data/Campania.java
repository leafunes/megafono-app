package data;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

public class Campania {
	
	private String nombre;
	private String descripcion;
	private Date inicio;
	
	private Usuario owner;
	private List<Tag> tags;
	
	private MensajeCampania mensaje;
	private EstadoCampania estado;
	private TipoDuracion duracion;
	
	public Campania(String nombre, String descripcion) {
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
		return new DateTime(inicio);
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio.toDate();
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

	public MensajeCampania getMensaje() {
		return mensaje;
	}

	public void setMensaje(MensajeCampania mensaje) {
		this.mensaje = mensaje;
	}

	public EstadoCampania getEstado() {
		return estado;
	}

	public void setEstado(EstadoCampania estado) {
		this.estado = estado;
	}

	public TipoDuracion getDuracion() {
		return duracion;
	}

	public void setDuracion(TipoDuracion duracion) {
		this.duracion = duracion;
	}
	
	
	
}
