package protocolos;

public class ProtocoloFuncion implements Protocolo<ProtocoloFuncion>
{
	private String appName;
	
	private long idFuncion;
	
	private String fecha;
	
	private long idLugar;
	
	private long idEspectaculo;
	
	private boolean realizado;
	
	public ProtocoloFuncion( long idFuncion, String fecha, long idLugar, long idEspectaculo, boolean realizado )
	{
		super( );
		this.idFuncion = idFuncion;
		this.fecha = fecha;
		this.idLugar = idLugar;
		this.idEspectaculo = idEspectaculo;
		this.realizado = realizado;
	}
	
	public ProtocoloFuncion( )
	{
	
	}
	
	private ProtocoloFuncion( String respuesta )
	{
		String[] componentes = respuesta.split( SEPARADOR_PARAMS );
		this.appName = componentes[ 0 ];
		this.idFuncion = Long.parseLong( componentes[ 1 ] );
		this.fecha = componentes[ 2 ];
		this.idLugar = Long.parseLong( componentes[ 3 ] );
		this.idEspectaculo = Long.parseLong( componentes[ 4 ] );
		this.realizado = Boolean.parseBoolean( componentes[ 5 ] );
	}
	
	public long getIdFuncion( )
	{
		return idFuncion;
	}
	
	public void setIdFuncion( long idFuncion )
	{
		this.idFuncion = idFuncion;
	}
	
	public String getFecha( )
	{
		return fecha;
	}
	
	public void setFecha( String fecha )
	{
		this.fecha = fecha;
	}
	
	public long getIdLugar( )
	{
		return idLugar;
	}
	
	public void setIdLugar( long idLugar )
	{
		this.idLugar = idLugar;
	}
	
	public long getIdEspectaculo( )
	{
		return idEspectaculo;
	}
	
	public void setIdEspectaculo( long idEspectaculo )
	{
		this.idEspectaculo = idEspectaculo;
	}
	
	public boolean isRealizado( )
	{
		return realizado;
	}
	
	public void setRealizado( boolean realizado )
	{
		this.realizado = realizado;
	}
	
	/**
	 * Retrieves the appName of the ProtocoloFuncion
	 *
	 * @return The appName of the ProtocoloFuncion
	 */
	public String getAppName( )
	{
		return appName;
	}
	
	/**
	 * Updates the appName of the ProtocoloFuncion by the one given by parameter
	 *
	 * @param appName The new appName of the ProtocoloFuncion
	 */
	public void setAppName( String appName )
	{
		this.appName = appName;
	}
	
	public String toString( )
	{
		StringBuilder builder = new StringBuilder( );
		
		builder.append( appName ).append( SEPARADOR_PARAMS );
		builder.append( idFuncion ).append( SEPARADOR_PARAMS );
		builder.append( fecha ).append( SEPARADOR_PARAMS );
		builder.append( idLugar ).append( SEPARADOR_PARAMS );
		builder.append( idEspectaculo ).append( SEPARADOR_PARAMS );
		builder.append( realizado );
		
		return builder.toString( );
	}
	
	@Override
	public ProtocoloFuncion fromProtocol( String s )
	{
		return new ProtocoloFuncion( s );
	}
}
