package rest;

import tm.GeneroTM;
import tm.intermediate.EspectaculoGeneroTM;
import vos.Espectaculo;
import vos.Genero;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "generos" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class GeneroServices
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
	public Response createGenero( Genero genero )
	{
		GeneroTM tm = new GeneroTM( getPath( ) );
		try
		{
			genero = tm.createGenero( genero );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( genero ).build( );
	}
	
	@GET
	public Response getGenero( )
	{
		List<Genero> list;
		GeneroTM tm = new GeneroTM( getPath( ) );
		try
		{
			list = tm.getGeneros( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getGenero( @PathParam( "id" ) Long id )
	{
		Genero gen;
		GeneroTM tm = new GeneroTM( getPath( ) );
		try
		{
			gen = tm.getGenero( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( gen ).build( );
	}
	
	@GET
	@Path( "{id_genero}/espectaculos" )
	public Response getEspectaculosWithGenero( @PathParam( "id_genero" ) Long idGenero )
	{
		List<Espectaculo> list;
		EspectaculoGeneroTM tm = new EspectaculoGeneroTM( getPath( ) );
		try
		{
			list = tm.getEspectaculoWithGenero( idGenero );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
}