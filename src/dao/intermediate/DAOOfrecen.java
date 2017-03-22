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
	public List<Espectaculo> getEspectaculosFromCompania( Long idCompania ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM OFRECE O INNER JOIN ESPECTACULO E " );
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
	
	public List<CompaniaDeTeatro> getCompaniasWhoOffer( Long idEspectaculo ) throws SQLException
	{
		List<CompaniaDeTeatro> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELET * " );
		sql.append( "FROM OFRECEN O INNER JOIN COMPANIAS_DE_TEATRO C " );
		sql.append( "                ON O.id_compania = C.id " );
		sql.append( String.format( "WHERE O.id_espectaculo = %s", idEspectaculo ) );
		
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
	
	public void createEntryOfrecen( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO OFRECEN " );
		sql.append( "( id_compania, id_espectaculo )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idCompania ) );
		sql.append( String.format( "%s ", idEspectaculo ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public void deleteEntryOfrecen( Long idCompnia, Long idEspectaculo ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM OFRECEN " );
		sql.append( String.format( "WHERE id_compania = %s ", idCompnia ) );
		sql.append( String.format( "AND id_espectaculo = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}