package view.camp;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import services.CampaniaService;
import view.Function;
import view.ViewValidator;

public class CampaniaEditDescripcion extends GridLayout implements CampaniaEditor{
	
	public static final String NAME = "EditDescrCamp";
	
	private ViewValidator validator;
	
	private TextField nombre;
	private TextArea descr;
	private FormLayout mainLayout;
	
	private Campania currentCampania;
	
	private BeanFieldGroup<Campania> binder;
	
	public CampaniaEditDescripcion() {
		
		super(3,3);
		setSizeFull();
		
		nombre = new TextField("Nombre: ");
		descr = new TextArea("Descripcion: ");
		mainLayout = new FormLayout();
		
		validator = new ViewValidator();
		validator.isNotEmpty(nombre::getValue, "Debe ingresar un nombre");
		
		mainLayout.addComponent(nombre);
		mainLayout.addComponent(descr);
		
		
		addComponent(mainLayout,1,1);
		
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
