package eu.matfx.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * Simulated a action rule from the Synology Surveillance Station 
 * @author m.goerlich
 *
 */
public class TestClient 
{
	
	private static final String HTTP_ADDRESS_TO_SERVER = "http://192.168.150.81:9999";
	
	private static final String USER = "mg";
	
	private static final String PASSWORD = "mg";
	
	
	public static void main(String[] args)
	{
		
		//
		Client c = ClientBuilder.newClient();
		c.register(HttpAuthenticationFeature.basic(USER, PASSWORD));
		WebTarget target = c.target( HTTP_ADDRESS_TO_SERVER );
		String webContextPath = "/motiondetection/camera1";
	    
	      
	    do
	    {
	    	  
	    	//request to the server
	    	System.out.println("" + target.path(webContextPath).request(MediaType.APPLICATION_XML).get());
	    	  
	    	  
	    	  try 
	    	  {
	    		  //sleep 5 seconds and the request again
	    		  Thread.sleep(5000);
	    	  }
	    	  catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    	  }
	    	  
	    }
	    //never ending
	    while(true);
	      
	}

}
