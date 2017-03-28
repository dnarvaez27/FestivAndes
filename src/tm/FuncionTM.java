package tm;

import dao.DAOFuncion;
import vos.Funcion;
import vos.reportes.RFC3;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FuncionTM extends TransactionManager
{
	public FuncionTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Funcion createFuncion( Long id, String password, Funcion accesibilidad ) throws SQLException
	{
		Funcion ac;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createFuncion( id, password, accesibilidad );
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
	
	public List<Funcion> getFuncionesFrom( Long idEspectaculo ) throws SQLException
	{
		List<Funcion> list;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getFuncionesFrom( idEspectaculo );
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
	
	public List<Funcion> getFunciones( ) throws SQLException
	{
		List<Funcion> list;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getFunciones( );
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
	
	public Funcion getFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		Funcion ac;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getFuncion( fecha, idLugar );
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
	
	public Funcion updateFuncion( Long idUsuario, String password, Date fecha, Long idLugar, Funcion accesibilidad ) throws SQLException
	{
		Funcion ac;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateFuncion( idUsuario, password, fecha, idLugar, accesibilidad );
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
	
	public void deleteFuncion( Date fecha, Long idLugar ) throws SQLException
	{
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteFuncion( fecha, idLugar );
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
	
	public List<Funcion> generarReporte1( String nombreCategoria, String nombreCompania, String ciudad, String pais, String nombreEspectaculo, String idioma, String fechaInicio, String fechaFin, Integer duracionInicio, Integer duracionFin, String lugar, String accesoEspecial, String publicoObjetivo, List<String> order, boolean asc ) throws SQLException
	{
		List<Funcion> list;
		DAOFuncion dao = new DAOFuncion( );
		
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.RFC1( nombreCategoria, nombreCompania, ciudad, pais, nombreEspectaculo, idioma, fechaInicio, fechaFin, duracionInicio, duracionFin, lugar, accesoEspecial, publicoObjetivo, order, asc );
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
		return list;
	}
	
	public RFC3 generarReporte3( Date fecha, Long idLugar ) throws SQLException
	{
		RFC3 rfc3;
		DAOFuncion dao = new DAOFuncion( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			rfc3 = dao.RFC3( fecha, idLugar );
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
		return rfc3;
	}
}