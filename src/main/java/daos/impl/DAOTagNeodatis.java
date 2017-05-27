package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOTag;
import data.Tag;
import misc.NeodatisServerContentListener;

public class DAOTagNeodatis extends DAONeodatis<Tag> implements DAOTag{
	
	public DAOTagNeodatis(){
		
	}

	@Override
	public List<Tag> getAllTags() {
		
		List<Tag> toReturn = new ArrayList<>();
		
		openServer();
		
		Objects<Tag> objects = odb.getObjects(Tag.class);
		
		closeServer();
		
		toReturn.addAll(objects);
		
		return toReturn;
		
	}

	@Override
	public Tag getTagByName(String nombre) {
		
		IQuery query = new CriteriaQuery(Tag.class, Where.equal("nombre", nombre));
		
		openServer();
		
		Tag toReturn = null;
		Objects<Tag> objs = odb.getObjects(query);
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeServer();
		
		return toReturn;
		
	}

	@Override
	public Tag getParentOf(Tag t) {
		
		IQuery query = new CriteriaQuery(Tag.class, Where.equal("padre.nombre", t.getPadre().getNombre()));
		
		openServer();

		Tag toReturn = null;
		Objects<Tag> objs = odb.getObjects(query);
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeServer();
		
		return toReturn;

	}

}
