package view.camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TreeTable;

import data.Campania;
import data.Tag;
import services.TagService;
import view.DropHandlerAcceptAll;

public class CampaniaEditTags extends VerticalLayout implements CampaniaEditor{
	
	public static final String NAME = "CampEditTags";
	
	private HorizontalSplitPanel split;
	private TreeTable tagsDisponibles;
	private TreeTable tagsElegidos;
	
	private Map<String, Tag> allTags;
	private Campania currentCampania;
	
	private TagService tagService;
	
	public CampaniaEditTags() {
		setSizeFull();
		allTags = new TreeMap<>();
		tagService = TagService.getService();
		tagService.getAllHabilitedTags().forEach(t -> allTags.put(t.getNombre(), t));
		
		split = new HorizontalSplitPanel();
		
		tagsDisponibles = new TreeTable();
		tagsElegidos = new TreeTable();
		
		tagsDisponibles.addContainerProperty("Nombre", String.class, null);
		tagsDisponibles.addContainerProperty("Descripcion", String.class, null);
		

		tagsElegidos.addContainerProperty("Nombre", String.class, null);
		tagsElegidos.addContainerProperty("Descripcion", String.class, null);
		
		//Se agregan todos los tags al arbol de disponibles
		allTags.forEach((id, tag) -> tagsDisponibles.addItem(getTagToTable(tag), id));

		Panel tagDisp = new Panel("Tags Disponibles", tagsDisponibles);
		Panel tagEleg = new Panel("Tags Eliegidos", tagsElegidos);
		tagEleg.setSizeFull();
		tagsElegidos.setSizeFull();
		tagDisp.setSizeFull();
		tagsDisponibles.setSizeFull();
		
		actualizeDisponibles();
		initDropHandlers();
		
		split.setFirstComponent(tagDisp);
		split.setSecondComponent(tagEleg);
		
		split.setLocked(true);
		
		addComponent(split);
	}

	private Object[] getTagToTable(Tag t){
		return new Object[]{t.getNombre(), t.getDescripcion()};
	}
	
	private void actualizeDisponibles() {
		
		tagsDisponibles.getItemIds().stream().map(id -> id.toString())
										.forEach(id -> setParent(tagsDisponibles, id));
		
		tagsDisponibles.getItemIds().stream().filter(id -> !tagsDisponibles.hasChildren(id))
											.forEach(id -> tagsDisponibles.setChildrenAllowed(id, false));
		
	}
	
	private void actualizeElegidos(){
		
		tagsElegidos.getItemIds().stream().map(id -> id.toString())
									.forEach(id -> setParent(tagsElegidos, id));
		
		tagsElegidos.getItemIds().stream().filter(id -> !tagsElegidos.hasChildren(id))
											.forEach(id -> tagsElegidos.setChildrenAllowed(id, false));

		
	}
	
	private void initDropHandlers() {
		
		tagsElegidos.setDragMode(TableDragMode.ROW);
		tagsDisponibles.setDragMode(TableDragMode.ROW);
		
		tagsElegidos.setDropHandler(getdropHandler(tagsDisponibles, tagsElegidos));
		tagsDisponibles.setDropHandler(getdropHandler(tagsElegidos, tagsDisponibles));
		
		tagsDisponibles.addExpandListener(e -> actualizeDisponibles());
		tagsElegidos.addExpandListener(e -> actualizeElegidos());
		
	}
	
	private void moveItem(TreeTable from, TreeTable to, Object id){
		
		if(from.hasChildren(id))
			from.getChildren(id).forEach(child -> moveItem(from, to, child));
		
		to.addItem(getTagToTable(allTags.get(id)), id);
		to.setCollapsed(id, from.isCollapsed(id));
		
		from.removeItem(id);
		
	}
	
	private void setParent(TreeTable t, String id){
		
		Tag padre = allTags.get(id).getPadre();
		
		if(padre!= null){
			t.setChildrenAllowed(padre.getNombre(), true);

			t.setParent(id, padre.getNombre());

		}
		
	}
	
	private DropHandler getdropHandler(TreeTable from, TreeTable to){
		return new DropHandlerAcceptAll() {
			
			@Override
			public void drop(DragAndDropEvent event) {
		        Transferable t = event.getTransferable();
		        Object sourceItemId = t.getData("itemId");
		        
		        if (t.getSourceComponent() != from) return;
		        
		        moveItem(from, to, sourceItemId);
		        actualizeDisponibles();
		        actualizeElegidos();
		        
		     }
		};
	}
	
	private void addChildrensToList(String id, List<Tag> l){
		
		l.add(allTags.get(id));

		if(tagsElegidos.hasChildren(id)){
			
			tagsElegidos.getChildren(id).stream().map(child -> child.toString())
											.forEach(child -> l.add(allTags.get(child)));
		
			tagsElegidos.getChildren(id).forEach(child -> addChildrensToList((String)child, l));
			
		}
		
	}

	@Override
	public void editCampania(Campania c) {
		currentCampania = c;
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() {
		List<Tag> toAdd = new ArrayList<>();
		
		tagsElegidos.getItemIds().stream()
								.filter(id -> tagsElegidos.isRoot(id))
								.map(id -> id.toString())
								.forEach(id -> addChildrensToList(id, toAdd));
		
		currentCampania.setTags(toAdd);
		
	}

}
