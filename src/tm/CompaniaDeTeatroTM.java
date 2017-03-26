package tm;

import dao.DAOCompaniaDeTeatro;
import dao.DAOUsuario;
import vos.CompaniaDeTeatro;
import vos.Usuario;

import java.sql.SQLException;
import java.util.List;

public class CompaniaDeTeatroTM extends TransactionManager
{
	public CompaniaDeTeatroTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public CompaniaDeTeatro createCompaniaDeTeatro( Long id, String password, CompaniaDeTeatro companiaDeTeatro ) throws SQLException
	{
		CompaniaDeTeatro ac;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			companiaDeTeatro.setRol( Usuario.USUARIO_COMPANIA );
			companiaDeTeatro.setTipoIdentificacion( CompaniaDeTeatro.TIPO_ID );
			
			this.connection = getConnection( );
			connection.setAutoCommit( false );
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			daoUsuario.createUsuario( companiaDeTeatro );
			ac = dao.createCompaniaDeTeatro( id, password, companiaDeTeatro );
			
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		finally
		{
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
		return ac;
	}
	
	public List<CompaniaDeTeatro> getCompaniaDeTeatros( ) throws SQLException
	{
		List<CompaniaDeTeatro> list;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getCompaniaDeTeatros( );
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
	
	public CompaniaDeTeatro getCompaniaDeTeatro( Long id ) throws SQLException
	{
		CompaniaDeTeatro ac;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getCompaniaDeTeatro( id );
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
	
	public CompaniaDeTeatro updateCompaniaDeTeatro( Long id, CompaniaDeTeatro usuario ) throws SQLException
	{
		CompaniaDeTeatro ac;
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			usuario.setRol( Usuario.USUARIO_COMPANIA );
			
			this.connection = getConnection( );
			connection.setAutoCommit( false );
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			daoUsuario.updateUsuario( id, usuario );
			ac = dao.updateCompaniaDeTeatro( id, usuario );
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
	
	public void deleteCompaniaDeTeatro( Long id ) throws SQLException
	{
		DAOCompaniaDeTeatro dao = new DAOCompaniaDeTeatro( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			connection.setAutoCommit( false );
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			dao.deleteCompaniaDeTeatro( id );
			daoUsuario.deleteUsuario( id, CompaniaDeTeatro.TIPO_ID );
			
			connection.commit( );
		}
		catch( SQLException e )
		{
			System.err.println( "SQLException:" + e.getMessage( ) );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		catch( Exception e )
		{
			System.err.println( "GeneralException:" + e.getMessage( ) );
			e.printStackTrace( );
			connection.rollback( );
			throw e;
		}
		finally
		{
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
	}
}