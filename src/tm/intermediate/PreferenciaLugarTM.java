package tm.intermediate;

import dao.intermediate.DAOPreferenciaLugar;
import tm.TransactionManager;
import vos.Lugar;
import vos.UsuarioRegistrado;

import java.sql.SQLException;
import java.util.List;

public class PreferenciaLugarTM extends TransactionManager
{
	public PreferenciaLugarTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createPreferenciaLugar( Long idUsuario, String tipo, Long idLugar ) throws SQLException
	{
		DAOPreferenciaLugar dao = new DAOPreferenciaLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.createEntryPreferenciaLugar( idLugar, idUsuario, tipo );
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
	
	public List<Lugar> getLugaresPreferidosByUser( Long idUsuario, String tipo ) throws SQLException
	{
		List<Lugar> list;
		DAOPreferenciaLugar dao = new DAOPreferenciaLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getLugaresPreferidosByUser( idUsuario, tipo );
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
	
	public List<UsuarioRegistrado> getUsersWhoPrefer( Long idLugar ) throws SQLException
	{
		List<UsuarioRegistrado> list;
		DAOPreferenciaLugar dao = new DAOPreferenciaLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getUsersWhoPreferLugar( idLugar );
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
	
	public Lugar getPreferedLugarByUsar( Long idUsuario, String tipo, Long idLugar ) throws SQLException
	{
		Lugar lugar;
		DAOPreferenciaLugar dao = new DAOPreferenciaLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			lugar = dao.getLugarPreferidoUser( idUsuario, tipo, idLugar );
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
		return lugar;
	}
	
	public void deletePreferenciaLugar( Long idUsuario, String tipo, Long idLugar ) throws SQLException
	{
		DAOPreferenciaLugar dao = new DAOPreferenciaLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deletePreferenciaLugar( idUsuario, tipo, idLugar );
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