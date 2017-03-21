package tm;

import dao.DAOClasificacion;
import vos.Clasificacion;

import java.sql.SQLException;
import java.util.List;

public class ClasificacionTM extends TransactionManager
{
	public ClasificacionTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Clasificacion createClasificacion( Clasificacion accesibilidad ) throws SQLException
	{
		Clasificacion clas;
		DAOClasificacion dao = new DAOClasificacion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			clas = dao.createClasificacion( accesibilidad );
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
		return clas;
	}
	
	public List<Clasificacion> getClasificaciones( ) throws SQLException
	{
		List<Clasificacion> list;
		DAOClasificacion dao = new DAOClasificacion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getClasificaciones( );
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
	
	public Clasificacion getClasificacion( Long id ) throws SQLException
	{
		Clasificacion clas;
		DAOClasificacion dao = new DAOClasificacion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			clas = dao.getClasificacion( id );
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
		return clas;
	}
	
	public Clasificacion updateClasificacion( Long id, Clasificacion accesibilidad ) throws SQLException
	{
		Clasificacion clas;
		DAOClasificacion dao = new DAOClasificacion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			clas = dao.updateClasificacion( id, accesibilidad );
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
		return clas;
	}
	
	public void deleteClasificacion( Long id ) throws SQLException
	{
		DAOClasificacion dao = new DAOClasificacion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteClasificacion( id );
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