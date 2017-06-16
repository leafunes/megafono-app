package data;

import java.awt.Image;

public class MensajeCampania {
	
	private String mensaje;
	private Image imagen;
	
	public MensajeCampania(String mensaje, Image imagen) {
		this.mensaje = mensaje;
		this.imagen = imagen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	
	

}
