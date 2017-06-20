package data.precios;

import data.Tag;

public class PrecioTag extends Precio{
	
	private Tag objetoValuable;
	
	public PrecioTag() {
		
		super();
	}

	public Tag getObjetoValuable() {
		return objetoValuable;
	}

	public void setObjetoValuable(Tag objetoValuable) {
		this.objetoValuable = objetoValuable;
	}
	
	

}
