package data;

public class AccionPublicitariaDescr {
	
	private TipoAccionPublicitaria tipo;
	private String nombre;
	private String descripcion;
	
	public AccionPublicitariaDescr(TipoAccionPublicitaria tipo, String nombre, String descripcion) {

		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public TipoAccionPublicitaria getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
	
}
