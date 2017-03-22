package rest;

import tm.LugarTM;
import tm.intermediate.LugarRequerimientoTM;
import tm.intermediate.PreferenciaLugarTM;
import vos.Lugar;
import vos.RequerimientoTecnico;
import vos.UsuarioRegistrado;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class LugarServices extends Services
{
	@POST
	public Response createLugar( Long id, String password, Lugar lugar )
	{
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			lugar = tm.createLugar( id, password, lugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( lugar ).build( );
	}
	
	@GET
	public Response getLugares( )
	{
		List<Lugar> list;
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			list = tm.getLugares( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id}" )
	public Response getLugar( @PathParam( "id" ) Long id )
	{
		Lugar l;
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			l = tm.getLugar( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateLugar( @PathParam( "id" ) Long id, Lugar lugar )
	{
		Lugar l;
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			l = tm.updateLugar( id, lugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( l ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteLugar( @PathParam( "id" ) Long id )
	{
		LugarTM tm = new LugarTM( getPath( ) );
		try
		{
			tm.deleteLugar( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@GET
	@Path( "{id_lugar}/usuariosprefieren" )
	public Response getUsuariosWhoPrefers( @PathParam( "id_lugar" ) Long idLugar )
	{
		List<UsuarioRegistrado> list;
		PreferenciaLugarTM tm = new PreferenciaLugarTM( getPath( ) );
		try
		{
			list = tm.getUsersWhoPrefer( idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{id_lugar}/requerimientos" )
	public Response getRequerimientosFromLugar( @PathParam( "id_lugar" ) Long idLugar )
	{
		List<RequerimientoTecnico> list;
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			list = tm.getRequerimientosFromLugar( idLugar );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@POST
	@Path( "{id_lugar}/requerimientos/{id_req}" )
	public Response createRequerimientoFromLugar(
			@PathParam( "id_lugar" ) Long idLugar, @PathParam( "id_req" ) Long idRequerimiento )
	{
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			tm.createLugarRequerimiento( idLugar, idRequerimiento );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
	
	@DELETE
	@Path( "{id_lugar}/requerimientos/{id_req}" )
	public Response deleteRequerimientoFromLugar(
			@PathParam( "id_lugar" ) Long idLugar, @PathParam( "id_req" ) Long idRequerimiento )
	{
		LugarRequerimientoTM tm = new LugarRequerimientoTM( getPath( ) );
		try
		{
			tm.deleteLugarRequerimiento( idLugar, idRequerimiento );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}