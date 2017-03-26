package rest;

import tm.FestivalTM;
import vos.Festival;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "festivales" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class FestivalServices extends Services
{
	public FestivalServices( )
	{
	}
	
	public FestivalServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createFestival( Festival object )
	{
		FestivalTM tm = new FestivalTM( getPath( ) );
		try
		{
			object = tm.createFestival( object );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( object ).build( );
	}
	
	@GET
	public Response getFestivals( )
	{
		List<Festival> list;
		FestivalTM tm = new FestivalTM( getPath( ) );
		try
		{
			list = tm.getFestivals( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getFestival( @PathParam( "id" ) Long id )
	{
		Festival ac;
		FestivalTM tm = new FestivalTM( getPath( ) );
		try
		{
			ac = tm.getFestival( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateFestival( @PathParam( "id" ) Long id, Festival object )
	{
		Festival ac;
		FestivalTM tm = new FestivalTM( getPath( ) );
		try
		{
			ac = tm.updateFestival( id, object );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteFestival( @PathParam( "id" ) Long id )
	{
		FestivalTM tm = new FestivalTM( getPath( ) );
		try
		{
			tm.deleteFestival( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@Path( "{idFestival}/espectaculos" )
	public EspectaculoServices getCustomer( )
	{
		return new EspectaculoServices( context );
	}
}