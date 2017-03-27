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
	
	public UsuarioRegistrado createUsuarioRegistrado( UsuarioRegistrado object, Long id, String password ) throws SQLException
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
			sql.append( "INSERT INTO USUARIOS_REGISTRADOS " );
			sql.append( "( id_usuario, tipo_id, edad ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", object.getTipoIdentificacion( ) ) );
			sql.append( String.format( "%s ", object.getEdad( ) ) );
			sql.append( ") " );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		else
		{
			throw new SQLException( "No tiene permisos para agregar usuarios" );
		}
		s.close( );
		return object;
	}
	
	public List<UsuarioRegistrado> getUsuarioRegistrados( ) throws SQLException
	{
		List<UsuarioRegistrado> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS_REGISTRADOS UR INNER JOIN USUARIOS U" );
		sql.append( "                             ON UR.id_usuario = U.identificacion " );
		sql.append( "                             AND UR.tipo_id = U.tipo_identificacion " );
		
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
	
	public UsuarioRegistrado getUsuarioRegistrado( Long id, String tipoId ) throws SQLException
	{
		UsuarioRegistrado object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS_REGISTRADOS UR INNER JOIN USUARIOS U " );
		sql.append( "                             ON UR.id_usuario = U.identificacion " );
		sql.append( "                             AND UR.tipo_id = U.tipo_identificacion " );
		sql.append( String.format( "WHERE id_usuario = %s ", id ) );
		sql.append( String.format( "AND tipo_id = '%s' ", tipoId ) );
		
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
	
	public UsuarioRegistrado updateUsuarioRegistrado( Long id, String tipo, UsuarioRegistrado object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE USUARIOS " );
		sql.append( String.format( "SET nombre = '%s' ", object.getNombre( ) ) );
		sql.append( String.format( "SET email = '%s' ", object.getEmail( ) ) );
		sql.append( String.format( "SET password = '%s' ", object.getPassword( ) ) );
		sql.append( String.format( "WHERE identificacion = %s ", id ) );
		sql.append( String.format( "AND tipo_identificacion = '%s' ", tipo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		sql = new StringBuilder( );
		sql.append( "UPDATE USUARIOS_REGISTRADOS " );
		sql.append( String.format( "SET edad = %s ", object.getEdad( ) ) );
		sql.append( String.format( "WHERE id_usuario = %s ", id ) );
		sql.append( String.format( "AND tipo_id = '%s' ", tipo ) );
		
		s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		s.close( );
		return object;
	}
	
	public void deleteUsuarioRegistrado( Long id, String tipoId ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM USUARIOS_REGISTRADOS " );
		sql.append( String.format( "WHERE id_usuario = %s", id ) );
		sql.append( String.format( "AND tipo_id = '%s'", tipoId ) );
		
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
		object.setTipoIdentificacion( rs.getString( "tipo_id" ) );
		object.setEdad( rs.getInt( "edad" ) );
		object.setIdFestival( rs.getLong( "id_festival" ) );
		object.setRol( rs.getString( "rol" ) );
		return object;
	}
}