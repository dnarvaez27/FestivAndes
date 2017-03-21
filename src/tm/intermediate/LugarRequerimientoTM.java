package tm.intermediate;

import dao.intermediate.DAOLugarRequerimiento;
import tm.TransactionManager;
import vos.Lugar;
import vos.RequerimientoTecnico;

import java.sql.SQLException;
import java.util.List;

public class LugarRequerimientoTM extends TransactionManager
{
	public LugarRequerimientoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createLugarRequerimiento( Long idLugar, Long idRequerimiento ) throws SQLException
	{
		DAOLugarRequerimiento dao = new DAOLugarRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.createEntryLugarRequerimiento( idLugar, idRequerimiento );
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
	
	public List<RequerimientoTecnico> getRequerimientosFromLugar( Long idLugar ) throws SQLException
	{
		List<RequerimientoTecnico> list;
		DAOLugarRequerimiento dao = new DAOLugarRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getRequermientosFromLugar( idLugar );
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
	
	public List<Lugar> getLugaresWithRequerimiento( Long idRequerimiento ) throws SQLException
	{
		List<Lugar> list;
		DAOLugarRequerimiento dao = new DAOLugarRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getLugaresWithRequerimiento( idRequerimiento );
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
	
	public void deleteLugarRequerimiento( Long idLugar, Long idRequerimiento ) throws SQLException
	{
		DAOLugarRequerimiento dao = new DAOLugarRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEntryLugarRequerimiento( idLugar, idRequerimiento );
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