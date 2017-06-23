package view.camp;

import com.vaadin.ui.Component;

import data.Campania;
import data.Tag;

public interface CampaniaEditor extends Component{

	
	public void editCampania(Campania c);
	public void clear();
	public void commit();
	
	public boolean isValid();

	
}
