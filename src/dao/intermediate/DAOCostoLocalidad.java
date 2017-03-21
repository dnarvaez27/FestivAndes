package dao.intermediate;

import dao.DAO;
import vos.intermediate.CostoLocalidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dave on 20/03/2017.
 */
public class DAOCostoLocalidad extends DAO
{
	public CostoLocalidad createEntryCostoLocalidad( CostoLocalidad costoLocalidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO COSTO_LOCALIDAD " );
		sql.append( "( fecha, id_lugar, id_localidad, costo )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", costoLocalidad.getFecha( ) ) );
		sql.append( String.format( "%s, ", costoLocalidad.getIdLugar( ) ) );
		sql.append( String.format( "%s, ", costoLocalidad.getIdLocalidad( ) ) );
		sql.append( String.format( "%s ", costoLocalidad.getCosto( ) ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
		return costoLocalidad;
	}
	
	public List<CostoLocalidad> getCostoLocalidadesFromFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		List<CostoLocalidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD " );
		sql.append( String.format( "WHERE fecha = %s", fecha ) );
		sql.append( String.format( "AND id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToCostoLocalidad( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<CostoLocalidad> getCostoLocalidadFromLocalidad( Long idLocalidad ) throws SQLException
	{
		List<CostoLocalidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD " );
		sql.append( String.format( "WHERE id_localidad = %s", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToCostoLocalidad( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	
	public CostoLocalidad getCostoLocalidadFromFuncion( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		CostoLocalidad costoLocalidad = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD " );
		sql.append( String.format( "WHERE fecha = %s", fecha ) );
		sql.append( String.format( "AND id_lugar = %s", idLugar ) );
		sql.append( String.format( "AND id_localidad = %s", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			costoLocalidad = resultToCostoLocalidadExtended( rs );
		}
		rs.close( );
		s.close( );
		return costoLocalidad;
	}
	
	public List<CostoLocalidad.Extended> getCostoLocalidadesFromFuncionExtended( Date fecha, Long idLugar ) throws SQLException
	{
		List<CostoLocalidad.Extended> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD CL INNER JOIN LOCALIDADES L " );
		sql.append( "                      ON CL.id_localidad = L.id " );
		sql.append( String.format( "WHERE fecha = %s", fecha ) );
		sql.append( String.format( "AND id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToCostoLocalidadExtended( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public CostoLocalidad updateCostoLocalidad( Date fecha, Long idLugar, Long idLocalidad, CostoLocalidad costoLocalidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE COSTO_LOCALIDAD " );
		sql.append( "SET " );
		sql.append( String.format( "fecha = %s, ", costoLocalidad.getFecha( ) ) );
		sql.append( String.format( "id_lugar = %s, ", costoLocalidad.getIdLugar( ) ) );
		sql.append( String.format( "id_localidad = %s, ", costoLocalidad.getIdLocalidad( ) ) );
		sql.append( String.format( "costo = %s = %s", costoLocalidad.getCosto( ) ) );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s AND ", fecha ) );
		sql.append( String.format( "id_lugar = %s AND ", idLugar ) );
		sql.append( String.format( "id_localidad = %s ", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
		return costoLocalidad;
	}
	
	public void deleteEntryCostoLocalidad( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM COSTO_LOCALIDAD " );
		sql.append( String.format( "WHERE fecha = %s ", fecha ) );
		sql.append( String.format( "AND id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND id_localidad = %s ", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	private CostoLocalidad.Extended resultToCostoLocalidadExtended( ResultSet rs ) throws SQLException
	{
		CostoLocalidad.Extended extended = new CostoLocalidad.Extended( );
		extended.setFecha( rs.getDate( "fecha" ) );
		extended.setIdLugar( rs.getLong( "id_lugar" ) );
		extended.setNombreLocalidad( rs.getString( "nombre_localidad" ) );
		extended.setCosto( rs.getFloat( "costo" ) );
		return extended;
	}
	
	private CostoLocalidad resultToCostoLocalidad( ResultSet rs ) throws SQLException
	{
		CostoLocalidad costoLocalidad = new CostoLocalidad( );
		costoLocalidad.setFecha( rs.getDate( "fecha" ) );
		costoLocalidad.setIdLugar( rs.getLong( "id_lugar" ) );
		costoLocalidad.setIdLocalidad( rs.getLong( "id_localidad" ) );
		costoLocalidad.setCosto( rs.getFloat( "costo" ) );
		return costoLocalidad;
	}
}