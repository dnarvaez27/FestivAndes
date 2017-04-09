package tm.intermediate;

import dao.intermediate.DAOPreferenciaGenero;
import tm.TransactionManager;
import vos.Genero;
import vos.UsuarioRegistrado;

import java.sql.SQLException;
import java.util.List;

public class PreferenciaGeneroTM extends TransactionManager
{
	
	public PreferenciaGeneroTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createPreferenciaGenero( Long idUsuario, String tipo, Long idGenero ) throws SQLException
	{
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.createEntryPreferenciaGenero( idUsuario, tipo, idGenero );
			
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
	
	public List<Genero> getGenerosPreferidosByUser( Long idUsuario, String tipo ) throws SQLException
	{
		List<Genero> list;
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getGeneroesPreferidosByUser( idUsuario, tipo );
			
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
	
	public List<UsuarioRegistrado> getUsersWhoPrefer( Long idGenero ) throws SQLException
	{
		List<UsuarioRegistrado> list;
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getUsersWhoPreferGenero( idGenero );
			
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
	
	public Genero getPreferedGeneroByUsar( Long idUsuario, String tipo, Long idGenero ) throws SQLException
	{
		Genero genero;
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			genero = dao.getGeneroPreferidoUser( idUsuario, tipo, idGenero );
			
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
		return genero;
	}
	
	public void deletePreferenciaGenero( Long idUsuario, String tipo, Long idGenero ) throws SQLException
	{
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deletePreferenciaGenero( idUsuario, tipo, idGenero );
			
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