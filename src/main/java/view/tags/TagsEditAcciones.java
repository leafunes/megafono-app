package view.tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormat;

import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import data.AccionPublicitaria;
import data.Campania;
import data.Tag;
import data.TipoAccionPublicitaria;
import misc.MessageBox;
import services.AccionPublicitariaService;
import view.AccionField;

public class TagsEditAcciones extends VerticalLayout implements TagEditor{
	
	private Grid accionesElegidas;
	private AccionField accionField;
	
	private List<AccionPublicitaria> accionesPublicitarias;
	
	private AccionPublicitariaService accionService = AccionPublicitariaService.getService();
	private MessageBox messageBox;
	
	public TagsEditAcciones() {
		
		messageBox = MessageBox.getMessageBox();
		setSizeFull();
		
		accionesPublicitarias = new ArrayList<>();
		accionField = new AccionField(accionesPublicitarias);
		accionesElegidas = new Grid();
		
		addComponent(accionesElegidas);
		setExpandRatio(accionesElegidas, 70);
		addComponent(accionField);
		setSpacing(true);
		
		accionesElegidas.addColumn("Tipo", TipoAccionPublicitaria.class);
		accionesElegidas.addColumn("Destinatario", String.class);
		accionesElegidas.addColumn("Periodicidad", String.class);
		accionesElegidas.setSizeFull();

		messageBox.suscribirse("NewAccion", () -> addCurrentRow());
		
		
	}
	
	private void addCurrentRow(){
		
		addAccion(accionField.getCurrentAccion());
		
	}
	
	private void addAccion(AccionPublicitaria a){
		accionesElegidas.addRow(a.getTipo(), 
								a.getDestinatario(),
								a.getPeriodicidad()
								.toString(PeriodFormat.wordBased(Locale.getDefault())));
	}
	
	@Override
	public void clear() {
		
		accionField.setTag(null);
		accionesPublicitarias.clear();
		
	}

	@Override
	public void commit() {
		
		accionesPublicitarias.forEach(a -> accionService.saveAccion(a));
		
	}

	@Override
	public void editTag(Tag t) {
		accionesPublicitarias.clear();
		accionesElegidas.getContainerDataSource().removeAllItems();
		accionesElegidas.refreshAllRows();
		
		accionesPublicitarias.addAll(accionService.getAllActionsOf(t));
		accionesPublicitarias.forEach(a -> addAccion(a));
		
		accionField.setTag(t);
		
	}
}
