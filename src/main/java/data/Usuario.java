package data;

public class Usuario {
	
	private String email;
	private String pswMd5;
	private Rol rol;
	
	public Usuario(String email, String pswMd5, Rol rol) {
		
		this.email = email;
		this.pswMd5 = pswMd5;
		this.rol = rol;
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMd5() {
		return pswMd5;
	}
	public void setMd5(String pswMd5) {
		this.pswMd5 = pswMd5;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	

}
