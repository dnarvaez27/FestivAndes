package tm;

import java.sql.SQLException;
import java.util.List;

import dao.DAOLugar;
import vos.Lugar;

public class LugarTM extends TransactionManager{
	public LugarTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Lugar createLugar( Lugar lugar ) throws SQLException
	{
		Lugar l;
		DAOLugar dao = new DAOLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			l = dao.createLugar( lugar );
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
		return l;
	}
	
	public List<Lugar> getLugares( ) throws SQLException
	{
		List<Lugar> list;
		DAOLugar dao = new DAOLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getLugares( );
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
	
	public Lugar getLugar( Long id ) throws SQLException
	{
		Lugar l;
		DAOLugar dao = new DAOLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			l = dao.getLugar( id );
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
		return l;
	}
	
	public Lugar udpateLugar( Lugar lugar ) throws SQLException
	{
		Lugar l;
		DAOLugar dao = new DAOLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			l = dao.updateLugar( lugar );
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
		return l;
	}
	
	public void deleteLugar( Long id ) throws SQLException
	{
		DAOLugar dao = new DAOLugar( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteLugar( id );
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
