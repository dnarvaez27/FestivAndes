package dao;

import vos.Genero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOGenero extends DAO
{
	public DAOGenero( )
	{
		super( );
	}
	
	public Genero createGenero( Genero genero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO GENEROS " );
		sql.append( "( id, nombre ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", genero.getId( ) ) );
		sql.append( String.format( "'%s' ", genero.getNombre( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return genero;
	}
	
	public List<Genero> getGeneros( ) throws SQLException
	{
		List<Genero> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM GENEROS" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToGenero( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public Genero getGenero( Long id ) throws SQLException
	{
		Genero genero = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM GENEROS " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			genero = resultToGenero( rs );
		}
		rs.close( );
		s.close( );
		return genero;
	}
	
	public Genero updateGenero( Long id, Genero genero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE GENEROS " );
		sql.append( String.format( "SET nombre = '%s' ", genero.getNombre( ) ) );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return genero;
	}
	
	public void deleteGenero( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM GENEROS " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Genero resultToGenero( ResultSet rs ) throws SQLException
	{
		Genero g = new Genero( );
		g.setId( rs.getLong( "id" ) );
		g.setNombre( rs.getString( "nombre" ) );
		return g;
	}
}