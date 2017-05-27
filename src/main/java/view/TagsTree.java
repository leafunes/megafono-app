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
		
		tagsTree.setDragMode(TreeDragMode.NODE);
		
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

		        TreeTargetDetails target = (TreeTargetDetails)
		            event.getTargetDetails();

		        // Get ids of the dragged item and the target item
		        Object sourceItemId = t.getData("itemId");
		        Object targetItemId = target.getItemIdOver();
		        Object preParent =  tagsTree.getParent(sourceItemId);

		        // On which side of the target the item was dropped
		        VerticalDropLocation location = target.getDropLocation();

		        HierarchicalContainer container = (HierarchicalContainer)
		        tagsTree.getContainerDataSource();

	            Object parentId = container.getParent(targetItemId);
		        
		        Tag sourceTag = tagService.getTagByName((String)sourceItemId);
		        Tag targetTag = tagService.getTagByName((String)targetItemId);
		        Tag parentTag = tagService.getTagByName((String)parentId);
		        System.out.println(sourceTag.getNombre());
		        System.out.println(targetTag.getNombre());
		        System.out.println(parentId);
		        // Drop right on an item -> make it a child
		        if (location == VerticalDropLocation.MIDDLE){
		            tagsTree.setChildrenAllowed(targetItemId, true);
		            tagsTree.setParent(sourceItemId, targetItemId);
		            tagService.setPadre(sourceTag, targetTag);
		        }

		        // Drop at the top of a subtree -> make it previous
		        else if (location == VerticalDropLocation.TOP) {
		            container.setParent(sourceItemId, parentId);
		            tagService.setPadre(sourceTag, parentTag);
		            if(tagsTree.isRoot(preParent))
		            	tagsTree.setChildrenAllowed(preParent, false);
		            container.moveAfterSibling(sourceItemId, targetItemId);
		            container.moveAfterSibling(targetItemId, sourceItemId);
		        }

		        // Drop below another item -> make it next
		        else if (location == VerticalDropLocation.BOTTOM) {
		            container.setParent(sourceItemId, parentId);
		            tagService.setPadre(sourceTag, parentTag);
		            if(tagsTree.isRoot(preParent))
		            	tagsTree.setChildrenAllowed(preParent, false);
		            container.moveAfterSibling(sourceItemId, targetItemId);
		        }
				
			}
		});
		
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
