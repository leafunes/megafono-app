package view.camp;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Campaña;
import services.CampañaService;

public class CampañaEditDescripcion extends FormLayout implements CampañaEditor{
	
	public static final String NAME = "EditDescrCamp";
	
	private TextField nombre;
	private TextArea descr;
	
	private Campaña currentCampaña;
	
	private BeanFieldGroup<Campaña> binder;
	private CampañaService campañaService;
	
	public CampañaEditDescripcion() {
		
		campañaService = CampañaService.getService();
		
		nombre = new TextField("Nombre: ");
		descr = new TextArea("Descripcion: ");
		
		addComponent(nombre);
		addComponent(descr);
		
	}


	@Override
	public void editCampaña(Campaña c) {
		currentCampaña = c;
		binder = BeanFieldGroup.bindFieldsBuffered(currentCampaña, this);
		
		binder.bind(nombre, "nombre");
		binder.bind(descr, "descripcion");
		
	}
	

	@Override
	public void commit() {
		try {
			
			binder.commit();
			campañaService.saveCampaña(currentCampaña);
			
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}




}
