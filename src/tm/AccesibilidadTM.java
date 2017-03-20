package tm;

import dao.DAOAccesibilidad;
import vos.Accesibilidad;

import java.sql.SQLException;
import java.util.List;

public class AccesibilidadTM extends TransactionManager
{
	public AccesibilidadTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Accesibilidad createAccesibilidad( Accesibilidad accesibilidad ) throws SQLException
	{
		Accesibilidad ac;
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createAccesibilidad( accesibilidad );
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
	
	public List<Accesibilidad> getAccesibilidades( ) throws SQLException
	{
		List<Accesibilidad> list;
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getAccesibilidades( );
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
	
	public Accesibilidad getAccesibilidad( Long id ) throws SQLException
	{
		Accesibilidad ac;
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getAccesibilidad( id );
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
	
	public Accesibilidad udpateAccesibilidad( Accesibilidad accesibilidad ) throws SQLException
	{
		Accesibilidad ac;
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateAccesibilidad( accesibilidad );
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
	
	public void deleteAccesibilidad( Long id ) throws SQLException
	{
		DAOAccesibilidad dao = new DAOAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteAccesibilidad( id );
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