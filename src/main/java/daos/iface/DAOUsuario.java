package daos.iface;

import data.Usuario;

public interface DAOUsuario extends DAO<Usuario>{
	
	public boolean isUsernameInUse(String username);
	public boolean existeUsuario(String username, String md5);
	public Usuario getUsuario(String username, String md5);

}
