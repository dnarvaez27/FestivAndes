package utilities.data;

import utilities.SQLUtils;
import vos.*;
import vos.intermediate.CostoLocalidad;
import vos.intermediate.LugarLocalidad;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

import static utilities.data.DataRandom.*;

public class DataGen implements DataConstant, DataControl
{
	private List<Festival> festivales;
	
	private List<Espectaculo> espectaculos;
	
	private List<CompaniaDeTeatro> companias;
	
	private HashMap<String, Funcion> funciones;
	
	private HashMap<Long, List<Localidad>> lugarLocalidades;
	
	private HashMap<String, List<CostoLocalidad>> costoLocalidades;
	
	private HashMap<String, List<Silla>> sillas;
	
	private List<Boleta> boletas;
	
	private List<Usuario> usuarios;
	
	private List<UsuarioRegistrado> usuariosRegistrados;
	
	private List<Abono> abonos;
	
	private List<Object[]> preferenciaGenero;
	
	private List<Object[]> preferenciaLugares;
	
	public DataGen( )
	{
		festivales = new LinkedList<>( );
		espectaculos = new LinkedList<>( );
		companias = new LinkedList<>( );
		funciones = new HashMap<>( );
		lugarLocalidades = new HashMap<>( );
		costoLocalidades = new HashMap<>( );
		sillas = new HashMap<>( );
		usuarios = new LinkedList<>( );
		usuariosRegistrados = new LinkedList<>( );
		boletas = new LinkedList<>( );
		abonos = new LinkedList<>( );
		preferenciaGenero = new LinkedList<>( );
		preferenciaLugares = new LinkedList<>( );
		
		// Lugar( Localidades( Sillas ), Requerimientos, Accesibilidad )
		generarInfoLugares( );
		System.out.println( "Lugares Done" );
		
		// Festivales( Companias( Generos, Requerimientos ), Espectaculos( Funciones ) )
		generarFestivales( );
		System.out.println( "Festivales Done" );
		
		// Usuarios
		generarUsuarios( );
		System.out.println( "Usuario Done" );
		
		// Usuarios Registrados
		generarUsuariosRegistrados( );
		System.out.println( "Registrados Done" );
		
		generarPreferencias( );
		System.out.println( "Preferencias Done" );
		
		// Boletas
		generarBoletas( );
		System.out.println( "Boletas Done" );
		
		generarAbonos( );
		System.out.println( "Abonos Done" );
	}
	
	private void generarCompanias( Festival festival )
	{
		for( int i = 0; i < NUM_COMPANIAS; i++ )
		{
			Date llegada = generateNextRandomDate( festival.getFechaInicio( ), 10 );
			Date salida = generateNextRandomDate( llegada, 120 );
			salida = festival.getFechaFin( ).before( salida ) ? festival.getFechaFin( ) : salida;
			
			CompaniaDeTeatro companiaDeTeatro = new CompaniaDeTeatro( generateUsuario( festival.getId( ) ) );
			
			companiaDeTeatro.setNombre( COMPANIAS_NAME[ i ] );
			companiaDeTeatro.setEmail( getEmail( COMPANIAS_NAME[ i ] ) );
			companiaDeTeatro.setFechaLlegada( llegada );
			companiaDeTeatro.setFechaSalida( salida );
			companiaDeTeatro.setPaisOrigen( PAISES[ nextInt( PAISES.length ) ] );
			companiaDeTeatro.setPaginaWeb( getPaginaWeb( COMPANIAS_NAME[ i ] ) );
			companiaDeTeatro.setNombreRepresentante( PEOPLE_NAME[ nextInt( PEOPLE_NAME.length ) ] );
			
			usuarios.add( companiaDeTeatro );
			companias.add( companiaDeTeatro );
			
		}
	}
	
	private void generarFestivales( )
	{
		for( int i = 1; i <= NUM_FESTIVALES; i++ )
		{
			Date fechaInicio = generateRandomDate( );
			Date fechaFin = generateNextRandomDate( fechaInicio, FESTIVAL_MAX_DAY_LENGTH );
			
			Festival festival = new Festival( );
			festival.setId( Integer.toUnsignedLong( i ) );
			festival.setFechaInicio( fechaInicio );
			festival.setFechaFin( fechaFin );
			festival.setCiudad( CIUDADES[ nextInt( CIUDADES.length ) ] );
			
			festivales.add( festival );
			
			generarCompanias( festival );
			generarEspectaculos( festival.getId( ) );
		}
	}
	
	private void generarEspectaculos( Long idFestival )
	{
		for( int i = 0; i < NUM_ESPECTACULOS; i++ )
		{
			Espectaculo espectaculo = new Espectaculo( );
			espectaculo.setId( espectaculos.size( ) + Integer.toUnsignedLong( i ) );
			espectaculo.setIdFestival( idFestival );
			espectaculo.setDuracion( nextIntBetween( MIN_DURATION_ESPECTACULO, MAX_DURATION_ESPECTACULO ) );
			espectaculo.setCostoRealizacion( nextFloatBetween( MIN_COSTO_ESPECTACULO, MAX_COSTO_ESPECTACULO ) );
			espectaculo.setIdioma( LANGUAGES[ nextInt( LANGUAGES.length ) ] );
			espectaculo.setNombre( ESPECTACULOS_NAME[ nextInt( ESPECTACULOS_NAME.length ) ] );
			espectaculo.setIdClasificacion( CLASIFICACIONES[ nextInt( CLASIFICACIONES.length ) ].getId( ) );
			espectaculo.setGeneros( getRandomFrom( GENEROS, MIN_GENEROS_ESPECTACULO, MAX_GENEROS_ESPECTACULO ) );
			espectaculo.setReqs( getRandomFrom( REQUERIMIENTOS, MIN_REQUERIMIENTOS_ESPECTACULO, MAX_REQUERIMIENTOS_ESPECTACULO ) );
			espectaculo.setDescripcion( textOf( MIN_DESCRIPCION_ESPECTACULO, MAX_DESCRIPCION_ESPECTACULO ) );
			espectaculo.setCompanias( getRandomFrom( companias.toArray( new CompaniaDeTeatro[ 0 ] ), MIN_COMPANIAS_ESPECTACULO, MAX_COMPANIAS_ESPECTACULO ) );
			
			espectaculos.add( espectaculo );
			generarFunciones( espectaculo );
		}
	}
	
	private void generarFunciones( Espectaculo espectaculo )
	{
		Optional<Festival> optional = festivales.stream( ).filter( f -> f.getId( ).equals( espectaculo.getIdFestival( ) ) ).findAny( );
		if( optional.isPresent( ) )
		{
			Festival festival = optional.get( );
			long dias = festival.getFechaFin( ).getTime( ) - festival.getFechaInicio( ).getTime( );
			dias = dias / ( 1000 * 60 * 60 * 24 );
			for( int i = 0; i < NUM_FUNCIONES; i++ )
			{
				Funcion funcion = new Funcion( );
				funcion.setIdEspectaculo( espectaculo.getId( ) );
				funcion.setSeRealiza( nextInt( 2 ) );
				funcion.setFecha( generateNextRandomTimestamp( festival.getFechaInicio( ), Math.abs( ( int ) dias ), 1440 ) );
				funcion.setIdLugar( LUGARES[ nextInt( LUGARES.length ) ].getId( ) );
				
				funciones.put( keyOf( funcion ), funcion );
				
				generarCostoLocalidades( funcion );
			}
		}
	}
	
	private void generarSillas( Long idLugar, List<Localidad> localidades )
	{
		for( Localidad localidad : localidades )
		{
			List<Silla> sillasLoc = new LinkedList<>( );
			
			int filas = nextIntBetween( MIN_CANTIDAD_FILAS, MAX_CANTIDAD_FILAS );
			for( int f = 1; f <= filas; f++ )
			{
				int max = nextIntBetween( MIN_CANTIDAD_SILLAS_POR_FILA, MAX_CANTIDAD_SILLAS_POR_FILA );
				for( int s = 1; s <= max; s++ )
				{
					Silla silla = new Silla( );
					silla.setIdLugar( idLugar );
					silla.setIdLocalidad( localidad.getId( ) );
					silla.setNumSilla( s );
					silla.setNumFila( f );
					
					sillasLoc.add( silla );
				}
			}
			
			sillas.put( keyOf( idLugar, localidad.getId( ) ), sillasLoc );
		}
	}
	
	private void generarInfoLugares( )
	{
		for( Lugar lugar : LUGARES )
		{
			List<Localidad> localidades = new LinkedList<>( );
			for( Localidad localidad : LOCALIDADES )
			{
				//				boolean in = nextInt( 10 ) % 2 == 0;
				//				if( in )
				//				{
				Localidad l = new Localidad( );
				l.setId( localidad.getId( ) );
				l.setNombre( localidad.getNombre( ) );
				l.setEsNumerado( nextInt( 10 ) % 2 == 0 ? 0 : 1 );
				l.setCapacidad( nextInt( MAX_CAPACIDAD_LOCALIDAD ) );
				
				localidades.add( l );
				//				}
			}
			lugarLocalidades.put( lugar.getId( ), localidades );
			
			generarSillas( lugar.getId( ), localidades );
			
			lugar.setRequerimientosTecnicos( getRandomFrom( REQUERIMIENTOS, MIN_REQUERIMIENTOS_LUGAR, MAX_REQUERIMIENTOS_LUGAR ) );
			lugar.setAccesibilidades( getRandomFrom( ACCESIBILIDADES, MIN_ACCESIBIILIDADES_LUGAR, MAX_ACCESIBILIDADES_LUGAR ) );
		}
	}
	
	private void generarCostoLocalidades( Funcion funcion )
	{
		List<CostoLocalidad> localidades = new LinkedList<>( );
		for( Localidad localidad : lugarLocalidades.get( funcion.getIdLugar( ) ) )
		{
			CostoLocalidad l = new CostoLocalidad( );
			l.setFecha( funcion.getFecha( ) );
			l.setIdLugar( funcion.getIdLugar( ) );
			l.setIdLocalidad( localidad.getId( ) );
			l.setCosto( nextFloatBetween( MIN_COSTO_LOCALIDAD, MAX_COSTO_LOCALIDAD ) );
			
			localidades.add( l );
		}
		costoLocalidades.put( keyOf( funcion ), localidades );
	}
	
	private void generarBoletas( )
	{
		for( Map.Entry<String, List<CostoLocalidad>> clEntry : costoLocalidades.entrySet( ) )
		{
			Funcion funcion = funciones.get( clEntry.getKey( ) );
			for( CostoLocalidad localidad : clEntry.getValue( ) )
			{
				for( Silla silla : sillas.get( keyOf( funcion.getIdLugar( ), localidad.getIdLocalidad( ) ) ) )
				{
					Boleta boleta = new Boleta( );
					boleta.setFecha( funcion.getFecha( ) );
					boleta.setIdLugar( funcion.getIdLugar( ) );
					boleta.setIdLocalidad( localidad.getIdLocalidad( ) );
					boleta.setNumBoleta( Integer.toUnsignedLong( boletas.size( ) ) );
					boleta.setNumeroFila( Integer.toUnsignedLong( silla.getNumFila( ) ) );
					boleta.setNumeroSilla( Integer.toUnsignedLong( silla.getNumSilla( ) ) );
					
					UsuarioRegistrado user = usuariosRegistrados.get( nextInt( usuariosRegistrados.size( ) ) );
					boleta.setIdUsuario( user.getIdentificacion( ) );
					boleta.setTipoIdUsuario( user.getTipoIdentificacion( ) );
					
					boletas.add( boleta );
				}
			}
		}
	}
	
	private void generarUsuarios( )
	{
		for( Festival festival : festivales )
		{
			for( int i = 0; i < NUM_USUARIOS; i++ )
			{
				Usuario usuario = generateUsuario( festival.getId( ) );
				usuario.setRol( ROLES[ nextInt( ROLES.length ) ] );
				
				usuarios.add( usuario );
			}
		}
	}
	
	private Usuario generateUsuario( Long idFestival )
	{
		int i = usuarios.size( );
		String nombre = PEOPLE_NAME[ i % PEOPLE_NAME.length ];
		
		Usuario usuario = new Usuario( );
		usuario.setIdentificacion( Integer.toUnsignedLong( i ) );
		usuario.setNombre( nombre );
		usuario.setIdFestival( idFestival );
		usuario.setPassword( generatePassword( ) );
		usuario.setEmail( nombre );
		usuario.setTipoIdentificacion( TIPOS_ID[ nextInt( TIPOS_ID.length ) ] );
		return usuario;
	}
	
	private void generarUsuariosRegistrados( )
	{
		for( Festival festival : festivales )
		{
			for( int i = 0; i < NUM_ESPECTADORES; i++ )
			{
				UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado( generateUsuario( festival.getId( ) ) );
				usuarioRegistrado.setEdad( nextIntBetween( MIN_EDAD_ESPECTADORES, MAX_EDAD_ESPECTADORES ) );
				usuarioRegistrado.setRol( Usuario.USUARIO_REGISTRADO );
				
				usuarios.add( usuarioRegistrado );
				usuariosRegistrados.add( usuarioRegistrado );
			}
		}
	}
	
	private void generarAbonos( )
	{
		for( Festival festival : festivales )
		{
			for( int i = 0; i < NUM_ABONOS; i++ )
			{
				Abono abono = new Abono( );
				abono.setDescuento( nextFloatBetween( MIN_DESCUENTO_ABONO, MAX_DESCUENTO_ABONO ) );
				abono.setIdFestival( festival.getId( ) );
				
				abono.setFunciones( getRandomFrom( costoLocalidades.values( )
				                                                   .stream( )
				                                                   .flatMap( Collection::stream )
				                                                   .collect( Collectors.toList( ) )
				                                                   .toArray( new CostoLocalidad[ 0 ] ), MIN_FUNCIONES_ABONO, MAX_FUNCION_ABONO ) );
				
				UsuarioRegistrado user = usuariosRegistrados.get( nextInt( usuariosRegistrados.size( ) ) );
				
				abono.setIdUsuario( user.getIdentificacion( ) );
				abono.setTipoId( user.getTipoIdentificacion( ) );
				
				abonos.add( abono );
			}
		}
	}
	
	private void generarPreferencias( )
	{
		for( UsuarioRegistrado registrado : usuariosRegistrados )
		{
			int cant = nextIntBetween( MIN_PREFERENCIA_GENEROS_USER, MAX_PREFERENCIA_GENEROS_USER );
			for( Genero genero : GENEROS )
			{
				preferenciaGenero.add( new Object[] { registrado.getIdentificacion( ), registrado.getTipoIdentificacion( ), genero.getId( ) } );
				if( --cant == 0 )
				{
					break;
				}
			}
			cant = nextIntBetween( MIN_PREFERENCIA_LUGAR_USER, MAX_PREFERENCIA_LUGAR_USER );
			for( Lugar lugar : LUGARES )
			{
				preferenciaLugares.add( new Object[] { registrado.getIdentificacion( ), registrado.getTipoIdentificacion( ), lugar.getId( ) } );
				if( --cant == 0 )
				{
					break;
				}
			}
		}
	}
	
	// -------------------------------------------------------------------
	
	private String keyOf( Long idLugar, Long idLocalidad )
	{
		return String.format( "%s:%s", idLugar, idLocalidad );
	}
	
	private String keyOf( Funcion funcion )
	{
		return String.format( "%s (%s)", SQLUtils.DateUtils.timeToString( funcion.getFecha( ) ), funcion.getIdLugar( ) );
	}
	
	private String textOf( int min, int max )
	{
		int tam = nextIntBetween( min, max );
		return TEXT.substring( 0, tam );
	}
	
	private <T> List<T> getRandomFrom( T[] array, int min, int max )
	{
		List<T> list = new LinkedList<>( );
		int cant = nextIntBetween( min, max );
		while( list.size( ) != cant )
		{
			T toAdd = array[ nextInt( array.length ) ];
			if( !list.contains( toAdd ) )
			{
				list.add( toAdd );
			}
		}
		return list;
	}
	
	private String generatePassword( )
	{
		return new BigInteger( 130, new SecureRandom( ) ).toString( 32 );
	}
	
	private String getPaginaWeb( String nombresCompania )
	{
		return String.format( "%s.com", nombresCompania.replaceAll( "(?![0-9]|[a-z]|[A-Z]).", "" ).toLowerCase( ) );
	}
	
	private String getEmail( String nombre )
	{
		return String.format( "%s@%s", nombre.replaceAll( "(?![0-9]|[a-z]|[A-Z]| ).", "" ).replaceAll( " ", "_" ), MAIL[ nextInt( MAIL.length ) ] )
		             .toLowerCase( );
	}
	
	private void toFile( String path ) throws Exception
	{
		List<Long[]> tempOfrecen = new LinkedList<>( );
		List<Long[]> tempGeneroEspectaculo = new LinkedList<>( );
		List<Long[]> tempRequerimientos = new LinkedList<>( );
		List<Long[]> tempLugarAccesibilidades = new LinkedList<>( );
		List<Long[]> tempLugarReq = new LinkedList<>( );
		
		PrintWriter out = new PrintWriter( new FileOutputStream( path ), true );
		for( Genero genero : GENEROS )
		{
			out.println( DataSQL.Insert.genero( genero ) );
		}
		out.println( );
		for( Clasificacion clasificacion : CLASIFICACIONES )
		{
			out.println( DataSQL.Insert.clasificacion( clasificacion ) );
		}
		out.println( );
		for( Accesibilidad accesibilidad : ACCESIBILIDADES )
		{
			out.println( DataSQL.Insert.accesibilidad( accesibilidad ) );
		}
		out.println( );
		for( RequerimientoTecnico requerimiento : REQUERIMIENTOS )
		{
			out.println( DataSQL.Insert.requerimientoTecnico( requerimiento ) );
		}
		out.println( );
		for( Localidad localidad : LOCALIDADES )
		{
			out.println( DataSQL.Insert.localidad( localidad ) );
		}
		out.println( );
		for( Lugar lugar : LUGARES )
		{
			out.println( DataSQL.Insert.lugar( lugar ) );
			for( Accesibilidad accesibilidad : lugar.getAccesibilidades( ) )
			{
				tempLugarAccesibilidades.add( new Long[] { lugar.getId( ), accesibilidad.getId( ) } );
			}
			for( RequerimientoTecnico requerimientoTecnico : lugar.getRequerimientosTecnicos( ) )
			{
				tempLugarReq.add( new Long[] { lugar.getId( ), requerimientoTecnico.getId( ) } );
			}
		}
		out.println( );
		for( Festival festival : festivales )
		{
			out.println( DataSQL.Insert.festival( festival ) );
		}
		out.println( );
		for( Usuario usuario : usuarios )
		{
			out.println( DataSQL.Insert.usuario( usuario ) );
		}
		out.println( );
		for( UsuarioRegistrado usuario : usuariosRegistrados )
		{
			out.println( DataSQL.Insert.usuarioRegistrado( usuario ) );
		}
		out.println( );
		for( CompaniaDeTeatro companiaDeTeatro : companias )
		{
			out.println( DataSQL.Insert.companiaDeTeatro( companiaDeTeatro ) );
		}
		out.println( );
		for( Espectaculo espectaculo : espectaculos )
		{
			out.println( DataSQL.Insert.espectaculo( espectaculo ) );
			for( CompaniaDeTeatro c : espectaculo.getCompanias( ) )
			{
				tempOfrecen.add( new Long[] { c.getIdentificacion( ), espectaculo.getId( ) } );
			}
			for( Genero genero : espectaculo.getGeneros( ) )
			{
				tempGeneroEspectaculo.add( new Long[] { espectaculo.getId( ), genero.getId( ) } );
			}
			for( RequerimientoTecnico requerimiento : espectaculo.getReqs( ) )
			{
				tempRequerimientos.add( new Long[] { espectaculo.getId( ), requerimiento.getId( ) } );
			}
		}
		out.println( );
		for( Funcion funcion : funciones.values( ) )
		{
			out.println( DataSQL.Insert.funcion( funcion ) );
		}
		out.println( );
		for( Long[] ofrece : tempOfrecen )
		{
			out.println( DataSQL.Insert.ofrecen( ofrece[ 0 ], ofrece[ 1 ] ) );
		}
		out.println( );
		for( Long[] genero : tempGeneroEspectaculo )
		{
			out.println( DataSQL.Insert.espectaculoGeneros( genero[ 0 ], genero[ 1 ] ) );
		}
		out.println( );
		for( Long[] requerimiento : tempRequerimientos )
		{
			out.println( DataSQL.Insert.espectaculoRequerimiento( requerimiento[ 0 ], requerimiento[ 1 ] ) );
		}
		out.println( );
		for( Map.Entry<Long, List<Localidad>> entry : lugarLocalidades.entrySet( ) )
		{
			Long idLugar = entry.getKey( );
			for( Localidad localidad : entry.getValue( ) )
			{
				LugarLocalidad ll = new LugarLocalidad( );
				ll.setCapacidad( localidad.getCapacidad( ) );
				ll.setEsNumerado( localidad.getEsNumerada( ) );
				ll.setIdLugar( idLugar );
				ll.setIdLocalidad( localidad.getId( ) );
				
				out.println( DataSQL.Insert.lugarLocalidad( ll ) );
			}
		}
		out.println( );
		for( Map.Entry<String, List<CostoLocalidad>> entry : costoLocalidades.entrySet( ) )
		{
			for( CostoLocalidad costoLocalidad : entry.getValue( ) )
			{
				out.println( DataSQL.Insert.costoLocalidad( costoLocalidad ) );
			}
		}
		out.println( );
		for( List<Silla> list : sillas.values( ) )
		{
			for( Silla silla : list )
			{
				out.println( DataSQL.Insert.silla( silla ) );
			}
		}
		out.println( );
		for( Boleta boleta : boletas )
		{
			out.println( DataSQL.Insert.boleta( boleta ) );
		}
		out.println( );
		for( Long[] lugarAccesibilidad : tempLugarAccesibilidades )
		{
			out.println( DataSQL.Insert.lugarAccesibilidad( lugarAccesibilidad[ 0 ], lugarAccesibilidad[ 1 ] ) );
		}
		out.println( );
		for( Long[] lugarReqs : tempLugarReq )
		{
			out.println( DataSQL.Insert.lugarRequerimiento( lugarReqs[ 0 ], lugarReqs[ 1 ] ) );
		}
		out.println( );
		for( Object[] prefGenero : preferenciaGenero )
		{
			out.println( DataSQL.Insert.preferenciaGenero( ( long ) prefGenero[ 0 ], ( String ) prefGenero[ 1 ], ( long ) prefGenero[ 2 ] ) );
		}
		out.println( );
		for( Object[] prefLugar : preferenciaLugares )
		{
			out.println( DataSQL.Insert.preferenciaLugar( ( long ) prefLugar[ 0 ], ( String ) prefLugar[ 1 ], ( long ) prefLugar[ 2 ] ) );
		}
		out.println( );
		for( Abono abono : abonos )
		{
			out.println( DataSQL.Insert.abono( abono ) );
			for( CostoLocalidad clf : abono.getFunciones( ) )
			{
				out.println( DataSQL.Insert.abonoFuncion( abono, clf.getIdLugar( ), clf.getIdLocalidad( ), clf.getFecha( ) ) );
			}
		}
		out.println( );
	}
	
	public void statistics( )
	{
		System.out.println( String.format( "Festivales: %s", festivales.size( ) ) );
		System.out.println( String.format( "Companias: %s", companias.size( ) ) );
		System.out.println( String.format( "Espectaculos: %s", espectaculos.size( ) ) );
		System.out.println( String.format( "Funciones: %s", funciones.size( ) ) );
		System.out.println( String.format( "Sillas: %s", sillas.entrySet( ).stream( ).mapToInt( l -> l.getValue( ).size( ) ).sum( ) ) );
		System.out.println( String.format( "Localidades: %s", lugarLocalidades.entrySet( )
		                                                                      .stream( )
		                                                                      .mapToInt( l -> l.getValue( ).size( ) )
		                                                                      .sum( ) ) );
		System.out.println( String.format( "Costo_Localidad: %s", costoLocalidades.entrySet( )
		                                                                          .stream( )
		                                                                          .mapToInt( l -> l.getValue( ).size( ) )
		                                                                          .sum( ) ) );
		System.out.println( String.format( "Boletas: %s", boletas.size( ) ) );
		System.out.println( String.format( "Abonos: %s", abonos.size( ) ) );
	}
	
	public void menu( )
	{
		System.out.println( "Ingrese una opcion: \n -FESTIVALES\n -COMPANIAS\n -ESPECTACULOS\n -FUNCIONES\n -SILLAS\n -LOCALIDADES\n -COSTO_LOCALIDAD\n -BOLETAS\n -ABONOS \n--END" );
		Scanner sc = new Scanner( System.in );
		String line;
		while( !( line = sc.nextLine( ) ).equals( "END" ) )
		{
			switch( line )
			{
				case "FESTIVALES":
					System.out.println( "Ingrese el indice del festival deseado" );
					System.out.println( festivales.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "COMPANIAS":
					System.out.println( "Ingrese el indice de la compania deseada" );
					System.out.println( companias.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "ESPECTACULOS":
					System.out.println( "Ingrese el indice del espectaculo deseado" );
					System.out.println( espectaculos.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "FUNCIONES":
					System.out.println( "Ingrese el indice de la funcion deseada" );
					System.out.println( funciones.entrySet( ).toArray( new Map.Entry[ 0 ] )[ Integer.parseInt( sc.nextLine( ) ) ].getValue( ) );
					break;
				case "SILLAS":
					System.out.println( "Ingrese el indice de la silla deseada" );
					System.out.println( sillas.entrySet( ).toArray( new Map.Entry[ 0 ] )[ Integer.parseInt( sc.nextLine( ) ) ] );
					break;
				case "LOCALIDADES":
					System.out.println( "Ingrese el indice de la localidad deseada" );
					System.out.println( lugarLocalidades.get( Long.parseLong( sc.nextLine( ) ) ) );
					break;
				case "COSTO_LOCALIDAD":
					System.out.println( "Ingrese el indice del costo-localidad deseado" );
					System.out.println( costoLocalidades.entrySet( ).toArray( new Map.Entry[ 0 ] )[ Integer.parseInt( sc.nextLine( ) ) ] );
					break;
				case "BOLETAS":
					System.out.println( "Ingrese el indice de la boleta deseada" );
					System.out.println( boletas.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "ABONOS":
					System.out.println( "Ingrese el indice del abono deseado" );
					System.out.println( abonos.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
			}
		}
		sc.close( );
	}
	
	public static void main( String[] args )
	{
		DataGen d = new DataGen( );
		d.statistics( );
		try
		{
			d.toFile( "data.sql" );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}