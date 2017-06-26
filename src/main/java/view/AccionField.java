package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Period;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import data.AccionPublicitaria;
import data.Campania;
import data.Tag;
import data.TipoAccionPublicitaria;
import data.UnidadTiempo;
import misc.MessageBox;

public class AccionField extends HorizontalLayout{
	
	private ViewValidator validator;
	
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
		
		this.acciones = acciones;
		
		tipoAccion = new ComboBox();
		Arrays.asList(TipoAccionPublicitaria.values()).forEach(t -> tipoAccion.addItem(t));
		tipoAccion.setInvalidAllowed(false);
		tipoAccion.setNullSelectionAllowed(false);
		tipoAccion.select(TipoAccionPublicitaria.MAIL);
		
		addBtt = new Button("+");
		cantDe = new TextField();
		destinatario = new TextField();
		
		addBtt.addClickListener(e -> addAccion());
		
		unidadTiempo = new ComboBox();
		Arrays.asList(UnidadTiempo.values()).forEach(t -> unidadTiempo.addItem(t));
		unidadTiempo.setInvalidAllowed(false);
		unidadTiempo.setNullSelectionAllowed(false);
		unidadTiempo.select(UnidadTiempo.MINUTOS);
		
		addComponent(new Label("Tipo: "));
		addComponent(tipoAccion);
		addComponent(new Label("Destintatario: "));
		addComponent(destinatario);
		addComponent(new Label("Cada: "));
		addComponent(cantDe);
		addComponent(unidadTiempo);
		addComponent(addBtt);
		
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
