package tm;

import dao.DAOUsuario;
import dao.DAOUsuarioRegistrado;
import vos.Usuario;
import vos.UsuarioRegistrado;

import java.sql.SQLException;
import java.util.List;

public class UsuarioRegistradoCM extends TransactionManager
{
	public UsuarioRegistradoCM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public UsuarioRegistrado createUsuarioRegistrado( Long id, String password, UsuarioRegistrado usuarioRegistrado ) throws SQLException
	{
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		
		usuarioRegistrado.setRol( Usuario.USUARIO_REGISTRADO );
		daoUsuario.createUsuario( usuarioRegistrado );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			usuarioRegistrado = dao.createUsuarioRegistrado( usuarioRegistrado );
			
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
		return usuarioRegistrado;
	}
	
	public List<UsuarioRegistrado> getUsuarioRegistrados( ) throws SQLException
	{
		List<UsuarioRegistrado> list;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getUsuarioRegistrados( );
			
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
	
	public UsuarioRegistrado getUsuarioRegistrado( Long id, String tipo ) throws SQLException
	{
		UsuarioRegistrado usuarioRegistrado;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			usuarioRegistrado = dao.getUsuarioRegistrado( id, tipo );
			
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
		return usuarioRegistrado;
	}
	
	public UsuarioRegistrado updateUsuarioRegistrado( Long id, String tipo, UsuarioRegistrado usuarioRegistrado ) throws SQLException
	{
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			usuarioRegistrado = dao.updateUsuarioRegistrado( id, tipo, usuarioRegistrado );
			
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
		return usuarioRegistrado;
	}
	
	public void deleteUsuarioRegistrado( Long id, String tipo ) throws SQLException
	{
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteUsuarioRegistrado( id, tipo );
			
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
	}
}
