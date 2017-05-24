package services;

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
	
	public List<Tag> getRootTags(){
		return daoTag.getRootTags();
	}
	

}
