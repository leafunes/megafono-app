package data;

import java.util.ArrayList;

public class Tag {
	
	private Precio precio;
	private Tag padre;
	private boolean isRoot;
	
	private String nombre;
	
	public Tag(String n, Precio p) {
		this.nombre = n;
		this.precio = p;
		isRoot = true;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String n){
		this.nombre = n;
	}
	
	public Tag getPadre() {
		return padre;
	}

	public void setPadre(Tag padre) {
		this.padre = padre;
		isRoot = (padre == null);
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isRoot ? 1231 : 1237);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((padre == null) ? 0 : padre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		return result;
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
		if (isRoot != other.isRoot)
			return false;
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
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

}
