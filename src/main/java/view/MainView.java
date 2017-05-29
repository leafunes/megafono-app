package view;

import java.io.File;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import view.tags.TagsPanel;


public class MainView extends VerticalLayout implements View{
	
	private static final long serialVersionUID = 1L;
	
	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	private String logoPath = basepath + "/WEB-INF/images/logo.png";
	
	private HorizontalSplitPanel mainSplit;
	private Panel menuPanel;
	private Panel logoPanel;
	private Panel mainPanel;
	
	
	public MainView() {

		setSizeFull();
		init();
		
	}
	
	private void init(){
		
		initLogo();
		initMenu();
		initMain();
		
		
		this.addComponent(logoPanel);
		this.addComponent(mainSplit);
		this.setExpandRatio(mainSplit, 5);

		
	}
	
	private void initMenu(){
		MenuTree menu = new MenuTree();
		menuPanel = new Panel();
		
		menu.setSizeFull();
		menuPanel.setContent(menu);
		menuPanel.setSizeFull();
		
	}
	
	private void initLogo(){
		FileResource logoResource = new FileResource(new File(logoPath));
		Image logoImg = new Image(null, logoResource);
		
		logoPanel = new Panel();
		logoPanel.setContent(logoImg);
		
	}
	
	private void initMain(){

		mainSplit = new HorizontalSplitPanel();

		mainPanel = new Panel();
		mainPanel.setSizeFull();
		
		mainSplit.setSizeFull();
		
		mainSplit.setFirstComponent(menuPanel);
		mainSplit.setSecondComponent(mainPanel);
		mainSplit.setLocked(true);
		mainSplit.setSplitPosition(15, Unit.PERCENTAGE);
		
	}
	
	protected void initNavigator(){

		Navigator nav = new Navigator(getUI(), mainPanel);
		
		
		nav.addView("", new WelcomePanel());
		nav.addView(TagsPanel.NAME, new TagsPanel());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
