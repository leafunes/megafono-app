package view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Tag;
import misc.MessageBox;
import misc.Procedure;
import services.TagService;

public class TagsTree extends Panel{
	
	private Tree tagsTree;
	private TagService tagService;
	private Tag tagSelected;
	
	private MessageBox messageBox = MessageBox.getMessageBox();
	
	public TagsTree(TagsEdit editPanel) {
		
		messageBox.suscribirse("NewTag", () -> loadTree());
		
		tagService = TagService.getService();
		
		tagsTree = new Tree();
		tagsTree.setSizeFull();
		
		tagsTree.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				if(event.getButton() == MouseButton.LEFT){
					
					String tagSelectedName = (String)event.getItemId();
					
					tagSelected = tagService.getTagByName(tagSelectedName);
					
					System.out.println(tagSelected);
					
					editPanel.editTag(tagSelected);
					
				}
				
			}
		});
		
		loadTree();
		
		setContent(tagsTree);
		setSizeFull();
		setStyleName(ValoTheme.PANEL_BORDERLESS);
		
		
	}
	
	private void loadTree(){
		
		tagsTree.clear();

		List<Tag> tags = tagService.getAllTags();
		
		tags.forEach(t -> tagsTree.addItem(t.getNombre()));
		
		for (Tag t: tags) {
	
			if(t.getPadre() != null)
				tagsTree.setParent(t.getNombre(), t.getPadre().getNombre());
			else
				tagsTree.setChildrenAllowed(t.getNombre(), false);
			
		}
		
	}
	
	public void addTag(String name){
		
		tagService.addTag(new Tag(name, null));
		
		tagsTree.addItem(name);
		tagsTree.setChildrenAllowed(name, false);
		
		if(tagSelected != null){
			tagsTree.setParent(name, tagSelected.getNombre());
		}
	}
	
	

}
