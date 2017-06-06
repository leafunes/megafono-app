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

@SuppressWarnings("serial")
public class TagsEditPricing extends VerticalLayout implements TagEditor{
	
	private TextField montoField;
	
	private Label currentPrice;
	
	private MessageBox messageBox;
	private BeanFieldGroup<Precio> binder;
	private Tag currentTag;
	private Precio nuevoPrecio;
	
	private PrecioService precioService;
	
	public TagsEditPricing() {
		
		precioService = PrecioService.getService();
		messageBox = MessageBox.getMessageBox();
		
		montoField = new TextField();
		
		currentPrice = new Label();
		
		addComponent(montoField);
		addComponent(currentPrice);
		
	}
	
	@Override
	public void editTag(Tag t){
		
		currentPrice.setValue(precioService.getCurrentPriceOf(t).getMonto().toPlainString());

		currentTag = t;
		nuevoPrecio = new Precio();
		nuevoPrecio.setObjetoValuable(t);
		
		if(binder == null) binder = BeanFieldGroup.bindFieldsBuffered(nuevoPrecio, this);
		binder.bind(montoField, "monto");
		
	}
	
	@Override
	public void commit(){
		try {
				
			binder.commit();
			
			precioService.actualizePrices(currentTag);
			
			precioService.addPrecio(nuevoPrecio);
			
			currentPrice.setValue(nuevoPrecio.getMonto().toPlainString());
			
			messageBox.publish("ModifyInTags");
			
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
