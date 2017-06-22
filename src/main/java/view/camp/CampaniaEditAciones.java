package view.camp;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import data.TipoAccionPublicitaria;

public class CampaniaEditAciones extends VerticalLayout implements CampaniaEditor{
	
	public static final String NAME = "CampEditAcciones";
	
	private GridLayout mainLayout;
	
	public CampaniaEditAciones() {
		
		mainLayout = new GridLayout(5, 2);
		
		ComboBox combo = new ComboBox("Tipo: ");
		Arrays.asList(TipoAccionPublicitaria.values()).stream().map(t -> t.toString())
													.forEach(t -> combo.addItem(t));
		combo.select(TipoAccionPublicitaria.MAIL);
		
		mainLayout.addComponent(combo, 2, 1);
		mainLayout.addComponent(new TextField(), 3, 1);
		mainLayout.addComponent(new Button("+"), 4, 1);
		
		addComponent(mainLayout);
		
	}
	
	@Override
	public void editCampania(Campania c) {
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
