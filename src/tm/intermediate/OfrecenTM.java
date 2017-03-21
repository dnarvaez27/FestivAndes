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
			dao.setConnection( this.connection );
			dao.createEntryOfrecen( idCompania, idEspectaculo );
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
	
	public List<Espectaculo> getEspectaculoFrom( Long idCompania ) throws SQLException
	{
		List<Espectaculo> list;
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculosFromCompania( idCompania );
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
	
	public List<CompaniaDeTeatro> getCompaniasWhoOffer( Long idEspectaculo ) throws SQLException
	{
		List<CompaniaDeTeatro> list;
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getCompaniasWhoOffer( idEspectaculo );
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
		return list;
	}
	
	public void deleteEntryOfrecen( Long idCompania, Long idEspectaculo ) throws SQLException
	{
		DAOOfrecen dao = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEntryOfrecen( idCompania, idEspectaculo );
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