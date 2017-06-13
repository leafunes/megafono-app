package view.camp;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Campaña;

public class CampañaEditDescripcion extends FormLayout implements CampañaEditor{
	
	public static final String NAME = "EditDescrCamp";
	
	private TextField nombre;
	private TextArea descr;
	
	
	public CampañaEditDescripcion() {
		
		nombre = new TextField("Nombre: ");
		descr = new TextArea("Descripcion: ");
		
		addComponent(nombre);
		addComponent(descr);
		
	}


	@Override
	public void editCampaña(Campaña c) {
		nombre.setValue(c.getNombre());
		
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
