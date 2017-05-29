package view.tags;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.Tag;
import misc.MessageBox;
import services.TagService;

public class TagsEditDescripcion extends VerticalLayout{
	
	private Tag currentTag;
	private TagService tagService;
	private MessageBox messageBox;
	private BeanFieldGroup<Tag> binder;
	private TextField nombreTag;
	private TextArea descripcionTag;
	private CheckBox habilitadoTag;
	
	public TagsEditDescripcion( ) {
		tagService = TagService.getService();
		messageBox = MessageBox.getMessageBox();
		
		HorizontalLayout horLayout = new HorizontalLayout();
		FormLayout form = new FormLayout();
		
		nombreTag = new TextField("Nombre: ");
		descripcionTag = new TextArea("Descripcion: ");
		habilitadoTag = new CheckBox(" Habilitado");
		
		form.addComponent(nombreTag);
		form.addComponent(descripcionTag);
		form.addComponent(habilitadoTag);
		
		horLayout.addComponent(form);
		horLayout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		
		addComponent(horLayout);
		setComponentAlignment(horLayout, Alignment.MIDDLE_CENTER);
		
		setSizeFull();
		
		
	}
	
	public void editTag (Tag t){
		
		currentTag = t;

		binder = BeanFieldGroup.bindFieldsBuffered(currentTag, this);
		binder.bind(nombreTag, "nombre");
		binder.bind(descripcionTag, "descripcion");
		binder.bind(habilitadoTag, "habilitado");
	}
	
	public void commit(){
		
		try {
			if(binder != null){
				
				boolean oldHabilitado = currentTag.isHabilitado();
				
				binder.commit();
				tagService.addTag(currentTag);
				if(oldHabilitado != currentTag.isHabilitado())
					tagService.actulizeHabilitations(currentTag);
				
				messageBox.publish("ModifyInTags");
				
			}
		} catch (CommitException e) {
			e.printStackTrace();
		}
	}
	

	

}
