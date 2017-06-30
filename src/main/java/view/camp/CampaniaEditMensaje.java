package view.camp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;

import data.Campania;
import data.MensajeCampania;
import data.Tag;
import net.coobird.thumbnailator.Thumbnails;
import services.CampaniaService;

public class CampaniaEditMensaje extends GridLayout implements CampaniaEditor{
	
	public static final String NAME = "EditMsgCamp";
	
	private TextArea mensajeTxt;
	private Upload upload;
	private Embedded image;
	private FormLayout mainLayout;
	
	private Campania currentCampania;
	private final String UploadPath = "upload/";
	private String filePath = "";
	private String fileThumPath = "";
	
	private CampaniaService campaniaService;

	public CampaniaEditMensaje() {
		
		super(3,3);
		setSizeFull();
		
		mainLayout = new FormLayout();
		campaniaService = CampaniaService.getService();
		
		image = new Embedded("Imagen del mensaje: ");
		mensajeTxt = new TextArea("Texto del mensaje:");
		
		upload = new Upload();
		upload.setCaption("Subir Imagen: ");
		upload.setButtonCaption("Subir");
		upload.setReceiver((name, type) -> {
			OutputStream toRet = null;
			
			new File(UploadPath).mkdirs();
			filePath = UploadPath + DateTime.now().getMillis() + "." + FilenameUtils.getExtension(name);
			File file = new File(filePath);
			
			try {
				file.createNewFile();
				toRet = new FileOutputStream(file);
			} 
			catch (IOException e) {
				Notification.show("No se pudo abrir el archivo", Notification.Type.ERROR_MESSAGE);
			}
			
			return toRet;
		});
		
		upload.addSucceededListener( e -> {
			File thumb = new File(FilenameUtils.getFullPath(filePath) + 
					"thumbnail." + FilenameUtils.getName(filePath));
			
			fileThumPath = thumb.getPath();
			
			try {Thumbnails.of(filePath).size(120, 120).toFile(thumb);}
			catch (IOException e1) {e1.printStackTrace();}
			
			image.setSource(new FileResource(thumb));
		});
		
		mainLayout.addComponent(mensajeTxt);
		mainLayout.addComponent(image);
		mainLayout.addComponent(upload);
		
		addComponent(mainLayout,1 ,1 );
		
	}

	@Override
	public void editCampania(Campania c) {

		currentCampania = c;
		
	}

	@Override
	public void clear() {
		image.setSource(null);
		mensajeTxt.setValue("");
		
		if(filePath != "") new File(filePath).delete();
		if(fileThumPath != "") new File(fileThumPath).delete();
		
	}

	@Override
	public void commit() {
		
		campaniaService.setMensaje(currentCampania, mensajeTxt.getValue(), filePath, fileThumPath);

	}

	@Override
	public boolean isValid() {
		return true;
	}

}
