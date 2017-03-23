package rest;

import tm.LocalidadTM;
import tm.intermediate.CostoLocalidadTM;
import vos.Localidad;
import vos.intermediate.CostoLocalidad;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "localidades" )
public class LocalidadServices
{
	@Context
	private ServletContext context;
	
	@POST
	public Response createLocalidad( Localidad localidad )
	{
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			localidad = tm.createLocalidad( localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( localidad ).build( );
	}
	
	@GET
	public Response getLocalidades( )
	{
		List<Localidad> list;
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			list = tm.getLocalidades( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getLocalidad( @PathParam( "id" ) Long id )
	{
		Localidad l;
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			l = tm.getLocalidad( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateLocalidad( @PathParam( "id" ) Long id, Localidad localidad )
	{
		Localidad l;
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			l = tm.updateLocalidad( id, localidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteLocalidad( @PathParam( "id" ) Long id )
	{
		LocalidadTM tm = new LocalidadTM( getPath( ) );
		try
		{
			tm.deleteLocalidad( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_localidad}/funciones" )
	public Response getFuncionesInLocalidad( @PathParam( "id_localidad" ) Long idLocalidad )
	{
		List<CostoLocalidad> list;
		CostoLocalidadTM tm = new CostoLocalidadTM( getPath( ) );
		try
		{
			list = tm.getCostoLocalidadFromLocalidad( idLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
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