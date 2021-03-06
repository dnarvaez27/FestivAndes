package tm.intermediate;

import dao.intermediate.DAOOfrecen;
import tm.TransactionManager;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;

import java.sql.SQLException;
import java.util.List;

public class OfrecenTM extends TransactionManager
{
	public OfrecenTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createOfrecen( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.createEntryOfrecen( idCompania, idEspectaculo );
			
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
	
	public List<Espectaculo> getEspectaculosFrom( Long idCompania ) throws SQLException
	{
		List<Espectaculo> list;
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getEspectaculosFromCompania( idCompania );
			
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
	
	public Espectaculo getEspectaculoFrom( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		Espectaculo espectaculo;
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			espectaculo = dao.getEspectaculoFrom( idCompania, idEspectaculo );
			
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
		return espectaculo;
	}
	
	public List<CompaniaDeTeatro> getCompaniasWhoOffer( Long idEspectaculo ) throws SQLException
	{
		List<CompaniaDeTeatro> list;
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getCompaniasWhoOffer( idEspectaculo );
			
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
		return list;
	}
	
	public void deleteEntryOfrecen( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteEntryOfrecen( idCompania, idEspectaculo );
			
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