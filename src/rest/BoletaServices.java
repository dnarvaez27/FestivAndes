package rest;

import tm.BoletaCM;
import utilities.SQLUtils;
import vos.Boleta;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "boletas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class BoletaServices extends Services
{
	public BoletaServices( )
	{
	
	}
	
	public BoletaServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createBoleta( Boleta boleta,
	                              @PathParam( "idUsuario" ) Long idUsuario,
	                              @PathParam( "idTipo" ) String tipo, @HeaderParam( "password" ) String password )
	{
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			boleta.setTipoIdUsuario( tipo );
			boleta.setIdUsuario( idUsuario );
			boleta = tm.createBoleta( idUsuario, tipo, password, boleta );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( boleta ).build( );
	}
	
	@POST
	@Path( "multi" )
	public Response createBoletas(
			@PathParam( "idUsuario" ) Long idUsuario,
			@PathParam( "idTipo" ) String tipo,
			@HeaderParam( "password" ) String password,
			@QueryParam( "cant" ) Integer cantBoletas,
			@QueryParam( "fecha" ) Long fechaMilis, @QueryParam( "idLugar" ) Long idLugar, @QueryParam( "idLocalidad" ) Long idLocalidad )
	{
		List<Boleta> list;
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			// TODO: 8/04/2017 Fecha milis...
			list = tm.createBoletas( idUsuario, tipo, SQLUtils.DateUtils.milisToDate( fechaMilis ), idLugar, idLocalidad, cantBoletas );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	public Response getBoletasFrom( @PathParam( "idUsuario" ) Long idUsuario, @PathParam( "idTipo" ) String tipo )
	{
		List<Boleta> list;
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			list = tm.getBoletasFrom( idUsuario, tipo );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "all" )
	public Response getBoletas( )
	{
		List<Boleta> list;
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			list = tm.getBoletas( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getBoleta( @PathParam( "id" ) Long id )
	{
		Boleta boleta;
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			boleta = tm.getBoleta( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( boleta ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateBoleta( @PathParam( "id" ) Long numBoleta, Boleta boleta )
	{
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			boleta = tm.updateBoleta( numBoleta, boleta );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( boleta ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteBoleta( @PathParam( "id" ) Long id )
	{
		BoletaCM tm = new BoletaCM( getPath( ) );
		try
		{
			tm.deleteBoleta( id );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}