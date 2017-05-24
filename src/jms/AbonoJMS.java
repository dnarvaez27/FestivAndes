package jms;

import protocolos.ProtocoloAbono;
import tm.AbonoCM;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.NamingException;

/**
 * @author dnarvaez27
 */
public class AbonoJMS extends JMSManager<ProtocoloAbono>
{
	private ProtocoloAbono abono;
	
	/**
	 * JMS instance, given by the Singleton pattern
	 */
	private static AbonoJMS instancia;
	
	/**
	 * Reference to the TransactionManager (Connection Manager) used to solve the given requirements requested
	 */
	private AbonoCM transactionManager;
	
	private AbonoJMS( )
	{
	
	}
	
	/**
	 * Retrieves the instance of the Singleton class: AbonoJMS
	 *
	 * @param transactionManager - instance of the TransactionManager to use in the class
	 * @return FuncionJMS - Instance of the Singleton class
	 */
	public static AbonoJMS getInstance( AbonoCM transactionManager )
	{
		instancia = instancia == null ? new AbonoJMS( ) : instancia;
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
			OnMessageResponse response = onMessageSetUp( message, CREAR_ABONOS_ASK, CREAR_ABONOS_RESPONSE );
			if( response.isAnswer( ) )
			{
				if( waiting )
				{
					this.respuesta.add( protocolToObject( response.getParams( ), ProtocoloAbono.class ) );
					this.numberApps++;
				}
			}
			else if( response.getQueue( ) != null )
			{
				String protocol = CREAR_ABONOS_RESPONSE + CONNECTOR + transactionManager.createAbono( protocolToObject( response.getParams( ), ProtocoloAbono.class ) );
				enqueueResponse( response.getQueue( ), protocol );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
	
	/**
	 * Method that sends a request protocol, by the request in the {@link #CREAR_ABONOS_ASK} constant with 1 parameters
	 *
	 * @throws JMSException    Caso de JMSException
	 * @throws NamingException Caso de NamingException
	 */
	@Override
	void sendMessage( ) throws NamingException, JMSException
	{
		
		sendMessageSetUp( CREAR_ABONOS_ASK, abono.toString( ) );
	}
	
	/**
	 * Updates the abono of the AbonoJMS by the one given by parameter
	 *
	 * @param abono The new abono of the AbonoJMS
	 */
	public void setAbono( ProtocoloAbono abono )
	{
		this.abono = abono;
	}
}
