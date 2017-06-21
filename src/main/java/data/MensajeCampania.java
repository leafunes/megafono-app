package data;

import java.awt.Image;
import java.nio.file.Path;

public class MensajeCampania {
	
	private String mensaje;
	private String imagen;
	private String imagenThumbnail;
	
	public MensajeCampania(String mensaje, String imagen, String imagenThumbnail) {
		
		this.mensaje = mensaje;
		this.imagen = imagen;
		this.imagenThumbnail = imagenThumbnail;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getImagenThumbnail() {
		return imagenThumbnail;
	}

	public void setImagenThumbnail(String imagenThumnail) {
		this.imagenThumbnail = imagenThumnail;
	}
	
	
	
	

}
