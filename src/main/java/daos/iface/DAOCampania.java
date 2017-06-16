package daos.iface;

import java.util.List;

import data.Campania;
import data.Usuario;

public interface DAOCampania extends DAO<Campania>{
	
	public List<Campania> getAllCampaniasOf(Usuario u);

}
