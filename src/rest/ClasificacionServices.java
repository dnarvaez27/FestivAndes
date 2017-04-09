package rest;

import tm.ClasificacionCM;
import vos.Clasificacion;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "clasificaciones" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class ClasificacionServices extends Services
{
	public ClasificacionServices( )
	{
	}
	
	public ClasificacionServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createClasificacion( Clasificacion clasificacion )
	{
		ClasificacionCM tm = new ClasificacionCM( getPath( ) );
		try
		{
			clasificacion = tm.createClasificacion( clasificacion );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( clasificacion ).build( );
	}
	
	@GET
	public Response getClasificaciones( )
	{
		List<Clasificacion> list;
		ClasificacionCM tm = new ClasificacionCM( getPath( ) );
		try
		{
			list = tm.getClasificaciones( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getClasificacion( @PathParam( "id" ) Long id )
	{
		Clasificacion clasificacion;
		ClasificacionCM tm = new ClasificacionCM( getPath( ) );
		try
		{
			clasificacion = tm.getClasificacion( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( clasificacion ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateClasificacion( @PathParam( "id" ) Long id, Clasificacion clasificacion )
	{
		ClasificacionCM tm = new ClasificacionCM( getPath( ) );
		try
		{
			clasificacion = tm.updateClasificacion( id, clasificacion );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( clasificacion ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteClasificacion( Long id )
	{
		ClasificacionCM tm = new ClasificacionCM( getPath( ) );
		try
		{
			tm.deleteClasificacion( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}