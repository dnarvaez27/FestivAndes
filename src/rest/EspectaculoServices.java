package rest;

import tm.EspectaculoTM;
import tm.intermediate.EspectaculoGeneroTM;
import tm.intermediate.OfrecenTM;
import utilities.DateFormatter;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;
import vos.Genero;
import vos.reportes.RFC4;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "espectaculos" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class EspectaculoServices extends Services
{
	public EspectaculoServices( )
	{
		super( );
	}
	
	public EspectaculoServices( ServletContext context )
	{
		super( context );
	}
	
	// Usuario Administrador
	@POST
	public Response createEspectaculo( Espectaculo espectaculo,
	                                   @HeaderParam( "id" ) Long id,
	                                   @HeaderParam( "password" ) String password,
	                                   @PathParam( "idFestival" ) Long idFestival )
	{
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			espectaculo.setIdFestival( idFestival );
			espectaculo = tm.createEspectaculo( id, password, espectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( espectaculo ).build( );
	}
	
	@GET
	public Response getEspectaculos( @PathParam( "idFestival" ) Long idFestival )
	{
		List<Espectaculo> list;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			list = tm.getEspectaculos( idFestival );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "all" )
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
	public Response getEspectaculo(
			@PathParam( "idFestival" ) Long idFestival, @PathParam( "id" ) Long id )
	{
		Espectaculo es;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			es = tm.getEspectaculo( idFestival, id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( es ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateEspectaculo(
			@PathParam( "idFestival" ) Long idFestival,
			@PathParam( "id" ) Long id, Espectaculo espectaculo )
	{
		Espectaculo es;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			es = tm.updateEspectaculo( idFestival, id, espectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( es ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteEspectaculo(
			@PathParam( "idFestival" ) Long idFestival, @PathParam( "id" ) Long id )
	{
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			tm.deleteEspectaculo( idFestival, id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@Path( "{idEspectaculo}/funciones" )
	public FuncionServices getFunciones( )
	{
		return new FuncionServices( context );
	}
	
	// GENEROS
	@GET
	@Path( "{id}/generos" )
	public Response getGenerosFrom( @PathParam( "id" ) Long idEspectaculo )
	{
		List<Genero> list;
		EspectaculoGeneroTM tm = new EspectaculoGeneroTM( getPath( ) );
		try
		{
			list = tm.getGenerosFrom( idEspectaculo );
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
	public Response createEntryGenero(
			@PathParam( "id_espectaculo" ) Long idEspectaculo, Long idGenero )
	{
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
	
	// COMPANIAS
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
	
	// REPORTES
	@GET
	@Path( "{id_espectaculo}/rfc4" )
	public Response generarReporteRFC4(
			@PathParam( "idFestival" ) Long idFestival,
			@PathParam( "id_espectaculo" ) Long idEspectaculo )
	{
		RFC4 req;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			req = tm.generarReporte( idFestival, idEspectaculo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( req ).build( );
	}
	
	@GET
	@Path( "popular" )
	public Response getEspectaculosPopulares(
			@QueryParam( "fecha_inicio" ) String fInicio, @QueryParam( "fecha_fin" ) String fFin )
	{
		List<Espectaculo> list;
		EspectaculoTM tm = new EspectaculoTM( getPath( ) );
		try
		{
			list = tm.getEspectaculosPopulares( DateFormatter.format( fInicio ), DateFormatter.format( fFin ) );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
}