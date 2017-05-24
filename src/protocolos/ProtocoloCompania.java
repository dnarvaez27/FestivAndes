package protocolos;

/**
 * Created by Dave on 21/05/2017.
 */
public class ProtocoloCompania implements Protocolo<ProtocoloCompania>
{
	private String appName;
	
	private Double response;
	
	public ProtocoloCompania( String appName, Double response )
	{
		this.appName = appName;
		this.response = response;
	}
	
	public ProtocoloCompania( )
	{
	
	}
	
	public ProtocoloCompania( String protocolo )
	{
		String[] componentes = protocolo.split( SEPARADOR_PARAMS );
		
		appName = componentes[ 0 ];
		response = Double.parseDouble( componentes[ 1 ] );
	}
	
	/**
	 * Retrieves the response of the ProtocoloCompania
	 *
	 * @return The response of the ProtocoloCompania
	 */
	public Double getResponse( )
	{
		return response;
	}
	
	/**
	 * Updates the response of the ProtocoloCompania by the one given by parameter
	 *
	 * @param response The new response of the ProtocoloCompania
	 */
	public void setResponse( Double response )
	{
		this.response = response;
	}
	
	/**
	 * Retrieves the appName of the ProtocoloCompania
	 *
	 * @return The appName of the ProtocoloCompania
	 */
	public String getAppName( )
	{
		return appName;
	}
	
	/**
	 * Updates the appName of the ProtocoloCompania by the one given by parameter
	 *
	 * @param appName The new appName of the ProtocoloCompania
	 */
	public void setAppName( String appName )
	{
		this.appName = appName;
	}
	
	@Override
	public ProtocoloCompania fromProtocol( String s )
	{
		return new ProtocoloCompania( s );
	}
	
	@Override
	public String toString( )
	{
		return appName + SEPARADOR_PARAMS + String.valueOf( response );
	}
}
