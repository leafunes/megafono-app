package view;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Tree;

import misc.MessageBox;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.camp.CampaniaEditDescripcion;
import view.camp.CampaniaPanel;
import view.camp.CampaniaViewer;
import view.tags.TagsPanel;

public class MenuClienteTree extends Tree{
	
	private final String campaniasLbl = "Campañas";
	private final String crearCampLbl = "Crear";
	private final String verCampLbl = "Ver";
	
	private MessageBox msgBox = MessageBox.getMessageBox();
	
	public MenuClienteTree() {
		
		addItem(campaniasLbl);
		/**/addItem(crearCampLbl);
		/**/addItem(verCampLbl);
	
		setChildrenAllowed(crearCampLbl, false);
		setChildrenAllowed(verCampLbl, false);
		
		setParent(crearCampLbl, campaniasLbl);
		setParent(verCampLbl, campaniasLbl);
		

		
		addItemClickListener( e -> {
			if (e.getButton() == MouseButton.LEFT){

				Navigator nav = getUI().getNavigator();
				switch ((String)e.getItemId()) {
				case crearCampLbl:
					nav.navigateTo(CampaniaPanel.NAME);
					msgBox.publish("CreateNewCamp");
					break;
					
				case verCampLbl:
					nav.navigateTo(CampaniaViewer.NAME);

				default:
					break;
				}
				
				
				
			}
			
		});

	}

}
