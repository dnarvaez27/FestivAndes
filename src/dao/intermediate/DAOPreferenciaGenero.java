package dao.intermediate;

import dao.DAO;
import dao.DAOGenero;
import dao.DAOUsuarioRegistrado;
import vos.Genero;
import vos.UsuarioRegistrado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOPreferenciaGenero extends DAO
{
	public void createEntryPreferenciaGenero( Long idUsuario, String tipo, Long idGenero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO PREFERENCIA_GENEROS " );
		sql.append( "( id_usuario, id_tipo, id_genero )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", idUsuario ) );
		sql.append( String.format( "'%s', ", tipo ) );
		sql.append( String.format( "%s ", idGenero ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<Genero> getGeneroesPreferidosByUser( Long idUsuario, String tipo ) throws SQLException
	{
		List<Genero> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM PREFERENCIA_GENEROS P INNER JOIN GENEROS L " );
		sql.append( "                        ON P.id_genero = L.id " );
		sql.append( String.format( "WHERE P.id_usuario = %s ", idUsuario ) );
		sql.append( String.format( "  AND P.id_tipo = '%s' ", tipo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOGenero.resultToGenero( rs ) );
		}
		return list;
	}
	
	public List<UsuarioRegistrado> getUsersWhoPreferGenero( Long idGenero ) throws SQLException
	{
		List<UsuarioRegistrado> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM PREFERENCIA_GENEROS P INNER JOIN USUARIOS_REGISTRADO U " );
		sql.append( "                            ON P.id_usuario = U.identificacion " );
		sql.append( "                            AND P.id_tipo = U.tipo_id " );
		sql.append( String.format( "WHERE P.id_genero = %s ", idGenero ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOUsuarioRegistrado.restultToAccesibildiad( rs ) );
		}
		return list;
	}
	
	public Genero getGeneroPreferidoUser( Long idUsuario, String tipo, Long idGenero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM PREFERENCIA_GENEROS P INNER JOIN GENEROS G " );
		sql.append( "                        ON P.id_genero = G.id " );
		sql.append( String.format( "WHERE P.id_usuario = %s ", idUsuario ) );
		sql.append( String.format( "  AND P.id_tipo = '%s' ", tipo ) );
		sql.append( String.format( "  AND P.id_genero = %s ", idGenero ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			return DAOGenero.resultToGenero( rs );
		}
		return null;
	}
	
	public void deletePreferenciaGenero( Long idUsuario, String tipo, Long idGenero ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM PREFERENCIA_GENEROS " );
		sql.append( String.format( "WHERE id_usuario = %s ", idUsuario ) );
		sql.append( String.format( "  AND id_tipo = '%s' ", tipo ) );
		sql.append( String.format( "  AND id_genero = %s", idGenero ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}