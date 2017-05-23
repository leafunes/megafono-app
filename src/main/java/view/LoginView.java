package view;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import daos.iface.DAO;
import daos.impl.DAONeodatis;
import data.Usuario;
import services.UsuarioService;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LoginView extends VerticalLayout{
	
	private TextField username;
	private PasswordField password;
	private Button sendFields;
	
	private HorizontalLayout horizontalLayout;
	private FormLayout loginForm;
	
	private UsuarioService usuarioService;
	
	public LoginView() {
		
		usuarioService = UsuarioService.getService();
		
		init();
		this.setSizeFull();
		
		
	}
	
	private void init(){
		
		username = new TextField("Nombre de Usuario");
		password = new PasswordField("Contraseña");
		sendFields = new Button("Login");
		
		sendFields.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(!usuarioService.altaUsuario(username.getValue(), password.getValue()))
					Notification.show("Usuario ya existente", Notification.Type.ERROR_MESSAGE);
				
			}
		});
		
		loginForm = new FormLayout();
		horizontalLayout = new HorizontalLayout();
		
		loginForm.addComponent(username);
		loginForm.addComponent(password);
		loginForm.addComponent(sendFields);
		
		
		horizontalLayout.addComponent(loginForm);
		horizontalLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
		
	}
	
}
