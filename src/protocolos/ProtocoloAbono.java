package protocolos;

import java.util.LinkedList;
import java.util.List;

public class ProtocoloAbono implements Protocolo<ProtocoloAbono>
{
	//TODO INFO BOLETA?
	
	private String appName;
	
	public static class FuncionAbono
	{
		private String nombreEspectaculo;
		
		private String fecha;
		
		private String nombreLocalidad;
		
		FuncionAbono( String respuesta )
		{
			String[] componentes = respuesta.split( SEPARADOR_DATOS_ELEMENTOS_LISTA );
			
			this.nombreEspectaculo = componentes[ 0 ];
			this.fecha = componentes[ 1 ];
			this.nombreLocalidad = componentes[ 2 ];
		}
		
		public FuncionAbono( )
		{
		}
		
		public FuncionAbono( String nombreEspectaculo, String fecha, String nombreLocalidad )
		{
			
			this.nombreEspectaculo = nombreEspectaculo;
			this.fecha = fecha;
			this.nombreLocalidad = nombreLocalidad;
		}
		
		public String getNombreEspectaculo( )
		{
			return nombreEspectaculo;
		}
		
		public void setNombreEspectaculo( String nombreEspectaculo )
		{
			this.nombreEspectaculo = nombreEspectaculo;
		}
		
		public String getFecha( )
		{
			return fecha;
		}
		
		public void setFecha( String fecha )
		{
			this.fecha = fecha;
		}
		
		public String getNombreLocalidad( )
		{
			return nombreLocalidad;
		}
		
		public void setNombreLocalidad( String nombreLocalidad )
		{
			this.nombreLocalidad = nombreLocalidad;
		}
		
		public String toString( )
		{
			return nombreEspectaculo + SEPARADOR_DATOS_ELEMENTOS_LISTA + fecha + SEPARADOR_DATOS_ELEMENTOS_LISTA + nombreLocalidad;
		}
	}
	
	private String nombreFestival;
	
	private long idUsuario;
	
	private String tipoId;
	
	private double descuento;
	
	private List<FuncionAbono> funciones;
	
	public ProtocoloAbono( String nombreFestival, long idUsuario, String tipoId, double descuento, List<FuncionAbono> funciones )
	{
		super( );
		this.nombreFestival = nombreFestival;
		this.idUsuario = idUsuario;
		this.tipoId = tipoId;
		this.descuento = descuento;
		this.funciones = funciones;
	}
	
	public ProtocoloAbono( )
	{
	
	}
	
	private ProtocoloAbono( String respuesta )
	{
		String[] componentes = respuesta.split( SEPARADOR_PARAMS );
		
		this.appName = componentes[ 0 ];
		this.nombreFestival = componentes[ 1 ];
		this.idUsuario = Integer.parseInt( componentes[ 2 ] );
		this.tipoId = componentes[ 3 ];
		this.descuento = Double.parseDouble( componentes[ 4 ] );
		this.funciones = new LinkedList<>( );
		String[] subcomponentes = componentes[ 5 ].split( SEPARADOR_ELEMENTOS_LISTA );
		
		for( String subcomponente : subcomponentes )
		{
			funciones.add( new FuncionAbono( subcomponente ) );
		}
	}
	
	public String getNombreFestival( )
	{
		return nombreFestival;
	}
	
	public void setNombreFestival( String nombreFestival )
	{
		this.nombreFestival = nombreFestival;
	}
	
	public long getIdUsuario( )
	{
		return idUsuario;
	}
	
	public void setIdUsuario( long idUsuario )
	{
		this.idUsuario = idUsuario;
	}
	
	public String getTipoId( )
	{
		return tipoId;
	}
	
	public void setTipoId( String tipoId )
	{
		this.tipoId = tipoId;
	}
	
	public double getDescuento( )
	{
		return descuento;
	}
	
	public void setDescuento( double descuento )
	{
		this.descuento = descuento;
	}
	
	/**
	 * Retrieves the appName of the ProtocoloAbono
	 *
	 * @return The appName of the ProtocoloAbono
	 */
	public String getAppName( )
	{
		return appName;
	}
	
	/**
	 * Updates the appName of the ProtocoloAbono by the one given by parameter
	 *
	 * @param appName The new appName of the ProtocoloAbono
	 */
	public void setAppName( String appName )
	{
		this.appName = appName;
	}
	
	public List<FuncionAbono> getFunciones( )
	{
		return funciones;
	}
	
	public void setFunciones( List<FuncionAbono> funciones )
	{
		this.funciones = funciones;
	}
	
	@Override
	public ProtocoloAbono fromProtocol( String s )
	{
		return new ProtocoloAbono( s );
	}
	
	public String toString( )
	{
		StringBuilder builder = new StringBuilder( );
		builder.append( appName ).append( SEPARADOR_PARAMS );
		builder.append( nombreFestival ).append( SEPARADOR_PARAMS );
		builder.append( idUsuario ).append( SEPARADOR_PARAMS );
		builder.append( tipoId ).append( SEPARADOR_PARAMS );
		builder.append( descuento ).append( SEPARADOR_PARAMS );
		
		for( int i = 0; i < funciones.size( ); i++ )
		{
			builder.append( funciones.get( i ).toString( ) );
			builder.append( i != ( funciones.size( ) - 1 ) ? SEPARADOR_ELEMENTOS_LISTA : "" );
		}
		return builder.toString( );
	}
}