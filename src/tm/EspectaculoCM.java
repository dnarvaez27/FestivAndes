package tm;

import dao.DAOEspectaculo;
import dao.DAOFestival;
import dao.DAOUsuario;
import dao.intermediate.DAOOfrecen;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;
import vos.Festival;
import vos.Usuario;
import vos.reportes.RFC4;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EspectaculoCM extends TransactionManager
{
	public EspectaculoCM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Espectaculo createEspectaculo( Long id, String tipo, String password, Espectaculo espectaculo ) throws Exception
	{
		DAOEspectaculo dao = new DAOEspectaculo( );
		DAOOfrecen daoOfrecen = new DAOOfrecen( );
		DAOUsuario daoUsuario = new DAOUsuario( );
		DAOFestival daoFestival = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUsuario.setConnection( this.connection );
			daoFestival.setConnection( this.connection );
			daoOfrecen.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( daoUsuario.isUserRole( id, tipo, password, Usuario.USUARIO_ADMINISTRADOR ) )
			{
				Festival festival = daoFestival.getFestival( espectaculo.getIdFestival( ) );
				if( festival == null )
				{
					throw new Exception( String.format( "El festival con id: %s, no existe en el sistema", espectaculo.getIdFestival( ) ) );
				}
				
				espectaculo = dao.createEspectaculo( espectaculo );
				for( CompaniaDeTeatro companiaDeTeatro : espectaculo.getCompanias( ) )
				{
					daoOfrecen.createEntryOfrecen( companiaDeTeatro.getIdentificacion( ), espectaculo.getId( ) );
				}
				
				connection.commit( );
			}
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
			closeDAO( daoOfrecen );
			closeDAO( daoUsuario );
		}
		return espectaculo;
	}
	
	public List<Espectaculo> getEspectaculos( Long idFestival ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getEspectaculos( idFestival );
			
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
	
	public List<Espectaculo> getEspectaculos( ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getEspectaculos( );
			
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
	
	public Espectaculo getEspectaculo( Long idFestival, Long id ) throws SQLException
	{
		Espectaculo espectaculo;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			espectaculo = dao.getEspectaculo( idFestival, id );
			
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
		return espectaculo;
	}
	
	public Espectaculo updateEspectaculo( Long idFestival, Long id, Espectaculo espectaculo ) throws SQLException
	{
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			espectaculo = dao.updateEspectaculo( idFestival, id, espectaculo );
			
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
		return espectaculo;
	}
	
	public void deleteEspectaculo( Long idFestival, Long idEspectaculo ) throws SQLException
	{
		DAOEspectaculo dao = new DAOEspectaculo( );
		DAOOfrecen daoOfrecen = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			daoOfrecen.setConnection( this.connection );
			
			dao.deleteEspectaculo( idFestival, idEspectaculo );
			
			for( CompaniaDeTeatro companiaDeTeatro : daoOfrecen.getCompaniasWhoOffer( idEspectaculo ) )
			{
				daoOfrecen.deleteEntryOfrecen( companiaDeTeatro.getIdentificacion( ), idEspectaculo );
			}
			
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
			closeDAO( daoOfrecen );
		}
	}
	
	public RFC4 generarReporte( Long idFestival, Long id ) throws SQLException
	{
		RFC4 req;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			req = dao.generarReporte( idFestival, id );
			
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
		return req;
	}
	
	public List<Espectaculo> getEspectaculosPopulares( Date fInicio, Date fFin ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getEspectaculosPopulares( fInicio, fFin );
			
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
		return list;
	}
}