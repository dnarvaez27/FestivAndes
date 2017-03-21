package rest;

import tm.LugarTM;
import vos.Lugar;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class LugarServices extends Services
{
	@POST
	public Response createLugar( Lugar lugar )
	{
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			lugar = tm.createLugar( lugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugar ).build( );
	}
	
	@GET
	public Response getLugares( )
	{
		List<Lugar> list;
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			list = tm.getLugares( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getLugar( @PathParam( "id" ) Long id )
	{
		Lugar l;
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			l = tm.getLugar( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateLugar( @PathParam( "id" ) Long id, Lugar lugar )
	{
		Lugar l;
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			l = tm.updateLugar( id, lugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteLugar( @PathParam( "id" ) Long id )
	{
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			tm.deleteLugar( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}
