package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOTag;
import data.Tag;

public class DAOTagNeodatis extends DAONeodatis<Tag> implements DAOTag{

	@Override
	public List<Tag> getRootTags() {
		
		List<Tag> toReturn = new ArrayList<>();
		
		CriteriaQuery query = new CriteriaQuery(Tag.class, Where.equal("isRoot", true));
		
		openServer();
		
		Objects<Tag> objects = odb.getObjects(query);
		
		closeServer();
		
		toReturn.addAll(objects);
		
		return toReturn;
		
	}


}
