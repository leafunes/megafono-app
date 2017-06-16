package view.camp;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import services.CampaniaService;

public class CampaniaEditDescripcion extends FormLayout implements CampaniaEditor{
	
	public static final String NAME = "EditDescrCamp";
	
	private TextField nombre;
	private TextArea descr;
	
	private Campania currentCampania;
	
	private BeanFieldGroup<Campania> binder;
	private CampaniaService campaniaService;
	
	public CampaniaEditDescripcion() {
		
		campaniaService = CampaniaService.getService();
		
		nombre = new TextField("Nombre: ");
		descr = new TextArea("Descripcion: ");
		
		addComponent(nombre);
		addComponent(descr);
		
	}


	@Override
	public void editCampania(Campania c) {
		currentCampania = c;
		binder = BeanFieldGroup.bindFieldsBuffered(currentCampania, this);
		
		binder.bind(nombre, "nombre");
		binder.bind(descr, "descripcion");
		
	}
	

	@Override
	public void commit() {
		try {
			
			binder.commit();
			campaniaService.saveCampania(currentCampania);
			
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}




}
