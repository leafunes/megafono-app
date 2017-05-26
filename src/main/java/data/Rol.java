package data;

import java.util.ArrayList;
import java.util.List;

public class Rol {
	
	private String nombre;
	private String descripcion;
	private List<Permiso> permisos;
	
	public Rol(String nombre, String descripcion) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		
		this.permisos = new ArrayList<>();
		
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

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void addPermiso(Permiso permiso) {
		this.permisos.add(permiso);
	}

}
