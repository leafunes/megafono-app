package view;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Tree;

import view.tags.TagsPanel;

public class MenuAnalistaTree extends Tree{
	
	private static final long serialVersionUID = 1L;
	
	private String gestionLbl = "Gestion";
	private String tagsLbl = "Tags";
	private String accionesLbl = "Acciones";
	private String reportingLbl = "Reporting";
	private String wesaLbl = "Wesa";
	
	public MenuAnalistaTree() {
		
		addItem(gestionLbl);
		/**/addItem(tagsLbl);
		/**/addItem(accionesLbl);
		
		addItem(reportingLbl);
		/**/addItem(wesaLbl);
		
		setChildrenAllowed(tagsLbl, false);
		setChildrenAllowed(accionesLbl, false);
		setChildrenAllowed(wesaLbl, false);
		
		setParent(tagsLbl, gestionLbl);
		setParent(accionesLbl, gestionLbl);
		setParent(wesaLbl,reportingLbl);
		
		addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				Navigator nav = getUI().getNavigator();
				if (event.getButton() == MouseButton.LEFT){
					String ID = (String)event.getItemId();
					
					if(ID == tagsLbl)
						nav.navigateTo(TagsPanel.NAME);
					
				}
				
			}
		});

	}
	
	

}
