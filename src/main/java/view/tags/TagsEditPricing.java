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
	
	private TextField leftComma;
	private TextField rigthComma;
	
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
		newPrecio.setLeftComma(0);
		newPrecio.setRigthComma(0);
		
		leftComma = new TextField();
		rigthComma = new TextField();
		
		currentPrice = new Label();
		
		addComponent(leftComma);
		addComponent(rigthComma);
		addComponent(currentPrice);
		
	}
	
	@Override
	public void editTag(Tag t){
		
		Precio precio = precioService.getCurrentPriceOf(t);
		if(precio != null)currentPrice.setValue(precio.getLeftComma() + "," + precio.getRigthComma());
		
		currentTag = t;
		newPrecio.setObjetoValuable(t);
		
		binder = BeanFieldGroup.bindFieldsBuffered(newPrecio, this);
		binder.bind(leftComma, "leftComma");
		binder.bind(rigthComma, "rigthComma");
	}
	
	@Override
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

	@Override
	public void clear() {
		
	}

}
