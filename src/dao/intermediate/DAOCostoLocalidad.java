package dao.intermediate;

import dao.DAO;
import dao.DAOFuncion;
import dao.DAOLocalidad;
import dao.DAOSilla;
import utilities.SQLUtils;
import vos.Funcion;
import vos.Localidad;
import vos.Silla;
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
		sql.append( String.format( "%s, ", toDateTime( costoLocalidad.getFecha( ) ) ) );
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
		sql.append( String.format( "WHERE CL.fecha = %s ", toDateTime( fecha ) ) );
		sql.append( String.format( "AND Cl.id_lugar = %s ", idLugar ) );
		
		System.out.println( sql.toString( ) );
		
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
		sql.append( String.format( "WHERE CL.fecha = %s ", toDateTime( fecha ) ) );
		sql.append( String.format( "AND CL.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND CL.id_localidad = %s ", idLocalidad ) );
		
		System.out.println( sql.toString( ) );
		
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
	
	public List<Silla.SillaExtended> getEstadoSillas( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		List<Silla.SillaExtended> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  ID_LUGAR, " );
		sql.append( "  ID_LOCALIDAD, " );
		sql.append( "  NUM_FILA, " );
		sql.append( "  NUM_SILLA, " );
		sql.append( "  '0' AS DISPONIBILIDAD " );
		sql.append( "FROM ((SELECT S.* " );
		sql.append( "       FROM " );
		sql.append( "         SILLAS S " );
		sql.append( "         INNER JOIN " );
		sql.append( "         COSTO_LOCALIDAD CL " );
		sql.append( "           ON S.ID_LUGAR = CL.ID_LUGAR " );
		sql.append( "              AND S.ID_LOCALIDAD = CL.ID_LOCALIDAD " );
		sql.append( String.format( "WHERE CL.fecha = %s ", toDateTime( fecha ) ) );
		sql.append( String.format( "AND CL.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND CL.id_localidad = %s ) ", idLocalidad ) );
		sql.append( "      MINUS " );
		sql.append( "      (SELECT S.* " );
		sql.append( "       FROM BOLETAS B " );
		sql.append( "         INNER JOIN " );
		sql.append( "         SILLAS S " );
		sql.append( "           ON B.NUM_FILA = S.NUM_FILA " );
		sql.append( "              AND B.NUM_SILLA = S.NUM_SILLA " );
		sql.append( "              AND B.ID_LOCALIDAD = S.ID_LOCALIDAD " );
		sql.append( "              AND B.ID_LUGAR = S.ID_LUGAR " );
		sql.append( String.format( "WHERE B.fecha = %s ", toDateTime( fecha ) ) );
		sql.append( String.format( "AND B.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND B.id_localidad = %s )) ", idLocalidad ) );
		sql.append( "UNION (SELECT " );
		sql.append( "         S.ID_LUGAR, " );
		sql.append( "         S.ID_LOCALIDAD, " );
		sql.append( "         S.NUM_FILA, " );
		sql.append( "         S.NUM_SILLA, " );
		sql.append( "         '1' AS DISPONIBILIDAD " );
		sql.append( "       FROM BOLETAS B " );
		sql.append( "         INNER JOIN " );
		sql.append( "         SILLAS S " );
		sql.append( "           ON B.NUM_FILA = S.NUM_FILA " );
		sql.append( "              AND B.NUM_SILLA = S.NUM_SILLA " );
		sql.append( "              AND B.ID_LOCALIDAD = S.ID_LOCALIDAD " );
		sql.append( "              AND B.ID_LUGAR = S.ID_LUGAR " );
		sql.append( String.format( "WHERE B.fecha = %s ", toDateTime( fecha ) ) );
		sql.append( String.format( "AND B.id_lugar = %s ", idLugar ) );
		sql.append( String.format( "AND B.id_localidad = %s )", idLocalidad ) );
		sql.append( "ORDER BY ID_LUGAR, ID_LOCALIDAD, NUM_FILA, NUM_SILLA " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			Silla.SillaExtended silla = new Silla.SillaExtended( DAOSilla.resultToSilla( rs ) );
			silla.setDisponibilidad( rs.getInt( "disponibilidad" ) );
			list.add( silla );
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
	
	public CostoLocalidad search( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		CostoLocalidad costoLocalidad = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COSTO_LOCALIDAD " );
		sql.append( String.format( "WHERE FECHA = %s ", SQLUtils.DateUtils.toDateTime( fecha ) ) );
		sql.append( String.format( "AND ID_LOCALIDAD = %s ", idLocalidad ) );
		sql.append( String.format( "AND ID_LUGAR = %s ", idLocalidad ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			costoLocalidad = resultToCostoLocalidad( rs );
		}
		rs.close( );
		s.close( );
		return costoLocalidad;
	}
	
	public static CostoLocalidad resultToCostoLocalidad( ResultSet rs ) throws SQLException
	{
		CostoLocalidad costoLocalidad = new CostoLocalidad( );
		costoLocalidad.setFecha( rs.getTimestamp( "fecha" ) );
		costoLocalidad.setIdLugar( rs.getLong( "id_lugar" ) );
		costoLocalidad.setIdLocalidad( rs.getLong( "id_localidad" ) );
		costoLocalidad.setCosto( rs.getFloat( "costo" ) );
		return costoLocalidad;
	}
}