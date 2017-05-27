package daos.iface;

import java.util.List;

import data.Tag;

public interface DAOTag extends DAO<Tag>{
	
	public List<Tag> getAllTags();
	public Tag getTagByName(String nombre);
	public Tag getParentOf(Tag t);

}
