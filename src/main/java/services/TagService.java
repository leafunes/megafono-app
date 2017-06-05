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
	
	public List<Tag> getAllHabilitedTags(){
		
		return daoTag.getAllHabilitedTags();
	}

	public void deleteTag(Tag tag) {
		
		
		List<Tag> childrensOf = daoTag.getChildrenOf(tag);
		
		daoTag.deleteAll(childrensOf);
		daoTag.delete(tag);
		
	}

	public void actulizeHabilitations(Tag tag) {
		
		Tag oldTag = daoTag.getTagByName(tag.getNombre());
		
		if(oldTag.isHabilitado() != tag.isHabilitado()){
			List<Tag> childrensOf = daoTag.getChildrenOf(tag);
			
			childrensOf.forEach(t -> t.setHabilitado(tag.isHabilitado()));
			
			childrensOf.forEach(t -> actulizeHabilitations(t));
			
			daoTag.saveAll(childrensOf);
		}
		
	}
	

}
