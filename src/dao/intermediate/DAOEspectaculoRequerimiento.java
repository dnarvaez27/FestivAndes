package dao.intermediate;

import dao.DAO;
import dao.DAOEspectaculo;
import vos.Espectaculo;
import vos.RequerimientoTecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOEspectaculoRequerimiento extends DAO{
	public List<RequerimientoTecnico> getRequerimientoTecnicosFromEspectaculo( Long idEspectaculo ) throws SQLException
	{
		List<RequerimientoTecnico> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULO_REQUERIMIENTO ER INNER JOIN REQUERIMIENTOS R ON ER.id_requerimiento = R.id " );
		sql.append( String.format( "WHERE id_espectaculo = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToRequerimientoTecnico( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Espectaculo> getEspectaculosFromRequerimientoTecnico( Long idRequerimientoTecnico ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULO_REQUERIMIENTO ER INNER JOIN REQUERIMIENTOS E ON EG.id_espectaculo = E.id " );
		sql.append( String.format( "WHERE ER.id_requerimiento = %s ", idRequerimientoTecnico ) );
		
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
	
	public void createEntryER( Long idEspectaculo, Long idRequerimientoTecnico ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO ESPECTACULO_REQUERIMIENTO " );
		sql.append( "( id_espectaculo, id_requerimiento )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idEspectaculo ) );
		sql.append( String.format( "%s ", idRequerimientoTecnico ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public void deleteEntryEG( Long idEspectaculo, Long idRequerimientoTecnico ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ESPECTACULO_REQUERIMIENTO " );
		sql.append( String.format( "WHERE id_espectaculo = %s", idEspectaculo ) );
		sql.append( String.format( "WHERE id_requerimiento = %s", idRequerimientoTecnico ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	private RequerimientoTecnico resultToRequerimientoTecnico( ResultSet rs ) throws SQLException
	{
		RequerimientoTecnico req = new RequerimientoTecnico( );
		req.setNombre( rs.getString( "nombre" ) );
		req.setId( rs.getLong( "id" ) );
		return req;
	}
}
