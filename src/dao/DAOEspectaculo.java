package dao;

import vos.Espectaculo;
import vos.Usuario;
import vos.reportes.RFC4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOEspectaculo extends DAO
{
	public DAOEspectaculo( )
	{
		super( );
	}
	
	public Espectaculo createEspectaculo( Long id, String password, Espectaculo object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s", id ) );
		sql.append( String.format( "AND password = '%s' ", password ) );
		sql.append( String.format( "AND rol = '%s' ", Usuario.USUARIO_ADMINISTRADOR ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			sql = new StringBuilder( );
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
			sql.append( String.format( "%s, ", object.getIdClasificacion( ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return object;
	}
	
	public List<Espectaculo> getEspectaculos( ) throws SQLException
	{
		List<Espectaculo> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM ESPECTACULOS" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToEspectaculo( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Espectaculo getEspectaculo( Long id ) throws SQLException
	{
		Espectaculo object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM ESPECTACULOS " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToEspectaculo( rs );
		}
		s.close( );
		return object;
	}
	
	public Espectaculo updateEspectaculo( Long id, Espectaculo object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE ESPECTACULOS " );
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "duracion = '%s'", object.getDuracion( ) ) );
		sql.append( String.format( "idioma = '%s'", object.getIdioma( ) ) );
		sql.append( String.format( "costo_realizacion = '%s'", object.getCostoRealizacion( ) ) );
		sql.append( String.format( "descripcion = '%s'", object.getDescripcion( ) ) );
		sql.append( String.format( "id_festival= '%s'", object.getIdFestival( ) ) );
		sql.append( String.format( "id_clasificacion = '%s'", object.getIdClasificacion( ) ) );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteEspectaculo( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ESPECTACULOS " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
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
		object.setCostoRealizacion( rs.getFloat( "costo_duracion" ) );
		object.setDescripcion( rs.getString( "descripcion" ) );
		object.setIdFestival( rs.getLong( "id_festival" ) );
		object.setIdClasificacion( rs.getLong( "id_clasificacion" ) );
		return object;
	}
	
	/**
	 * RFC4
	 *
	 * @return
	 * @throws SQLException
	 */
	public RFC4 generarReporte( Long idEspectaculo ) throws SQLException
	{
		RFC4 req = new RFC4( );
		List<RFC4.P1> p1s = new LinkedList<>( );
		List<RFC4.P2> p2s = new LinkedList<>( );
		
		// P1
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  E.nombre," );
		sql.append( "  B.id_funcion," );
		sql.append( "  S.nombre,             AS LUGAR" );
		sql.append( "  Lo.nombre             AS LOCALIDAD," );
		sql.append( "  U.rol                 AS TIPO_USUARIO," );
		sql.append( "  COUNT( B.id_funcion ) AS BOLETAS_VENDIDAS," );
		sql.append( "  SUM( FCL.costo )      AS TOTAL_VENDIDO" );
		sql.append( "FROM ESPECTACULO E, BOLETA B, FUNCION_COSTO_LOCALIDAD FCL, USUARIO U, LOCALIDAD Lo, FUNCION F, SITIO S " );
		sql.append( "WHERE E.id_espectaculo = F.id_espectaculo" );
		sql.append( "      AND F.id_funcion = FCL.id_funcion" );
		sql.append( "      AND B.id_localidad = FCL.id_localidad" );
		sql.append( "      AND B.id_funcion = FCL.id_funcion" );
		sql.append( "      AND B.id_usuario = U.identificacion" );
		sql.append( "      AND B.id_usuario_tipo = U.tipo_identificacion" );
		sql.append( "      AND Lo.id_localidad = B.id_localidad" );
		sql.append( "      AND S.id_lugar = F.id_lugar" );
		sql.append( String.format( "      AND E.id_espectaculo = %s ", idEspectaculo ) );
		sql.append( "GROUP BY E.nombre, B.id_funcion, S.nombre, Lo.nombre, B.id_localidad, rol" );
		sql.append( "ORDER BY E.nombre, id_funcion, id_localidad" );
		
		PreparedStatement prepStmt1 = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt1 );
		
		ResultSet rs = prepStmt1.executeQuery( );
		while( rs.next( ) )
		{
			RFC4.P1 p1 = req.new P1( );
			
			String nombreEspectaculo = rs.getString( "nombre" );
			Long idFuncion = rs.getLong( "id_funcion" );
			String lugar = rs.getString( "lugar" );
			String nombreLocalidad = rs.getString( "localidad" );
			String tipoUsuario = rs.getString( "tipo_usuario" );
			int numBoletasVendidas = rs.getInt( "boletas_vendidas" );
			double totalVendido = rs.getDouble( "total_vendido" );
			
			p1.setNombreEspectaculo( nombreEspectaculo );
			p1.setIdFuncion( idFuncion );
			p1.setLugar( lugar );
			p1.setNombreLocalidad( nombreLocalidad );
			p1.setTipoUsuario( tipoUsuario );
			p1.setNumBoletasVendidas( numBoletasVendidas );
			p1.setTotalVendido( totalVendido );
			
			p1s.add( p1 );
		}
		// TODO Esoectaculo id ???
		// P2
		sql = new StringBuilder( );
		sql.append( "SELECT" );
		sql.append( "  C.id_lugar," );
		sql.append( "  ( OCUPADOS * 100 / TOTAL ) AS PORCENTAJE_DE_OCUPACION" );
		sql.append( "FROM ( SELECT" );
		sql.append( "         id_lugar," );
		sql.append( "         SUM( CAPACIDAD ) TOTAL" );
		sql.append( "       FROM LUGAR_LOCALIDAD" );
		sql.append( "       GROUP BY id_lugar ) C" );
		sql.append( "  INNER JOIN ( SELECT" );
		sql.append( "                 id_lugar," );
		sql.append( "                 COUNT( id_lugar ) OCUPADOS" );
		sql.append( "               FROM BOLETA" );
		sql.append( "               GROUP BY id_lugar ) O" );
		sql.append( "    ON C.id_lugar = O.id_lugar" );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt2 );
		
		rs = prepStmt2.executeQuery( );
		while( rs.next( ) )
		{
			RFC4.P2 p2 = req.new P2( );
			
			Long idLugar = rs.getLong( "id_lugar" );
			Double porcentajeOcupacion = rs.getDouble( "porcentaje_de_ocupacion" );
			
			p2.setIdLugar( idLugar );
			p2.setPorcentajeOcupacion( porcentajeOcupacion );
			
			p2s.add( p2 );
		}
		
		req.setP1( p1s );
		req.setP2( p2s );
		
		return req;
	}
}