package dao;

import vos.Localidad;
import vos.Lugar;
import vos.Usuario;
import vos.reportes.RFC2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOLugar extends DAO
{
	public DAOLugar( )
	{
		super( );
	}
	
	public Lugar createLugar( Long id, String password, Lugar lugar ) throws SQLException
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
			sql.append( "INSERT INTO LUGARES " );
			sql.append( "( id, nombre, diponibilidad_inicio, disponibilidad_fin, es_abierto, tipo_lugar ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", lugar.getId( ) ) );
			sql.append( String.format( "'%s', ", lugar.getNombre( ) ) );
			sql.append( String.format( "%s, ", toDate( lugar.getDisponibilidadInicio( ) ) ) );
			sql.append( String.format( "%s, ", toDate( lugar.getDisponibilidadFin( ) ) ) );
			sql.append( String.format( "%s, ", lugar.getEsAbierto( ) ) );
			sql.append( String.format( "'%s' ", lugar.getTipo( ) ) );
			sql.append( ")" );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		s.close( );
		return lugar;
	}
	
	public List<Lugar> getLugares( ) throws SQLException
	{
		List<Lugar> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM LUGARES" );
		
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
	
	public Lugar getLugar( Long id ) throws SQLException
	{
		Lugar lugar = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM LUGARES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			lugar = restultToAccesibildiad( rs );
		}
		s.close( );
		return lugar;
	}
	
	public Lugar updateLugar( Long id, Lugar lugar ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE LUGARES " );
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s', ", lugar.getNombre( ) ) );
		sql.append( String.format( "disponibilidad_inicio = %s, ", toDate( lugar.getDisponibilidadInicio( ) ) ) );
		sql.append( String.format( "disponibilidad_fin = %s, ", toDate( lugar.getDisponibilidadFin( ) ) ) );
		sql.append( String.format( "es_abierto = %s, ", lugar.getEsAbierto( ) ) );
		sql.append( String.format( "tipo = '%s', ", lugar.getTipo( ) ) );
		sql.append( String.format( "WHERE id = %s ", lugar.getId( ) ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return lugar;
	}
	
	public void deleteLugar( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM LUGARES " );
		sql.append( String.format( "WHERE id = %s", id ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Lugar restultToAccesibildiad( ResultSet rs ) throws SQLException
	{
		Lugar l = new Lugar( );
		l.setId( rs.getLong( "id" ) );
		l.setNombre( rs.getString( "nombre" ) );
		l.setDisponibilidadInicio( rs.getDate( "disponibilidad_inicio" ) );
		l.setDisponibilidadFin( rs.getDate( "disponibilidad_fin" ) );
		l.setEsAbierto( rs.getInt( "es_abierto" ) );
		l.setTipo( rs.getString( "tipo" ) );
		return l;
	}
	
	public List<RFC2> consultaRFC2( String nombre, String tipoEspacio, Integer capacidad ) throws SQLException
	{
		StringBuilder sqlSitio = new StringBuilder( );
		sqlSitio.append( "SELECT * " );
		sqlSitio.append( "FROM LUGARES " );
		
		if( nombre != null && !nombre.isEmpty( ) )
		{
			sqlSitio.append( "WHERE NOMBRE = '" + nombre + "'" );
			if( tipoEspacio != null && !tipoEspacio.isEmpty( ) )
			{
				sqlSitio.append( "AND TIPO_ESPACIO = '" + tipoEspacio + "'" );
			}
			if( capacidad != null )
			{
				sqlSitio.append( "AND CAPACIDAD = " + capacidad );
			}
		}
		else if( tipoEspacio != null && !tipoEspacio.isEmpty( ) )
		{
			sqlSitio.append( "WHERE TIPO_ESPACIO = '" + tipoEspacio + "'" );
			if( capacidad != null )
			{
				sqlSitio.append( "AND CAPACIDAD = " + capacidad );
			}
		}
		else if( capacidad != null )
		{
			sqlSitio.append( "WHERE CAPACIDAD = " + capacidad );
		}
		
		PreparedStatement prepStmt1 = connection.prepareStatement( sqlSitio.toString( ) );
		recursos.add( prepStmt1 );
		ResultSet rs = prepStmt1.executeQuery( );
		
		List<RFC2> respuesta = new ArrayList<>( );
		
		while( rs.next( ) )
		{
			Lugar lugar = restultToAccesibildiad(rs );
			
			StringBuilder sql = new StringBuilder( );
			sql.append( "" )
					
			
			
			sql.append( "SELECT * FROM(" );
			sql.append( "SELECT * FROM(" );
			sql.append( "(SELECT * " );
			sql.append( "FROM FUNCION" );
			sql.append( String.format( "WHERE ID_SITIO = %s )", s.getId( ) ) );
			sql.append( "NATURAL INNER JOIN" );
			sql.append( "(SELECT * " );
			sql.append( "FROM FUNCION_COSTO_LOCALIDAD))" );
			sql.append( "NATURAL INNER JOIN " );
			sql.append( "(SELECT * " );
			sql.append( "FROM ESPECTACULO))" );
			sql.append( "ORDER BY ID_ESPECTACULO, ID_FUNCION" );
			
			PreparedStatement prepStmt = connection.prepareStatement( sql.toString( ) );
			recursos.add( prepStmt );
			ResultSet rs = prepStmt.executeQuery( );
			
			List<EspectaculoDetail> espectaculos = new ArrayList( );
			
			Long idEspectaculo = ( long ) -1;
			Long idFuncion = ( long ) -1;
			List<LocalidadCosto> localidadesC = new ArrayList( );
			List<FuncionDetail> funciones = new ArrayList( );
			while( rs.next( ) )
			{
				if( idEspectaculo != rs.getLong( "ID_ESPECTACULO" ) )
				{
					idEspectaculo = rs.getLong( "ID_ESPECTACULO" );
					
					String nombreE = rs.getString( "NOMBRE" );
					Integer duracion = rs.getInt( "DURACION" );
					String idioma = rs.getString( "IDIOMA" );
					Float costoRealizacion = rs.getFloat( "COSTO_REALIZACION" );
					Boolean participacionPublico = rs.getBoolean( "PARTICIPACION_PUBLICO" );
					String descripcion = rs.getString( "PARTICIPACION" );
					Long idFestival = rs.getLong( "ID_FESTIVAL" );
					Long idPublicoObjetivo = rs.getLong( "ID_PUBLICO_OBJETIVO" );
					
					funciones = new ArrayList( );
					
					EspectaculoDetail e = new EspectaculoDetail( idEspectaculo, nombreE, duracion, idioma, costoRealizacion, descripcion, idFestival, idPublicoObjetivo, funciones );
					espectaculos.add( e );
				}
				if( idFuncion != rs.getLong( "ID_FUNCION" ) )
				{
					idFuncion = rs.getLong( "ID_FUNCION" );
					
					Date fecha = rs.getDate( "FECHA" );
					Boolean realizadoConExito = rs.getBoolean( "REALIZADO_CON_EXITO" );
					
					localidadesC = new ArrayList( );
					FuncionDetail f = new FuncionDetail( idFuncion, fecha, idEspectaculo, s.getId( ), realizadoConExito, localidadesC );
					funciones.add( f );
				}
				
				Localidad l = traerLocalidad( rs.getLong( "ID_LOCALIDAD" ) );
				Double costo = rs.getDouble( "COSTO" );
				Integer boleteria = boleteriaDisponible( idFuncion, l.getId( ), s.getId( ) );
				LocalidadCosto locC = new LocalidadCosto( l, costo, boleteria );
				
				localidadesC.add( locC );
			}
			
			RFC2 nuevo = new RFC2( s, espectaculos );
			respuesta.add( nuevo );
			
		}
		
		return respuesta;
	}
}