package view.camp;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

import data.Campaña;

public class CampañaEditMensaje extends FormLayout implements CampañaEditor{
	
	public static final String NAME = "EditMsgCamp";
	
	public CampañaEditMensaje() {
		
		addComponent(new Label("wesa"));
		
	}

	@Override
	public void editCampaña(Campaña c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

}
