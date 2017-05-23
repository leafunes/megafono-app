package daos.iface;

import data.Usuario;

public interface DAOUsuario extends DAO<Usuario>{
	
	public boolean isUsernameInUse(String username);

}
