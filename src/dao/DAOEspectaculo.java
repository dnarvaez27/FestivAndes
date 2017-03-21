package dao;

import vos.Espectaculo;

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
}