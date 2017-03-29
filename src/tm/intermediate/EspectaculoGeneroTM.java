package tm.intermediate;

import dao.DAOGenero;
import dao.intermediate.DAOEspectaculoGenero;
import tm.TransactionManager;
import vos.Espectaculo;
import vos.Genero;

import java.sql.SQLException;
import java.util.List;

public class EspectaculoGeneroTM extends TransactionManager
{
	public EspectaculoGeneroTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createEspectaculoGenero( Long idEspectaculo, Long idGenero ) throws SQLException
	{
		DAOEspectaculoGenero dao = new DAOEspectaculoGenero( );
		DAOGenero daoGenero = new DAOGenero( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoGenero.setConnection( this.connection );
			dao.setConnection( this.connection );
			dao.createEntryEG( idEspectaculo, idGenero );
			
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
	
	public List<Genero> getGenerosFrom( Long idEspectaculo ) throws SQLException
	{
		List<Genero> list;
		DAOEspectaculoGenero dao = new DAOEspectaculoGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getGenerosFromEspectaculo( idEspectaculo );
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
	
	public List<Espectaculo> getEspectaculoWithGenero( Long idGenero ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculoGenero dao = new DAOEspectaculoGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculosFromGenero( idGenero );
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
	
	public void deleteEspectaculoGenero( Long idEspectaculo, Long idGenero ) throws SQLException
	{
		DAOEspectaculoGenero dao = new DAOEspectaculoGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEntryEG( idEspectaculo, idGenero );
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