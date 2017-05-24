package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class WelcomePanel extends Panel implements View{

	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		setSizeFull();
		setContent(new Label("Bienvienido al Sistema"));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
