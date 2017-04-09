package tm;

import dao.DAOSilla;
import dao.intermediate.DAOLugarLocalidad;
import vos.Silla;

import java.sql.SQLException;
import java.util.List;

public class SillaCM extends TransactionManager
{
	public SillaCM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Silla createSilla( Silla silla ) throws SQLException// throws Exception
	{
		DAOSilla dao = new DAOSilla( );
		DAOLugarLocalidad daoLugarLocalidad = new DAOLugarLocalidad( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoLugarLocalidad.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( daoLugarLocalidad.getLocalidadFromLugar( silla.getIdLugar( ), silla.getIdLocalidad( ) ) == null )
			{
				connection.rollback( );
				throw new SQLException( "No existe la localidad o lugar donde se agregará la silla " );
			}
			
			silla = dao.createSilla( silla );
			
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			connection.rollback( );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		finally
		{
			closeDAO( daoLugarLocalidad );
			closeDAO( dao );
		}
		return silla;
	}
	
	public List<Silla> getSillas( ) throws SQLException
	{
		List<Silla> list;
		DAOSilla dao = new DAOSilla( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getSillas( );
			
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
	
	public List<Silla> getSillasFrom( Long idLugar, Long idLocalidad ) throws SQLException
	{
		List<Silla> list;
		DAOSilla dao = new DAOSilla( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getSillasFrom( idLugar, idLocalidad );
			
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
	
	public Silla getSilla( Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad ) throws SQLException
	{
		Silla silla;
		DAOSilla dao = new DAOSilla( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			silla = dao.getSilla( num_silla, num_fila, id_lugar, id_localidad );
			
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
		return silla;
	}
	
	public Silla updateSilla( Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad, Silla silla ) throws SQLException
	{
		DAOSilla dao = new DAOSilla( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			silla = dao.updateSilla( num_silla, num_fila, id_lugar, id_localidad, silla );
			
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
		return silla;
	}
	
	public void deleteSilla( Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad ) throws SQLException
	{
		DAOSilla dao = new DAOSilla( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			dao.deleteSilla( num_silla, num_fila, id_lugar, id_localidad );
			
			connection.rollback( );
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