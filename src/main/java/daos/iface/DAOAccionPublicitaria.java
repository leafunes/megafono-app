package daos.iface;

import java.util.List;

import data.AccionPublicitaria;
import data.Campania;
import data.Tag;

public interface DAOAccionPublicitaria extends DAO<AccionPublicitaria>{

	public List<AccionPublicitaria> getAllActionsOf(Tag t);

	public List<AccionPublicitaria> getAllActionsOf(Campania c);
	
	

}
