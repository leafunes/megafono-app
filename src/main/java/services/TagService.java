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
	
	public List<Tag> getAllTags(){
		
		return daoTag.getAllTags();
	}
	

}
