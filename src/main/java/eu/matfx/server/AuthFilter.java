package eu.matfx.server;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Class to check the authorization
 * @author m.goerlich
 *
 */

public class AuthFilter implements ContainerRequestFilter
{
	//hardcoded user pwd
	private String USER = "mg";
	private String PASSWORD = "mg";

	@Override
	public void filter(ContainerRequestContext  containerRequest) throws IOException 
	{
		MultivaluedMap<String, String> headerMap = containerRequest.getHeaders();
		//get from header the authorization
		String value = headerMap.getFirst("authorization");
        if(value != null && value.length() > 0 && value.startsWith("Basic "))
        {
        	value = value.replace("Basic ", "");
        	//decode base 64 string and split the values
        	String[] splitValue = decode(value);
        	System.out.println("splitValue " + 	splitValue[0] + " " + splitValue[1] );
        	//check the values 
        	if(USER.equals(splitValue[0]) && PASSWORD.equals(splitValue[1]))
        	{
        		
        		//all is fine nothing to do
        		
        	}
        	else
        	{
        		System.out.println("test 1");
        		//no authorization
                throw new WebApplicationException(Status.UNAUTHORIZED);
        	}
        	
        }
        //no authorization
        else
        {
        	unauthenticated("");
        //	System.out.println("test 2");
        //	throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        
        
		
		
		
	}
	
	private static void unauthenticated (String user) {
	     throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
	         .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Server\"")
	         .build());
	 }
	
	private static String[] decode(final String encodedString) 
	{
		Decoder decoder = Base64.getDecoder();
		final byte[] decodedBytes =	decoder.decode(encodedString.getBytes());
		final String pair = new String(decodedBytes);
		final String[] userDetails = pair.split(":", 2);
		return userDetails;
	}

}
