package rest;

import tm.GeneroCM;
import tm.intermediate.EspectaculoGeneroTM;
import vos.Espectaculo;
import vos.Genero;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "generos" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class GeneroServices extends Services
{
	public GeneroServices( )
	{
	}
	
	public GeneroServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createGenero( Genero genero )
	{
		GeneroCM tm = new GeneroCM( getPath( ) );
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
		GeneroCM tm = new GeneroCM( getPath( ) );
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
		Genero genero;
		GeneroCM tm = new GeneroCM( getPath( ) );
		try
		{
			genero = tm.getGenero( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( genero ).build( );
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