package services;

import java.io.File;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import daos.iface.DAO;
import daos.iface.DAOCampania;
import daos.impl.DAOCampaniaNeodatis;
import daos.impl.DAONeodatis;
import data.AccionPublicitaria;
import data.Campania;
import data.MensajeCampania;
import data.Usuario;

public class CampaniaService {
	
	private static CampaniaService singleton;
	
	private DAOCampania daoCampania;
	private DAO<MensajeCampania> daoMensaje;
	
	public static CampaniaService getService(){
		if (singleton == null)
			singleton = new CampaniaService();
		return singleton;
	}
	
	private CampaniaService(){
		daoCampania = new DAOCampaniaNeodatis();
		daoMensaje = new DAONeodatis<>();
	}
	
	public void saveCampania(Campania c){
		
		daoCampania.save(c);
		
	}
	
	public List<Campania> getAllCampaniasOf(Usuario u){
		
		return daoCampania.getAllCampaniasOf(u);
		
	}

	public void setMensaje(Campania c, String txt, String img, String imgThumb) {
		
		if(c.getMensaje() != null){
			File imagen = new File(img);
			File thumbnail = new File(imgThumb);
			
			imagen.delete();
			thumbnail.delete();
			daoMensaje.delete(c.getMensaje());
		}
		
		MensajeCampania mensaje = new MensajeCampania(txt, img, imgThumb);
		c.setMensaje(mensaje);
		
	}

	public DateTime getEndOf(Campania camp) {
		
		DateTime toRet = null;
		
		switch (camp.getDuracion()) {
		case SEMANAL:
			toRet = camp.getInicio().plus(Duration.standardDays(7));
			break;
		
		case MENSUAL:
			toRet = camp.getInicio().plus(Duration.standardDays(30));
			break;
			
		case BIMENSUAL:
			toRet = camp.getInicio().plus(Duration.standardDays(60));
			break;
			
		case SEMESTRAL:
			toRet = camp.getInicio().plus(Duration.standardDays(180));
			break;

		default:
			break;
		}
			
			
		return toRet;
		
	}


}
