package rest;

import tm.CompaniaDeTeatroTM;
import tm.intermediate.OfrecenTM;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "companias" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class CompaniaDeTeatroServices
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
	public Response createCompaniaDeTeatro( Long id, String password, CompaniaDeTeatro accesibilidad )
	{
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			accesibilidad = tm.createCompaniaDeTeatro( id, password, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@GET
	public Response getCompaniaDeTeatros( )
	{
		List<CompaniaDeTeatro> list;
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			list = tm.getCompaniaDeTeatros( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getCompaniaDeTeatro( @PathParam( "id" ) Long id )
	{
		CompaniaDeTeatro ac;
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			ac = tm.getCompaniaDeTeatro( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateCompaniaDeTeatro(
			@PathParam( "id" ) Long id, CompaniaDeTeatro companiaDeTeatro )
	{
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			tm.updateCompaniaDeTeatro( id, companiaDeTeatro );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( companiaDeTeatro ).build( );
	}
	
	@DELETE
	@Path( "{id}/{tipo}" )
	public Response deleteCompaniaDeTeatro(
			@PathParam( "id" ) Long id, @PathParam( "tipo" ) String tipo )
	{
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			tm.deleteCompaniaDeTeatro( id, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_compania}/espectaculos" )
	public Response getEspectaculosFromCompania( @PathParam( "id_compania" ) Long idCompania )
	{
		List<Espectaculo> list;
		OfrecenTM tm = new OfrecenTM( getPath( ) );
		try
		{
			list = tm.getEspectaculoFrom( idCompania );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
		
	}
	
	@DELETE
	@Path( "{id_compania}/espectaculos/{id_espectaculo}" )
	public Response deleteEspectaculoFromCompania(
			@PathParam( "id_compania" ) Long idCompania,
			@PathParam( "id_espectaculo" ) Long idEspectaculo )
	{
		OfrecenTM tm = new OfrecenTM( getPath( ) );
		try
		{
			tm.deleteEntryOfrecen( idCompania, idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@POST
	@Path( "{id_compania}/espectaculos/{id_espectaculo}" )
	public Response assignEspectaculoToCompania(
			@PathParam( "id_compania" ) Long idCompania,
			@PathParam( "id_espectaculo" ) Long idEspectaculo )
	{
		OfrecenTM tm = new OfrecenTM( getPath( ) );
		try
		{
			tm.createOfrecen( idCompania, idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}