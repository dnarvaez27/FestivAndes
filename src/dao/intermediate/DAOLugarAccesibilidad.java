package dao.intermediate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dao.DAO;
import dao.DAOLugar;
import dao.DAOAccesibilidad;
import vos.Lugar;
import vos.Accesibilidad;

public class DAOLugarAccesibilidad extends DAO{
	public void createEntryLugarAccesibilidad( Long idLugar, Long idAccesibilidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO LUGAR_ACCESIBILIDAD " );
		sql.append( "( id_lugar, id_accesibilidad )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idLugar ) );
		sql.append( String.format( "%s ", idAccesibilidad ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<Accesibilidad> getAccesibilidadesFromLugar( Long idLugar ) throws SQLException
	{
		List<Accesibilidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_ACCESIBILIDAD LR INNER JOIN ACCESIBILIDADES R" );
		sql.append( "                             ON LR.id_accesibilidad = R.id " );
		sql.append( String.format( "WHERE LR.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOAccesibilidad.resultToAccesibilidad( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Lugar> getLugaresWithAccesibilidad( Long idAccesibilidad ) throws SQLException
	{
		List<Lugar> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_ACCESIBILIDAD LR INNER JOIN LUGARES L " );
		sql.append( "                             ON LR.id_lugar = L.id " );
		sql.append( String.format( "WHERE LR.id_accesibilidad = %s ", idAccesibilidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOLugar.restultToAccesibildiad( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public Accesibilidad getAccesibilidadFromLugar( Long idLugar, Long idAccesibilidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_ACCESIBILIDAD LR INNER JOIN ACCESIBILIDADES R" );
		sql.append( "                             ON LR.id_accesibilidad = R.id " );
		sql.append( String.format( "WHERE LR.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND LR.id_accesibilidad = %s", idAccesibilidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			return DAOAccesibilidad.resultToAccesibilidad( rs );
		}
		return null;
	}
	
	public Lugar getLugarWithAccesibilidad( Long idLugar, Long idAccesibilidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_ACCESIBILIDAD LR INNER JOIN LUGARES L " );
		sql.append( "                             ON LR.id_lugar = L.id " );
		sql.append( String.format( "WHERE LR.id_accesibilidad = %s ", idAccesibilidad ) );
		sql.append( String.format( "AND LR.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			return DAOLugar.restultToAccesibildiad( rs );
		}
		return null;
	}
	
	public void deleteEntryLugarAccesibilidad( Long idLugar, Long idAccesibilidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LUGAR_ACCESIBILIDAD " );
		sql.append( String.format( "WHERE id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND id_accesibilidad = %s", idAccesibilidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}
