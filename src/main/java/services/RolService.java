package services;

import daos.iface.DAORol;
import daos.impl.DAORolNeodatis;
import data.Rol;

public class RolService {
	
	private DAORol daoRol;
	
	private static RolService singleton;
	
	public static RolService getService(){
		if(singleton == null)
			singleton = new RolService();
		return singleton;
	}
	
	private RolService(){
		
		daoRol = new DAORolNeodatis();
		
	}
	
	public Rol getRolByName(String rol){
		return daoRol.getRolByName(rol);
	}

}
