package view.tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.Period;

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
	
	private GridLayout mainLayout;
	private Grid accionesElegidas;
	private AccionField accionField;
	
	private List<AccionPublicitaria> accionesPublicitarias;
	
	private AccionPublicitariaService accionService = AccionPublicitariaService.getService();
	private MessageBox messageBox;
	
	public TagsEditAcciones() {
		
		messageBox = MessageBox.getMessageBox();
		
		accionesPublicitarias = new ArrayList<>();
		accionField = new AccionField(accionesPublicitarias);
		mainLayout = new GridLayout(7, 2);
		accionesElegidas = new Grid();
		
		accionesElegidas.addColumn("Tipo", TipoAccionPublicitaria.class);
		accionesElegidas.addColumn("Destinatario", String.class);
		accionesElegidas.addColumn("Periodicidad", Period.class);
		accionesElegidas.setSizeFull();
		
		mainLayout.addComponent(accionesElegidas, 1, 0, 5, 0);
		mainLayout.addComponent(accionField, 1, 1, 5, 1);
		mainLayout.setColumnExpandRatio(6, 0.1F);
		
		mainLayout.setSpacing(true);
		
		messageBox.suscribirse("NewAccion", () -> addCurrentRow());
		
		addComponent(mainLayout);
		
	}
	
	private void addCurrentRow(){
		
		addAccion(accionField.getCurrentAccion());
		
	}
	
	private void addAccion(AccionPublicitaria a){
		accionesElegidas.addRow(a.getTipo(), 
								a.getDestinatario(),
								a.getPeriodicidad());
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
