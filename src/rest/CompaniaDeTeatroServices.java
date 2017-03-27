package rest;

import tm.CompaniaDeTeatroTM;
import tm.intermediate.OfrecenTM;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "companias" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class CompaniaDeTeatroServices extends Services
{
	public CompaniaDeTeatroServices( )
	{
	}
	
	public CompaniaDeTeatroServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createCompaniaDeTeatro( CompaniaDeTeatro compania,
	                                        @HeaderParam( "id" ) Long id,
	                                        @HeaderParam( "password" ) String password )
	{
		System.out.println( compania.getFechaLlegada( ) );
		
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			compania = tm.createCompaniaDeTeatro( id, password, compania );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( compania ).build( );
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
	@Path( "{id}" )
	public Response deleteCompaniaDeTeatro( @PathParam( "id" ) Long id )
	{
		CompaniaDeTeatroTM tm = new CompaniaDeTeatroTM( getPath( ) );
		try
		{
			tm.deleteCompaniaDeTeatro( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	// ESPECTACULOS
	@GET
	@Path( "{id_compania}/espectaculos" )
	public Response getEspectaculosFromCompania( @PathParam( "id_compania" ) Long idCompania )
	{
		List<Espectaculo> list;
		OfrecenTM tm = new OfrecenTM( getPath( ) );
		try
		{
			list = tm.getEspectaculosFrom( idCompania );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id_compania}/espectaculos/{id_espectaculo}" )
	public Response getEspectaculosFromCompania(
			@PathParam( "id_compania" ) Long idCompania,
			@PathParam( "id_espectaculo" ) Long idEspectaculo )
	{
		Espectaculo espectaculo;
		OfrecenTM tm = new OfrecenTM( getPath( ) );
		try
		{
			espectaculo = tm.getEspectaculoFrom( idCompania, idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( espectaculo ).build( );
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