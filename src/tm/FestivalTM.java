package tm;

import dao.DAOFestival;
import vos.Festival;

import java.sql.SQLException;
import java.util.List;

public class FestivalTM extends TransactionManager
{
	public FestivalTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Festival createFestival( Festival accesibilidad ) throws SQLException
	{
		Festival ac;
		DAOFestival dao = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createFestival( accesibilidad );
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
		return ac;
	}
	
	public List<Festival> getFestivals( ) throws SQLException
	{
		List<Festival> list;
		DAOFestival dao = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getFestivales( );
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
	
	public Festival getFestival( Long id ) throws SQLException
	{
		Festival ac;
		DAOFestival dao = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getFestival( id );
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
		return ac;
	}
	
	public Festival updateFestival( Long id, Festival accesibilidad ) throws SQLException
	{
		Festival ac;
		DAOFestival dao = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateFestival( id, accesibilidad );
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
		return ac;
	}
	
	public void deleteFestival( Long id ) throws SQLException
	{
		DAOFestival dao = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteFestival( id );
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