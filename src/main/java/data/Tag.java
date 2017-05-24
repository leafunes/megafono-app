package data;

import java.util.ArrayList;

public class Tag {
	
	private Precio precio;
	private ArrayList<Tag> hijos;
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

	public ArrayList<Tag> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Tag> hijos) {
		this.hijos = hijos;
	}
	
	public void addHijo(Tag toAdd){
		this.hijos.add(toAdd);
		isRoot = false;
	}
	
	public void removeHijo(Tag toRemove){
		if(this.hijos.contains(toRemove))
			this.hijos.remove(toRemove);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hijos == null) ? 0 : hijos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		if (hijos == null) {
			if (other.hijos != null)
				return false;
		} else if (!hijos.equals(other.hijos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

}
