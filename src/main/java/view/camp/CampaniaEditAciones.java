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
import com.vaadin.server.ClientConnector;
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
import misc.MessageBox;
import services.AccionPublicitariaService;
import services.CampaniaService;
import view.AccionField;
import view.ViewValidator;

public class CampaniaEditAciones extends VerticalLayout implements CampaniaEditor{
	
	public static final String NAME = "CampEditAcciones";
	
	private GridLayout mainLayout;
	private Grid accionesElegidas;
	private AccionField accionField;
	
	private List<AccionPublicitaria> accionesPublicitarias;
	
	private AccionPublicitariaService accionService = AccionPublicitariaService.getService();
	private MessageBox messageBox;
	
	public CampaniaEditAciones() {
		
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
		
		accionesElegidas.addRow(accionField.getCurrentAccion().getTipo(), 
								accionField.getCurrentAccion().getDestinatario(),
								accionField.getCurrentAccion().getPeriodicidad());
		
	}
	
	@Override
	public void editCampania(Campania c) {

		accionesPublicitarias.clear();
		accionesElegidas.getContainerDataSource().removeAllItems();
		
		accionField.setCampania(c);
		
	}

	@Override
	public void clear() {

		accionesPublicitarias.clear();
		accionesElegidas.getContainerDataSource().removeAllItems();
		
		
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
