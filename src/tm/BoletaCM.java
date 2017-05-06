package tm;

import dao.DAOBoleta;
import dao.DAOFuncion;
import dao.DAOUsuario;
import utilities.SQLUtils;
import vos.Boleta;
import vos.Silla;
import vos.Usuario;

import java.sql.SQLException;
import java.util.*;

public class BoletaCM extends TransactionManager
{
	public BoletaCM( String contextPathP )
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
	
	public Boleta createBoleta( Long id, String tipo, String password, Boleta boleta ) throws SQLException
	{
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
			if( daoUsuario.isUserRole( id, tipo, password, Usuario.USUARIO_REGISTRADO, Usuario.USUARIO_NO_REGISTRADO ) && dao.verificarCompraBoleta( boleta ) )
			{
				boleta = dao.createBoleta( boleta );
				connection.commit( );
			}
			else
			{
				connection.rollback( );
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
			closeDAO( daoUsuario );
			closeDAO( dao );
		}
		return boleta;
	}
	
	private Integer[] verificarEspacio( List<Silla> disponibles, Integer cantidadSolicitada )
	{
		Integer[] info = null;
		
		int cantidadActual = 0;
		
		int filaActual = -1;
		Integer fila = -1;
		
		Iterator<Silla> it = disponibles.iterator( );
		while( ( cantidadActual < cantidadSolicitada ) && it.hasNext( ) )
		{
			Silla silla = it.next( );
			fila = silla.getNumFila( );
			
			filaActual = filaActual == -1 ? fila : filaActual;
			
			if( fila != filaActual )
			{
				filaActual = fila;
				cantidadActual = 0;
			}
			cantidadActual++;
		}
		if( fila != -1 && cantidadActual >= cantidadSolicitada )
		{
			Silla s = it.next( );
			info = new Integer[ 2 ];
			info[ 0 ] = fila;
			info[ 1 ] = s.getNumSilla( );
		}
		
		return info;
	}
	
	public List<Boleta> createBoletas( Long id, String tipo, Date fechaFuncion, Long idLugarFuncion, Long idLugarLocalidad, Integer cantBoletas ) throws Exception
	{
		List<Boleta> list = new LinkedList<>( );
		DAOBoleta dao = new DAOBoleta( );
		DAOFuncion daoFuncion = new DAOFuncion( );
		DAOUsuario daoUser = new DAOUsuario( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			daoUser.setConnection( this.connection );
			daoFuncion.setConnection( this.connection );
			dao.setConnection( this.connection );
			
			if( daoUser.isUserRole( id, tipo, password, Usuario.USUARIO_REGISTRADO ) )
			{
				List<Silla> disponibles = daoFuncion.getSillasDisponibles( fechaFuncion, idLugarFuncion, idLugarLocalidad );
				Integer[] info = verificarEspacio( disponibles, cantBoletas );
				
				if( info == null )
				{
					connection.rollback( );
					throw new Exception( "No hay cupo suficiente para la compra de boletas en sillas contiguas" );
				}
				Boleta boleta = new Boleta( );
				boleta.setIdUsuario( id );
				boleta.setTipoIdUsuario( tipo );
				boleta.setFecha( fechaFuncion );
				boleta.setIdLugar( idLugarFuncion );
				boleta.setIdLocalidad( idLugarLocalidad );
				boleta.setNumeroFila( ( long ) info[ 0 ] );
				
				int cant = 0;
				Long numBoleta = dao.getNextBoleta( );
				for( long s = info[ 1 ] - cantBoletas; cant < cantBoletas; s++, cant++ )
				{
					boleta.setNumBoleta( numBoleta++ );
					boleta.setNumeroSilla( s );
					list.add( new Boleta( dao.createBoleta( boleta ) ) );
				}
				
				connection.commit( );
			}
			else
			{
				connection.rollback( );
				throw new Exception( "La acción debe ser realizada por un Usuario Registrado" );
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
			closeDAO( daoUser );
			closeDAO( dao );
		}
		return list;
	}
	
	public List<Boleta> getBoletas( ) throws SQLException
	{
		List<Boleta> list;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getBoletas( );
			
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
	
	public List<Boleta> getBoletasFrom( Long idUsuario, String tipo ) throws SQLException
	{
		List<Boleta> list;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			list = dao.getBoletasFrom( idUsuario, tipo );
			
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
	
	public Boleta getBoleta( Long id ) throws SQLException
	{
		Boleta boleta;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			boleta = dao.getBoleta( id );
			
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
		return boleta;
	}
	
	public Boleta updateBoleta( Long numBoleta, Boleta boleta ) throws SQLException
	{
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			boleta = dao.updateBoleta( numBoleta, boleta );
			
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
		return boleta;
	}
	
	public void deleteBoleta( Long id ) throws Exception
	{
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			this.connection.setAutoCommit( false );
			
			dao.setConnection( this.connection );
			
			Boleta boleta = dao.getBoleta( id );
			if( boleta != null )
			{
				Calendar hoy = Calendar.getInstance( );
				Calendar c = SQLUtils.DateUtils.dateToCalendar( boleta.getFecha( ) );
				c.add( Calendar.DAY_OF_MONTH, -5 );
				if( hoy.before( c ) || hoy.equals( c ) )
				{
					dao.deleteBoleta( id );
					connection.commit( );
				}
				else
				{
					connection.rollback( );
					throw new Exception( "La boleta no puede ser devuelta, en menos de 5 días se realiza la funcion" );
				}
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
		}
	}
}