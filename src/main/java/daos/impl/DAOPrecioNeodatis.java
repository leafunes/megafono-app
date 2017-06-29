package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;

import daos.iface.DAOPrecio;
import data.Tag;
import data.TipoAccionPublicitaria;
import data.precios.Precio;
import data.precios.PrecioAccionPublicitaria;
import data.precios.PrecioTag;

public class DAOPrecioNeodatis extends DAONeodatis<Precio> implements DAOPrecio {
	
	@Override
	public PrecioAccionPublicitaria getCurrentPriceOf(TipoAccionPublicitaria t){
		IQuery query = new SimpleNativeQuery(){
			public boolean match(PrecioAccionPublicitaria p) {
				
				TipoAccionPublicitaria tag = p.getObjetoValuable();
				
                return p.isCurrent() && t.equals(tag);
            }
		};
		
		openClient();
		
		Objects<PrecioAccionPublicitaria> objs = odb.getObjects(query);
		
		PrecioAccionPublicitaria toReturn = null;
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeClient();
		
		return toReturn;
		
		
	}

	@Override
	public PrecioTag getCurrentPriceOf(Tag t) {
		
		IQuery query = new SimpleNativeQuery(){
			public boolean match(PrecioTag p) {
				
				Tag tag = p.getObjetoValuable();
				
                return p.isCurrent() && t.equals(tag);
            }
			
		};
		
		openClient();
		
		Objects<PrecioTag> objs = odb.getObjects(query);
		
		PrecioTag toReturn = null;
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeClient();
		
		return toReturn;
	
	}

	@Override
	public List<PrecioTag> getAllPricesOf(Tag t) {

		IQuery query = new SimpleNativeQuery(){
			public boolean match(PrecioTag p) {
				
				Tag tag = (Tag)p.getObjetoValuable();
				
                return t.equals(tag);
            }
			
		};
		
		openClient();
		
		Objects<PrecioTag> objs = odb.getObjects(query);
		
		closeClient();
		
		List<PrecioTag> toRet = new ArrayList<>(objs);
		toRet.sort((p1, p2) -> p1.getFechaCreacion().compareTo(p2.getFechaCreacion()));
		
		return toRet;
		
	}
	
	
	

}
