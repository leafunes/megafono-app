package services;

import daos.iface.DAOUsuario;
import daos.impl.DAOUsuarioNeodatis;
import data.Usuario;

public class UsuarioService {
	
	private static UsuarioService singleton;
	
	private DAOUsuario daoUsuario;
	
	public static UsuarioService getService(){
		if(singleton == null) singleton = new UsuarioService();
		
		return singleton;
	}
	
	private UsuarioService(){
		daoUsuario = new DAOUsuarioNeodatis();
	}
	
	public boolean altaUsuario(String nombre, String password){
		
		if(daoUsuario.isUsernameInUse(nombre)) return false;
		
		Usuario toAdd = new Usuario(nombre, password);
		
		daoUsuario.save(toAdd);
		
		return true;
		
	}

}
