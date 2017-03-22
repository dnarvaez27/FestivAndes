package dao;

import vos.Lugar;
import vos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOLugar extends DAO
{
	public DAOLugar( )
	{
		super( );
	}
	
	public Lugar createLugar( Long id, String password, Lugar lugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", id ) );
		sql.append( String.format( "AND password = '%s' ", password ) );
		sql.append( String.format( "AND rol = '%s'", Usuario.USUARIO_ADMINISTRADOR ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			sql = new StringBuilder( );
			sql.append( "INSERT INTO LUGARES " );
			sql.append( "( id, nombre, diponibilidad_inicio, disponibilidad_fin, es_abierto, tipo_lugar ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", lugar.getId( ) ) );
			sql.append( String.format( "'%s', ", lugar.getNombre( ) ) );
			sql.append( String.format( "%s, ", toDate( lugar.getDisponibilidadInicio( ) ) ) );
			sql.append( String.format( "%s, ", toDate( lugar.getDisponibilidadFin( ) ) ) );
			sql.append( String.format( "%s, ", lugar.getEsAbierto( ) ) );
			sql.append( String.format( "'%s' ", lugar.getTipo( ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return lugar;
	}
	
	public List<Lugar> getLugares( ) throws SQLException
	{
		List<Lugar> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM LUGARES" );
		
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
	
	public Lugar getLugar( Long id ) throws SQLException
	{
		Lugar lugar = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGARES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			lugar = restultToAccesibildiad( rs );
		}
		s.close( );
		return lugar;
	}
	
	public Lugar updateLugar( Long id, Lugar lugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE LUGARES " );
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s', ", lugar.getNombre( ) ) );
		sql.append( String.format( "disponibilidad_inicio = %s, ", toDate( lugar.getDisponibilidadInicio( ) ) ) );
		sql.append( String.format( "disponibilidad_fin = %s, ", toDate( lugar.getDisponibilidadFin( ) ) ) );
		sql.append( String.format( "es_abierto = %s, ", lugar.getEsAbierto( ) ) );
		sql.append( String.format( "tipo = '%s', ", lugar.getTipo( ) ) );
		sql.append( String.format( "WHERE id = %s ", lugar.getId( ) ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return lugar;
	}
	
	public void deleteLugar( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LUGARES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Lugar restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		Lugar l = new Lugar( );
		l.setId( rs.getLong( "id" ) );
		l.setNombre( rs.getString( "nombre" ) );
		l.setDisponibilidadInicio( rs.getDate( "disponibilidad_inicio" ) );
		l.setDisponibilidadFin( rs.getDate( "disponibilidad_fin" ) );
		l.setEsAbierto( rs.getInt( "es_abierto" ) );
		l.setTipo( rs.getString( "tipo" ) );
		return l;
	}
}