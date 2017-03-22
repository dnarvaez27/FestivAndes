package dao;

import vos.Funcion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOFuncion extends DAO
{
	public DAOFuncion( )
	{
		super( );
	}
	
	public Funcion createFuncion( Funcion funcion ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO FUNCIONES " );
		sql.append( "( fecha, id_lugar, id_espectaculo ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", funcion.getFecha( ) ) );
		sql.append( String.format( "%s, ", funcion.getIdLugar( ) ) );
		sql.append( String.format( "%s, ", funcion.getIdEspectaculo( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return funcion;
	}
	
	public List<Funcion> getFunciones( ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM FUNCIONES" );
		
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
	
	public Funcion getFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		Funcion funcion = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM FUNCIONES " );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s ", fecha ) );
		sql.append( "AND" );
		sql.append( String.format( "id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			funcion = restultToAccesibildiad( rs );
		}
		s.close( );
		return funcion;
	}
	
	public Funcion updateFuncion( Date fecha, Long idLugar, Funcion funcion ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE FUNCIONES " );
		sql.append( "SET " );
		sql.append( String.format( "fecha = '%s'", funcion.getFecha( ) ) );
		sql.append( String.format( "id_lugar = '%s'", funcion.getIdEspectaculo( ) ) );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s ", fecha ) );
		sql.append( "AND" );
		sql.append( String.format( "id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return funcion;
	}
	
	public void deleteFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM FUNCIONES " );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s ", fecha ) );
		sql.append( "AND" );
		sql.append( String.format( "id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Funcion restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		Funcion funcion = new Funcion( );
		funcion.setFecha( rs.getDate( "fecha" ) );
		funcion.setIdLugar( rs.getLong( "id_lugar" ) );
		funcion.setIdEspectaculo( rs.getLong( "id_espectaculo" ) );
		return funcion;
	}
}