package rest;

import tm.AccesibilidadTM;
import vos.Accesibilidad;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "accesibilidades" )
public class AccesibilidadServices extends Services
{
	@POST
	public Response createAccesibilidad( Accesibilidad accesibilidad )
	{
		AccesibilidadTM tm = new AccesibilidadTM( getPath( ) );
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
	public Response getAccesibilidades( )
	{
		List<Accesibilidad> list;
		AccesibilidadTM tm = new AccesibilidadTM( getPath( ) );
		try
		{
			list = tm.getAccesibilidades( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getAccesibilidad( @PathParam( "id" ) Long id )
	{
		Accesibilidad ac;
		AccesibilidadTM tm = new AccesibilidadTM( getPath( ) );
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
}