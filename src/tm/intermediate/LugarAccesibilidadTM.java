package tm.intermediate;

import dao.intermediate.DAOLugarAccesibilidad;
import tm.TransactionManager;
import vos.Accesibilidad;
import vos.Lugar;

import java.sql.SQLException;
import java.util.List;

public class LugarAccesibilidadTM extends TransactionManager
{
	public LugarAccesibilidadTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createLugarAccesibilidad( Long idLugar, Long idAccesibilidad ) throws SQLException
	{
		DAOLugarAccesibilidad dao = new DAOLugarAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.createEntryLugarAccesibilidad( idLugar, idAccesibilidad );
			
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
	
	public List<Accesibilidad> getAccesibilidadsFromLugar( Long idLugar ) throws SQLException
	{
		List<Accesibilidad> list;
		DAOLugarAccesibilidad dao = new DAOLugarAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getAccesibilidadesFromLugar( idLugar );
			
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
	
	public List<Lugar> getLugaresWithAccesibilidad( Long idAccesibilidad ) throws SQLException
	{
		List<Lugar> list;
		DAOLugarAccesibilidad dao = new DAOLugarAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getLugaresWithAccesibilidad( idAccesibilidad );
			
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
	
	public void deleteLugarAccesibilidad( Long idLugar, Long idAccesibilidad ) throws SQLException
	{
		DAOLugarAccesibilidad dao = new DAOLugarAccesibilidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteEntryLugarAccesibilidad( idLugar, idAccesibilidad );
			
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
