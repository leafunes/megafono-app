package data.precios;

import data.TipoAccionPublicitaria;

public class PrecioAccionPublicitaria extends Precio{
	
	TipoAccionPublicitaria objetoValuable;
	
	public PrecioAccionPublicitaria() {
		super();
	}

	public TipoAccionPublicitaria getObjetoValuable() {
		return this.objetoValuable;
	}

	public void setObjetoValuable(TipoAccionPublicitaria objetoValuable) {
		this.objetoValuable = objetoValuable;
	}
	
	

}
