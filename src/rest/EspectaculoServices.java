package rest;

import tm.EspectaculoTM;
import tm.intermediate.EspectaculoGeneroTM;
import tm.intermediate.OfrecenTM;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;
import vos.reportes.RFC4;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "espectaculos" )
public class EspectaculoServices
{
	@Context
	private ServletContext context;
	
	@POST
	public Response createEspectaculo( Long id, String password, Espectaculo espectaculo )
	{
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			espectaculo = tm.createEspectaculo( id, password, espectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( espectaculo ).build( );
	}
	
	@GET
	public Response getEspectaculos( )
	{
		List<Espectaculo> list;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			list = tm.getEspectaculos( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getEspectaculo( @PathParam( "id" ) Long id )
	{
		Espectaculo es;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			es = tm.getEspectaculo( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( es ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateEspectaculo( @PathParam( "id" ) Long id, Espectaculo espectaculo )
	{
		Espectaculo es;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			es = tm.updateEspectaculo( id, espectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( es ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteEspectaculo( @PathParam( "id" ) Long id )
	{
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			tm.deleteEspectaculo( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id}/generos" )
	public Response getGenerosFrom( @PathParam( "id" ) Long idEspectaculo )
	{
		List<Espectaculo> list;
		EspectaculoGeneroTM tm = new EspectaculoGeneroTM( getPath( ) );
		try
		{
			list = tm.getEspectaculoWithGenero( idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@DELETE
	@Path( "{id_espectaculo}/generos/{id_genero}" )
	public Response deleteGenero(
			@PathParam( "id_espectaculo" ) Long idEspectaculo,
			@PathParam( "id_genero" ) Long idGenero )
	{
		EspectaculoGeneroTM tm = new EspectaculoGeneroTM( getPath( ) );
		try
		{
			tm.deleteEspectaculoGenero( idEspectaculo, idGenero );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@POST
	@Path( "{id_espectaculo}/generos" )
	public Response createEntry( @PathParam( "id_espectaculo" ) Long idEspectaculo, Long idGenero )
	{
		// TODO Long idGenero por JSON o por URI?
		EspectaculoGeneroTM tm = new EspectaculoGeneroTM( getPath( ) );
		try
		{
			tm.createEspectaculoGenero( idEspectaculo, idGenero );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_espectaculo}/companias" )
	public Response getCompaniasFromEspectaculo( @PathParam( "id_espectaculo" ) Long idEspectaculo )
	{
		List<CompaniaDeTeatro> list;
		OfrecenTM tm = new OfrecenTM( getPath( ) );
		try
		{
			list = tm.getCompaniasWhoOffer( idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id_espectaculo}/rfc4" )
	public Response generarReporteRFC4( @PathParam( "id_espectaculo" ) Long idEspectaculo )
	{
		RFC4 req;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			req = tm.generarReporte( idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( req ).build( );
	}
	
	private String getPath( )
	{
		return context.getRealPath( "WEB-INF/ConnecctionDate" );
	}
	
	private String doErrorMessage( Exception e )
	{
		return "{ \"ERROR\": \"" + e.getMessage( ) + "\"}";
	}
}