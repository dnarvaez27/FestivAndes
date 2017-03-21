package dao;

import vos.Festival;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOFestival extends DAO
{
	public DAOFestival( )
	{
		super( );
	}
	
	public Festival createFestival( Festival object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO FESTIVALES " );
		sql.append( "( id, nombre ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getId( ) ) );
		sql.append( String.format( "%s, ", object.getFechaInicio( ) ) );
		sql.append( String.format( "%s, ", object.getFechaFin( ) ) );
		sql.append( String.format( "%s ", object.getCiudad( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public List<Festival> getFestivales( ) throws SQLException
	{
		List<Festival> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM FESTIVALES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToFestival( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Festival getFestival( Long id ) throws SQLException
	{
		Festival object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM FESTIVALES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToFestival( rs );
		}
		s.close( );
		return object;
	}
	
	public Festival updateFestival( Long id, Festival object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE FESTIVALES " );
		sql.append( "SET " );
		sql.append( String.format( "fecha_inicio = '%s', ", object.getFechaInicio( ) ) );
		sql.append( String.format( "fecha_fin = %s, ", object.getFechaFin( ) ) );
		sql.append( String.format( "ciudad = %s ", object.getCiudad( ) ) );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteFestival( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM FESTIVALES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	private Festival resultToFestival( ResultSet rs ) throws SQLException
	{
		Festival object = new Festival( );
		object.setFechaInicio( rs.getDate( "fecha_inicio" ) );
		object.setFechaFin( rs.getDate( "fecha_fin" ) );
		object.setCiudad( rs.getString( "ciudad" ) );
		object.setId( rs.getLong( "id" ) );
		return object;
	}
}