package dao;

import vos.Funcion;
import vos.Usuario;
import vos.reportes.RFC3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOFuncion extends DAO
{
	public DAOFuncion( )
	{
		super( );
	}
	
	public Funcion createFuncion( Long id, String password, Funcion funcion ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", id ) );
		sql.append( String.format( "AND password = '%s' ", password ) );
		sql.append( String.format( "AND rol = '%s' ", Usuario.USUARIO_ADMINISTRADOR ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		
		if( rs.next( ) )
		{
			sql = new StringBuilder( );
			sql.append( "INSERT INTO FUNCIONES " );
			sql.append( "( fecha, id_lugar, id_espectaculo, se_realiza ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", toDate( funcion.getFecha( ) ) ) );
			sql.append( String.format( "%s, ", funcion.getIdLugar( ) ) );
			sql.append( String.format( "%s, ", funcion.getIdEspectaculo( ) ) );
			sql.append( String.format( "%s ", funcion.getSeRealiza( ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return funcion;
	}
	
	public List<Funcion> getFunciones( ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM FUNCIONES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToFuncion( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public List<Funcion> getFuncionesFrom( Long idEspectaculo ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM FUNCIONES " );
		sql.append( String.format( "WHERE id_espectaculo = %s ", idEspectaculo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToFuncion( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Funcion getFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		Funcion funcion = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM FUNCIONES " );
		sql.append( String.format( "WHERE fecha = %s ", toDate( fecha ) ) );
		sql.append( String.format( "  AND id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			funcion = resultToFuncion( rs );
		}
		s.close( );
		return funcion;
	}
	
	public Funcion updateFuncion( Long idUsuario, String password, Date fecha, Long idLugar, Funcion funcion ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", idUsuario ) );
		sql.append( String.format( "AND password = '%s' ", password ) );
		sql.append( String.format( "AND rol = '%s'", Usuario.USUARIO_ORGANIZADOR ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		
		if( rs.next( ) )
		{
			sql = new StringBuilder( );
			sql.append( "UPDATE FUNCIONES " );
			sql.append( "SET " );
			sql.append( String.format( "fecha = %s, ", toDate( funcion.getFecha( ) ) ) );
			sql.append( String.format( "id_lugar = '%s', ", funcion.getIdLugar( ) ) );
			sql.append( String.format( "se_realiza = %s ", funcion.getSeRealiza( ) ) );
			sql.append( "WHERE " );
			sql.append( String.format( "fecha = %s ", toDate( fecha ) ) );
			sql.append( "AND" );
			sql.append( String.format( "id_lugar = %s", idLugar ) );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return funcion;
	}
	
	public void deleteFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM FUNCIONES " );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s ", toDate( fecha ) ) );
		sql.append( "AND" );
		sql.append( String.format( "id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Funcion resultToFuncion( ResultSet rs ) throws SQLException
	{
		Funcion funcion = new Funcion( );
		funcion.setFecha( rs.getDate( "fecha" ) );
		funcion.setIdLugar( rs.getLong( "id_lugar" ) );
		funcion.setIdEspectaculo( rs.getLong( "id_espectaculo" ) );
		funcion.setSeRealiza( rs.getInt( "se_realiza" ) );
		return funcion;
	}
	
	public List<Funcion> RFC1( String nombreGenero, String nombreCompania, String ciudad, String pais, String nombreEspectaculo, String idioma, String fechaInicio, String fechaFin, Integer duracionInicio, Integer duracionFin, String lugar, String accesibilidad, String clasificaciones, List<String> order, boolean asc ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT DISTINCT FUNCIONES.* " );
		sql.append( "FROM " );
		sql.append( "  FUNCIONES " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  ESPECTACULOS ON FUNCIONES.ID_ESPECTACULO = ESPECTACULOS.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  OFRECE ON OFRECE.ID_ESPECTACULO = FUNCIONES.ID_ESPECTACULO " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  COMPANIAS_DE_TEATRO ON OFRECE.ID_COMPANIA_DE_TEATRO = COMPANIAS_DE_TEATRO.ID AND " );
		sql.append( "                         OFRECE.TIPO_ID = COMPANIAS_DE_TEATRO.TIPO_ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  ESPECTACULO_GENEROS ON ESPECTACULOS.ID = ESPECTACULO_GENEROS.ID_ESPECTACULO " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  GENEROS ON ESPECTACULO_GENEROS.ID_GENERO = GENEROS.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  LUGARES ON FUNCIONES.ID_LUGAR = LUGARES.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  FESTIVALES ON ESPECTACULOS.ID_FESTIVAL = FESTIVALES.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  LUGAR_ACCESIBILIDAD ON LUGARES.ID = LUGAR_ACCESIBILIDAD.ID_LUGAR " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  ACCESIBILIDADES ON LUGAR_ACCESIBILIDAD.ID_ACCESIBILIDAD = ACCESIBILIDADES.ID " );
		sql.append( "  LEFT JOIN " );
		sql.append( "  CLASIFICACIONES ON ESPECTACULOS.ID_CLASIFICACION = CLASIFICACIONES.ID " );
		sql.append( "WHERE FUNCIONES.FECHA IS NOT NULL " );
		
		sql.append( ciudad != null && !ciudad.isEmpty( ) ? String.format( "AND FESTIVALES.ciudad = '%s' ", ciudad ) : "" );
		sql.append( nombreGenero != null && !nombreGenero.isEmpty( ) ? String.format( "AND GENEROS.nombre = '%s' ", nombreGenero ) : "" );
		sql.append( nombreCompania != null && !nombreCompania.isEmpty( ) ? String.format( "AND COMPANIAS_DE_TEATRO.nombre = '%s' ", nombreCompania ) : "" );
		sql.append( pais != null && !pais.isEmpty( ) ? String.format( "AND COMPANIAS_DE_TEATRO.pais_origen = '%s' ", pais ) : "" );
		sql.append( nombreEspectaculo != null && !nombreEspectaculo.isEmpty( ) ? String.format( "AND ESPECTACULOS.nombre = '%s' ", nombreEspectaculo ) : "" );
		sql.append( idioma != null && !idioma.isEmpty( ) ? String.format( "AND ESPECTACULOS.idioma = '%s' ", idioma ) : "" );
		sql.append( duracionInicio != null && duracionFin != null ? String.format( "AND ESPECTACULOS.duracion BETWEEN %s AND %s ", duracionInicio, duracionFin ) : "" );
		sql.append( lugar != null && !lugar.isEmpty( ) ? String.format( "AND LUGARES.nombre = '%s' ", lugar ) : "" );
		sql.append( accesibilidad != null && !accesibilidad.isEmpty( ) ? String.format( "AND ACCESIBILIDADES.nombre = '%s' ", accesibilidad ) : "" );
		sql.append( clasificaciones != null && !clasificaciones.isEmpty( ) ? String.format( "AND CLASIFICACIONES.nombre = '%s' ", clasificaciones ) : "" );
		
		if( fechaInicio != null && fechaFin != null && !fechaInicio.isEmpty( ) && !fechaFin.isEmpty( ) )
		{
			sql.append( String.format( "AND FUNCIONES.fecha BETWEEN %s AND %s ", toDate( fechaInicio ), toDate( fechaFin ) ) );
		}
		
		if( order != null && !order.isEmpty( ) )
		{
			sql.append( "ORDER BY " );
			for( String s : order )
			{
				sql.append( String.format( "FUNCIONES.%s, ", s ) );
			}
			sql.deleteCharAt( sql.length( ) - 2 );
			sql.append( asc ? "ASC" : "DESC" );
		}
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToFuncion( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public RFC3 RFC3( Date fecha, Long idLugar ) throws SQLException
	{
		RFC3 rfc3 = new RFC3( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  FECHA, " );
		sql.append( "  LUGARES.NOMBRE AS lugar " );
		sql.append( "FROM FUNCIONES " );
		sql.append( "  INNER JOIN LUGARES ON FUNCIONES.ID_LUGAR = LUGARES.ID " );
		sql.append( String.format( "WHERE FECHA = %s ", toDate( fecha ) ) );
		sql.append( String.format( "      AND ID_LUGAR = %s ", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			rfc3.setFecha( rs.getDate( "fecha" ) );
			rfc3.setLugar( rs.getString( "lugar" ) );
			
			sql = new StringBuilder( );
			sql.append( "SELECT " );
			sql.append( "  LOCALIDADES.NOMBRE       AS LOCALIDAD, " );
			sql.append( "  ROL                      AS TIPO_USUARIO, " );
			sql.append( "  BOLETAS_VENDIDAS, " );
			sql.append( "  COSTO * BOLETAS_VENDIDAS AS TOTAL_PRODUCIDO " );
			sql.append( "FROM " );
			sql.append( "  LUGARES " );
			sql.append( "  INNER JOIN " );
			sql.append( "  (SELECT " );
			sql.append( "     COSTO_LOCALIDAD.FECHA, " );
			sql.append( "     COSTO_LOCALIDAD.ID_LUGAR, " );
			sql.append( "     COSTO_LOCALIDAD.ID_LOCALIDAD, " );
			sql.append( "     COSTO_LOCALIDAD.COSTO, " );
			sql.append( "     USUARIOS.ROL, " );
			sql.append( "     COUNT(COSTO_LOCALIDAD.FECHA) AS BOLETAS_VENDIDAS " );
			sql.append( "   FROM " );
			sql.append( "     COSTO_LOCALIDAD " );
			sql.append( "     INNER JOIN " );
			sql.append( "     BOLETAS ON BOLETAS.ID_LUGAR = COSTO_LOCALIDAD.ID_LUGAR " );
			sql.append( "                AND BOLETAS.FECHA = COSTO_LOCALIDAD.FECHA " );
			sql.append( "                AND BOLETAS.ID_LOCALIDAD = COSTO_LOCALIDAD.ID_LOCALIDAD " );
			sql.append( "     INNER JOIN " );
			sql.append( "     USUARIOS ON BOLETAS.ID_USUARIO = USUARIOS.IDENTIFICACION AND " );
			sql.append( "                 BOLETAS.ID_TIPO = USUARIOS.TIPO_IDENTIFICACION " );
			sql.append( "   GROUP BY COSTO_LOCALIDAD.FECHA, COSTO_LOCALIDAD.ID_LUGAR, COSTO_LOCALIDAD.ID_LOCALIDAD, " );
			sql.append( "     COSTO, USUARIOS.ROL) B ON LUGARES.ID = B.ID_LUGAR " );
			sql.append( "  INNER JOIN " );
			sql.append( "  LOCALIDADES ON B.ID_LOCALIDAD = LOCALIDADES.ID " );
			sql.append( String.format( "WHERE FECHA = %s ", toDate( fecha ) ) );
			sql.append( String.format( "      AND ID_LUGAR = %s ", idLugar ) );
			sql.append( "ORDER BY FECHA, LUGARES.NOMBRE, LOCALIDAD, TIPO_USUARIO " );
			
			List<RFC3.Localidad> localidades = new LinkedList<>( );
			
			s = connection.prepareStatement( sql.toString( ) );
			rs = s.executeQuery( );
			while( rs.next( ) )
			{
				String localidad = rs.getString( "localidad" );
				String tipoUsuario = rs.getString( "tipo_usuario" );
				Integer boletasVendidas = rs.getInt( "boletas_vendidas" );
				Double totalProducido = rs.getDouble( "total_producido" );
				
				RFC3.Localidad loc = rfc3.new Localidad( );
				loc.setLocalidad( localidad );
				loc.setTipoUsuario( tipoUsuario );
				loc.setBoletasVendidas( boletasVendidas );
				loc.setTotalProducido( totalProducido );
				
				localidades.add( loc );
			}
			rfc3.setLocalidades( localidades );
		}
		rs.close( );
		s.close( );
		return rfc3;
	}
}