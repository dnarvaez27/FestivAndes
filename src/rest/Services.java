package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * Created by Dave on 25/03/2017.
 */
public abstract class Services
{
	@Context
	protected ServletContext context;
	
	public Services( )
	{
	
	}
	
	public Services( ServletContext context )
	{
		this.context = context;
	}
	
	protected String getPath( )
	{
		return context.getRealPath( "WEB-INF/ConnectionData" );
	}
	
	protected String doErrorMessage( Exception e )
	{
		return "{ \"ERROR\": \"" + e.getMessage( ) + "\"}";
	}
}