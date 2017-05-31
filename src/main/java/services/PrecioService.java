package services;

import org.joda.time.DateTime;

import daos.iface.DAOPrecio;
import daos.impl.DAOPrecioNeodatis;
import data.Precio;
import data.Tag;

public class PrecioService {
	
	private DAOPrecio daoPrecio;
	
	private static PrecioService singleton;
	
	public static PrecioService getService(){
		
		if(singleton == null)
			singleton = new PrecioService();
		
		return singleton;
		
	}
	
	private PrecioService(){
		daoPrecio = new DAOPrecioNeodatis();
		
	}
	
	public Precio getCurrentPriceOf(Tag t){
		return daoPrecio.getCurrentPriceOf(t);
	}
	
	public void addPrecio(Precio p){
		daoPrecio.save(p);
	}

	public void actualizePrices(Tag tag) {
		
		Precio toActualize = getCurrentPriceOf(tag);
		
		if(toActualize != null){
			toActualize.setFechaFin(DateTime.now());
			toActualize.setCurrent(false);
			
			daoPrecio.save(toActualize);
		}
		
	}

}
