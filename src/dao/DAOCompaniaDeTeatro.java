package dao;

import vos.CompaniaDeTeatro;
import vos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOCompaniaDeTeatro extends DAO
{
	public DAOCompaniaDeTeatro( )
	{
		super( );
	}
	
	public CompaniaDeTeatro createCompaniaDeTeatro( CompaniaDeTeatro object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO USUARIOS " );
		sql.append( "( id_usuario, email, password, nombre, rol )" );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
		sql.append( String.format( "'%s', ", object.getEmail( ) ) );
		sql.append( String.format( "'%s', ", object.getPassword( ) ) );
		sql.append( String.format( "'%s', ", object.getNombre( ) ) );
		sql.append( String.format( "'%s', ", object.getIdentificacion( ) != -1 ? Usuario.USUARIO_REGISTRADO : Usuario.USUARIO_NO_REGISTRADO ) );
		sql.append( "); " );
		
		PreparedStatement s = connection.prepareCall( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		sql = new StringBuilder( );
		sql.append( "INSERT INTO COMPANIAS_DE_TEATRO " );
		sql.append( "( id, nombre, nombre_representante, pagina_web, pais_origen, hora_llegada, hora_salida ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getId( ) ) );
		sql.append( String.format( "'%s' ", object.getNombre( ) ) );
		sql.append( String.format( "'%s', ", object.getNombreRepresentante( ) ) );
		sql.append( String.format( "'%s', ", object.getPaginaWeb( ) ) );
		sql.append( String.format( "'%s', ", object.getPaisOrigen( ) ) );
		sql.append( String.format( "%s, ", object.getHoraLlegada( ) ) );
		sql.append( String.format( "%s, ", object.getHoraLlegada( ) ) );
		sql.append( ")" );
		
		s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public List<CompaniaDeTeatro> getCompaniaDeTeatros( ) throws SQLException
	{
		List<CompaniaDeTeatro> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM COMPANIAS_DE_TEATRO" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToCompaniaDeTeatro( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public CompaniaDeTeatro getCompaniaDeTeatro( Long id ) throws SQLException
	{
		CompaniaDeTeatro object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COMPANIAS_DE_TEATRO " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToCompaniaDeTeatro( rs );
		}
		s.close( );
		return object;
	}
	
	public CompaniaDeTeatro updateCompaniaDeTeatro( Long idCompania, CompaniaDeTeatro object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE USUARIOS " );
		sql.append( String.format( "SET nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "SET nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "SET email = '%s'", object.getEmail( ) ) );
		sql.append( String.format( "SET password = '%s'", object.getPassword( ) ) );
		sql.append( String.format( "WHERE identificacion = %s", idCompania ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		sql = new StringBuilder( );
		sql.append( "UPDATE COMPANIAS_DE_TEATRO " );
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s', ", object.getNombre( ) ) );
		sql.append( String.format( "nombre_representante = '%s', ", object.getNombreRepresentante( ) ) );
		sql.append( String.format( "pagina_web = '%s', ", object.getPaginaWeb( ) ) );
		sql.append( String.format( "pais_origen = '%s', ", object.getPaisOrigen( ) ) );
		sql.append( String.format( "fecha_llegada = %s, ", object.getHoraLlegada( ) ) );
		sql.append( String.format( "fecha_salida = %s ", object.getHoraSalida( ) ) );
		sql.append( String.format( "WHERE id = %s", idCompania ) );
		
		s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteCompaniaDeTeatro( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM COMPANIAS_DE_TEATRO " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static CompaniaDeTeatro resultToCompaniaDeTeatro( ResultSet rs ) throws SQLException
	{
		CompaniaDeTeatro object = new CompaniaDeTeatro( );
		object.setId( rs.getLong( "id" ) );
		object.setNombre( rs.getString( "nombre" ) );
		object.setNombreRepresentante( rs.getString( "nombre_representante" ) );
		object.setPaginaWeb( rs.getString( "pagina_web" ) );
		object.setPaisOrigen( rs.getString( "pais_origen" ) );
		object.setHoraLlegada( rs.getDate( "hora_llegada" ) );
		object.setHoraSalida( rs.getDate( "hora_salida" ) );
		return object;
	}
}