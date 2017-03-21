package tm;

import dao.DAOEspectaculo;
import vos.Espectaculo;

import java.sql.SQLException;
import java.util.List;

public class EspectaculoTM extends TransactionManager
{
	public EspectaculoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Espectaculo createEspectaculo( Espectaculo accesibilidad ) throws SQLException
	{
		Espectaculo ac;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createEspectaculo( accesibilidad );
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
	
	public List<Espectaculo> getEspectaculos( ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculos( );
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
	
	public Espectaculo getEspectaculo( Long id ) throws SQLException
	{
		Espectaculo ac;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getEspectaculo( id );
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
	
	public Espectaculo updateEspectaculo( Long id, Espectaculo accesibilidad ) throws SQLException
	{
		Espectaculo ac;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateEspectaculo( id, accesibilidad );
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
	
	public void deleteEspectaculo( Long id ) throws SQLException
	{
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEspectaculo( id );
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