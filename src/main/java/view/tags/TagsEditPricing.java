package view.tags;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Precio;
import data.Tag;
import dtos.PrecioDTO;
import misc.MessageBox;
import services.PrecioService;

@SuppressWarnings("serial")
public class TagsEditPricing extends VerticalLayout implements TagEditor{
	
	private TextField montoField;
	
	private Label currentPrice;
	
	private MessageBox messageBox;
	private BeanFieldGroup<Precio> binder;
	private Tag currentTag;
	private Precio nuevoPrecio;
	
	private List<PrecioDTO> precios;
	private BeanItemContainer<PrecioDTO> container;
	
	private PrecioService precioService;
	
	public TagsEditPricing() {
		
		precioService = PrecioService.getService();
		messageBox = MessageBox.getMessageBox();
		
		montoField = new TextField();
		
		currentPrice = new Label();
		
		precios = new ArrayList<>();
		container = new BeanItemContainer<>(PrecioDTO.class, precios);
		
		Grid grid = new Grid(container);
		grid.setColumns("monto", "fechaCreacion", "fechaFin");
		grid.getColumn("monto").setHeaderCaption("Monto");
		grid.getColumn("fechaCreacion").setHeaderCaption("Fecha de Creacion");
		grid.getColumn("fechaFin").setHeaderCaption("Fecha de Fin");

		addComponent(grid);
		
		addComponent(montoField);
		addComponent(currentPrice);
		
		

		
		
	}
	
	@Override
	public void editTag(Tag t){

		currentTag = t;
		nuevoPrecio = new Precio();
		Precio anteriorPrecio = precioService.getCurrentPriceOf(t);
		
		currentPrice.setValue(anteriorPrecio.getMonto().toPlainString());
		
		nuevoPrecio.setMonto(anteriorPrecio.getMonto().toPlainString());
		nuevoPrecio.setObjetoValuable(t);
		
		binder = BeanFieldGroup.bindFieldsBuffered(nuevoPrecio, this);
		binder.bind(montoField, "monto");
		
		precios = precioService.getHistoricalPricesOf(t);
		container.removeAllItems();
		container.addAll(precios);
		
	}
	
	@Override
	public void commit(){
		try {
			binder.commit();
			precioService.actualizePrices(currentTag, nuevoPrecio);
			
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void clear() {
		
		binder.clear();
		binder.unbind(montoField);
		montoField.setValue("0.0");
		currentPrice.setValue("0.0");
		
	}

}
