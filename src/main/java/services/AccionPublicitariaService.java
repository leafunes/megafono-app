package services;

import java.util.List;

import daos.iface.DAOAccionPublicitaria;
import daos.impl.DAOAccionPublicitariaNeodatis;
import data.AccionPublicitaria;
import data.Tag;

public class AccionPublicitariaService {
	
	private static AccionPublicitariaService singleton;
	
	private DAOAccionPublicitaria daoAccion;
	
	public static AccionPublicitariaService getService(){
		
		if(singleton == null)
			singleton = new AccionPublicitariaService();
		
		return singleton;
		
	}
	
	private AccionPublicitariaService(){
		
		daoAccion = new DAOAccionPublicitariaNeodatis();
		
	}
	
	public void saveAccion(AccionPublicitaria a){
		daoAccion.save(a);
	}
	
	public List<AccionPublicitaria> getAllActionsOf(Tag t){
		return daoAccion.getAllActionsOf(t);
	}

}
