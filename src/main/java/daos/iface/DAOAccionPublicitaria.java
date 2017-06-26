package daos.iface;

import java.util.List;

import data.AccionPublicitaria;
import data.Tag;

public interface DAOAccionPublicitaria extends DAO<AccionPublicitaria>{

	public List<AccionPublicitaria> getAllActionsOf(Tag t);
	
	

}
