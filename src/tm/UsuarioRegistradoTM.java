package tm;

import dao.DAOUsuarioRegistrado;
import vos.UsuarioRegistrado;

import java.sql.SQLException;
import java.util.List;

public class UsuarioRegistradoTM extends TransactionManager
{
	public UsuarioRegistradoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public UsuarioRegistrado createUsuarioRegistrado( Long id, String password, UsuarioRegistrado accesibilidad ) throws SQLException
	{
		UsuarioRegistrado ur;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ur = dao.createUsuarioRegistrado( id, password, accesibilidad );
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
		return ur;
	}
	
	public List<UsuarioRegistrado> getUsuarioRegistrados( ) throws SQLException
	{
		List<UsuarioRegistrado> list;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getUsuarioRegistrados( );
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
	
	public UsuarioRegistrado getUsuarioRegistrado( Long id ) throws SQLException
	{
		UsuarioRegistrado ur;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ur = dao.getUsuarioRegistrado( id );
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
		return ur;
	}
	
	public UsuarioRegistrado updateUsuarioRegistrado( Long id, UsuarioRegistrado accesibilidad ) throws SQLException
	{
		UsuarioRegistrado ur;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ur = dao.updateUsuarioRegistrado( id, accesibilidad );
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
		return ur;
	}
	
	public void deleteUsuarioRegistrado( Long id ) throws SQLException
	{
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteUsuarioRegistrado( id );
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
