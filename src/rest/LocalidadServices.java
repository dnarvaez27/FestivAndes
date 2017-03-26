package rest;

import tm.LocalidadTM;
import tm.intermediate.CostoLocalidadTM;
import tm.intermediate.LugarLocalidadTM;
import vos.Funcion;
import vos.Localidad;
import vos.Lugar;
import vos.intermediate.LugarLocalidad;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "localidades" )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class LocalidadServices extends Services
{
	public LocalidadServices( )
	{
	}
	
	public LocalidadServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	@Path( "all" )
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
	
	@POST
	public Response assignLocalidadToLugar( LugarLocalidad lugarLocalidad,
	                                        @PathParam( "idLugar" ) Long idLugar )
	{
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			lugarLocalidad.setIdLugar( idLugar );
			tm.createLugarLocalidad( lugarLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugarLocalidad ).build( );
	}
	
	@GET
	public Response getLocalidadesFrom( @PathParam( "idLugar" ) Long idLugar )
	{
		List<Localidad> list;
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			list = tm.getLocalidadsFromLugar( idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "all" )
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
	public Response getLocalidad( @PathParam( "idLugar" ) Long idLugar, @PathParam( "id" ) Long id )
	{
		Localidad l;
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			l = tm.getLocalidad( idLugar, id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@GET
	@Path( "all/{id}" )
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
	@Path( "all/{id}" )
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
	
	@PUT
	@Path( "{idLocalidad}" )
	public Response updateLugarLocalidad( LugarLocalidad lugarLocalidad,
	                                      @PathParam( "idLugar" ) Long idLugar,
	                                      @PathParam( "idLocalidad" ) Long idLocalidad )
	{
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			lugarLocalidad = tm.updateLugarLocalidad( idLugar, idLocalidad, lugarLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugarLocalidad ).build( );
	}
	
	@DELETE
	@Path( "{idLocalidad}" )
	public Response deleteLocalidadFromLugar(
			@PathParam( "idLugar" ) Long idLugar, @PathParam( "idLocalidad" ) Long idLocalidad )
	{
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			tm.deleteLugarLocalidad( idLugar, idLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "all/{id}" )
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
	
	// LUGARES
	@GET
	@Path( "{id_localidad}/lugares" )
	public Response getLugaresWithLocalidad( @PathParam( "id_localidad" ) Long idLocalidad )
	{
		List<Lugar> list;
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			list = tm.getLugaresWithLocalidad( idLocalidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	// FUNCIONES
	@GET
	@Path( "{id_localidad}/funciones" )
	public Response getFuncionesInLocalidad( @PathParam( "id_localidad" ) Long idLocalidad )
	{
		List<Funcion> list;
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
	
	//SILLAS
	@Path( "{idLocalidad}/sillas" )
	public SillaServices getSillas( @PathParam( "idLugar" ) Long idLugar )
	{
		return new SillaServices( context, idLugar );
	}
}