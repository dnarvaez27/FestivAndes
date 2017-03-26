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
	
	public CostoLocalidad createCostoLocalidad( CostoLocalidad accesibilidad ) throws SQLException
	{
		CostoLocalidad ac;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createEntryCostoLocalidad( accesibilidad );
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
	
	public List<Localidad> getCostoLocalidadesFromFuncion( Date fechaFuncion, Long idLugarFuncion ) throws SQLException
	{
		List<Localidad> list;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getCostoLocalidadesFromFuncion( fechaFuncion, idLugarFuncion );
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
	
	public List<Funcion> getCostoLocalidadFromLocalidad( Long idLocalidad ) throws SQLException
	{
		List<Funcion> list;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			list = dao.getCostoLocalidadFromLocalidad( idLocalidad );
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
	
	public Localidad getCostoLocalidadFrom( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		Localidad costoLocalidad;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			costoLocalidad = dao.getCostoLocalidadFromFuncion( fecha, idLugar, idLocalidad );
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
		return costoLocalidad;
	}
	
	public CostoLocalidad updateCostoLocalidad( Date fecha, Long idLugar, Long idLocalidad, CostoLocalidad costoLocalidad ) throws SQLException
	{
		CostoLocalidad ac;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateCostoLocalidad( fecha, idLugar, idLocalidad, costoLocalidad );
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
	
	public void deleteCostoLocalidad( Date fechaFuncion, Long idLugarFuncion, Long idLocalidad ) throws SQLException
	{
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteEntryCostoLocalidad( fechaFuncion, idLugarFuncion, idLocalidad );
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