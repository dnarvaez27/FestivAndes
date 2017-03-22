package dao;

import vos.Funcion;
import vos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOFuncion extends DAO
{
	public DAOFuncion( )
	{
		super( );
	}
	
	public Funcion createFuncion( Long id, String password, Funcion funcion ) throws SQLException
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
			sql.append( "INSERT INTO FUNCIONES " );
			sql.append( "( fecha, id_lugar, id_espectaculo, se_realiza ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", toDate( funcion.getFecha( ) ) ) );
			sql.append( String.format( "%s, ", funcion.getIdLugar( ) ) );
			sql.append( String.format( "%s, ", funcion.getIdEspectaculo( ) ) );
			sql.append( String.format( "%s ", funcion.getSeRealiza( ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return funcion;
	}
	
	public List<Funcion> getFunciones( ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM FUNCIONES" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToFuncion( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Funcion getFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		Funcion funcion = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM FUNCIONES " );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s ", toDate( fecha ) ) );
		sql.append( "AND" );
		sql.append( String.format( "id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			funcion = resultToFuncion( rs );
		}
		s.close( );
		return funcion;
	}
	
	public Funcion updateFuncion( Long idUsuario, String password, Date fecha, Long idLugar, Funcion funcion ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", idUsuario ) );
		sql.append( String.format( "AND password = '%s' ", password ) );
		sql.append( String.format( "AND rol = '%s'", Usuario.USUARIO_ORGANIZADOR ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		
		if( rs.next( ) )
		{
			sql = new StringBuilder( );
			sql.append( "UPDATE FUNCIONES " );
			sql.append( "SET " );
			sql.append( String.format( "fecha = %s, ", toDate( funcion.getFecha( ) ) ) );
			sql.append( String.format( "id_lugar = '%s', ", funcion.getIdLugar( ) ) );
			sql.append( String.format( "se_realiza = %s ", funcion.getSeRealiza( ) ) );
			sql.append( "WHERE " );
			sql.append( String.format( "fecha = %s ", toDate( fecha ) ) );
			sql.append( "AND" );
			sql.append( String.format( "id_lugar = %s", idLugar ) );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return funcion;
	}
	
	public void deleteFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM FUNCIONES " );
		sql.append( "WHERE " );
		sql.append( String.format( "fecha = %s ", toDate( fecha ) ) );
		sql.append( "AND" );
		sql.append( String.format( "id_lugar = %s", idLugar ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	private Funcion resultToFuncion( ResultSet rs ) throws SQLException
	{
		Funcion funcion = new Funcion( );
		funcion.setFecha( rs.getDate( "fecha" ) );
		funcion.setIdLugar( rs.getLong( "id_lugar" ) );
		funcion.setIdEspectaculo( rs.getLong( "id_espectaculo" ) );
		funcion.setSeRealiza( rs.getInt( "se_realiza" ) );
		return funcion;
	}
	
	public List<Funcion> RFC1( String nombreCategoria, String nombreCompania, String ciudad, String pais, String nombreEspectaculo, String idioma, Date fechaInicio, Date fechaFin, Integer duracionInicio, Integer duracionFin, String lugar, String accesoEspecial, String publicoObjetivo, String order, boolean asc ) throws SQLException
	{
		List<Funcion> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM CATEGORIA, COMPANIA_TEATRO, ESPECTACULO, FUNCION, OFRECEN, ESPECTACULO_CATEGORIAS, SITIO, ACCESO_ESPECIAL, SITIO_ACCESO_ESPECIAL, PUBLICO_OBJETIVO " );
		sql.append( "WHERE COMPANIA_TEATRO.ID_COMPANIA = OFRECEN.ID_COMPANIA_TEATRO " );
		sql.append( "AND ESPECTACULO.ID_ESPECTACULO = OFRECEN.ID_ESPECTACULO " );
		sql.append( "AND ESPECTACULO.ID_ESPECTACULO = ESPECTACULO_GENERO.id_espectaculo " );
		sql.append( "AND CATEGORIA.ID_CATEGORIA = ESPECTACULO_CATEGORIAS.ID_CATEGORIA " );
		sql.append( "AND FUNCION.ID_ESPECTACULO = ESPECTACULO.ID_ESPECTACULO " );
		sql.append( "AND SITIO.ID_SITIO = FUNCIONES.ID_SITIO " );
		sql.append( "AND SITIO.ID_SITIO = SITIO_ACCESO_ESPECIAL.ID_SITIO " );
		sql.append( "AND ACCESO_ESPECIAL.ID_ACCESO_ESPECIAL = SITIO_ACCESO_ESPECIAL.ID_ACCESO_ESPECIAL " );
		sql.append( "AND ESPECTACULO.ID_PUBLICO_OBJETIVO = PUBLICO_OBJETIVO.ID_PUBLICO_OBJETIVO " );
		
		sql.append( ciudad != null && !ciudad.isEmpty( ) ? String.format( "AND FESTIVALES.NOMBRE = '%s' ", ciudad ) : "" );
		sql.append( nombreCategoria != null && !nombreCategoria.isEmpty( ) ? String.format( "AND CATEGORIA.NOMBRE = '%s' ", nombreCategoria ) : "" );
		sql.append( nombreCompania != null && !nombreCompania.isEmpty( ) ? String.format( "AND COMPANIA_TEATRO.NOMBRE = '%s' ", nombreCompania ) : "" );
		sql.append( pais != null && !pais.isEmpty( ) ? String.format( "AND COMPANIA_TEATRO.PAIS = '%s' ", pais ) : "" );
		sql.append( nombreEspectaculo != null && !nombreEspectaculo.isEmpty( ) ? String.format( "AND ESPECTACULO.NOMBRE = '%s' ", nombreEspectaculo ) : "" );
		sql.append( idioma != null && !idioma.isEmpty( ) ? String.format( "AND ESPECTACULO.IDIOMA = '%s' ", idioma ) : "" );
		sql.append( fechaInicio != null && fechaFin != null ? String.format( "AND FUNCION.FECHA BETWEEN %s AND %s", toDate( fechaInicio ), toDate( fechaFin ) ) : "" );
		sql.append( duracionInicio != null && duracionFin != null ? String.format( "AND ESPECTACULO.DURACION BETWEEN %s AND %s ", duracionInicio, duracionFin ) : "" );
		sql.append( lugar != null && !lugar.isEmpty( ) ? String.format( "AND LUGARES.nombre = '%s' ", lugar ) : "" );
		sql.append( accesoEspecial != null && !accesoEspecial.isEmpty( ) ? String.format( "AND ACCESO_ESPECIAL.TIPO_ACCESO = '%s'", accesoEspecial ) : "" );
		sql.append( publicoObjetivo != null && !publicoObjetivo.isEmpty( ) ? String.format( "AND PUBLICO_OBJETIVO.TIPO = '%s'", publicoObjetivo ) : "" );
		
		if( order != null )
		{
			sql.append( "ORDER BY " );
			String[] ord = order.split( "_" );
			
			for( int i = 0; i < ord.length; i++ )
			{
				sql.append( ord[ i ] );
				sql.append( i + 1 != ord.length ? ", " : " " );
			}
			sql.append( asc ? "ASC" : "DESC" );
		}
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToFuncion( rs ) );
		}
		rs.close( );
		s.close( );
		return list;
	}
}