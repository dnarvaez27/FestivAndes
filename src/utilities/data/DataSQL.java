package utilities.data;

import vos.*;
import vos.intermediate.CostoLocalidad;
import vos.intermediate.LugarLocalidad;

import java.util.Date;

import static utilities.SQLUtils.DateUtils;
import static utilities.SQLUtils.DateUtils.toDateTime;

/**
 * Created by dnarv on 3/05/2017.
 */
public class DataSQL
{
	public static String formatSQL( String sql )
	{
		return sql.replace( "'", "''" );
	}
	
	public static class Insert
	{
		public static String abono( Abono abono )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO ABONO " );
			sBuilder.append( "(ID_FESTIVAL, ID_USUARIO, TIPO_ID, DESCUENTO) " );
			sBuilder.append( "VALUES ( " );
			sBuilder.append( String.format( "%s, ", abono.getIdFestival( ) ) );
			sBuilder.append( String.format( "%s, ", abono.getIdUsuario( ) ) );
			sBuilder.append( String.format( "'%s', ", formatSQL( abono.getTipoId( ) ) ) );
			sBuilder.append( String.format( "%s ", abono.getDescuento( ) ) );
			sBuilder.append( ") " );
			return sBuilder.toString( );
		}
		
		public static String accesibilidad( Accesibilidad accesibilidad )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO ACCESIBILIDADES " );
			sBuilder.append( "( id, nombre ) " );
			sBuilder.append( "VALUES " );
			sBuilder.append( "( " );
			sBuilder.append( String.format( "%s, ", accesibilidad.getId( ) ) );
			sBuilder.append( String.format( "'%s' ", formatSQL( accesibilidad.getNombre( ) ) ) );
			sBuilder.append( ")" );
			
			return sBuilder.toString( );
		}
		
		public static String boleta( Boleta boleta )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO BOLETAS " );
			sBuilder.append( "(NUM_BOLETA, NUM_SILLA, NUM_FILA, ID_LOCALIDAD, ID_LUGAR, FECHA, ID_USUARIO, ID_TIPO ) " );
			sBuilder.append( "VALUES " );
			sBuilder.append( "( " );
			sBuilder.append( String.format( "%s, ", boleta.getNumBoleta( ) ) );
			sBuilder.append( String.format( "%s, ", boleta.getNumeroSilla( ) ) );
			sBuilder.append( String.format( "%s, ", boleta.getNumeroFila( ) ) );
			sBuilder.append( String.format( "%s, ", boleta.getIdLocalidad( ) ) );
			sBuilder.append( String.format( "%s, ", boleta.getIdLugar( ) ) );
			sBuilder.append( String.format( "%s, ", toDateTime( boleta.getFecha( ) ) ) );
			sBuilder.append( String.format( "%s, ", boleta.getIdUsuario( ) ) );
			sBuilder.append( String.format( "'%s' ", formatSQL( boleta.getTipoIdUsuario( ) ) ) );
			sBuilder.append( ") " );
			
			return sBuilder.toString( );
		}
		
		public static String clasificacion( Clasificacion clasificacion )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO CLASIFICACIONES " );
			sBuilder.append( "( id, nombre ) " );
			sBuilder.append( "VALUES " );
			sBuilder.append( "( " );
			sBuilder.append( String.format( "%s, ", clasificacion.getId( ) ) );
			sBuilder.append( String.format( "'%s' ", formatSQL( clasificacion.getNombre( ) ) ) );
			sBuilder.append( ")" );
			return sBuilder.toString( );
		}
		
		public static String companiaDeTeatro( CompaniaDeTeatro companiaDeTeatro )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO COMPANIAS_DE_TEATRO " );
			sql.append( "( id, tipo_id, nombre, nombre_representante, pais_origen, pagina_web, fecha_llegada, fecha_salida ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", companiaDeTeatro.getIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", formatSQL( companiaDeTeatro.getTipoIdentificacion( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( companiaDeTeatro.getNombre( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( companiaDeTeatro.getNombreRepresentante( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( companiaDeTeatro.getPaisOrigen( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( companiaDeTeatro.getPaginaWeb( ) ) ) );
			sql.append( String.format( "%s, ", DateUtils.toDate( companiaDeTeatro.getFechaLlegada( ) ) ) );
			sql.append( String.format( "%s ", DateUtils.toDate( companiaDeTeatro.getFechaSalida( ) ) ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String espectaculo( Espectaculo espectaculo )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO ESPECTACULOS " );
			sBuilder.append( "( id, nombre, duracion, idioma, costo_realizacion, descripcion, id_festival, id_clasificacion ) " );
			sBuilder.append( "VALUES " );
			sBuilder.append( "( " );
			sBuilder.append( String.format( "%s, ", espectaculo.getId( ) ) );
			sBuilder.append( String.format( "'%s', ", formatSQL( espectaculo.getNombre( ) ) ) );
			sBuilder.append( String.format( "%s, ", espectaculo.getDuracion( ) ) );
			sBuilder.append( String.format( "'%s', ", formatSQL( espectaculo.getIdioma( ) ) ) );
			sBuilder.append( String.format( "%s, ", espectaculo.getCostoRealizacion( ) ) );
			sBuilder.append( String.format( "'%s', ", formatSQL( espectaculo.getDescripcion( ) ) ) );
			sBuilder.append( String.format( "%s, ", espectaculo.getIdFestival( ) ) );
			sBuilder.append( String.format( "%s ", espectaculo.getIdClasificacion( ) ) );
			sBuilder.append( ") " );
			
			return sBuilder.toString( );
		}
		
		public static String festival( Festival festival )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO FESTIVALES " );
			sBuilder.append( "( id, fecha_inicio, fecha_fin, ciudad ) " );
			sBuilder.append( "VALUES " );
			sBuilder.append( "( " );
			sBuilder.append( String.format( "%s, ", festival.getId( ) ) );
			sBuilder.append( String.format( "%s, ", DateUtils.toDate( festival.getFechaInicio( ) ) ) );
			sBuilder.append( String.format( "%s, ", DateUtils.toDate( festival.getFechaFin( ) ) ) );
			sBuilder.append( String.format( "'%s' ", formatSQL( festival.getCiudad( ) ) ) );
			sBuilder.append( ")" );
			
			return sBuilder.toString( );
		}
		
		public static String funcion( Funcion funcion )
		{
			StringBuilder sBuilder = new StringBuilder( );
			sBuilder.append( "INSERT INTO FUNCIONES " );
			sBuilder.append( "( fecha, id_lugar, id_espectaculo, se_realiza ) " );
			sBuilder.append( "VALUES " );
			sBuilder.append( "( " );
			sBuilder.append( String.format( "%s, ", toDateTime( funcion.getFecha( ) ) ) );
			sBuilder.append( String.format( "%s, ", funcion.getIdLugar( ) ) );
			sBuilder.append( String.format( "%s, ", funcion.getIdEspectaculo( ) ) );
			sBuilder.append( String.format( "%s ", funcion.getSeRealiza( ) ) );
			sBuilder.append( ")" );
			
			return sBuilder.toString( );
		}
		
		public static String genero( Genero genero )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO GENEROS " );
			sql.append( "( id, nombre ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", genero.getId( ) ) );
			sql.append( String.format( "'%s' ", formatSQL( genero.getNombre( ) ) ) );
			sql.append( ")" );
			return sql.toString( );
		}
		
		public static String localidad( Localidad localidad )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO LOCALIDADES " );
			sql.append( "( id, nombre ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", localidad.getId( ) ) );
			sql.append( String.format( "'%s' ", formatSQL( localidad.getNombre( ) ) ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String lugar( Lugar lugar )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO LUGARES " );
			sql.append( "( id, nombre, disponibilidad_inicio, disponibilidad_fin, es_abierto, tipo_lugar ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", lugar.getId( ) ) );
			sql.append( String.format( "'%s', ", formatSQL( lugar.getNombre( ) ) ) );
			sql.append( String.format( "%s, ", DateUtils.toDateTime( lugar.getDisponibilidadInicio( ) ) ) );
			sql.append( String.format( "%s, ", DateUtils.toDateTime( lugar.getDisponibilidadFin( ) ) ) );
			sql.append( String.format( "%s, ", lugar.getEsAbierto( ) ) );
			sql.append( String.format( "'%s' ", formatSQL( lugar.getTipo( ) ) ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String requerimientoTecnico( RequerimientoTecnico requerimientoTecnico )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO REQUERIMIENTOS_TECNICOS " );
			sql.append( "( id, nombre ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", requerimientoTecnico.getId( ) ) );
			sql.append( String.format( "'%s' ", formatSQL( requerimientoTecnico.getNombre( ) ) ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String silla( Silla silla )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO SILLAS " );
			sql.append( "( num_fila, num_silla, id_localidad, id_lugar ) " );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", silla.getNumFila( ) ) );
			sql.append( String.format( "%s, ", silla.getNumSilla( ) ) );
			sql.append( String.format( "%s, ", silla.getIdLocalidad( ) ) );
			sql.append( String.format( "%s ", silla.getIdLugar( ) ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String usuario( Usuario usuario )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO USUARIOS " );
			sql.append( "( identificacion, tipo_identificacion, email, password, nombre, rol, id_festival ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", usuario.getIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", formatSQL( usuario.getTipoIdentificacion( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( usuario.getEmail( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( usuario.getPassword( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( usuario.getNombre( ) ) ) );
			sql.append( String.format( "'%s', ", formatSQL( usuario.getRol( ) ) ) );
			sql.append( String.format( "%s ", usuario.getIdFestival( ) ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String usuarioRegistrado( UsuarioRegistrado usuarioRegistrado )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO USUARIOS_REGISTRADOS " );
			sql.append( "( id_usuario, tipo_id, edad ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", usuarioRegistrado.getIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", formatSQL( usuarioRegistrado.getTipoIdentificacion( ) ) ) );
			sql.append( String.format( "%s ", usuarioRegistrado.getEdad( ) ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String ofrecen( Long idCompania, Long idEspectaculo )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO OFRECE " );
			sql.append( "( id_compania_de_teatro, tipo_id, id_espectaculo )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idCompania ) );
			sql.append( String.format( "'%s', ", formatSQL( CompaniaDeTeatro.TIPO_ID ) ) );
			sql.append( String.format( "%s ", idEspectaculo ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String lugarLocalidad( LugarLocalidad ll )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO LUGAR_LOCALIDAD " );
			sql.append( "( id_lugar, id_localidad, es_numerado, capacidad )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", ll.getIdLugar( ) ) );
			sql.append( String.format( "%s, ", ll.getIdLocalidad( ) ) );
			sql.append( String.format( "%s, ", ll.getEsNumerado( ) ) );
			sql.append( String.format( "%s ", ll.getCapacidad( ) ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String costoLocalidad( CostoLocalidad costoLocalidad )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO COSTO_LOCALIDAD " );
			sql.append( "( fecha, id_lugar, id_localidad, costo )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", toDateTime( costoLocalidad.getFecha( ) ) ) );
			sql.append( String.format( "%s, ", costoLocalidad.getIdLugar( ) ) );
			sql.append( String.format( "%s, ", costoLocalidad.getIdLocalidad( ) ) );
			sql.append( String.format( "%s ", costoLocalidad.getCosto( ) ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String espectaculoGeneros( Long idEspectaculo, Long idGenero )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO ESPECTACULO_GENEROS " );
			sql.append( "( id_espectaculo, id_genero )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idEspectaculo ) );
			sql.append( String.format( "%s ", idGenero ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String espectaculoRequerimiento( Long idEspectaculo, Long idRequerimiento )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO ESPECTACULO_REQUERIMIENTO " );
			sql.append( "( id_espectaculo, id_requerimiento )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idEspectaculo ) );
			sql.append( String.format( "%s ", idRequerimiento ) );
			sql.append( ")" );
			
			return sql.toString( );
		}
		
		public static String lugarAccesibilidad( Long idLugar, Long idAccesibilidad )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO LUGAR_ACCESIBILIDAD " );
			sql.append( "( id_lugar, id_accesibilidad )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idLugar ) );
			sql.append( String.format( "%s ", idAccesibilidad ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String lugarRequerimiento( Long idLugar, Long idRequerimiento )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO LUGAR_REQUERIMIENTO " );
			sql.append( "( id_lugar, id_requerimiento )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idLugar ) );
			sql.append( String.format( "%s ", idRequerimiento ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String preferenciaGenero( Long idUsuario, String tipo, Long idGenero )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO PREFERENCIA_GENEROS " );
			sql.append( "( id_usuario, id_tipo, id_genero )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idUsuario ) );
			sql.append( String.format( "'%s', ", formatSQL( tipo ) ) );
			sql.append( String.format( "%s ", idGenero ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String preferenciaLugar( Long idUsuario, String tipo, Long idLugar )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO PREFERENCIA_LUGARES " );
			sql.append( "( id_lugar, id_usuario, id_tipo )" );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", idLugar ) );
			sql.append( String.format( "%s, ", idUsuario ) );
			sql.append( String.format( "'%s' ", formatSQL( tipo ) ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String abonoFuncion( Abono abono, Long idLugarFuncion, Long idLocalidadFuncion, Date fechaFuncion )
		{
			StringBuilder sql = new StringBuilder( );
			sql.append( "INSERT INTO ABONO_FUNCION " );
			sql.append( "(ID_FESTIVAL, ID_USUARIO, TIPO_ID, FECHA, ID_LUGAR, ID_LOCALIDAD) " );
			sql.append( "VALUES ( " );
			sql.append( String.format( "%s, ", abono.getIdFestival( ) ) );
			sql.append( String.format( "%s, ", abono.getIdUsuario( ) ) );
			sql.append( String.format( "'%s', ", formatSQL( abono.getTipoId( ) ) ) );
			sql.append( String.format( "%s, ", toDateTime( fechaFuncion ) ) );
			sql.append( String.format( "%s, ", idLugarFuncion ) );
			sql.append( String.format( "%s ", idLocalidadFuncion ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
	}
}