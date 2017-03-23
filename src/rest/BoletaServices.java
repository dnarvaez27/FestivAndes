package rest;

import tm.BoletaTM;
import vos.Boleta;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path( "boletas" )
public class BoletaServices
{
	@Context
	private ServletContext context;
	
	@POST
	public Response createBoleta( Long id, String password, Boleta accesibilidad )
	{
		BoletaTM tm = new BoletaTM( getPath( ) );
		try
		{
			accesibilidad = tm.createBoleta( id, password, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( accesibilidad ).build( );
	}
	
	@GET
	public Response getBoletas( )
	{
		List<Boleta> list;
		BoletaTM tm = new BoletaTM( getPath( ) );
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
		Boleta ac;
		BoletaTM tm = new BoletaTM( getPath( ) );
		try
		{
			ac = tm.getBoleta( id );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@PUT
	@Path( "{id}" )
	public Response updateBoleta( @PathParam( "id" ) Long numBoleta, Boleta accesibilidad )
	{
		Boleta ac;
		BoletaTM tm = new BoletaTM( getPath( ) );
		try
		{
			ac = tm.updateBoleta( numBoleta, accesibilidad );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( ac ).build( );
	}
	
	@DELETE
	@Path( "{id}" )
	public Response deleteBoleta( @PathParam( "id" ) Long id )
	{
		BoletaTM tm = new BoletaTM( getPath( ) );
		try
		{
			tm.deleteBoleta( id );
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