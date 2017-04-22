package dao.intermediate;

import dao.DAO;
import dao.DAOCompaniaDeTeatro;
import dao.DAOEspectaculo;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dave on 20/03/2017.
 */
public class DAOOfrecen extends DAO
{
	public void createEntryOfrecen( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO OFRECE " );
		sql.append( "( id_compania_de_teatro, tipo_id, id_espectaculo )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idCompania ) );
		sql.append( String.format( "'%s', ", CompaniaDeTeatro.TIPO_ID ) );
		sql.append( String.format( "%s ", idEspectaculo ) );
		sql.append( ")" );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<Espectaculo> getEspectaculosFromCompania( Long idCompania ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM OFRECE O INNER JOIN ESPECTACULOS E " );
		sql.append( "                ON O.id_espectaculo = E.id " );
		sql.append( String.format( "WHERE O.id_compania_de_teatro = %s", idCompania ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOEspectaculo.resultToEspectaculo( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public Espectaculo getEspectaculoFrom( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		Espectaculo espectaculo = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM OFRECE O INNER JOIN ESPECTACULOS E " );
		sql.append( "                ON O.id_espectaculo = E.id " );
		sql.append( String.format( "WHERE O.id_compania_de_teatro = %s ", idCompania ) );
		sql.append( String.format( "  AND E.id = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			espectaculo = DAOEspectaculo.resultToEspectaculo( rs );
		}
		rs.close( );
		s.close( );
		return espectaculo;
	}
	
	public List<CompaniaDeTeatro> getCompaniasWhoOffer( Long idEspectaculo ) throws SQLException
	{
		List<CompaniaDeTeatro> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT C.*, U.* " );
		sql.append( "FROM " );
		sql.append( "  OFRECE O " );
		sql.append( "  INNER JOIN " );
		sql.append( "  COMPANIAS_DE_TEATRO C " );
		sql.append( "    ON O.id_compania_de_teatro = C.id " );
		sql.append( "  INNER JOIN " );
		sql.append( "  USUARIOS U " );
		sql.append( "    ON C.ID = U.IDENTIFICACION " );
		sql.append( "       AND C.TIPO_ID = U.TIPO_IDENTIFICACION " );
		sql.append( String.format( "WHERE O.id_espectaculo = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOCompaniaDeTeatro.resultToCompaniaDeTeatro( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public void deleteEntryOfrecen( Long icCompania, Long idEspectaculo ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM OFRECE " );
		sql.append( String.format( "WHERE id_compania_de_teatro = %s ", icCompania ) );
		sql.append( String.format( "AND id_espectaculo = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}