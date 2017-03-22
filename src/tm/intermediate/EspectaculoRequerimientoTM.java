package tm.intermediate;

import java.sql.SQLException;
import java.util.List;

import dao.intermediate.DAOEspectaculoRequerimiento;
import tm.TransactionManager;
import vos.Espectaculo;
import vos.RequerimientoTecnico;

public class EspectaculoRequerimientoTM extends TransactionManager{
	public EspectaculoRequerimientoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public void createEspectaculoRequerimiento( Long idEspectaculo, Long idRequerimiento ) throws SQLException
	{
		DAOEspectaculoRequerimiento dao = new DAOEspectaculoRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.createEntryEG( idEspectaculo, idRequerimiento );
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
	
	public List<RequerimientoTecnico> getRequerimientosFrom( Long idEspectaculo ) throws SQLException
	{
		List<RequerimientoTecnico> list;
		DAOEspectaculoRequerimiento dao = new DAOEspectaculoRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getRequerimientoTecnicosFromEspectaculo( idEspectaculo );
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
	
	public List<Espectaculo> getEspectaculoWithRequerimiento( Long idRequerimiento ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculoRequerimiento dao = new DAOEspectaculoRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculosFromRequerimientoTecnico( idRequerimiento );
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
	
	public void deleteEspectaculoRequerimiento( Long idEspectaculo, Long idRequerimiento ) throws SQLException
	{
		DAOEspectaculoRequerimiento dao = new DAOEspectaculoRequerimiento( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEntryEG( idEspectaculo, idRequerimiento );
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