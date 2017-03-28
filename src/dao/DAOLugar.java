package dao;

import vos.Lugar;
import vos.Usuario;
import vos.reportes.RFC2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOLugar extends DAO
{
	public DAOLugar( )
	{
		super( );
	}
	
	public Lugar createLugar( Long id, String password, Lugar lugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", id ) );
		sql.append( String.format( "AND password = '%s' ", password ) );
		sql.append( String.format( "AND rol = '%s'", Usuario.USUARIO_ADMINISTRADOR ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			sql = new StringBuilder( );
			sql.append( "INSERT INTO LUGARES " );
			sql.append( "( id, nombre, disponibilidad_inicio, disponibilidad_fin, es_abierto, tipo_lugar ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", lugar.getId( ) ) );
			sql.append( String.format( "'%s', ", lugar.getNombre( ) ) );
			sql.append( String.format( "%s, ", toDate( lugar.getDisponibilidadInicio( ) ) ) );
			sql.append( String.format( "%s, ", toDate( lugar.getDisponibilidadFin( ) ) ) );
			sql.append( String.format( "%s, ", lugar.getEsAbierto( ) ) );
			sql.append( String.format( "'%s' ", lugar.getTipo( ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return lugar;
	}
	
	public List<Lugar> getLugares( ) throws SQLException
	{
		List<Lugar> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM LUGARES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( restultToAccesibildiad( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Lugar getLugar( Long id ) throws SQLException
	{
		Lugar lugar = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGARES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			lugar = restultToAccesibildiad( rs );
		}
		s.close( );
		return lugar;
	}
	
	public Lugar updateLugar( Long id, Lugar lugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE LUGARES " );
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s', ", lugar.getNombre( ) ) );
		sql.append( String.format( "disponibilidad_inicio = %s, ", toDate( lugar.getDisponibilidadInicio( ) ) ) );
		sql.append( String.format( "disponibilidad_fin = %s, ", toDate( lugar.getDisponibilidadFin( ) ) ) );
		sql.append( String.format( "es_abierto = %s, ", lugar.getEsAbierto( ) ) );
		sql.append( String.format( "tipo_lugar = '%s' ", lugar.getTipo( ) ) );
		sql.append( String.format( "WHERE id = %s ", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return lugar;
	}
	
	public void deleteLugar( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LUGARES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Lugar restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		Lugar l = new Lugar( );
		l.setId( rs.getLong( "id" ) );
		l.setNombre( rs.getString( "nombre" ) );
		l.setDisponibilidadInicio( rs.getDate( "disponibilidad_inicio" ) );
		l.setDisponibilidadFin( rs.getDate( "disponibilidad_fin" ) );
		l.setEsAbierto( rs.getInt( "es_abierto" ) );
		l.setTipo( rs.getString( "tipo_lugar" ) );
		return l;
	}
	
	public RFC2 generarReporte( Long idLugar ) throws SQLException
	{
		RFC2 rfc2 = new RFC2( );
		Lugar l = getLugar( idLugar );
		rfc2.setLugar( l.getNombre( ) );
		rfc2.setTipoLugar( l.getTipo( ) );
		rfc2.setEsAbierto( l.getEsAbierto( ) );
		rfc2.setDisponibilidadInicio( l.getDisponibilidadInicio( ) );
		rfc2.setDisponibilidadFin( l.getDisponibilidadFin( ) );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  LOCALIDADES.NOMBRE                   AS localidad, " );
		sql.append( "  LUGAR_LOCALIDAD.CAPACIDAD, " );
		sql.append( "  LUGAR_LOCALIDAD.ES_NUMERADO, " );
		sql.append( "  FUNCIONES.FECHA                      AS fecha_funcion, " );
		sql.append( "  ESPECTACULOS.NOMBRE                  AS ESPECTACULO, " );
		sql.append( "  COSTO_LOCALIDAD.COSTO, " );
		sql.append( "  CAPACIDAD - NVL(BOLETAS_VENDIDAS, 0) AS BOLETERIA_DISPONIBLE " );
		sql.append( "FROM " );
		sql.append( "  LUGARES " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  LUGAR_LOCALIDAD ON LUGARES.ID = LUGAR_LOCALIDAD.ID_LUGAR " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  FUNCIONES ON LUGARES.ID = FUNCIONES.ID_LUGAR " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  COSTO_LOCALIDAD " );
		sql.append( "    ON FUNCIONES.ID_LUGAR = COSTO_LOCALIDAD.ID_LUGAR " );
		sql.append( "       AND FUNCIONES.FECHA = COSTO_LOCALIDAD.FECHA " );
		sql.append( "       AND LUGAR_LOCALIDAD.ID_LOCALIDAD = COSTO_LOCALIDAD.ID_LOCALIDAD " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  ESPECTACULOS ON FUNCIONES.ID_ESPECTACULO = ESPECTACULOS.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  LOCALIDADES ON LUGAR_LOCALIDAD.ID_LOCALIDAD = LOCALIDADES.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  (SELECT " );
		sql.append( "     BOLETAS.ID_LUGAR, " );
		sql.append( "     BOLETAS.ID_LOCALIDAD, " );
		sql.append( "     BOLETAS.FECHA, " );
		sql.append( "     COUNT(BOLETAS.ID_LOCALIDAD) BOLETAS_VENDIDAS " );
		sql.append( "   FROM " );
		sql.append( "     FUNCIONES " );
		sql.append( "     INNER JOIN " );
		sql.append( "     BOLETAS ON FUNCIONES.ID_LUGAR = BOLETAS.ID_LUGAR AND FUNCIONES.FECHA = BOLETAS.FECHA " );
		sql.append( "     INNER JOIN " );
		sql.append( "     COSTO_LOCALIDAD " );
		sql.append( "       ON FUNCIONES.ID_LUGAR = COSTO_LOCALIDAD.ID_LUGAR AND FUNCIONES.FECHA = COSTO_LOCALIDAD.FECHA " );
		sql.append( "          AND BOLETAS.ID_LOCALIDAD = COSTO_LOCALIDAD.ID_LOCALIDAD " );
		sql.append( "   GROUP BY BOLETAS.ID_LUGAR, BOLETAS.ID_LOCALIDAD, BOLETAS.FECHA) Z " );
		sql.append( "    ON Z.ID_LUGAR = LUGARES.ID " );
		sql.append( "       AND Z.ID_LOCALIDAD = COSTO_LOCALIDAD.ID_LOCALIDAD " );
		sql.append( "       AND Z.FECHA = FUNCIONES.FECHA " );
		sql.append( String.format( "WHERE LUGARES.ID = %s ", idLugar ) );
		sql.append( "ORDER BY LUGARES.ID, FUNCIONES.FECHA, LUGAR_LOCALIDAD.ID_LOCALIDAD " );
		
		List<RFC2.Funcion> funciones = new LinkedList<>( );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			RFC2.Funcion funcion = rfc2.new Funcion( );
			
			String localidad = rs.getString( "localidad" );
			Integer capacidad = rs.getInt( "capacidad" );
			Integer esNumerado = rs.getInt( "es_numerado" );
			Date fechaFuncion = rs.getDate( "fecha_funcion" );
			String espectaculo = rs.getString( "espectaculo" );
			Double costo = rs.getDouble( "costo" );
			Integer boleteriaDisponible = rs.getInt( "boleteria_disponible" );
			
			funcion.setLocalidad( localidad );
			funcion.setCapacidad( capacidad );
			funcion.setEsNumerado( esNumerado );
			funcion.setFechaFuncion( fechaFuncion );
			funcion.setEspectaculo( espectaculo );
			funcion.setCosto( costo );
			funcion.setBoleteriaDisponible( boleteriaDisponible );
			
			funciones.add( funcion );
		}
		rfc2.setLocalidades( funciones );
		
		rs.close( );
		s.close( );
		return rfc2;
	}
}