package tm;

import dao.DAOAccesibilidad;
import vos.Accesibilidad;

import java.sql.SQLException;
import java.util.List;

public class AccesibilidadCM extends TransactionManager
{
	public AccesibilidadCM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Accesibilidad createAccesibilidad( Accesibilidad accesibilidad ) throws SQLException
	{
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			accesibilidad = dao.createAccesibilidad( accesibilidad );
			
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
		return accesibilidad;
	}
	
	public List<Accesibilidad> getAccesibilidades( ) throws SQLException
	{
		List<Accesibilidad> list;
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getAccesibilidades( );
			
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
	
	public Accesibilidad getAccesibilidad( Long id ) throws SQLException
	{
		Accesibilidad ac;
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			ac = dao.getAccesibilidad( id );
			
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
		return ac;
	}
	
	public Accesibilidad updateAccesibilidad( Long id, Accesibilidad accesibilidad ) throws SQLException
	{
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			accesibilidad = dao.updateAccesibilidad( id, accesibilidad );
			
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
		return accesibilidad;
	}
	
	public void deleteAccesibilidad( Long id ) throws SQLException
	{
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteAccesibilidad( id );
			
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