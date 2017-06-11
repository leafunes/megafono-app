package daos.iface;

import java.util.List;

import data.Precio;
import data.Tag;

public interface DAOPrecio extends DAO<Precio>{
	
	public Precio getCurrentPriceOf(Tag o);

	public List<Precio> getAllPricesOf(Tag t);

}
