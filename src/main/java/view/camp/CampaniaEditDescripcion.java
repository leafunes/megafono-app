package view.camp;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import services.CampaniaService;
import view.Function;
import view.ViewValidator;

public class CampaniaEditDescripcion extends FormLayout implements CampaniaEditor{
	
	public static final String NAME = "EditDescrCamp";
	
	private ViewValidator validator;
	
	private TextField nombre;
	private TextArea descr;
	
	private Campania currentCampania;
	
	private BeanFieldGroup<Campania> binder;
	
	public CampaniaEditDescripcion() {
		
		nombre = new TextField("Nombre: ");
		descr = new TextArea("Descripcion: ");
		
		validator = new ViewValidator();
		validator.isNotEmpty(nombre::getValue, "Debe ingresar un nombre");
		
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
			
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void clear() {
		
		
	}


	@Override
	public boolean isValid() {
		
		return validator.testAll();
		
	}




}
