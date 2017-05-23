package daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOUsuario;
import data.Usuario;

public class DAOUsuarioNeodatis extends DAONeodatis<Usuario>implements DAOUsuario{

	public DAOUsuarioNeodatis() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isUsernameInUse(String username) {
		
		CriteriaQuery query = new CriteriaQuery(Usuario.class, Where.equal("email", username));
		
		openServer();
		
		Objects<Usuario> lista = odb.getObjects(query);
		
		closeServer();
		
		return !lista.isEmpty();
		
	}

	

}
