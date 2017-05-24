package daos.iface;

import java.util.List;

import data.Tag;

public interface DAOTag extends DAO<Tag>{
	
	public List<Tag> getRootTags();

}
