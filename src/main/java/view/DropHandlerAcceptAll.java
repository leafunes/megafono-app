package view;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;

public abstract class DropHandlerAcceptAll implements DropHandler{

	@Override
	public abstract void drop(DragAndDropEvent event);

	@Override
	public AcceptCriterion getAcceptCriterion() {
		return AcceptAll.get();
	}

}
