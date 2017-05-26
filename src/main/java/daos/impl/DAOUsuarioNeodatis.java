package daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAORol;
import daos.iface.DAOUsuario;
import data.Permiso;
import data.Rol;
import data.Usuario;

public class DAOUsuarioNeodatis extends DAONeodatis<Usuario>implements DAOUsuario{
	
	//TODO pasar a neodatis
	
	List<Usuario> db;

	public DAOUsuarioNeodatis() {
		db = new ArrayList<>();
		
		DAORol roles = new DAORolNeodatis();
		
		Rol rolAnalista = roles.getRolByName("analista");
		
		Usuario lea = new Usuario("lea@lea.com", "8e139f7d88e2832bd2d7c5457a66d049", rolAnalista);
		//psw: leandroFunes
		db.add(lea);
	}
	
	@Override
	public boolean isUsernameInUse(String username) {
		
		for (Usuario u : db) {
			if (u.getEmail() == username)
				return true;
		}
		
		return false;
		
	}

	@Override
	public boolean existeUsuario(String username, String md5) {
		return getUsuario(username, md5) != null;
	}
	
	@Override
	public void save(Usuario u){
		
		db.add(u);
		
	}

	@Override
	public Usuario getUsuario(String username, String md5) {
		for (Usuario u : db) {
			if(u.getEmail().equals(username) && u.getMd5().equals(md5))
				return u;
		}
		
		return null;
	}
	
	

	

}
