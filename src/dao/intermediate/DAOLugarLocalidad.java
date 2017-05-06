package dao.intermediate;

import dao.DAO;
import dao.DAOLocalidad;
import dao.DAOLugar;
import vos.Localidad;
import vos.Lugar;
import vos.intermediate.LugarLocalidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOLugarLocalidad extends DAO
{
	public void createEntryLugarLocalidad( LugarLocalidad ll ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO LUGAR_LOCALIDAD " );
		sql.append( "( id_lugar, id_localidad, es_numerado, capacidad )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", ll.getIdLugar( ) ) );
		sql.append( String.format( "%s, ", ll.getIdLocalidad( ) ) );
		sql.append( String.format( "%s, ", ll.getEsNumerado( ) ) );
		sql.append( String.format( "%s ", ll.getCapacidad( ) ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<Localidad> getLocalidadesFromLugar( Long idLugar ) throws SQLException
	{
		List<Localidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_LOCALIDAD LL INNER JOIN LOCALIDADES L" );
		sql.append( "                             ON LL.id_localidad = L.id " );
		sql.append( String.format( "WHERE LL.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOLocalidad.resultToLocalidad( rs, 1 ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Lugar> getLugaresWithLocalidad( Long idLocalidad ) throws SQLException
	{
		List<Lugar> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_LOCALIDAD LL INNER JOIN LUGARES L " );
		sql.append( "                             ON LL.id_lugar = L.id " );
		sql.append( String.format( "WHERE LL.id_localidad = %s ", idLocalidad ) );
		
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
	
	public Localidad getLocalidadFromLugar( Long idLugar, Long idLocalidad ) throws SQLException
	{
		Localidad loc = null;
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_LOCALIDAD LL INNER JOIN LOCALIDADES L" );
		sql.append( "                             ON LL.id_localidad = L.id " );
		sql.append( String.format( "WHERE LL.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND LL.id_localidad = %s", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			loc = DAOLocalidad.resultToLocalidad( rs, 1 );
		}
		rs.close( );
		s.close( );
		return loc;
	}
	
	public Lugar getLugarWithLocalidad( Long idLugar, Long idLocalidad ) throws SQLException
	{
		Lugar lug = null;
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_LOCALIDAD LL INNER JOIN LUGARES L " );
		sql.append( "                             ON LL.id_lugar = L.id " );
		sql.append( String.format( "WHERE LL.id_localidad = %s ", idLocalidad ) );
		sql.append( String.format( "AND LL.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			lug = DAOLugar.restultToAccesibildiad( rs );
		}
		rs.close( );
		s.close( );
		return lug;
	}
	
	public LugarLocalidad updateLugarLocalidad( Long idLugar, Long idLocalidad, LugarLocalidad lugarLocalidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE LUGAR_LOCALIDAD " );
		sql.append( "SET " );
		sql.append( String.format( "es_numerado = %s, ", lugarLocalidad.getEsNumerado( ) ) );
		sql.append( String.format( "capacidad = %s ", lugarLocalidad.getCapacidad( ) ) );
		sql.append( String.format( "WHERE id_lugar = %s ", idLugar ) );
		sql.append( String.format( "  AND id_localidad = %s ", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		
		lugarLocalidad.setIdLocalidad( idLocalidad );
		lugarLocalidad.setIdLugar( idLugar );
		
		s.close( );
		return lugarLocalidad;
	}
	
	public void deleteEntryLugarLocalidad( Long idLugar, Long idLocalidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LUGAR_LOCALIDAD " );
		sql.append( String.format( "WHERE id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND id_localidad = %s", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}