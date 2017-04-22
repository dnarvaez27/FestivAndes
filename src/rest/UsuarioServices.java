package rest;

import tm.CompaniaDeTeatroCM;
import tm.UsuarioCM;
import tm.UsuarioRegistradoCM;
import vos.Usuario;
import vos.reportes.RFC7;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "usuarios" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class UsuarioServices extends Services
{
	public UsuarioServices( )
	{
	}
	
	public UsuarioServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createUsuario( Usuario usuario )
	{
		UsuarioCM tm = new UsuarioCM( getPath( ) );
		try
		{
			usuario = tm.createUsuario( usuario );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( usuario ).build( );
	}
	
	//	@GET
	//	public Response getUsuarios( )
	//	{
	//		List<Usuario> list;
	//		UsuarioCM tm = new UsuarioCM( getPath( ) );
	//		try
	//		{
	//			list = tm.getUsuarios( );
	//		}
	//		catch( SQLException e )
	//		{
	//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
	//		}
	//		return Response.status( 200 ).entity( list ).build( );
	//	}
	//
	
	@GET
	public Response getUsuariosRol( @QueryParam( "rol" ) String rol )
	{
		List<Usuario> list;
		try
		{
			if( rol != null && !rol.isEmpty( ) )
			{
				switch( rol )
				{
					case Usuario.USUARIO_COMPANIA:
					{
						CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
						return Response.status( 200 ).entity( tm.getCompaniaDeTeatros( ) ).build( );
					}
					case Usuario.USUARIO_REGISTRADO:
					{
						UsuarioRegistradoCM tm = new UsuarioRegistradoCM( getPath( ) );
						return Response.status( 200 ).entity( tm.getUsuarioRegistrados( ) ).build( );
					}
					default:
					{
						UsuarioCM tm = new UsuarioCM( getPath( ) );
						list = tm.getUsuariosRol( rol );
						break;
					}
				}
			}
			else
			{
				UsuarioCM tm = new UsuarioCM( getPath( ) );
				list = tm.getUsuarios( );
			}
			return Response.status( 200 ).entity( list ).build( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{id}/{tipo}" )
	public Response getUsuario( @PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo )
	{
		Usuario us;
		UsuarioCM tm = new UsuarioCM( getPath( ) );
		try
		{
			us = tm.getUsuario( id, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( us ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateUsuario( @PathParam( "id" ) Long id, Usuario usuario )
	{
		UsuarioCM tm = new UsuarioCM( getPath( ) );
		try
		{
			usuario = tm.updateUsuario( id, usuario );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( usuario ).build( );
	}
	
	@DELETE
	@Path( "{id}/{tipo}" )
	public Response deleteUsuario( @PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo )
	{
		UsuarioCM tm = new UsuarioCM( getPath( ) );
		try
		{
			tm.deleteUsuario( id, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	// REGISTRADOS
	
	@Path( "/registrados" )
	public UsuarioRegistradoServices getRegistrados( )
	{
		return new UsuarioRegistradoServices( context );
	}
	
	@GET
	@Path( "/asistencia" )
	public Response asistenciaUsuario( @HeaderParam( "id" ) Long id, @HeaderParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		UsuarioCM tm = new UsuarioCM( getPath( ) );
		List<RFC7> rfc7;
		try
		{
			rfc7 = tm.asistenciaClientesRegistrados( id, tipo, password );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( rfc7 ).build( );
	}
	
}