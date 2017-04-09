package tm;

import dao.DAOAbono;
import dao.DAOFestival;
import dao.DAOFuncion;
import dao.intermediate.DAOAbonoFuncion;
import utilities.DateUtils;
import vos.Abono;
import vos.Festival;
import vos.intermediate.CostoLocalidad;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Dave on 9/04/2017.
 */
public class AbonoCM extends TransactionManager
{
	public AbonoCM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Abono createAbono( Abono abono ) throws Exception
	{
		DAOAbono dao = new DAOAbono( );
		DAOAbonoFuncion daoAbonoFuncion = new DAOAbonoFuncion( );
		DAOFuncion daoFuncion = new DAOFuncion( );
		DAOFestival daoFestival = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoFestival.setConnection( this.connection );
			dao.setConnection( this.connection );
			daoFuncion.setConnection( this.connection );
			daoAbonoFuncion.setConnection( this.connection );
			
			Calendar hoy = Calendar.getInstance( );
			Festival festival = daoFestival.getFestival( abono.getIdFestival( ) );
			if( festival == null )
			{
				connection.rollback( );
				throw new Exception( String.format( "El festival %s no existe en la base de datos", abono.getIdFestival( ) ) );
			}
			Calendar c = DateUtils.dateToCalendar( festival.getFechaInicio( ) );
			c.add( Calendar.WEEK_OF_YEAR, -3 );
			if( hoy.after( c ) )
			{
				connection.rollback( );
				throw new Exception( String.format( "El festival %s inicia en menos de 3 semanas. No se puede realizar la compra del abono", festival.getId( ) ) );
			}
			dao.crearAbono( abono );
			
			for( CostoLocalidad cl : abono.getFunciones( ) )
			{
				System.out.println( cl.getFecha( ) );
				if( daoFuncion.getSillasDisponibles( cl.getFecha( ), cl.getIdLugar( ), cl.getIdLocalidad( ) ).size( ) > 0 )
				{
					daoAbonoFuncion.createEntryAbonoFuncion( abono, cl.getFecha( ), cl.getIdLugar( ), cl.getIdLocalidad( ) );
				}
				else
				{
					connection.rollback( );
					throw new Exception( String.format( "No hay más boletas disponibles en la funcion: %s en %s", cl.getFecha( ), cl.getIdLugar( ) ) );
				}
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
			closeDAO( daoFuncion );
			closeDAO( daoAbonoFuncion );
			closeDAO( dao );
		}
		return abono;
	}
	
	public List<Abono> getAbonos( ) throws SQLException
	{
		List<Abono> list;
		DAOAbono dao = new DAOAbono( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getAbonos( );
			
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
	
	public List<Abono> getAbonosFrom( Long idUsuario, String tipoId ) throws SQLException
	{
		List<Abono> list;
		DAOAbono dao = new DAOAbono( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getAbonosFrom( idUsuario, tipoId );
			
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
	
	public Abono getAbono( Long idFestival, Long idUsuario, String tipoId ) throws Exception
	{
		Abono abono;
		DAOAbono dao = new DAOAbono( );
		DAOAbonoFuncion daoAbonoFuncion = new DAOAbonoFuncion( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			daoAbonoFuncion.setConnection( this.connection );
			
			abono = dao.getAbono( idFestival, idUsuario, tipoId );
			if( abono == null )
			{
				throw new Exception( String.format( "El abono del usuario: %s.%s, para el festival %s, no existe", tipoId, idUsuario, idFestival ) );
			}
			abono.setFunciones( daoAbonoFuncion.getFuncionesFromAbono( idFestival, idUsuario, tipoId ) );
			
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
		return abono;
	}
	
	public Abono updateAbono( Long idFestival, Long idUsuario, String tipoId, Abono abono ) throws SQLException
	{
		DAOAbono dao = new DAOAbono( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			abono.setIdFestival( idFestival );
			abono.setIdUsuario( idUsuario );
			abono.setTipoId( tipoId );
			abono = dao.updateAbono( idFestival, idUsuario, tipoId, abono );
			
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
		return abono;
	}
	
	public void deleteAbono( Long idFestival, Long idUsuario, String tipoId ) throws Exception
	{
		DAOAbono dao = new DAOAbono( );
		DAOAbonoFuncion daoAbonoFuncion = new DAOAbonoFuncion( );
		DAOFestival daoFestival = new DAOFestival( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoFestival.setConnection( this.connection );
			daoAbonoFuncion.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			Festival festival = daoFestival.getFestival( idFestival );
			if( festival == null )
			{
				throw new Exception( String.format( "No existe el festival con el id %s ", idFestival ) );
			}
			
			Calendar c = DateUtils.dateToCalendar( festival.getFechaInicio( ) );
			c.add( Calendar.WEEK_OF_YEAR, -3 );
			if( Calendar.getInstance( ).after( c ) )
			{
				throw new Exception( String.format( "El festival %s comienza en menos de 3 semanas, no se puede realizar la devolución", idFestival ) );
			}
			
			daoAbonoFuncion.deleteEntriesFromAbono( idFestival, idUsuario, tipoId );
			dao.deleteAbono( idFestival, idUsuario, tipoId );
			
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
	}
}