package daos.impl;

public class NeodatisEnumContainer {
	
	private String enumValue;
	private Class<?> enumClass;
	private Object obj;
	
	public NeodatisEnumContainer(String enumValue, Class<?> enumClass, Object obj) {
		this.enumValue = enumValue;
		this.enumClass = enumClass;
		this.obj = obj;
	}

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	public Class<?> getEnumClass() {
		return enumClass;
	}

	public void setEnumClass(Class<?> enumClass) {
		this.enumClass = enumClass;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	


}
