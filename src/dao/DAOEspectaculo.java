package dao;

import vos.Espectaculo;
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
		sql.append( String.format( "%s, ", object.getIdClasificacion( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
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
		sql.append( "  E.NOMBRE," );
		sql.append( "  B.ID_FUNCION," );
		sql.append( "  S.NOMBRE,             AS LUGAR" );
		sql.append( "  Lo.NOMBRE             AS LOCALIDAD," );
		sql.append( "  U.ROL                 AS TIPO_USUARIO," );
		sql.append( "  COUNT( B.ID_FUNCION ) AS BOLETAS_VENDIDAS," );
		sql.append( "  SUM( FCL.COSTO )      AS TOTAL_VENDIDO" );
		sql.append( "FROM ESPECTACULO E, BOLETA B, FUNCION_COSTO_LOCALIDAD FCL, USUARIO U, LOCALIDAD Lo, FUNCION F, SITIO S " );
		sql.append( "WHERE E.ID_ESPECTACULO = F.ID_ESPECTACULO" );
		sql.append( "      AND F.ID_FUNCION = FCL.ID_FUNCION" );
		sql.append( "      AND B.ID_LOCALIDAD = FCL.ID_LOCALIDAD" );
		sql.append( "      AND B.ID_FUNCION = FCL.ID_FUNCION" );
		sql.append( "      AND B.ID_USUARIO = U.ID_USUARIO" );
		sql.append( "      AND B.ID_USUARIO_TIPO = U.TIPO_ID" );
		sql.append( "      AND Lo.ID_LOCALIDAD = B.ID_LOCALIDAD" );
		sql.append( "      AND S.ID_SITIO = F.ID_SITIO" );
		sql.append( String.format( "      AND E.ID_ESPECTACULO = %s ", idEspectaculo ) );
		sql.append( "GROUP BY E.NOMBRE, B.ID_FUNCION, S.NOMBRE, Lo.NOMBRE, B.ID_LOCALIDAD, ROL" );
		sql.append( "ORDER BY E.NOMBRE, ID_FUNCION, ID_LOCALIDAD;" );
		
		PreparedStatement prepStmt1 = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt1 );
		
		ResultSet rs = prepStmt1.executeQuery( );
		while( rs.next( ) )
		{
			RFC4.P1 p1 = req.new P1( );
			
			String nombreEspectaculo = rs.getString( "NOMBRE" );
			Long idFuncion = rs.getLong( "ID_FUNCION" );
			String lugar = rs.getString( "LUGAR" );
			String nombreLocalidad = rs.getString( "LOCALIDAD" );
			String tipoUsuario = rs.getString( "TIPO_USUARIO" );
			int numBoletasVendidas = rs.getInt( "BOLETAS_VENDIDAS" );
			double totalVendido = rs.getDouble( "TOTAL_VENDIDO" );
			
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
		sql.append( "  C.ID_SITIO," );
		sql.append( "  ( OCUPADOS * 100 / TOTAL ) AS PORCENTAJE_DE_OCUPACION" );
		sql.append( "FROM ( SELECT" );
		sql.append( "         ID_SITIO," );
		sql.append( "         SUM( CAPACIDAD ) TOTAL" );
		sql.append( "       FROM SITIO_LOCALIDAD" );
		sql.append( "       GROUP BY ID_SITIO ) C" );
		sql.append( "  INNER JOIN ( SELECT" );
		sql.append( "                 ID_SITIO," );
		sql.append( "                 COUNT( ID_SITIO ) OCUPADOS" );
		sql.append( "               FROM BOLETA" );
		sql.append( "               GROUP BY ID_SITIO ) O" );
		sql.append( "    ON C.ID_SITIO = O.ID_SITIO;" );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt2 );
		
		rs = prepStmt2.executeQuery( );
		while( rs.next( ) )
		{
			RFC4.P2 p2 = req.new P2( );
			
			Long idLugar = rs.getLong( "ID_SITIO" );
			Double porcentajeOcupacion = rs.getDouble( "PORCENTAJE_DE_OCUPACION" );
			
			p2.setIdLugar( idLugar );
			p2.setPorcentajeOcupacion( porcentajeOcupacion );
			
			p2s.add( p2 );
		}
		
		req.setP1( p1s );
		req.setP2( p2s );
		
		return req;
	}
}