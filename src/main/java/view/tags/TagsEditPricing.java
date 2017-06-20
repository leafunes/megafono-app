package view.tags;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Tag;
import data.precios.Precio;
import data.precios.PrecioTag;
import dtos.PrecioDTO;
import misc.MessageBox;
import services.PrecioService;

@SuppressWarnings("serial")
public class TagsEditPricing extends VerticalLayout implements TagEditor{
	
	private TextField nuevoPrecioMonto;
	private GridLayout mainLayout;
	private Grid grid;
	
	private MessageBox messageBox;
	private BeanFieldGroup<Precio> binder;
	private Tag currentTag;
	private PrecioTag nuevoPrecio;
	
	private BeanItemContainer<PrecioDTO> container;
	
	private PrecioService precioService;
	
	public TagsEditPricing() {
		
		precioService = PrecioService.getService();
		messageBox = MessageBox.getMessageBox();
		
		mainLayout = new GridLayout(4,3);
		nuevoPrecioMonto = new TextField();
		//validator para numeros con coma
		//acepta cosas como 1,2; 23,45; 23,4
		//no acepta cosas como 1, ; ; 1,a
		nuevoPrecioMonto.addValidator(v -> {if(!((BigDecimal)v).toPlainString().matches("\\d{1,}.?(\\d){0,2}"))	
											throw new InvalidValueException("El precio debe ser un numero");});
		nuevoPrecioMonto.setEnabled(false);
		
		container = new BeanItemContainer<>(PrecioDTO.class, new ArrayList<>());
		
		grid = new Grid(container);
		grid.setColumns("monto", "fechaCreacion", "fechaFin");
		grid.getColumn("monto").setHeaderCaption("Monto");
		grid.getColumn("fechaCreacion").setHeaderCaption("Fecha de Creacion");
		grid.getColumn("fechaFin").setHeaderCaption("Fecha de Fin");
		
		mainLayout.addComponent(grid, 0, 0, 3, 1);
		grid.setSizeFull();
		
		Label precioLbl = new Label("Nuevo Precio: ");
		mainLayout.addComponent(precioLbl ,1 ,2);
		mainLayout.addComponent(nuevoPrecioMonto, 2, 2);
		mainLayout.setComponentAlignment(precioLbl, Alignment.MIDDLE_RIGHT);
		mainLayout.setComponentAlignment(nuevoPrecioMonto, Alignment.MIDDLE_CENTER);
		
		mainLayout.setRowExpandRatio(0, 9);
		mainLayout.setRowExpandRatio(2, 1);
		
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		setSizeFull();
		addComponent(mainLayout);
	}
	
	private void actualizeContainer(){
		container.removeAllItems();
		container.addAll(precioService.getHistoricalPricesOf(currentTag));
		grid.scrollToEnd();
	}
	
	private void createNewPrice(){
		nuevoPrecio = new PrecioTag();
		Precio anteriorPrecio = precioService.getCurrentPriceOf(currentTag);
		
		nuevoPrecio.setMonto(anteriorPrecio.getMonto().toPlainString());
		nuevoPrecio.setObjetoValuable(currentTag);
	}
	
	private void bind(){
		binder = BeanFieldGroup.bindFieldsBuffered(nuevoPrecio, this);
		binder.bind(nuevoPrecioMonto, "monto");
	}
	
	@Override
	public void editTag(Tag t){
	
		nuevoPrecioMonto.setEnabled(true);

		currentTag = t;
		
		createNewPrice();
		
		nuevoPrecioMonto.setValue(nuevoPrecio.getMonto().toPlainString());
		
		bind();
		actualizeContainer();
	}
	
	@Override
	public void commit(){
		try {
			if(nuevoPrecioMonto.isValid() && nuevoPrecioMonto.getValue() != null){
				binder.commit();
				precioService.actualizePrices(currentTag, nuevoPrecio);
				actualizeContainer();
				createNewPrice();
				bind();
			}
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void clear() {
		
		binder.clear();
		binder.unbind(nuevoPrecioMonto);
		nuevoPrecioMonto.setValue("0.0");
		nuevoPrecioMonto.setEnabled(false);
		
		container.removeAllItems();
		
	}

}
