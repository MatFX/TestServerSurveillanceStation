package eu.matfx.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


/**
 * example for motion detection service. to trigger anything at the server.
 * @author m.goerlich
 *
 */
@Path("motiondetection/{endpoint}")
public class AService
{

	@GET 
	@Consumes("application/x-www-form-urlencoded")
	public void doMotionDetection(@PathParam("endpoint") String id)
	{
	
		//normally any other thing will be trigger at the server...for example: turn on all lights at the house.
		System.out.println("motion detected with endpoint: " + id);
		
	}

	
}
