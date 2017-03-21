package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import vos.Localidad;

public class DAOLocalidad extends DAO{
	public DAOLocalidad( )
	{
		super( );
	}
	
	public Localidad createLocalidad( Localidad localidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO LOCALIDADES " );
		sql.append( "( id, nombre ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", localidad.getId( ) ) );
		sql.append( String.format( "'%s' ", localidad.getNombre( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return localidad;
	}
	
	public List<Localidad> getLocalidades( ) throws SQLException
	{
		List<Localidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM LOCALIDADES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToLocalidad( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Localidad getLocalidad( Long id ) throws SQLException
	{
		Localidad localidad = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LOCALIDADES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			localidad = resultToLocalidad( rs );
		}
		s.close( );
		return localidad;
	}
	
	public Localidad updateLocalidad( Localidad localidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE LOCALIDADES " );
		sql.append( String.format( "SET nombre = '%s' ", localidad.getNombre( ) ) );
		sql.append( String.format( "WHERE id = %s", localidad.getId( ) ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return localidad;
	}
	
	public void deleteLocalidad( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LOCALIDADES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	private Localidad resultToLocalidad( ResultSet rs ) throws SQLException
	{
		Localidad l = new Localidad( );
		l.setId( rs.getLong( "ID" ) );
		l.setNombre( rs.getString( "NOMBRE" ) );
		return l;
	}
}
