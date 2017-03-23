package rest;

import tm.UsuarioRegistradoTM;
import tm.intermediate.PreferenciaLugarTM;
import vos.Lugar;
import vos.UsuarioRegistrado;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "registrados" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class UsuarioRegistradoServices
{
	@Context
	private ServletContext context;
	
	private String getPath( )
	{
		return context.getRealPath( "WEB-INF/ConnectionData" );
	}
	
	private String doErrorMessage( Exception e )
	{
		return "{ \"ERROR\": \"" + e.getMessage( ) + "\"}";
	}
	
	@POST
	public Response createUsuarioRegistrado( Long id, String password, UsuarioRegistrado accesibilidad )
	{
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			accesibilidad = tm.createUsuarioRegistrado( id, password, accesibilidad );
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
	@Path( "{id}/{tipo}" )
	public Response getUsuarioRegistrado( @PathParam( "id" ) Long id, String tipo )
	{
		UsuarioRegistrado ac;
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			ac = tm.getUsuarioRegistrado( id, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}/{tipo}" )
	public Response updateUsuarioRegistrado(
			@PathParam( "id" ) Long id,
			@PathParam( "tipo" ) String tipo, UsuarioRegistrado accesibilidad )
	{
		UsuarioRegistrado ac;
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			ac = tm.updateUsuarioRegistrado( id, tipo, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@DELETE
	@Path( "{id}/{tipo}" )
	public Response deleteUsuarioRegistrado(
			@PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo )
	{
		UsuarioRegistradoTM tm = new UsuarioRegistradoTM( getPath( ) );
		try
		{
			tm.deleteUsuarioRegistrado( id, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_usuario}/lugares" )
	public Response getLugaresPreferidos( @PathParam( "id_usuario" ) Long idUsuario )
	{
		List<Lugar> list;
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			list = tm.getLugaresPreferidosByUser( idUsuario );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id_usuario}/lugares/{id_lugar}" )
	public Response getLugarPreferido(
			@PathParam( "id_usuario" ) Long idUsuario, @PathParam( "id_lugar" ) Long idLugar )
	{
		Lugar lugar;
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			lugar = tm.getPreferedLugarByUsar( idUsuario, idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugar ).build( );
	}
	
	@POST
	@Path( "{id_usuario}/lugares/{id_lugar}" )
	public Response createLugarPreferido(
			@PathParam( "id_usuario" ) Long idUsuario, @PathParam( "id_lugar" ) Long idLugar )
	{
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			tm.createPreferenciaLugar( idUsuario, idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "{id_usuario}/lugares/{id_lugar}" )
	public Response deleteLugarPreferido(
			@PathParam( "id_usuario" ) Long idUsuario, @PathParam( "id_lugar" ) Long idLugar )
	{
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			tm.deletePreferenciaLugar( idUsuario, idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}