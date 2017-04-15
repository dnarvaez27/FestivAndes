package tm.intermediate;

import dao.intermediate.DAOCostoLocalidad;
import tm.TransactionManager;
import vos.Funcion;
import vos.Localidad;
import vos.Silla;
import vos.intermediate.CostoLocalidad;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
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
	
	private List<List<Silla.SillaExtended>> ordenarSillas( List<Silla.SillaExtended> sillas )
	{
		List<List<Silla.SillaExtended>> list = new LinkedList<>( );
		int numFila = -1;
		List<Silla.SillaExtended> fila = new LinkedList<>( );
		for( Silla.SillaExtended silla : sillas )
		{
			numFila = numFila == -1 ? silla.getNumFila( ) : numFila;
			if( numFila != silla.getNumFila( ) )
			{
				list.add( fila );
				fila = new LinkedList<>( );
				numFila = silla.getNumFila( );
			}
			fila.add( silla );
		}
		list.add( fila );
		return list;
	}
	
	public Localidad.LocalidadExtended getCostoLocalidadFrom( Date fecha, Long idLugar, Long idLocalidad ) throws SQLException
	{
		Localidad.LocalidadExtended localidad;
		DAOCostoLocalidad dao = new DAOCostoLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			localidad = new Localidad.LocalidadExtended( dao.getCostoLocalidadFromFuncion( fecha, idLugar, idLocalidad ) );
			List<Silla.SillaExtended> list = dao.getEstadoSillas( fecha, idLugar, idLocalidad );
			localidad.setSillas( ordenarSillas( list ) );
			
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