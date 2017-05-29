package view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

import data.Tag;
import misc.MessageBox;
import services.TagService;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class TagsEdit extends Panel{
	
	private FormLayout mainLayout;
	private TextField nombreTag;
	private Tag currentTag;
	BeanFieldGroup<Tag> binder;
	
	private TagService tagService;
	
	private MessageBox messageBox = MessageBox.getMessageBox();
	
	public TagsEdit(){
		
		setSizeFull();
		
		tagService = TagService.getService();

		mainLayout = new FormLayout();
		
		nombreTag = new TextField();
		
		Button okBtt = new Button("OK");
		okBtt.addClickListener(event -> {
			
				
					try {
						if(binder != null){
							
							binder.commit();
							tagService.addTag(currentTag);
							messageBox.publish("ModifyInTags");
							
						}
					} catch (CommitException e) {
						e.printStackTrace();
					}
		});
		
		Button deleteBtt = new Button("Borrar");
		
		deleteBtt.addClickListener(event -> {
			tagService.deleteTag(currentTag);
			currentTag = null;
			messageBox.publish("ModifyInTags");
		});
		
		mainLayout.addComponent(nombreTag);
		mainLayout.addComponent(okBtt);
		mainLayout.addComponent(deleteBtt);
		mainLayout.setSizeFull();
		
		setContent(mainLayout);
		
	}
	
	public void editTag(Tag tag){
		nombreTag.setValue(tag.getNombre());
		
		currentTag = tag;

		binder = BeanFieldGroup.bindFieldsBuffered(currentTag, this);
		binder.bind(nombreTag, "nombre");
		
	}


}
