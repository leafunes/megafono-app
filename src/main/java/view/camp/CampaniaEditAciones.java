package view.camp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Period;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.AccionPublicitaria;
import data.Campania;
import data.Tag;
import data.TipoAccionPublicitaria;
import data.UnidadTiempo;
import services.AccionPublicitariaService;
import services.CampaniaService;
import view.ViewValidator;

public class CampaniaEditAciones extends VerticalLayout implements CampaniaEditor{
	
	public static final String NAME = "CampEditAcciones";
	
	ViewValidator validator;
	
	private GridLayout mainLayout;
	private Grid accionesElegidas;
	private TextField destinatario;
	private ComboBox tipoAccion;
	private Button addBtt;
	private TextField cantDe;
	private ComboBox unidadTiempo;
	
	private List<AccionPublicitaria> accionesPublicitarias;
	private AccionPublicitaria currentAccion;
	private Campania currentCampania;
	
	private AccionPublicitariaService accionService = AccionPublicitariaService.getService();
	
	public CampaniaEditAciones() {
		
		accionesPublicitarias = new ArrayList<>();
		mainLayout = new GridLayout(7, 2);
		accionesElegidas = new Grid();
		validator = new ViewValidator();
		
		tipoAccion = new ComboBox("Tipo: ");
		Arrays.asList(TipoAccionPublicitaria.values()).forEach(t -> tipoAccion.addItem(t));
		tipoAccion.setInvalidAllowed(false);
		tipoAccion.setNullSelectionAllowed(false);
		tipoAccion.select(TipoAccionPublicitaria.MAIL);
		
		addBtt = new Button("+");
		cantDe = new TextField("Cada: ");
		destinatario = new TextField();
		
		addBtt.addClickListener(e -> addAccion());
		
		unidadTiempo = new ComboBox();
		Arrays.asList(UnidadTiempo.values()).forEach(t -> unidadTiempo.addItem(t));
		unidadTiempo.setInvalidAllowed(false);
		unidadTiempo.setNullSelectionAllowed(false);
		unidadTiempo.select(UnidadTiempo.MINUTOS);
		
		accionesElegidas.addColumn("Tipo", TipoAccionPublicitaria.class);
		accionesElegidas.addColumn("Destinatario", String.class);
		accionesElegidas.addColumn("Periodicidad", Period.class);
		accionesElegidas.setSizeFull();
		
		mainLayout.addComponent(accionesElegidas, 1, 0, 6, 0);
		mainLayout.addComponent(tipoAccion, 2, 1);
		mainLayout.addComponent(destinatario, 3, 1);
		mainLayout.addComponent(cantDe, 4, 1);
		mainLayout.addComponent(unidadTiempo, 5, 1);
		mainLayout.addComponent(addBtt, 6, 1);
		
		validator.isNotEmpty(destinatario::getValue, "Debe ingresar un destinatario");
		validator.isNotEmpty(cantDe::getValue, "Debe ingresar una cantidad de tiempo");
		
		addComponent(mainLayout);
		
	}
	
	private void addAccion(){
		
		if(validator.testAll()){
			Period p = null;
			Integer cantidad = Integer.valueOf(cantDe.getValue().toString());
			switch (UnidadTiempo.valueOf(unidadTiempo.getValue().toString())) {
				case MINUTOS:
					p = Period.minutes(cantidad);
					break;
					
				case HORAS:
					p = Period.hours(cantidad);
					break;
					
				case SEMANAS:
					p = Period.weeks(cantidad);
					break;
					
				case DIAS:
					p = Period.days(cantidad);
					break;
	
				default:
					break;
			}
			String dest = destinatario.getValue().toString();
			TipoAccionPublicitaria tipo = TipoAccionPublicitaria.valueOf(tipoAccion.getValue().toString());
			
			currentAccion = new AccionPublicitaria(tipo ,dest, "", "");
			currentAccion.setPeriodicidad(p);
			currentAccion.setCamp(currentCampania);
			accionesPublicitarias.add(currentAccion);
			
			addCurrentRow();
		}
	}
	
	private void addCurrentRow(){
		
		accionesElegidas.addRow(currentAccion.getTipo(), 
								currentAccion.getDestinatario(),
								currentAccion.getPeriodicidad());
		
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
		
		accionesPublicitarias.forEach(a -> accionService.saveAccion(a));
		
	}

	@Override
	public boolean isValid() {
		return true;
	}
	
	

}
