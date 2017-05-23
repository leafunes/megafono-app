package view;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class MainView extends HorizontalLayout{
	
	private VerticalLayout verLayout;
	
	public MainView() {
		
		init();
		
	}
	
	private void init(){
		// Find the application directory
		String basepath = VaadinService.getCurrent()
		.getBaseDirectory().getAbsolutePath();
		
		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		"/WEB-INF/images/logo.png"));
		// Show the image in the application
		Image image = new Image(null, resource);
		
		this.addComponent(image);
		
	}

}
