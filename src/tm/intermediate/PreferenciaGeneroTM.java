package tm.intermediate;

import java.sql.SQLException;
import java.util.List;

import dao.intermediate.DAOPreferenciaGenero;
import tm.TransactionManager;
import vos.Genero;
import vos.UsuarioRegistrado;

public class PreferenciaGeneroTM extends TransactionManager{

	public PreferenciaGeneroTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createPreferenciaGenero( Long idUsuario, Long idGenero ) throws SQLException
	{
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.createEntryPreferenciaGenero( idUsuario, idGenero );
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
	}
	
	public List<Genero> getGeneroesPreferidosByUser( Long idUsuario ) throws SQLException
	{
		List<Genero> list;
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getGeneroesPreferidosByUser( idUsuario );
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException: " + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException: " + e.getMessage( ) );
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
			dao.setConnection( this.connection );
			list = dao.getUsersWhoPrefer( idGenero );
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException: " + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException: " + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return list;
	}
	
	public Genero getPreferedGeneroByUsar( Long idUsuario, Long idGenero ) throws SQLException
	{
		Genero genero;
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			genero = dao.getGeneroPreferidoUser( idUsuario, idGenero );
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
		return genero;
	}
	
	public void deletePreferenciaGenero( Long idUsuario, Long idGenero ) throws SQLException
	{
		DAOPreferenciaGenero dao = new DAOPreferenciaGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deletePreferenciaGenero( idUsuario, idGenero );
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			e.printStackTrace( );
			throw e;
		}
		finally
		{
			closeDAO( dao );
		}
	}
}
