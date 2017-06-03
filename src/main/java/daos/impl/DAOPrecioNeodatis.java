package daos.impl;

import java.util.Collection;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.iface.DAOPrecio;
import data.Precio;
import data.Tag;

public class DAOPrecioNeodatis extends DAONeodatis<Precio> implements DAOPrecio {

	@Override
	public Precio getCurrentPriceOf(Tag t) {

		IQuery query = new SimpleNativeQuery(){
			public boolean match(Precio p) {
				
				Tag tag = (Tag)p.getObjetoValuable();
				
                return p.isCurrent() && t.equals(tag);
            }
			
		};
		
		openClient();
		
		Objects<Precio> objs = odb.getObjects(query);
		
		Precio toReturn = null;
		
		if(!objs.isEmpty())
			toReturn = objs.getFirst();
		
		closeClient();
		
		return toReturn;
		
		
		
	}

}
