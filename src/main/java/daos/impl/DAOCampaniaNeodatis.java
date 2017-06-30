package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOCampania;
import data.Campania;
import data.EstadoCampania;
import data.Usuario;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DAOCampaniaNeodatis extends DAONeodatis<Campania> implements DAOCampania{

	@Override
	public List<Campania> getAllCampaniasOf(Usuario u) {
		
		List<Campania> toRet = new ArrayList<>();
		
		IQuery query = new CriteriaQuery(Campania.class, Where.equal("owner.email", u.getEmail()));
		
		openClient();
		
		Objects<Campania> objs = odb.getObjects(query);
		
		closeClient();
		
		if(!objs.isEmpty())
			toRet.addAll(objs);
		
		return toRet;
		
	}

	@Override
	public List<Campania> getAllActiveCampanias() {
		
		List<Campania> toRet = new ArrayList<>();
		//TODO
		//IQuery query = new CriteriaQuery(Campania.class, Where.equal("estado", EstadoCampania.ACTIVA));
		
		openClient();
		
		Objects<Campania> objs = odb.getObjects(Campania.class);
		
		closeClient();
		
		if(!objs.isEmpty())
			toRet.addAll(objs);
		
		return toRet;
	}

	
	
	
}
