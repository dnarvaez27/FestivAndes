package rest;

import tm.SillaTM;
import vos.Silla;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "sillas" )
public class SillaServices
{
	@Context
	private ServletContext context;
	
	@POST
	public Response createSilla( Silla silla )
	{
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			silla = tm.createSilla( silla );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( silla ).build( );
	}
	
	@GET
	public Response getSilla( )
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
	@Path( "{id_lugar}/{id_localidad}/{numero_fila}/{numero_silla}" )
	public Response getSilla(
			@PathParam( "id_lugar" ) Long id_lugar,
			@PathParam( "id_localidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numero_fila,
			@PathParam( "numero_silla" ) Integer numero_silla )
	{
		Silla pSilla;
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			pSilla = tm.getSilla( numero_silla, numero_fila, id_lugar, id_localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( pSilla ).build( );
	}
	
	@PUT
	@Path( "{id_lugar}/{id_localidad}/{numero_fila}/{numero_silla}" )
	public Response updateSilla(
			@PathParam( "id_lugar" ) Long id_lugar,
			@PathParam( "id_localidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numero_fila,
			@PathParam( "numero_silla" ) Integer numero_silla, Silla pSilla )
	{
		Silla l;
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			l = tm.updateSilla( numero_silla, numero_fila, id_lugar, id_localidad, pSilla );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{id_lugar}/{id_localidad}/{numero_fila}/{numero_silla}" )
	public Response deleteSilla(
			@PathParam( "id_lugar" ) Long id_lugar,
			@PathParam( "id_localidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numero_fila,
			@PathParam( "numero_silla" ) Integer numero_silla )
	{
		SillaTM tm = new SillaTM( getPath( ) );
		try
		{
			tm.deleteSilla( numero_silla, numero_fila, id_lugar, id_localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
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