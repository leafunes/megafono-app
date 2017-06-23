package misc;

import org.joda.time.DateTime;

import daos.iface.DAOPrecio;
import daos.iface.DAORol;
import daos.iface.DAOUsuario;
import daos.impl.DAOPrecioNeodatis;
import daos.impl.DAORolNeodatis;
import daos.impl.DAOUsuarioNeodatis;

import data.Rol;
import data.TipoAccionPublicitaria;
import data.Usuario;
import data.precios.PrecioAccionPublicitaria;

public class HardcodedInfo {
	
	//informacion necesaria para arrancar 
	//desde cero (sin base de datos) la aplicacion
	
	private static DAORol daoRol = new DAORolNeodatis();
	private static DAOUsuario daoUsuario = new DAOUsuarioNeodatis();
	private static DAOPrecio daoPrecio = new DAOPrecioNeodatis();
	
	private static final Rol cliente = new Rol("ClienteRol", "Gestion de campañas, agregar tags a campañas, agregar "
				+ "acciones publicitarias a campañas");
	private static final Rol analista = new Rol("AnalistaRol","Gestion de Tags, Gestion de acciones publicitarias "
			+ "Gestion de precios, vista de reporting");
	
	private static final Usuario admin = new Usuario("admin@admin.com", "21232f297a57a5a743894a0e4a801fc3", analista);
	
	
	
	public static void init(){
		//TODO: agregar permisos y mainViews
		
		if(!daoRol.existeRol(cliente.getNombre()))daoRol.save(cliente);
		
		if(!daoRol.existeRol(analista.getNombre()))daoRol.save(analista);
		
		if(!daoUsuario.existeUsuario(admin.getEmail(), admin.getMd5()))daoUsuario.save(admin);
		
		
		for(TipoAccionPublicitaria t : TipoAccionPublicitaria.values()){
			
			if(daoPrecio.getCurrentPriceOf(t) == null){
				PrecioAccionPublicitaria p = new PrecioAccionPublicitaria();
				p.setCurrent(true);
				p.setFechaCreacion(DateTime.now());
				p.setMonto("0");
				p.setObjetoValuable(t);
				
				daoPrecio.save(p);
			}
			
		}
		
	}
	


}