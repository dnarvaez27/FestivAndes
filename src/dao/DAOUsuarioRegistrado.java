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
	
	public UsuarioRegistrado createUsuarioRegistrado( UsuarioRegistrado object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO USUARIOS_REGISTRADO " );
		sql.append( "( id_usuario, email, password, nombre, rol, edad ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
		sql.append( String.format( "'%s', ", object.getEmail( ) ) );
		sql.append( String.format( "'%s', ", object.getPassword( ) ) );
		sql.append( String.format( "'%s', ", object.getNombre( ) ) );
		sql.append( String.format( "'%s', ", object.getIdentificacion( ) != -1 ? Usuario.USUARIO_REGISTRADO : Usuario.USUARIO_NO_REGISTRADO ) );
		sql.append( String.format( "%s ", object.getEdad( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public List<UsuarioRegistrado> getUsuarioRegistrados( ) throws SQLException
	{
		List<UsuarioRegistrado> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM USUARIOS_REGISTRADO " );
		
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
		sql.append( "FROM USUARIOS_REGISTRADO " );
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
		sql.append( "UPDATE USUARIOS_REGISTRADO " );
		sql.append( String.format( "SET nombre = '%s'", object.getNombre( ) ) );
		sql.append( String.format( "SET email = '%s'", object.getEmail( ) ) );
		sql.append( String.format( "SET password = '%s'", object.getPassword( ) ) );
		sql.append( String.format( "SET edad = %s", object.getEdad( ) ) );
		sql.append( String.format( "WHERE identificacion = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteUsuarioRegistrado( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM USUARIOS_REGISTRADO " );
		sql.append( String.format( "WHERE identificacion = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	private UsuarioRegistrado restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		UsuarioRegistrado object = new UsuarioRegistrado( );
		object.setEmail( rs.getString( "EMAIL" ) );
		object.setPassword( rs.getString( "PASSWORD" ) );
		object.setNombre( rs.getString( "NOMBRE" ) );
		object.setIdentificacion( rs.getLong( "IDENTIFICACION" ) );
		object.setEdad( rs.getInt( "EDAD" ) );
		return object;
	}
}