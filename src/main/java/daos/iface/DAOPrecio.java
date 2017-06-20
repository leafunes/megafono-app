package daos.iface;

import java.util.List;

import data.Tag;
import data.precios.Precio;
import data.precios.PrecioTag;

public interface DAOPrecio extends DAO<Precio>{
	
	public PrecioTag getCurrentPriceOf(Tag o);

	public List<PrecioTag> getAllPricesOf(Tag t);

}
