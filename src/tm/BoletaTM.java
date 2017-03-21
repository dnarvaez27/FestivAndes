package tm;

import dao.DAOBoleta;
import vos.Boleta;

import java.sql.SQLException;
import java.util.List;

public class BoletaTM extends TransactionManager
{
	public BoletaTM( String contextPathP )
	{
		super( contextPathP );
	}
	
	public Boleta createBoleta( Boleta accesibilidad ) throws SQLException
	{
		Boleta ac;
		DAOBoleta dao = new DAOBoleta( );
		try
		{
			this.connection = getConnection( );
			dao.setConnection( this.connection );
			ac = dao.createBoleta( accesibilidad );
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