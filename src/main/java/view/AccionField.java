package view;

import java.util.Arrays;
import java.util.List;

import org.joda.time.Period;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.AccionPublicitaria;
import data.Campania;
import data.Tag;
import data.TipoAccionPublicitaria;
import data.UnidadTiempo;
import misc.MessageBox;

public class AccionField extends VerticalLayout{

	private static final long serialVersionUID = 1L;

	private ViewValidator validator;
	private HorizontalLayout upperLayout;
	private HorizontalLayout lowerLayout;
	
	private TextField destinatario;
	private ComboBox tipoAccion;
	private Button addBtt;
	private TextField cantDe;
	private ComboBox unidadTiempo;
	
	private List<AccionPublicitaria> acciones;
	private AccionPublicitaria currentAccion;
	private Campania currentCampania;
	private Tag currentTag;
	
	private MessageBox messageBox;
	
	public AccionField(List<AccionPublicitaria> acciones) {
		
		messageBox = MessageBox.getMessageBox();
		validator = new ViewValidator();
		
		upperLayout = new HorizontalLayout();
		lowerLayout = new HorizontalLayout();
		
		this.acciones = acciones;
		
		tipoAccion = new ComboBox();
		Arrays.asList(TipoAccionPublicitaria.values()).forEach(t -> tipoAccion.addItem(t));
		tipoAccion.setInvalidAllowed(false);
		tipoAccion.setNullSelectionAllowed(false);
		tipoAccion.select(TipoAccionPublicitaria.MAIL);
		
		addBtt = new Button("+");
		addBtt.setSizeFull();
		cantDe = new TextField();
		destinatario = new TextField();
		
		addBtt.addClickListener(e -> addAccion());
		
		unidadTiempo = new ComboBox();
		Arrays.asList(UnidadTiempo.values()).forEach(t -> unidadTiempo.addItem(t));
		unidadTiempo.setInvalidAllowed(false);
		unidadTiempo.setNullSelectionAllowed(false);
		unidadTiempo.select(UnidadTiempo.MINUTOS);
		
		upperLayout.addComponent(new Label("Tipo: "));
		upperLayout.addComponent(tipoAccion);
		upperLayout.addComponent(new Label("Destintatario: "));
		upperLayout.addComponent(destinatario);
		upperLayout.setComponentAlignment(tipoAccion, Alignment.MIDDLE_LEFT);
		upperLayout.setComponentAlignment(destinatario, Alignment.MIDDLE_LEFT);
		
		lowerLayout.addComponent(new Label("Cada: "));
		lowerLayout.addComponent(cantDe);
		lowerLayout.addComponent(unidadTiempo);
		lowerLayout.addComponent(addBtt);
		lowerLayout.setComponentAlignment(cantDe, Alignment.MIDDLE_LEFT);
		lowerLayout.setComponentAlignment(unidadTiempo, Alignment.MIDDLE_LEFT);
		
		addComponent(upperLayout);
		addComponent(lowerLayout);
		
		lowerLayout.setSpacing(true);
		upperLayout.setSpacing(true);
		lowerLayout.setSizeFull();
		upperLayout.setSizeFull();
		setSpacing(true);
		
		validator.isNotEmpty(destinatario::getValue, "Debe ingresar un destinatario");
		validator.isNumber(cantDe::getValue, "Debe ingresar un numero");
		validator.isNotEmpty(cantDe::getValue, "Debe ingresar una cantidad de tiempo");
		
		
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
			currentAccion.setTag(currentTag);
			acciones.add(currentAccion);
			
			messageBox.publish("NewAccion");
		}
	}
	
	public void setCampania(Campania c){
		this.currentCampania = c;
	}
	
	public void setTag(Tag t){
		this.currentTag = t;
	}
	
	public boolean isValid(){
		return validator.isValid();
	}

	public AccionPublicitaria getCurrentAccion() {
		return currentAccion;
	}
	

}
