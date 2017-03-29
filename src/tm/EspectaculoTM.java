package tm;

import dao.DAOEspectaculo;
import dao.intermediate.DAOOfrecen;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;
import vos.reportes.RFC4;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EspectaculoTM extends TransactionManager
{
	public EspectaculoTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Espectaculo createEspectaculo( Long id, String password, Espectaculo espectaculo ) throws SQLException
	{
		Espectaculo ac;
		DAOEspectaculo dao = new DAOEspectaculo( );
		DAOOfrecen daoOfrecen = new DAOOfrecen( );
		try
		{
			this.connection = getConnection( );
			connection.setAutoCommit( false );
			
			daoOfrecen.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			ac = dao.createEspectaculo( id, password, espectaculo );
			for( CompaniaDeTeatro companiaDeTeatro : espectaculo.getCompanias( ) )
			{
				daoOfrecen.createEntryOfrecen( companiaDeTeatro.getIdentificacion( ), espectaculo.getId( ) );
			}
			
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
			closeDAO( dao );
			closeDAO( daoOfrecen );
		}
		return ac;
	}
	
	public List<Espectaculo> getEspectaculos( Long idFestival ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculos( idFestival );
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
	
	public List<Espectaculo> getEspectaculos( ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculos( );
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
	
	public Espectaculo getEspectaculo( Long idFestival, Long id ) throws SQLException
	{
		Espectaculo ac;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.getEspectaculo( idFestival, id );
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
	
	public Espectaculo updateEspectaculo( Long idFestival, Long id, Espectaculo espectaculo ) throws SQLException
	{
		Espectaculo ac;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.updateEspectaculo( idFestival, id, espectaculo );
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
			
			this.connection.commit( );
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
			closeDAO( dao );
		}
	}
	
	public RFC4 generarReporte( Long idFestival, Long id ) throws SQLException
	{
		RFC4 req;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			req = dao.generarReporte( idFestival, id );
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
		return req;
	}
	
	public List<Espectaculo> getEspectaculosPopulares( Date fInicio, Date fFin ) throws SQLException
	{
		List<Espectaculo> list;
		DAOEspectaculo dao = new DAOEspectaculo( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			list = dao.getEspectaculosPopulares( fInicio, fFin );
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
}