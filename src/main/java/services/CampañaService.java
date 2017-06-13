package services;

import daos.iface.DAOCampaña;
import daos.impl.DAOCampañaNeodatis;
import data.Campaña;

public class CampañaService {
	
	private static CampañaService singleton;
	
	private DAOCampaña daoCampaña;
	
	public static CampañaService getService(){
		if (singleton == null)
			singleton = new CampañaService();
		return singleton;
	}
	
	private CampañaService(){
		daoCampaña = new DAOCampañaNeodatis();
	}
	
	public void saveCampaña(Campaña c){
		
		daoCampaña.save(c);
		
	}

}
