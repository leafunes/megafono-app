package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOCampania;
import data.Campania;
import data.Usuario;

public class DAOCampaniaNeodatis extends DAONeodatis<Campania> implements DAOCampania{

	@Override
	public List<Campania> getAllCampaniasOf(Usuario u) {
		
		List<Campania> toRet = new ArrayList<>();
		
		//IQuery query = new CriteriaQuery(Campania.class, Where.equal("owner.email", u.getEmail()));
		//FIXME
		openClient();
		
		Objects<Campania> objs = odb.getObjects(Campania.class);
		
		closeClient();
		
		if(!objs.isEmpty())
			toRet.addAll(objs);
		
		return toRet;
		
	}

	
	
	
}
