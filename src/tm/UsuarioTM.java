package tm;

import java.sql.SQLException;
import java.util.List;

import dao.DAOUsuario;
import vos.Usuario;

public class UsuarioTM extends TransactionManager {
	public UsuarioTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Usuario createUsuario( Usuario usuario ) throws SQLException
	{
		Usuario us;
		DAOUsuario dao = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			us = dao.createUsuario( usuario );
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
		return us;
	}
	
	public List<Usuario> getUsuarios( ) throws SQLException
	{
		List<Usuario> list;
		DAOUsuario dao = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getUsuarios( );
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
	
	public Usuario getUsuario( Long id ) throws SQLException
	{
		Usuario us;
		DAOUsuario dao = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			us = dao.getUsuario( id );
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
		return us;
	}
	
	public Usuario udpateUsuario( Usuario usuario ) throws SQLException
	{
		Usuario us;
		DAOUsuario dao = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			us = dao.updateUsuario( usuario );
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
		return us;
	}
	
	public void deleteUsuario( Long id ) throws SQLException
	{
		DAOUsuario dao = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteUsuario( id );
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
