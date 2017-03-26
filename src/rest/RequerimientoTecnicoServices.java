package rest;

import tm.RequerimientoTecnicoTM;
import tm.intermediate.LugarRequerimientoTM;
import vos.Lugar;
import vos.RequerimientoTecnico;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "requerimientos" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class RequerimientoTecnicoServices extends Services
{
	public RequerimientoTecnicoServices( )
	{
	}
	
	public RequerimientoTecnicoServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createRequerimientoTecnico( RequerimientoTecnico requerimientoTecnico )
	{
		RequerimientoTecnicoTM tm = new RequerimientoTecnicoTM( getPath( ) );
		try
		{
			requerimientoTecnico = tm.createRequerimientoTecnico( requerimientoTecnico );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( requerimientoTecnico ).build( );
	}
	
	@GET
	public Response getRequerimientoTecnico( )
	{
		List<RequerimientoTecnico> list;
		RequerimientoTecnicoTM tm = new RequerimientoTecnicoTM( getPath( ) );
		try
		{
			list = tm.getRequerimientosTecnicos( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getRequerimientoTecnico( @PathParam( "id" ) Long id )
	{
		RequerimientoTecnico req;
		RequerimientoTecnicoTM tm = new RequerimientoTecnicoTM( getPath( ) );
		try
		{
			req = tm.getRequerimientoTecnico( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( req ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateRequerimientoTecnico(
			@PathParam( "id" ) Long id, RequerimientoTecnico req )
	{
		RequerimientoTecnico l;
		RequerimientoTecnicoTM tm = new RequerimientoTecnicoTM( getPath( ) );
		try
		{
			l = tm.updateRequerimientoTecnico( id, req );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteRequerimientoTecnico( @PathParam( "id" ) Long id )
	{
		RequerimientoTecnicoTM tm = new RequerimientoTecnicoTM( getPath( ) );
		try
		{
			tm.deleteRequerimientoTecnico( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_requerimiento}/lugares" )
	public Response getLugaresWithRequerimiento(
			@PathParam( "id_requerimiento" ) Long idRequerimiento )
	{
		List<Lugar> list;
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			list = tm.getLugaresWithRequerimiento( idRequerimiento );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
}