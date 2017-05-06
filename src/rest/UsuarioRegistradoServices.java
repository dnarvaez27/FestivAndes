package rest;

import tm.UsuarioRegistradoCM;
import tm.intermediate.PreferenciaGeneroTM;
import tm.intermediate.PreferenciaLugarTM;
import vos.Genero;
import vos.Lugar;
import vos.UsuarioRegistrado;
import vos.reportes.RFC12;
import vos.reportes.RFC7;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "registrados" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class UsuarioRegistradoServices extends Services
{
	public UsuarioRegistradoServices( )
	{
	}
	
	public UsuarioRegistradoServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createUsuarioRegistrado( UsuarioRegistrado accesibilidad,
	                                         @HeaderParam( "id" ) Long id, @HeaderParam( "password" ) String password )
	{
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
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
	public Response getUsuariosRegistrados( )
	{
		List<UsuarioRegistrado> list;
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
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
	public Response getUsuarioRegistrado( @PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo )
	{
		UsuarioRegistrado ac;
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
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
	public Response updateUsuarioRegistrado( @PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo, UsuarioRegistrado usuarioRegistrado )
	{
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
		try
		{
			usuarioRegistrado = tm.updateUsuarioRegistrado( id, tipo, usuarioRegistrado );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( usuarioRegistrado ).build( );
	}
	
	@DELETE
	@Path( "{id}/{tipo}" )
	public Response deleteUsuarioRegistrado( @PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo )
	{
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
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
	
	// BOLETAS
	
	@Path( "{idUsuario}/{idTipo}/boletas" )
	public BoletaServices getBoletas( )
	{
		return new BoletaServices( context );
	}
	
	// ABONOS
	
	@Path( "{idUsuario}/{idTipo}/abonos" )
	public AbonoServices getAbonos( )
	{
		return new AbonoServices( context );
	}
	
	// PREFERENCIA - GENERO
	
	@GET
	@Path( "{idUsuario}/{tipo}/generos" )
	public Response getGenerosPreferidos( @PathParam( "idUsuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo )
	{
		List<Genero> list;
		PreferenciaGeneroTM tm = new PreferenciaGeneroTM( getPath( ) );
		try
		{
			list = tm.getGenerosPreferidosByUser( idUsuario, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	// TODO: 8/04/2017 El nombre del genero por Json?
	@POST
	@Path( "{idUsuario}/{tipo}/generos/{idGenero}" )
	public Response createEntryGeneroPreferido(
			@PathParam( "idUsuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo, @PathParam( "idGenero" ) Long idGenero )
	{
		PreferenciaGeneroTM tm = new PreferenciaGeneroTM( getPath( ) );
		try
		{
			tm.createPreferenciaGenero( idUsuario, tipo, idGenero );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "{idUsuario}/{tipo}/generos/{idGenero}" )
	public Response deleteEntryGeneroPreferido(
			@PathParam( "idUsuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo, @PathParam( "idGenero" ) Long idGenero )
	{
		PreferenciaGeneroTM tm = new PreferenciaGeneroTM( getPath( ) );
		try
		{
			tm.deletePreferenciaGenero( idUsuario, tipo, idGenero );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	// PREFERENCIA - LUGARES
	@GET
	@Path( "{id_usuario}/{tipo}/lugares" )
	public Response getLugaresPreferidos( @PathParam( "id_usuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo )
	{
		List<Lugar> list;
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			list = tm.getLugaresPreferidosByUser( idUsuario, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id_usuario}/{tipo}/lugares/{id_lugar}" )
	public Response getLugarPreferido(
			@PathParam( "id_usuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo, @PathParam( "id_lugar" ) Long idLugar )
	{
		Lugar lugar;
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			lugar = tm.getPreferedLugarByUsar( idUsuario, tipo, idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugar ).build( );
	}
	
	// TODO: 8/04/2017 Nombre del Lugar por Json?
	@POST
	@Path( "{id_usuario}/{tipo}/lugares/{id_lugar}" )
	public Response createLugarPreferido(
			@PathParam( "id_usuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo, @PathParam( "id_lugar" ) Long idLugar )
	{
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			tm.createPreferenciaLugar( idUsuario, tipo, idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "{id_usuario}/{tipo}/lugares/{id_lugar}" )
	public Response deleteLugarPreferido(
			@PathParam( "id_usuario" ) Long idUsuario, @PathParam( "tipo" ) String tipo, @PathParam( "id_lugar" ) Long idLugar )
	{
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			tm.deletePreferenciaLugar( idUsuario, tipo, idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_usuario}/{tipo}/asistencia" )
	public Response asistenciaUsuario(
			@PathParam( "id_usuario" ) Long id, @PathParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
		RFC7 rfc7;
		try
		{
			rfc7 = tm.asistenciaRegistrado( id, tipo, password );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( rfc7 ).build( );
	}
	
	@GET
	@Path( "/buenos" )
	public Response clientesBuenos(
			@HeaderParam( "id_usuario" ) Long id,
			@HeaderParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password, @QueryParam( "cantidad" ) Integer cantidad )
	{
		UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
		List<RFC12> list;
		try
		{
			list = tm.clientesCheveres( id, tipo, password, cantidad );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
}