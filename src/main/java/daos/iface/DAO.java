package daos.iface;

import java.util.Collection;

public interface DAO <T>{
	
	public void save(T t);
	public void delete(T t);
	public void saveAll(Collection<T> colection);
	public void deleteAll(Collection<T> colection);

}
