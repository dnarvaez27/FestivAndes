package jms;

import protocolos.Protocolo;
import protocolos.ProtocoloCompania;
import tm.CompaniaDeTeatroCM;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.NamingException;
import java.util.Arrays;

/**
 * @author dnarvaez27
 */
public class CompaniaDeTeatroJMS extends JMSManager<ProtocoloCompania>
{
	private Long idCompania;
	
	private String tipoIdCompania;
	
	/**
	 * JMS instance, given by the Singleton pattern
	 */
	private static CompaniaDeTeatroJMS instancia;
	
	/**
	 * Reference to the TransactionManager (Connection Manager) used to solve the given requirements requested
	 */
	private CompaniaDeTeatroCM transactionManager;
	
	/**
	 * Retrieves the instance of the Singleton class: CompaniaDeTeatroJMS
	 *
	 * @param transactionManager - instance of the TransactionManager to use in the class
	 * @return FuncionJMS - Instance of the Singleton class
	 */
	public static CompaniaDeTeatroJMS getInstance( CompaniaDeTeatroCM transactionManager )
	{
		instancia = instancia == null ? new CompaniaDeTeatroJMS( ) : instancia;
		instancia.transactionManager = transactionManager;
		return instancia;
	}
	
	/**
	 * Method that is called when a message is received.<br>
	 * The method calls the {@link JMSManager#onMessageSetUp(Message, String, String)}
	 * and by the result given, takes action if is an answer (Someone replies) or is a request (Should be responded)
	 *
	 * @param message Message received
	 */
	
	@Override
	public void onMessage( Message message )
	{
		try
		{
			OnMessageResponse response = onMessageSetUp( message, ELIMINAR_COMPANIA_ASK, ELIMINAR_COMPANIA_RESPONSE );
			if( response.isAnswer( ) )
			{
				if( waiting )
				{
					this.respuesta.add( new ProtocoloCompania( response.getParams( ) ) );
					this.numberApps++;
				}
			}
			else if( response.getQueue( ) != null )
			{
				ProtocoloCompania protocoloCompania = new ProtocoloCompania( );
				try
				{
					protocoloCompania = transactionManager.deleteCompaniaDeTeatro( protocolParamToId( response.getParams( ) ) );
				}
				catch( Exception e )
				{
					protocoloCompania.setResponse( 0 );
				}
				enqueueResponse( response.getQueue( ), protocoloCompania.toString( ) );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
	
	/**
	 * Method that sends a request protocol, by the request in the {@link #ELIMINAR_COMPANIA_ASK} constant with 1 parameters<br>
	 * <strong>MUST</strong> initialize idCompania & tipoIdCompania. ( {@link #setIdCompania(Long)}, {@link #setTipoIdCompania(String)} )
	 *
	 * @throws JMSException    Caso de JMSException
	 * @throws NamingException Caso de NamingException
	 */
	@Override
	void sendMessage( ) throws NamingException, JMSException
	{
		String params = idCompania + Protocolo.SEPARADOR_PARAMS + tipoIdCompania;
		sendMessageSetUp( ELIMINAR_COMPANIA_ASK, params );
	}
	
	/**
	 * Updates the idCompania of the CompaniaDeTeatroJMS by the one given by parameter
	 *
	 * @param idCompania The new idCompania of the CompaniaDeTeatroJMS
	 */
	public void setIdCompania( Long idCompania )
	{
		this.idCompania = idCompania;
	}
	
	/**
	 * Updates the tipoIdCompania of the CompaniaDeTeatroJMS by the one given by parameter
	 *
	 * @param tipoIdCompania The new tipoIdCompania of the CompaniaDeTeatroJMS
	 */
	public void setTipoIdCompania( String tipoIdCompania )
	{
		this.tipoIdCompania = tipoIdCompania;
	}
	
	private Long protocolParamToId( String param )
	{
		return Arrays.stream( param.split( Protocolo.SEPARADOR_PARAMS ) ).map( Long::parseLong ).toArray( Long[]::new )[ 0 ];
	}
}
