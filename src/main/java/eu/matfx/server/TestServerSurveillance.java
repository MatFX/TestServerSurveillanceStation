package eu.matfx.server;


import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;



public class TestServerSurveillance
{
	/**
	 * ip to listening
	 */
	private final static String DEFAULT_SERVER_IP = "http://192.168.150.81:9999/";
	
	
   public static void main( String[] args ) throws IOException, InterruptedException 
   {
	   
	   //args with a new IP? no => use default
	   String baseUrl = ( args.length > 0 ) ? args[0] : DEFAULT_SERVER_IP;
      
	   //init the service at the server
	   ResourceConfig rc = new ResourceConfig();
	   //authorization filter
	   rc.register(AuthFilter.class);
	   //motion detection service
	   rc.register(AService.class);
	   //with the logging filter you can see the incoming http request;
	   /** Example console output:
	    * 
	    *   Sep 03, 2018 9:52:34 AM org.glassfish.jersey.filter.LoggingFilter log
			INFORMATION: 4 * Server has received a request on thread grizzly-http-server-3
			4 > GET http://192.168.150.81:9999/motiondetection/camera1
			4 > accept: application/xml
			4 > authorization: Basic bWc6bWc=
			4 > connection: keep-alive
			4 > host: 192.168.150.81:9999
			4 > user-agent: Jersey/2.23.1 (HttpUrlConnection 1.8.0_172)
	    */
	   rc.register(LoggingFilter.class);    
     
      
	   
	   final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
            URI.create( baseUrl ), rc, false );
    
	   Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
	   
		   @Override
	       public void run() 
	       {
	            server.shutdownNow();
	       }
	   }));
	   server.start();
	   System.out.println( String.format( "\nGrizzly-HTTP-Server started with URL: %s\n",
                                         baseUrl + "motiondetection/{endpoint}" ) );
     
	   Thread.currentThread().join();
     
     
   }
}
