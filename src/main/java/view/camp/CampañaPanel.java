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

import data.Campaña;
import misc.MessageBox;
import misc.Procedure;

public class CampañaPanel extends Panel implements View{

	public static final String NAME = "CampPanel";
	
	private HashMap<String, CampañaEditor> pages;
	private List<String> pagesOrder;
	private Panel navigable;
	private VerticalLayout mainLayout;
	private Button nextBtt;
	
	private Campaña currentCampaña;
	
	private int currentPageIndex;
	private MessageBox msgBox = MessageBox.getMessageBox();
	
	public CampañaPanel() {
		
		super("Crear Campaña");
		msgBox.suscribirse("CreateNewCamp", this::createCampaña);
		
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

	private void createCampaña() {
		
		currentCampaña = new Campaña("Nueva Campaña", "");
		editCampaña(currentCampaña);
		
	}

	private void editCampaña(Campaña c) {
		pages.forEach((k, v) -> v.editCampaña(c));
		
	}

	private void nextPage() {
		
		if(currentPageIndex < pages.size() - 1){
			String nameOfNextView = pagesOrder.get(currentPageIndex + 1);
			navigable.setContent(pages.get(nameOfNextView));
			currentPageIndex++;
		}
		
	}

	private void initPages() {
		pages = new HashMap<>();
		pages.put(CampañaEditDescripcion.NAME, new CampañaEditDescripcion());
		pages.put(CampañaEditMensaje.NAME, new CampañaEditMensaje());
		
		pagesOrder = Arrays.asList(CampañaEditDescripcion.NAME, 
								CampañaEditMensaje.NAME);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
