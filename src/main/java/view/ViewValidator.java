package view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.ui.Notification;

public class ViewValidator {
	
	class Condition{
		Predicate<Object> predicado;
		String errorMsg;
		
		public Condition(Predicate<Object> predicado, String errorMsg) {
			this.predicado = predicado;
			this.errorMsg = errorMsg;
		}
		
		
	}
	
	private Map<Function<?>, Condition> condiciones;
	
	
	public ViewValidator() {
		condiciones = new HashMap<>();
	}
	
	public void isFalse(Function<Boolean> f, String errorMsg){

		condiciones.put(f, new Condition(o -> (Boolean)o == false, errorMsg));
	}
	
	public void isTrue(Function<Boolean> f, String errorMsg){

		condiciones.put(f, new Condition(o -> (Boolean)o == true, errorMsg));
	}
	
	public void isEmpty(Function<String> f, String errorMsg){

		condiciones.put(f, new Condition(o -> ((String)o).isEmpty(), errorMsg));
	}
	
	public void isNotEmpty(Function<String> f, String errorMsg){
		
		condiciones.put(f, new Condition(o -> !((String)o).isEmpty(), errorMsg));
		
	}
	
	public void isNumber(Function<String> f, String errMsg) {

		condiciones.put(f, new Condition(o -> StringUtils.isNumeric((String)o), errMsg));
		
	}
	
	public boolean isValid(){
		for (Function<?> f : condiciones.keySet()) {
			
			Condition c = condiciones.get(f);
			Object toTest = f.execute();
			
			if(c.predicado.negate().test(toTest)){
				return false;
			}
			
		}
		
		return true;
	}
	
	public boolean testAll(){
		
		for (Function<?> f : condiciones.keySet()) {
			
			Condition c = condiciones.get(f);
			Object toTest = f.execute();
			
			if(c.predicado.negate().test(toTest)){
				Notification.show(c.errorMsg, Notification.Type.ERROR_MESSAGE);
				return false;
			}
			
		}
		
		return true;
		
	}

}
