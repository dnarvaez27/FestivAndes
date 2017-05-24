package dao;

import protocolos.ProtocoloRFC5;
import vos.CompaniaDeTeatro;
import vos.Usuario;
import vos.UsuarioRegistrado;
import vos.reportes.RFC5;
import vos.reportes.RFC8;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DAOCompaniaDeTeatro extends DAO
{
	public DAOCompaniaDeTeatro( )
	{
		super( );
	}
	
	public CompaniaDeTeatro createCompaniaDeTeatro( CompaniaDeTeatro object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO COMPANIAS_DE_TEATRO " );
		sql.append( "( id, tipo_id, nombre, nombre_representante, pais_origen, pagina_web, fecha_llegada, fecha_salida ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getIdentificacion( ) ) );
		sql.append( String.format( "'%s', ", object.getTipoIdentificacion( ) ) );
		sql.append( String.format( "'%s', ", object.getNombre( ) ) );
		sql.append( String.format( "'%s', ", object.getNombreRepresentante( ) ) );
		sql.append( String.format( "'%s', ", object.getPaisOrigen( ) ) );
		sql.append( String.format( "'%s', ", object.getPaginaWeb( ) ) );
		sql.append( String.format( "%s, ", toDate( object.getFechaLlegada( ) ) ) );
		sql.append( String.format( "%s ", toDate( object.getFechaSalida( ) ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public List<CompaniaDeTeatro> getCompaniaDeTeatros( ) throws SQLException
	{
		List<CompaniaDeTeatro> list = new ArrayList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM COMPANIAS_DE_TEATRO CT INNER JOIN USUARIOS U" );
		sql.append( "                                      ON CT.id = U.identificacion " );
		sql.append( "                                      AND CT.tipo_id = U.tipo_identificacion " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToCompaniaDeTeatro( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public CompaniaDeTeatro getCompaniaDeTeatro( Long id ) throws SQLException
	{
		CompaniaDeTeatro object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM COMPANIAS_DE_TEATRO CT INNER JOIN USUARIOS U " );
		sql.append( "                            ON CT.id = U.identificacion " );
		sql.append( String.format( "WHERE id = %s ", id ) );
		sql.append( String.format( "AND tipo_id = '%s'", CompaniaDeTeatro.TIPO_ID ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToCompaniaDeTeatro( rs );
		}
		rs.close( );
		s.close( );
		return object;
	}
	
	public CompaniaDeTeatro updateCompaniaDeTeatro( Long idCompania, CompaniaDeTeatro object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE COMPANIAS_DE_TEATRO " );
		sql.append( "SET " );
		sql.append( String.format( "nombre = '%s', ", object.getNombre( ) ) );
		sql.append( String.format( "nombre_representante = '%s', ", object.getNombreRepresentante( ) ) );
		sql.append( String.format( "pagina_web = '%s', ", object.getPaginaWeb( ) ) );
		sql.append( String.format( "pais_origen = '%s', ", object.getPaisOrigen( ) ) );
		sql.append( String.format( "fecha_llegada = %s, ", toDate( object.getFechaLlegada( ) ) ) );
		sql.append( String.format( "fecha_salida = %s ", toDate( object.getFechaSalida( ) ) ) );
		sql.append( String.format( "WHERE id = %s ", idCompania ) );
		sql.append( String.format( "AND tipo_id = '%s'", CompaniaDeTeatro.TIPO_ID ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public void deleteCompaniaDeTeatro( Long id ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM COMPANIAS_DE_TEATRO " );
		sql.append( String.format( "WHERE id = %s ", id ) );
		sql.append( String.format( "AND tipo_id = '%s'", CompaniaDeTeatro.TIPO_ID ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public List<RFC5> rentabilidad( )
	{
		List<RFC5> list = new LinkedList<>( );
		//TODO RFC5
		return list;
	}
	
	public static CompaniaDeTeatro resultToCompaniaDeTeatro( ResultSet rs ) throws SQLException
	{
		CompaniaDeTeatro object = new CompaniaDeTeatro( );
		object.setIdentificacion( rs.getLong( "id" ) );
		object.setTipoIdentificacion( rs.getString( "tipo_id" ) );
		object.setNombreRepresentante( rs.getString( "nombre_representante" ) );
		object.setPaginaWeb( rs.getString( "pagina_web" ) );
		object.setPaisOrigen( rs.getString( "pais_origen" ) );
		object.setFechaLlegada( rs.getDate( "fecha_llegada" ) );
		object.setFechaSalida( rs.getDate( "fecha_salida" ) );
		
		object.setEmail( rs.getString( "email" ) );
		object.setPassword( rs.getString( "password" ) );
		object.setRol( rs.getString( "rol" ) );
		object.setIdFestival( rs.getLong( "id_festival" ) );
		object.setNombre( rs.getString( "nombre" ) );
		object.setImagen( rs.getString( "imagen" ) );
		return object;
	}
	
	public RFC8 informacionCompania( Long idCompania ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		
		sql.append( "SELECT " );
		sql.append( "  ID_COMPANIA, " );
		sql.append( "  NOMBRE_COMPANIA, " );
		sql.append( "  ID_ESPECTACULO, " );
		sql.append( "  NOMBRE_ESPECTACULO, " );
		sql.append( "  FECHA_FUNCION, " );
		sql.append( "  LUGAR_FUNCION, " );
		sql.append( "  ASISTENCIA * 100 / TOTAL_CAPACIDAD AS OCUPACION, " );
		sql.append( "  ASISTENCIA, " );
		sql.append( "  ASISTENCIA_REGISTRADOS, " );
		sql.append( "  TOTAL " );
		sql.append( "FROM (SELECT " );
		sql.append( "        E.ID          AS ID_ESPECTACULO, " );
		sql.append( "        E.NOMBRE      AS NOMBRE_ESPECTACULO, " );
		sql.append( "        CT.ID         AS ID_COMPANIA, " );
		sql.append( "        U.NOMBRE     AS NOMBRE_COMPANIA, " );
		sql.append( "        F.FECHA       AS FECHA_FUNCION, " );
		sql.append( "        F.ID_LUGAR    AS LUGAR_FUNCION, " );
		sql.append( "        COUNT(*)      AS ASISTENCIA, " );
		sql.append( "        SUM(CL.COSTO) AS TOTAL " );
		sql.append( "      FROM " );
		sql.append( "        BOLETAS B " );
		sql.append( "        INNER JOIN " );
		sql.append( "        USUARIOS U ON U.IDENTIFICACION = B.ID_USUARIO AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
		sql.append( "        INNER JOIN " );
		sql.append( "        COSTO_LOCALIDAD CL ON B.FECHA = CL.FECHA AND B.ID_LUGAR = CL.ID_LUGAR AND B.ID_LOCALIDAD = CL.ID_LOCALIDAD " );
		sql.append( "        INNER JOIN " );
		sql.append( "        LUGARES L ON L.ID = B.ID_LUGAR " );
		sql.append( "        INNER JOIN " );
		sql.append( "        FUNCIONES F ON F.FECHA = B.FECHA AND F.ID_LUGAR = B.ID_LUGAR " );
		sql.append( "        INNER JOIN " );
		sql.append( "        ESPECTACULOS E ON F.ID_ESPECTACULO = E.ID " );
		sql.append( "        INNER JOIN " );
		sql.append( "        OFRECE O ON O.ID_ESPECTACULO = E.ID " );
		sql.append( "        RIGHT JOIN " );
		sql.append( "        COMPANIAS_DE_TEATRO CT ON O.ID_COMPANIA_DE_TEATRO = CT.ID " );
		sql.append( "        INNER JOIN " );
		sql.append( "        USUARIOS U ON U.IDENTIFICACION = CT.ID AND U.TIPO_IDENTIFICACION = CT.TIPO_ID " );
		sql.append( String.format( "WHERE CT.ID = %s ", idCompania ) );
		sql.append( "      GROUP BY E.ID, E.NOMBRE, CT.ID, U.NOMBRE, F.FECHA, F.ID_LUGAR " );
		sql.append( "      ORDER BY E.ID) Z " );
		sql.append( "  LEFT JOIN (SELECT " );
		sql.append( "               CL.FECHA, " );
		sql.append( "               CL.ID_LUGAR, " );
		sql.append( "               COUNT(*) AS ASISTENCIA_REGISTRADOS " );
		sql.append( "             FROM BOLETAS B " );
		sql.append( "               INNER JOIN COSTO_LOCALIDAD CL ON B.FECHA = CL.FECHA AND B.ID_LUGAR = CL.ID_LUGAR AND B.ID_LOCALIDAD = CL.ID_LOCALIDAD " );
		sql.append( "               INNER JOIN USUARIOS U ON B.ID_USUARIO = U.IDENTIFICACION AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
		sql.append( String.format( "   WHERE U.ROL = '%s' ", Usuario.USUARIO_REGISTRADO ) );
		sql.append( "             GROUP BY CL.FECHA, CL.ID_LUGAR, U.ROL) B ON Z.FECHA_FUNCION = B.FECHA AND Z.LUGAR_FUNCION = B.ID_LUGAR " );
		sql.append( "  LEFT JOIN (SELECT " );
		sql.append( "               L.ID, " );
		sql.append( "               SUM(CAPACIDAD) AS TOTAL_CAPACIDAD " );
		sql.append( "             FROM LUGARES L " );
		sql.append( "               INNER JOIN LUGAR_LOCALIDAD LL ON L.ID = LL.ID_LUGAR " );
		sql.append( "             GROUP BY L.ID) C ON C.ID = Z.LUGAR_FUNCION " );
		sql.append( String.format( "WHERE ID_COMPANIA = %s ", idCompania ) );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		
		RFC8 rfc8 = new RFC8( );
		List<RFC8.EspectaculoInfo> list = new LinkedList<>( );
		Long idActual = -1L;
		
		boolean move = false;
		while( move || rs.next( ) )
		{
			move = true;
			
			if( rfc8.getIdCompania( ) == null )
			{
				rfc8.setIdCompania( rs.getLong( "ID_COMPANIA" ) );
				rfc8.setNombreCompania( rs.getString( "NOMBRE_COMPANIA" ) );
			}
			
			RFC8.EspectaculoInfo aux = rfc8.new EspectaculoInfo( );
			List<RFC8.FuncionOcupacion> temp = new LinkedList<>( );
			aux.setNombreespectaculo( rs.getString( "NOMBRE_ESPECTACULO" ) );
			idActual = idActual == -1 ? rs.getLong( "ID_ESPECTACULO" ) : idActual;
			
			while( rs.getLong( "ID_ESPECTACULO" ) == ( idActual ) )
			{
				RFC8.FuncionOcupacion fo = rfc8.new FuncionOcupacion( );
				fo.setAsistencia( aux.getNombreEspectaculo( ) == null ? 0 : rs.getInt( "ASISTENCIA" ) );
				fo.setAsistencia_registrados( rs.getInt( "ASISTENCIA_REGISTRADOS" ) );
				fo.setFecha( rs.getDate( "FECHA_FUNCION" ) );
				fo.setIdFuncion( rs.getLong( "LUGAR_FUNCION" ) );
				fo.setPorcentaje( rs.getDouble( "OCUPACION" ) );
				fo.setTotalPlata( rs.getDouble( "TOTAL" ) );
				temp.add( fo );
				if( !rs.next( ) )
				{
					move = false;
					break;
				}
			}
			
			aux.setOcupaciones( temp );
			Integer asistencia = 0;
			Integer asistenciaRegistrados = 0;
			Double plataTotal = 0d;
			
			for( RFC8.FuncionOcupacion fo : temp )
			{
				asistencia += fo.getAsistencia( );
				asistenciaRegistrados += fo.getAsistencia_registrados( );
				plataTotal += fo.getTotalPlata( );
			}
			aux.setAsistencia( asistencia );
			aux.setAsistenciaRegistrados( asistenciaRegistrados );
			aux.setDinero( plataTotal );
			list.add( aux );
			if( move )
			{
				idActual = rs.getLong( "ID_ESPECTACULO" );
			}
		}
		rfc8.setEspectaculos( list );
		return rfc8;
	}
	
	public List<UsuarioRegistrado> rfc9( Long idCompania, Date fInicio, Date fEnd ) throws SQLException
	{
		List<UsuarioRegistrado> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT DISTINCT U.*, UR.* " );
		sql.append( "FROM " );
		sql.append( "  FUNCIONES F " );
		sql.append( "  INNER JOIN " );
		sql.append( "  ESPECTACULOS E " );
		sql.append( "    ON F.ID_ESPECTACULO = E.ID " );
		sql.append( "  INNER JOIN " );
		sql.append( "  OFRECE O " );
		sql.append( "    ON O.ID_ESPECTACULO = E.ID " );
		sql.append( "  INNER JOIN " );
		sql.append( "  COMPANIAS_DE_TEATRO CT " );
		sql.append( "    ON O.ID_COMPANIA_DE_TEATRO = CT.ID AND O.TIPO_ID = CT.TIPO_ID " );
		sql.append( "  INNER JOIN " );
		sql.append( "  BOLETAS B " );
		sql.append( "    ON B.ID_LUGAR = F.ID_LUGAR AND B.FECHA = F.FECHA " );
		sql.append( "  INNER JOIN " );
		sql.append( "  USUARIOS U " );
		sql.append( "    ON B.ID_USUARIO = U.IDENTIFICACION AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
		sql.append( "  INNER JOIN " );
		sql.append( "  USUARIOS_REGISTRADOS UR " );
		sql.append( "    ON U.IDENTIFICACION = UR.ID_USUARIO AND U.TIPO_IDENTIFICACION = UR.TIPO_ID " );
		sql.append( String.format( "WHERE CT.ID = %s ", idCompania ) );
		sql.append( String.format( "  AND F.FECHA BETWEEN %s AND %s ", toDateTime( fInicio ), toDateTime( fEnd ) ) );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		
		while( rs.next( ) )
		{
			list.add( DAOUsuarioRegistrado.resultToUsuarioRegistrado( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<UsuarioRegistrado> rfc10( Long idCompania, Date fInicio, Date fEnd ) throws SQLException
	{
		List<UsuarioRegistrado> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( "  INNER JOIN USUARIOS_REGISTRADOS UR " );
		sql.append( "    ON USUARIOS.IDENTIFICACION = UR.ID_USUARIO AND USUARIOS.TIPO_IDENTIFICACION = UR.TIPO_ID " );
		sql.append( "MINUS ( SELECT " );
		sql.append( "          U.*, " );
		sql.append( "          UR.* " );
		sql.append( "        FROM " );
		sql.append( "          FUNCIONES F " );
		sql.append( "          INNER JOIN " );
		sql.append( "          ESPECTACULOS E " );
		sql.append( "            ON F.ID_ESPECTACULO = E.ID " );
		sql.append( "          INNER JOIN " );
		sql.append( "          OFRECE O " );
		sql.append( "            ON O.ID_ESPECTACULO = E.ID " );
		sql.append( "          INNER JOIN " );
		sql.append( "          COMPANIAS_DE_TEATRO CT " );
		sql.append( "            ON O.ID_COMPANIA_DE_TEATRO = CT.ID AND O.TIPO_ID = CT.TIPO_ID " );
		sql.append( "          INNER JOIN " );
		sql.append( "          BOLETAS B " );
		sql.append( "            ON B.ID_LUGAR = F.ID_LUGAR AND B.FECHA = F.FECHA " );
		sql.append( "          INNER JOIN " );
		sql.append( "          USUARIOS U " );
		sql.append( "            ON B.ID_USUARIO = U.IDENTIFICACION AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
		sql.append( "          INNER JOIN " );
		sql.append( "          USUARIOS_REGISTRADOS UR " );
		sql.append( "            ON U.IDENTIFICACION = UR.ID_USUARIO AND U.TIPO_IDENTIFICACION = UR.TIPO_ID " );
		sql.append( String.format( "WHERE CT.ID = %s ", idCompania ) );
		sql.append( String.format( "  AND F.FECHA BETWEEN %s AND %s )", toDateTime( fInicio ), toDateTime( fEnd ) ) );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		
		while( rs.next( ) )
		{
			list.add( DAOUsuarioRegistrado.resultToUsuarioRegistrado( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------
	
	public List<ProtocoloRFC5> requerimiento5Sitio( String fechaInic, String fechaFin, Long idUsuario, String tipoId ) throws SQLException, Exception
	{
		List<ProtocoloRFC5> rta = new LinkedList<>( );
		
		StringBuilder sentenciaBoletas = new StringBuilder( );
		sentenciaBoletas.append( "SELECT * FROM BOLETA " );
		sentenciaBoletas.append( "WHERE FECHA_DE_COMPRA BETWEEN " );
		sentenciaBoletas.append( toDate( fechaInic ) );
		sentenciaBoletas.append( " AND " );
		sentenciaBoletas.append( toDate( fechaFin ) );
		
		StringBuilder sentenciaCompSit = new StringBuilder( );
		sentenciaCompSit.append( "SELECT ID_SITIO, ID_FUNCION FROM " );
		sentenciaCompSit.append( "((((SELECT * FROM COMPANIA_TEATRO " );
		sentenciaCompSit.append( "WHERE " );
		sentenciaCompSit.append( String.format( "ID_USUARIO = %s", idUsuario ) );
		sentenciaCompSit.append( " AND " );
		sentenciaCompSit.append( String.format( "TIPO_ID = '%s'", tipoId ) );
		sentenciaCompSit.append( ") " );
		sentenciaCompSit.append( "NATURAL INNER JOIN OFRECE) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN FUNCION) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN SITIO)" );
		
		StringBuilder sentenciaReq = new StringBuilder( );
		sentenciaReq.append( "SELECT ID_SITIO, ID_FUNCION, COUNT(*) AS ASISTENTES, SUM(COSTO) AS PRODUCIDO " );
		sentenciaReq.append( "FROM ((" );
		sentenciaReq.append( "(" + sentenciaCompSit.toString( ) + ")" );
		sentenciaReq.append( " NATURAL INNER JOIN " );
		sentenciaReq.append( "(" + sentenciaBoletas.toString( ) + ")" );
		sentenciaReq.append( ") NATURAL INNER JOIN FUNCION_COSTO_LOCALIDAD) " );
		sentenciaReq.append( "GROUP BY ID_SITIO, ID_FUNCION " );
		sentenciaReq.append( "ORDER BY ID_SITIO " );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sentenciaReq.toString( ) );
		recursos.add( prepStmt2 );
		ResultSet rs2 = prepStmt2.executeQuery( );
		
		Long idSitio = ( long ) -1;
		Integer totVendidos = 0;
		Double totFacturado = ( double ) 0;
		List<ProtocoloRFC5.RFC5Funcion> funciones = new LinkedList<>( );
		while( rs2.next( ) )
		{
			ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
			if( idSitio != rs2.getLong( "ID_SITIO" ) && idSitio != -1 )
			{
				nuevo.setNombreSitio( nombreSitio( idSitio ) );
				nuevo.setBoletasVendidas( totVendidos );
				nuevo.setValorTotalFacturado( totFacturado );
				nuevo.setFunciones( funciones );
				rta.add( nuevo );
				
				totVendidos = 0;
				totFacturado = ( double ) 0;
				funciones = new ArrayList<>( );
			}
			idSitio = rs2.getLong( "ID_SITIO" );
			Long idFuncion = rs2.getLong( "ID_FUNCION" );
			Integer asistentes = rs2.getInt( "ASISTENTES" );
			Double producido = rs2.getDouble( "PRODUCIDO" );
			Integer capacidad = capacidadSitioFuncion( idFuncion );
			Double proporcion = ( double ) asistentes / capacidad;
			totVendidos += asistentes;
			totFacturado += producido;
			ProtocoloRFC5.RFC5Funcion func = nuevo.new RFC5Funcion( );
			func.setIdFuncion( idFuncion );
			func.setAsistentes( asistentes );
			func.setProducido( producido );
			func.setProporcionAsistencia( proporcion );
			funciones.add( func );
		}
		
		ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
		nuevo.setNombreSitio( nombreSitio( idSitio ) );
		nuevo.setBoletasVendidas( totVendidos );
		nuevo.setValorTotalFacturado( totFacturado );
		nuevo.setFunciones( funciones );
		rta.add( nuevo );
		
		return rta;
	}
	
	public List<ProtocoloRFC5> requerimiento5TipodeSitio( String fechaInic, String fechaFin, Long idUsuario, String tipoId ) throws SQLException, Exception
	{
		List<ProtocoloRFC5> rta = new ArrayList<>( );
		
		StringBuilder sentenciaBoletas = new StringBuilder( );
		sentenciaBoletas.append( "SELECT * FROM BOLETA " );
		sentenciaBoletas.append( "WHERE FECHA_DE_COMPRA BETWEEN " );
		sentenciaBoletas.append( toDate( fechaInic ) );
		sentenciaBoletas.append( " AND " );
		sentenciaBoletas.append( toDate( fechaFin ) );
		
		StringBuilder sentenciaCompSit = new StringBuilder( );
		sentenciaCompSit.append( "SELECT ID_SITIO, ID_FUNCION, TIPO_ESPACIO FROM " );
		sentenciaCompSit.append( "((((SELECT * FROM COMPANIA_TEATRO " );
		sentenciaCompSit.append( "WHERE " );
		sentenciaCompSit.append( String.format( "ID_USUARIO = %s", idUsuario ) );
		sentenciaCompSit.append( " AND " );
		sentenciaCompSit.append( String.format( "TIPO_ID = '%s'", tipoId ) );
		sentenciaCompSit.append( ") " );
		sentenciaCompSit.append( "NATURAL INNER JOIN OFRECE) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN FUNCION) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN SITIO)" );
		
		StringBuilder sentenciaReq = new StringBuilder( );
		sentenciaReq.append( "SELECT TIPO_ESPACIO, ID_FUNCION, COUNT(*) AS ASISTENTES, SUM(COSTO) AS PRODUCIDO " );
		sentenciaReq.append( "FROM ((" );
		sentenciaReq.append( "(" + sentenciaCompSit.toString( ) + ")" );
		sentenciaReq.append( " NATURAL INNER JOIN " );
		sentenciaReq.append( "(" + sentenciaBoletas.toString( ) + ")" );
		sentenciaReq.append( ") NATURAL INNER JOIN FUNCION_COSTO_LOCALIDAD) " );
		sentenciaReq.append( "GROUP BY TIPO_ESPACIO, ID_FUNCION " );
		sentenciaReq.append( "ORDER BY TIPO_ESPACIO " );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sentenciaReq.toString( ) );
		recursos.add( prepStmt2 );
		ResultSet rs2 = prepStmt2.executeQuery( );
		
		String tipoEspacio = "";
		Integer totVendidos = 0;
		Double totFacturado = ( double ) 0;
		List<ProtocoloRFC5.RFC5Funcion> funciones = new ArrayList<>( );
		while( rs2.next( ) )
		{
			ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
			if( !tipoEspacio.equals( rs2.getString( "TIPO_ESPACIO" ) ) && !tipoEspacio.equals( "" ) )
			{
				nuevo.setTipoDeSitio( tipoEspacio );
				nuevo.setBoletasVendidas( totVendidos );
				nuevo.setValorTotalFacturado( totFacturado );
				nuevo.setFunciones( funciones );
				rta.add( nuevo );
				
				totVendidos = 0;
				totFacturado = ( double ) 0;
				funciones = new ArrayList<>( );
			}
			tipoEspacio = rs2.getString( "TIPO_ESPACIO" );
			Long idFuncion = rs2.getLong( "ID_FUNCION" );
			Integer asistentes = rs2.getInt( "ASISTENTES" );
			Double producido = rs2.getDouble( "PRODUCIDO" );
			Integer capacidad = capacidadSitioFuncion( idFuncion );
			Double proporcion = ( double ) asistentes / capacidad;
			totVendidos += asistentes;
			totFacturado += producido;
			ProtocoloRFC5.RFC5Funcion func = nuevo.new RFC5Funcion( );
			func.setIdFuncion( idFuncion );
			func.setAsistentes( asistentes );
			func.setProducido( producido );
			func.setProporcionAsistencia( proporcion );
			funciones.add( func );
		}
		
		ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
		nuevo.setTipoDeSitio( tipoEspacio );
		nuevo.setBoletasVendidas( totVendidos );
		nuevo.setValorTotalFacturado( totFacturado );
		nuevo.setFunciones( funciones );
		rta.add( nuevo );
		
		return rta;
	}
	
	public List<ProtocoloRFC5> requerimiento5Espectaculo( String fechaInic, String fechaFin, Long idUsuario, String tipoId ) throws SQLException, Exception
	{
		List<ProtocoloRFC5> rta = new ArrayList<>( );
		
		StringBuilder sentenciaBoletas = new StringBuilder( );
		sentenciaBoletas.append( "SELECT * FROM BOLETA " );
		sentenciaBoletas.append( "WHERE FECHA_DE_COMPRA BETWEEN " );
		sentenciaBoletas.append( toDate( fechaInic ) );
		sentenciaBoletas.append( " AND " );
		sentenciaBoletas.append( toDate( fechaFin ) );
		
		StringBuilder sentenciaCompSit = new StringBuilder( );
		sentenciaCompSit.append( "SELECT ID_ESPECTACULO, ID_FUNCION FROM " );
		sentenciaCompSit.append( "(((SELECT * FROM COMPANIA_TEATRO " );
		sentenciaCompSit.append( "WHERE " );
		sentenciaCompSit.append( String.format( "ID_USUARIO = %s", idUsuario ) );
		sentenciaCompSit.append( " AND " );
		sentenciaCompSit.append( String.format( "TIPO_ID = '%s'", tipoId ) );
		sentenciaCompSit.append( ") " );
		sentenciaCompSit.append( "NATURAL INNER JOIN OFRECE) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN FUNCION) " );
		
		StringBuilder sentenciaReq = new StringBuilder( );
		sentenciaReq.append( "SELECT ID_ESPECTACULO, ID_FUNCION, COUNT(*) AS ASISTENTES, SUM(COSTO) AS PRODUCIDO " );
		sentenciaReq.append( "FROM ((" );
		sentenciaReq.append( "(" ).append( sentenciaCompSit.toString( ) ).append( ")" );
		sentenciaReq.append( " NATURAL INNER JOIN " );
		sentenciaReq.append( "(" ).append( sentenciaBoletas.toString( ) ).append( ")" );
		sentenciaReq.append( ") NATURAL INNER JOIN FUNCION_COSTO_LOCALIDAD) " );
		sentenciaReq.append( "GROUP BY ID_ESPECTACULO, ID_FUNCION " );
		sentenciaReq.append( "ORDER BY ID_ESPECTACULO " );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sentenciaReq.toString( ) );
		recursos.add( prepStmt2 );
		ResultSet rs2 = prepStmt2.executeQuery( );
		
		Long idEspectaculo = ( long ) -1;
		Integer totVendidos = 0;
		Double totFacturado = ( double ) 0;
		List<ProtocoloRFC5.RFC5Funcion> funciones = new LinkedList<>( );
		while( rs2.next( ) )
		{
			ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
			if( idEspectaculo != rs2.getLong( "ID_ESPECTACULO" ) && idEspectaculo != -1 )
			{
				nuevo.setEspectaculo( nombreEspectaculo( idEspectaculo ) );
				nuevo.setBoletasVendidas( totVendidos );
				nuevo.setValorTotalFacturado( totFacturado );
				nuevo.setFunciones( funciones );
				rta.add( nuevo );
				
				totVendidos = 0;
				totFacturado = ( double ) 0;
				funciones = new ArrayList<>( );
			}
			idEspectaculo = rs2.getLong( "ID_ESPECTACULO" );
			Long idFuncion = rs2.getLong( "ID_FUNCION" );
			Integer asistentes = rs2.getInt( "ASISTENTES" );
			Double producido = rs2.getDouble( "PRODUCIDO" );
			Integer capacidad = capacidadSitioFuncion( idFuncion );
			Double proporcion = ( double ) asistentes / capacidad;
			totVendidos += asistentes;
			totFacturado += producido;
			ProtocoloRFC5.RFC5Funcion func = nuevo.new RFC5Funcion( );
			func.setIdFuncion( idFuncion );
			func.setAsistentes( asistentes );
			func.setProducido( producido );
			func.setProporcionAsistencia( proporcion );
			funciones.add( func );
		}
		
		ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
		nuevo.setEspectaculo( nombreEspectaculo( idEspectaculo ) );
		nuevo.setBoletasVendidas( totVendidos );
		nuevo.setValorTotalFacturado( totFacturado );
		nuevo.setFunciones( funciones );
		rta.add( nuevo );
		
		return rta;
	}
	
	public List<ProtocoloRFC5> requerimiento5Categoria( String fechaInic, String fechaFin, Long idUsuario, String tipoId ) throws SQLException, Exception
	{
		List<ProtocoloRFC5> rta = new ArrayList<>( );
		
		StringBuilder sentenciaBoletas = new StringBuilder( );
		sentenciaBoletas.append( "SELECT * FROM BOLETA " );
		sentenciaBoletas.append( "WHERE FECHA_DE_COMPRA BETWEEN " );
		sentenciaBoletas.append( toDate( fechaInic ) );
		sentenciaBoletas.append( " AND " );
		sentenciaBoletas.append( toDate( fechaFin ) );
		
		StringBuilder sentenciaCompSit = new StringBuilder( );
		sentenciaCompSit.append( "SELECT ID_CATEGORIA, ID_FUNCION FROM " );
		sentenciaCompSit.append( "((((SELECT * FROM COMPANIA_TEATRO " );
		sentenciaCompSit.append( "WHERE " );
		sentenciaCompSit.append( String.format( "ID_USUARIO = %s", idUsuario ) );
		sentenciaCompSit.append( " AND " );
		sentenciaCompSit.append( String.format( "TIPO_ID = '%s'", tipoId ) );
		sentenciaCompSit.append( ") " );
		sentenciaCompSit.append( "NATURAL INNER JOIN OFRECE) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN FUNCION) " );
		sentenciaCompSit.append( "NATURAL INNER JOIN ESPECTACULO_CATEGORIAS)" );
		
		StringBuilder sentenciaReq = new StringBuilder( );
		sentenciaReq.append( "SELECT ID_CATEGORIA, ID_FUNCION, COUNT(*) AS ASISTENTES, SUM(COSTO) AS PRODUCIDO " );
		sentenciaReq.append( "FROM ((" );
		sentenciaReq.append( "(" ).append( sentenciaCompSit.toString( ) ).append( ")" );
		sentenciaReq.append( " NATURAL INNER JOIN " );
		sentenciaReq.append( "(" ).append( sentenciaBoletas.toString( ) ).append( ")" );
		sentenciaReq.append( ") NATURAL INNER JOIN FUNCION_COSTO_LOCALIDAD) " );
		sentenciaReq.append( "GROUP BY ID_CATEGORIA, ID_FUNCION " );
		sentenciaReq.append( "ORDER BY ID_CATEGORIA " );
		
		PreparedStatement prepStmt2 = connection.prepareStatement( sentenciaReq.toString( ) );
		recursos.add( prepStmt2 );
		ResultSet rs2 = prepStmt2.executeQuery( );
		
		Long idCategoria = ( long ) -1;
		Integer totVendidos = 0;
		Double totFacturado = ( double ) 0;
		List<ProtocoloRFC5.RFC5Funcion> funciones = new ArrayList<>( );
		while( rs2.next( ) )
		{
			ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
			if( idCategoria != rs2.getLong( "ID_CATEGORIA" ) && idCategoria != -1 )
			{
				nuevo.setCategoria( nombreCategoria( idCategoria ) );
				nuevo.setBoletasVendidas( totVendidos );
				nuevo.setValorTotalFacturado( totFacturado );
				nuevo.setFunciones( funciones );
				rta.add( nuevo );
				
				totVendidos = 0;
				totFacturado = ( double ) 0;
				funciones = new LinkedList<>( );
			}
			idCategoria = rs2.getLong( "ID_CATEGORIA" );
			Long idFuncion = rs2.getLong( "ID_FUNCION" );
			Integer asistentes = rs2.getInt( "ASISTENTES" );
			Double producido = rs2.getDouble( "PRODUCIDO" );
			Integer capacidad = capacidadSitioFuncion( idFuncion );
			Double proporcion = ( double ) asistentes / capacidad;
			totVendidos += asistentes;
			totFacturado += producido;
			ProtocoloRFC5.RFC5Funcion func = nuevo.new RFC5Funcion( );
			func.setIdFuncion( idFuncion );
			func.setAsistentes( asistentes );
			func.setProducido( producido );
			func.setProporcionAsistencia( proporcion );
			funciones.add( func );
		}
		
		ProtocoloRFC5 nuevo = new ProtocoloRFC5( );
		nuevo.setCategoria( nombreCategoria( idCategoria ) );
		nuevo.setBoletasVendidas( totVendidos );
		nuevo.setValorTotalFacturado( totFacturado );
		nuevo.setFunciones( funciones );
		rta.add( nuevo );
		
		return rta;
	}
	
	private String nombreCategoria( Long idAsociado ) throws SQLException, Exception
	{
		String sql = "SELECT NOMBRE FROM GENEROS WHERE ID= " + idAsociado;
		
		PreparedStatement prepStmt = connection.prepareStatement( sql );
		recursos.add( prepStmt );
		ResultSet rs = prepStmt.executeQuery( );
		
		if( rs.next( ) )
		{
			return rs.getString( "NOMBRE" );
		}
		
		return null;
	}
	
	private String nombreEspectaculo( Long idAsociado ) throws SQLException, Exception
	{
		String sql = "SELECT NOMBRE FROM ESPECTACULOS WHERE ID = " + idAsociado;
		
		PreparedStatement prepStmt = connection.prepareStatement( sql );
		recursos.add( prepStmt );
		ResultSet rs = prepStmt.executeQuery( );
		
		if( rs.next( ) )
		{
			return rs.getString( "NOMBRE" );
		}
		
		return null;
	}
	
	private String nombreSitio( Long idAsociado ) throws SQLException, Exception
	{
		String sql = "SELECT NOMBRE FROM LUGARES WHERE ID = " + idAsociado;
		
		PreparedStatement prepStmt = connection.prepareStatement( sql );
		recursos.add( prepStmt );
		ResultSet rs = prepStmt.executeQuery( );
		
		if( rs.next( ) )
		{
			return rs.getString( "NOMBRE" );
		}
		
		return null;
	}
	
	private Integer capacidadSitioFuncion( Long idFuncion ) throws SQLException, Exception
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT SUM(LL.CAPACIDAD) AS CAPACIDAD " );
		sql.append( "FROM LUGARES l " );
		sql.append( "  INNER JOIN LUGAR_LOCALIDAD LL " );
		sql.append( "    ON l.ID = LL.ID_LUGAR " );
		sql.append( String.format( "WHERE L.ID = %s ", idFuncion ) );
		
		PreparedStatement prepStmt = connection.prepareStatement( sql.toString( ) );
		recursos.add( prepStmt );
		ResultSet rs = prepStmt.executeQuery( );
		
		if( rs.next( ) )
		{
			return rs.getInt( "CAPACIDAD" );
		}
		return null;
	}
}