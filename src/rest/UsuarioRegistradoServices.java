package rest;

import tm.UsuarioRegistradoTM;
import vos.UsuarioRegistrado;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "registrados" )
public class UsuarioRegistradoServices extends Services
{
	@POST
	public Response createUsuarioRegistrado( UsuarioRegistrado accesibilidad )
	{
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			accesibilidad = tm.createUsuarioRegistrado( accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@GET
	public Response getUsuarioRegistrados( )
	{
		List<UsuarioRegistrado> list;
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			list = tm.getUsuarioRegistrados( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getUsuarioRegistrado( @PathParam( "id" ) Long id )
	{
		UsuarioRegistrado ac;
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			ac = tm.getUsuarioRegistrado( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateUsuarioRegistrado(
			@PathParam( "id" ) Long id, UsuarioRegistrado accesibilidad )
	{
		UsuarioRegistrado ac;
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			ac = tm.updateUsuarioRegistrado( id, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteUsuarioRegistrado( @PathParam( "id" ) Long id )
	{
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			tm.deleteUsuarioRegistrado( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}