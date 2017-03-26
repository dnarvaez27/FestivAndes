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
		sql.append( "SELECT " );
		sql.append( "       GENEROS.nombre                      AS genero, " );
		sql.append( "       COMPANIAS_DE_TEATRO.nombre          AS compania, " );
		sql.append( "       COMPANIAS_DE_TEATRO.pais_origen     AS pais, " );
		sql.append( "       ESPECTACULOS.nombre                 AS espectaculo, " );
		sql.append( "       LUGARES.nombre                      AS lugar, " );
		sql.append( "       ACCESIBILIDADES.nombre              AS accesos, " );
		sql.append( "       CLASIFICACIONES.nombre              AS clasificacion " );
		sql.append( "FROM " );
		sql.append( "   GENEROS, COMPANIAS_DE_TEATRO, ESPECTACULOS, FUNCIONES, OFRECE, ESPECTACULO_GENEROS, LUGARES, ACCESIBILIDADES, LUGAR_ACCESIBILIDAD, CLASIFICACIONES, FESTIVALES " );
		sql.append( "WHERE COMPANIAS_DE_TEATRO.id = OFRECE.id_compania_de_teatro " );
		sql.append( "  AND ESPECTACULOS.id = OFRECE.id_espectaculo " );
		sql.append( "  AND ESPECTACULOS.id = ESPECTACULO_GENEROS.id_espectaculo " );
		sql.append( "  AND GENEROS.id = ESPECTACULO_GENEROS.id_genero " );
		sql.append( "  AND FUNCIONES.id_espectaculo = ESPECTACULOS.id " );
		sql.append( "  AND LUGARES.id = FUNCIONES.id_lugar " );
		sql.append( "  AND LUGARES.id = LUGAR_ACCESIBILIDAD.id_lugar " );
		sql.append( "  AND ACCESIBILIDADES.id = LUGAR_ACCESIBILIDAD.id_accesibilidad " );
		sql.append( "  AND ESPECTACULOS.id_clasificacion = CLASIFICACIONES.id " );
		
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
				sql.append( s + ", " );
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
	
	public RFC3 RFC3( ) throws SQLException
	{
		RFC3 req = new RFC3( );
		List<RFC3> requerimiento = new LinkedList<>( );
		String sql = "SELECT\n" + "  E.NOMBRE,\n" + "  B.FECHA,\n" + "  B.ID_LUGAR,\n" + "  L.NOMBRE              AS LUGAR,\n" + "  Lo.TIPO             AS LOCALIDAD,\n" + "  U.ROL                 AS TIPO_USUARIO,\n" + "  B.ID_LOCALIDAD        AS LOCALIDAD,\n" + "  --COUNT( B.ID_FUNCION ) AS BOLETAS_VENDIDAS,\n" + "  SUM( FCL.COSTO )      AS TOTAL_VENDIDO\n" + "FROM ESPECTACULOS E, BOLETAS B, COSTO_LOCALIDAD FCL, USUARIOS U, LOCALIDADES Lo, FUNCIONES F, LUGARES L\n" + "WHERE B.ID_LOCALIDAD = FCL.ID_LOCALIDAD\n" + "      AND B.FECHA = FCL.FECHA\n" + "      AND B.ID_LUGAR = FCL.ID_LUGAR\n" + "      AND B.ID_TIPO = U.TIPO_IDENTIFICACION\n" + "      AND B.ID_LUGAR = F.ID_LUGAR\n" + "GROUP BY E.NOMBRE, B.FECHA, B.ID_LUGAR, L.NOMBRE, Lo.TIPO, B.ID_LOCALIDAD, ROL\n" + "ORDER BY E.NOMBRE, B.FECHA, B.ID_LUGAR, B.ID_LOCALIDAD";
		PreparedStatement prepStmt1 = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt1 );
		
		ResultSet rs = prepStmt1.executeQuery( );
		while( rs.next( ) )
		{
			RFC3 rest = new RFC3( );
			
			String nombreEspectaculo = rs.getString( "NOMBRE" );
			Long idFuncion = rs.getLong( "ID_FUNCION" );
			String lugar = rs.getString( "LUGAR" );
			String nombreLocalidad = rs.getString( "LOCALIDAD" );
			String tipoUsuario = rs.getString( "TIPO_USUARIO" );
			int numBoletasVendidas = rs.getInt( "BOLETAS_VENDIDAS" );
			double totalVendido = rs.getDouble( "TOTAL_VENDIDO" );
			
			rest.setNombreEspectaculo( nombreEspectaculo );
			rest.setIdFuncion( idFuncion );
			rest.setLugar( lugar );
			rest.setNombreLocalidad( nombreLocalidad );
			rest.setTipoUsuario( tipoUsuario );
			rest.setNumBoletasVendidas( numBoletasVendidas );
			rest.setTotalVendido( totalVendido );
			
			requerimiento.add( rest );
		}
		req.setReq( requerimiento );
		return req;
	}
}