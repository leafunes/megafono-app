package daos.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

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
		//saveEnums(t);
		
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
	
	private void saveEnums(Object t){
		
		Class<?> clazz = t.getClass();
		
		if(clazz.isAssignableFrom(Collection.class)) 
			((Collection<?>)t) 
			.forEach(o -> saveEnums(o));
		
		
		else if(!clazz.isPrimitive())
			Arrays.asList(clazz.getDeclaredFields())
			.forEach(f -> callSaveEnumRecursion(f, t));
	
	}
	
	private void callSaveEnumRecursion(Field f, Object t){
	
		f.setAccessible(true);
		
		try {
			if(f.getType().isEnum())saveOneEnum(f.get(t).toString(), t.getClass(), t);
			else saveEnums(f.get(t));
			
		}
		catch (IllegalArgumentException | IllegalAccessException e) {e.printStackTrace();}
		
		f.setAccessible(false);
	}
	
	private void saveOneEnum(String enumValue, Class<?> enumClass, Object obj){
		
		System.out.println("Guardando " + enumValue + " del tipo " + enumClass + " del objeto " + obj.toString());
		
		IQuery query = new CriteriaQuery(NeodatisEnumContainer.class, Where.and()
															.add(Where.equal("enumClass", enumClass))
															.add(Where.equal("obj", obj)));
		
		Objects<NeodatisEnumContainer> objs = odb.getObjects(query);
		
		if(objs.isEmpty()) odb.store(new NeodatisEnumContainer(enumValue, enumClass, obj));
		else{
			NeodatisEnumContainer enumCont = objs.getFirst();
			enumCont.setEnumValue(enumValue);
			odb.store(enumCont);
		}
		
	}
	
	protected void openClient(){
		
		odb = NeodatisServerContentListener.getServer().openClient(DBLocation);
		
	}
	
	protected void closeClient(){
		if(!odb.isClosed())
			odb.close();
	}


}
