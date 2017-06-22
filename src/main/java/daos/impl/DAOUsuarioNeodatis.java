package daos.impl;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOUsuario;
import data.Usuario;

public class DAOUsuarioNeodatis extends DAONeodatis<Usuario>implements DAOUsuario{
	
	@Override
	public boolean isUsernameInUse(String username) {
		
		IQuery query = new CriteriaQuery(Usuario.class, Where.equal("email", username));
		
		openClient();
		
		Objects<Usuario> objs = odb.getObjects(query);
		
		closeClient();
		
		return !objs.isEmpty();
		
	}

	@Override
	public boolean existeUsuario(String username, String md5) {
		return getUsuario(username, md5) != null;
	}

	@Override
	public Usuario getUsuario(String username, String md5) {
		
		IQuery query = new CriteriaQuery(Usuario.class, Where.and()
														.add(Where.equal("email", username))
														.add(Where.equal("pswMd5", md5)));
		
		Usuario toReturn = null;
		
		openClient();
		
		Objects<Usuario> objs = odb.getObjects(query);
		
		closeClient();
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		return toReturn;
	}
	

}
