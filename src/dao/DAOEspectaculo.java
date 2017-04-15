package dao;

import vos.Espectaculo;
import vos.reportes.RFC4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOEspectaculo extends DAO
{
	public DAOEspectaculo( )
	{
		super( );
	}
	
	public Espectaculo createEspectaculo( Espectaculo object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO ESPECTACULOS " );
		sql.append( "( id, nombre, duracion, idioma, costo_realizacion, descripcion, id_festival, id_clasificacion ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getId( ) ) );
		sql.append( String.format( "'%s', ", object.getNombre( ) ) );
		sql.append( String.format( "%s, ", object.getDuracion( ) ) );
		sql.append( String.format( "'%s', ", object.getIdioma( ) ) );
		sql.append( String.format( "%s, ", object.getCostoRealizacion( ) ) );
		sql.append( String.format( "'%s', ", object.getDescripcion( ) ) );
		sql.append( String.format( "%s, ", object.getIdFestival( ) ) );
		sql.append( String.format( "%s ", object.getIdClasificacion( ) ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public List<Espectaculo> getEspectaculos( Long idFestival ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM ESPECTACULOS " );
		sql.append( String.format( "WHERE id_festival = %s ", idFestival ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToEspectaculo( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Espectaculo> getEspectaculos( ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM ESPECTACULOS " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToEspectaculo( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public Espectaculo getEspectaculo( Long idFestival, Long id ) throws SQLException
	{
		Espectaculo object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULOS " );
		sql.append( String.format( "WHERE id = %s ", id ) );
		sql.append( String.format( "AND id_festival = %s ", idFestival ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToEspectaculo( rs );
		}
		rs.close( );
		s.close( );
		return object;
	}
	
	public Espectaculo getEspectaculo( Long id ) throws SQLException
	{
		Espectaculo object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULOS " );
		sql.append( String.format( "WHERE id = %s ", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToEspectaculo( rs );
		}
		rs.close( );
		s.close( );
		return object;
	}
	
	public Espectaculo updateEspectaculo( Long idFestival, Long id, Espectaculo object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE ESPECTACULOS " );
		sql.append( "SET " );
		sql.append( String.format( "id = %s, ", object.getId( ) ) );
		sql.append( String.format( "nombre = '%s', ", object.getNombre( ) ) );
		sql.append( String.format( "duracion = %s, ", object.getDuracion( ) ) );
		sql.append( String.format( "idioma = '%s', ", object.getIdioma( ) ) );
		sql.append( String.format( "costo_realizacion = %s, ", object.getCostoRealizacion( ) ) );
		sql.append( String.format( "descripcion = '%s', ", object.getDescripcion( ) ) );
		sql.append( String.format( "id_festival= %s, ", object.getIdFestival( ) ) );
		sql.append( String.format( "id_clasificacion = %s ", object.getIdClasificacion( ) ) );
		sql.append( String.format( "WHERE id = %s ", id ) );
		sql.append( String.format( "AND id_festival = %s ", idFestival ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public void deleteEspectaculo( Long idFestival, Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ESPECTACULOS " );
		sql.append( String.format( "WHERE id = %s ", id ) );
		sql.append( String.format( "AND id_festival = %s ", idFestival ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Espectaculo resultToEspectaculo( ResultSet rs ) throws SQLException
	{
		Espectaculo object = new Espectaculo( );
		object.setId( rs.getLong( "id" ) );
		object.setNombre( rs.getString( "nombre" ) );
		object.setDuracion( rs.getInt( "duracion" ) );
		object.setIdioma( rs.getString( "idioma" ) );
		object.setCostoRealizacion( rs.getFloat( "costo_realizacion" ) );
		object.setDescripcion( rs.getString( "descripcion" ) );
		object.setIdFestival( rs.getLong( "id_festival" ) );
		object.setIdClasificacion( rs.getLong( "id_clasificacion" ) );
		return object;
	}
	
	public RFC4 generarReporte( Long idFestival, Long idEspectaculo ) throws SQLException
	{
		RFC4 req = new RFC4( );
		List<RFC4.P1> p1s = new LinkedList<>( );
		List<RFC4.P2> p2s = new LinkedList<>( );
		req.setEspectaculo( getEspectaculo( idFestival, idEspectaculo ).getNombre( ) );
		
		// P1
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  F.FECHA, " );
		sql.append( "  L.NOMBRE                 AS LUGAR, " );
		sql.append( "  ROL                      AS TIPO_USUARIO, " );
		sql.append( "  NVL(BOLETAS_VENDIDAS, 0) AS BOLETAS_VENDIDAS, " );
		sql.append( "  NVL(TOTAL, 0)            AS TOTAL " );
		sql.append( "FROM (SELECT " );
		sql.append( "        FECHA, " );
		sql.append( "        ID_LUGAR, " );
		sql.append( "        ROL, " );
		sql.append( "        SUM(BOLETAS_VENDIDAS)         AS BOLETAS_VENDIDAS, " );
		sql.append( "        SUM(BOLETAS_VENDIDAS * COSTO) AS TOTAL " );
		sql.append( "      FROM (SELECT " );
		sql.append( "              FECHA, " );
		sql.append( "              ID_LUGAR, " );
		sql.append( "              ID_LOCALIDAD, " );
		sql.append( "              COSTO, " );
		sql.append( "              ROL, " );
		sql.append( "              COUNT(*) AS BOLETAS_VENDIDAS " );
		sql.append( "            FROM (SELECT " );
		sql.append( "                    F.FECHA, " );
		sql.append( "                    F.ID_LUGAR, " );
		sql.append( "                    F.ID_LOCALIDAD, " );
		sql.append( "                    F.COSTO, " );
		sql.append( "                    B.ID_USUARIO " );
		sql.append( "                  FROM COSTO_LOCALIDAD F " );
		sql.append( "                    INNER JOIN BOLETAS B " );
		sql.append( "                      ON F.ID_LUGAR = B.ID_LUGAR " );
		sql.append( "                         AND F.FECHA = B.FECHA " );
		sql.append( "                         AND F.ID_LOCALIDAD = B.ID_LOCALIDAD) B INNER JOIN USUARIOS U " );
		sql.append( "                ON B.ID_USUARIO = U.IDENTIFICACION " );
		sql.append( "            GROUP BY FECHA, ID_LUGAR, COSTO, ID_LOCALIDAD, ROL) " );
		sql.append( "      GROUP BY FECHA, ID_LUGAR, ROL) " );
		sql.append( "     A RIGHT JOIN FUNCIONES F " );
		sql.append( "    ON A.FECHA = F.FECHA " );
		sql.append( "       AND A.ID_LUGAR = F.ID_LUGAR " );
		sql.append( "  LEFT JOIN LUGARES L ON F.ID_LUGAR = L.ID " );
		sql.append( String.format( "WHERE f.ID_ESPECTACULO = %s ", idEspectaculo ) );
		sql.append( "ORDER BY FECHA, L.NOMBRE, ROL, BOLETAS_VENDIDAS " );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			RFC4.P1 p1 = req.new P1( );
			
			Date fecha = rs.getDate( "fecha" );
			String lugar = rs.getString( "lugar" );
			String tipoUsuario = rs.getString( "tipo_usuario" );
			int numBoletasVendidas = rs.getInt( "boletas_vendidas" );
			double totalVendido = rs.getDouble( "total" );
			
			p1.setFecha( fecha );
			p1.setLugar( lugar );
			p1.setTipoUsuario( tipoUsuario );
			p1.setBoletasVendidas( numBoletasVendidas );
			p1.setTotal( totalVendido );
			
			p1s.add( p1 );
		}
		// P2
		sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  l.NOMBRE                             AS LUGAR," );
		sql.append( "  NVL( ( OCUPADOS * 100 / TOTAL ), 0 ) AS PORCENTAJE_DE_OCUPACION " );
		sql.append( "FROM ( SELECT " );
		sql.append( "         id_lugar, " );
		sql.append( "         SUM( CAPACIDAD ) AS TOTAL " );
		sql.append( "       FROM LUGAR_LOCALIDAD " );
		sql.append( "       GROUP BY id_lugar ) C " );
		sql.append( "  INNER JOIN ( SELECT " );
		sql.append( "                 F.id_lugar, " );
		sql.append( "                 COUNT( B.id_lugar ) OCUPADOS " );
		sql.append( "               FROM BOLETAS B RIGHT JOIN FUNCIONES F " );
		sql.append( "                   ON B.ID_LUGAR = F.ID_LUGAR " );
		sql.append( "                      AND B.FECHA = F.FECHA " );
		sql.append( String.format( "WHERE F.ID_ESPECTACULO = %s ", idEspectaculo ) );
		sql.append( "               GROUP BY F.id_lugar ) O " );
		sql.append( "    ON C.id_lugar = O.id_lugar " );
		sql.append( "  INNER JOIN LUGARES L " );
		sql.append( "    ON O.ID_LUGAR = L.ID " );
		sql.append( "ORDER BY PORCENTAJE_DE_OCUPACION DESC " );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt2 );
		
		rs = prepStmt2.executeQuery( );
		while( rs.next( ) )
		{
			RFC4.P2 p2 = req.new P2( );
			
			String idLugar = rs.getString( "lugar" );
			Double porcentajeOcupacion = rs.getDouble( "porcentaje_de_ocupacion" );
			
			p2.setLugar( idLugar );
			p2.setPorcentajeOcupacion( porcentajeOcupacion );
			
			p2s.add( p2 );
		}
		
		req.setProducido( p1s );
		req.setOcupacion( p2s );
		
		rs.close( );
		s.close( );
		return req;
	}
	
	public List<Espectaculo> getEspectaculosPopulares( Date fInicio, Date fFin ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT ESPECTACULOS.*, NUM_ASISTENTES " );
		sql.append( "FROM (SELECT " );
		sql.append( "        ESPECTACULOS.ID, " );
		sql.append( "        COUNT(ESPECTACULOS.NOMBRE) AS NUM_ASISTENTES " );
		sql.append( "      FROM " );
		sql.append( "        BOLETAS " );
		sql.append( "        INNER JOIN " );
		sql.append( "        FUNCIONES ON BOLETAS.ID_LUGAR = FUNCIONES.ID_LUGAR AND BOLETAS.FECHA = FUNCIONES.FECHA " );
		sql.append( "        INNER JOIN " );
		sql.append( "        ESPECTACULOS ON FUNCIONES.ID_ESPECTACULO = ESPECTACULOS.ID " );
		sql.append( String.format( "      WHERE FUNCIONES.FECHA BETWEEN %s AND %s ", toDateTime( fInicio ), toDateTime( fFin ) ) );
		sql.append( "      GROUP BY ESPECTACULOS.ID) B INNER JOIN ESPECTACULOS ON B.ID = ESPECTACULOS.ID " );
		sql.append( "WHERE NUM_ASISTENTES = (SELECT MAX(NUM_ASISTENTES) " );
		sql.append( "                        FROM (SELECT " );
		sql.append( "                                ESPECTACULOS.NOMBRE, " );
		sql.append( "                                COUNT(ESPECTACULOS.NOMBRE) AS NUM_ASISTENTES " );
		sql.append( "                              FROM " );
		sql.append( "                                BOLETAS " );
		sql.append( "                                INNER JOIN " );
		sql.append( "                                FUNCIONES ON BOLETAS.ID_LUGAR = FUNCIONES.ID_LUGAR AND " );
		sql.append( "                                             BOLETAS.FECHA = FUNCIONES.FECHA " );
		sql.append( "                                INNER JOIN " );
		sql.append( "                                ESPECTACULOS ON FUNCIONES.ID_ESPECTACULO = ESPECTACULOS.ID " );
		sql.append( String.format( "                              WHERE FUNCIONES.FECHA BETWEEN %s AND %s ", toDate( fInicio ), toDate( fFin ) ) );
		sql.append( "                              GROUP BY ESPECTACULOS.NOMBRE)) " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			Espectaculo.EspectaculoExtended e = new Espectaculo.EspectaculoExtended( resultToEspectaculo( rs ) );
			e.setNumAsistentes( rs.getInt( "num_asistentes" ) );
			
			list.add( e );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
}