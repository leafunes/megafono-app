package services;

import java.util.ArrayList;
import java.util.List;

import daos.iface.DAOPrecio;
import daos.iface.DAOTag;
import daos.impl.DAOPrecioNeodatis;
import daos.impl.DAOTagNeodatis;
import data.Precio;
import data.Tag;

public class TagService {
	
	private static TagService singleton;
	private DAOTag daoTag;
	private DAOPrecio daoPrecio;
	
	public static TagService getService(){
		if(singleton == null)
			singleton = new TagService();
		return singleton;
	}
	
	private TagService(){
		
		daoTag = new DAOTagNeodatis();
		daoPrecio = new DAOPrecioNeodatis();
		
	}
	
	public Tag getTagByName(String name){
		
		return daoTag.getTagByName(name);
		
	}
	
	public void actualizeTag(Tag t){
		daoTag.save(t);
	}
	
	public void createTag(Tag t){
		
		Precio primerPrecio = new Precio();
		primerPrecio.setObjetoValuable(t);
		
		daoPrecio.save(primerPrecio);
		
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
		
		List<Tag> childrensOf = daoTag.getChildrenOf(tag);
		
		childrensOf.forEach(t -> t.setHabilitado(tag.isHabilitado()));
		
		childrensOf.forEach(t -> actulizeHabilitations(t));
		
		daoTag.saveAll(childrensOf);
		
	}
	

}
