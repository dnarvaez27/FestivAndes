package protocolos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class ProtocoloRFC5 implements Protocolo<ProtocoloRFC5>
{
	private String appName;
	
	@JsonProperty( value = "nombreSitio" )
	private String nombreSitio;
	
	@JsonProperty( value = "tipoDeSitio" )
	private String tipoDeSitio;
	
	@JsonProperty( value = "espectaculo" )
	private String espectaculo;
	
	@JsonProperty( value = "categoria" )
	private String categoria;
	
	@JsonProperty( value = "boletasVendidas" )
	private Integer boletasVendidas;
	
	@JsonProperty( value = "valorTotalFacturado" )
	private Double valorTotalFacturado;
	
	@JsonProperty( value = "funciones" )
	private List<RFC5Funcion> funciones;
	
	public class RFC5Funcion
	{
		//TODO
		@JsonProperty( value = "idFuncion" )
		private Long idFuncion;
		
		@JsonProperty( value = "asistentes" )
		private Integer asistentes;
		
		@JsonProperty( value = "proporcionAsistencia" )
		private Double proporcionAsistencia;
		
		@JsonProperty( value = "producido" )
		private Double producido;
		
		RFC5Funcion( String respuesta )
		{
			String[] componentes = respuesta.split( SEPARADOR_DATOS_ELEMENTOS_LISTA );
			this.idFuncion = Long.parseLong( componentes[ 0 ] );
			this.asistentes = Integer.parseInt( componentes[ 1 ] );
			this.proporcionAsistencia = Double.parseDouble( componentes[ 2 ] );
			this.producido = Double.parseDouble( componentes[ 3 ] );
		}
		
		public Long getIdFuncion( )
		{
			return idFuncion;
		}
		
		public void setIdFuncion( Long idFuncion )
		{
			this.idFuncion = idFuncion;
		}
		
		public Integer getAsistentes( )
		{
			return asistentes;
		}
		
		public void setAsistentes( Integer asistentes )
		{
			this.asistentes = asistentes;
		}
		
		public Double getProporcionAsistencia( )
		{
			return proporcionAsistencia;
		}
		
		public void setProporcionAsistencia( Double proporcionAsistencia )
		{
			this.proporcionAsistencia = proporcionAsistencia;
		}
		
		public Double getProducido( )
		{
			return producido;
		}
		
		public void setProducido( Double producido )
		{
			this.producido = producido;
		}
		
		public String toString( )
		{
			StringBuilder builder = new StringBuilder( );
			
			builder.append( idFuncion ).append( SEPARADOR_DATOS_ELEMENTOS_LISTA );
			builder.append( asistentes ).append( SEPARADOR_DATOS_ELEMENTOS_LISTA );
			builder.append( proporcionAsistencia ).append( SEPARADOR_DATOS_ELEMENTOS_LISTA );
			builder.append( producido ).append( SEPARADOR_DATOS_ELEMENTOS_LISTA );
			
			return builder.toString( );
		}
		
	}
	
	private ProtocoloRFC5( String respuesta )
	{
		String[] componentes = respuesta.split( SEPARADOR_PARAMS );
		
		this.appName = componentes[ 0 ];
		this.nombreSitio = componentes[ 1 ];
		this.tipoDeSitio = componentes[ 2 ];
		this.espectaculo = componentes[ 3 ];
		this.categoria = componentes[ 4 ];
		this.boletasVendidas = Integer.parseInt( componentes[ 5 ] );
		this.valorTotalFacturado = Double.parseDouble( componentes[ 6 ] );
		this.funciones = new LinkedList<>( );
		
		String[] subcomponentes = componentes[ 7 ].split( SEPARADOR_LISTA_RFC5 );
		
		for( String subcomponente : subcomponentes )
		{
			funciones.add( new RFC5Funcion( subcomponente ) );
		}
	}
	
	public String getNombreSitio( )
	{
		return nombreSitio;
	}
	
	public void setNombreSitio( String nombreSitio )
	{
		this.nombreSitio = nombreSitio;
	}
	
	public String getTipoDeSitio( )
	{
		return tipoDeSitio;
	}
	
	public void setTipoDeSitio( String tipoDeSitio )
	{
		this.tipoDeSitio = tipoDeSitio;
	}
	
	public String getEspectaculo( )
	{
		return espectaculo;
	}
	
	public void setEspectaculo( String espectaculo )
	{
		this.espectaculo = espectaculo;
	}
	
	public String getCategoria( )
	{
		return categoria;
	}
	
	public void setCategoria( String categoria )
	{
		this.categoria = categoria;
	}
	
	public Integer getBoletasVendidas( )
	{
		return boletasVendidas;
	}
	
	public void setBoletasVendidas( Integer boletasVendidas )
	{
		this.boletasVendidas = boletasVendidas;
	}
	
	public Double getValorTotalFacturado( )
	{
		return valorTotalFacturado;
	}
	
	public void setValorTotalFacturado( Double valorTotalFacturado )
	{
		this.valorTotalFacturado = valorTotalFacturado;
	}
	
	public List<RFC5Funcion> getFunciones( )
	{
		return funciones;
	}
	
	public void setFunciones( List<RFC5Funcion> funciones )
	{
		this.funciones = funciones;
	}
	
	public String toString( )
	{
		StringBuilder builder = new StringBuilder( );
		builder.append( appName ).append( SEPARADOR_PARAMS );
		builder.append( nombreSitio ).append( SEPARADOR_PARAMS );
		builder.append( tipoDeSitio ).append( SEPARADOR_PARAMS );
		builder.append( espectaculo ).append( SEPARADOR_PARAMS );
		builder.append( categoria ).append( SEPARADOR_PARAMS );
		builder.append( boletasVendidas ).append( SEPARADOR_PARAMS );
		builder.append( valorTotalFacturado ).append( SEPARADOR_PARAMS );
		
		for( int i = 0; i < funciones.size( ); i++ )
		{
			builder.append( funciones.get( i ).toString( ) );
			builder.append( i != ( funciones.size( ) - 1 ) ? SEPARADOR_LISTA_RFC5 : "" );
		}
		
		return builder.toString( );
	}
	
	@Override
	public ProtocoloRFC5 fromProtocol( String s )
	{
		return new ProtocoloRFC5( s );
	}
}
