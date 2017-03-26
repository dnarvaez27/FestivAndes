package dao.intermediate;

import dao.DAO;
import dao.DAOFuncion;
import dao.DAOLocalidad;
import vos.Funcion;
import vos.Localidad;
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
		sql.append( String.format( "%s, ", toDate( costoLocalidad.getFecha( ) ) ) );
		sql.append( String.format( "%s, ", costoLocalidad.getIdLugar( ) ) );
		sql.append( String.format( "%s, ", costoLocalidad.getIdLocalidad( ) ) );
		sql.append( String.format( "%s ", costoLocalidad.getCosto( ) ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
		return costoLocalidad;
	}
	
	public List<CostoLocalidad> getCostosLocalidades( ) throws SQLException
	{
		List<CostoLocalidad> list = new LinkedList<>( );
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD " );
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			list.add( resultToCostoLocalidad( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Localidad> getCostoLocalidadesFromFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		List<Localidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  NVL( CAPACIDAD, -1 )   AS CAPACIDAD, " );
		sql.append( "  L.id                   AS ID, " );
		sql.append( "  L.nombre               AS nombre, " );
		sql.append( "  COSTO, " );
		sql.append( "  NVL( ES_NUMERADO, 0 )  AS ES_NUMERADO " );
		sql.append( "FROM ( COSTO_LOCALIDAD CL RIGHT JOIN LOCALIDADES L " );
		sql.append( "    ON CL.id_localidad = L.id " );
		sql.append( "  LEFT JOIN LUGAR_LOCALIDAD LL " );
		sql.append( "    ON L.ID = LL.ID_LOCALIDAD " );
		sql.append( "       AND CL.ID_LUGAR = LL.ID_LUGAR ) " );
		sql.append( String.format( "WHERE CL.fecha = %s ", toDate( fecha ) ) );
		sql.append( String.format( "AND Cl.id_lugar = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOLocalidad.resultToLocalidad( rs, 3 ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Funcion> getCostoLocalidadFromLocalidad( Long idLocalidad ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD CL INNER JOIN FUNCIONES F " );
		sql.append( "                     ON CL.fecha = F.fecha " );
		sql.append( "                     AND CL.id_lugar = F.id_lugar " );
		sql.append( String.format( "WHERE id_localidad = %s", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOFuncion.resultToFuncion( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public Localidad getCostoLocalidadFromFuncion( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		Localidad costoLocalidad = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  NVL( CAPACIDAD, -1 )   AS CAPACIDAD, " );
		sql.append( "  L.id                   AS ID, " );
		sql.append( "  L.nombre               AS nombre, " );
		sql.append( "  COSTO, " );
		sql.append( "  NVL( ES_NUMERADO, 0 )  AS ES_NUMERADO " );
		sql.append( "FROM ( COSTO_LOCALIDAD CL RIGHT JOIN LOCALIDADES L " );
		sql.append( "    ON CL.id_localidad = L.id " );
		sql.append( "  LEFT JOIN LUGAR_LOCALIDAD LL " );
		sql.append( "    ON L.ID = LL.ID_LOCALIDAD " );
		sql.append( "       AND CL.ID_LUGAR = LL.ID_LUGAR ) " );
		sql.append( String.format( "WHERE CL.fecha = %s ", toDate( fecha ) ) );
		sql.append( String.format( "AND CL.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND CL.id_localidad = %s ", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			costoLocalidad = DAOLocalidad.resultToLocalidad( rs, 3 );
		}
		rs.close( );
		s.close( );
		return costoLocalidad;
	}
	
	public CostoLocalidad updateCostoLocalidad( Date fecha, Long idLugar, Long idLocalidad, CostoLocalidad costoLocalidad ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE COSTO_LOCALIDAD " );
		sql.append( "SET " );
		sql.append( String.format( "WHERE fecha = %s  ", fecha ) );
		sql.append( String.format( "  AND id_lugar = %s ", idLugar ) );
		sql.append( String.format( "  AND id_localidad = %s ", idLocalidad ) );
		
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