package misc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;
import org.neodatis.odb.OdbConfiguration;

import services.ScheduleService;

/**
 * Application Lifecycle Listener implementation class NeodatisServerContentListener
 *
 */
@WebListener
public class NeodatisServerContentListener implements ServletContextListener {

	private static final int NEODATIS_SERVER_PORT = 1337;
	private	static ODBServer server;
	private static boolean isOk;
	
    public NeodatisServerContentListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         System.out.println("Bajando Neodatis");
         
         if(server != null){
        	 try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
         }
         
         ScheduleService.getService().stop();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	try{
    		System.out.println("Inicializando Neodatis");
    		
    		server = ODBFactory.openServer(NEODATIS_SERVER_PORT);
    		
    		server.startServer(true);
    		
    		OdbConfiguration.setReconnectObjectsToSession(true);
    		
    		HardcodedInfo.init();
    		ScheduleService.getService();
    		
    		isOk = true;
    		
    	}
    	catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	
    	
    }
    
    public static boolean isOk(){
    	return isOk;
    }
    
    public static ODBServer getServer(){
    	return server;
    }
	
}
