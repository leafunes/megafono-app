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
		okBtt.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
					try {
						if(binder != null){
							Tag oldTag = tagService.cloneTag(currentTag);
							
							binder.commit();
							tagService.addTag(currentTag);//actualizeTag(oldTag, currentTag);
							messageBox.publish("NewTag");
							
						}
					} catch (CommitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		mainLayout.addComponent(nombreTag);
		mainLayout.addComponent(okBtt);
		mainLayout.setSizeFull();
		
		setContent(mainLayout);
		
	}
	
	public void editTag(Tag tag){
		nombreTag.setValue(tag.getNombre());
		
		currentTag = tag;
		
		System.out.println(currentTag);
		System.out.println(tag);

		binder = BeanFieldGroup.bindFieldsBuffered(currentTag, this);
		binder.bind(nombreTag, "nombre");
		
	}


}
