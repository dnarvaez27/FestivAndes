package dao;

import vos.Usuario;
import vos.UsuarioRegistrado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOUsuarioRegistrado extends DAO
{
	public DAOUsuarioRegistrado( )
	{
		super( );
	}
	
	public UsuarioRegistrado createUsuarioRegistrado( Long id, String password, UsuarioRegistrado object ) throws SQLException
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
			sql.append( "INSERT INTO USUARIOS " );
			sql.append( "( id_usuario, email, password, nombre, rol )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", object.getEmail( ) ) );
			sql.append( String.format( "'%s', ", object.getPassword( ) ) );
			sql.append( String.format( "'%s', ", object.getNombre( ) ) );
			sql.append( String.format( "'%s', ", object.getIdentificacion( ) != -1 ? Usuario.USUARIO_REGISTRADO : Usuario.USUARIO_NO_REGISTRADO ) );
			sql.append( "); " );
			
			s = connection.prepareCall( sql.toString( ) );
			recursos.add( s );
			s.execute( );
			
			sql = new StringBuilder( );
			sql.append( "INSERT INTO USUARIOS_REGISTRADO " );
			sql.append( "( id_usuario, edad ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
			sql.append( String.format( "%s ", object.getEdad( ) ) );
			sql.append( "); " );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return object;
	}
	
	public List<UsuarioRegistrado> getUsuarioRegistrados( ) throws SQLException
	{
		List<UsuarioRegistrado> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS_REGISTRADO UR INNER JOIN USUARIOS U" );
		sql.append( "                             ON UR.id_usuario = U.identificacion " );
		
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
	
	public UsuarioRegistrado getUsuarioRegistrado( Long id ) throws SQLException
	{
		UsuarioRegistrado object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS_REGISTRADO UR INNER JOIN USUARIOS U " );
		sql.append( "                          ON UR.id_usuario = U.identificacion " );
		sql.append( String.format( "WHERE id_usuario = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = restultToAccesibildiad( rs );
		}
		s.close( );
		return object;
	}
	
	public UsuarioRegistrado updateUsuarioRegistrado( Long id, UsuarioRegistrado object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE USUARIOS " );
		sql.append( String.format( "SET nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "SET nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "SET email = '%s'", object.getEmail( ) ) );
		sql.append( String.format( "SET password = '%s'", object.getPassword( ) ) );
		sql.append( String.format( "WHERE identificacion = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		sql = new StringBuilder( );
		sql.append( "UPDATE USUARIOS_REGISTRADO " );
		sql.append( String.format( "SET edad = %s", object.getEdad( ) ) );
		sql.append( String.format( "WHERE id_usuario = %s", id ) );
		
		s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		s.close( );
		return object;
	}
	
	public void deleteUsuarioRegistrado( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM USUARIOS_REGISTRADO " );
		sql.append( String.format( "WHERE id_usuario = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		sql = new StringBuilder( );
		sql.append( "DELETE FROM USUARIOS " );
		sql.append( String.format( "WHERE  identificacion = %s", id ) );
		
		s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		s.close( );
	}
	
	public static UsuarioRegistrado restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		UsuarioRegistrado object = new UsuarioRegistrado( );
		object.setEmail( rs.getString( "email" ) );
		object.setPassword( rs.getString( "password" ) );
		object.setNombre( rs.getString( "nombre" ) );
		object.setIdentificacion( rs.getLong( "id_usuario" ) );
		object.setEdad( rs.getInt( "edad" ) );
		return object;
	}
}