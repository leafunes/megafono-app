package services;

import java.util.ArrayList;
import java.util.List;

import daos.iface.DAOAccionPublicitaria;
import daos.iface.DAOCampania;
import daos.impl.DAOAccionPublicitariaNeodatis;
import daos.impl.DAOCampaniaNeodatis;
import data.AccionPublicitaria;
import data.Campania;
import data.Tag;

public class AccionPublicitariaService {
	
	private static AccionPublicitariaService singleton;
	
	private DAOAccionPublicitaria daoAccion;
	private DAOCampania daoc = new DAOCampaniaNeodatis();
	
	public static AccionPublicitariaService getService(){
		
		if(singleton == null)
			singleton = new AccionPublicitariaService();
		
		return singleton;
		
	}
	
	private AccionPublicitariaService(){
		
		daoAccion = new DAOAccionPublicitariaNeodatis();
		daoc = new DAOCampaniaNeodatis();
		
	}
	
	public void saveAccion(AccionPublicitaria a){
		daoAccion.save(a);
		
	}
	
	List<AccionPublicitaria> getAllActionsOf(Campania c){
		
		List<AccionPublicitaria> toRet = new ArrayList<>();
		
		c.getTags().forEach(t -> toRet.addAll(getAllActionsOf(t)));
		
		toRet.addAll(daoAccion.getAllActionsOf(c));
		
		return toRet;
	}
	
	public List<AccionPublicitaria> getAllActionsOf(Tag t){
		return daoAccion.getAllActionsOf(t);
	}
	
	public List<AccionPublicitaria> getALLActions(){
		List<AccionPublicitaria> accionesTotales = new ArrayList<>();
		
		List<Campania> activas = daoc.getAllActiveCampanias();
		
		for (Campania c : activas){
			List<Tag> tags = c.getTags();
			for(Tag t : tags)
				accionesTotales.addAll(daoAccion.getAllActionsOf(t));
			accionesTotales.addAll(daoAccion.getAllActionsOf(c));
		}
		
		return accionesTotales;
	}

}
