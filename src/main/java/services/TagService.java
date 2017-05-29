package services;

import java.util.ArrayList;
import java.util.List;

import daos.iface.DAOTag;
import daos.impl.DAOTagNeodatis;
import data.Tag;

public class TagService {
	
	private static TagService singleton;
	private DAOTag daoTag;
	
	public static TagService getService(){
		if(singleton == null)
			singleton = new TagService();
		return singleton;
	}
	
	private TagService(){
		
		daoTag = new DAOTagNeodatis();
		
	}
	
	public Tag getTagByName(String name){
		
		return daoTag.getTagByName(name);
		
	}
	
	public void addTag(Tag t){
		daoTag.save(t);
		
	}
	
	public void setPadre(Tag hijo, Tag padre){
		
		hijo.setPadre(padre);
		
		daoTag.save(hijo);
		
	}
	
	public List<Tag> getAllHabilitedTags(){
		
		return daoTag.getAllHabilitedTags();
	}

	public void deleteTag(Tag tag) {
		
		tag.setHabilitado(false);
		
		List<Tag> childrensOf = daoTag.getChildrensOf(tag);
		
		childrensOf.forEach(t -> t.setHabilitado(false));
		
		daoTag.saveAll(childrensOf);
		daoTag.save(tag);
		
	}
	

}
