package jms;

import protocolos.ProtocoloFuncion;
import tm.FuncionCM;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.NamingException;
import java.util.List;

/**
 * @author dnarvaez27
 */
public class FuncionJMS extends JMSManager<ProtocoloFuncion>
{
	private String nombreCategoria;
	
	private String nombreCompania;
	
	private String ciudad;
	
	private String pais;
	
	private String nombreEspectaculo;
	
	private String idioma;
	
	private String fechaInicio;
	
	private String fechaFin;
	
	private Integer duracionInicio;
	
	private Integer duracionFin;
	
	private String lugar;
	
	private String accesoEspecial;
	
	private String publicoObjetivo;
	
	private List<String> order;
	
	private boolean asc;
	
	/**
	 * JMS instance, given by the Singleton pattern
	 */
	private static FuncionJMS instancia;
	
	/**
	 * Reference to the TransactionManager (Connection Manager) used to solve the given requirements requested
	 */
	private FuncionCM transactionManager;
	
	/**
	 * Retrieves the instance of the Singleton class: FuncionJMS
	 *
	 * @param transactionManager - instance of the TransactionManager to use in the class
	 * @return FuncionJMS - Instance of the Singleton class
	 */
	public static FuncionJMS getInstance( FuncionCM transactionManager )
	{
		instancia = instancia == null ? new FuncionJMS( ) : instancia;
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
			OnMessageResponse response = onMessageSetUp( message, ALL_FUNCIONES_ASK, ALL_FUNCIONES_RESPONSE );
			if( response.isAnswer( ) )
			{
				if( waiting )
				{
					this.respuesta.addAll( protocolToList( response.getParams( ), ProtocoloFuncion.class ) );
					this.numberApps++;
				}
			}
			else
			{
				String protocol = ALL_FUNCIONES_RESPONSE + CONNECTOR + listToProtocol( FuncionCM.funcionesToProtocol( transactionManager.generarReporte1( nombreCategoria, nombreCompania, ciudad, pais, nombreEspectaculo, idioma, fechaInicio, fechaFin, duracionInicio, duracionFin, lugar, accesoEspecial, publicoObjetivo, order, asc ) ) );
				enqueueResponse( response.getQueue( ), protocol );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
	
	/**
	 * Method that sends a request protocol, by the request in the {@link #ALL_FUNCIONES_ASK} constant with no parameters
	 *
	 * @throws JMSException    Caso de JMSException
	 * @throws NamingException Caso de NamingException
	 */
	@Override
	void sendMessage( ) throws NamingException, JMSException
	{
		sendMessageSetUp( ALL_FUNCIONES_ASK, "" );
	}
	
	public void setUpParams( String nombreCategoria, String nombreCompania, String ciudad, String pais, String nombreEspectaculo, String idioma, String fechaInicio, String fechaFin, Integer duracionInicio, Integer duracionFin, String lugar, String accesoEspecial, String publicoObjetivo, List<String> order, boolean asc )
	{
		this.nombreCategoria = nombreCategoria;
		this.nombreCompania = nombreCompania;
		this.ciudad = ciudad;
		this.pais = pais;
		this.nombreEspectaculo = nombreEspectaculo;
		this.idioma = idioma;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracionInicio = duracionInicio;
		this.duracionFin = duracionFin;
		this.lugar = lugar;
		this.accesoEspecial = accesoEspecial;
		this.publicoObjetivo = publicoObjetivo;
		this.order = order;
		this.asc = asc;
	}
}
