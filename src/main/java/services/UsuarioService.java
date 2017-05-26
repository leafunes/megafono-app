package services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import daos.iface.DAORol;
import daos.iface.DAOUsuario;
import daos.impl.DAORolNeodatis;
import daos.impl.DAOUsuarioNeodatis;
import data.Rol;
import data.Usuario;

public class UsuarioService {
	
	private static UsuarioService singleton;
	
	private DAOUsuario daoUsuario;
	private DAORol daoRol;
	private Usuario logedUser;
	
	public static UsuarioService getService(){
		if(singleton == null) singleton = new UsuarioService();
		
		return singleton;
	}
	
	private UsuarioService(){
		daoUsuario = new DAOUsuarioNeodatis();
		daoRol = new DAORolNeodatis();
	}
	
	public void altaUsuario(String email, String password, String rol){
		
		if(!daoRol.existeRol(rol)) throw new IllegalArgumentException("Rol no existente");
		
		Rol rolToAdd= daoRol.getRolByName(rol);
		
		Usuario toAdd = new Usuario(email, getMd5Of(password), rolToAdd);
		
		daoUsuario.save(toAdd);
		
		
	}
	
	public boolean isUsernameInUse(String nombre){
		return daoUsuario.isUsernameInUse(nombre);
	}
	
	public boolean loginUsuario(String nombre, String password){
		
		Usuario user = daoUsuario.getUsuario(nombre, getMd5Of(password));
		
		if(user != null){
			
			logedUser = user;
			
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private String getMd5Of(String s){
		
		MessageDigest m;
		String hashText = "";
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(s.getBytes());
			
			byte[] digest = m.digest();
			
			BigInteger bigInt = new BigInteger(1,digest);
			hashText = bigInt.toString(16);
			
			while(hashText.length() < 32 ){
			  hashText = "0"+hashText;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashText;
		
	}

}
