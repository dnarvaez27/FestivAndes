package dao;

import vos.Clasificacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOClasificacion extends DAO
{
	public DAOClasificacion( )
	{
		super( );
	}
	
	public Clasificacion createClasificacion( Clasificacion object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO CLASIFICACIONES " );
		sql.append( "( id, nombre ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getId( ) ) );
		sql.append( String.format( "'%s' ", object.getNombre( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public List<Clasificacion> getClasificaciones( ) throws SQLException
	{
		List<Clasificacion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM CLASIFICACIONES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( restultToAccesibildiad( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Clasificacion getClasificacion( Long id ) throws SQLException
	{
		Clasificacion object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM CLASIFICACIONES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = restultToAccesibildiad( rs );
		}
		s.close( );
		return object;
	}
	
	public Clasificacion updateClasificacion( Long id, Clasificacion object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE CLASIFICACIONES " );
		sql.append( String.format( "SET nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteClasificacion( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM CLASIFICACIONES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Clasificacion restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		Clasificacion clasificacion = new Clasificacion( );
		clasificacion.setId( rs.getLong( "id" ) );
		clasificacion.setNombre( rs.getString( "nombre" ) );
		return clasificacion;
	}
}