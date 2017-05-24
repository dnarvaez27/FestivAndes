package tm;

import dao.DAOCompaniaDeTeatro;
import dao.DAOUsuario;
import exceptions.IncompleteReplyException;
import exceptions.NonReplyException;
import jms.CompaniaDeTeatroJMS;
import jms.JMSConstantes;
import protocolos.ProtocoloCompania;
import vos.CompaniaDeTeatro;
import vos.Usuario;
import vos.UsuarioRegistrado;
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
		protocoloCompania.setResponse( 0 );
		
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( dao.getCompaniaDeTeatro( id ) != null )
			{
				dao.deleteCompaniaDeTeatro( id );
				daoUsuario.deleteUsuario( id, CompaniaDeTeatro.TIPO_ID );
				protocoloCompania.setResponse( 1 );
				
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
			jms.setUpJMSManager( NUMBER_APPS, QUEUE_COMPANIA, JMSConstantes.TOPIC_COMPANIA_GLOBAL );
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
}