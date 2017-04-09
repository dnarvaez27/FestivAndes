package rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tm.SillaCM;
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
		SillaCM tm = new SillaCM( getPath( ) );
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
		SillaCM tm = new SillaCM( getPath( ) );
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
		SillaCM tm = new SillaCM( getPath( ) );
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
			@PathParam( "idLocalidad" ) Long idLocalidad,
			@PathParam( "numero_fila" ) Integer numeroFila, @PathParam( "numero_silla" ) Integer numeroSilla )
	{
		Silla pSilla;
		SillaCM tm = new SillaCM( getPath( ) );
		try
		{
			pSilla = tm.getSilla( numeroSilla, numeroFila, idLugar, idLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( pSilla ).build( );
	}
	
	@PUT
	@Path( "{numero_fila}/{numero_silla}" )
	public Response updateSilla( Silla silla,
	                             @PathParam( "idLocalidad" ) Long id_localidad,
	                             @PathParam( "numero_fila" ) Integer numeroFila, @PathParam( "numero_silla" ) Integer numeroSilla )
	{
		SillaCM tm = new SillaCM( getPath( ) );
		try
		{
			silla.setIdLugar( idLugar );
			silla.setIdLocalidad( id_localidad );
			silla = tm.updateSilla( numeroSilla, numeroFila, idLugar, id_localidad, silla );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( silla ).build( );
	}
	
	@DELETE
	@Path( "{numero_fila}/{numero_silla}" )
	public Response deleteSilla(
			@PathParam( "idLocalidad" ) Long id_localidad,
			@PathParam( "numero_fila" ) Integer numeroFila, @PathParam( "numero_silla" ) Integer numeroSilla )
	{
		SillaCM tm = new SillaCM( getPath( ) );
		try
		{
			tm.deleteSilla( numeroSilla, numeroFila, idLugar, id_localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}