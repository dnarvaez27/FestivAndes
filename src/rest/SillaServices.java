package rest;

import org.codehaus.jackson.annotate.JsonIgnore;
import tm.SillaTM;
import vos.Silla;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "sillas" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class SillaServices extends Services
{
	@JsonIgnore
	private Long idLugar;
	
	public SillaServices( )
	{
	}
	
	public SillaServices( ServletContext context, Long idLugar )
	{
		super( context );
		this.idLugar = idLugar;
	}
	
	@POST
	public Response createSilla( Silla silla, @PathParam( "idLocalidad" ) Long idLocalidad )
	{
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			silla.setIdLocalidad( idLocalidad );
			silla.setIdLugar( idLugar );
			silla = tm.createSilla( silla );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( silla ).build( );
	}
	
	@GET
	public Response getSillasFrom( @PathParam( "idLocalidad" ) Long idLocalidad )
	{
		List<Silla> list;
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			list = tm.getSillasFrom( idLugar, idLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "all" )
	public Response getSillas( )
	{
		List<Silla> list;
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			list = tm.getSillas( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{numero_fila}/{numero_silla}" )
	public Response getSilla(
			@PathParam( "idLocalidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numero_fila,
			@PathParam( "numero_silla" ) Integer numero_silla )
	{
		Silla pSilla;
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			pSilla = tm.getSilla( numero_silla, numero_fila, idLugar, id_localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( pSilla ).build( );
	}
	
	@PUT
	@Path( "{numero_fila}/{numero_silla}" )
	public Response updateSilla(
			@PathParam( "idLocalidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numero_fila,
			@PathParam( "numero_silla" ) Integer numero_silla, Silla pSilla )
	{
		Silla l;
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			pSilla.setIdLugar( idLugar );
			pSilla.setIdLocalidad( id_localidad );
			l = tm.updateSilla( numero_silla, numero_fila, idLugar, id_localidad, pSilla );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{numero_fila}/{numero_silla}" )
	public Response deleteSilla(
			@PathParam( "idLocalidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numero_fila,
			@PathParam( "numero_silla" ) Integer numero_silla )
	{
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			tm.deleteSilla( numero_silla, numero_fila, idLugar, id_localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}