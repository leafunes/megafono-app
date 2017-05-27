package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private TagsEdit editPanel;
	
	private Map<String, Boolean> state;
	
	private MessageBox messageBox = MessageBox.getMessageBox();
	
	public TagsTree(TagsEdit editPanel) {
		
		messageBox.suscribirse("NewTag", () -> loadTree());
		
		tagService = TagService.getService();
		
		this.editPanel = editPanel;
		
		tagsTree = new Tree();
		tagsTree.setSizeFull();
		tagsTree.setDragMode(TreeDragMode.NODE);
		
		initDropHandler();
		
		initListeners();
		
		loadTree();
		
		setContent(tagsTree);
		setSizeFull();
		setStyleName(ValoTheme.PANEL_BORDERLESS);
		
		
	}
	
	private void loadTree(){
		
		saveState();
		
		tagsTree.removeAllItems();

		List<Tag> tags = tagService.getAllTags();
		
		tags.forEach(t -> tagsTree.addItem(t.getNombre()));
		
		for (Tag t: tags) {
			
			tagsTree.setChildrenAllowed(t.getNombre(), false);
			
			if(t.getPadre() != null){

				tagsTree.setChildrenAllowed(t.getPadre().getNombre(), true);

				tagsTree.setParent(t.getNombre(), t.getPadre().getNombre());


			}
		}
		
		loadState();
		
	}
	
	public void addTag(String name){
		
		tagService.addTag(new Tag(name, tagSelected));

		tagsTree.addItem(name);
		tagsTree.setChildrenAllowed(name, false);
		tagsTree.select(name);//Para que se edite
		
		if(tagSelected != null){
			tagsTree.setChildrenAllowed(tagSelected.getNombre(), true);
			tagsTree.setParent(name, tagSelected.getNombre());
			
			tagsTree.expandItem(tagSelected.getNombre());
		}
		
	}
	
	private void initListeners(){
		
		tagsTree.addValueChangeListener(event -> {

	    	tagSelected = null;
			
		    if (event.getProperty() != null && event.getProperty().getValue() != null) {
				String tagSelectedName = (String) event.getProperty().getValue();
				
				tagSelected = tagService.getTagByName(tagSelectedName);
				
				editPanel.editTag(tagSelected);
		    }
		});
		
	}
	
	private void saveState(){

		state = new HashMap<>();
		
		tagsTree.getItemIds().forEach( id -> state.put((String)id, tagsTree.isExpanded(id)));
		
		state.forEach((id, expanded) -> System.out.println(id + " " + expanded));
		
	}
	
	private void loadState(){
		
		state.forEach((id, expanded) -> { if(expanded) tagsTree.expandItem(id); });
		
	}
	
	private void initDropHandler(){
		
		tagsTree.setDropHandler(new DropHandler() {
			
			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
			
			@Override
			public void drop(DragAndDropEvent event) {
				 // Wrapper for the object that is dragged
		        Transferable t = event.getTransferable();

		        // Make sure the drag source is the same tree
		        if (t.getSourceComponent() != tagsTree)
		            return;

		        TreeTargetDetails target = (TreeTargetDetails)event.getTargetDetails();

		        // Get ids of the dragged item and the target item
		        Object sourceItemId = t.getData("itemId");
		        Object targetItemId = target.getItemIdOver();
		        Object preParentId =  tagsTree.getParent(sourceItemId);
	            Object parentId = tagsTree.getParent(targetItemId);
		        
		        // The item can't move into itself
		        if(sourceItemId.equals(targetItemId))
		        	return;
		        
		        Tag sourceTag = tagService.getTagByName((String)sourceItemId);
		        Tag targetTag = tagService.getTagByName((String)targetItemId);
		        Tag parentTag = tagService.getTagByName((String)parentId);
		        
		        if(!tagsTree.hasChildren(preParentId))
	            	tagsTree.setChildrenAllowed(preParentId, false);

		        // On which side of the target the item was dropped
		        VerticalDropLocation location = target.getDropLocation();

		        // Drop right on an item -> make it a child
		        if (location == VerticalDropLocation.MIDDLE){
		            tagsTree.setChildrenAllowed(targetItemId, true);
		            tagsTree.setParent(sourceItemId, targetItemId);
		            tagsTree.expandItem(targetItemId);
		            
		            tagService.setPadre(sourceTag, targetTag);
		        }

		        else{
		            tagsTree.setParent(sourceItemId, parentId);
		            tagService.setPadre(sourceTag, parentTag);
		        }
		        
				
			}
		});
		
	}
	
	

}
