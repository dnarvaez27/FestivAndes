package dao;

import vos.Accesibilidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOAccesibilidad extends DAO
{
	public DAOAccesibilidad( )
	{
		super( );
	}
	
	public Accesibilidad createAccesibilidad( Accesibilidad accesibilidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO ACCESIBILIDADES " );
		sql.append( "( id, nombre ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", accesibilidad.getId( ) ) );
		sql.append( String.format( "'%s' ", accesibilidad.getNombre( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return accesibilidad;
	}
	
	public List<Accesibilidad> getAccesibilidades( ) throws SQLException
	{
		List<Accesibilidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM ACCESIBILIDADES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToAccesibilidad( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Accesibilidad getAccesibilidad( Long id ) throws SQLException
	{
		Accesibilidad accesibilidad = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ACCESIBILIDADES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			accesibilidad = resultToAccesibilidad( rs );
		}
		s.close( );
		return accesibilidad;
	}
	
	public Accesibilidad updateAccesibilidad( Long id, Accesibilidad accesibilidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE ACCESIBILIDADES " );
		sql.append( String.format( "SET nombre = '%s' ", accesibilidad.getNombre( ) ) );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return accesibilidad;
	}
	
	public void deleteAccesibilidad( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ACCESIBILIDADES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Accesibilidad resultToAccesibilidad( ResultSet rs ) throws SQLException
	{
		Accesibilidad a = new Accesibilidad( );
		a.setId( rs.getLong( "ID" ) );
		a.setNombre( rs.getString( "NOMBRE" ) );
		return a;
	}
}