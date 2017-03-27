package dao;

import vos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOUsuario extends DAO
{
	public DAOUsuario( )
	{
		super( );
	}
	
	public Usuario createUsuario( Usuario usuario ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO USUARIOS " );
		sql.append( "( identificacion, tipo_identificacion, email, password, nombre, rol, id_festival ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", usuario.getIdentificacion( ) ) );
		sql.append( String.format( "'%s', ", usuario.getTipoIdentificacion( ) ) );
		sql.append( String.format( "'%s', ", usuario.getEmail( ) ) );
		sql.append( String.format( "'%s', ", usuario.getPassword( ) ) );
		sql.append( String.format( "'%s', ", usuario.getNombre( ) ) );
		sql.append( String.format( "'%s', ", usuario.getRol( ) ) );
		sql.append( String.format( "%s ", usuario.getIdFestival( ) ) );
		sql.append( ")" );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return usuario;
	}
	
	public List<Usuario> getUsuarios( ) throws SQLException
	{
		List<Usuario> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM USUARIOS" );
		
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
	
	public Usuario getUsuario( Long identificacion, String tipo ) throws SQLException
	{
		Usuario usuario = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", identificacion ) );
		sql.append( String.format( "  AND tipo_identificacion = '%s' ", tipo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			usuario = restultToAccesibildiad( rs );
		}
		s.close( );
		return usuario;
	}
	
	public Usuario getUsuarioNoRegistrado( ) throws SQLException
	{
		Usuario usuario = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE rol = '%s' ", Usuario.USUARIO_NO_REGISTRADO ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			usuario = restultToAccesibildiad( rs );
		}
		s.close( );
		return usuario;
	}
	
	public Usuario updateUsuario( Long id, Usuario usuario ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE USUARIOS " );
		sql.append( "SET " );
		sql.append( String.format( "email = '%s', ", usuario.getEmail( ) ) );
		sql.append( String.format( "password = '%s', ", usuario.getPassword( ) ) );
		sql.append( String.format( "nombre = '%s', ", usuario.getNombre( ) ) );
		sql.append( String.format( "rol = '%s', ", usuario.getRol( ) ) );
		sql.append( String.format( "id_festival = %s ", usuario.getIdFestival( ) ) );
		sql.append( String.format( "WHERE identificacion = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return usuario;
	}
	
	public void deleteUsuario( Long identificacion, String tipo ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", identificacion ) );
		sql.append( String.format( "AND tipo_identificacion = '%s'", tipo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Usuario restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		Usuario u = new Usuario( );
		u.setIdentificacion( rs.getLong( "identificacion" ) );
		u.setTipoIdentificacion( rs.getString( "tipo_identificacion" ) );
		u.setEmail( rs.getString( "email" ) );
		u.setPassword( rs.getString( "password" ) );
		u.setNombre( rs.getString( "nombre" ) );
		u.setRol( rs.getString( "rol" ) );
		u.setIdFestival( rs.getLong( "id_festival" ) );
		return u;
	}
}