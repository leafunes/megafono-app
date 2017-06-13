package view.camp;

import com.vaadin.ui.Component;

import data.Campaña;
import data.Tag;

public interface CampañaEditor extends Component{

	
	public void editCampaña(Campaña c);
	public void clear();
	public void commit();

	
}
