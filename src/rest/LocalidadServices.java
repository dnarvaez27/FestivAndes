package rest;

import tm.LocalidadCM;
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
		LocalidadCM tm = new LocalidadCM( getPath( ) );
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
	public Response assignLocalidadToLugar( LugarLocalidad lugarLocalidad, @PathParam( "idLugar" ) Long idLugar )
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
		LocalidadCM tm = new LocalidadCM( getPath( ) );
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
	public Response getLocalidadFromPlace( @PathParam( "idLugar" ) Long idLugar, @PathParam( "id" ) Long id )
	{
		Localidad localidad;
		LugarLocalidadTM tm = new LugarLocalidadTM( getPath( ) );
		try
		{
			localidad = tm.getLocalidad( idLugar, id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( localidad ).build( );
	}
	
	@GET
	@Path( "all/{id}" )
	public Response getLocalidad( @PathParam( "id" ) Long id )
	{
		Localidad l;
		LocalidadCM tm = new LocalidadCM( getPath( ) );
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
		LocalidadCM tm = new LocalidadCM( getPath( ) );
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
	                                      @PathParam( "idLugar" ) Long idLugar, @PathParam( "idLocalidad" ) Long idLocalidad )
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
	public Response deleteLocalidadFromLugar( @PathParam( "idLugar" ) Long idLugar, @PathParam( "idLocalidad" ) Long idLocalidad )
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
		LocalidadCM tm = new LocalidadCM( getPath( ) );
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
	public SillaServices getSillas( @PathParam( "idLugar" ) Long idLugar, @PathParam( "idLocalidad" ) Long idLocalidad )
	{
		return new SillaServices( context, idLugar );
	}
}