package tm;

import dao.DAOUsuario;
import dao.DAOUsuarioRegistrado;
import vos.Usuario;
import vos.UsuarioRegistrado;

import java.sql.SQLException;
import java.util.List;

public class UsuarioRegistradoTM extends TransactionManager
{
	public UsuarioRegistradoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public UsuarioRegistrado createUsuarioRegistrado( Long id, String password, UsuarioRegistrado usuarioRegistrado ) throws SQLException
	{
		UsuarioRegistrado ur;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			usuarioRegistrado.setRol( Usuario.USUARIO_REGISTRADO );
			daoUsuario.createUsuario( usuarioRegistrado );
			ur = dao.createUsuarioRegistrado( usuarioRegistrado, id, password );
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
			closeDAO( daoUsuario );
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
	
	public UsuarioRegistrado getUsuarioRegistrado( Long id, String tipo ) throws SQLException
	{
		UsuarioRegistrado ur;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ur = dao.getUsuarioRegistrado( id, tipo );
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
	
	public UsuarioRegistrado updateUsuarioRegistrado( Long id, String tipo, UsuarioRegistrado accesibilidad ) throws SQLException
	{
		UsuarioRegistrado ur;
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ur = dao.updateUsuarioRegistrado( id, tipo, accesibilidad );
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
	
	public void deleteUsuarioRegistrado( Long id, String tipo ) throws SQLException
	{
		DAOUsuarioRegistrado dao = new DAOUsuarioRegistrado( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteUsuarioRegistrado( id, tipo );
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
