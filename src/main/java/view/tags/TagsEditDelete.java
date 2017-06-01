package view.tags;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import data.Tag;
import misc.MessageBox;
import services.TagService;

public class TagsEditDelete extends VerticalLayout{
	
	//Fields
	private TextField confirmText;
	private Button deleteBtt;
	private Label warningLabel;
	private GridLayout mainLayout;
	
	//Data model
	private Tag currentTag;
	
	//Services y otros
	private TagService tagService;
	private MessageBox messageBox;
	
	public TagsEditDelete() {
		
		tagService = TagService.getService();
		messageBox = MessageBox.getMessageBox();
		
		mainLayout = new GridLayout(3, 4);
		
		confirmText = new TextField();
		deleteBtt = new Button("Eliminar Tag");
		deleteBtt.setStyleName(ValoTheme.BUTTON_DANGER);
		
		warningLabel = new Label("<p>Esta acción no se puede deshacer.</p>"
								+ "<p>Para proseguir ingrese el nombre del tag y </p>"
								+ "<p>presione \"Eliminar Tag\"</p>", ContentMode.HTML);
		
		warningLabel.addStyleName("warning");
		warningLabel.setSizeFull();
		deleteBtt.addClickListener(e -> {
			if(currentTag != null && confirmText.getValue().equals(currentTag.getNombre())){
				tagService.deleteTag(currentTag);
				
				currentTag = null;
				confirmText.setValue("");
				
				messageBox.publish("ModifyInTags");
				messageBox.publish("TagDeleted");
			}
			});
		
		mainLayout.addComponent(warningLabel, 0, 0, 2, 1);
		mainLayout.addComponent(confirmText, 1, 2);
		mainLayout.addComponent(deleteBtt, 1, 3);
		
		mainLayout.setComponentAlignment(deleteBtt, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(confirmText, Alignment.MIDDLE_CENTER);
		
		mainLayout.setRowExpandRatio(0, 5);
		mainLayout.setRowExpandRatio(2, 1);
		mainLayout.setRowExpandRatio(3, 1);
		
		mainLayout.setSizeFull();
		
		addComponent(mainLayout);
		
		setSizeFull();
		
	}
	
	public void editTag(Tag t){
		
		currentTag = t;
		
	}

}
