package daos.impl;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAORol;
import data.Rol;

public class DAORolNeodatis extends DAONeodatis<Rol> implements DAORol{
	

	@Override
	public boolean existeRol(String nombre) {
		
		return getRolByName(nombre) != null;
		
	}

	@Override
	public Rol getRolByName(String nombre) {
		IQuery query = new CriteriaQuery(Rol.class, Where.equal("nombre", nombre));
		
		Rol toReturn = null;
		
		openClient();
		
		Objects<Rol> objs = odb.getObjects(query);
		
		closeClient();
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		return toReturn;
	}

}
