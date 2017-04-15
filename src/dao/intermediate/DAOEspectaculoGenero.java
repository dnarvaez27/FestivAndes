package dao.intermediate;

import dao.DAO;
import dao.DAOEspectaculo;
import vos.Espectaculo;
import vos.Genero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dave on 20/03/2017.
 */
public class DAOEspectaculoGenero extends DAO
{
	public void createEntryEG( Long idEspectaculo, Long idGenero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO ESPECTACULO_GENEROS " );
		sql.append( "( id_espectaculo, id_genero )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idEspectaculo ) );
		sql.append( String.format( "%s ", idGenero ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<Genero> getGenerosFromEspectaculo( Long idEspectaculo ) throws SQLException
	{
		List<Genero> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULO_GENEROS EG INNER JOIN GENEROS G ON EG.id_genero = G.id " );
		sql.append( String.format( "WHERE id_espectaculo = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToGenero( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Espectaculo> getEspectaculosFromGenero( Long idGenero ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULO_GENEROS EG INNER JOIN ESPECTACULOS E ON EG.id_espectaculo = E.id " );
		sql.append( String.format( "WHERE EG.id_genero = %s ", idGenero ) );
		
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
	
	public void deleteEntryEG( Long idEspectaculo, Long idGenero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ESPECTACULO_GENEROS " );
		sql.append( String.format( "WHERE id_espectaculo = %s", idEspectaculo ) );
		sql.append( String.format( "WHERE id_genero = %s", idGenero ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	private Genero resultToGenero( ResultSet rs ) throws SQLException
	{
		Genero genero = new Genero( );
		genero.setNombre( rs.getString( "nombre" ) );
		genero.setId( rs.getLong( "id" ) );
		genero.setImagen( rs.getString( "imagen" ) );
		return genero;
	}
}