package misc;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GlobalProperties {
	
	private Properties props;
	static private GlobalProperties singleton;
	
	static public GlobalProperties getGlobalProps(){
		if(singleton == null) singleton = new GlobalProperties();
		
		return singleton;
	}
	
	
	private GlobalProperties(){
		
		props = new Properties();
		
		try {
			
			props.load(new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"config.properties")));
		} 
		
		catch(Exception e) {
		e.printStackTrace();
		}
		
		
	}
	
	public Properties getProps(){
		return props;
	}

}
