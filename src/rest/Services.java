package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

public abstract class Services
{
	@Context
	protected ServletContext context;
	
	protected String getPath( )
	{
		return context.getRealPath( "WEB-INF/ConnecctionDate" );
	}
	
	protected String doErrorMessage( Exception e )
	{
		return "{ \"ERROR\": \"" + e.getMessage( ) + "\"}";
	}
}