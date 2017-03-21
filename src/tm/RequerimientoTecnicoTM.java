package tm;

import java.sql.SQLException;
import java.util.List;

import dao.DAORequerimientoTecnico;
import vos.RequerimientoTecnico;

public class RequerimientoTecnicoTM extends TransactionManager{
	public RequerimientoTecnicoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public RequerimientoTecnico createRequerimientoTecnico( RequerimientoTecnico requerimientoTecnico ) throws SQLException
	{
		RequerimientoTecnico req;
		DAORequerimientoTecnico dao = new DAORequerimientoTecnico( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			req = dao.createRequerimientoTecnico( requerimientoTecnico );
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
		return req;
	}
	
	public List<RequerimientoTecnico> getRequerimientosTecnicos( ) throws SQLException
	{
		List<RequerimientoTecnico> list;
		DAORequerimientoTecnico dao = new DAORequerimientoTecnico( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getRequerimientosTecnicos( );
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
	
	public RequerimientoTecnico getRequerimientoTecnico( Long id ) throws SQLException
	{
		RequerimientoTecnico req;
		DAORequerimientoTecnico dao = new DAORequerimientoTecnico( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			req = dao.getRequerimientoTecnico( id );
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
		return req;
	}
	
	public RequerimientoTecnico updateRequerimientoTecnico(Long id, RequerimientoTecnico requerimientoTecnico ) throws SQLException
	{
		RequerimientoTecnico req;
		DAORequerimientoTecnico dao = new DAORequerimientoTecnico( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			req = dao.updateRequerimientoTecnico( id, requerimientoTecnico );
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
		return req;
	}
	
	public void deleteRequerimientoTecnico( Long id ) throws SQLException
	{
		DAORequerimientoTecnico dao = new DAORequerimientoTecnico( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteRequerimientoTecnico( id );
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
