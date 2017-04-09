package tm.intermediate;

import dao.intermediate.DAOLugarLocalidad;
import tm.TransactionManager;
import vos.Localidad;
import vos.Lugar;
import vos.intermediate.LugarLocalidad;

import java.sql.SQLException;
import java.util.List;

public class LugarLocalidadTM extends TransactionManager
{
	public LugarLocalidadTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createLugarLocalidad( LugarLocalidad ll ) throws SQLException
	{
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.createEntryLugarLocalidad( ll );
			
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
	
	public List<Localidad> getLocalidadsFromLugar( Long idLugar ) throws SQLException
	{
		List<Localidad> list;
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getLocalidadesFromLugar( idLugar );
			
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
	
	public Localidad getLocalidad( Long idLugar, Long idLocalidad ) throws SQLException
	{
		Localidad localidad;
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			localidad = dao.getLocalidadFromLugar( idLugar, idLocalidad );
			
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
		return localidad;
	}
	
	public List<Lugar> getLugaresWithLocalidad( Long idLocalidad ) throws SQLException
	{
		List<Lugar> list;
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getLugaresWithLocalidad( idLocalidad );
			
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
	
	public LugarLocalidad updateLugarLocalidad( Long idLugar, Long idLocalidad, LugarLocalidad lugarLocalidad ) throws SQLException
	{
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			lugarLocalidad = dao.updateLugarLocalidad( idLugar, idLocalidad, lugarLocalidad );
			
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
		return lugarLocalidad;
	}
	
	public void deleteLugarLocalidad( Long idLugar, Long idLocalidad ) throws SQLException
	{
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteEntryLugarLocalidad( idLugar, idLocalidad );
			
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