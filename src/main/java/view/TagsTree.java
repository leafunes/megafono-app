package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.Tree.TreeTargetDetails;
import com.vaadin.ui.themes.ValoTheme;

import data.Tag;
import misc.MessageBox;
import services.TagService;

public class TagsTree extends Panel{
	
	private Tree tagsTree;
	private TagService tagService;
	private Tag tagSelected;
	
	private Map<String, Boolean> isExtended;
	
	private MessageBox messageBox = MessageBox.getMessageBox();
	
	public TagsTree(TagsEdit editPanel) {
		
		messageBox.suscribirse("NewTag", () -> loadTree());
		
		tagService = TagService.getService();
		
		isExtended = new HashMap<>();
		
		tagsTree = new Tree();
		tagsTree.setSizeFull();
		
		
		
		tagsTree.addExpandListener(event -> isExtended.put((String)event.getItemId(), true));
		

		tagsTree.addCollapseListener(event -> isExtended.put((String)event.getItemId(), false));
		
		tagsTree.addValueChangeListener(event -> {
		    if (event.getProperty() != null && event.getProperty().getValue() != null) {
				String tagSelectedName = (String) event.getProperty().getValue();
				
				tagSelected = tagService.getTagByName(tagSelectedName);
				
				editPanel.editTag(tagSelected);
		    }
		    else{
		    	tagSelected = null;
		    }
		});
		
		
		loadTree();
		
		setContent(tagsTree);
		setSizeFull();
		setStyleName(ValoTheme.PANEL_BORDERLESS);
		
		
	}
	
	private void loadTree(){
		
		tagsTree.removeAllItems();

		List<Tag> tags = tagService.getAllTags();
		
		tags.forEach(t -> {
			tagsTree.addItem(t.getNombre());
			if(!isExtended.containsKey(t.getNombre()))
				isExtended.put(t.getNombre(), false);
		});
		
		for (Tag t: tags) {

			if(isExtended.get(t.getNombre()))
				tagsTree.expandItem(t.getNombre());
			
			tagsTree.setChildrenAllowed(t.getNombre(), false);
			
			if(t.getPadre() != null){

				tagsTree.setChildrenAllowed(t.getPadre().getNombre(), true);

				tagsTree.setParent(t.getNombre(), t.getPadre().getNombre());


			}
		}
		
	}
	
	public void addTag(String name){
		
		tagService.addTag(new Tag(name, tagSelected));
		
		isExtended.put(name, false);

		tagsTree.addItem(name);
		tagsTree.setChildrenAllowed(name, false);
		
		if(tagSelected != null){
			tagsTree.setChildrenAllowed(tagSelected.getNombre(), true);
			tagsTree.setParent(name, tagSelected.getNombre());
			
			tagsTree.expandItem(tagSelected.getNombre());
			isExtended.put(tagSelected.getNombre(), true);
		}
		
		tagsTree.select(name);//Para que se edite
	}
	
	

}
