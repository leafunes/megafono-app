package daos.impl;

import java.util.Collection;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OdbConfiguration;

import daos.iface.DAO;
import misc.GlobalProperties;
import misc.NeodatisServerContentListener;

public class DAONeodatis<T> implements DAO<T> {
	
	protected final String DBLocation = GlobalProperties.getGlobalProps().getProps().getProperty("DBLocation");
	protected ODB odb;
	
	public DAONeodatis() {

		
	}
	
	@Override
	public void save(T t) {
		
		openClient();
		
		odb.store(t);
		
		closeClient();
		
	}

	@Override
	public void delete(T t) {
		openClient();
		
		odb.delete(t);
		
		closeClient();
		
	}
	
	@Override
	public void saveAll(Collection<T> colection) {
		openClient();
		
		colection.forEach(t -> save(t));
		
		closeClient();
		
	}

	@Override
	public void deleteAll(Collection<T> colection) {
		
		openClient();
		
		colection.forEach(t -> delete(t));
		
		closeClient();
		
	}
	
	protected void openClient(){

		odb = NeodatisServerContentListener.getServer().openClient(DBLocation);
		
	}
	
	protected void closeClient(){
		if(!odb.isClosed())
			odb.close();
	}


}
