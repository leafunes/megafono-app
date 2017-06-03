package view.tags;

import org.joda.time.DateTime;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Precio;
import data.Tag;
import misc.MessageBox;
import services.PrecioService;

public class TagsEditPricing extends VerticalLayout{
	
	private TextField montoField;
	
	private Label currentPrice;
	

	private MessageBox messageBox;
	private BeanFieldGroup<Precio> binder;
	private Tag currentTag;
	private Precio newPrecio;
	
	private PrecioService precioService;
	
	public TagsEditPricing() {
		
		precioService = PrecioService.getService();
		messageBox = MessageBox.getMessageBox();
		
		newPrecio = new Precio();
		newPrecio.setMonto("0.0");
		
		montoField = new TextField();
		
		currentPrice = new Label();
		
		addComponent(montoField);
		addComponent(currentPrice);
		
	}
	
	public void editTag(Tag t){
		
		if(t != null){
			
			Precio precio = precioService.getCurrentPriceOf(t);
			if(precio != null)currentPrice.setValue(precio.getMonto().toPlainString());
			else currentPrice.setValue("0.0");
			
			currentTag = t;
			newPrecio.setObjetoValuable(t);
			
			binder = BeanFieldGroup.bindFieldsBuffered(newPrecio, this);
			binder.bind(montoField, "monto");
		}
		else{
			
			montoField.setValue("0.0");
			currentPrice.setValue("0.0");
			
		}
	}
	
	public void commit(){
		try {
			if(binder != null){
				
				binder.commit();
				
				newPrecio.setCurrent(true);
				newPrecio.setFechaCreacion(DateTime.now());
				
				precioService.actualizePrices(currentTag);
				
				precioService.addPrecio(newPrecio);
				
				messageBox.publish("ModifyInTags");
				
			}
		} catch (CommitException e) {
			e.printStackTrace();
		}
		
	}

}
