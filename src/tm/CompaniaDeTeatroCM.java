package tm;

import dao.DAOCompaniaDeTeatro;
import dao.DAOFuncion;
import dao.DAOUsuario;
import dao.intermediate.DAOOfrecen;
import exceptions.IncompleteReplyException;
import exceptions.NonReplyException;
import jms.CompaniaDeTeatroJMS;
import jms.FuncionRFC5JMS;
import jms.JMSConstantes;
import protocolos.ProtocoloCompania;
import protocolos.ProtocoloRFC5;
import vos.*;
import vos.reportes.RFC8;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CompaniaDeTeatroCM extends TransactionManager
{
	public CompaniaDeTeatroCM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public CompaniaDeTeatro createCompaniaDeTeatro( Long id, String tipo, String password, CompaniaDeTeatro companiaDeTeatro ) throws SQLException
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		
		companiaDeTeatro.setRol( Usuario.USUARIO_COMPANIA );
		companiaDeTeatro.setTipoIdentificacion( CompaniaDeTeatro.TIPO_ID );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( daoUsuario.isUserRole( id, tipo, password, Usuario.USUARIO_ADMINISTRADOR ) )
			{
				daoUsuario.createUsuario( companiaDeTeatro );
				companiaDeTeatro = dao.createCompaniaDeTeatro( companiaDeTeatro );
				
				connection.commit( );
			}
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
		return companiaDeTeatro;
	}
	
	public List<CompaniaDeTeatro> getCompaniaDeTeatros( ) throws SQLException
	{
		List<CompaniaDeTeatro> list;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getCompaniaDeTeatros( );
			
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException: " + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException: " + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return list;
	}
	
	public CompaniaDeTeatro getCompaniaDeTeatro( Long id ) throws SQLException
	{
		CompaniaDeTeatro companiaDeTeatro;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			companiaDeTeatro = dao.getCompaniaDeTeatro( id );
			
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return companiaDeTeatro;
	}
	
	public CompaniaDeTeatro updateCompaniaDeTeatro( Long id, CompaniaDeTeatro usuario ) throws SQLException
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		
		usuario.setRol( Usuario.USUARIO_COMPANIA );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			daoUsuario.updateUsuario( id, usuario );
			usuario = dao.updateCompaniaDeTeatro( id, usuario );
			
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return usuario;
	}
	
	public ProtocoloCompania deleteCompaniaDeTeatro( Long id ) throws Exception
	{
		ProtocoloCompania protocoloCompania = new ProtocoloCompania( );
		protocoloCompania.setAppName( APP );
		protocoloCompania.setResponse( 0D );
		
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		DAOOfrecen daoOfrecen = new DAOOfrecen( );
		DAOFuncion daoFuncion = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			daoOfrecen.setConnection( this.connection );
			daoFuncion.setConnection( this.connection );
			
			if( dao.getCompaniaDeTeatro( id ) != null )
			{
				dao.deleteCompaniaDeTeatro( id );
				daoUsuario.deleteUsuario( id, CompaniaDeTeatro.TIPO_ID );
				
				double sum = 0;
				List<Espectaculo> espectaculos = daoOfrecen.getEspectaculosFromCompania( id );
				for( Espectaculo espectaculo : espectaculos )
				{
					for( Funcion funcion : daoFuncion.getFuncionesFrom( espectaculo.getId( ) ) )
					{
						if( daoFuncion.validarCancelacion( funcion.getFecha( ), funcion.getIdLugar( ) ) )
						{
							sum += daoFuncion.cantidadDevuelta( funcion.getFecha( ), funcion.getIdLugar( ) );
						}
					}
				}
				
				protocoloCompania.setResponse( sum );
				
				connection.commit( );
			}
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
		return protocoloCompania;
	}
	
	@SuppressWarnings( "unchecked" )
	public List<ProtocoloCompania> deleteCompaniaDeTeatroRemote( Long id ) throws Exception
	{
		List<ProtocoloCompania> list = new LinkedList<>( );
		try
		{
			list.add( deleteCompaniaDeTeatro( id ) );
			
			CompaniaDeTeatroJMS jms = new CompaniaDeTeatroJMS( );
			jms.setUpJMSManager( NUMBER_APPS, QUEUE_COMPANIA, QUEUE_COMPANIA_RESPONSE, JMSConstantes.TOPIC_COMPANIA_GLOBAL );
			jms.setIdCompania( id );
			jms.setTipoIdCompania( CompaniaDeTeatro.TIPO_ID );
			list.addAll( jms.getResponse( ) );
		}
		catch( NonReplyException e )
		{
			throw new IncompleteReplyException( "No Reply from apps", list );
		}
		catch( IncompleteReplyException e )
		{
			List<ProtocoloCompania> partialResponse = ( List<ProtocoloCompania> ) e.getPartialResponse( );
			list.addAll( partialResponse );
			throw new IncompleteReplyException( "Incomplete Reply:", partialResponse );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			if( connection != null )
			{
				connection.close( );
			}
		}
		return list;
	}
	
	public RFC8 informacionCompania( Long idCompania, String tipo, String password ) throws Exception
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		RFC8 resp;
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( daoUsuario.isUserRole( idCompania, tipo, password, Usuario.USUARIO_COMPANIA ) )
			{
				resp = dao.informacionCompania( idCompania );
				connection.commit( );
			}
			else
			{
				throw new Exception( "Este usuario no tiene permisos para esta operacion" );
			}
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
		return resp;
	}
	
	public List<RFC8> informacionTodasCompanias( Long id, String tipo, String password ) throws Exception
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		List<RFC8> resp;
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( daoUsuario.isUserRole( id, tipo, password, Usuario.USUARIO_ADMINISTRADOR ) )
			{
				List<CompaniaDeTeatro> ct = dao.getCompaniaDeTeatros( );
				
				resp = new LinkedList<>( );
				for( CompaniaDeTeatro c : ct )
				{
					resp.add( dao.informacionCompania( c.getIdentificacion( ) ) );
				}
				
				connection.commit( );
			}
			else
			{
				throw new Exception( "Este usuario no tiene permisos para esta operacion" );
			}
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
		return resp;
	}
	
	public List<UsuarioRegistrado> rfc9( Long idCompania, Date fInicio, Date fEnd ) throws SQLException
	{
		List<UsuarioRegistrado> list;
		
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			this.connection.setTransactionIsolation( Connection.TRANSACTION_READ_COMMITTED );
			
			dao.setConnection( this.connection );
			list = dao.rfc9( idCompania, fInicio, fEnd );
			
			this.connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return list;
	}
	
	public List<UsuarioRegistrado> rfc10( Long idCompania, Date fInicio, Date fEnd ) throws SQLException
	{
		List<UsuarioRegistrado> list;
		
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			this.connection.setTransactionIsolation( Connection.TRANSACTION_READ_COMMITTED );
			
			dao.setConnection( this.connection );
			list = dao.rfc10( idCompania, fInicio, fEnd );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return list;
	}
	
	// -------------------------------------------------------------------------------------------------
	
	public List<ProtocoloRFC5> requerimiento5Remoto( String fechaInic, String fechaFin, Long idUsuario, String tipoId, String criterio ) throws Exception
	{
		List<ProtocoloRFC5> lista = new LinkedList<>( );
		
		try
		{
			this.connection = getConnection( );
			
			lista.addAll( requerimiento5Local( fechaInic, fechaFin, idUsuario, tipoId, criterio ) );
			
			FuncionRFC5JMS jms = FuncionRFC5JMS.getInstance( this );
			
			jms.setUpJMSManager( NUMBER_APPS, QUEUE_REPORTE, QUEUE_REPORTE_RESPONSE, JMSConstantes.TOPIC_REPORTE_GLOBAL );
			
			lista.addAll( jms.getResponse( ) );
			connection.commit( );
		}
		catch( NonReplyException e )
		{
			throw new IncompleteReplyException( "No Reply from apps", lista );
		}
		catch( IncompleteReplyException e )
		{
			List<ProtocoloRFC5> partialResponse = ( List<ProtocoloRFC5> ) e.getPartialResponse( );
			lista.addAll( partialResponse );
			throw new IncompleteReplyException( "Incomplete Reply:", partialResponse );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException: " + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException: " + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			if( connection != null )
			{
				connection.close( );
			}
		}
		
		return lista;
	}
	
	public List<ProtocoloRFC5> requerimiento5Local( String fechaInic, String fechaFin, Long idUsuario, String tipoId, String criterio ) throws Exception
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		List<ProtocoloRFC5> rta = new LinkedList<>( );
		try
		{
			//////Transacción
			this.connection = getConnection( );
			dao.setConnection( connection );
			connection.setAutoCommit( false );
			connection.setTransactionIsolation( Connection.TRANSACTION_SERIALIZABLE );
			if( criterio.equals( "SITIO" ) )
			{
				rta = dao.requerimiento5Sitio( fechaInic, fechaFin, idUsuario, tipoId );
			}
			else if( criterio.equals( "TIPO_DE_SITIO" ) )
			{
				rta = dao.requerimiento5TipodeSitio( fechaInic, fechaFin, idUsuario, tipoId );
			}
			else if( criterio.equals( "ESPECTACULO" ) )
			{
				rta = dao.requerimiento5Espectaculo( fechaInic, fechaFin, idUsuario, tipoId );
			}
			else if( criterio.equals( "CATEGORIA" ) )
			{
				rta = dao.requerimiento5Categoria( fechaInic, fechaFin, idUsuario, tipoId );
			}
			connection.commit( );
			
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		finally
		{
			try
			{
				dao.cerrarRecursos( );
				if( this.connection != null )
				{
					this.connection.close( );
				}
			}
			catch( SQLException exception )
			{
				System.err.println( "SQLException closing resources:" + exception.getMessage( ) );
				exception.printStackTrace( );
				throw exception;
			}
		}
		return rta;
	}
}
