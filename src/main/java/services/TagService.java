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
	
	public Tag cloneTag(Tag toClone){
		Tag toReturn = new Tag(toClone.getNombre(), toClone.getPrecio());
		
		toReturn.setPadre(toClone.getPadre());
		
		return toReturn;
	}
	
	public Tag getTagByName(String name){
		
		return daoTag.getTagByName(name);
		
	}
	
	public void addTag(Tag t){
		daoTag.save(t);
		
	}
	
	public void actualizeTag(Tag oldTag, Tag newTag){
		daoTag.actualize(oldTag, newTag);
	}
	
	public List<Tag> getAllTags(){
		
		return daoTag.getAllTags();
	}
	

}
