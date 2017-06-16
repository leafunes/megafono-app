package view.camp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Campania;
import misc.MessageBox;
import misc.Procedure;

public class CampaniaPanel extends Panel implements View{

	public static final String NAME = "CampPanel";
	
	private HashMap<String, CampaniaEditor> pages;
	private List<String> pagesOrder;
	private Panel navigable;
	private VerticalLayout mainLayout;
	private Button nextBtt;
	
	private Campania currentCampania;
	
	private int currentPageIndex;
	private MessageBox msgBox = MessageBox.getMessageBox();
	
	public CampaniaPanel() {
		
		super("Crear Campaña");
		msgBox.suscribirse("CreateNewCamp", () -> createCampania());
		
		initPages();
		
		navigable = new Panel();
		
		mainLayout = new VerticalLayout();
		
		nextBtt = new Button("Siguiente");
		
		navigable.setContent(pages.get(pagesOrder.get(0)));
		
		mainLayout.addComponent(navigable);
		mainLayout.addComponent(nextBtt);
		
		nextBtt.addClickListener(e -> nextPage());
		
		navigable.setStyleName(ValoTheme.PANEL_BORDERLESS);
		setContent(mainLayout);
	}

	private void createCampania() {
		
		currentCampania = new Campania("Nueva Campaña", "");
		editCampania(currentCampania);
		
	}

	private void editCampania(Campania c) {
		pages.forEach((k, v) -> v.editCampania(c));
		
	}

	private void nextPage() {
		
		if(currentPageIndex < pages.size() - 1){
			String nameOfNextView = pagesOrder.get(currentPageIndex + 1);
			navigable.setContent(pages.get(nameOfNextView));
			currentPageIndex++;
		}
		else{
			pages.forEach((k, v) -> v.commit());
			pages.forEach((k, v) -> v.clear());
		}
		
	}

	private void initPages() {
		pages = new HashMap<>();
		pages.put(CampaniaEditDescripcion.NAME, new CampaniaEditDescripcion());
		pages.put(CampaniaEditMensaje.NAME, new CampaniaEditMensaje());
		
		pagesOrder = Arrays.asList(CampaniaEditDescripcion.NAME, 
								CampaniaEditMensaje.NAME);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
