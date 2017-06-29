package data;


public class Tag {
	
	private String nombre;
	private String descripcion;
	private Tag padre;
	private boolean habilitado;
	
	public Tag(String nombre) {
		this(nombre, null);
	}
	
	public Tag(String nombre, Tag padre){
		this.nombre = nombre;
		this.padre = padre;
		this.habilitado = true;
		this.descripcion = "";
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tag getPadre() {
		return padre;
	}

	public void setPadre(Tag padre) {
		this.padre = padre;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (padre == null) {
			if (other.padre != null)
				return false;
		} else if (!padre.equals(other.padre))
			return false;
		return true;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}


}
