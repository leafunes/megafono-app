package view.tags;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Tag;
import misc.MessageBox;
import services.TagService;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class TagsEdit extends Panel{
	
	BeanFieldGroup<Tag> binder;
	
	private TabSheet tabs;
	private TagsEditDescripcion editDescripcion;
	private TagsEditPricing editPricing;
	private TagsEditDelete editDelete;
	private GridLayout mainLayout;
	
	private MessageBox messageBox;
	
	public TagsEdit(){
		
		messageBox = MessageBox.getMessageBox();
		
		editDescripcion = new TagsEditDescripcion();
		editPricing = new TagsEditPricing();
		editDelete = new TagsEditDelete();
		mainLayout = new GridLayout(6,2);
		
		Button okBtt = new Button("OK");
		okBtt.addClickListener(event -> {
			editDescripcion.commit();
			editPricing.commit();
			
		});
		
		okBtt.setWidth(100, Unit.PERCENTAGE);
		okBtt.setStyleName(ValoTheme.BUTTON_PRIMARY);
		okBtt.setClickShortcut(KeyCode.ENTER);
		
		Button cancelBtt = new Button("Cancelar");

		messageBox.suscribirse("TagDeleted", () -> cancelBtt.click());
		
		cancelBtt.addClickListener(event -> {
			editDescripcion.clear();
			editPricing.clear();
			editDelete.clear();
			messageBox.publish("CancelButtonTag");
			
		});
		
		cancelBtt.setWidth(100, Unit.PERCENTAGE);
		
		tabs = new TabSheet();

		tabs.addTab(editDescripcion, "Descripcion");
		tabs.addTab(new Label("TODO"), "Acciones");
		tabs.addTab(editPricing, "Pricing");
		tabs.addTab(editDelete, "Borrar Tag");
		tabs.setSizeFull();
		

		mainLayout.addComponent(tabs, 0, 0 , 5, 0);
		mainLayout.addComponent(okBtt, 1, 1);
		mainLayout.addComponent(cancelBtt, 4, 1);
		mainLayout.setComponentAlignment(okBtt, Alignment.BOTTOM_CENTER);
		mainLayout.setComponentAlignment(cancelBtt, Alignment.BOTTOM_CENTER);
		
		mainLayout.setRowExpandRatio(0, 9);
		mainLayout.setRowExpandRatio(1, 1);
		mainLayout.setSpacing(true);
		
		mainLayout.setSizeFull();
		
		setContent(mainLayout);
		
		setSizeFull();
		
	}
	
	public void editTag(Tag tag){
		editDescripcion.editTag(tag);
		editPricing.editTag(tag);
		editDelete.editTag(tag);
		
	}


}
