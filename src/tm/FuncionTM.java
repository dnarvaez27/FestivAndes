package tm;

import dao.DAOFuncion;
import vos.Funcion;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FuncionTM extends TransactionManager
{
	public FuncionTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Funcion createFuncion( Funcion accesibilidad ) throws SQLException
	{
		Funcion ac;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createFuncion( accesibilidad );
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
	
	public List<Funcion> getFunciones( ) throws SQLException
	{
		List<Funcion> list;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getFunciones( );
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
	
	public Funcion getFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		Funcion ac;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getFuncion( fecha, idLugar );
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
	
	public Funcion updateFuncion( Date fecha, Long idLugar, Funcion accesibilidad ) throws SQLException
	{
		Funcion ac;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateFuncion( fecha, idLugar, accesibilidad );
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
	
	public void deleteFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteFuncion( fecha, idLugar );
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