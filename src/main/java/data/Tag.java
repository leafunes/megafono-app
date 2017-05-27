package data;

import java.util.ArrayList;

public class Tag {
	
	private String nombre;
	private Tag padre;
	
	public Tag(String nombre) {
		this.nombre = nombre;
	}
	
	public Tag(String nombre, Tag padre){
		this.nombre = nombre;
		this.padre = padre;
		
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
}
