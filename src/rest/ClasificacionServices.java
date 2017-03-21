package rest;

import tm.ClasificacionTM;
import vos.Clasificacion;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "clasificaciones" )
public class ClasificacionServices extends Services
{
	@POST
	public Response createClasificacion( Clasificacion accesibilidad )
	{
		ClasificacionTM tm = new ClasificacionTM( getPath( ) );
		try
		{
			accesibilidad = tm.createClasificacion( accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@GET
	public Response getClasificacions( )
	{
		List<Clasificacion> list;
		ClasificacionTM tm = new ClasificacionTM( getPath( ) );
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
		Clasificacion ac;
		ClasificacionTM tm = new ClasificacionTM( getPath( ) );
		try
		{
			ac = tm.getClasificacion( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateClasificacion( @PathParam( "id" ) Long id, Clasificacion accesibilidad )
	{
		Clasificacion ac;
		ClasificacionTM tm = new ClasificacionTM( getPath( ) );
		try
		{
			ac = tm.updateClasificacion( id, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteClasificacion( Long id )
	{
		ClasificacionTM tm = new ClasificacionTM( getPath( ) );
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