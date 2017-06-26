package view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import daos.iface.DAO;
import daos.impl.DAONeodatis;
import data.Usuario;
import services.UsuarioService;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LoginView extends VerticalLayout{
	
	private TextField username;
	private PasswordField password;
	private Button loginButton;
	private Button regisButton;
	
	private HorizontalLayout horizontalLayout;
	private FormLayout loginForm;
	
	private UsuarioService usuarioService;
	
	public LoginView() {
		
		usuarioService = UsuarioService.getService();
		
		init();
		this.setSizeFull();
		
		
	}
	
	private void init(){
		
		username = new TextField("Mail");
		password = new PasswordField("Contraseña");
		loginButton = new Button("Login");
		regisButton = new Button("Registrarse");
		
		loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		

		username.setBuffered(true);
		username.setNullSettingAllowed(true);
		username.setNullRepresentation("");
		username.addValidator(new NullValidator("Debe ingresar un email", false));
		username.addValidator(new EmailValidator("No es un email valido"));
		
		//password.setBuffered(true);
		password.setNullSettingAllowed(true);
		password.setNullRepresentation("");
		password.addValidator(new NullValidator("Debe ingresar una contraseña", false));
		
		loginButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(username.isValid() && !username.getValue().isEmpty() &&
						password.isValid() && !password.getValue().isEmpty()){
					if(usuarioService.loginUsuario(username.getValue(), password.getValue())){
						MainView view = getMainView();
						getUI().setContent(view);
						view.initNavigator();
					}
					
					else
						Notification.show("Datos invalidos", Notification.Type.ERROR_MESSAGE);
					}				
			}
		});
		
		regisButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(username.isValid() && !username.getValue().isEmpty() &&
						password.isValid() && !password.getValue().isEmpty()){
					if(!usuarioService.isUsernameInUse(username.getValue())){
						usuarioService.altaCliente(username.getValue(), password.getValue());
						
						Notification.show("Registrado", Notification.Type.ASSISTIVE_NOTIFICATION);
					}
					
					else
						Notification.show("Email ya en uso", Notification.Type.ERROR_MESSAGE);
					}	
				
			}
		});
		
		loginForm = new FormLayout();
		horizontalLayout = new HorizontalLayout();
		
		HorizontalLayout buttons = new HorizontalLayout();
		
		buttons.addComponent(loginButton);
		buttons.addComponent(regisButton);
		buttons.setSpacing(true);
		
		loginForm.addComponent(username);
		loginForm.addComponent(password);
		loginForm.addComponent(buttons);
		
		
		horizontalLayout.addComponent(loginForm);
		horizontalLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
		
	}
	
	private MainView getMainView(){
		Class<MainView> clazz;
		try {
			clazz = (Class<MainView>) Class.forName(usuarioService.getMainViewOfLoggedUser());
			Constructor<MainView> ctor = clazz.getConstructor();
			return ctor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			e.printStackTrace();
		}
		return null;
	}

	
}
