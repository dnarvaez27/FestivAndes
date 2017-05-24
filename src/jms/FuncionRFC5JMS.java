package jms;

import protocolos.Protocolo;
import protocolos.ProtocoloRFC5;
import tm.CompaniaDeTeatroCM;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.NamingException;
import java.util.List;

/**
 * Created by dnarv on 23/05/2017.
 */
public class FuncionRFC5JMS extends JMSManager<ProtocoloRFC5>
{
	private String fechaInicio;
	
	private String fechaFin;
	
	private Long idCompania;
	
	private String tipoIdCompania;
	
	private String criterio;
	
	private List<ProtocoloRFC5> rfc5s;
	
	private static FuncionRFC5JMS instancia;
	
	private CompaniaDeTeatroCM transactionManager;
	
	/**
	 * Retrieves the instance of the Singleton class: FuncionJMS
	 *
	 * @param transactionManager - instance of the TransactionManager to use in the class
	 * @return FuncionJMS - Instance of the Singleton class
	 */
	public static FuncionRFC5JMS getInstance( CompaniaDeTeatroCM transactionManager )
	{
		instancia = instancia == null ? new FuncionRFC5JMS( ) : instancia;
		instancia.transactionManager = transactionManager;
		return instancia;
	}
	
	@Override
	void sendMessage( ) throws NamingException, JMSException
	{
		sendMessageSetUp( REPORTE_ASK, fieldsToProtocolo( ) );
	}
	
	@Override
	public void onMessage( Message message )
	{
		try
		{
			OnMessageResponse response = onMessageSetUp( message, REPORTE_ASK, REPORTE_RESPONSE );
			if( response.isAnswer( ) )
			{
				if( waiting )
				{
					this.respuesta.addAll( protocolToList( response.getParams( ), ProtocoloRFC5.class ) );
					this.numberApps++;
				}
			}
			else
			{
				String[] split = response.getParams( ).split( Protocolo.SEPARADOR_PARAMS );
				rfc5s = transactionManager.requerimiento5Local( split[ 0 ], split[ 1 ], Long.parseLong( split[ 2 ] ), split[ 3 ], split[ 4 ] );
				String protocol = REPORTE_RESPONSE + CONNECTOR + listToProtocol( rfc5s );
				enqueueResponse( response.getQueue( ), protocol );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
	
	private String fieldsToProtocolo( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		sBuilder.append( fechaInicio ).append( Protocolo.SEPARADOR_PARAMS );
		sBuilder.append( fechaFin ).append( Protocolo.SEPARADOR_PARAMS );
		sBuilder.append( idCompania ).append( Protocolo.SEPARADOR_PARAMS );
		sBuilder.append( tipoIdCompania ).append( Protocolo.SEPARADOR_PARAMS );
		sBuilder.append( criterio ).append( Protocolo.SEPARADOR_PARAMS );
		
		return sBuilder.toString( );
	}
	
	/**
	 * Updates the fechaInicio of the FuncionRFC5JMS by the one given by parameter
	 *
	 * @param fechaInicio The new fechaInicio of the FuncionRFC5JMS
	 */
	public void setFechaInicio( String fechaInicio )
	{
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Updates the fechaFin of the FuncionRFC5JMS by the one given by parameter
	 *
	 * @param fechaFin The new fechaFin of the FuncionRFC5JMS
	 */
	public void setFechaFin( String fechaFin )
	{
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Updates the idCompania of the FuncionRFC5JMS by the one given by parameter
	 *
	 * @param idCompania The new idCompania of the FuncionRFC5JMS
	 */
	public void setIdCompania( Long idCompania )
	{
		this.idCompania = idCompania;
	}
	
	/**
	 * Updates the tipoIdCompania of the FuncionRFC5JMS by the one given by parameter
	 *
	 * @param tipoIdCompania The new tipoIdCompania of the FuncionRFC5JMS
	 */
	public void setTipoIdCompania( String tipoIdCompania )
	{
		this.tipoIdCompania = tipoIdCompania;
	}
	
	/**
	 * Updates the criterio of the FuncionRFC5JMS by the one given by parameter
	 *
	 * @param criterio The new criterio of the FuncionRFC5JMS
	 */
	public void setCriterio( String criterio )
	{
		this.criterio = criterio;
	}
}
