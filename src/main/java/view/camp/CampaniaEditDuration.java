package view.camp;

import java.util.Arrays;
import java.util.Date;

import org.joda.time.DateTime;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import data.TipoDuracion;
import view.ViewValidator;

public class CampaniaEditDuration extends GridLayout implements CampaniaEditor{
	
	public static final String NAME = "CampEdirDuracion";
	
	private ViewValidator validator;
	
	private InlineDateField fechaInicio;
	private ComboBox duracion;
	private FormLayout mainLayout;
	
	private Campania currentCampania;
	
	public CampaniaEditDuration() {
		
		super(3,3);
		setSizeFull();
		
		validator = new ViewValidator();
		mainLayout = new FormLayout();
		
		fechaInicio = new InlineDateField("Fecha de inicio: ", new Date());
		fechaInicio.setResolution(Resolution.MINUTE);
		
		duracion = new ComboBox("Duracion: ");
		Arrays.asList(TipoDuracion.values()).forEach(t -> duracion.addItem(t));
		duracion.setNullSelectionAllowed(false);
		duracion.select(TipoDuracion.SEMANAL);
		
		validator.isFalse(() -> isBeforeNow(), "La fecha debe ser posterior a hoy");
		
		mainLayout.addComponent(fechaInicio);
		mainLayout.addComponent(duracion);
		
		addComponent(mainLayout, 1, 1);
		
	}
	
	private boolean isBeforeNow(){
		
		return new DateTime(fechaInicio.getValue()).isBeforeNow();
		
	}

	@Override
	public void editCampania(Campania c) {
		currentCampania = c;
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() {
		currentCampania.setDuracion(TipoDuracion.valueOf(duracion.getValue().toString()));
		currentCampania.setInicio(new DateTime(fechaInicio.getValue()));
		
	}

	@Override
	public boolean isValid() {
		
		return validator.testAll();
	}

}
