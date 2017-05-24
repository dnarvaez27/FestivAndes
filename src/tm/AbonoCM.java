package tm;

import dao.*;
import dao.intermediate.DAOAbonoFuncion;
import dao.intermediate.DAOCostoLocalidad;
import exceptions.IncompleteReplyException;
import exceptions.NonReplyException;
import jms.AbonoJMS;
import protocolos.ProtocoloAbono;
import utilities.SQLUtils;
import vos.Abono;
import vos.Festival;
import vos.Funcion;
import vos.intermediate.CostoLocalidad;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
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
			Calendar c = SQLUtils.DateUtils.dateToCalendar( festival.getFechaInicio( ) );
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
	
	public ProtocoloAbono createAbono( ProtocoloAbono abono ) throws Exception
	{
		ProtocoloAbono retAbono = null;
		
		DAOFestival daoFestival = new DAOFestival( );
		DAOFuncion daoFuncion = new DAOFuncion( );
		DAOLocalidad daoLocalidad = new DAOLocalidad( );
		DAOCostoLocalidad daoCostoLocalidad = new DAOCostoLocalidad( );
		DAOEspectaculo daoEspectaculo = new DAOEspectaculo( );
		
		try
		{
			try
			{
				this.connection = getConnection( );
				daoFestival.setConnection( this.connection );
				daoFuncion.setConnection( this.connection );
				daoLocalidad.setConnection( this.connection );
				daoCostoLocalidad.setConnection( this.connection );
				daoEspectaculo.setConnection( this.connection );
				
				retAbono = abonoToProtocol( createAbono( protocolToAbono( abono, daoFestival, daoLocalidad, daoFuncion, daoCostoLocalidad ) ), daoFestival, daoFuncion, daoEspectaculo, daoLocalidad );
			}
			catch( SQLException e )
			{
				System.out.println( "La aplicacion no pudo crear el abono" );
				System.err.println( "SQLException: " + e.getMessage( ) );
				connection.rollback( );
				e.printStackTrace( );
			}
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
			closeDAO( daoFestival );
			closeDAO( daoFuncion );
			closeDAO( daoLocalidad );
			closeDAO( daoCostoLocalidad );
			closeDAO( daoEspectaculo );
		}
		return retAbono;
	}
	
	@SuppressWarnings( "unchecked" )
	public List<ProtocoloAbono> createAbonoRemote( ProtocoloAbono abono ) throws Exception
	{
		List<ProtocoloAbono> list = new LinkedList<>( );
		
		DAOFestival daoFestival = new DAOFestival( );
		DAOFuncion daoFuncion = new DAOFuncion( );
		DAOLocalidad daoLocalidad = new DAOLocalidad( );
		DAOCostoLocalidad daoCostoLocalidad = new DAOCostoLocalidad( );
		DAOEspectaculo daoEspectaculo = new DAOEspectaculo( );
		
		try
		{
			try
			{
				this.connection = getConnection( );
				daoFestival.setConnection( this.connection );
				daoFuncion.setConnection( this.connection );
				daoLocalidad.setConnection( this.connection );
				daoCostoLocalidad.setConnection( this.connection );
				daoEspectaculo.setConnection( this.connection );
				
				list.add( createAbono( abono ) );
			}
			catch( SQLException e )
			{
				System.out.println( "La aplicacion no pudo crear el abono" );
				System.err.println( "SQLException: " + e.getMessage( ) );
				connection.rollback( );
				e.printStackTrace( );
			}
			
			AbonoJMS jms = AbonoJMS.getInstance( this );
			jms.setUpJMSManager( NUMBER_APPS, QUEUE_ABONO, AbonoJMS.TOPIC_ABONOS_GLOBAL );
			jms.setAbono( abono );
			list.addAll( jms.getResponse( ) );
		}
		catch( NonReplyException e )
		{
			throw new IncompleteReplyException( "No Reply from apps", list );
		}
		catch( IncompleteReplyException e )
		{
			List<ProtocoloAbono> partialResponse = ( List<ProtocoloAbono> ) e.getPartialResponse( );
			list.addAll( partialResponse );
			throw new IncompleteReplyException( "Incomplete Reply:", partialResponse );
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
			closeDAO( daoFestival );
			closeDAO( daoFuncion );
			closeDAO( daoLocalidad );
			closeDAO( daoCostoLocalidad );
			closeDAO( daoEspectaculo );
			
			if( connection != null )
			{
				connection.close( );
			}
		}
		return list;
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
			
			Calendar c = SQLUtils.DateUtils.dateToCalendar( festival.getFechaInicio( ) );
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
	
	private static ProtocoloAbono abonoToProtocol( Abono abono, DAOFestival daoFestival, DAOFuncion daoFuncion, DAOEspectaculo daoEspectaculo, DAOLocalidad daoLocalidad ) throws SQLException
	{
		ProtocoloAbono prot = null;
		Festival festival = daoFestival.getFestival( abono.getIdFestival( ) );
		if( festival != null )
		{
			prot = new ProtocoloAbono( );
			prot.setNombreFestival( festival.getNombre( ) );
			prot.setDescuento( abono.getDescuento( ) );
			prot.setIdUsuario( abono.getIdUsuario( ) );
			prot.setTipoId( abono.getTipoId( ) );
			prot.setFunciones( funcionAbonoToProtocol( abono.getFunciones( ), daoFuncion, daoEspectaculo, daoLocalidad ) );
			prot.setAppName( APP );
		}
		return prot;
	}
	
	private static List<ProtocoloAbono.FuncionAbono> funcionAbonoToProtocol( List<CostoLocalidad> funciones, DAOFuncion daoFuncion, DAOEspectaculo daoEspectaculo, DAOLocalidad daoLocalidad ) throws SQLException
	{
		List<ProtocoloAbono.FuncionAbono> list = new LinkedList<>( );
		for( CostoLocalidad costoLocalidad : funciones )
		{
			ProtocoloAbono.FuncionAbono fa = new ProtocoloAbono.FuncionAbono( );
			
			Funcion funcion = daoFuncion.getFuncion( costoLocalidad.getFecha( ), costoLocalidad.getIdLugar( ) );
			
			fa.setFecha( SQLUtils.DateUtils.timeToString( costoLocalidad.getFecha( ) ) );
			fa.setNombreEspectaculo( daoEspectaculo.getEspectaculo( funcion.getIdEspectaculo( ) ).getNombre( ) );
			fa.setNombreLocalidad( daoLocalidad.getLocalidad( costoLocalidad.getIdLocalidad( ) ).getNombre( ) );
			
			list.add( fa );
		}
		return list;
	}
	
	private static List<CostoLocalidad> protocolFuncionesToCostoLocalidad( List<ProtocoloAbono.FuncionAbono> funciones, DAOLocalidad daoLocalidad, DAOFuncion daoFuncion, DAOCostoLocalidad daoCostoLocalidad ) throws SQLException
	{
		List<CostoLocalidad> list = new LinkedList<>( );
		for( ProtocoloAbono.FuncionAbono funcion : funciones )
		{
			CostoLocalidad cl = new CostoLocalidad( );
			cl.setFecha( SQLUtils.DateUtils.parseDateTime( funcion.getFecha( ) ) );
			cl.setIdLocalidad( daoLocalidad.searchLocalidad( funcion.getNombreLocalidad( ) ).getId( ) );
			cl.setIdLugar( daoFuncion.search( funcion.getNombreEspectaculo( ), cl.getFecha( ) ).getIdLugar( ) );
			cl.setCosto( daoCostoLocalidad.search( cl.getFecha( ), cl.getIdLugar( ), cl.getIdLocalidad( ) ).getCosto( ) );
			
			list.add( cl );
		}
		return list;
	}
	
	private static Abono protocolToAbono( ProtocoloAbono protocoloAbono, DAOFestival daoFestival, DAOLocalidad daoLocalidad, DAOFuncion daoFuncion, DAOCostoLocalidad daoCostoLocalidad ) throws SQLException
	{
		Abono abono = new Abono( );
		abono.setTipoId( protocoloAbono.getTipoId( ) );
		abono.setIdUsuario( protocoloAbono.getIdUsuario( ) );
		abono.setDescuento( Float.parseFloat( String.valueOf( protocoloAbono.getDescuento( ) ) ) );
		abono.setFunciones( protocolFuncionesToCostoLocalidad( protocoloAbono.getFunciones( ), daoLocalidad, daoFuncion, daoCostoLocalidad ) );
		abono.setIdFestival( daoFestival.searchFestival( protocoloAbono.getNombreFestival( ) ).getId( ) );
		return abono;
	}
}
