package daos.impl;

import java.util.ArrayList;
import java.util.List;

import daos.iface.DAORol;
import data.Permiso;
import data.Rol;

public class DAORolNeodatis extends DAONeodatis<Rol> implements DAORol{
	
	//TODO hacer neodatis
	
	private List<Rol> db;
	
	
	public DAORolNeodatis() {
		db = new ArrayList<Rol>();
		
		Rol rolCliente = new Rol("cliente", "Gestion de campanias, agregar tags a campanias, agregar "
				+ "acciones publicitarias a campanias");
		
		rolCliente.addPermiso(new Permiso("permiso cliente", ""));
		
		Rol rolAnalista = new Rol("analista","Gestion de Tags, Gestion de acciones publicitarias "
				+ "Gestion de precios, vista de reporting");
		
		rolAnalista.addPermiso(new Permiso("permiso analista", ""));
		
		db.add(rolCliente);
		db.add(rolAnalista);
		
	}

	@Override
	public boolean existeRol(String nombre) {
		
		return getRolByName(nombre) != null;
		
	}

	@Override
	public Rol getRolByName(String nombre) {
		for (Rol r : db) {
			if(r.getNombre().equals(nombre))
				return r;
		}
		
		return null;
	}

}
