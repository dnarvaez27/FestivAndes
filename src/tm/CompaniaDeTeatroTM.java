package tm;

import dao.DAOCompaniaDeTeatro;
import vos.CompaniaDeTeatro;

import java.sql.SQLException;
import java.util.List;

public class CompaniaDeTeatroTM extends TransactionManager
{
	public CompaniaDeTeatroTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public CompaniaDeTeatro createCompaniaDeTeatro( CompaniaDeTeatro accesibilidad ) throws SQLException
	{
		CompaniaDeTeatro ac;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createCompaniaDeTeatro( accesibilidad );
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
	
	public List<CompaniaDeTeatro> getCompaniaDeTeatros( ) throws SQLException
	{
		List<CompaniaDeTeatro> list;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getCompaniaDeTeatros( );
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
	
	public CompaniaDeTeatro getCompaniaDeTeatro( Long id ) throws SQLException
	{
		CompaniaDeTeatro ac;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getCompaniaDeTeatro( id );
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
	
	public CompaniaDeTeatro updateCompaniaDeTeatro( Long id, CompaniaDeTeatro accesibilidad ) throws SQLException
	{
		CompaniaDeTeatro ac;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateCompaniaDeTeatro( id, accesibilidad );
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
	
	public void deleteCompaniaDeTeatro( Long id ) throws SQLException
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteCompaniaDeTeatro( id );
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