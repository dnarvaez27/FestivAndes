package dao;

import vos.Localidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOLocalidad extends DAO
{
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
			list.add( resultToBasicLocalidad( rs ) );
		}
		rs.close( );
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
			localidad = resultToBasicLocalidad( rs );
		}
		rs.close( );
		s.close( );
		return localidad;
	}
	
	public Localidad updateLocalidad( Long id, Localidad localidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE LOCALIDADES " );
		sql.append( String.format( "SET nombre = '%s' ", localidad.getNombre( ) ) );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
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
	
	public Localidad searchLocalidad( String name ) throws SQLException
	{
		Localidad localidad = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "  FROM LOCALIDADES " );
		sql.append( String.format( "WHERE NOMBRE = '%s' ", name ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			localidad = resultToBasicLocalidad( rs );
		}
		rs.close( );
		s.close( );
		return localidad;
	}
	
	private static Localidad resultToBasicLocalidad( ResultSet rs ) throws SQLException
	{
		Localidad l = new Localidad( );
		l.setId( rs.getLong( "id" ) );
		l.setNombre( rs.getString( "nombre" ) );
		return l;
	}
	
	public static Localidad resultToLocalidad( ResultSet rs, int datos ) throws SQLException
	{
		Localidad l = resultToBasicLocalidad( rs );
		
		switch( datos )
		{
			case 3:
				l.setCosto( rs.getFloat( "costo" ) );
			case 1:
				l.setCapacidad( rs.getInt( "capacidad" ) );
				l.setEsNumerado( rs.getInt( "es_numerado" ) );
				break;
			case 2:
				l.setCosto( rs.getFloat( "costo" ) );
				break;
		}
		return l;
	}
}