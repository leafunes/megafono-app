package view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Tag;
import services.TagService;

public class TagsTree extends Panel{
	
	private Tree tagsTree;
	private TagService tagService;
	
	public TagsTree() {
		
		tagService = TagService.getService();
		
		tagsTree = new Tree();
		
		//loadTree();
		
		setContent(tagsTree);
		setSizeFull();
		setStyleName(ValoTheme.PANEL_BORDERLESS);
		
		
	}
	
	private void loadTree(){

		List<Tag> tags = tagService.getRootTags();
		
		tags.forEach(t -> tagsTree.addItem(t));
		
		for (Tag t: tags) if(t.getPadre() != null){
			
			tagsTree.setParent(t.getNombre(), t.getPadre().getNombre());
			
		}
		
		
	}
	
	

}
