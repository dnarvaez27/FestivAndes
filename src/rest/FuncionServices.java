package rest;

import tm.FuncionTM;
import vos.Funcion;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Path( "funciones" )
public class FuncionServices extends Services
{
	@POST
	public Response createFuncion( Funcion accesibilidad )
	{
		FuncionTM tm = new FuncionTM( getPath( ) );
		try
		{
			accesibilidad = tm.createFuncion( accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@GET
	public Response getFuncions( )
	{
		List<Funcion> list;
		FuncionTM tm = new FuncionTM( getPath( ) );
		try
		{
			list = tm.getFunciones( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{lugar}/{fecha}" )
	public Response getFuncion(
			@PathParam( "lugar" ) Long idFecha, @PathParam( "fecha" ) Date fecha )
	{
		Funcion ac;
		FuncionTM tm = new FuncionTM( getPath( ) );
		try
		{
			ac = tm.getFuncion( fecha, idFecha );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{lugar}/{fecha}" )
	public Response updateFuncion(
			@PathParam( "lugar" ) Long idFecha,
			@PathParam( "fecha" ) Date fecha, Funcion accesibilidad )
	{
		Funcion ac;
		FuncionTM tm = new FuncionTM( getPath( ) );
		try
		{
			ac = tm.updateFuncion( fecha, idFecha, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@DELETE
	@Path( "{lugar}/{fecha}" )
	public Response deleteFuncion(
			@PathParam( "lugar" ) Long idFecha, @PathParam( "fecha" ) Date fecha )
	{
		FuncionTM tm = new FuncionTM( getPath( ) );
		
		try
		{
			tm.deleteFuncion( fecha, idFecha );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}