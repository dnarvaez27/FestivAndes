package utilities.data;

import vos.*;

import static utilities.SQLUtils.DateUtils;

/**
 * Created by dnarv on 3/05/2017.
 */
public class DataSQL
{
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
			sBuilder.append( String.format( "'%s', ", abono.getTipoId( ) ) );
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
			sBuilder.append( String.format( "'%s' ", accesibilidad.getNombre( ) ) );
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
			sBuilder.append( String.format( "%s, ", DateUtils.toDateTime( boleta.getFecha( ) ) ) );
			sBuilder.append( String.format( "%s, ", boleta.getIdUsuario( ) ) );
			sBuilder.append( String.format( "'%s' ", boleta.getTipoIdUsuario( ) ) );
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
			sBuilder.append( String.format( "'%s' ", clasificacion.getNombre( ) ) );
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
			sql.append( String.format( "'%s', ", companiaDeTeatro.getTipoIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", companiaDeTeatro.getNombre( ) ) );
			sql.append( String.format( "'%s', ", companiaDeTeatro.getNombreRepresentante( ) ) );
			sql.append( String.format( "'%s', ", companiaDeTeatro.getPaisOrigen( ) ) );
			sql.append( String.format( "'%s', ", companiaDeTeatro.getPaginaWeb( ) ) );
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
			sBuilder.append( String.format( "'%s', ", espectaculo.getNombre( ) ) );
			sBuilder.append( String.format( "%s, ", espectaculo.getDuracion( ) ) );
			sBuilder.append( String.format( "'%s', ", espectaculo.getIdioma( ) ) );
			sBuilder.append( String.format( "%s, ", espectaculo.getCostoRealizacion( ) ) );
			sBuilder.append( String.format( "'%s', ", espectaculo.getDescripcion( ) ) );
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
			sBuilder.append( String.format( "'%s' ", festival.getCiudad( ) ) );
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
			sBuilder.append( String.format( "%s, ", DateUtils.toDateTime( funcion.getFecha( ) ) ) );
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
			sql.append( String.format( "'%s' ", genero.getNombre( ) ) );
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
			sql.append( String.format( "'%s' ", localidad.getNombre( ) ) );
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
			sql.append( String.format( "'%s', ", lugar.getNombre( ) ) );
			sql.append( String.format( "%s, ", DateUtils.toDate( lugar.getDisponibilidadInicio( ) ) ) );
			sql.append( String.format( "%s, ", DateUtils.toDate( lugar.getDisponibilidadFin( ) ) ) );
			sql.append( String.format( "%s, ", lugar.getEsAbierto( ) ) );
			sql.append( String.format( "'%s' ", lugar.getTipo( ) ) );
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
			sql.append( String.format( "'%s' ", requerimientoTecnico.getNombre( ) ) );
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
			sql.append( String.format( "'%s', ", usuario.getTipoIdentificacion( ) ) );
			sql.append( String.format( "'%s', ", usuario.getEmail( ) ) );
			sql.append( String.format( "'%s', ", usuario.getPassword( ) ) );
			sql.append( String.format( "'%s', ", usuario.getNombre( ) ) );
			sql.append( String.format( "'%s', ", usuario.getRol( ) ) );
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
			sql.append( String.format( "'%s', ", usuarioRegistrado.getTipoIdentificacion( ) ) );
			sql.append( String.format( "%s ", usuarioRegistrado.getEdad( ) ) );
			sql.append( ") " );
			
			return sql.toString( );
		}
		
		public static String ofrecen( Long idCompania, Long idEspectaculo )
		{
			return null;
		}
	}
}