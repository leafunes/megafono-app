package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOAccionPublicitaria;
import data.AccionPublicitaria;
import data.Campania;
import data.Tag;

public class DAOAccionPublicitariaNeodatis extends DAONeodatis<AccionPublicitaria> 
											implements DAOAccionPublicitaria{

	@Override
	public List<AccionPublicitaria> getAllActionsOf(Tag t) {
		IQuery query = new CriteriaQuery(AccionPublicitaria.class, 
										Where.equal("tag.nombre", t.getNombre()));
		
		List<AccionPublicitaria> toRet = new ArrayList<>();
		
		openClient();
		
		Objects<AccionPublicitaria> objs = odb.getObjects(query);
		
		closeClient();
		
		if(!objs.isEmpty())
			toRet.addAll(objs);
		
		
		return toRet;
	}

	
	@Override
	public List<AccionPublicitaria> getAllActionsOf(Campania c) {
		IQuery query = new CriteriaQuery(AccionPublicitaria.class, 
				Where.equal("campania.nombre", c.getNombre()));

		List<AccionPublicitaria> toRet = new ArrayList<>();
		
		openClient();
		
		Objects<AccionPublicitaria> objs = odb.getObjects(query);
		
		closeClient();
		
		if(!objs.isEmpty())
		toRet.addAll(objs);
		
		
		return toRet;
	}
	

}
