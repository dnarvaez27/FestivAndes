package dao.intermediate;

import dao.DAO;
import dao.DAOLugar;
import dao.DAORequerimientoTecnico;
import vos.Lugar;
import vos.RequerimientoTecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dave on 21/03/2017.
 */
public class DAOLugarRequerimiento extends DAO
{
	public void createEntryLugarRequerimiento( Long idLugar, Long idRequerimiento ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO LUGAR_REQUERIMIENTO " );
		sql.append( "( id_lugar, id_requerimiento )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idLugar ) );
		sql.append( String.format( "%s ", idRequerimiento ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<RequerimientoTecnico> getRequermientosFromLugar( Long idLugar ) throws SQLException
	{
		List<RequerimientoTecnico> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_REQUERIMIENTO LR INNER JOIN REQUERIMIENTOS_TECNICOS R" );
		sql.append( "                             ON LR.id_requerimiento = R.id " );
		sql.append( String.format( "WHERE LR.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAORequerimientoTecnico.resultToRequerimientoTecnico( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Lugar> getLugaresWithRequerimiento( Long idRequerimiento ) throws SQLException
	{
		List<Lugar> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_REQUERIMIENTO LR INNER JOIN LUGARES L " );
		sql.append( "                             ON LR.id_lugar = L.id " );
		sql.append( String.format( "WHERE LR.id_requerimiento = %s ", idRequerimiento ) );
		
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
	
	public RequerimientoTecnico getRequerimientoFromLugar( Long idLugar, Long idRequerimiento ) throws SQLException
	{
		RequerimientoTecnico req = null;
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_REQUERIMIENTO LR INNER JOIN REQUERIMIENTOS_TECNICOS R" );
		sql.append( "                             ON LR.id_requerimiento = R.id " );
		sql.append( String.format( "WHERE LR.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND LR.id_requerimiento = %s", idRequerimiento ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			req = DAORequerimientoTecnico.resultToRequerimientoTecnico( rs );
		}
		rs.close( );
		s.close( );
		return req;
	}
	
	public Lugar getLugarWithRequerimiento( Long idLugar, Long idRequerimiento ) throws SQLException
	{
		Lugar lug = null;
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGAR_REQUERIMIENTO LR INNER JOIN LUGARES L " );
		sql.append( "                             ON LR.id_lugar = L.id " );
		sql.append( String.format( "WHERE LR.id_requerimiento = %s ", idRequerimiento ) );
		sql.append( String.format( "AND LR.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			lug = DAOLugar.restultToAccesibildiad( rs );
		}
		return lug;
	}
	
	public void deleteEntryLugarRequerimiento( Long idLugar, Long idRequerimiento ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LUGAR_REQUERIMIENTO " );
		sql.append( String.format( "WHERE id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND id_requerimiento = %s", idRequerimiento ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}