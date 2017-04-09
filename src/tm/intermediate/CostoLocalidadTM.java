package tm.intermediate;

import dao.intermediate.DAOCostoLocalidad;
import tm.TransactionManager;
import vos.Funcion;
import vos.Localidad;
import vos.intermediate.CostoLocalidad;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CostoLocalidadTM extends TransactionManager
{
	public CostoLocalidadTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public CostoLocalidad createCostoLocalidad( CostoLocalidad costoLocalidad ) throws SQLException
	{
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			costoLocalidad = dao.createEntryCostoLocalidad( costoLocalidad );
			
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
		return costoLocalidad;
	}
	
	public List<Localidad> getCostoLocalidadesFromFuncion( Date fechaFuncion, Long idLugarFuncion ) throws SQLException
	{
		List<Localidad> list;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getCostoLocalidadesFromFuncion( fechaFuncion, idLugarFuncion );
			
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
	
	public List<Funcion> getCostoLocalidadFromLocalidad( Long idLocalidad ) throws SQLException
	{
		List<Funcion> list;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getCostoLocalidadFromLocalidad( idLocalidad );
			
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
	
	public Localidad getCostoLocalidadFrom( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		Localidad localidad;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			localidad = dao.getCostoLocalidadFromFuncion( fecha, idLugar, idLocalidad );
			
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
	
	public CostoLocalidad updateCostoLocalidad( Date fecha, Long idLugar, Long idLocalidad, CostoLocalidad costoLocalidad ) throws SQLException
	{
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			costoLocalidad = dao.updateCostoLocalidad( fecha, idLugar, idLocalidad, costoLocalidad );
			
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
		return costoLocalidad;
	}
	
	public void deleteCostoLocalidad( Date fechaFuncion, Long idLugarFuncion, Long idLocalidad ) throws SQLException
	{
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteEntryCostoLocalidad( fechaFuncion, idLugarFuncion, idLocalidad );
			
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