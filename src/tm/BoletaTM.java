package tm;

import dao.DAOBoleta;
import dao.DAOUsuario;
import vos.Boleta;
import vos.Usuario;

import java.sql.SQLException;
import java.util.List;

public class BoletaTM extends TransactionManager
{
	public BoletaTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	private boolean checkUsuarioRegistrado( Long id, String tipo, DAOUsuario dao ) throws SQLException
	{
		if( dao.getUsuario( id, tipo ) == null || id == null || tipo == null )
		{
			Usuario nr = dao.getUsuarioNoRegistrado( );
			if( nr == null )
			{
				nr = Usuario.UNREGISTERED_USER;
				dao.createUsuario( nr );
			}
			return false;
		}
		return true;
	}
	
	public Boleta createBoleta( Long id, String tipo, Boleta boleta ) throws SQLException
	{
		Boleta ac;
		DAOBoleta dao = new DAOBoleta( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			daoUsuario.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( !checkUsuarioRegistrado( id, tipo, daoUsuario ) )
			{
				id = Usuario.UNREGISTERED_USER.getIdentificacion( );
				tipo = Usuario.UNREGISTERED_USER.getTipoIdentificacion( );
			}
			ac = dao.createBoleta( id, tipo, boleta );
			
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
	
	public List<Boleta> getBoletas( ) throws SQLException
	{
		List<Boleta> list;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getBoletas( );
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
	
	public List<Boleta> getBoletasFrom( Long idUsuario, String tipo ) throws SQLException
	{
		List<Boleta> list;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getBoletasFrom( idUsuario, tipo );
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
	
	public Boleta getBoleta( Long id ) throws SQLException
	{
		Boleta ac;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getBoleta( id );
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
	
	public Boleta updateBoleta( Long numBoleta, Boleta accesibilidad ) throws SQLException
	{
		Boleta ac;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateBoleta( numBoleta, accesibilidad );
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
	
	public void deleteBoleta( Long id ) throws SQLException
	{
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			dao.deleteBoleta( id );
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