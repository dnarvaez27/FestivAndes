package rest;

import tm.AccesibilidadCM;
import tm.intermediate.LugarAccesibilidadTM;
import vos.Accesibilidad;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "accesibilidades" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class AccesibilidadServices extends Services
{
	public AccesibilidadServices( )
	{
	
	}
	
	public AccesibilidadServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createAccesibilidad( Accesibilidad accesibilidad )
	{
		AccesibilidadCM tm = new AccesibilidadCM( getPath( ) );
		try
		{
			accesibilidad = tm.createAccesibilidad( accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@GET
	public Response getAccesibilidades( @PathParam( "idLugar" ) Long idLugar )
	{
		List<Accesibilidad> list;
		LugarAccesibilidadTM tm = new LugarAccesibilidadTM( getPath( ) );
		try
		{
			list = tm.getAccesibilidadsFromLugar( idLugar );
		}
		catch( SQLException e )
		{
			e.printStackTrace( );
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "all" )
	public Response getAccesibilidades( )
	{
		List<Accesibilidad> list;
		AccesibilidadCM tm = new AccesibilidadCM( getPath( ) );
		try
		{
			list = tm.getAccesibilidades( );
		}
		catch( SQLException e )
		{
			e.printStackTrace( );
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getAccesibilidad( @PathParam( "id" ) Long id )
	{
		Accesibilidad ac;
		AccesibilidadCM tm = new AccesibilidadCM( getPath( ) );
		try
		{
			ac = tm.getAccesibilidad( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateAccesibilidad( @PathParam( "id" ) Long id, Accesibilidad accesibilidad )
	{
		AccesibilidadCM tm = new AccesibilidadCM( getPath( ) );
		try
		{
			accesibilidad = tm.updateAccesibilidad( id, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteAccesibilidad( @PathParam( "id" ) Long id )
	{
		AccesibilidadCM tm = new AccesibilidadCM( getPath( ) );
		try
		{
			tm.deleteAccesibilidad( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}