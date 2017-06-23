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
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;

import data.Campania;
import data.MensajeCampania;
import data.Tag;
import net.coobird.thumbnailator.Thumbnails;
import services.CampaniaService;

public class CampaniaEditMensaje extends FormLayout implements CampaniaEditor{
	
	public static final String NAME = "EditMsgCamp";
	
	private TextArea mensajeTxt;
	private Upload upload;
	
	private Campania currentCampania;
	private MensajeCampania mensajeCampania;
	private final String UploadPath = "upload/";
	private String filePath = "";
	private String fileThumPath = "";
	
	private CampaniaService campaniaService;

	public CampaniaEditMensaje() {
		
		campaniaService = CampaniaService.getService();
		
		Embedded image = new Embedded("Imagen del mensaje: ");
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
		
		addComponent(mensajeTxt);
		addComponent(image);
		addComponent(upload);
		
		
	}

	@Override
	public void editCampania(Campania c) {

		currentCampania = c;
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
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
