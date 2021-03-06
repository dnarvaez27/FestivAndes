package rest;

import exceptions.IncompleteReplyException;
import protocolos.ProtocoloCompania;
import tm.CompaniaDeTeatroCM;
import tm.intermediate.OfrecenTM;
import utilities.SQLUtils;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;
import vos.UsuarioRegistrado;
import vos.reportes.RFC8;

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
	                                        @HeaderParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			compania = tm.createCompaniaDeTeatro( id, tipo, password, compania );
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
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
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
		CompaniaDeTeatro companiaDeTeatro;
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			companiaDeTeatro = tm.getCompaniaDeTeatro( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( companiaDeTeatro ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateCompaniaDeTeatro( @PathParam( "id" ) Long id, CompaniaDeTeatro companiaDeTeatro )
	{
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			companiaDeTeatro = tm.updateCompaniaDeTeatro( id, companiaDeTeatro );
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
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			tm.deleteCompaniaDeTeatro( id );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "/remote/{id}" )
	public Response deleteCompaniaDeTeatroRemote( @PathParam( "id" ) Long id )
	{
		List<ProtocoloCompania> list;
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			list = tm.deleteCompaniaDeTeatroRemote( id );
		}
		catch( IncompleteReplyException e )
		{
			return Response.status( Response.Status.OK ).entity( e.getPartialResponse( ) ).build( );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
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
	public Response getEspectaculosFromCompania( @PathParam( "id_compania" ) Long idCompania, @PathParam( "id_espectaculo" ) Long idEspectaculo )
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
	public Response deleteEspectaculoFromCompania( @PathParam( "id_compania" ) Long idCompania, @PathParam( "id_espectaculo" ) Long idEspectaculo )
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
	public Response assignEspectaculoToCompania( @PathParam( "id_compania" ) Long idCompania, @PathParam( "id_espectaculo" ) Long idEspectaculo )
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
	
	@GET
	@Path( "{id_compania}/informacion" )
	public Response infoCompania(
			@HeaderParam( "id_compania" ) Long idCompania, @HeaderParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		RFC8 rfc8;
		try
		{
			
			rfc8 = tm.informacionCompania( idCompania, tipo, password );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( rfc8 ).build( );
	}
	
	@GET
	@Path( "informacion" )
	public Response infoTodasCompanias( @HeaderParam( "id" ) Long id, @HeaderParam( "tipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		List<RFC8> rfc8;
		try
		{
			rfc8 = tm.informacionTodasCompanias( id, tipo, password );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( rfc8 ).build( );
	}
	
	@GET
	@Path( "{id_compania}/rfc9" )
	public Response rfc9( @PathParam( "id_compania" ) Long idCompania, @QueryParam( "inicio" ) Long fInicio, @QueryParam( "fin" ) Long fEnd )
	{
		List<UsuarioRegistrado> list;
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			list = tm.rfc9( idCompania, SQLUtils.DateUtils.milisToDate( fInicio ), SQLUtils.DateUtils.milisToDate( fEnd ) );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id_compania}/rfc10" )
	public Response rfc10( @PathParam( "id_compania" ) Long idCompania, @QueryParam( "inicio" ) Long fInicio, @QueryParam( "fin" ) Long fEnd )
	{
		List<UsuarioRegistrado> list;
		
		CompaniaDeTeatroCM tm = new CompaniaDeTeatroCM( getPath( ) );
		try
		{
			list = tm.rfc10( idCompania, SQLUtils.DateUtils.milisToDate( fInicio ), SQLUtils.DateUtils.milisToDate( fEnd ) );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
}