package view.camp;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import services.CampaniaService;
import services.UsuarioService;

public class CampaniaViewer extends Panel implements View{
	
	static public final String NAME = "campViewer";
	
	private Grid grid;
	private BeanItemContainer<Campania> container;
	private CampaniaService campaniaService;
	private UsuarioService usuarioService;
	
	public CampaniaViewer() {
		
		campaniaService = CampaniaService.getService();
		usuarioService = UsuarioService.getService();
		
		VerticalLayout mainLayout = new VerticalLayout();
		container = new BeanItemContainer<>(Campania.class, campaniaService.getAllCampaniasOf(usuarioService.getLoggedUser()));
		
		grid = new Grid(container);
		
		mainLayout.addComponent(grid);
		
		setContent(mainLayout);
		
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
