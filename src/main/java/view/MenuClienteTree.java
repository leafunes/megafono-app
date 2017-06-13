package view;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Tree;

import misc.MessageBox;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.camp.CampañaEditDescripcion;
import view.camp.CampañaPanel;
import view.tags.TagsPanel;

public class MenuClienteTree extends Tree{
	
	private String campañasLbl = "Campañas";
	private String crearCampLbl = "Crear";
	private String verCampLbl = "Ver";
	
	private MessageBox msgBox = MessageBox.getMessageBox();
	
	public MenuClienteTree() {
		
		addItem(campañasLbl);
		/**/addItem(crearCampLbl);
		/**/addItem(verCampLbl);
	
		setChildrenAllowed(crearCampLbl, false);
		setChildrenAllowed(verCampLbl, false);
		
		setParent(crearCampLbl, campañasLbl);
		setParent(verCampLbl, campañasLbl);
		
		addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				Navigator nav = getUI().getNavigator();
				if (event.getButton() == MouseButton.LEFT){
					String ID = (String)event.getItemId();
					
					if(ID == crearCampLbl){
						nav.navigateTo(CampañaPanel.NAME);
						msgBox.publish("CreateNewCamp");
					}
					
				}
				
			}
		});

	}

}
