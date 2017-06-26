package data;

import java.util.ArrayList;
import java.util.List;

public class Rol {
	
	private String nombre;
	private String descripcion;
	private String nombreMainView;
	private List<Permiso> permisos;
	
	public Rol(String nombre, String descripcion, String mainView) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombreMainView = mainView;
		
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

	public String getNombreMainView() {
		return nombreMainView;
	}

	public void setNombreMainView(String nombreMainView) {
		this.nombreMainView = nombreMainView;
	}

}
