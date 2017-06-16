package services;

import java.util.List;

import daos.iface.DAOCampania;
import daos.impl.DAOCampaniaNeodatis;
import data.Campania;
import data.Usuario;

public class CampaniaService {
	
	private static CampaniaService singleton;
	
	private DAOCampania daoCampania;
	
	public static CampaniaService getService(){
		if (singleton == null)
			singleton = new CampaniaService();
		return singleton;
	}
	
	private CampaniaService(){
		daoCampania = new DAOCampaniaNeodatis();
	}
	
	public void saveCampania(Campania c){
		
		daoCampania.save(c);
		
	}
	
	public List<Campania> getAllCampaniasOf(Usuario u){
		
		return daoCampania.getAllCampaniasOf(u);
		
	}

}
