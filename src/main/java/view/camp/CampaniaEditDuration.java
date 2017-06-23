package view.camp;

import java.util.Arrays;
import java.util.Date;

import org.joda.time.DateTime;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import data.Campania;
import data.TipoDuracion;

public class CampaniaEditDuration extends FormLayout implements CampaniaEditor{
	
	public static final String NAME = "CampEdirDuracion";
	
	private InlineDateField fechaInicio;
	private ComboBox duracion;
	
	private Campania currentCampania;
	
	public CampaniaEditDuration() {
		
		fechaInicio = new InlineDateField("Fecha de inicio: ", new Date());
		fechaInicio.setResolution(Resolution.MINUTE);
		
		duracion = new ComboBox("Duracion: ");
		Arrays.asList(TipoDuracion.values()).forEach(t -> duracion.addItem(t));
		
		addComponent(fechaInicio);
		addComponent(duracion);
		
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
		System.out.println(currentCampania);
		currentCampania.setDuracion(TipoDuracion.valueOf(duracion.getValue().toString()));
		currentCampania.setInicio(new DateTime(fechaInicio.getValue()));
		
	}

	@Override
	public boolean isValid() {
		
		DateTime fecha = new DateTime(fechaInicio.getValue());
		
		if(fecha.isBeforeNow()){
			Notification.show("La fecha debe ser posterior a hoy", Notification.Type.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
