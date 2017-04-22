package dao;

import utilities.DateUtils;
import vos.Usuario;
import vos.reportes.RFC7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DAOUsuario extends DAO
{
	public DAOUsuario( )
	{
		super( );
	}
	
	public boolean isUserRole( Long id, String tipo, String password, String... roles ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", id ) );
		sql.append( String.format( "  AND tipo_identificacion = '%s' ", tipo ) );
		sql.append( String.format( "  AND ( " ) );
		// TODO: 10/04/2017
		for( int i = 0; i < roles.length; i++ )
		{
			sql.append( String.format( "rol = '%s' ", roles[ i ] ) );
			if( i + 1 < roles.length )
			{
				sql.append( " OR " );
			}
			sql.append( " ) " );
		}
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		boolean is = rs.next( );
		
		rs.close( );
		s.close( );
		return is;
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
			list.add( resultToUsuario( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Usuario> getUsuariosRol( String rol ) throws SQLException
	{
		List<Usuario> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE ROL = '%s'", rol ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToUsuario( rs ) );
		}
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
			usuario = resultToUsuario( rs );
		}
		rs.close( );
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
			usuario = resultToUsuario( rs );
		}
		rs.close( );
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
		s.close( );
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
	
	private static Usuario resultToUsuario( ResultSet rs ) throws SQLException
	{
		Usuario u = new Usuario( );
		u.setIdentificacion( rs.getLong( "identificacion" ) );
		u.setTipoIdentificacion( rs.getString( "tipo_identificacion" ) );
		u.setEmail( rs.getString( "email" ) );
		u.setPassword( rs.getString( "password" ) );
		u.setNombre( rs.getString( "nombre" ) );
		u.setRol( rs.getString( "rol" ) );
		u.setIdFestival( rs.getLong( "id_festival" ) );
		u.setImagen( rs.getString( "imagen" ) );
		return u;
	}
	
	public List<RFC7> asistenciaUsuariosRegistrados( ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT " );
		sql.append( "  B.FECHA, " );
		sql.append( "  B.ID_LUGAR, " );
		sql.append( "  E.NOMBRE AS NOMBRE_ESPECTACULO, " );
		sql.append( "  COUNT(*) AS TOTAL, " );
		sql.append( "  F.SE_REALIZA, " );
		sql.append( "  F.ID_ESPECTACULO, " );
		sql.append( "  E.DURACION, " );
		sql.append( "  U.IDENTIFICACION, " );
		sql.append( "  U.TIPO_IDENTIFICACION " );
		sql.append( "FROM " );
		sql.append( "  BOLETAS B " );
		sql.append( "  INNER JOIN " );
		sql.append( "  FUNCIONES F " );
		sql.append( "    ON F.FECHA = B.FECHA " );
		sql.append( "       AND F.ID_LUGAR = B.ID_LUGAR " );
		sql.append( "  INNER JOIN " );
		sql.append( "  ESPECTACULOS E " );
		sql.append( "    ON F.ID_ESPECTACULO = E.ID " );
		sql.append( "  INNER JOIN " );
		sql.append( "  USUARIOS U " );
		sql.append( "    ON B.ID_USUARIO = U.IDENTIFICACION " );
		sql.append( "       AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
		sql.append( String.format( "WHERE U.ROL = '%s' ", Usuario.USUARIO_REGISTRADO ) );
		sql.append( "GROUP BY F.FECHA, F.ID_LUGAR, U.IDENTIFICACION, U.TIPO_IDENTIFICACION, B.FECHA, B.ID_LUGAR, E.NOMBRE, F.SE_REALIZA, F.ID_ESPECTACULO, E.DURACION " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		List<RFC7> resp = new LinkedList<>( );
		
		while( rs.next( ) )
		{
			RFC7 req = new RFC7( );
			List<RFC7.FuncionEspectaculo> terminadas = new LinkedList<>( );
			List<RFC7.FuncionEspectaculo> enCurso = new LinkedList<>( );
			List<RFC7.FuncionEspectaculo> previstas = new LinkedList<>( );
			List<RFC7.FuncionEspectaculo> canceladas = new LinkedList<>( );
			RFC7.FuncionEspectaculo f = req.new FuncionEspectaculo( );
			f.setCantidad( rs.getInt( "TOTAL" ) );
			f.setFuncion( DAOFuncion.resultToFuncion( rs ) );
			f.setNombreEspectaculo( rs.getString( "NOMBRE_ESPECTACULO" ) );
			
			if( rs.getInt( "SE_REALIZA" ) == 2 )
			{
				canceladas.add( f );
			}
			else
			{
				Calendar c = Calendar.getInstance( );
				Calendar d = DateUtils.dateToCalendar( rs.getDate( "FECHA" ) );
				Calendar e = DateUtils.dateToCalendar( rs.getDate( "FECHA" ) );
				e.add( Calendar.MINUTE, rs.getInt( "DURACION" ) );
				
				if( c.getTimeInMillis( ) < d.getTimeInMillis( ) )
				{
					previstas.add( f );
				}
				else
				{
					if( c.getTimeInMillis( ) < e.getTimeInMillis( ) )
					{
						enCurso.add( f );
					}
					else
					{
						terminadas.add( f );
					}
				}
			}
			
			req.setCanceladas( canceladas );
			req.setEnCurso( enCurso );
			req.setPrevistas( previstas );
			req.setTerminadas( terminadas );
			req.setIdUsuario( rs.getLong( "IDENTIFICACION" ) );
			req.setTipo( rs.getString( "TIPO_IDENTIFICACION" ) );
			
			resp.add( req );
		}
		return resp;
	}
}