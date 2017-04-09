package dao;

import vos.Abono;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dave on 9/04/2017.
 */
public class DAOAbono extends DAO
{
	public DAOAbono( )
	{
		super( );
	}
	
	public void crearAbono( Abono abono ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO ABONO " );
		sql.append( "(ID_FESTIVAL, ID_USUARIO, TIPO_ID, DESCUENTO) " );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", abono.getIdFestival( ) ) );
		sql.append( String.format( "%s, ", abono.getIdUsuario( ) ) );
		sql.append( String.format( "'%s', ", abono.getTipoId( ) ) );
		sql.append( String.format( "%s ", abono.getDescuento( ) ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<Abono> getAbonos( ) throws SQLException
	{
		List<Abono> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ABONO " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToAbono( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Abono> getAbonosFrom( Long idUsuario, String tipoId ) throws SQLException
	{
		List<Abono> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ABONO " );
		sql.append( String.format( "WHERE ID_USUARIO = %s ", idUsuario ) );
		sql.append( String.format( "  AND TIPO_ID = '%s' ", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		
		while( rs.next( ) )
		{
			list.add( resultToAbono( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public Abono getAbono( Long idFestival, Long idUsuario, String tipoId ) throws SQLException
	{
		Abono abono = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ABONO " );
		sql.append( String.format( "WHERE ID_FESTIVAL = %s ", idFestival ) );
		sql.append( String.format( "  AND ID_USUARIO = %s ", idUsuario ) );
		sql.append( String.format( "  AND TIPO_ID = '%s' ", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			abono = resultToAbono( rs );
		}
		
		rs.close( );
		s.close( );
		return abono;
	}
	
	public Abono updateAbono( Long idFestival, Long idUsuario, String tipoId, Abono abono ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE ABONO " );
		sql.append( "SET DESCUENTO = 10 " );
		sql.append( String.format( "WHERE ID_FESTIVAL = %s ", idFestival ) );
		sql.append( String.format( "  AND ID_USUARIO = %s ", idUsuario ) );
		sql.append( String.format( "  AND TIPO_ID = '%s' ", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
		return abono;
	}
	
	public void deleteAbono( Long idFestival, Long idUsuario, String tipoId ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ABONO " );
		sql.append( String.format( "WHERE ID_FESTIVAL = %s ", idFestival ) );
		sql.append( String.format( "  AND ID_USUARIO = %s ", idUsuario ) );
		sql.append( String.format( "  AND TIPO_ID = '%s' ", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	private static Abono resultToAbono( ResultSet rs ) throws SQLException
	{
		Abono abono = new Abono( );
		abono.setIdFestival( rs.getLong( "id_festival" ) );
		abono.setIdUsuario( rs.getLong( "id_usuario" ) );
		abono.setTipoId( rs.getString( "tipo_id" ) );
		abono.setDescuento( rs.getFloat( "descuento" ) );
		return abono;
	}
}