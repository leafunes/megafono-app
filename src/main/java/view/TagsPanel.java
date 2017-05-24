package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TagsPanel extends Panel implements View{

	private static final long serialVersionUID = 1L;

	public static final String NAME = "TAGS";
	
	private HorizontalSplitPanel mainSplit;
	
	public TagsPanel() {
		super("Tags");
		
		mainSplit = new HorizontalSplitPanel();
		
		Tree tags = new Tree();
		//TODO
		tags.addItem("TAG PRUEBA");
		tags.addItem("TAG PRUEBA 2ASDASDASDASDASDASDASDASD");
		tags.setParent("TAG PRUEBA 2ASDASDASDASDASDASDASDASD", "TAG PRUEBA");
		tags.setChildrenAllowed("TAG PRUEBA 2ASDASDASDASDASDASDASDASD", false);
		//tags.setSizeFull();

		Panel treePanel = new Panel();
		
		treePanel.setContent(tags);
		treePanel.setSizeFull();
		treePanel.setStyleName(ValoTheme.PANEL_BORDERLESS);
		
		Button agregaTagBtt = new Button("Agregar");
		agregaTagBtt.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		agregaTagBtt.setWidth(100, Unit.PERCENTAGE);
		
		VerticalLayout leftLayout = new VerticalLayout();
		leftLayout.addComponent(treePanel);
		leftLayout.addComponent(agregaTagBtt);
		leftLayout.setComponentAlignment(agregaTagBtt, Alignment.MIDDLE_CENTER);

		leftLayout.setSizeFull();
		leftLayout.setExpandRatio(treePanel, 9);
		
		Panel editPanel = new Panel();
		
		editPanel.setContent(new Label("TODO"));
		
		mainSplit.setFirstComponent(leftLayout);
		mainSplit.setSecondComponent(editPanel);
		
		mainSplit.setSplitPosition(25, Unit.PERCENTAGE);
		mainSplit.setLocked(true);
		mainSplit.setStyleName(ValoTheme.SPLITPANEL_LARGE);
		
		this.setSizeFull();
		this.setContent(mainSplit);
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
