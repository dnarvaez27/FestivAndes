package rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import tm.LocalidadTM;
import vos.Localidad;

@Path( "localidades" )
public class LocalidadServices extends Services{
	@POST
	public Response createLocalidad( Localidad localidad )
	{
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			localidad = tm.createLocalidad( localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( localidad ).build( );
	}
	
	@GET
	public Response getLocalidades( )
	{
		List<Localidad> list;
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			list = tm.getLocalidades( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getLocalidad( @PathParam( "id" ) Long id )
	{
		Localidad l;
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			l = tm.getLocalidad( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	@PUT
	@Path( "{id}" )
	public Response updateLocalidad( @PathParam( "id" ) Long id, Localidad localidad )
	{
		Localidad l;
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			l = tm.updateLocalidad( id, localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteLocalidad( @PathParam( "id" ) Long id )
	{
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			tm.deleteLocalidad( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}
