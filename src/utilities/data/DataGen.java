package utilities.data;

import utilities.DateUtils;
import vos.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

import static utilities.data.DataRandom.*;

public class DataGen implements DataConstant, DataControl
{
	private List<Festival> festivales;
	
	private List<Espectaculo> espectaculos;
	
	private List<CompaniaDeTeatro> companias;
	
	private HashMap<String, Funcion> funciones;
	
	private HashMap<Long, List<Localidad>> lugarLocalidades;
	
	private HashMap<String, List<Localidad>> costoLocalidades;
	
	private HashMap<String, List<Silla>> sillas;
	
	private List<Boleta> boletas;
	
	private List<Usuario> usuarios;
	
	private List<UsuarioRegistrado> usuariosRegistrados;
	
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
		
		// Boletas
		generarBoletas( );
		System.out.println( "Boletas Done" );
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
		List<Localidad> localidades = new LinkedList<>( );
		for( Localidad localidad : lugarLocalidades.get( funcion.getIdLugar( ) ) )
		{
			Localidad l = new Localidad( );
			l.setId( localidad.getId( ) );
			l.setNombre( localidad.getNombre( ) );
			l.setCosto( nextFloatBetween( MIN_COSTO_LOCALIDAD, MAX_COSTO_LOCALIDAD ) );
			
			localidades.add( l );
		}
		costoLocalidades.put( keyOf( funcion ), localidades );
	}
	
	private void generarBoletas( )
	{
		for( Map.Entry<String, List<Localidad>> clEntry : costoLocalidades.entrySet( ) )
		{
			Funcion funcion = funciones.get( clEntry.getKey( ) );
			for( Localidad localidad : clEntry.getValue( ) )
			{
				for( Silla silla : sillas.get( keyOf( funcion.getIdLugar( ), localidad.getId( ) ) ) )
				{
					Boleta boleta = new Boleta( );
					boleta.setFecha( funcion.getFecha( ) );
					boleta.setIdLugar( funcion.getIdLugar( ) );
					boleta.setIdLocalidad( localidad.getId( ) );
					boleta.setNumBoleta( Integer.toUnsignedLong( boletas.size( ) ) );
					boleta.setNumeroFila( Integer.toUnsignedLong( silla.getNumFila( ) ) );
					boleta.setNumeroSilla( Integer.toUnsignedLong( silla.getNumSilla( ) ) );
					
					UsuarioRegistrado user = usuariosRegistrados.get( nextInt( usuariosRegistrados.size( ) ) );
					boleta.setIdUsuario( user.getIdentificacion( ) );
					boleta.setTipoIdUsuario( user.getTipoIdentificacion( ) );
					System.out.println( boletas.size( ) );
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
	
	// -------------------------------------------------------------------
	
	private Long[] valuesOf( String key )
	{
		return Arrays.stream( key.split( ":" ) ).map( Long::parseLong ).toArray( Long[]::new );
	}
	
	private String keyOf( Long idLugar, Long idLocalidad )
	{
		return String.format( "%s:%s", idLugar, idLocalidad );
	}
	
	private String keyOf( Funcion funcion )
	{
		return String.format( "%s (%s)", DateUtils.timeToString( funcion.getFecha( ) ), funcion.getIdLugar( ) );
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
	
	public static void main( String[] args )
	{
		Long init = System.currentTimeMillis( );
		DataGen d = new DataGen( );
		
		System.out.println( String.format( "Festivales: %s", d.festivales.size( ) ) );
		System.out.println( String.format( "Companias: %s", d.companias.size( ) ) );
		System.out.println( String.format( "Espectaculos: %s", d.espectaculos.size( ) ) );
		System.out.println( String.format( "Funciones: %s", d.funciones.size( ) ) );
		System.out.println( String.format( "Sillas: %s", d.sillas.entrySet( ).stream( ).mapToInt( l -> l.getValue( ).size( ) ).sum( ) ) );
		System.out.println( String.format( "Localidades: %s", d.lugarLocalidades.entrySet( )
		                                                                        .stream( )
		                                                                        .mapToInt( l -> l.getValue( ).size( ) )
		                                                                        .sum( ) ) );
		System.out.println( String.format( "Costo_Localidad: %s", d.costoLocalidades.entrySet( )
		                                                                            .stream( )
		                                                                            .mapToInt( l -> l.getValue( ).size( ) )
		                                                                            .sum( ) ) );
		System.out.println( String.format( "Boletas: %s", d.boletas.size( ) ) );
		
		System.out.println( System.currentTimeMillis( ) - init + " ms" );
		
		System.out.println( "Ingrese una opcion: \n -FESTIVALES\n -COMPANIAS\n -ESPECTACULOS\n -FUNCIONES\n -SILLAS\n -LOCALIDADES\n -COSTO_LOCALIDAD\n -BOLETAS \n--END" );
		Scanner sc = new Scanner( System.in );
		String line;
		while( !( line = sc.nextLine( ) ).equals( "END" ) )
		{
			switch( line )
			{
				case "FESTIVALES":
					System.out.println( "Ingrese el indice del festival deseado" );
					System.out.println( d.festivales.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "COMPANIAS":
					System.out.println( "Ingrese el indice de la compania deseada" );
					System.out.println( d.companias.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "ESPECTACULOS":
					System.out.println( "Ingrese el indice del espectaculo deseado" );
					System.out.println( d.espectaculos.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
				case "FUNCIONES":
					System.out.println( "Ingrese el indice de la funcion deseada" );
					System.out.println( d.funciones.entrySet( ).toArray( new Map.Entry[ 0 ] )[ Integer.parseInt( sc.nextLine( ) ) ].getValue( ) );
					break;
				case "SILLAS":
					System.out.println( "Ingrese el indice de la silla deseada" );
					System.out.println( d.sillas.entrySet( ).toArray( new Map.Entry[ 0 ] )[ Integer.parseInt( sc.nextLine( ) ) ] );
					break;
				case "LOCALIDADES":
					System.out.println( "Ingrese el indice de la localidad deseada" );
					System.out.println( d.lugarLocalidades.get( Long.parseLong( sc.nextLine( ) ) ) );
					break;
				case "COSTO_LOCALIDAD":
					System.out.println( "Ingrese el indice del costo-localidad deseado" );
					System.out.println( d.costoLocalidades.entrySet( ).toArray( new Map.Entry[ 0 ] )[ Integer.parseInt( sc.nextLine( ) ) ] );
					break;
				case "BOLETAS":
					System.out.println( "Ingrese el indice de la boleta deseada" );
					System.out.println( d.boletas.get( Integer.parseInt( sc.nextLine( ) ) ) );
					break;
			}
		}
		sc.close( );
	}
}