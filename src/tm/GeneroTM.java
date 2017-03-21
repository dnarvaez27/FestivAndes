package tm;

import java.sql.SQLException;
import java.util.List;

import dao.DAOGenero;
import vos.Genero;

public class GeneroTM extends TransactionManager{
	public GeneroTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Genero createGenero( Genero genero ) throws SQLException
	{
		Genero gen;
		DAOGenero dao = new DAOGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			gen = dao.createGenero( genero );
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
		return gen;
	}
	
	public List<Genero> getGeneros( ) throws SQLException
	{
		List<Genero> list;
		DAOGenero dao = new DAOGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getGeneros( );
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
	
	public Genero getGenero( Long id ) throws SQLException
	{
		Genero gen;
		DAOGenero dao = new DAOGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			gen = dao.getGenero( id );
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
		return gen;
	}
	
	public Genero udpateGenero( Genero genero ) throws SQLException
	{
		Genero gen;
		DAOGenero dao = new DAOGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			gen = dao.updateGenero( genero );
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
		return gen;
	}
	
	public void deleteGenero( Long id ) throws SQLException
	{
		DAOGenero dao = new DAOGenero( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteGenero( id );
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
