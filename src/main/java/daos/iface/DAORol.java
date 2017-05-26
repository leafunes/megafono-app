package daos.iface;

import data.Rol;

public interface DAORol extends DAO<Rol>{
	
	public boolean existeRol(String nombre);
	public Rol getRolByName(String nombre);

}
