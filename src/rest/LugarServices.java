package rest;

import tm.LugarCM;
import tm.intermediate.LugarRequerimientoTM;
import tm.intermediate.PreferenciaLugarTM;
import vos.Lugar;
import vos.RequerimientoTecnico;
import vos.UsuarioRegistrado;
import vos.reportes.RFC2;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "lugares" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class LugarServices extends Services
{
	// LUGAR
	public LugarServices( )
	{
	}
	
	public LugarServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createLugar( Lugar lugar,
	                             @HeaderParam( "id" ) Long id, @HeaderParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		LugarCM tm = new LugarCM( getPath( ) );
		try
		{
			lugar = tm.createLugar( id, tipo, password, lugar );
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
		LugarCM tm = new LugarCM( getPath( ) );
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
		LugarCM tm = new LugarCM( getPath( ) );
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
		LugarCM tm = new LugarCM( getPath( ) );
		try
		{
			lugar = tm.updateLugar( id, lugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugar ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteLugar( @PathParam( "id" ) Long id )
	{
		LugarCM tm = new LugarCM( getPath( ) );
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
	
	// REQUERIMIENTOS FUNCIONALES
	
	@GET
	@Path( "{idLugar}/rfc2" )
	public Response rfc2( @PathParam( "idLugar" ) Long idLugar )
	{
		LugarCM tm = new LugarCM( getPath( ) );
		RFC2 rfc2;
		try
		{
			rfc2 = tm.generarReporte( idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( rfc2 ).build( );
	}
	
	// PREFERENCIA USUARIOS
	
	@GET
	@Path( "{id_lugar}/preferedBy" )
	public Response getUsuariosWhoPrefers( @PathParam( "id_lugar" ) Long idLugar )
	{
		List<UsuarioRegistrado> list;
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			list = tm.getUsersWhoPrefer( idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	// REQUERIMIENTOS_LUGAR
	
	@GET
	@Path( "{id_lugar}/requerimientos" )
	public Response getRequerimientosFromLugar( @PathParam( "id_lugar" ) Long idLugar )
	{
		List<RequerimientoTecnico> list;
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			list = tm.getRequerimientosFromLugar( idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	// TODO: 8/04/2017 Json con el nombre del req?
	@POST
	@Path( "{id_lugar}/requerimientos/{id_req}" )
	public Response createRequerimientoFromLugar( @PathParam( "id_lugar" ) Long idLugar, @PathParam( "id_req" ) Long idRequerimiento )
	{
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			tm.createLugarRequerimiento( idLugar, idRequerimiento );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "{id_lugar}/requerimientos/{id_req}" )
	public Response deleteRequerimientoFromLugar( @PathParam( "id_lugar" ) Long idLugar, @PathParam( "id_req" ) Long idRequerimiento )
	{
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			tm.deleteLugarRequerimiento( idLugar, idRequerimiento );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	// LOCALIDADES
	
	@Path( "{idLugar}/localidades" )
	public LocalidadServices getLocalidades( @PathParam( "idLugar" ) Long idLugar )
	{
		return new LocalidadServices( context );
	}
	
	// ACCESIBILIDADES
	
	@Path( "{idLugar}/accesibilidades" )
	public AccesibilidadServices getAccesibilidades( )
	{
		return new AccesibilidadServices( context );
	}
}