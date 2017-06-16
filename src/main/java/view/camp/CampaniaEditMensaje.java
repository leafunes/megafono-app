package view.camp;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

import data.Campania;
import data.MensajeCampania;

public class CampaniaEditMensaje extends FormLayout implements CampaniaEditor{
	
	public static final String NAME = "EditMsgCamp";
	
	private Campania currentCampania;
	private MensajeCampania mensajeCampania;
	
	public CampaniaEditMensaje() {
		
		addComponent(new Label("wesa"));
		
	}

	@Override
	public void editCampania(Campania c) {
		currentCampania = c;
		
		mensajeCampania = new MensajeCampania("", null);
		currentCampania.setMensaje(mensajeCampania);
		
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
