package view.camp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Campania;
import misc.MessageBox;
import misc.Procedure;
import services.CampaniaService;

public class CampaniaPanel extends Panel implements View{

	public static final String NAME = "CampPanel";
	
	private HashMap<String, CampaniaEditor> pages;
	private List<String> pagesOrder;
	private Panel navigable;
	private GridLayout mainLayout;
	private Button nextBtt;
	private Button prevBtt;
	
	private Campania currentCampania;
	
	private int currentPageIndex;
	private MessageBox msgBox = MessageBox.getMessageBox();
	private CampaniaService campaniaService;
	
	public CampaniaPanel() {
		
		super("Crear Campaña");
		setSizeFull();
		msgBox.suscribirse("CreateNewCamp", () -> createCampania());
		campaniaService = CampaniaService.getService();
		
		initPages();
		
		navigable = new Panel();
		
		mainLayout = new GridLayout(4, 4);
		
		nextBtt = new Button("Siguiente");
		prevBtt = new Button("Anterior");
		
		navigable.setContent(pages.get(pagesOrder.get(0)));
		
		mainLayout.addComponent(navigable, 0, 0, 3, 1);
		mainLayout.addComponent(nextBtt, 3, 2);
		mainLayout.addComponent(prevBtt, 1, 2);
		
		mainLayout.setRowExpandRatio(0, 80);
		
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setComponentAlignment(nextBtt, Alignment.MIDDLE_LEFT);
		nextBtt.addClickListener(e -> nextPage());
		prevBtt.addClickListener(e -> prevPage());
		
		navigable.setStyleName(ValoTheme.PANEL_BORDERLESS);
		navigable.setSizeFull();
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
	
		String nameOfCurrentView = pagesOrder.get(currentPageIndex);
		CampaniaEditor currentEditor = pages.get(nameOfCurrentView);
		
		if(currentEditor.isValid()){
			if(currentPageIndex < pages.size() - 1){
				String nameOfNextView = pagesOrder.get(currentPageIndex + 1);
				navigable.setContent(pages.get(nameOfNextView));
				currentPageIndex++;
			}
			else{
				pages.forEach((k, v) -> v.commit());
				pages.forEach((k, v) -> v.clear());
				campaniaService.saveCampania(currentCampania);
			}
		}

		if(currentPageIndex == pages.size() - 1)
			nextBtt.setCaption("Ok");
		
	}
	
	private void prevPage() {
		
		if(currentPageIndex > 0){
			String nameOfPrevView = pagesOrder.get(currentPageIndex - 1);
			navigable.setContent(pages.get(nameOfPrevView));
			currentPageIndex--;
			
		}

			nextBtt.setCaption("Siguiente");
		
	}

	private void initPages() {
		pages = new HashMap<>();
		pages.put(CampaniaEditDescripcion.NAME, new CampaniaEditDescripcion());
		pages.put(CampaniaEditMensaje.NAME, new CampaniaEditMensaje());
		pages.put(CampaniaEditTags.NAME, new CampaniaEditTags());
		pages.put(CampaniaEditAciones.NAME, new CampaniaEditAciones());
		pages.put(CampaniaEditDuration.NAME, new CampaniaEditDuration());
		
		pagesOrder = Arrays.asList(CampaniaEditDescripcion.NAME,
								CampaniaEditDuration.NAME,
								CampaniaEditMensaje.NAME,
								CampaniaEditTags.NAME,
								CampaniaEditAciones.NAME);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		if(currentCampania == null)
			createCampania();
		
		
	}
	
}
