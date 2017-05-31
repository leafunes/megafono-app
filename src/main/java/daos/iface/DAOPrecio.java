package daos.iface;

import data.Precio;
import data.Tag;

public interface DAOPrecio extends DAO<Precio>{
	
	public Precio getCurrentPriceOf(Object o);

}
