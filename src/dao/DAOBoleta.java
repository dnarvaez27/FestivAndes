package dao;

import vos.Boleta;
import vos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOBoleta extends DAO
{
	public DAOBoleta( )
	{
		super( );
	}
	
	public Boleta createBoleta( Long id, String password, Boleta object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM USUARIOS " );
		sql.append( String.format( "WHERE identificacion = %s ", id ) );
		sql.append( String.format( "AND password = '%s'", password ) );
		sql.append( String.format( "AND ( rol = '%s'", Usuario.USUARIO_REGISTRADO ) );
		sql.append( String.format( " OR rol = '%s' )", Usuario.USUARIO_NO_REGISTRADO ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) && verificarCompraBoleta( object ) )
		{
			Usuario user = DAOUsuario.restultToAccesibildiad( rs );
			
			sql = new StringBuilder( );
			sql.append( "INSERT INTO BOLETAS " );
			sql.append( "( num_boleta, num_silla, num_fila, id_localidad, id_lugar, fecha, id_usuario, id_tipo ) " );
			sql.append( "VALUES " );
			sql.append( "( " );
			sql.append( String.format( "%s, ", object.getNumBoleta( ) ) );
			sql.append( String.format( "%s, ", object.getIdNumeroSilla( ) ) );
			sql.append( String.format( "%s, ", object.getIdNumeroFila( ) ) );
			sql.append( String.format( "%s, ", object.getIdLocalidad( ) ) );
			sql.append( String.format( "%s, ", object.getIdLugar( ) ) );
			sql.append( String.format( "%s, ", toDate( object.getFecha( ) ) ) );
			sql.append( String.format( "%s, ", user.getIdentificacion( ) ) );
			sql.append( String.format( "'%s' ", user.getTipoIdentificacion( ) ) );
			sql.append( ")" );
			
			System.out.println( sql.toString( ) );
			
			s = connection.prepareStatement( sql.toString( ) );
			recursos.add( s );
			s.execute( );
		}
		rs.close( );
		s.close( );
		return object;
	}
	
	private boolean verificarCompraBoleta( Boleta boleta ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM BOLETAS " );
		sql.append( String.format( "WHERE NUM_SILLA = %s ", boleta.getIdNumeroSilla( ) ) );
		sql.append( String.format( "  AND NUM_FILA = %s ", boleta.getIdNumeroFila( ) ) );
		sql.append( String.format( "  AND ID_LUGAR = %s ", boleta.getIdLugar( ) ) );
		sql.append( String.format( "  AND ID_LOCALIDAD = %s ", boleta.getIdLocalidad( ) ) );
		sql.append( String.format( "  AND fecha = %s ", toDate( boleta.getFecha( ) ) ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		boolean rta = !rs.next( );
		
		rs.close( );
		s.close( );
		return rta;
	}
	
	public List<Boleta> getBoletas( ) throws SQLException
	{
		List<Boleta> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM BOLETAS" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToBoleta( rs ) );
		}
		
		s.close( );
		return list;
	}
	
	public Boleta getBoleta( Long numBoleta ) throws SQLException
	{
		Boleta object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM BOLETAS " );
		sql.append( String.format( "WHERE num_boleta = %s", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToBoleta( rs );
		}
		s.close( );
		return object;
	}
	
	public Boleta updateBoleta( Long numBoleta, Boleta object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE BOLETAS " );
		sql.append( "SET" );
		sql.append( String.format( "num_silla = %s, ", object.getIdNumeroSilla( ) ) );
		sql.append( String.format( "num_fila = %s, ", object.getIdNumeroFila( ) ) );
		sql.append( String.format( "id_localidad = %s, ", object.getIdLocalidad( ) ) );
		sql.append( String.format( "id_lugar = %s, ", object.getIdLugar( ) ) );
		sql.append( String.format( "fecha = %s, ", toDate( object.getFecha( ) ) ) );
		sql.append( String.format( "id_usuario = %s ", object.getIdUsuario( ) ) );
		sql.append( String.format( "WHERE num_boleta = %s ", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
		return object;
	}
	
	public void deleteBoleta( Long numBoleta ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM BOLETAS " );
		sql.append( String.format( "WHERE num_boleta = %s", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	public static Boleta resultToBoleta( ResultSet rs ) throws SQLException
	{
		Boleta object = new Boleta( );
		object.setNumBoleta( rs.getLong( "num_boleta" ) );
		object.setIdNumeroSilla( rs.getLong( "num_silla" ) );
		object.setIdNumeroFila( rs.getLong( "num_fila" ) );
		object.setIdLocalidad( rs.getLong( "id_localidad" ) );
		object.setIdLugar( rs.getLong( "id_lugar" ) );
		object.setFecha( rs.getDate( "fecha" ) );
		object.setIdUsuario( rs.getLong( "id_usuario" ) );
		object.setTipoIdUsuario( rs.getString( "id_tipo" ) );
		return object;
	}
}