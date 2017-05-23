package daos.iface;

public interface DAO <T>{
	
	public void save(T t);
	public void delete(T t);

}
