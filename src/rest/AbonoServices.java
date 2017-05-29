package rest;

import exceptions.IncompleteReplyException;
import protocolos.ProtocoloAbono;
import tm.AbonoCM;
import vos.Abono;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dave on 9/04/2017.
 */
@Path( "/abonos" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class AbonoServices extends Services
{
	public AbonoServices( )
	{
	
	}
	
	public AbonoServices( ServletContext context )
	{
		super( context );
	}
	
	@POST
	public Response createAbono( Abono abono )
	{
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			abono = tm.createAbono( abono );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( abono ).build( );
	}
	
	@POST
	@Path( "/remote/" )
	public Response createAbonoRemote( ProtocoloAbono abono )
	{
		List<ProtocoloAbono> list;
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			list = tm.createAbonoRemote( abono );
		}
		catch( IncompleteReplyException e )
		{
			return Response.status( Response.Status.OK ).entity( e.getPartialResponse( ) ).build( );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "all" )
	public Response getAbonos( )
	{
		List<Abono> list;
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			list = tm.getAbonos( );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	public Response getAbonos( @PathParam( "idUsuario" ) Long idUsuario, @PathParam( "idTipo" ) String tipoId )
	{
		List<Abono> list;
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			list = tm.getAbonosFrom( idUsuario, tipoId );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( list ).build( );
	}
	
	@GET
	@Path( "{idFestival}" )
	public Response getAbono(
			@PathParam( "idFestival" ) Long idFestival, @PathParam( "idUsuario" ) Long idUsuario, @PathParam( "idTipo" ) String tipoId )
	{
		Abono abono;
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			abono = tm.getAbono( idFestival, idUsuario, tipoId );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( abono ).build( );
	}
	
	@PUT
	@Path( "{idFestival}" )
	public Response updateAbono( Abono abono,
	                             @PathParam( "idFestival" ) Long idFestival,
	                             @PathParam( "idUsuario" ) Long idUsuario, @PathParam( "idTipo" ) String tipoId )
	{
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			abono = tm.updateAbono( idFestival, idUsuario, tipoId, abono );
		}
		catch( SQLException e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( abono ).build( );
	}
	
	@DELETE
	@Path( "{idFestival}" )
	public Response deleteAbono(
			@PathParam( "idFestival" ) Long idFestival, @PathParam( "idUsuario" ) Long idUsuario, @PathParam( "idTipo" ) String tipoId )
	{
		AbonoCM tm = new AbonoCM( getPath( ) );
		try
		{
			tm.deleteAbono( idFestival, idUsuario, tipoId );
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).build( );
	}
}