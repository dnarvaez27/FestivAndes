package dao;

import vos.RequerimientoTecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAORequerimientoTecnico extends DAO
{
	public DAORequerimientoTecnico( )
	{
		super( );
	}
	
	public RequerimientoTecnico createRequerimientoTecnico( RequerimientoTecnico req ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO REQUERIMIENTOS_TECNICOS " );
		sql.append( "( id, nombre ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", req.getId( ) ) );
		sql.append( String.format( "'%s' ", req.getNombre( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return req;
	}
	
	public List<RequerimientoTecnico> getRequerimientosTecnicos( ) throws SQLException
	{
		List<RequerimientoTecnico> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM REQUERIMIENTOS_TECNICOS" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToRequerimientoTecnico( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public RequerimientoTecnico getRequerimientoTecnico( Long id ) throws SQLException
	{
		RequerimientoTecnico req = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM REQUERIMIENTOS_TECNICOS " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			req = resultToRequerimientoTecnico( rs );
		}
		rs.close( );
		s.close( );
		return req;
	}
	
	public RequerimientoTecnico updateRequerimientoTecnico( Long id, RequerimientoTecnico req ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE REQUERIMIENTOS_TECNICOS " );
		sql.append( String.format( "SET nombre = '%s' ", req.getNombre( ) ) );
		sql.append( String.format( "WHERE id = %s", req.getId( ) ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return req;
	}
	
	public void deleteRequerimientoTecnico( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM REQUERIMIENTOS_TECNICOS " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static RequerimientoTecnico resultToRequerimientoTecnico( ResultSet rs ) throws SQLException
	{
		RequerimientoTecnico req = new RequerimientoTecnico( );
		req.setId( rs.getLong( "id" ) );
		req.setNombre( rs.getString( "nombre" ) );
		req.setImagen( rs.getString( "imagen" ) );
		return req;
	}
}