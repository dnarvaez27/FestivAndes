package tm.intermediate;

import java.sql.SQLException;
import java.util.List;

import dao.intermediate.DAOLugarLocalidad;
import tm.TransactionManager;
import vos.Lugar;
import vos.intermediate.LugarLocalidad;
import vos.Localidad;

public class LugarLocalidadTM extends TransactionManager {
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
			dao.setConnection( this.connection );
			dao.createEntryLugarLocalidad( ll );
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
	}
	
	public List<Localidad> getLocalidadsFromLugar( Long idLugar ) throws SQLException
	{
		List<Localidad> list;
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getLocalidadesFromLugar( idLugar );
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
	
	public List<Lugar> getLugaresWithLocalidad( Long idLocalidad ) throws SQLException
	{
		List<Lugar> list;
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getLugaresWithLocalidad( idLocalidad );
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
	
	public void deleteLugarLocalidad( Long idLugar, Long idLocalidad ) throws SQLException
	{
		DAOLugarLocalidad dao = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEntryLugarLocalidad( idLugar, idLocalidad );
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
