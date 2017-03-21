package tm;

import java.sql.SQLException;
import java.util.List;

import dao.DAOLocalidad;
import vos.Localidad;

public class LocalidadTM extends TransactionManager{
	public LocalidadTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Localidad createLocalidad( Localidad localidad ) throws SQLException
	{
		Localidad l;
		DAOLocalidad dao = new DAOLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			l = dao.createLocalidad( localidad );
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
	
	public List<Localidad> getLocalidades( ) throws SQLException
	{
		List<Localidad> list;
		DAOLocalidad dao = new DAOLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getLocalidades( );
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
	
	public Localidad getLocalidad( Long id ) throws SQLException
	{
		Localidad l;
		DAOLocalidad dao = new DAOLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			l = dao.getLocalidad( id );
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
	
	public Localidad updateLocalidad( Long id,Localidad localidad ) throws SQLException
	{
		Localidad l;
		DAOLocalidad dao = new DAOLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			l = dao.updateLocalidad(id, localidad );
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
	
	public void deleteLocalidad( Long id ) throws SQLException
	{
		DAOLocalidad dao = new DAOLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteLocalidad( id );
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
