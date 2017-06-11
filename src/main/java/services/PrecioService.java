package services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import daos.iface.DAOPrecio;
import daos.impl.DAOPrecioNeodatis;
import data.Precio;
import data.Tag;
import dtos.PrecioDTO;

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

	public void actualizePrices(Tag tag, Precio p) {
		
		Precio currentPrice = daoPrecio.getCurrentPriceOf(tag);
		
		if(p.getMonto().longValueExact() != currentPrice.getMonto().longValueExact()){
			
			p.setFechaCreacion(DateTime.now());
			
			currentPrice.setFechaFin(DateTime.now());
			currentPrice.setCurrent(false);
			
			daoPrecio.save(currentPrice);
			daoPrecio.save(p);
			
		}
		
	}

	public List<PrecioDTO> getHistoricalPricesOf(Tag t) {
		
		List<PrecioDTO> toReturn = new ArrayList<>();
		
		daoPrecio.getAllPricesOf(t).forEach(p -> toReturn.add(new PrecioDTO(p)));
		
		return toReturn;
	}

}
