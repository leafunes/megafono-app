package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOTag;
import data.Tag;

public class DAOTagNeodatis extends DAONeodatis<Tag> implements DAOTag{
	
	public DAOTagNeodatis(){
		
	}

	@Override
	public List<Tag> getAllHabilitedTags() {
		
		List<Tag> toReturn = new ArrayList<>();
		
		IQuery query = new CriteriaQuery(Tag.class, Where.equal("habilitado", true));
		
		openClient();
		
		Objects<Tag> objects = odb.getObjects(query);
		
		closeClient();
		
		toReturn.addAll(objects);
		
		return toReturn;
		
	}

	@Override
	public Tag getTagByName(String nombre) {
		
		IQuery query = new CriteriaQuery(Tag.class, Where.equal("nombre", nombre));
		
		openClient();
		
		Tag toReturn = null;
		Objects<Tag> objs = odb.getObjects(query);
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeClient();
		
		return toReturn;
		
	}

	@Override
	public Tag getParentOf(Tag t) {
		
		IQuery query = new CriteriaQuery(Tag.class, Where.equal("padre.nombre", t.getPadre().getNombre()));
		
		openClient();

		Tag toReturn = null;
		Objects<Tag> objs = odb.getObjects(query);
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeClient();
		
		return toReturn;

	}

	@Override
	public List<Tag> getChildrenOf(Tag t) {
		List<Tag> toReturn = new ArrayList<>();
		
		IQuery query = new CriteriaQuery(Tag.class, Where.equal("padre.nombre", t.getNombre()));
		
		openClient();
		
		Objects<Tag> obj = odb.getObjects(query);
		
		closeClient();
		
		toReturn.addAll(obj);
		
		return toReturn;
	}

	@Override
	public List<Tag> getAllTags() {
		
		List<Tag> toReturn = new ArrayList<>();
		
		openClient();
		
		Objects<Tag> objs = odb.getObjects(Tag.class);
		
		closeClient();
		
		toReturn.addAll(objs);
		
		return toReturn;
	}

}
