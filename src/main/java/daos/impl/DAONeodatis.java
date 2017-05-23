package daos.impl;

import org.neodatis.odb.ODB;

import daos.iface.DAO;
import misc.GlobalProperties;
import misc.NeodatisServerContentListener;

public class DAONeodatis<T> implements DAO<T> {
	
	protected final String DBLocation = GlobalProperties.getGlobalProps().getProps().getProperty("DBLocation");
	protected ODB odb;
	
	@Override
	public void save(T t) {
		
		openServer();
		
		odb.store(t);
		
		closeServer();
		
	}

	@Override
	public void delete(T t) {
		openServer();
		
		odb.delete(t);
		
		closeServer();
		
	}
	
	protected void openServer(){
		odb = NeodatisServerContentListener.getServer().openClient(DBLocation);
	}
	
	protected void closeServer(){
		odb.close();
	}

}
