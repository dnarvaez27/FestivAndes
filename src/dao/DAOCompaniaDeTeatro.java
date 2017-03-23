package dao;

import vos.CompaniaDeTeatro;
import vos.Usuario;
import vos.reportes.RFC5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DAOCompaniaDeTeatro extends DAO
{
	public DAOCompaniaDeTeatro( )
	{
		super( );
	}
	
	public CompaniaDeTeatro createCompaniaDeTeatro( Long id, String password, CompaniaDeTeatro object ) throws SQLException
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
			sql.append( "INSERT INTO USUARIOS " );
			sql.append( "( identificacion, tipo_identificacion, email, password, nombre, rol )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", object.getTipoIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", object.getEmail( ) ) );
			sql.append( String.format( "'%s', ", object.getPassword( ) ) );
			sql.append( String.format( "'%s', ", object.getNombre( ) ) );
			sql.append( String.format( "'%s' ", Usuario.USUARIO_COMPANIA ) );
			sql.append( "); " );
			
			s = connection.prepareCall( sql.toString( ) );
			recursos.add( s );
			s.execute( );
			
			sql = new StringBuilder( );
			sql.append( "INSERT INTO COMPANIAS_DE_TEATRO " );
			sql.append( "( id, tipo_id, nombre, nombre_representante, pais_origen, pagina_web, hora_llegada, hora_salida ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", object.getId( ) ) );
			sql.append( String.format( "'%s', ", object.getTipoIdentificacion( ) ) );
			sql.append( String.format( "'%s' ", object.getNombre( ) ) );
			sql.append( String.format( "'%s', ", object.getNombreRepresentante( ) ) );
			sql.append( String.format( "'%s', ", object.getPaisOrigen( ) ) );
			sql.append( String.format( "'%s', ", object.getPaginaWeb( ) ) );
			sql.append( String.format( "%s, ", toDate( object.getFechaLlegada( ) ) ) );
			sql.append( String.format( "%s, ", toDate( object.getFechaLlegada( ) ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return object;
	}
	
	public List<CompaniaDeTeatro> getCompaniaDeTeatros( ) throws SQLException
	{
		List<CompaniaDeTeatro> list = new ArrayList<>( );
		
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
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s', ", object.getNombre( ) ) );
		sql.append( String.format( "email = '%s', ", object.getEmail( ) ) );
		sql.append( String.format( "password = '%s' ", object.getPassword( ) ) );
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
		sql.append( String.format( "fecha_llegada = %s, ", toDate( object.getFechaLlegada( ) ) ) );
		sql.append( String.format( "fecha_salida = %s ", toDate( object.getFechaSalida( ) ) ) );
		sql.append( String.format( "WHERE id = %s ", idCompania ) );
		sql.append( String.format( "AND tipo_id = %s", object.getTipoIdentificacion( ) ) );
		
		s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteCompaniaDeTeatro( Long id, String tipoId ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s", id ) );
		sql.append( String.format( "AND tipo_identificacion = '%s'", tipoId ) );
		
		sql.append( "DELETE FROM COMPANIAS_DE_TEATRO " );
		sql.append( String.format( "WHERE id = %s", id ) );
		sql.append( String.format( "AND tipo_id = '%s'", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public List<RFC5> rentabilidad( )
	{
		List<RFC5> list = new LinkedList<>( );
		//TODO
		return list;
	}
	
	public static CompaniaDeTeatro resultToCompaniaDeTeatro( ResultSet rs ) throws SQLException
	{
		CompaniaDeTeatro object = new CompaniaDeTeatro( );
		object.setId( rs.getLong( "id" ) );
		object.setTipoIdentificacion( rs.getString( "tipo_id" ) );
		object.setNombre( rs.getString( "nombre" ) );
		object.setNombreRepresentante( rs.getString( "nombre_representante" ) );
		object.setPaginaWeb( rs.getString( "pagina_web" ) );
		object.setPaisOrigen( rs.getString( "pais_origen" ) );
		object.setFechaLlegada( rs.getDate( "hora_llegada" ) );
		object.setFechaSalida( rs.getDate( "hora_salida" ) );
		object.setEmail( rs.getString( "email" ) );
		object.setPassword( rs.getString( "password" ) );
		object.setRol( rs.getString( "rol" ) );
		return object;
	}
}